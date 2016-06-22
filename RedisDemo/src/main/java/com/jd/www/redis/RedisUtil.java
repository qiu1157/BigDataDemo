package com.jd.www.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by qiuxiangu on 2016/6/11 16:09.
 */
public class RedisUtil {
    private static final String REDIS_HOST = "Master.Hadoop";
    private static final int REDIS_PORT = 6379;
    private static int MaxActive = 3000;
    private static int MaxIdle = 1000;
    private static long MaxWait = 1500l;

    private static JedisPool pool;
    static {
        //创建jedis池配置实例
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(MaxIdle);
        config.setMaxWaitMillis(MaxWait);

        pool = new JedisPool(config, REDIS_HOST, REDIS_PORT);
    }

    public static Jedis getJedisObject() {
        return pool.getResource();
    }

    public static void recycleJedisObject(Jedis jedis) {
        pool.returnResource(jedis);
    }
}
