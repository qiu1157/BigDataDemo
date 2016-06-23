package com.jd.test;



/**
 * Created by qiuxiangu on 2016/6/8.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        byte[] bytes = "foobar".getBytes();
        for(byte b : bytes) {
            System.out.println(Integer.toBinaryString(b));
        }
    }
}
