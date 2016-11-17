package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by User on 2016/7/28.
 */
public class CollectActivityModel implements Serializable {
    String cover;
    long apply_end_time;
    String name;
    int id;
    Integer status;
    String address;
    long applyEndTime;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(long applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public static CollectActivityModel getModel(ActivityModel model){
       CollectActivityModel model1 = new CollectActivityModel();
       model1.setCover(model.getCover());
       model1.setApply_end_time(model.getApplyEndTime());
       model1.setName(model.getName());
       model1.setId(model.getId());
       model1.setStatus(model.getStatus());
       return model1;
   }
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public long getApply_end_time() {
        return apply_end_time;
    }

    public void setApply_end_time(long apply_end_time) {
        this.apply_end_time = apply_end_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
