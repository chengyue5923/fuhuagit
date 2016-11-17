package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 2016/7/22.
 */
public class CollectMapModel implements Serializable {
    List<CollectProductModel> product;
    List<CollectActivityModel> activity;
    List<ReportModel> report;
    List<RightModel> rights;

    public List<CollectProductModel> getProduct() {
        return product;
    }

    public void setProduct(List<CollectProductModel> product) {
        this.product = product;
    }

    public List<CollectActivityModel> getActivity() {
        return activity;
    }

    public void setActivity(List<CollectActivityModel> activity) {
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
}
