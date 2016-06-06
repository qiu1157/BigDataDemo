package com.jd.test;

import com.jd.www.hbase.HbaseUtils;
import com.jd.www.util.Constants;
import com.jd.www.vo.ClickVo;

import java.io.IOException;

/**
 * Created by qiuxiangu on 2016/6/4.
 */
public class HbaseTest {
   public static void main(String[] args) throws IOException {
      HbaseUtils hbaseUtils = new HbaseUtils();
      /*hbaseUtils.createTable("AppClickOrder", new String[]{"click"});
      ClickVo clickVo = new ClickVo("00001", "HomeFloor", "abc", 10000d);
      hbaseUtils.insertClick(Constants.CLICK_TABLE, Constants.COLUMN_FAMILY, clickVo, System.currentTimeMillis() );
      */
//      ClickVo clickVo = hbaseUtils.getClick(Constants.CLICK_TABLE, Constants.COLUMN_FAMILY, "00001");
//      System.out.println(clickVo.toString());
      hbaseUtils.dropTable(Constants.CLICK_TABLE);
   }
}
