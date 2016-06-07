package com.jd.www.hbase;

import com.jd.www.util.Constants;
import com.jd.www.vo.ClickVo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

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
                LOGGER.warn("{} already exist !!", tableName);
            } else {
                HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(tableName));
                for (String columnFamily : columnFamilys) {
                    HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(columnFamily);
                    hColumnDescriptor.setTimeToLive(2 * 24 * 60 * 60);
                    desc.addFamily(hColumnDescriptor );
                }
                admin.createTable(desc);
                LOGGER.info("create table {} is success!!", tableName);
            }
            admin.close();
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void dropTable(String tableName) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(configuration);
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
            LOGGER.info("{} drop success!!", tableName);
        } else {
            LOGGER.warn("{} not exists!!", tableName);
        }
        admin.close();
    }

    public boolean insertClick(String tableName, byte[] columnName, String key, String eventId, String eventParam, double ts) {
        HTable table = getTable(tableName);
        try {
            Put put = new Put(Bytes.toBytes(key));
            put.add(Constants.COLUMN_CK_FAMILY, columnName, Bytes.toBytes(String.format("%s###%s###%f", eventId, eventParam, ts)));
            table.put(put);
            return true;
        } catch (IOException e) {
            LOGGER.error("insert {} failed!", Constants.COLUMN_CK_FAMILY);
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

    public ClickVo getClick(String tableName, String key) throws IOException {
        HTable table = getTable(tableName);
        Get get = new Get(Bytes.toBytes(key));
        get.addFamily(Constants.COLUMN_CK_FAMILY);
        Result result = table.get(get);
        if (table.exists(get)) {
            ClickVo clickVo = new ClickVo();
            LOGGER.info("rowKey is exists!!");
            clickVo.setMbaMuid(key);
            for (Cell cell : result.rawCells()) {
                if (Arrays.equals(Constants.LV1_EVENT, CellUtil.cloneQualifier(cell))) {
                    String event = new String(CellUtil.cloneValue(cell));
                    clickVo.setLv1EventId(event.split("###")[0]);
                    clickVo.setLv1EventParam(event.split("###")[1]);
                    clickVo.setLv1clickTime(Double.valueOf(event.split("###")[2]));
                } else if (Arrays.equals(Constants.LV3_EVENT, CellUtil.cloneQualifier(cell))) {
                    String event = new String(CellUtil.cloneValue(cell));
                    clickVo.setLv3EventId(event.split("###")[0]);
                    clickVo.setLv3EventParam(event.split("###")[1]);
                    clickVo.setLv3clickTime(Double.valueOf(event.split("###")[2]));
                } else if (Arrays.equals(Constants.LV4_EVENT, CellUtil.cloneQualifier(cell))) {
                    String event = new String(CellUtil.cloneValue(cell));
                    clickVo.setLv4EventId(event.split("###")[0]);
                    clickVo.setLv4EventParam(event.split("###")[1]);
                    clickVo.setLv4clickTime(Double.valueOf(event.split("###")[2]));
                }
            }
            return clickVo;
        }
        table.close();
        return null;
    }

    public void deleteOneRow(String tableName, String rowKey) {
        HTable table = getTable(tableName);
        Delete del = new Delete(Bytes.toBytes(rowKey));
        try {
            table.delete(del);
        } catch (IOException e) {
            LOGGER.error("Delete rowKey {} failed!!", rowKey);
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                LOGGER.error("Delete rowKey {} close Table {} failed", rowKey, tableName);
                e.printStackTrace();
            }
        }
    }

    public void insertAddCard(String tableName, String key, String skuId, String reportTime) {
        HTable table = getTable(tableName);
        Put put = new Put(Bytes.toBytes(key));
        put.add(Constants.COLUMN_AC_FAMILY, Constants.SKUID, Bytes.toBytes(String.format("%s", skuId)));
        put.add(Constants.COLUMN_AC_FAMILY, Constants.REPORT_TIME, Bytes.toBytes(reportTime));
        try {
            table.put(put);
        } catch (Exception e) {
            LOGGER.error("insert add card is faile!! {} ", skuId);
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertOrder(String tableName, String key, String skuId, String orderId, String reportTime, Long fee) {
        HTable table = getTable(tableName);
        Put put = new Put(Bytes.toBytes(key));
        put.add(Constants.COLUMN_ORD_FAMILY, Constants.ORDER_ID, Bytes.toBytes(orderId));
        put.add(Constants.COLUMN_ORD_FAMILY, Constants.SKUID, Bytes.toBytes(String.format("%s", skuId)));
        put.add(Constants.COLUMN_ORD_FAMILY, Constants.REPORT_TIME, Bytes.toBytes(reportTime));
        put.add(Constants.COLUMN_ORD_FAMILY, Constants.ORDER_FEE, Bytes.toBytes(fee));
        try {
            table.put(put);
        } catch (Exception e) {
            LOGGER.error("insert orderId: {} skuId: {} faile!", orderId, skuId);
            e.printStackTrace();
        } finally {
            try {
                table.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setHColumnDescriptor(String tableName, byte[] columnFamily) throws IOException {
        HBaseAdmin admin = new HBaseAdmin(this.configuration);
        HTable table = getTable(tableName);
        HTableDescriptor desc = table.getTableDescriptor();
        HColumnDescriptor hColumnDescriptor = desc.getFamily(columnFamily);
        hColumnDescriptor.setTimeToLive(5);
        admin.modifyColumn(tableName, hColumnDescriptor);
    }
}
