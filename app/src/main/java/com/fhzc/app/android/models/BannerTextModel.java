package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/7/22.
 */
public class BannerTextModel implements Serializable{

    int  id;
    String type;
    String text;
    String cover;
    String fromId;
    String fromType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    @Override
    public String toString() {
        return "BannerTextModel{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", cover='" + cover + '\'' +
                ", fromId='" + fromId + '\'' +
                ", fromType='" + fromType + '\'' +
                '}';
    }
}
