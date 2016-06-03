package com.jd.www.util;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by qiuxiangu on 2016/6/3.
 */
public class Constants {

    public static final byte[] COLUMN_FAMILY = Bytes.toBytes("click");
    public static final byte[] EVENT_ID = Bytes.toBytes("eventId");
    public static final byte[] UUID =Bytes.toBytes("uuid");
    public static final byte[] EVENT_PARAM = Bytes.toBytes("eventParam");
    public static final byte[] CLICK_TIME = Bytes.toBytes("clickTime");
}
