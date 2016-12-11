package com.dreamer.sort;

/**
 * Created by hws on 16/8/23.
 */
public class FastSort2 implements Sort {

    @Override
    public void sort(int[] datas) {
        sort(datas, 0, datas.length - 1);
    }

    private void sort(int[] datas, int start, int end) {
        if(start >= end){
            return;
        }
        int index = start;
        int selNum = datas[end];
        int temp;
        for (int i = start; i < end ; i++) {
            if (selNum > datas[i]) {
                temp = datas[index];
                datas[index] = datas[i];
                datas[i] = temp;
                index++;
            }
        }
        temp = datas[index];
        datas[index] = datas[end];
        datas[end] = temp;
        sort(datas,start,index-1);
        sort(datas,index+1,end);
    }

}
