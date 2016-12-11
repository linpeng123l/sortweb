package com.dreamer.service;

import com.dreamer.db.DbUtil;
import com.dreamer.domain.CountYear;
import com.dreamer.domain.KeyWordCountAll;
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
public class KeyWordService {
    static String[] notInWords = new String[]{"Data mining","Association rules","Association rule","Association rule mining","Frequent itemsets","Knowledge discovery","association rules mining","Support","Confidence","Algorithms","ALGORITHM","Frequent itemset"
            ,"DATABASES","Machine learning","PATTERNS","DISCOVERY","big data","frequent itemset mining","data warehouse","Knowledge Discovery in Databases","DATABASE","SYSTEMS","Association","SYSTEM",
            "mining","Mining methods and algorithms","Component","and association rules","KNOWLEDGE","maintenance","SETS","Data-mining"};

    public static List<KeyWordCountAll> queryKeyWordCountAlls() {
        try {
            Connection connection = DbUtil.openConnection();
            List<KeyWordCountAll> keyWordCountAlls = new ArrayList<>();
            String sql = "SELECT key_words_use, count(*) count " +
                    "FROM `key_words` WHERE key_words_use NOT IN (" + StringUtil.inString(notInWords) + ")\n" +
                    "GROUP BY key_words_use HAVING count >=10 ORDER BY count DESC";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KeyWordCountAll keyWordCount = new KeyWordCountAll();
                keyWordCount.setCount(rs.getInt("count"));
                keyWordCount.setKey_word_use(rs.getString("key_words_use"));
                System.out.println("=");

                List<CountYear> countYears = new ArrayList<>();
                String countYearSql = "SELECT publish_year, count(*) count FROM `key_words` WHERE key_words_use = '" + keyWordCount.getKey_word_use() + "' GROUP BY publish_year ORDER BY publish_year ;";
                PreparedStatement countYearPs = connection.prepareStatement(countYearSql);
                ResultSet countYearRs = countYearPs.executeQuery();
                while (countYearRs.next()) {
                    int kk =0;
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
