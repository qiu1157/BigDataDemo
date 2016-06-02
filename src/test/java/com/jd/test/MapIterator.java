package com.jd.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by qiuxiangu on 2016/6/2.
 */
public class MapIterator {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        for(int i= 0; i<1000000; i++) {
            map.put(""+i, ""+i);
        }
        long startTime = System.currentTimeMillis();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            entry.getValue();
        }

        long endTime = System.currentTimeMillis();
        for(Iterator<String> itr = map.keySet().iterator(); itr.hasNext();) {
            map.get(itr.next());
        }
        long endTime2 = System.currentTimeMillis();
        for(Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator(); itr.hasNext();) {
            Map.Entry<String, String> entry = itr.next();
            entry.getValue();
        }
        long endTime3 = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        System.out.println(endTime2 - endTime);
        System.out.println(endTime3 - endTime2);
    }
}
