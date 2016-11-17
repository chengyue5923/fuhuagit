package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/8/12.
 */

public class SuggestAssetsModel implements Serializable {
    String typeName;
    int proportion;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
    }
}
