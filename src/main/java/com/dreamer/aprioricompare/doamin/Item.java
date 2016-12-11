package com.dreamer.aprioricompare.doamin;

public class Item {
    String item;
    float count;
    int order;

    public Item(String item, float count) {
        this.item = item;
        this.count = count;
    }

    public String getItemValue() {
        return item;
    }

    public float getCount() {
        return count;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


}
