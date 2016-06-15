package com.jd.test;

/**
 * Created by qiuxiangu on 2016/6/8.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        StringBuffer sb = new StringBuffer();
        sb.append("aaa");
        sb.append(",");
        System.out.println(sb.toString());
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }
}
