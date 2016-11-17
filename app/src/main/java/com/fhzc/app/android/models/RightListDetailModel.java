package com.fhzc.app.android.models;


import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * 权益详情中的选择供应商的model
 */
public class RightListDetailModel implements Serializable {

    String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    String image;
    String title;
    String content;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
