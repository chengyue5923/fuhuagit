package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lenovo on 2016/8/12.
 */
public class MemberModels implements Serializable {
    List<MemberModel> list;

    public List<MemberModel> getList() {
        return list;
    }

    public void setList(List<MemberModel> list) {
        this.list = list;
    }
}
