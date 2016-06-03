package com.jd.www.hbase;

import com.jd.www.util.Constants;
import com.jd.www.vo.ClickVo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.*;
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

    public synchronized HConnection gethConnection() throws ZooKeeperConnectionException {
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

    public boolean insertClick(String tableName, byte[] key, ClickVo clickVo, long ts)  {
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

    public ClickVo getClick(String tableName, byte[] key ) {
        HTable table = getTable(tableName);
        ResultScanner result = null;
        Scan scan = new Scan();

        return null;
    }
}
