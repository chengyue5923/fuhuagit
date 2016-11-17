package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 2016/7/20.
 */
public class ProductModels implements Serializable {
    List<ProductModel> items;

    public List<ProductModel> getItems() {
        return items;
    }

    public void setItems(List<ProductModel> items) {
        this.items = items;
    }
}
