package com.jd.www.storm.trident;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.LocalDRPC;
import org.apache.storm.generated.StormTopology;

/**
 * Created by qiuxiangu on 2016/12/14.
 */
public class TridentMain {
    public static void main(String[] args) {
        Config conf = new Config();
        conf.setMaxSpoutPending(20);
        if (args.length == 0) {
            LocalDRPC drpc = new LocalDRPC();
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("wordCounter", conf, buildTopology(drpc));
            for (int i = 0; i < 100; i++) {

            }
        }
    }

    private static StormTopology buildTopology(LocalDRPC drpc) {

        return null;
    }
}
