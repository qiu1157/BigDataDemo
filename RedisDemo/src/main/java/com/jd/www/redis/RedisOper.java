package com.jd.www.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.List;

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
     * */

    public  void set(String key, String value) {
        jedis.set(key, value);
        returnResource(jedis);
    }

    /**
     * 获取Key的值
     * @param key
     * @return
     */
    public String getValue(String key) {
        String value =  jedis.get(key);
        returnResource(jedis);
        return value;
    }

    /**
     * value值追加
     * @param key
     * @param value
     */
    public void append(String key, String value) {
        if (jedis.exists(key)) {
            jedis.append(key, value);
        }else {
            jedis.set(key, value);
        }
        returnResource(jedis);
    }

    public List<String> blpop(String[] key) {
        try {
            LOG.info("blpop");
           return jedis.blpop(1,key);
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
}
