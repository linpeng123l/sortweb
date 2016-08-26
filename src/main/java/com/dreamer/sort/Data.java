package com.dreamer.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by hws on 16/8/22.
 */
public class Data {


    public static int[] generateRandomData(int count, int maxNum) {
        int[] datas = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            datas[i] = random.nextInt(maxNum);
        }
//        PrintUtil.printArray("generate datas", datas);
        return datas;
    }

    public static List<int[]> copyArray(List<int[]> datas) {
        List<int[]> newDatas = new ArrayList<>(datas.size());
        for (int i = 0; i < datas.size(); i++) {
            newDatas.add(Arrays.copyOf(datas.get(i), datas.get(i).length));
        }
        return newDatas;
    }

}
