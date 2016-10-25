package com.jd.www.redis;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by qiuxiangu on 2016/6/23.
 */
public class RedisOperTest {
    @Test
    public void sadd() throws Exception {
        oper.sadd("myset", "test");
    }

    RedisOper oper = new RedisOper();
    @Test
    public void getSortSet() throws Exception {
        List<String> list = oper.getSortSet("myzset", 1);
        for (String str : list) {
            System.out.println(str);
        }
    }

    @Test
    public void hmset() throws Exception {
        oper.hmset("e:\\百度云同步盘\\工作同步盘\\京东商城\\app.txt");
    }




    @Test
    public void rpoplpush() throws Exception {
            oper.rpoplpush("mylist", "myoterlist");
    }

    @Test
    public void rpush() throws Exception {

    }

    @Test
    public void rpop() throws Exception {

    }


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

    @Test
    public void hscan() throws Exception {
        String jsonStr = oper.hscan();
        System.out.println(jsonStr);
        Map<String, String> map = JSON.parseObject(jsonStr, Map.class);
        for(Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey()+"=="+entry.getValue());
        }
    }

}