package com.jd.www.redis.work;

import com.jd.jim.cli.Cluster;

import java.io.IOException;

/**
 * Created by qiuxiangu on 2016/8/18.
 */
public class RunMain {
    public static void main(String[] args) throws IOException {
        RedisUtil redisUtil = new RedisUtil();
        Cluster client = redisUtil.getClient();
/*        if ("update".equals(args[0])) {
            System.out.println("load 数据到redis");
            redisUtil.hmset("/home/recsys/qiuxg/keyword/app.txt");
        } else if ("scan".equals(args[0])) {
            System.out.println("迭代白名单:" + args[1]);
            String str = redisUtil.getWhiteMap(args[1]);
            System.out.println(str);
        } else if ("del".equals(args[0])) {
            System.out.println("删除key:" + args[1]);
            redisUtil.del(args[1]);
        } else if ("zscan".equals(args[0])) {
            List<String> list = redisUtil.getSortSet(args[1], Integer.parseInt(args[2]));
            System.out.println("迭代 sore set==" + args[1]);
            System.out.println("list size=" + list.size());
            for (String str : list) {
                System.out.println(str);
            }
        } else if ("exists".equals(args[0])) {
            redisUtil.exists(args[1]);
        }*/
    }
}
