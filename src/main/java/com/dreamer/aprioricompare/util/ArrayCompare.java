package com.dreamer.aprioricompare.util;

import java.util.List;

/**
 * Created by linpeng123l on 2016/12/10.
 */
public class ArrayCompare {

    public static boolean compareStrArray(String[] array1, String[] array2) {
        if (array1.length != array2.length) {
            return false;
        }
        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].equals(array2[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean inStrArray(List<String> arrays, String[] subArrays) {
        if (subArrays.length > arrays.size()) {
            return false;
        }
        for (int i = 0; i < subArrays.length; i++) {
            boolean isIn = false;
            for(int j = 0;j<arrays.size();j++){
                if (subArrays[i].equals(arrays.get(j))) {
                    isIn = true;
                    break;
                }
            }
            if(!isIn){
                return false;
            }
        }
        return true;
    }

}
