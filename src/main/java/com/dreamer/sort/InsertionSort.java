package com.dreamer.sort;

/**
 * Created by hws on 16/8/22.
 * 稳定的算法,时间复杂度最好是O(n),最坏是O(n^2)
 */
public class InsertionSort implements Sort {

    /**
     * @param datas 待排序序列
     */
    @Override
    public void sort(int[] datas) {
        for (int i = 1; i < datas.length; i++) {
            int j = i - 1;
            int sel_num = datas[i];
            while (j >= 0 && sel_num < datas[j]) {
                datas[j + 1] = datas[j--];
            }
            datas[j + 1] = sel_num;
        }
    }
}
