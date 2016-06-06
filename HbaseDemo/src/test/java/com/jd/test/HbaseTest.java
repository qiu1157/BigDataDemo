package com.jd.test;

import com.jd.www.hbase.HbaseUtils;
import com.jd.www.util.Constants;

import java.io.IOException;

/**
 * Created by qiuxiangu on 2016/6/4.
 */
public class HbaseTest {
    public static void main(String[] args) throws IOException {
        HbaseUtils hbaseUtils = new HbaseUtils();
//      hbaseUtils.dropTable(Constants.CLICK_TABLE);
//      hbaseUtils.createTable(Constants.CLICK_TABLE, new String[]{"ck"});
        hbaseUtils.insertClick(Constants.CLICK_TABLE, Constants.LV1_EVENT, "1462892973549-046010ecd58c13dc27", "Home_Floor", "abcd", System.currentTimeMillis());
//      hbaseUtils.insertClick(Constants.CLICK_TABLE, Constants.LV1_EVENT, "1425724352971-db7415d8fd31fef31d","Home_Floor", "abc_dsa#1", System.currentTimeMillis());
//      ClickVo cklickVo = new ClickVo("00001", "HomeFloor", "abc", 10000d);
//      hbaseUtils.insertClick(Constants.CLICK_TABLE, Constants.COLUMN_FAMILY, clickVo, System.currentTimeMillis());
//      ClickVo clickVo = hbaseUtils.getClick(Constants.CLICK_TABLE, Constants.COLUMN_FAMILY, "00001");
//      System.out.println(clickVo.toString());
    }
}
