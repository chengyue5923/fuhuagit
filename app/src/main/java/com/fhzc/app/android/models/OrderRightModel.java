package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/8/1.
 */
public class OrderRightModel implements Serializable {
    long markDate;
    int rightsId;
    int status;//0:


    String summary;
    String isRecommend;
    String spendScore;
    String level;
    String rightsNum;
    String levelName;
    String supplyPhone;
    String spendType;
    String supply;
    String cover;
    String name;
    String id;
    String cid;
    int isReserved;

    public int getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(int isReserved) {
        this.isReserved = isReserved;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    public void setSpendScore(String spendScore) {
        this.spendScore = spendScore;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getRightsNum() {
        return rightsNum;
    }

    public void setRightsNum(String rightsNum) {
        this.rightsNum = rightsNum;
    }

    public String getSupplyPhone() {
        return supplyPhone;
    }

    public void setSupplyPhone(String supplyPhone) {
        this.supplyPhone = supplyPhone;
    }

    public String getSpendType() {
        return spendType;
    }

    public void setSpendType(String spendType) {
        this.spendType = spendType;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpendScore() {
        return spendScore;
    }

    public String getLevel() {
        return level;
    }

    public String getId() {
        return id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public long getMarkDate() {
        return markDate;
    }

    public void setMarkDate(long markDate) {
        this.markDate = markDate;
    }

    public int getRightsId() {
        return rightsId;
    }

    public void setRightsId(int rightsId) {
        this.rightsId = rightsId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public long getMark_date() {
        return markDate;
    }

    public void setMark_date(long mark_date) {
        this.markDate = mark_date;
    }

    public int getRights_id() {
        return rightsId;
    }

    public void setRights_id(int rights_id) {
        this.rightsId = rights_id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OrderRightModel() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "OrderRightModel{" +
                "markDate=" + markDate +
                ", rightsId=" + rightsId +
                ", status=" + status +
                ", summary='" + summary + '\'' +
                ", isRecommend='" + isRecommend + '\'' +
                ", spendScore='" + spendScore + '\'' +
                ", level='" + level + '\'' +
                ", rightsNum='" + rightsNum + '\'' +
                ", levelName='" + levelName + '\'' +
                ", supplyPhone='" + supplyPhone + '\'' +
                ", spendType='" + spendType + '\'' +
                ", supply='" + supply + '\'' +
                ", cover='" + cover + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", cid='" + cid + '\'' +
                ", isReserved='" + isReserved + '\'' +
                '}';
    }
}
