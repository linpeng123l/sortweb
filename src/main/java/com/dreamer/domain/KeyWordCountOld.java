package com.dreamer.domain;

/**
 * Created by linpeng123l on 2016/10/17.
 */
public class KeyWordCountOld {

    private String content;
    private int count;
    private String key_words;
    private String category;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public String getKey_words() {
        return key_words;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setKey_words(String key_words) {
        this.key_words = key_words;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
