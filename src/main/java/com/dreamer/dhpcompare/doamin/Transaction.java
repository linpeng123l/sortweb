package com.dreamer.dhpcompare.doamin;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transaction {
    List<String> trans;

    public Transaction(List<String> trans) {
        this.trans = trans;
    }

    public void setTrans(List<String> trans) {
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

    public List<List<String>> queryK(int k) {
        List<List<String>> transList = new ArrayList<>();
        combine(transList, trans.size() - 1, k, new ArrayList<>());
        return transList;
    }

    public void combine(List<List<String>> transList, int end, int k, List<String> newtrans) {
        for (int i = end; i >= k - 1; i--) {
            List<String> copyNewTrans = new ArrayList<>(newtrans.size() + 1);
            copyNewTrans.add(trans.get(i));
            copyNewTrans.addAll(newtrans);
            if (i != 0 && k != 1) {
                combine(transList, i - 1, k - 1, copyNewTrans);
            } else {
                transList.add(copyNewTrans);
            }
        }
    }
}
