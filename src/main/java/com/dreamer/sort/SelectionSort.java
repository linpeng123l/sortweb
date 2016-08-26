package com.dreamer.sort;

/**
 * Created by hws on 16/8/22.
 * 不稳定的算法,时间复杂度最好和最坏都是O(n^2)
 */
public class SelectionSort implements Sort {
    @Override
    public void sort(int[] datas) {
        for (int i = 0; i < datas.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < datas.length; j++) {
                if (datas[minIndex] > datas[j]) {
                    minIndex = j;
                }
            }
            int temp = datas[minIndex];
            datas[minIndex] = datas[i];
            datas[i] = temp;
        }
    }
}
