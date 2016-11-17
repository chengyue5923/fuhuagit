package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 16-7-15.
 */
public class SessionModel implements Serializable {
    String sessionId;
    int unReadCount;
    int fromId;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }
}
