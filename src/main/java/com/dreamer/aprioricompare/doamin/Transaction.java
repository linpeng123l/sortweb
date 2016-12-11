package com.dreamer.aprioricompare.doamin;


import java.util.List;

public class Transaction {
    List<String> trans;

    public Transaction(List<String> trans) {
        this.trans = trans;
    }

    public List<String> getTrans() {
        return trans;
    }

    public boolean containsItemSet(String itemSet) {
        boolean status = true;
        String items[] = itemSet.split(",");
        for (int i = 0; i < items.length; i++) {
            if (!trans.contains(items[i])) {
                status = false;
                break;
            }
        }
        return status;
    }

}
