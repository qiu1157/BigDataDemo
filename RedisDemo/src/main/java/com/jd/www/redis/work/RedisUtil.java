package com.jd.www.redis.work;

import com.jd.jim.cli.Cluster;
import com.jd.jim.cli.JimClientFactory;
import com.jd.jim.cli.ReloadableJimClientFactory;
import com.jd.jim.cli.driver.types.ZSetTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by qiuxiangu on 2016/8/17.
 */
public class RedisUtil {
    public Cluster client;
    private static ReloadableJimClientFactory clientFactory;
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    public RedisUtil() {
        this.Init();
    }

    public void Init() {
        if (client == null) {
            synchronized (JimClientFactory.class) {
                if (client == null) {
                    clientFactory = new ReloadableJimClientFactory();
                    clientFactory.setJimUrl("jim://2789323535165389595/1809");
//                    clientFactory.setJimUrl("jim://2801425694298350571/1590");
                    client = clientFactory.getClient();
                }
            }
        }
    }

    public boolean insertSortSet(String key, String keyWord, int inc) {
        if (key == null || keyWord == null) {
            logger.error("Invaild Input");
            return false;
        }

        if (inc <= 0) {
            logger.error("inc is less then zero!");
            return false;
        }

        double result = client.zIncrBy(key, inc, keyWord);
        if (result <= 0) {
            logger.error("result less than zero");
        }else {
            return true;
        }
        return false;
    }

    public void hmset(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
        Map<String, String> map = new HashMap<String, String>();
        String line = null;
        while (( line = br.readLine()) !=null) {
            System.out.println("line=="+line);
            if(line.split("\\#\\#\\#").length > 1) {
                map.put(line.split("\\#\\#\\#")[0], line.split("\\#\\#\\#")[1]);
            }
        }
        client.hMSet("AppWhiteMap", map);
        br.close();
    }

    public String getWhiteMap(String key) {
        Map<String, String> map = client.hGetAll(key);
        logger.info("map size = {}", map.size());
        String jsonStr = com.alibaba.fastjson.JSON.toJSONString(map);
        return jsonStr;
    }

    public void del(String key) {
        client.del(key);
    }

    public  List<String> getSortSet(String key, int top) {
        List<String> list = new ArrayList<String>();
        try {
            Set<ZSetTuple> set = client.zRevRangeWithScores(key.getBytes("utf-8"), 0, top);
            logger.info("set size = {}", set.size());
            for (ZSetTuple tuple : set) {
                String val = new String(tuple.getValue(), "utf-8");
                double score = tuple.getScore();
                list.add(val + "\t" + score);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return list;
    }

    public void getSortSet(String key) {
        Set<String> set = client.zRange(key, 0 ,-1);
        for(String str : set) {
            logger.info("str={}",str);
        }
    }

    public void exists(String key) {
        boolean b = client.exists(key);
        logger.info("result:{}",b);
    }
}
