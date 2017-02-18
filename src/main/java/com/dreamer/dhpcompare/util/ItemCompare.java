package com.dreamer.dhpcompare.util;

import com.dreamer.dhpcompare.doamin.Item;

import java.util.Comparator;

/**
 * Created by linpeng123l on 2017/2/17.
 */
public class ItemCompare implements Comparator<Item>{
        @Override
        public int compare(Item o1, Item o2) {
//            if(o1.getItemValue().length()==o2.getItemValue().length()){
                return Integer.parseInt(o1.getItemValue()) - Integer.parseInt(o2.getItemValue());
//            }else {
//                return o1.getItemValue().length()-o2.getItemValue().length();
//            }
        }
}
