package com.jd.www.storm.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * Created by qiuxiangu on 2016/6/12.
 */
public class WordNormalizerBolt implements IRichBolt {
    private OutputCollector outputCollector;
    private static final Logger LOGGER = LoggerFactory.getLogger(WordNormalizerBolt.class);
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    public void execute(Tuple tuple) {
        String sentence = tuple.getStringByField("word");
        String[] words = sentence.split(" ");
        for (String word : words) {
            if (!word.isEmpty()) {
//                if ("out".equals(word)) {
                    try {
                        throw new Exception("out exception");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                }
                outputCollector.emit(tuple, new Values(word));
            }
        }
        outputCollector.ack(tuple);
    }

    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("word"));
    }

    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
