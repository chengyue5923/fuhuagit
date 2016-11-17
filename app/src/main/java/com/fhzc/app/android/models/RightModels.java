package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 2016/7/22.
 */
public class RightModels implements Serializable {
    List<RightModel> items;

    public List<RightModel> getItems() {
        return items;
    }

    public void setItems(List<RightModel> items) {
        this.items = items;
    }
}
