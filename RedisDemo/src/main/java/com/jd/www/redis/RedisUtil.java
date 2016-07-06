package com.jd.www.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by qiuxiangu on 2016/6/11 16:09.
 */
public class RedisUtil {
    private Jedis jedis;

    /*    public RedisUtil() {
            setup();
        }
        public void setup() {
            jedis = new Jedis("172.27.23.4", 6379);
        }

        public void set(String key, String value) {
            jedis.set(key, value);
        }*/
    private static final String REDIS_HOST = "Master.Hadoop";
    private static final int REDIS_PORT = 6379;
    private static int MaxActive = 3000;
    private static int MaxIdle = 1000;
    private static long MaxWait = 1500l;

    private static JedisPool pool = null;

    static {
        //创建jedis池配置实例
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(MaxIdle);
        config.setMaxWaitMillis(MaxWait);
        config.setTestOnBorrow(false);
        if (pool == null) {
            pool = new JedisPool(config, REDIS_HOST, REDIS_PORT);
        }
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

    public static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

}
