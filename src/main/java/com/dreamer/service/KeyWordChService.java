package com.dreamer.service;

import com.dreamer.db.DbUtil;
import com.dreamer.domain.KeyWordCountAll;
import com.dreamer.domain.CountYear;
import com.dreamer.util.StringUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lp on 2016/11/5.
 */
public class KeyWordChService {
    static String[] notInWords = new String[]{"关联规则", "数据挖掘", "频繁项集", "关联规则挖掘", "支持度", "频繁项目集", "算法", "关联分析", "知识发现", "数据仓库", "大数据", "置信度", "数据库", "项集"};

    public static List<KeyWordCountAll> queryKeyWordCountAlls() {
        try {
            Connection connection = DbUtil.openConnection();
            List<KeyWordCountAll> keyWordCountAlls = new ArrayList<>();
            String sql = "SELECT key_words_use, count(*) count " +
                    "FROM `key_words_ch` WHERE key_words_use NOT IN (" + StringUtil.inString(notInWords) + ")\n" +
                    "GROUP BY key_words_use HAVING count >=10 ORDER BY count DESC";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KeyWordCountAll keyWordCount = new KeyWordCountAll();
                keyWordCount.setCount(rs.getInt("count"));
                keyWordCount.setKey_word_use(rs.getString("key_words_use"));

                List<CountYear> countYears = new ArrayList<>();
                String countYearSql = "SELECT publish_year, count(*) count FROM `key_words_ch` WHERE key_words_use = '" + keyWordCount.getKey_word_use() + "' GROUP BY publish_year ORDER BY publish_year ;";
                PreparedStatement countYearPs = connection.prepareStatement(countYearSql);
                ResultSet countYearRs = countYearPs.executeQuery();
                while (countYearRs.next()) {
                    CountYear countYear = new CountYear();
                    countYear.setCount(countYearRs.getInt("count"));
                    countYear.setPublish_year(countYearRs.getInt("publish_year"));
                    countYears.add(countYear);
                }
                keyWordCount.setCountYears(countYears);
                keyWordCountAlls.add(keyWordCount);
            }
            return keyWordCountAlls;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("");
        }
    }

    public static void main(String[] args) {
        List<KeyWordCountAll> keyWordCountAlls = queryKeyWordCountAlls();
        for (KeyWordCountAll keyWordCountAll : keyWordCountAlls) {
            System.out.println(keyWordCountAll);
        }
    }
}
