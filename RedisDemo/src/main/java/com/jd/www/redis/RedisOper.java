package com.jd.www.redis;

import redis.clients.jedis.Jedis;

import static com.jd.www.redis.RedisUtil.getJedis;
import static com.jd.www.redis.RedisUtil.returnResource;

/**
 * Created by qiuxiangu on 2016/6/23.
 */
public class RedisOper {
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

    public void append(String key, String value) {
        if (jedis.exists(key)) {
            jedis.append(key, value);
        }else {
            jedis.set(key, value);
        }
        returnResource(jedis);
    }

}
