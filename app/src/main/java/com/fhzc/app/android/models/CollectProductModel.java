package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by User on 2016/7/28.
 */
public class CollectProductModel implements Serializable {
    String cover;
    float expected_min;
    float expected_max;
    String name;
    String renew_deadline;
    double invest_threshold;
    long collect_start;
    int id;
    long collect_end;
    int status;
    String incomeDistributionType;
    private String investTerm;
    private String annualYield;
    private String annualInterval;
    private int cid;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getAnnualInterval() {
        return annualInterval;
    }

    public void setAnnualInterval(String annualInterval) {
        this.annualInterval = annualInterval;
    }

    public String getInvestTerm() {
        return investTerm;
    }

    public void setInvestTerm(String investTerm) {
        this.investTerm = investTerm;
    }

    public static CollectProductModel getModel(ProductModel model) {
        CollectProductModel model1 = new CollectProductModel();
        model1.setCover(model.getCover());
        model1.setExpected_min(model.getExpectedMin());
        model1.setExpected_max(model.getExpectedMax());
        model1.setName(model.getName());
        model1.setRenew_deadline(model.getRenewDeadline());
        model1.setInvest_threshold(model.getInvestThreshold());
        model1.setId(model.getPid());
        model1.setStatus(model.getStatus());
        model1.setAnnualYield(model.getAnnualYield());
        model1.setInvestTerm(model.getInvestTerm());
        model1.setIncomeDistributionType(model.getIncomeDistributionType());
        return model1;
    }

    public String getAnnualYield() {
        return annualYield;
    }

    public void setAnnualYield(String annualYield) {
        this.annualYield = annualYield;
    }



    public String getIncomeDistributionType() {
        return incomeDistributionType;
    }

    public void setIncomeDistributionType(String incomeDistributionType) {
        this.incomeDistributionType = incomeDistributionType;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public float getExpected_min() {
        return expected_min;
    }

    public void setExpected_min(float expected_min) {
        this.expected_min = expected_min;
    }

    public float getExpected_max() {
        return expected_max;
    }

    public void setExpected_max(float expected_max) {
        this.expected_max = expected_max;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRenew_deadline() {
        return renew_deadline;
    }

    public void setRenew_deadline(String renew_deadline) {
        this.renew_deadline = renew_deadline;
    }

    public double getInvest_threshold() {
        return invest_threshold;
    }

    public void setInvest_threshold(double invest_threshold) {
        this.invest_threshold = invest_threshold;
    }

    public long getCollect_start() {
        return collect_start;
    }

    public void setCollect_start(long collect_start) {
        this.collect_start = collect_start;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCollect_end() {
        return collect_end;
    }

    public void setCollect_end(long collect_end) {
        this.collect_end = collect_end;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
