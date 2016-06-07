package com.jd.www.util;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by qiuxiangu on 2016/6/3.
 */
public class Constants {

    public static final String CLICK_TABLE = "AppClickOrder";
    public static final byte[] COLUMN_CK_FAMILY = Bytes.toBytes("ck");
    public static final byte[] COLUMN_AC_FAMILY = Bytes.toBytes("ac");
    public static final byte[] COLUMN_ORD_FAMILY = Bytes.toBytes("ord");

    public static final byte[] LV1_EVENT = Bytes.toBytes("lv1");
    public static final byte[] LV3_EVENT = Bytes.toBytes("lv3");
    public static final byte[] LV4_EVENT = Bytes.toBytes("lv4");

    public static final byte[] SKUID = Bytes.toBytes("sid");
    public static final byte[] REPORT_TIME = Bytes.toBytes("rt");
    public static final byte[] ORDER_ID = Bytes.toBytes("id");
    public static final byte[] ORDER_FEE = Bytes.toBytes("fee");

}
