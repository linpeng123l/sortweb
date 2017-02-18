package com.dreamer.dhpcompare;


import com.dreamer.dhpcompare.doamin.Item;
import com.dreamer.dhpcompare.doamin.ItemSet;
import com.dreamer.dhpcompare.doamin.Transaction;
import com.dreamer.dhpcompare.util.ArrayCompare;
import com.dreamer.dhpcompare.util.GenerateCanditateItemSetUtil;

import java.util.*;
import java.util.logging.Logger;

public class RBTDhp {

    private Logger logger = Logger.getLogger(String.valueOf(RBTDhp.class));

    List<Transaction> transactions = new ArrayList<>();
    List<Item> itemList;
    int TRAN_COUNT;
    float SDC;
    List<List<ItemSet>> frequenrItemSets;
    Map<List<String>, Integer> hashmap = new HashMap<>();

    public RBTDhp(List<Item> itemList, float sdc, List<Transaction> transactions) {
        this.SDC = sdc;
        this.itemList = itemList;
        for(Transaction transaction1 : transactions){
            List<String> trans = new ArrayList<>();
            trans.addAll(transaction1.getTrans());
            this.transactions.add(new Transaction(trans));
        }
        TRAN_COUNT = transactions.size();
        frequenrItemSets = new ArrayList<>(TRAN_COUNT);

        generateF1(itemList);
        makeTwoHash();
        int count = 0;
        for (int i = 1; !frequenrItemSets.get(i - 1).isEmpty(); i++) {
            long start = System.currentTimeMillis();
            List<ItemSet> canditateItemSets = GenerateCanditateItemSetUtil.genCanditateItemSets(frequenrItemSets.get(i - 1));
//            logger.info("第" + i + "次生成候选集，花费：" + (System.currentTimeMillis() - start));
            start = System.currentTimeMillis();
            scanItemSetList(canditateItemSets, i);
            pruneItemSetList(canditateItemSets);
//            logger.info("第" + i + "次扫描项集，花费：" + (System.currentTimeMillis() - start));
            frequenrItemSets.add(canditateItemSets);
            count++;
        }
        for (int i = count; i >= count - 1; i--) {
            System.out.println("No. of length " + (i + 1) + " frequent itemsets: " + frequenrItemSets.get(i).size());
            for (int j = 0; j < frequenrItemSets.get(i).size(); j++) {
                System.out.println("{" + frequenrItemSets.get(i).get(j).getItemSet() + "}: support-count=" + frequenrItemSets.get(i).get(j).getCount());
            }
        }
    }

    private void scanItemSetList(List<ItemSet> canditateItemSets, int k) {
        for (int i = 0; i < transactions.size(); i++) {
            countSupport(transactions.get(i), canditateItemSets, k);
            if (transactions.get(i).getTrans().size() > k) {
                makeHash(transactions.get(i), k);
            }
        }
    }

    private void makeHash(Transaction transaction, int k) {
        Map<String, Integer> map = new HashMap<>();
        List<List<String>> transList = transaction.queryK(k + 2);
        for (List<String> trans : transList) {
            int count_f = 0;
            for (int i = 0; i < trans.size(); i++) {
                List<String> copy = new ArrayList<>();
                copy.addAll(trans);
                copy.remove(i);
                if (hashmap.get(copy)!=null&&(float)hashmap.get(copy) / TRAN_COUNT >= SDC) {
                    count_f++;
                } else {
                    break;
                }
            }
            if (count_f == trans.size()) {
                hashmap.putIfAbsent(trans, 0);
                hashmap.put(trans, hashmap.get(trans) + 1);
                for (int j = 0; j < trans.size(); j++) {
                    map.putIfAbsent(trans.get(j), 0);
                    map.put(trans.get(j), map.get(trans.get(j)) + 1);
                }
            }
        }
        List<String> trans = transaction.getTrans();
        Iterator<String> iterator = trans.iterator();
        while (iterator.hasNext()) {
            String tran = iterator.next();
            if (map.get(tran) == null || map.get(tran) < 1) {
                iterator.remove();
            }
        }
    }

    /**
     * 计算并裁剪
     *
     * @param transaction       事物
     * @param canditateItemSets 候选集
     * @param k                 次数
     */
    private void countSupport(Transaction transaction, List<ItemSet> canditateItemSets, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (ItemSet itemSet : canditateItemSets) {
            if (ArrayCompare.inStrArray(transaction.getTrans(), itemSet.getItems())) {
//                itemSet.addCount();
                for (String item : itemSet.getItems()) {
                    map.putIfAbsent(item, 0);
                    map.put(item, map.get(item) + 1);
                }
            }
        }
        List<String> trans = transaction.getTrans();
        Iterator<String> iterator = trans.iterator();
        while (iterator.hasNext()) {
            String tran = iterator.next();
            if (map.get(tran) == null || map.get(tran) < k) {
                iterator.remove();
            }
        }
    }

    /**
     * 过滤不满足的支持度
     */
    private void pruneItemSetList(List<ItemSet> itemSets) {
          Iterator<ItemSet> itemSetIterator = itemSets.iterator();
        while (itemSetIterator.hasNext()) {
            ItemSet temp = itemSetIterator.next();
            if (hashmap.get(temp.getItemsAsList()) == null || (float) hashmap.get(temp.getItemsAsList()) / TRAN_COUNT < SDC) {
                itemSetIterator.remove();
            }
        }
    }

    /**
     * 生成频繁1项集
     */
    private void generateF1(List<Item> L) {
        //得到候选1项集,所有
        List<ItemSet> c1 = new ArrayList<ItemSet>();
        for (Item item : L) {
            ItemSet itemSet = new ItemSet(item.getItemValue(), item.getCount());
            List<String> trans = new ArrayList<>();
            hashmap.putIfAbsent(itemSet.getItemsAsList(), (int) itemSet.getCount());
            c1.add(itemSet);
        }
        pruneItemSetList(c1);
        frequenrItemSets.add(c1);
    }

    /**
     * 第一次扫描，设置hash
     */
    private void makeTwoHash() {
        for (Transaction transaction : transactions) {
            List<List<String>> transList = transaction.queryK(2);
            for (List<String> trans : transList) {
                for (int i = 0; i < trans.size() - 1; i++) {
                    hashmap.putIfAbsent(trans, 0);
                    hashmap.put(trans, hashmap.get(trans) + 1);
                }
            }
        }
    }
}
