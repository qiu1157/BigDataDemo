package com.jd.www.storm;

import com.jd.www.storm.bolt.WordCountBolt;
import com.jd.www.storm.bolt.WordNormalizerBolt;
import com.jd.www.storm.spout.RandomSentSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * Created by qiuxiangu on 2016/6/14.
 */
public class WordCountTopology {
    private static TopologyBuilder builder = new TopologyBuilder();

    public static void main(String[] args) throws InvalidTopologyException, AuthorizationException, AlreadyAliveException {
        Config config = new Config();

        builder.setSpout("RandomSentence", new RandomSentSpout(), 2);
        builder.setBolt("WordNormallizer", new WordNormalizerBolt(), 2).shuffleGrouping("RandomSentence");
        builder.setBolt("WordCount", new WordCountBolt(30), 2).fieldsGrouping("WordNormallizer", new Fields("word"));
        config.setDebug(false);

        if (args != null && args.length > 0) {
            config.setNumWorkers(1);
            StormSubmitter.submitTopology("WordCount", config, builder.createTopology());
        } else {
            config.setMaxTaskParallelism(1);
            LocalCluster cluster = new LocalCluster();
            cluster.submitTopology("WordCount-local", config, builder.createTopology());
        }
    }
}
