package com.fhzc.app.android.configer.enums;

import com.fhzc.app.android.R;

/**
 * Created by yanbo on 2016/8/1.
 */
public enum CustomerTypeNew {
    Associatemember("准会员", R.drawable.customer_bg_view, R.drawable.financial_bg_view, R.drawable.rightandpoint_associate, R.drawable.fincnail_sign_view, R.drawable.fincnail_sign_view, R.color.white),
    Silvermember("银卡", R.drawable.customer_bg_view, R.drawable.silver_sign_view, R.drawable.rightandpoint_silvervip, R.drawable.silver_sign_view, R.drawable.silver_sign_view, R.color.white),
    Goldmember("金卡", R.drawable.customer_bg_view, R.drawable.gold_bg_view, R.drawable.rightandpoint_goldvip, R.drawable.gold_sign_bg_view, R.drawable.gold_sign_bg_view, R.color.white),
    BlackGoldmember("铂金卡", R.drawable.customer_bg_view, R.drawable.black_gold_view, R.drawable.gold_view_paingts, R.drawable.blgck_gold_sign_view, R.drawable.blgck_gold_sign_view, R.color.backviptextColor),
    Investor("投资人", R.drawable.customer_bg_view, R.drawable.vip_bg_view, R.drawable.silver_viewsss_point, R.drawable.finacnal_bg_dign_view, R.drawable.finacnal_bg_dign_view, R.color.selected_main_text),
    Planner("理财师", R.drawable.planner_bg_sign_view, R.drawable.vip_bg_view, R.drawable.rightandpoint_investor, R.drawable.message_investor, R.drawable.clientlist_investor, R.color.white);

    String type;
    int avatarBg;
    int personinfoBg;
    int pointBg;
    int chatBg;
    int clientListBg;
    int textColor;

    CustomerTypeNew(String type, int avatarBg, int personinfoBg, int pointBg, int chatBg, int clientListBg, int textColor) {
        this.avatarBg = avatarBg;
        this.textColor = textColor;
        this.type = type;
        this.personinfoBg = personinfoBg;
        this.pointBg = pointBg;
        this.chatBg = chatBg;
        this.clientListBg = clientListBg;
    }

    public static CustomerTypeNew getCustomerByType(String type) {
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
