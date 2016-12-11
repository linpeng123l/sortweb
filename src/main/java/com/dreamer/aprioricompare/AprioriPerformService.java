package com.dreamer.aprioricompare;


import com.alibaba.fastjson.JSON;
import com.dreamer.aprioricompare.doamin.BitTransction;
import com.dreamer.aprioricompare.doamin.Item;
import com.dreamer.aprioricompare.doamin.Transaction;
import com.dreamer.sort.PerformData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AprioriPerformService {

    private static final int MAX_ITEM = 8000;
    private static List<Transaction> transactions = new ArrayList<Transaction>();
    private static List<Item> itemList = new ArrayList<Item>();
//    private static float SDC = (float) 0.2;

    private static float start = 0.25f;
    private static float end = 0.9f;
    private static int count = 4;
    private static List<BitTransction> bitTransctions;

    public static PerformData getSortPerformData() {
        init();

        List<PerformData.AlgorithmPerform> algorithmPerforms = new ArrayList<>();
        algorithmPerforms.add(testApriori());
        algorithmPerforms.add(testBitApriori());

        PerformData performData = new PerformData();
        performData.setAlgorithmPerforms(algorithmPerforms);

        float[] axis = new float[count];
        for(int i = 0;i<count;i++){
            axis[i] = start + ((end-start)*i)/(count-1);
        }
        performData.setxAxis(axis);
        return performData;
    }

    private static void init() {
        transactions = DataUtil.getTransactions();
        createItem();
        bitTransctions = convertTransactions();

    }

    static PerformData.AlgorithmPerform testBitApriori() {
        int[] times = new int[count];
        for (int i = 0; i < count; i++) {
            long startTime = System.currentTimeMillis();
            new BitApriori(itemList, start + ((end-start)*i)/(count-1), transactions,bitTransctions);
            long endTime = System.currentTimeMillis();
            times[i] = (int) (endTime - startTime);
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("基于位运算的apriori算法");
        return algorithmPerform;
    }


    static PerformData.AlgorithmPerform testApriori() {
        int[] times = new int[count];
        for (int i = 0; i < count; i++) {
            long startTime = System.currentTimeMillis();
            new Apriori(itemList, start + ((end-start)*i)/(count-1), transactions);
            long endTime = System.currentTimeMillis();
            times[i] = (int) (endTime - startTime);
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("apriori算法");
        return algorithmPerform;
    }

    private static List<BitTransction> convertTransactions() {
        int TRAN_COUNT = transactions.size();
        for(int i = 0;i<((TRAN_COUNT-1) / 32 + 1)*32-TRAN_COUNT;i++){
            transactions.add(new Transaction(new ArrayList<>()));
        }

        List<BitTransction> bitTransctions = new ArrayList<>(TRAN_COUNT / 32 + 1);
        for (int i = 0; i < ((TRAN_COUNT-1) / 32 + 1); i++) {
            long[] nums = new long[MAX_ITEM];
            for (int j = 0; j < 32; j++) {
                for (int k = 1; k <= MAX_ITEM; k++) {
                    nums[k-1] = nums[k-1] + (long) (Math.pow(2, 32 - j - 1) * (transactions.get(i * 32 + j).containsItemSet("" + k) ? 1 : 0));
                }
            }
            List<Long> nums2 = new ArrayList<>(MAX_ITEM);
            for (int j = 1; j <= MAX_ITEM; j++) {
                nums2.add(nums[j-1]);
            }
            bitTransctions.add(new BitTransction(nums2));
        }
        return bitTransctions;
    }

    private static void createItem() {
        Map<String, Integer> map = new HashMap<>();
        for (Transaction transaction : transactions) {
            for (String itemName : transaction.getTrans()) {
                map.putIfAbsent(itemName, 0);
                map.put(itemName, map.get(itemName) + 1);
            }
        }
        List<String> usedNames = new ArrayList<>();
        for (String itemName : map.keySet()) {
            Item item = new Item(itemName, map.get(itemName));
            if (item.getCount() >= transactions.size() * start) {
                itemList.add(item);
                usedNames.add(itemName);
            }
        }
        for (int i = 0; i < transactions.size(); i++) {
            List<String> trans = transactions.get(i).getTrans();
            boolean isIn = false;
            for (String tran : trans) {
                if (usedNames.contains(tran)) {
                    isIn = true;
                }
            }
            if (!isIn) {
                transactions.remove(i);
                i--;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSON(getSortPerformData()).toString());
    }

}
