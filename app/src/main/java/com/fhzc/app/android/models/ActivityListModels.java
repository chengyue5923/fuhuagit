package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 2016/7/21.
 */
public class ActivityListModels implements Serializable {
    List<OrderActivityModel> items;

    public List<OrderActivityModel> getItems() {
        return items;
    }

    public void setItems(List<OrderActivityModel> items) {
        this.items = items;
    }
}
