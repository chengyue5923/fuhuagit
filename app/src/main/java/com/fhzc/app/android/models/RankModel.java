package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/7/21.
 */
public class RankModel implements Serializable {
    int id;
    int plannerId;
    long yearMonth;
    int annualised;
    int departmentId;
    int rank;
    int departmentRank;
    String name;
    int year;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "RankModel{" +
                "id=" + id +
                ", plannerId=" + plannerId +
                ", yearMonth=" + yearMonth +
                ", annualised=" + annualised +
                ", departmentId=" + departmentId +
                ", rank=" + rank +
                ", departmentRank=" + departmentRank +
                ", name='" + name + '\'' +
                '}';
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

    public int getPlannerId() {
        return plannerId;
    }

    public void setPlannerId(int plannerId) {
        this.plannerId = plannerId;
    }

    public long getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(long yearMonth) {
        this.yearMonth = yearMonth;
    }

    public int getAnnualised() {
        return annualised;
    }

    public void setAnnualised(int annualised) {
        this.annualised = annualised;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getDepartmentRank() {
        return departmentRank;
    }

    public void setDepartmentRank(int departmentRank) {
        this.departmentRank = departmentRank;
    }
}
