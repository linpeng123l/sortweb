package com.dreamer.sort;

/**
 * Created by hws on 16/8/22.
 */
public class BubbleSort implements Sort {
    @Override
    public void sort(int[] datas) {
        for (int i = 0; i < datas.length-1; i++) {
            for (int j = datas.length - 1; j > i; j--) {
                if(datas[j]<datas[j-1]){
                    int temp = datas[j];
                    datas[j] = datas[j-1];
                    datas[j-1] = temp;
                }
            }
        }
    }
}
