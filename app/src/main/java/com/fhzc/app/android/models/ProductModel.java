package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by yanbo on 2016/7/20.
 */
public class ProductModel implements Serializable {
    String annualYield;
    String buyDay;
    String code;
    String cover;
    String credit;
    long ctime;
    String custodian;
    String desc;
    String detailContent;
    String detailUrl;
    String displayOrder;
    String dividendDay;
    private float expectedMax;
    private float expectedMin;
    long expiryDay;
    long foundDay;
    String reservationResult = "";
    String fundManagementFee;
    String fundManager;
    String fundSubscriptionFee;
    String highlights;
    String incomeDistributionType;
    String increaseTrust;
    String investmentOrientation;
    String investmentType;
    int investTermMax;
    int investAmount;
    int investTermMin;
    double investThreshold;
    int isDisplay;
    int isRecommend;
    int isRecord;
    int isRenew;
    int issuer;
    /**
     * 发行模式
     */
    String issueType;
    int level;
    String name;
    String notice;
    int pid;

    Integer productType;
    String projectSource;
    String proveUrl;
    String redeemDay;
    String renewDeadline;
    int risk;
    double scoreFactor;
    int status;
    String valueDay;
    int focusStatus;
    Integer reservationId;
    private String investTerm;
    private String productTypeName;
    private long collectStart;
    private long collectEnd;
    private String annualInterval;
    private String abbreviation;
    private String backCover;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBackCover() {
        return backCover;
    }

    public void setBackCover(String backCover) {
        this.backCover = backCover;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAnnualInterval() {
        return annualInterval;
    }

    public void setAnnualInterval(String annualInterval) {
        this.annualInterval = annualInterval;
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

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getInvestTerm() {
        return investTerm;
    }

    public void setInvestTerm(String investTerm) {
        this.investTerm = investTerm;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public int getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(int investAmount) {
        this.investAmount = investAmount;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public int getFocusStatus() {
        return focusStatus;
    }

    public void setFocusStatus(int focusStatus) {
        this.focusStatus = focusStatus;
    }

    public String getReservationResult() {
        return reservationResult;
    }

    public void setReservationResult(String reservationResult) {
        this.reservationResult = reservationResult;
    }

    public String getAnnualYield() {
        return annualYield;
    }

    public void setAnnualYield(String annualYield) {
        this.annualYield = annualYield;
    }

    public String getBuyDay() {
        return buyDay;
    }

    public void setBuyDay(String buyDay) {
        this.buyDay = buyDay;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public long getCtime() {
        return ctime;
    }

    public void setCtime(long ctime) {
        this.ctime = ctime;
    }

    public String getCustodian() {
        return custodian;
    }

    public void setCustodian(String custodian) {
        this.custodian = custodian;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(String displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getDividendDay() {
        return dividendDay;
    }

    public void setDividendDay(String dividendDay) {
        this.dividendDay = dividendDay;
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

    public long getFoundDay() {
        return foundDay;
    }

    public void setFoundDay(long foundDay) {
        this.foundDay = foundDay;
    }

    public String getFundManagementFee() {
        return fundManagementFee;
    }

    public void setFundManagementFee(String fundManagementFee) {
        this.fundManagementFee = fundManagementFee;
    }

    public String getFundManager() {
        return fundManager;
    }

    public void setFundManager(String fundManager) {
        this.fundManager = fundManager;
    }

    public String getFundSubscriptionFee() {
        return fundSubscriptionFee;
    }

    public void setFundSubscriptionFee(String fundSubscriptionFee) {
        this.fundSubscriptionFee = fundSubscriptionFee;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getIncomeDistributionType() {
        return incomeDistributionType;
    }

    public void setIncomeDistributionType(String incomeDistributionType) {
        this.incomeDistributionType = incomeDistributionType;
    }

    public String getIncreaseTrust() {
        return increaseTrust;
    }

    public void setIncreaseTrust(String increaseTrust) {
        this.increaseTrust = increaseTrust;
    }

    public String getInvestmentOrientation() {
        return investmentOrientation;
    }

    public void setInvestmentOrientation(String investmentOrientation) {
        this.investmentOrientation = investmentOrientation;
    }

    public String getInvestmentType() {
        return investmentType;
    }

    public void setInvestmentType(String investmentType) {
        this.investmentType = investmentType;
    }

    public int getInvestTermMax() {
        return investTermMax;
    }

    public void setInvestTermMax(int investTermMax) {
        this.investTermMax = investTermMax;
    }

    public int getInvestTermMin() {
        return investTermMin;
    }

    public void setInvestTermMin(int investTermMin) {
        this.investTermMin = investTermMin;
    }

    public double getInvestThreshold() {
        return investThreshold;
    }

    public void setInvestThreshold(double investThreshold) {
        this.investThreshold = investThreshold;
    }

    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getIsRecord() {
        return isRecord;
    }

    public void setIsRecord(int isRecord) {
        this.isRecord = isRecord;
    }

    public int getIsRenew() {
        return isRenew;
    }

    public void setIsRenew(int isRenew) {
        this.isRenew = isRenew;
    }

    public int getIssuer() {
        return issuer;
    }

    public void setIssuer(int issuer) {
        this.issuer = issuer;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getProjectSource() {
        return projectSource;
    }

    public void setProjectSource(String projectSource) {
        this.projectSource = projectSource;
    }

    public String getProveUrl() {
        return proveUrl;
    }

    public void setProveUrl(String proveUrl) {
        this.proveUrl = proveUrl;
    }

    public String getRedeemDay() {
        return redeemDay;
    }

    public void setRedeemDay(String redeemDay) {
        this.redeemDay = redeemDay;
    }

    public String getRenewDeadline() {
        return renewDeadline;
    }

    public void setRenewDeadline(String renewDeadline) {
        this.renewDeadline = renewDeadline;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public double getScoreFactor() {
        return scoreFactor;
    }

    public void setScoreFactor(double scoreFactor) {
        this.scoreFactor = scoreFactor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getValueDay() {
        return valueDay;
    }

    public void setValueDay(String valueDay) {
        this.valueDay = valueDay;
    }

    @Override
    public String toString() {
        return "ProductModel{" +
                "annualYield='" + annualYield + '\'' +
                ", buyDay='" + buyDay + '\'' +
                ", code='" + code + '\'' +
                ", cover='" + cover + '\'' +
                ", credit='" + credit + '\'' +
                ", ctime=" + ctime +
                ", custodian='" + custodian + '\'' +
                ", desc='" + desc + '\'' +
                ", detailContent='" + detailContent + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                ", displayOrder='" + displayOrder + '\'' +
                ", dividendDay='" + dividendDay + '\'' +
                ", expectedMax='" + expectedMax + '\'' +
                ", expectedMin='" + expectedMin + '\'' +
                ", expiryDay=" + expiryDay +
                ", foundDay=" + foundDay +
                ", reservationResult='" + reservationResult + '\'' +
                ", fundManagementFee='" + fundManagementFee + '\'' +
                ", fundManager='" + fundManager + '\'' +
                ", fundSubscriptionFee='" + fundSubscriptionFee + '\'' +
                ", highlights='" + highlights + '\'' +
                ", incomeDistributionType='" + incomeDistributionType + '\'' +
                ", increaseTrust='" + increaseTrust + '\'' +
                ", investmentOrientation='" + investmentOrientation + '\'' +
                ", investmentType='" + investmentType + '\'' +
                ", investTermMax=" + investTermMax +
                ", investTermMin=" + investTermMin +
                ", investThreshold=" + investThreshold +
                ", isDisplay=" + isDisplay +
                ", isRecommend=" + isRecommend +
                ", isRecord=" + isRecord +
                ", isRenew=" + isRenew +
                ", issuer=" + issuer +
                ", issueType=" + issueType +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", notice='" + notice + '\'' +
                ", pid=" + pid +
                ", productType='" + productType + '\'' +
                ", projectSource='" + projectSource + '\'' +
                ", proveUrl='" + proveUrl + '\'' +
                ", redeemDay='" + redeemDay + '\'' +
                ", renewDeadline='" + renewDeadline + '\'' +
                ", risk=" + risk +
                ", scoreFactor=" + scoreFactor +
                ", status=" + status +
                ", valueDay='" + valueDay + '\'' +
                ", focusStatus=" + focusStatus +
                ", reservationId=" + reservationId +
                '}';
    }
}
