//package com.dreamer.aprioricompare;
//
//
//import com.dreamer.aprioricompare.doamin.BitTransction;
//import com.dreamer.aprioricompare.doamin.Item;
//import com.dreamer.aprioricompare.doamin.ItemSet;
//import com.dreamer.aprioricompare.doamin.Transaction;
//import com.dreamer.aprioricompare.util.CountOne;
//import com.dreamer.aprioricompare.util.GenerateCanditateItemSetUtil;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.logging.Logger;
//
//public class EclatApriori {
//
//    private Logger logger = Logger.getLogger(String.valueOf(EclatApriori.class));
//
//    List<Transaction> transactions;
//    List<BitTransction> bitTransctions;
//    List<Item> itemList;
//    int TRAN_COUNT;
//    float SDC;
//    List<List<ItemSet>> frequenrItemSets;
//    private List<List<Integer>> eclats;
//
//    public EclatApriori(List<Item> itemList, float sdc, List<Transaction> transaction, List<BitTransction> bitTransctions) {
//        this.SDC = sdc;
//        this.itemList = itemList;
//        this.transactions = transaction;
//        this.bitTransctions = bitTransctions;
//        TRAN_COUNT = transaction.size();
//        frequenrItemSets = new ArrayList<>(TRAN_COUNT);
//
//
//        init();
//        generateF1(itemList);
//        int count = 0;
//        for (int i = 1; !frequenrItemSets.get(i - 1).isEmpty(); i++) {
//            List<ItemSet> canditateItemSets = GenerateCanditateItemSetUtil.genCanditateItemSets(frequenrItemSets.get(i - 1));
//            scanItemSetList(canditateItemSets);
//            pruneItemSetList(canditateItemSets);
//            frequenrItemSets.add(canditateItemSets);
//            count++;
//        }
//
//        // print the results
//       /* try {
//            File file = new File("result1.txt");
//            if (file.exists()) {
//                file.delete();
//            }
//
//            file.createNewFile();
//
//            FileWriter fw = new FileWriter(file);
//
//
//            for (int i = count; i >= 0; i--) {
//                fw.write("No. of length " + (i + 1) + " frequent itemsets: " + frequenrItemSets.get(i).size());
//                fw.write("\n");
//                System.out.println("No. of length " + (i + 1) + " frequent itemsets: " + frequenrItemSets.get(i).size());
//
//                for (int j = 0; j < frequenrItemSets.get(i).size(); j++) {
//                    fw.write("{" + frequenrItemSets.get(i).get(j).getItemSet() + "}: support-count=" + frequenrItemSets.get(i).get(j).getCount());
//                    fw.write("\n");
//                    System.out.println("{" + frequenrItemSets.get(i).get(j).getItemSet() + "}: support-count=" + frequenrItemSets.get(i).get(j).getCount());
//                }
//                fw.write("\n");
//            }
//
//            fw.close();
//
//        } catch (Exception e) {
//
//        }*/
//    }
//
//    private void init() {
//        eclats = new ArrayList<>();
//        for(int i =1;i<199;i++){
//            eclats.add(new ArrayList<>());
//        }
//        int TRAN_COUNT = transactions.size();
//        for (int i = 0; i < TRAN_COUNT; i++) {
//            Transaction transaction = transactions.get(i);
//            List<String> trans = transaction.getTrans();
//            for(String tran : trans){
//                eclats.get(Integer.parseInt(tran)).add(i);
//            }
//        }
//    }
//
//    private void scanItemSetList(List<ItemSet> canditateItemSets) {
//        for (ItemSet itemSet : canditateItemSets) {
//            String[] items = itemSet.getItems();
//
//
//            long count = trans.get(Integer.parseInt(items[0]) - 1);
//            for (int i = 1; i < items.length; i++) {
//                count = count & trans.get(Integer.parseInt(items[i]) - 1);
//            }
//            itemSet.setCount(itemSet.getCount()+ CountOne.getCount(count));
//        }
//
//        for (BitTransction bitTransction : bitTransctions) {
//            List<Long> trans = bitTransction.getTrans();
//            for (ItemSet itemSet : canditateItemSets) {
//                String[] items = itemSet.getItems();
//                long count = trans.get(Integer.parseInt(items[0]) - 1);
//                for (int i = 1; i < items.length; i++) {
//                    count = count & trans.get(Integer.parseInt(items[i]) - 1);
//                }
//                itemSet.setCount(itemSet.getCount()+ CountOne.getCount(count));
//            }
//        }
//    }
//
//    /**
//     * 过滤不满足的支持度
//     */
//    private void pruneItemSetList(List<ItemSet> itemSets) {
//        Iterator<ItemSet> itemSetIterator = itemSets.iterator();
//        while (itemSetIterator.hasNext()) {
//            ItemSet temp = itemSetIterator.next();
//            if (temp.getCount() / TRAN_COUNT < SDC) {
//                itemSetIterator.remove();
//            }
//        }
//    }
//
//    /**
//     * 生成频繁1项集
//     */
//    private void generateF1(List<Item> L) {
//        //得到候选1项集,所有
//        List<ItemSet> c1 = new ArrayList<ItemSet>();
//        for (Item item : L) {
//            ItemSet itemSet = new ItemSet(item.getItemValue(), item.getCount());
//            c1.add(itemSet);
//        }
//        pruneItemSetList(c1);
//        frequenrItemSets.add(c1);
//    }
//
//}
