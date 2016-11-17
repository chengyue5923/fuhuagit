package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/7/22.
 */
public class RightModel implements Serializable{
    int cid;
    String cover;
    long ctime;
    int id;
    long markDate;
    String spendType;
    String summary;
    String supply;
    String url;
    String notice;
    String reservationStatus;
    String reservationId;
    int spendScore;
    int spend_score;
    int level;
    String name;
    String levelName;
    private String advanceDay;
    String levelNeed;
    int isReserved;
    private String type;
    private String rightsName;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRightsName() {
        return rightsName;
    }

    public void setRightsName(String rightsName) {
        this.rightsName = rightsName;
    }

    public int getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(int isReserved) {
        this.isReserved = isReserved;
    }

    public String getLevelNeed() {
        return levelNeed;
    }

    public void setLevelNeed(String levelNeed) {
        this.levelNeed = levelNeed;
    }

    public String getAdvanceDay() {
        return advanceDay;
    }

    public void setAdvanceDay(String advanceDay) {
        this.advanceDay = advanceDay;
    }

    public long getMarkDate() {
        return markDate;
    }

    public int getSpend_score() {
        return spend_score;
    }

    public void setSpend_score(int spend_score) {
        this.spend_score = spend_score;
    }

    public void setMarkDate(long markDate) {
        this.markDate = markDate;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
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

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSpendScore() {
        return spendScore;
    }

    public void setSpendScore(int spendScore) {
        this.spendScore = spendScore;
    }

    public String getSpendType() {
        return spendType;
    }

    public void setSpendType(String spendType) {
        this.spendType = spendType;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "RightModel{" +
                "cid=" + cid +
                ", cover='" + cover + '\'' +
                ", ctime=" + ctime +
                ", id=" + id +
                ", markDate=" + markDate +
                ", spendType='" + spendType + '\'' +
                ", summary='" + summary + '\'' +
                ", supply='" + supply + '\'' +
                ", url='" + url + '\'' +
                ", notice='" + notice + '\'' +
                ", reservationStatus='" + reservationStatus + '\'' +
                ", reservationId='" + reservationId + '\'' +
                ", spendScore=" + spendScore +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", levelName='" + levelName + '\'' +
                '}';
    }
}
