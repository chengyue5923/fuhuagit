package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by User on 2016/8/3.
 */
public class LoginExistModel implements Serializable {
    boolean isExist;

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }
}
