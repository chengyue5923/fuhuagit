package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by User on 2016/8/20.
 */
public class AchievementModel implements Serializable {
    int yearDeptMyRank;
    int monthSale;
    int yearSale;
    int monthDeptMyRank;
    int yearMyRank;
    int monthMyRank;

    public int getYearDeptMyRank() {
        return yearDeptMyRank;
    }

    public void setYearDeptMyRank(int yearDeptMyRank) {
        this.yearDeptMyRank = yearDeptMyRank;
    }

    public int getMonthSale() {
        return monthSale;
    }

    public void setMonthSale(int monthSale) {
        this.monthSale = monthSale;
    }

    public int getYearSale() {
        return yearSale;
    }

    public void setYearSale(int yearSale) {
        this.yearSale = yearSale;
    }

    public int getMonthDeptMyRank() {
        return monthDeptMyRank;
    }

    public void setMonthDeptMyRank(int monthDeptMyRank) {
        this.monthDeptMyRank = monthDeptMyRank;
    }

    public int getYearMyRank() {
        return yearMyRank;
    }

    public void setYearMyRank(int yearMyRank) {
        this.yearMyRank = yearMyRank;
    }

    public int getMonthMyRank() {
        return monthMyRank;
    }

    public void setMonthMyRank(int monthMyRank) {
        this.monthMyRank = monthMyRank;
    }
}
