package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by User on 2016/7/24.
 */
public class ActivityStatusModel implements Serializable {
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
