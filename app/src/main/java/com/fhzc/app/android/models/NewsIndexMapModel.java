package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fw on 16/10/13.
 */

public class NewsIndexMapModel implements Serializable {
    private NewsItemModel news;
    private List<BannerModel> banner;

    public NewsItemModel getNews() {
        return news;
    }

    public void setNews(NewsItemModel news) {
        this.news = news;
    }

    public List<BannerModel> getBanner() {
        return banner;
    }

    public void setBanner(List<BannerModel> banner) {
        this.banner = banner;
    }
}
