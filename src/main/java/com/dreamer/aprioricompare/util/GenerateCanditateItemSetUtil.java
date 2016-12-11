package com.dreamer.aprioricompare.util;


import com.dreamer.aprioricompare.doamin.ItemSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by linpeng123l on 2016/12/10.
 */
public class GenerateCanditateItemSetUtil {

    public static List<ItemSet> genCanditateItemSets(List<ItemSet> preFrequentItemSets) {
        int level = preFrequentItemSets.get(0).getItemSet().split(",").length;
        List<ItemSet> canditateItemSets = new ArrayList<>();
        for (int i = 0; i < preFrequentItemSets.size(); i++) {
            ItemSet one = preFrequentItemSets.get(i);
            for (int j = i + 1; j < preFrequentItemSets.size(); j++) {
                ItemSet two = preFrequentItemSets.get(j);


                String[] firstBreaks = one.getItemSet().split(",");
                String[] secondBreaks = two.getItemSet().split(",");
                int index = 0;
                boolean match = true;
                while (index < level - 1) {
                    if (!firstBreaks[index].equals(secondBreaks[index])) {
                        match = false;
                        break;
                    }
                    index++;
                }
                if (match && !firstBreaks[index].equals(secondBreaks[index])) {
                    String[] items = new String[level + 1];
                    for (int k = 0; k < level; k++) {
                        items[k] = firstBreaks[k];
                    }
                    items[level] = secondBreaks[level - 1];
                    if (checkNewItemSetValid(preFrequentItemSets, items)) {
                        canditateItemSets.add(new ItemSet(items));
                    }
                }

            }
        }
        return canditateItemSets;
    }

    private static boolean checkNewItemSetValid(List<ItemSet> preFrequentItemSets, String[] items) {
        boolean status = true;
        for (int i = 0; i < items.length; i++) {
            boolean isIn = false;
            for (ItemSet itemSet : preFrequentItemSets) {
                String[] comItems = itemSet.getItems();
                int add = 0;
                boolean isMatch = true;
                if (comItems.length >= items.length - 1) {
                    for (int j = 0; j < items.length; j++) {
                        if (j == i) {
                            add = 1;
                        } else if (!items[j].equals(comItems[j - add])) {
                            isMatch = false;
                            break;
                        }
                    }
                } else {
                    isMatch = false;
                }
                if (isMatch == true) {
                    isIn = true;
                    break;
                }
            }
            if (!isIn) {
                status = false;
                break;
            }
        }
        return status;
    }

}
