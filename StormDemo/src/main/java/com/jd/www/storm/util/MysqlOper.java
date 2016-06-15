package com.jd.www.storm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

/**
 * Created by qiuxiangu on 2016/6/14.
 */
public class MysqlOper {
    private static final Logger LOGGER = LoggerFactory.getLogger(MysqlOper.class);

    public static void insert(Map<String, Integer> map) {
        String sql = "insert into bigdata.storm_word_count(keyword,cnt) values";
        StringBuffer dealSql = new StringBuffer();
        Connection conn = null;
        PreparedStatement ps = null;
        dealSql.append(sql);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            dealSql.append("('");
            dealSql.append(entry.getKey());
            dealSql.append("',");
            dealSql.append(entry.getValue());
            dealSql.append("),");
        }
        dealSql.deleteCharAt(dealSql.length() - 1);
        dealSql.append(";");
        LOGGER.info("dealSql:{}", dealSql);
        MysqlPool pool = new MysqlPool();
        try {
            pool.createPool();
            conn = pool.getConnection();
            ps = conn.prepareStatement(dealSql.toString());
            ps.executeUpdate();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pool.returnConnection(conn);
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
