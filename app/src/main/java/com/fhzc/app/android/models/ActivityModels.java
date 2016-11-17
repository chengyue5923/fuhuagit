package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 2016/7/21.
 */
public class ActivityModels implements Serializable {
    List<ActivityModel> items;

    public List<ActivityModel> getItems() {
        return items;
    }

    public void setItems(List<ActivityModel> items) {
        this.items = items;
    }
}
