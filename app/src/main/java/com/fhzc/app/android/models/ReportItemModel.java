package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fw on 16/10/13.
 */

public class ReportItemModel implements Serializable {
    private int total;
    private int page;
    private int pageSize;
    private List<ReportModel> items;
    private int totalPage;

    public int getTotalPage() {
        return totalPage;
    }

    public List<ReportModel> getItems() {
        return items;
    }

    public void setItems(List<ReportModel> items) {
        this.items = items;
    }

    public ReportItemModel(int total, int page, int pageSize, List<ReportModel> items, int totalPage) {
        this.total = total;
        this.page = page;
        this.pageSize = pageSize;
        this.items = items;
        this.totalPage = totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

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

}
