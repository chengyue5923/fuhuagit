package com.fhzc.app.android.models;


import java.io.Serializable;

/**
 * 用户详情
 */
public class MemberModel implements Serializable {
    int uid;
    String realname;
    String avatar;
    String money;
    String level;
    int score;
    int id;
    String address;
    int customerId;
    int main;
    String assetsSum;
    String phone;
    String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAssetsSum() {
        return assetsSum;
    }

    public void setAssetsSum(String assetsSum) {
        this.assetsSum = assetsSum;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "MemberModel{" +
                "uid=" + uid +
                ", realname='" + realname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", money='" + money + '\'' +
                ", level='" + level + '\'' +
                ", score=" + score +
                ", id=" + id +
                ", address='" + address + '\'' +
                '}';
    }
}
