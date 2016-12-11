package com.dreamer.domain;

import java.util.List;

/**
 * Created by lp on 2016/11/5.
 */
public class CountYear {
    private int publish_year;
    private int count;

    public CountYear(int publish_year, int count) {
        this.publish_year = publish_year;
        this.count = count;
    }
    public CountYear(){

    }

    public int getPublish_year() {
        return publish_year;
    }

    public void setPublish_year(int publish_year) {
        this.publish_year = publish_year;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountYear{" +
                "publish_year=" + publish_year +
                ", count=" + count +
                '}';
    }
}
