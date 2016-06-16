package com.jd.www.storm.bolt;

import org.apache.storm.generated.GlobalStreamId;
import org.apache.storm.grouping.CustomStreamGrouping;
import org.apache.storm.task.WorkerTopologyContext;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiuxiangu on 2016/6/16.
 */
public class ModuleGrouping implements CustomStreamGrouping {
    private int numTasks = 0;
    public void prepare(WorkerTopologyContext workerTopologyContext, GlobalStreamId globalStreamId, List<Integer> list) {
        numTasks = list.size();
    }

    public List<Integer> chooseTasks(int i, List<Object> list) {
        List<Integer> boltIds = new ArrayList<Integer>();
        if (list.size() > 0){
            String str = list.get(0).toString();
            if (str.isEmpty())
                boltIds.add(0);
            else
        }
    }
}
