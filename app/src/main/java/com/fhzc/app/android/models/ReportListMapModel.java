package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fw on 16/10/13.
 */

public class ReportListMapModel implements Serializable {

    ReportItemModel report;
    List<ReportbannerModel> banner;

    public ReportItemModel getReport() {
        return report;
    }

    public void setReport(ReportItemModel report) {
        this.report = report;
    }

    public List<ReportbannerModel> getBanner() {
        return banner;
    }

    public void setBanner(List<ReportbannerModel> banner) {
        this.banner = banner;
    }
}
