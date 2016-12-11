package com.dreamer.sort;

/**
 * Created by hws on 16/8/26.
 */
public class ShellSort implements Sort {
    @Override
    public void sort(int[] datas) {
        sort(datas, datas.length / 2);
    }

    public void sort(int[] datas, int step) {
        if (step < 1) {
            return;
        }
        for (int i = step; i < datas.length; i++) {
            int index = i;
            int selNum = datas[i];
            while (index >= step && selNum < datas[index - step]) {
                datas[index] = datas[index - step];
                index = index - step;
            }
            datas[index] = selNum;
        }
        sort(datas, step / 2);
    }
}
