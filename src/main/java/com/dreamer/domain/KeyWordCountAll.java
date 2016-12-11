package com.dreamer.domain;

import java.util.List;

/**
 * Created by lp on 2016/11/5.
 */
public class KeyWordCountAll {
    private String key_word_ch;
    private String key_word_use;
    private List<String> key_words;
    private int count;

    public List<CountYear> getCountYears() {
        return countYears;
    }

    public void setCountYears(List<CountYear> countYears) {
        int year = 2006;
        for (int i = 0; i < countYears.size() && year <= 2016; i++) {
            CountYear countYear = countYears.get(i);
            if (countYear.getPublish_year() != year) {
                countYears.add(i, new CountYear(year, 0));
            }
            year++;
        }
        for (int i = year; i <= 2016; i++) {
            countYears.add(new CountYear(i, 0));
        }
        this.countYears = countYears;
    }

    private List<CountYear> countYears;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getKey_word_ch() {
        return key_word_ch;
    }

    public void setKey_word_ch(String key_word_ch) {
        this.key_word_ch = key_word_ch;
    }

    public String getKey_word_use() {
        return key_word_use;
    }

    public void setKey_word_use(String key_word_use) {
        this.key_word_use = key_word_use;
    }

    public List<String> getKey_words() {
        return key_words;
    }

    public void setKey_words(List<String> key_words) {
        this.key_words = key_words;
    }

    @Override
    public String toString() {
        return "KeyWordCountAll{" +
                "key_word_ch='" + key_word_ch + '\'' +
                ", key_word_use='" + key_word_use + '\'' +
                ", key_words=" + key_words +
                ", count=" + count +
                ", countYears=" + countYears +
                '}';
    }
}
