package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/8/4.
 */
public class MyProductModel implements Serializable {
    long deadDate;
    int amount;
    int amountUsd;
    String period;
    int amountRMB;
    int productId;
    String annualYield;
    long buyTime;
    String dividendDay;
    String assetType;
    String productType;
    String proveUrl;
    String serial;
    long foundDay;
    String buyDay;
    String name;
    long valueDay;
    String redeemDay;
    long paymentDate;
    String notice;
    int status;
    String renewDeadline;
    private float expectedMax;
    private float expectedMin;
    long expireDay;
    long expiryDay;
    private String annualInterval;
    private long collectStart;
    private String annualOldYield;

    public String getAnnualOldYield() {
        return annualOldYield;
    }

    public void setAnnualOldYield(String annualOldYield) {
        this.annualOldYield = annualOldYield;
    }

    public long getCollectStart() {
        return collectStart;
    }

    public void setCollectStart(long collectStart) {
        this.collectStart = collectStart;
    }

    public long getCollectEnd() {
        return collectEnd;
    }

    public void setCollectEnd(long collectEnd) {
        this.collectEnd = collectEnd;
    }

    private long collectEnd;

    public String getAnnualInterval() {
        return annualInterval;
    }

    public void setAnnualInterval(String annualInterval) {
        this.annualInterval = annualInterval;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public long getExpireDay() {
        return expireDay;
    }

    public void setExpireDay(long expireDay) {
        this.expireDay = expireDay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRenewDeadline() {
        return renewDeadline;
    }

    public void setRenewDeadline(String renewDeadline) {
        this.renewDeadline = renewDeadline;
    }

    public float getExpectedMax() {
        return expectedMax;
    }

    public void setExpectedMax(float expectedMax) {
        this.expectedMax = expectedMax;
    }

    public float getExpectedMin() {
        return expectedMin;
    }

    public void setExpectedMin(float expectedMin) {
        this.expectedMin = expectedMin;
    }

    public long getExpiryDay() {
        return expiryDay;
    }

    public void setExpiryDay(long expiryDay) {
        this.expiryDay = expiryDay;
    }

    public long getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(long deadDate) {
        this.deadDate = deadDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountUsd() {
        return amountUsd;
    }

    public void setAmountUsd(int amountUsd) {
        this.amountUsd = amountUsd;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getAmountRMB() {
        return amountRMB;
    }

    public void setAmountRMB(int amountRMB) {
        this.amountRMB = amountRMB;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getAnnualYield() {
        return annualYield;
    }

    public void setAnnualYield(String annualYield) {
        this.annualYield = annualYield;
    }

    public long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(long buyTime) {
        this.buyTime = buyTime;
    }

    public String getDividendDay() {
        return dividendDay;
    }

    public void setDividendDay(String dividendDay) {
        this.dividendDay = dividendDay;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getProveUrl() {
        return proveUrl;
    }

    public void setProveUrl(String proveUrl) {
        this.proveUrl = proveUrl;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public long getFoundDay() {
        return foundDay;
    }

    public void setFoundDay(long foundDay) {
        this.foundDay = foundDay;
    }

    public String getBuyDay() {
        return buyDay;
    }

    public void setBuyDay(String buyDay) {
        this.buyDay = buyDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValueDay() {
        return valueDay;
    }

    public void setValueDay(long valueDay) {
        this.valueDay = valueDay;
    }

    public String getRedeemDay() {
        return redeemDay;
    }

    public void setRedeemDay(String redeemDay) {
        this.redeemDay = redeemDay;
    }

    public long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(long paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    @Override
    public String toString() {
        return "MyProductModel{" +
                "deadDate=" + deadDate +
                ", amount=" + amount +
                ", amountUsd=" + amountUsd +
                ", period='" + period + '\'' +
                ", amountRMB=" + amountRMB +
                ", productId=" + productId +
                ", annualYield='" + annualYield + '\'' +
                ", buyTime=" + buyTime +
                ", dividendDay='" + dividendDay + '\'' +
                ", assetType='" + assetType + '\'' +
                ", proveUrl='" + proveUrl + '\'' +
                ", serial='" + serial + '\'' +
                ", foundDay=" + foundDay +
                ", buyDay='" + buyDay + '\'' +
                ", name='" + name + '\'' +
                ", valueDay=" + valueDay +
                ", redeemDay='" + redeemDay + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }
}
