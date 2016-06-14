package com.jd.storm.test;

import com.jd.www.storm.util.MysqlPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by qiuxiangu on 2016/6/13.
 */
public class PoolTest {
    public static void main(String[] args) {
        Connection conn = null;
        MysqlPool pool = new MysqlPool();
        try {
            pool.createPool();
            String sql = "select * from bigdata.test";
            conn = pool.getConnection();
            PreparedStatement ps;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println(rs.getString("uname"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            pool.returnConnection(conn);
        }
    }
}
