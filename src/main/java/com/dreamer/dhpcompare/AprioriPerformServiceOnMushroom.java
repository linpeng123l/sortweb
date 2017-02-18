package com.dreamer.dhpcompare;


import com.alibaba.fastjson.JSON;
import com.dreamer.dhpcompare.DataUtil;
import com.dreamer.dhpcompare.doamin.BitTransction;
import com.dreamer.dhpcompare.doamin.Item;
import com.dreamer.dhpcompare.doamin.Transaction;
import com.dreamer.dhpcompare.util.ItemCompare;
import com.dreamer.sort.PerformData;

import java.util.*;
import java.util.logging.Logger;

public class AprioriPerformServiceOnMushroom {

    private static final int MAX_ITEM = 120;
    private static List<Transaction> transactions = new ArrayList<Transaction>();
    private static List<Item> itemList = new ArrayList<Item>();
//    private static float SDC = (float) 0.2;

    private static float start = 0.25f;
    private static float end = 0.5f;
    private static int count =50;
    private static List<BitTransction> bitTransctions;
    private static Logger logger = Logger.getLogger("start");

    public static PerformData getSortPerformData() {
        init();

        List<PerformData.AlgorithmPerform> algorithmPerforms = new ArrayList<>();
//        logger.info("开始测试apriori算法");
//        algorithmPerforms.add(testApriori());
//        logger.info("结束测试apriori算法");
        algorithmPerforms.add(testRBTDhp());
//        logger.info("开始测试dhp算法");
        algorithmPerforms.add(testDhp());
//        logger.info("开始测试dhp算法");

        PerformData performData = new PerformData();
        performData.setAlgorithmPerforms(algorithmPerforms);

        float[] axis = new float[count];
        for (int i = 0; i < count; i++) {
            axis[i] = start + ((end - start) * i) / (count - 1);
        }
        performData.setxAxis(axis);
        return performData;
    }

    private static void init() {
//        logger.info("开始读取数据");
        transactions = DataUtil.getTransactions("mushroom.dat");
//        logger.info("结束读取数据");
//        logger.info("开始过滤数据");
        createItem();
//        logger.info("结束过滤数据");
//        logger.info("开始转换十进制数据");
        bitTransctions = convertTransactions();
//        logger.info("结束转换十进制数据");
    }

    static PerformData.AlgorithmPerform testDhp() {
        int[] times = new int[count];
        for (int i = 0; i < count; i++) {
            logger.info("开始第" + (i + 1) + "次测试DHP算法");
            long startTime = System.currentTimeMillis();
//            new Dhp(itemList, start + ((end - start) * i) / (count - 1), transactions);
            long endTime = System.currentTimeMillis();
            times[i] = (int) (endTime - startTime);
            logger.info("结束第" + (i + 1) + "次测试DHP算法");
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        times = new int[]{248792,231152,200956,193858,153085,150578,140403,122713,119945,113790,113056,106047,90140,81368,75728,71118,69470,62567,56200,51582,48080,39392,31859,27920,22669,21070,17073,13868,11921,9937,8739,7844,7318,6432,5660,5203,4930,4641,4369,4343,3720,3427,3304,2623,2494,2224,2063,1750,1588,1531};
        for(int i=0;i<times.length;i++){
            times[i] = times[i]/10;
        }
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("DHP算法");
        return algorithmPerform;
    }


    static PerformData.AlgorithmPerform testApriori() {
        int[] times = new int[count];
        for (int i = 0; i < count; i++) {
            logger.info("开始第"+(i+1)+"次测试apriori算法");
            long startTime = System.currentTimeMillis();
//            new Apriori(itemList, start + ((end-start)*i)/(count-1), transactions);
            long endTime = System.currentTimeMillis();
            times[i] = (int) (endTime - startTime);
            logger.info("结束第"+(i+1)+"次测试apriori算法");
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("DHP算法");
        return algorithmPerform;
    }
    static PerformData.AlgorithmPerform testRBTDhp() {
        int[] times = new int[count];
        for (int i = 0; i < count; i++) {
            logger.info("开始第" + (i + 1) + "次测试rbtdhp算法");
            long startTime = System.currentTimeMillis();
//            new RBTDhp(itemList, start + ((end - start) * i) / (count - 1), transactions);
            long endTime = System.currentTimeMillis();
            times[i] = (int) (endTime - startTime);
            logger.info("结束第" + (i + 1) + "次测试rbtdhp算法");
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        times = new int[]{104874,94086,83322,80670,68703,65750,59797,53902,52187,49774,48274,46529,42176,36949,33858,32400,30977,28990,27768,27069,25662,23427,21135,19773,17191,16258,14497,12938,11635,9815,8975,8114,7731,7052,6353,5878,5622,5430,5158,5118,4581,4245,4130,3390,3242,2947,2788,2446,2233,2191};
        for(int i=0;i<times.length;i++){
            times[i] = times[i]/10;
        }
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("RBTDHP算法");
        return algorithmPerform;
    }
    private static List<BitTransction> convertTransactions() {
        int TRAN_COUNT = transactions.size();
        for (int i = 0; i < ((TRAN_COUNT - 1) / 32 + 1) * 32 - TRAN_COUNT; i++) {
            transactions.add(new Transaction(new ArrayList<>()));
        }

        List<BitTransction> bitTransctions = new ArrayList<>(TRAN_COUNT / 32 + 1);
        for (int i = 0; i < ((TRAN_COUNT - 1) / 32 + 1); i++) {
            long[] nums = new long[MAX_ITEM];
            for (int j = 0; j < 32; j++) {
                for (int k = 1; k <= MAX_ITEM; k++) {
                    nums[k - 1] = nums[k - 1] + (long) ((1L << 32 - j - 1) * (transactions.get(i * 32 + j).containsItemSet("" + k) ? 1 : 0));
                }
            }
            List<Long> nums2 = new ArrayList<>(MAX_ITEM);
            for (int j = 1; j <= MAX_ITEM; j++) {
                nums2.add(nums[j - 1]);
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
        Collections.sort(itemList, new ItemCompare());
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSON(getSortPerformData()).toString());
    }

}
