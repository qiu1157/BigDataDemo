package com.jd.www.hbase;

import com.jd.www.util.Constants;
import com.jd.www.vo.ClickVo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by qiuxiangu on 2016/6/3.
 */
public class HbaseUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(HbaseUtils.class);
    private Configuration configuration;
    private HConnection hConnection;

    public HbaseUtils(String zkSercers, String zkPort, String zkRoot) {
        this.configuration = HBaseConfiguration.create();
        this.configuration.set("hbase.zookeeper.quorum", zkSercers);
        this.configuration.set("hbase.zookeeper.property.clientPort", zkPort);
        this.configuration.set("zookeeper.znode.parent", zkRoot);
    }

    public HbaseUtils() {
        this.configuration = HBaseConfiguration.create();
        this.configuration.set("hbase.zookeeper.quorum", "master.hadoop");
        this.configuration.set("hbase.zookeeper.property.clientPort", "2181");
    }

    public synchronized HConnection gethConnection() throws IOException {
        if (hConnection == null) {
            hConnection = HConnectionManager.createConnection(configuration);
        }
        return hConnection;
    }

    public HTable getTable(String tableName) {
        HTable table = null;
        try {
            if (null == hConnection) {
                hConnection = gethConnection();
            }
            table = (HTable) hConnection.getTable(tableName);
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null == table) {
            throw new RuntimeException("connection hbase failed!" + tableName);
        }
        return table;
    }

    public void createTable(String tableName, String[] columnFamilys) {
        try {
            HBaseAdmin admin = new HBaseAdmin(this.configuration);
            if (admin.tableExists(tableName)) {
                LOGGER.warn(tableName + " already exist！！");
            } else {
                HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
                for (String columnFamily : columnFamilys) {
                    desc.addFamily(new HColumnDescriptor(columnFamily));
                }
                admin.createTable(desc);
                LOGGER.info("create table " + tableName + " is success！");
            }

        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean insertClick(String tableName, byte[] key, ClickVo clickVo, long ts) {
        HTable table = getTable(tableName);
        try {
            Put put = new Put(key);
            put.add(Constants.COLUMN_FAMILY, Constants.EVENT_ID, Bytes.toBytes(clickVo.getEventId()));
            put.add(Constants.COLUMN_FAMILY, Constants.EVENT_PARAM, Bytes.toBytes(clickVo.getEventParam()));
            put.add(Constants.COLUMN_FAMILY, Constants.UUID, Bytes.toBytes(clickVo.getMbaMuid()));
            put.add(Constants.COLUMN_FAMILY, Constants.CLICK_TIME, Bytes.toBytes(clickVo.getClickTime()));
            table.put(put);
            return true;
        } catch (IOException e) {
            LOGGER.error("insert "+ Constants.COLUMN_FAMILY + " failed!");
            e.printStackTrace();
            return false;
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ClickVo getClick(String tableName, byte[] key, String uuid ) throws IOException {
        HTable table = getTable(tableName);
        ResultScanner results = null;
        Scan scan = new Scan();
        scan.setFilter(new PrefixFilter(key));
        scan.addColumn(Constants.COLUMN_FAMILY, Constants.UUID);
        scan.addColumn(Constants.COLUMN_FAMILY, Constants.EVENT_ID);
        scan.addColumn(Constants.COLUMN_FAMILY, Constants.EVENT_PARAM);
        scan.addColumn(Constants.COLUMN_FAMILY, Constants.CLICK_TIME);
        results = table.getScanner(scan);
        for (Result result : results) {
            String huuid = Bytes.toString(result.getValue(Constants.COLUMN_FAMILY, Constants.UUID));
            if (uuid.equals(huuid)) {
                ClickVo click = new ClickVo(huuid
                                                            ,Bytes.toString(result.getValue(Constants.COLUMN_FAMILY, Constants.EVENT_ID))
                                                            ,Bytes.toString(result.getValue(Constants.COLUMN_FAMILY, Constants.EVENT_PARAM))
                                                            ,Bytes.toDouble(result.getValue(Constants.COLUMN_FAMILY, Constants.CLICK_TIME)));
                return click;
            }
        }
        table.close();
        return null;
    }

    /**
     * 删除表
     * @param tableName
     * @throws IOException
     */
    public void dropTable(String tableName) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(configuration);
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            LOGGER.info("{} drop success!!", tableName );
        }else {
            LOGGER.warn("打印 {} not exists!!", tableName);
        }
        admin.close();
    }
}
