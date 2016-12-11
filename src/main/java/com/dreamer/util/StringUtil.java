package com.dreamer.util;

/**
 * Created by lp on 2016/11/5.
 */
public class StringUtil {
    public static String inString(String[] strs) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            stringBuilder.append("'" + strs[i] + "'");
            if (i != strs.length - 1) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
