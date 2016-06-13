package com.jd.www.storm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.Vector;

/**
 * Created by qiuxiangu on 2016/6/13.
 */
public class MysqlPool {
    private static final Logger logger = LoggerFactory.getLogger(MysqlPool.class);
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://master.hadoop:3306/bigdata?characterEncoding=utf8";
    private static final String DB_USER = "bd";
    private static final String DB_PASSWORD = "bd";
    private static int MIN_NUM = 5;
    private static int MAX_NUM = 10;
    private static final int INCR_NUM = 1;
    private Vector<PooledConnection> connections = null; //存放连接池中的连接

    public MysqlPool() {

    }

    public synchronized void createPool() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        if (this.connections != null) {
            return;
        }

        //实例化连接
        Driver driver = (Driver) Class.forName(this.JDBC_DRIVER).newInstance();
        DriverManager.registerDriver(driver);
        this.connections = new Vector<PooledConnection>();
        this.createConnections(this.MIN_NUM);
    }

    /**
     * 创建数据库连接
     *
     * @param minNum
     */
    private void createConnections(int minNum) throws SQLException {
        for (int i = 0; i < minNum; i++) {
            if (this.connections.size() >= this.MAX_NUM) {
                return;
            }
            this.connections.addElement(new PooledConnection(newConnection()));
        }
    }

    /**
     * 创建一个数据库连接
     *
     * @return
     */
    private Connection newConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        //如果是第一次连接
        if (this.connections.size() == 0) {
            DatabaseMetaData metadata = conn.getMetaData();
            int dbMaxCountionsNum = metadata.getMaxConnections();
            if (dbMaxCountionsNum > 0 && this.MAX_NUM > dbMaxCountionsNum) {
                this.MAX_NUM = dbMaxCountionsNum;
            }
        }
        return conn;
    }

    public synchronized Connection getConnection() {
        Connection conn = null;
        if (this.connections == null) {
            return conn;
        }

        try {
            conn = this.getFreeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        while (conn == null) {
            try {
                this.wait(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                conn = this.getFreeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    private Connection getFreeConnection() throws SQLException {
        Connection conn = null;
        conn = this.findFreeConnection();
        if (conn == null) {
            this.createConnections(this.MIN_NUM);
            conn = this.findFreeConnection();
        }
        return conn;
    }

    /**
     * 从现有连接中查找一个可用连接
     *
     * @return
     */
    private Connection findFreeConnection() {
        Connection conn = null;
        for (int i = 0; i < this.connections.size(); i++) {
            PooledConnection pol = this.connections.get(i);
            if (!pol.isBusy()) {
                conn = pol.getConn();
                pol.setBusy(true);
                break;
            }
        }
        return conn;
    }

    /**
     * 将使用完毕的连接放回连接池
     *
     * @param conn
     */
    public void returnConnection(Connection conn) {
        if (this.connections == null) {
            return;
        }
        for (int i = 0; i < this.connections.size(); i++) {
            PooledConnection pool = this.connections.get(i);
            if (conn == pool.getConn()) {
                pool.setBusy(false);
            }
        }
    }

    /**
     * 刷新队列
     *
     * @throws InterruptedException
     * @throws SQLException
     */
    public synchronized void refreshConneciontPool() throws InterruptedException, SQLException {
        if (this.connections == null) {
            return;
        }

        for (int i = 0; i < connections.size(); i++) {
            PooledConnection pool = this.connections.get(i);
            if (pool.isBusy()) {
                this.wait(5000);
            }
            this.closeConnection(pool.getConn());
            pool.setConn(this.newConnection());
            pool.setBusy(false);
        }
    }

    /**
     * 关闭连接
     *
     * @param conn
     */
    private void closeConnection(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接池
     */
    public void closeConnectionPool() {
        if(this.connections == null) {
            return;
        }
        for (int i = 0; i < connections.size(); i++) {
            PooledConnection pool = connections.get(i);
            if (pool.isBusy()) {
                try {
                    this.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.closeConnection(pool.getConn());
            this.connections.remove(i);
        }
    }

    class PooledConnection {
        private Connection conn = null; //连接
        private boolean busy = false; //是否正在使用

        public Connection getConn() {
            return conn;
        }

        public void setConn(Connection conn) {
            this.conn = conn;
        }

        public boolean isBusy() {
            return busy;
        }

        public void setBusy(boolean busy) {
            this.busy = busy;
        }

        public PooledConnection(Connection conn) {
            this.conn = conn;
        }

    }

}
