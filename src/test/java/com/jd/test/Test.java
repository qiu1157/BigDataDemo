package com.jd.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by qiuxiangu on 2016/6/8.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
        System.out.println(df.format(new Date()));
    }
}
