package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 16-7-14.
 */
public class MessageResultModel implements Serializable {
    MessageEntity data;
    int code;

    public MessageEntity getData() {
        return data;
    }

    public void setData(MessageEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
