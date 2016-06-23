package com.jd.www.redis;

import org.junit.Test;

/**
 * Created by qiuxiangu on 2016/6/23.
 */
public class RedisOperTest {
    @Test
    public void append() throws Exception {
        RedisOper oper = new RedisOper();
        oper.append("myKey", "123");
    }

    @Test
    public void getValue() throws Exception {
        RedisOper oper = new RedisOper();
        System.out.println(oper.getValue("myKey"));
    }

}