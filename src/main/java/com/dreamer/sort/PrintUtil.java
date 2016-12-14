package com.dreamer.sort;

/**
 * Created by hws on 16/8/22.
 */
public class PrintUtil {

    public static void printArray(String head,int[] nums){
        System.out.print(head);
        for (int i = 0;i<nums.length-1;i++){
            System.out.print(nums[i]+" , ");
        }
        System.out.println(nums[nums.length-1]);
    }

}
