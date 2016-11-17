package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 16-7-14.
 */
public class PullResultModel implements Serializable {
    int code;
    PullMessageMapModel map;



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PullMessageMapModel getMap() {
        return map;
    }

    public void setMap(PullMessageMapModel map) {
        this.map = map;
    }
}
