package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by User on 2016/8/17.
 */
public class SystemNoticeModel implements Serializable {
    int id;
    int noticeId;
    int userId;
    int pushStatus;
    String content;
    String title;
    long publishTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(long publishTime) {
        this.publishTime = publishTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(int pushStatus) {
        this.pushStatus = pushStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
