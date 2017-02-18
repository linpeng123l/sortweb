package com.dreamer.dhpcompare;


import com.dreamer.dhpcompare.doamin.BitTransction;
import com.dreamer.dhpcompare.doamin.Item;
import com.dreamer.dhpcompare.doamin.ItemSet;
import com.dreamer.dhpcompare.doamin.Transaction;
import com.dreamer.dhpcompare.util.CountOne;
import com.dreamer.dhpcompare.util.GenerateCanditateItemSetUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class BitApriori {

    private Logger logger = Logger.getLogger(String.valueOf(BitApriori.class));

    List<Transaction> transactions;
    List<BitTransction> bitTransctions;
    List<Item> itemList;
    int TRAN_COUNT;
    float SDC;
    List<List<ItemSet>> frequenrItemSets;

    public BitApriori(List<Item> itemList, float sdc, List<Transaction> transaction, List<BitTransction> bitTransctions) {
        this.SDC = sdc;
        this.itemList = itemList;
        this.transactions = transaction;
        this.bitTransctions = bitTransctions;
        TRAN_COUNT = transaction.size();
        frequenrItemSets = new ArrayList<>(TRAN_COUNT);

        generateF1(itemList);
        int count = 0;
        for (int i = 1; !frequenrItemSets.get(i - 1).isEmpty(); i++) {
            List<ItemSet> canditateItemSets = GenerateCanditateItemSetUtil.genCanditateItemSets(frequenrItemSets.get(i - 1));
            scanItemSetList(canditateItemSets);
            pruneItemSetList(canditateItemSets);
            frequenrItemSets.add(canditateItemSets);
            count++;
        }

        // print the results


            for (int i = count; i >= count-1; i--) {
                System.out.println("No. of length " + (i + 1) + " frequent itemsets: " + frequenrItemSets.get(i).size());

                for (int j = 0; j < frequenrItemSets.get(i).size(); j++) {
                    System.out.println("{" + frequenrItemSets.get(i).get(j).getItemSet() + "}: support-count=" + frequenrItemSets.get(i).get(j).getCount());
                }
            }


    }

    private void scanItemSetList(List<ItemSet> canditateItemSets) {
        for (BitTransction bitTransction : bitTransctions) {
            List<Long> trans = bitTransction.getTrans();
            for (ItemSet itemSet : canditateItemSets) {
                String[] items = itemSet.getItems();
                long count = trans.get(Integer.parseInt(items[0]) - 1);
                for (int i = 1; i < items.length; i++) {
                    count = count & trans.get(Integer.parseInt(items[i]) - 1);
                }
                itemSet.setCount(itemSet.getCount()+ CountOne.getCount(count));
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
