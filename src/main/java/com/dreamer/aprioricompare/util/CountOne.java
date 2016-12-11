package com.dreamer.aprioricompare.util;

public class CountOne {
    /**
     * @author
     * @param i 待测数字
     * @return 二进制表示中1的个数
     */
    public static int getCount(long i) {
        int n;
        for(n=0; i > 0; n++) {
            i &= (i - 1);
        }
        return n;
    }
}
