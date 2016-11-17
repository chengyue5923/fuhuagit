package com.fhzc.app.android.configer.enums;

import com.fhzc.app.android.R;

/**
 * Created by yanbo on 2016/8/1.
 */
public enum CustomerType {
    Associatemember("准会员", R.drawable.personinfo_defaultava, R.drawable.personinfo__associate, R.drawable.rightandpoint_associate, R.drawable.message_associate, R.drawable.clientlist_associate, R.color.white),
    Silvermember("银卡", R.drawable.personinfo_defaultava, R.drawable.personinfo_silver, R.drawable.rightandpoint_silvervip, R.drawable.message_silver, R.drawable.clientlist_silvervip, R.color.white),
    Goldmember("金卡", R.drawable.personinfo_defaultava, R.drawable.personinfo_gold, R.drawable.rightandpoint_goldvip, R.drawable.message_goldvip, R.drawable.clientlist_goldvip, R.color.white),
    BlackGoldmember("铂金卡", R.drawable.personinfo_blackdefaultava, R.drawable.personinfo_blackglod, R.drawable.rightandpoint_blackvip, R.drawable.message_blackvip, R.drawable.clientlist_balckvip, R.color.backviptextColor),
    Investor("投资人", R.drawable.personinfo_defaultava, R.drawable.personinfo_investor, R.drawable.rightandpoint_investor, R.drawable.message_investor, R.drawable.clientlist_investor, R.color.selected_main_text),
    Planner("理财师", R.drawable.personinfo_defaultava, R.drawable.personinfo_plannerbg, R.drawable.rightandpoint_investor, R.drawable.message_investor, R.drawable.clientlist_investor, R.color.white);

    String type;
    int avatarBg;
    int personinfoBg;
    int pointBg;
    int chatBg;
    int clientListBg;
    int textColor;

    CustomerType(String type, int avatarBg, int personinfoBg, int pointBg, int chatBg, int clientListBg, int textColor) {
        this.avatarBg = avatarBg;
        this.textColor = textColor;
        this.type = type;
        this.personinfoBg = personinfoBg;
        this.pointBg = pointBg;
        this.chatBg = chatBg;
        this.clientListBg = clientListBg;
    }

    public static CustomerType getCustomerByType(String type) {
        if (type == null)
            return Investor;
        switch (type) {
            case "准会员":
                return Associatemember;

            case "银卡":
                return Silvermember;

            case "金卡":
                return Goldmember;

            case "铂金卡":
                return BlackGoldmember;

            case "投资人":
                return Investor;
            case "理财师":
                return Planner;

        }
        return Investor;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAvatarBg() {
        return avatarBg;
    }

    public void setAvatarBg(int avatarBg) {
        this.avatarBg = avatarBg;
    }

    public int getPersoninfoBg() {
        return personinfoBg;
    }

    public void setPersoninfoBg(int personinfoBg) {
        this.personinfoBg = personinfoBg;
    }

    public int getPointBg() {
        return pointBg;
    }

    public void setPointBg(int pointBg) {
        this.pointBg = pointBg;
    }

    public int getChatBg() {
        return chatBg;
    }

    public void setChatBg(int chatBg) {
        this.chatBg = chatBg;
    }

    public int getClientListBg() {
        return clientListBg;
    }

    public void setClientListBg(int clientListBg) {
        this.clientListBg = clientListBg;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }
}
