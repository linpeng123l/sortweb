package com;

/**
 * Created by linpeng123l on 2016/10/19.
 */
public class Test {

    public static void main(String[] args) {
        for (int j = 0; j < 32; j++) {
            boolean s  = (long) (Math.pow(2, 32 - j - 1))==(1L << 32 - j - 1);
            System.out.println(s);
        }
    }

}
