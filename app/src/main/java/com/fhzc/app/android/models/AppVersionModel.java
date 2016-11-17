package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by User on 2016/8/18.
 */
public class AppVersionModel implements Serializable {
    int id;
    String version;
    int isUsing;
    String type;
    String androidUrl;
    String introduction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(int isUsing) {
        this.isUsing = isUsing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
