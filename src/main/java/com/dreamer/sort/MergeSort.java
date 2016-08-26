package com.dreamer.sort;

/**
 * Created by hws on 16/8/24.
 */
public class MergeSort implements Sort {
    @Override
    public void sort(int[] datas) {
        sort(datas, 0, datas.length - 1);
    }

    private void sort(int[] datas, int start, int end) {
        if (end == start) {
            return;
        }
        int splitLen = start + (end - start) / 2;
        sort(datas, start, splitLen);
        sort(datas, splitLen + 1, end);
        merge(datas, start, splitLen, end);
    }

    private void merge(int[] datas, int start, int splitLen, int end) {
        int i = 0, j = 0;
        int[] temp = new int[end - start + 1];
        while (i <= splitLen - start && j <= end - splitLen - 1) {
            if (datas[start + i] <= datas[splitLen + 1 + j]) {
                temp[i + j] = datas[start + i];
                i++;
            } else {
                temp[i + j] = datas[splitLen + 1 + j];
                j++;
            }
        }
        for (int k = i; k <= splitLen - start; k++) {
            temp[k + j] = datas[start + k];
        }
        for (int k = j; k <= end - splitLen - 1; k++) {
            temp[k + i] = datas[splitLen + 1 + k];
        }
        for (int k = start; k <= end; k++) {
            datas[k] = temp[k-start];
        }
    }
}
