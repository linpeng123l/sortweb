package com.dreamer.sort;

/**
 * Created by hws on 16/8/23.
 */
public class FastSort implements Sort {

    @Override
    public void sort(int[] datas) {
        sort(datas, 0, datas.length - 1);
    }

    private void sort(int[] datas, int start, int end) {
        if (start < end) {
            int selNum = datas[start];
            int left = start;
            int right = end;
            while (right != left) {
                while (left < right && selNum < datas[right]) {
                    right--;
                }
                while (left < right && selNum >= datas[left]) {
                    left++;
                }
                int temp = datas[left];
                datas[left] = datas[right];
                datas[right] = temp;
            }
            datas[left] = selNum;
            sort(datas, start, left - 1);
            sort(datas, left + 1, end);
        }
    }

}


