package com.jd.www.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by qiuxiangu on 2016/5/28.
 */
public class EventConfig {
    private static Map<String, String> map = new HashMap<String, String>();
    private final static Logger logger = LoggerFactory.getLogger(EventConfig.class);
    static {
        InputStream is = EventConfig.class.getClassLoader().getResourceAsStream("eventId.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = null;
        try {
            while ((str = br.readLine()) != null) {
                map.put(str.split("=")[0], str.split("=")[1]);
            }
        } catch (IOException e) {
            logger.error("EventConfig read config failed!={}" ,e.getMessage());
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public int getLv(String eventId) {
        String lvStr = map.get(eventId);
        int lv = -1;
        try {
            lv = Integer.parseInt(lvStr);
        } catch (NumberFormatException e) {
            logger.error("EventConfig change type failed {}", e.getMessage());
            e.printStackTrace();
        }
        return lv;
    }

    public Set<String> getEventIds() {
        return map.keySet();
    }
}
