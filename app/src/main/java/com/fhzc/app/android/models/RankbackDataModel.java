package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 16-7-14.
 */
public class RankbackDataModel implements Serializable {
    String annualised;
    int plannerUid;
    String name;
    int sort;
    String avatar;

    public String getAnnualised() {
        return annualised;
    }

    public void setAnnualised(String annualised) {
        this.annualised = annualised;
    }

    public int getPlannerUid() {
        return plannerUid;
    }

    public void setPlannerUid(int plannerUid) {
        this.plannerUid = plannerUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
