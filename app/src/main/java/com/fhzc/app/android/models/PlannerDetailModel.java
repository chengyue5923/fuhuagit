package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/8/12.
 */

public class PlannerDetailModel implements Serializable {
    String workNum;
    long entryTime;
    String jobTitleCn;
    Integer roleId;
    String departmentId;
    String name;
    String mobile;
    String avatar;
    String position;
    String status;

    public String getWorkNum() {
        return workNum;
    }

    public void setWorkNum(String workNum) {
        this.workNum = workNum;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(long entryTime) {
        this.entryTime = entryTime;
    }

    public String getJobTitleCn() {
        return jobTitleCn;
    }

    public void setJobTitleCn(String jobTitleCn) {
        this.jobTitleCn = jobTitleCn;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
