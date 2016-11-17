package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 2016/7/22.
 */
public class IndexMapModel implements Serializable {
    List<ProductModel> product;
    List<ActivityModel> activity;
    List<ReportModel> report;
    List<RightModel> rights;
    List<BannerModel> banner_pic;
    List<BannerTextModel> banner_text;
    List<NewsModel> news;

    public List<NewsModel> getNews() {
        return news;
    }

    public void setNews(List<NewsModel> news) {
        this.news = news;
    }

    public List<BannerModel> getBanner_pic() {
        return banner_pic;
    }

    public void setBanner_pic(List<BannerModel> banner_pic) {
        this.banner_pic = banner_pic;
    }

    public List<BannerTextModel> getBanner_text() {
        return banner_text;
    }

    public void setBanner_text(List<BannerTextModel> banner_text) {
        this.banner_text = banner_text;
    }

    public List<ProductModel> getProduct() {
        return product;
    }

    public void setProduct(List<ProductModel> product) {
        this.product = product;
    }

    public List<ActivityModel> getActivity() {
        return activity;
    }

    public void setActivity(List<ActivityModel> activity) {
        this.activity = activity;
    }

    public List<ReportModel> getReport() {
        return report;
    }

    public void setReport(List<ReportModel> report) {
        this.report = report;
    }

    public List<RightModel> getRights() {
        return rights;
    }

    public void setRights(List<RightModel> rights) {
        this.rights = rights;
    }

    @Override
    public String toString() {
        return "IndexMapModel{" +
                "product=" + product +
                ", activity=" + activity +
                ", report=" + report +
                ", rights=" + rights +
                ", banner_pic=" + banner_pic +
                ", banner_text=" + banner_text +
                '}';
    }
}
