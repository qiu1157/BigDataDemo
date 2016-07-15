package com.jd.sz;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.net.URL;
import java.net.URLDecoder;
import java.util.regex.Pattern;


/**
 * Created by qiuxiangu on 2016/7/8.
 * 对URL统一编码，并解析url参数
 */
public class URL_Parser extends UDF {
    Pattern pattern;
    public Text evaluate(String url, String parameter) {
        if (!"".equals(url) || null == url) {
            return null;
        }
        try {
            url = URLDecoder.decode(url,  "utf-8");
            if ("".equals(parameter)) {
                return new Text(url);
            }
            URL aUrl = new URL(url);
            return new Text(aUrl.getQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
