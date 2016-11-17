package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/7/21.
 */
public class ContactUsModel implements Serializable {
    int id;
    String type;
    String introduction;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "ContactUsModel{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
