package com.fhzc.app.android.event;

import java.io.Serializable;

/**
 * Created by User on 2016/7/31.
 */
public class CancelCollectEvent {
    public String type;
    public int position;
    public boolean isCollect;
    public Serializable serializable;
    public CancelCollectEvent(String type, int position, boolean isCollect,Serializable serializable) {
        this.type = type;
        this.serializable = serializable;
        this.position = position;
        this.isCollect = isCollect;
    }

}
