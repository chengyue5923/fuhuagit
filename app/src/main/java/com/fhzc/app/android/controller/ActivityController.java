package com.fhzc.app.android.controller;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.android.Logger;
import com.base.platform.utils.java.MapBuilder;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.ActivityModel;
import com.fhzc.app.android.models.ActivityModels;
import com.fhzc.app.android.models.OrderActivityModel;
import com.fhzc.app.android.utils.net.ConnectTool;

/**
 * Created by User on 2016/7/14.
 */
public class ActivityController {
    private static ActivityController instance;


    public static ActivityController getInstance() {
        if (instance == null)
            instance = new ActivityController();
        return instance;
    }

    public void activityList(ViewNetCallBack callBack,int page) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.activityList,
                    new MapBuilder<String, Object>()
                            .add("page",page)
                            .getMap(), callBack, ActivityModels.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void activityList(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.activityList,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, ActivityModels.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void activityTypeList(ViewNetCallBack callBack,int cid,int page) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.getActivityTypeList,
                    new MapBuilder<String, Object>().add("cid",cid).add("page",page)
                            .getMap(), callBack, ActivityModels.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 自己活动报名
     */
    public void joinActivity(ViewNetCallBack callBack, int activityId, int personNum) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.joinActivity,
                    new MapBuilder<String, Object>()
                            .add("activityId", activityId)
                            .add("customerId", UserPreference.getRoleId())
                            .add("plannerId", UserPreference.getPlannerId())
                            .add("personNum", personNum)
                            .add("phone", null)
                            .add("personName", null)
                            .add("isOther","0")
                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 为他人活动报名
     */
    public void joinActivityforOther(ViewNetCallBack callBack, int activityId, String name,String phone,String number,String code) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.joinActivity,
                    new MapBuilder<String, Object>()
                            .add("activityId", activityId)
                            .add("customerId", UserPreference.getRoleId())
                            .add("personNum", number)
                            .add("phone",phone)
                            .add("personName", name)
                            .add("plannerId",UserPreference.getPlannerId())
                            .add("verifyCode", code)
                            .add("isOther","1")
                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 取消活动报名
     */
    public void cancelJoinActivity(ViewNetCallBack callBack, String activityApplyId) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.cancelJoinActivity,
                    new MapBuilder<String, Object>()
                            .add("activityApplyId", activityApplyId)
                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 我预约的活动
     */
    public void myOrderActivity(ViewNetCallBack callBack,int uid) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.myOrderActivity,
                    new MapBuilder<String, Object>()
                            .add("customer_id", uid)
                            .getMap(), callBack, OrderActivityModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 活动详情
     */
    public void activityDetail(ViewNetCallBack callBack, int activityId) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.activityDetail,
                    new MapBuilder<String, Object>()
                            .add("activityId", activityId)
                            .getMap(), callBack, ActivityModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 关注活动
     */
    public void focusRight(ViewNetCallBack callBack, int activityid) {
        try {
            ConnectTool.httpRequest(HttpConfig.focus, new MapBuilder<String, Object>()
                    .add("uid", UserPreference.getUid())
                    .add("fid", activityid)
                    .add("ftype", "activity")
                    .add("status", 1)

                    .getMap(), callBack, String.class);
        } catch (Exception e) {
        }
    }

    /**
     * 取消关注活动
     */

    public void cancelFocusRight(ViewNetCallBack callBack, int activityid) {
        try {
            ConnectTool.httpRequest(HttpConfig.focus, new MapBuilder<String, Object>()
                    .add("uid", UserPreference.getUid())
                    .add("fid", activityid)
                    .add("ftype", "activity")
                    .add("status", 0)

                    .getMap(), callBack, String.class);
        } catch (Exception e) {
        }
    }

    /**
     * 查看关注状态
     */

    public void focusStatus(ViewNetCallBack callBack, int activityId) {
        try {
            ConnectTool.httpRequest(HttpConfig.focusStatus, new MapBuilder<String, Object>()
                    .add("uid", UserPreference.getUid())
                    .add("fid", activityId)
                    .add("ftype", "activity")
                    .getMap(), callBack, Integer.class);
        } catch (Exception e) {
        }
    }
}
