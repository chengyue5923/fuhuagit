package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fw on 16/10/13.
 */

public class NewsItemModel implements Serializable {
    private int total;
    private int page;
    private int pageSize;
    private List<NewsModel> items;
    private int totalPage;

    public int getTotalPage() {
        return totalPage;
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

    public List<NewsModel> getItems() {
        return items;
    }

    public void setItems(List<NewsModel> items) {
        this.items = items;
    }
}
