package com.dreamer.sort;

/**
 * Created by hws on 16/8/24.
 */
public class HeapSort implements Sort {
    @Override
    public void sort(int[] datas) {
        buildMaxHeap(datas);
        int len = datas.length;
        for (int i = len - 1; i > 0; i--) {
            swap(datas, 0, i);
            maxHeapify(datas, 0, i);
        }
    }

    private void buildMaxHeap(int[] datas) {
        int endIndex = parent(datas.length);
        for (int i = endIndex; i >= 0; i--) {
            maxHeapify(datas, i, datas.length);
        }
    }

    private void maxHeapify(int[] datas, int index, int heapSize) {
        int indexMax = index;
        int left = leftChild(index);
        if ((left + 1) <= heapSize && datas[indexMax] < datas[left]) {
            indexMax = left;
        }
        int right = rightChild(index);
        if ((right + 1) <= heapSize && datas[indexMax] < datas[right]) {
            indexMax = right;
        }
        if (index != indexMax) {
            swap(datas, index, indexMax);
            maxHeapify(datas, indexMax, heapSize);
        }
    }

    private void swap(int[] datas, int i, int j) {
        int temp = datas[i];
        datas[i] = datas[j];
        datas[j] = temp;
    }

    private int parent(int index) {
        return (index + 1) / 2 - 1;
    }

    private int leftChild(int index) {
        return (index + 1) * 2 - 1;
    }

    private int rightChild(int index) {
        return (index + 1) * 2;
    }

}
