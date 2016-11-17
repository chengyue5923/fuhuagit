package com.fhzc.app.android.models;

import java.io.Serializable;

/**
 * Created by User on 2016/8/7.
 */
public class CustomerModel implements Serializable {
    long birthday;
    String passportCode;
    String address;
    String level;
    int passportTypeId;
    String name;
    String mobile;
    String risk;
    String passportType;
    String passportAddress;
    String email;
    String memo;
    String customerId;
    String main;
    String customer_level;
    String risk_level;
    String gender;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomer_level() {
        return customer_level;
    }

    public void setCustomer_level(String customer_level) {
        this.customer_level = customer_level;
    }

    public String getRisk_level() {
        return risk_level;
    }

    public void setRisk_level(String risk_level) {
        this.risk_level = risk_level;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getPassportCode() {
        return passportCode;
    }

    public void setPassportCode(String passportCode) {
        this.passportCode = passportCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getPassportTypeId() {
        return passportTypeId;
    }

    public void setPassportTypeId(int passportTypeId) {
        this.passportTypeId = passportTypeId;
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

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getPassportType() {
        return passportType;
    }

    public void setPassportType(String passportType) {
        this.passportType = passportType;
    }

    public String getPassportAddress() {
        return passportAddress;
    }

    public void setPassportAddress(String passportAddress) {
        this.passportAddress = passportAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
