package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by apple on 16/8/2.
 */
public class DividendRemendModel implements Serializable {
   String  distributeEarning;
    long paymentDate;
    String  payment;
    String earningRate;

    public String getDistributeEarning() {
        return distributeEarning;
    }

    public void setDistributeEarning(String distributeEarning) {
        this.distributeEarning = distributeEarning;
    }

    public String getEarningRate() {
        return earningRate;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setEarningRate(String earningRate) {
        this.earningRate = earningRate;
    }

    public long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(long paymentDate) {
        this.paymentDate = paymentDate;
    }
}
