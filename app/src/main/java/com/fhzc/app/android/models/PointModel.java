package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/8/3.
 */
public class PointModel implements Serializable {
    int expired;
    int available;
    int frozen;
    int yours;
    int willExpired;

    public int getWillExpired() {
        return willExpired;
    }

    public void setWillExpired(int willExpired) {
        this.willExpired = willExpired;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getFrozen() {
        return frozen;
    }

    public void setFrozen(int frozen) {
        this.frozen = frozen;
    }

    public int getYours() {
        return yours;
    }

    public void setYours(int yours) {
        this.yours = yours;
    }
}
