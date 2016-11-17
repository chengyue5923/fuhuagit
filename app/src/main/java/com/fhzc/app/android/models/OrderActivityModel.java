package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/7/29.
 */
public class OrderActivityModel implements Serializable {
    String cover;
    int activityId;
    long applyEndTime;
    int activityApplyId;
    String name;
    int status;
    String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public long getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(long applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public int getActivityApplyId() {
        return activityApplyId;
    }

    public void setActivityApplyId(int activityApplyId) {
        this.activityApplyId = activityApplyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
