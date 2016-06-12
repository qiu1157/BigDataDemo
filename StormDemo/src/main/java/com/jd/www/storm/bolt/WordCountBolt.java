package com.jd.www.storm.bolt;

import org.apache.storm.Config;
import org.apache.storm.Constants;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiuxiangu on 2016/6/12.
 */
public class WordCountBolt implements IRichBolt {
    private OutputCollector outputCollector;
    private Map<String, Integer> counters;
    private int topologyTickTupleFreqSecs;

    public WordCountBolt(int topologyTickTupleFreqSecs) {
        this.topologyTickTupleFreqSecs = topologyTickTupleFreqSecs;
    }

    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
        counters = new HashMap<String, Integer>();
    }

    public void execute(Tuple tuple) {
        if (isTickTuple(tuple)) {
            for(Map.Entry<String, Integer> entry : counters.entrySet()) {
                this.outputCollector.emit(new Values(String.format("%s#%s", entry.getKey(), entry.getValue())));
            }
        }else {
            String word = tuple.getString(0);
            Integer count = counters.get(word) == null ? 1 : counters.get(word) + 1;
            counters.put(word, count);
        }
    }
    public void cleanup() {

    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    public boolean isTickTuple(Tuple tuple) {
        return tuple.getSourceComponent().equals(Constants.SYSTEM_COMPONENT_ID)
                && tuple.getSourceStreamId().equals(Constants.SYSTEM_TICK_STREAM_ID);
    }
    public Map<String, Object> getComponentConfiguration() {
        HashMap<String, Object> configMap = new HashMap<String, Object>();
        configMap.put(Config.TOPOLOGY_TICK_TUPLE_FREQ_SECS, topologyTickTupleFreqSecs);
        return configMap;
    }
}
