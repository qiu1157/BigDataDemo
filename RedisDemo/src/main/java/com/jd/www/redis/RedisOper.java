package com.jd.www.redis;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.jd.www.redis.RedisUtil.getJedis;
import static com.jd.www.redis.RedisUtil.returnResource;

/**
 * Created by qiuxiangu on 2016/6/23.
 */
public class RedisOper {
    private static final Logger LOG = LoggerFactory.getLogger(RedisOper.class);
    private Jedis jedis;

    public RedisOper() {
        jedis = getJedis();
    }

    /**
     * 插入数据
     */

    public void set(String key, String value) {
        jedis.set(key, value);
        returnResource(jedis);
    }

    /**
     * 获取Key的值
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        String value = jedis.get(key);
        returnResource(jedis);
        return value;
    }

    /**
     * value值追加
     *
     * @param key
     * @param value
     */
    public void append(String key, String value) {
        if (jedis.exists(key)) {
            jedis.append(key, value);
        } else {
            jedis.set(key, value);
        }
        returnResource(jedis);
    }

    public List<String> blpop(String[] key) {
        try {
            LOG.info("blpop");
            return jedis.blpop(1, key);
        } finally {
            returnResource(jedis);
        }
    }

    public void lpush(String key, String value) {
        try {
            jedis.lpush(key, value);
        } finally {
            returnResource(jedis);
        }
    }

    public void rpush(String key, String value) {
        try {
            jedis.rpush(key, value);
        } finally {
            returnResource(jedis);
        }
    }

    public void rpop(String key) {
        try {
            jedis.rpop(key);
        } finally {
            returnResource(jedis);
        }
    }

    public void rpoplpush(String srcKey, String descKey) {
        try {
            jedis.rpoplpush(srcKey, descKey);
        } finally {
            returnResource(jedis);
        }
    }

    public String hscan() {
        ScanResult<Map.Entry<String, String>> scanResult = jedis.hscan("AppWhiteMap", "0");
        List<Map.Entry<String, String>> list = scanResult.getResult();
        Map<String, String> map = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }
        String jsonStr = JSON.toJSONString(map);
        return jsonStr;
    }

    public void hmset(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        Map<String, String> map = new HashMap<String, String>();
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println("line==" + line);
            if (line.split("\\#\\#\\#").length > 1) {
                map.put(line.split("\\#\\#\\#")[0], line.split("\\#\\#\\#")[1]);
            }
        }
        jedis.hmset("AppWhiteMap", map);
        br.close();
    }

    public List<String> getSortSet(String key, int top) {
        List<String> list = new ArrayList<String>();
        try {
            Set<Tuple> set = jedis.zrevrangeByScoreWithScores(key, 10, 0);
            for (Tuple tuple : set) {
                String val = new String(tuple.getElement());
                double score = tuple.getScore();
                list.add(val + "\t" + score);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void sadd(String key, String str) {
        jedis.sadd(key, str);
    }
}
