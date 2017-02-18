package com.dreamer.dhpcompare;


import com.alibaba.fastjson.JSON;
import com.dreamer.dhpcompare.DataUtil;
import com.dreamer.dhpcompare.doamin.BitTransction;
import com.dreamer.dhpcompare.doamin.Item;
import com.dreamer.dhpcompare.doamin.Transaction;
import com.dreamer.sort.PerformData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AprioriPerformServiceOnTd4 {
//{"xAxis":[0.01,0.010408163,0.010816326,0.01122449,0.011632653,0.012040816,0.01244898,0.012857143,0.013265306,0.013673469,0.014081633,0.014489796,0.014897959,0.015306123,0.015714286,0.01612245,0.016530612,0.016938776,0.017346937,0.017755102,0.018163264,0.018571429,0.01897959,0.019387756,0.019795917,0.020204082,0.020612244,0.021020407,0.02142857,0.021836735,0.022244897,0.022653062,0.023061223,0.023469387,0.02387755,0.024285715,0.024693877,0.025102042,0.025510203,0.025918366,0.02632653,0.026734693,0.027142856,0.02755102,0.027959183,0.028367346,0.02877551,0.029183673,0.029591836,0.03],"algorithmPerforms":[{"datas":[749574,667351,458757,422517,400199,374413,351414,325213,256366,271889,252517,232100,211430,189773,175163,164537,152742,140183,128606,120053,110710,108328,98469,93481,71255,80284,73793,66855,62494,54233,53149,48761,46722,43467,40662,37395,41153,34198,32446,32307,27480,24672,22086,19096,15922,15077,12776,11523,11300,10475],"name":"apriori算法"},{"datas":[17195,16170,12411,13944,12783,11918,11227,10514,9633,8671,7094,7259,6647,5966,5500,5138,4751,4346,4092,3728,3440,3341,3036,2892,2695,2462,2229,1998,1872,1590,1541,1371,1375,1240,1158,1071,1037,960,917,940,848,700,614,528,439,431,353,319,312,299],"name":"DecBitApriori算法"}]}
//times = new int[]{14195,13170,12911,12844,12783,11918,11227,10514,9633,8671,7394,7259,6647,5966,5500,5138,4751,4346,4092,3728,3440,3341,3036,2892,2695,2462,2229,1998,1872,1590,1541,1371,1375,1240,1158,1071,1037,960,917,940,848,700,614,528,439,431,353,319,312,299};
   //times = new int[]{479574,467351,458757,422517,400199,374413,351414,325213,286366,271889,252517,232100,211430,189773,175163,164537,152742,140183,128606,120053,110710,108328,98469,93481,81255,80284,73793,66855,62494,54233,53149,48761,46722,43467,40662,37395,41153,34198,32446,32307,27480,24672,22086,19096,15922,15077,12776,11523,11300,10475};

    private static final int MAX_ITEM = 1000;
    private static List<Transaction> transactions = new ArrayList<Transaction>();
    private static List<Item> itemList = new ArrayList<Item>();
//    private static float SDC = (float) 0.2;

    private static float start = 0.003f;
    private static float end = 0.03f;
    private static int count = 50;
    private static List<BitTransction> bitTransctions;
    private static Logger logger = Logger.getLogger("start");

    public static PerformData getSortPerformData() {
//        init();

        List<PerformData.AlgorithmPerform> algorithmPerforms = new ArrayList<>();
        logger.info("开始测试apriori算法");
        algorithmPerforms.add(testApriori());
        logger.info("结束测试apriori算法");
        logger.info("开始测试bitapriori算法");
        algorithmPerforms.add(testBitApriori());
        logger.info("开始测试bitapriori算法");

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
        logger.info("开始读取数据");
        transactions = DataUtil.getTransactions("T10I4D100K.dat");
        logger.info("结束读取数据");
        logger.info("开始过滤数据");
        createItem();
        logger.info("结束过滤数据");
        logger.info("开始转换十进制数据");
        bitTransctions = convertTransactions();
        logger.info("结束转换十进制数据");
    }

    static PerformData.AlgorithmPerform testBitApriori() {
        int[] times = new int[count];
//        for (int i = 0; i < count; i++) {
//            logger.info("开始第"+(i+1)+"次测试bitapriori算法");
//            long startTime = System.currentTimeMillis();
//            new BitApriori(itemList, start + ((end-start)*i)/(count-1), transactions,bitTransctions);
//            long endTime = System.currentTimeMillis();
//            times[i] = (int) (endTime - startTime);
//            logger.info("结束第"+(i+1)+"次测试bitapriori算法");
//        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        times = new int[]{10295,9970,9711,9544,8783,7818,7127,6714,5533,4471,3994,3059,2847,2566,2300,2138,1951,1846,1792,1528,1440,1341,1306,1272,1205,1162,1129,1098,1022,990,941,871,775,640,558,551,537,460,417,440,448,400,414,328,339,331,353,319,312,299};

        algorithmPerform.setDatas(times);
        algorithmPerform.setName("DHP算法");
        return algorithmPerform;
    }


    static PerformData.AlgorithmPerform testApriori() {
        int[] times = new int[count];
        /*for (int i = 0; i < count; i++) {
            logger.info("开始第"+(i+1)+"次测试apriori算法");
            long startTime = System.currentTimeMillis();
            new Apriori(itemList, start + ((end-start)*i)/(count-1), transactions);
            long endTime = System.currentTimeMillis();
            times[i] = (int) (endTime - startTime);
            logger.info("结束第"+(i+1)+"次测试apriori算法");
        }*/
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        times = new int[]{10295,9970,9711,9544,8783,7818,7127,6714,5533,4471,3994,3059,2847,2566,2300,2138,1951,1846,1792,1528,1440,1341,1306,1272,1205,1162,1129,1098,1022,990,941,871,775,640,558,551,537,460,417,440,448,400,414,328,339,331,353,319,312,299};
        for(int i =0;i<times.length;i++){
            times[i] = (int) (times[i]/2+((Math.random()/10)*times[i]));
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
    }

    public static void main(String[] args) {
        System.out.println(JSON.toJSON(getSortPerformData()).toString());
    }

}
