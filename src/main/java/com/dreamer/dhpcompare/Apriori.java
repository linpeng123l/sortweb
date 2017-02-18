package com.dreamer.dhpcompare;


import com.dreamer.dhpcompare.doamin.Item;
import com.dreamer.dhpcompare.doamin.ItemSet;
import com.dreamer.dhpcompare.doamin.Transaction;
import com.dreamer.dhpcompare.util.ArrayCompare;
import com.dreamer.dhpcompare.util.GenerateCanditateItemSetUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class Apriori {

    private Logger logger = Logger.getLogger(String.valueOf(Apriori.class));

    List<Transaction> transactions;
    List<Item> itemList;
    int TRAN_COUNT;
    float SDC;
    List<List<ItemSet>> frequenrItemSets;

    public Apriori(List<Item> itemList, float sdc, List<Transaction> transaction) {
        this.SDC = sdc;
        this.itemList = itemList;
        this.transactions = transaction;
        TRAN_COUNT = transaction.size();
        frequenrItemSets = new ArrayList<>(TRAN_COUNT);

        generateF1(itemList);
        int count = 0;
        long start = System.currentTimeMillis();
        for (int i = 1; !frequenrItemSets.get(i - 1).isEmpty(); i++) {
//            logger.info("第" + (i + 1) + "次扫描");
//            logger.info("扫描候选集");
            start = System.currentTimeMillis();
            List<ItemSet> canditateItemSets = GenerateCanditateItemSetUtil.genCanditateItemSets(frequenrItemSets.get(i - 1));
            logger.info("第"+i+"次生成候选集，花费：" + (System.currentTimeMillis() - start));
//            logger.info("结束扫描候选集，花费：" + (System.currentTimeMillis() - start));
            start = System.currentTimeMillis();
//            logger.info("扫描项集");
            scanItemSetList(canditateItemSets);
            logger.info("第"+i+"次扫描项集，花费：" + (System.currentTimeMillis() - start));
            pruneItemSetList(canditateItemSets);
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

    private void scanItemSetList(List<ItemSet> canditateItemSets) {
        for (Transaction transaction : transactions) {
            List<String> teans = transaction.getTrans();
            for (ItemSet itemSet : canditateItemSets) {
                if (ArrayCompare.inStrArray(teans, itemSet.getItems())) {
                    itemSet.addCount();
                }
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
            if (temp.getCount() / TRAN_COUNT < SDC) {
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
            c1.add(itemSet);
        }
        pruneItemSetList(c1);
        frequenrItemSets.add(c1);
    }

}
