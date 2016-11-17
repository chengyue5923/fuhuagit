package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 2016/7/20.
 */
public class ReportModels implements Serializable {
    List<ReportModel> items;

    public List<ReportModel> getItems() {
        return items;
    }

    public void setItems(List<ReportModel> items) {
        this.items = items;
    }
}
