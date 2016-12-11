package com.dreamer.aprioricompare.doamin;

public class ItemSet {
    String itemSet; // comma separated values
    String[] items;
    float count;

    public ItemSet(String itemSet, float count) {
        this.itemSet = itemSet;
        this.count = count;
    }

    public ItemSet(String[] items) {
        this.items = items;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < items.length - 1; i++) {
            stringBuilder.append(items[i] + ",");
        }
        stringBuilder.append(items[items.length - 1]);
        itemSet = stringBuilder.toString();
    }

    public String getItemSet() {
        return itemSet;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }
    public void addCount(){
        this.count++;
    }

    public String[] getItems() {
        if (items == null) {
            items = itemSet.split(",");
        }
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ItemSet [itemSet=" + itemSet + ", count=" + count + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemSet == null) ? 0 : itemSet.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemSet other = (ItemSet) obj;

        if (itemSet == null) {
            if (other.itemSet != null)
                return false;
        } else if (!itemSet.equals(other.itemSet))
            return false;
        return true;
    }


}
