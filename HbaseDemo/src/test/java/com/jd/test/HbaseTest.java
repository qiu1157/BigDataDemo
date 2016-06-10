package com.jd.test;

import com.jd.www.hbase.HbaseUtils;

import java.io.IOException;

/**
 * Created by qiuxiangu on 2016/6/4.
 */
public class HbaseTest {
    public static void main(String[] args) throws IOException {
        HbaseUtils hbaseUtils = new HbaseUtils();
        String uuid = "1425724352971-db7415d8fd31fef31d1";
        String date = "20160523 12:27:31".substring(0, 8).trim();
        String key = String.format("%s#%s", uuid, date);
        hbaseUtils.deleteAll("20160523");
//        hbaseUtils.dropTable(Constants.CLICK_TABLE);
//        hbaseUtils.createTable(Constants.CLICK_TABLE, new String[]{"ck", "ac", "ord"});
//        hbaseUtils.insertClick(Constants.CLICK_TABLE, Constants.LV1_EVENT, key, "Home_Floor", "576_0_CMSSH105677_1465274295__0_1___", 1465274651.638000d);

//        hbaseUtils.insertOrder(Constants.CLICK_TABLE, uuid, "200000084201", "19358714586", 2399l);
//        hbaseUtils.insertOrder(Constants.CLICK_TABLE, uuid, "200100086002", "19358714586", 2399l);
//        hbaseUtils.getSkuId(Constants.CLICK_TABLE, uuid, "19286654856", 2399l);
//        hbaseUtils.insertClick(Constants.CLICK_TABLE, Constants.LV3_EVENT, uuid, "M618Special_ClassHallSmall", "//sale.jd.com/m/act/4VRdmG362EbLpIx.html_00002718_00020840_2_2_1#5ac6076e13b04fb27ca43ff9dda6bd2603b22eab#8434", 1465274589.286d);

//      System.out.println(clickVo.toString());
//        ClickVo clickVo = hbaseUtils.getClick(Constants.CLICK_TABLE, uuid);
//        if (clickVo != null) {
//            System.out.println(clickVo.toString());
//        }
//        hbaseUtils.deleteOneRow(Constants.CLICK_TABLE, uuid);
    }
}
