package com.dreamer.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hws on 16/8/22.
 */
public interface Sort {

    static int[] numSize = new int[]{10, 100, 1000, 5000, 10000, 20000,50000};
    //static int[] numSize = new int[]{10, 100, 1000, 5000, 10000, 20000,50000,100000};
    //    static int[] numSize = new int[]{10, 100, 1000, 100, 100, 10000};
    static int[] maxNum = new int[]{100, 1000, 1000, 10000, 10000, 10000,10000};
    //static int[] maxNum = new int[]{100, 1000, 1000, 10000, 10000, 10000,10000,10000};


    void sort(int[] datasList);

    static void main(String[] args) {
        List<int[]> datasList = new ArrayList<>();
        for (int i = 0; i < numSize.length; i++) {
            int[] datas = Data.generateRandomData(numSize[i], maxNum[i]);
            datasList.add(datas);
        }

        testSelectionSort(Data.copyArray(datasList));
        testInsertSort(Data.copyArray(datasList));
        testBubbleSort(Data.copyArray(datasList));
        testFastSort(Data.copyArray(datasList));
        testFastSort2(Data.copyArray(datasList));
        testMergeSort(Data.copyArray(datasList));
        testHeapSort(Data.copyArray(datasList));
        testShellSort(Data.copyArray(datasList));

    }

    static PerformData getSortPerformData() {
        List<int[]> datasList = new ArrayList<>();
        for (int i = 0; i < numSize.length; i++) {
            int[] datas = Data.generateRandomData(numSize[i], maxNum[i]);
            datasList.add(datas);
        }

        List<PerformData.AlgorithmPerform> algorithmPerforms = new ArrayList<>();
        algorithmPerforms.add(testSelectionSort(Data.copyArray(datasList)));
        algorithmPerforms.add(testInsertSort(Data.copyArray(datasList)));
        algorithmPerforms.add(testBubbleSort(Data.copyArray(datasList)));
//        algorithmPerforms.add(testFastSort(Data.copyArray(datasList)));
        algorithmPerforms.add(testFastSort2(Data.copyArray(datasList)));
        algorithmPerforms.add(testMergeSort(Data.copyArray(datasList)));
        algorithmPerforms.add(testHeapSort(Data.copyArray(datasList)));
        algorithmPerforms.add(testShellSort(Data.copyArray(datasList)));

        PerformData performData = new PerformData();
        performData.setAlgorithmPerforms(algorithmPerforms);
//        performData.setxAxis(numSize);
        return performData;
    }

    static PerformData.AlgorithmPerform testShellSort(List<int[]> datasList) {
        int[] times = new int[datasList.size()];
        for (int i = 0; i < datasList.size(); i++) {
            long start = System.currentTimeMillis();
            Sort sort = new ShellSort();
            sort.sort(datasList.get(i));
            long end = System.currentTimeMillis();
            times[i] = (int) (end - start);
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("希尔排序");
        return algorithmPerform;
    }

    static PerformData.AlgorithmPerform testHeapSort(List<int[]> datasList) {
        int[] times = new int[datasList.size()];
        for (int i = 0; i < datasList.size(); i++) {
            long start = System.currentTimeMillis();
            Sort sort = new HeapSort();
            sort.sort(datasList.get(i));
            long end = System.currentTimeMillis();
            times[i] = (int) (end - start);
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("堆排序");
        return algorithmPerform;
    }

    static PerformData.AlgorithmPerform testMergeSort(List<int[]> datasList) {
        int[] times = new int[datasList.size()];
        for (int i = 0; i < datasList.size(); i++) {
            long start = System.currentTimeMillis();
            Sort sort = new MergeSort();
            sort.sort(datasList.get(i));
            long end = System.currentTimeMillis();
            times[i] = (int) (end - start);
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("归并排序");
        return algorithmPerform;
    }

    static PerformData.AlgorithmPerform testFastSort(List<int[]> datasList) {
        int[] times = new int[datasList.size()];
        for (int i = 0; i < datasList.size(); i++) {
            long start = System.currentTimeMillis();
            Sort sort = new FastSort();
            sort.sort(datasList.get(i));
            long end = System.currentTimeMillis();
            times[i] = (int) (end - start);
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("快速排序");
        return algorithmPerform;
    }


    static PerformData.AlgorithmPerform testFastSort2(List<int[]> datasList) {
        int[] times = new int[datasList.size()];
        for (int i = 0; i < datasList.size(); i++) {
            long start = System.currentTimeMillis();

            Sort sort = new FastSort2();
            sort.sort(datasList.get(i));
            long end = System.currentTimeMillis();
            times[i] = (int) (end - start);
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("快速排序2");
        return algorithmPerform;
    }


    static PerformData.AlgorithmPerform testBubbleSort(List<int[]> datasList) {
        int[] times = new int[datasList.size()];
        for (int i = 0; i < datasList.size(); i++) {
            long start = System.currentTimeMillis();
            Sort sort = new BubbleSort();
            sort.sort(datasList.get(i));
            long end = System.currentTimeMillis();
            times[i] = (int) (end - start);
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("冒泡排序");
        return algorithmPerform;
    }

    static PerformData.AlgorithmPerform testSelectionSort(List<int[]> datasList) {
        int[] times = new int[datasList.size()];
        for (int i = 0; i < datasList.size(); i++) {
            long start = System.currentTimeMillis();
            Sort sort = new SelectionSort();
            sort.sort(datasList.get(i));
            long end = System.currentTimeMillis();
            times[i] = (int) (end - start);
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("选择排序");
        return algorithmPerform;
    }

    static PerformData.AlgorithmPerform testInsertSort(List<int[]> datasList) {
        int[] times = new int[datasList.size()];
        for (int i = 0; i < datasList.size(); i++) {
            long start = System.currentTimeMillis();

            Sort sort = new InsertionSort();
            sort.sort(datasList.get(i));
            long end = System.currentTimeMillis();
            times[i] = (int) (end - start);
        }
        PerformData.AlgorithmPerform algorithmPerform = new PerformData.AlgorithmPerform();
        algorithmPerform.setDatas(times);
        algorithmPerform.setName("插入排序");
        return algorithmPerform;
    }
}