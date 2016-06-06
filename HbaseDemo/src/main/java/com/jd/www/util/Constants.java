package com.jd.www.util;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by qiuxiangu on 2016/6/3.
 */
public class Constants {

    public static final String CLICK_TABLE = "AppClickOrder";
    public static final byte[] COLUMN_FAMILY = Bytes.toBytes("ck");
    public static final byte[] UUID =Bytes.toBytes("uid");
    public static final byte[] CLICK_TIME = Bytes.toBytes("ctm");
    public static final byte[] LV1_EVENT = Bytes.toBytes("lv1");
    public static final byte[] LV3_EVENT = Bytes.toBytes("lv3");
    public static final byte[] LV4_EVENT = Bytes.toBytes("lv4");
}
