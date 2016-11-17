package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by apple on 16/8/2.
 */
public class PointRecordDetail implements Serializable {
    int id;
    int uid;
    int score;
    int eventId;
    String status;
    String operatorType;
    int operatorId;
    String detail;
    String fromType;
    long vaildTime;
    long ctime;
    int isVaild;
    int isApprove;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(String operatorType) {
        this.operatorType = operatorType;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public long getVaildTime() {
        return vaildTime;
    }

    public void setVaildTime(long vaildTime) {
        this.vaildTime = vaildTime;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public int getIsVaild() {
        return isVaild;
    }

    public void setIsVaild(int isVaild) {
        this.isVaild = isVaild;
    }

    public int getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(int isApprove) {
        this.isApprove = isApprove;
    }
}
