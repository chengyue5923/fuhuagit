package com.fhzc.app.android.models;


import java.io.Serializable;

/**
 * 专属理财的model
 */
public class RightFinancialDetailModel implements Serializable {

    String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    String title;
    String insertNumber;
    String financialTime;
    String startPrice;
    String startNumber;
    String endTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInsertNumber() {
        return insertNumber;
    }

    public void setInsertNumber(String insertNumber) {
        this.insertNumber = insertNumber;
    }

    public String getFinancialTime() {
        return financialTime;
    }

    public void setFinancialTime(String financialTime) {
        this.financialTime = financialTime;
    }

    public String getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(String startPrice) {
        this.startPrice = startPrice;
    }

    public String getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(String startNumber) {
        this.startNumber = startNumber;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
