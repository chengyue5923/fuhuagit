package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 2016/8/1.
 */
public class OrderListRightModel implements Serializable {

    int  total;
    int page;
    int pageSize;
    List<OrderRightModel> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<OrderRightModel> getItems() {
        return items;
    }

    public void setItems(List<OrderRightModel> items) {
        this.items = items;
    }
}
