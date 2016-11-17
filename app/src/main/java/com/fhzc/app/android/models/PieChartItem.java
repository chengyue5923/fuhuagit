package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/7/25.
 */
public class PieChartItem implements Serializable {
    String name;
    String price;
    int color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
