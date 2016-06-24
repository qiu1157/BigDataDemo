package com.jd.www.redis;

import org.junit.Test;

import java.util.List;

/**
 * Created by qiuxiangu on 2016/6/23.
 */
public class RedisOperTest {
    @Test
    public void rpush() throws Exception {

    }

    @Test
    public void rpop() throws Exception {

    }

    RedisOper oper = new RedisOper();
    @Test
    public void set() throws Exception {

    }

    @Test
    public void blpop() throws Exception {
        String[] keys =new String[] {"mylist", "mylist1"};
        List<String> list = oper.blpop(keys);
        for(String str : list) {
            System.out.println(str);
        }
    }

    @Test
    public void lpush() throws Exception {
        oper.lpush("mylist", "123");
    }

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