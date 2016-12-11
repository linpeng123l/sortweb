package com.dreamer.aprioricompare.doamin;

import java.util.List;

/**
 * Created by linpeng123l on 2016/12/11.
 */
public class BitTransction {

    private List<Long> trans;

    public BitTransction(List<Long> trans) {
        this.trans = trans;
    }

    public void setTrans(List<Long> trans) {
        this.trans = trans;
    }

    public List<Long> getTrans() {
        return trans;
    }
}
