package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/7/21.
 */
public class ActivityModel implements Serializable {
    int id;
    String name;
    String address;
    long applyBeginTime;
    long applyEndTime;
    long beginTime;
    long endTime;
    String memo;
    String sponsor;
    String areaId;
    long ctime;
    String url;
    int status;
    int isDisplay;
    String summary;
    int cid;
    String cover;
    String departmentId;
    String content;
    String userReq;
    String applyId;
    String activityResult;
    Object focusStatus;
    private int isApplyed;
    private boolean issign=true;

    public boolean issign() {
        return issign;
    }

    public void setIssign(boolean issign) {
        this.issign = issign;
    }

    public int getIsApplyed() {
        return isApplyed;
    }

    public void setIsApplyed(int isApplyed) {
        this.isApplyed = isApplyed;
    }

    public Object getFocusStatus() {
        return focusStatus;
    }

    public void setFocusStatus(Object focusStatus) {
        this.focusStatus = focusStatus;
    }

    public String getActivityResult() {
        return activityResult;
    }

    public void setActivityResult(String activityResult) {
        this.activityResult = activityResult;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getUserReq() {
        return userReq;
    }

    public void setUserReq(String userReq) {
        this.userReq = userReq;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getApplyBeginTime() {
        return applyBeginTime;
    }

    public void setApplyBeginTime(long applyBeginTime) {
        this.applyBeginTime = applyBeginTime;
    }

    public long getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(long applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ActivityModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", applyBeginTime=" + applyBeginTime +
                ", applyEndTime=" + applyEndTime +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", memo='" + memo + '\'' +
                ", sponsor='" + sponsor + '\'' +
                ", areaId='" + areaId + '\'' +
                ", ctime=" + ctime +
                ", url='" + url + '\'' +
                ", status=" + status +
                ", isDisplay=" + isDisplay +
                ", summary='" + summary + '\'' +
                ", cid=" + cid +
                ", cover='" + cover + '\'' +
                ", departmentId='" + departmentId + '\'' +
                ", content='" + content + '\'' +
                ", userReq='" + userReq + '\'' +
                ", applyId='" + applyId + '\'' +
                '}';
    }
}
