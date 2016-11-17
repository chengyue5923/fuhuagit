package com.fhzc.app.android.controller;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.MapBuilder;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.OrderListRightModel;
import com.fhzc.app.android.models.OrderRightModel;
import com.fhzc.app.android.models.RightModel;
import com.fhzc.app.android.models.RightModels;
import com.fhzc.app.android.utils.net.ConnectTool;

/**
 * Created by yanbo on 2016/7/22.
 */
public class RightController {
    private static RightController instance;


    public static RightController getInstance() {
        if (instance == null)
            instance = new RightController();
        return instance;
    }

    public void rightList(ViewNetCallBack callBack,int cid) {

        try {
            ConnectTool.httpRequest(HttpConfig.rightList, new MapBuilder<String, Object>()
                    .add("cid",cid)
                    .getMap(), callBack, OrderRightModel.class);
        } catch (Exception e) {
        }
    }

    /**
     * 权益兑换
     */

    public void exchangeRight(ViewNetCallBack callBack, int customerId, int rightsId,
                              String markDate, String detail,
                              String plannerId, String memo) {
        try {
            ConnectTool.httpRequest(HttpConfig.exchangeRight, new MapBuilder<String, Object>()

                    .add("rightsId", rightsId)
                    .add("customerId", customerId)
                    .add("markDate", markDate)
                    .add("detail", detail)
                    .add("memo", memo)
                    .getMap(), callBack, String.class);
        } catch (Exception e) {
        }
    }

    /**
     * 取消权益兑换
     */
    public void cancelExchangeRight(ViewNetCallBack callBack, int id) {
        try {
            ConnectTool.httpRequest(HttpConfig.cancelExchangeRight, new MapBuilder<String, Object>()

                    .add("id", id)

                    .getMap(), callBack, String.class);
        } catch (Exception e) {
        }
    }

    /**
     * 我预约的权益
     */
    public void myOrderRight(ViewNetCallBack callBack,int uid) {
        try {
            ConnectTool.httpRequest(HttpConfig.myOrderRight, new MapBuilder<String, Object>()
                      .add("customer_id", uid)
                    .getMap(), callBack, OrderRightModel.class);
        } catch (Exception e) {
        }
    }

    /**
     * 关注权益
     */
    public void focusRight(ViewNetCallBack callBack, int rightId) {
        try {
            ConnectTool.httpRequest(HttpConfig.focus, new MapBuilder<String, Object>()
                    .add("uid", UserPreference.getUid())
                    .add("fid", rightId)
                    .add("ftype", "rights")
                    .add("status", 1)

                    .getMap(), callBack, RightModels.class);
        } catch (Exception e) {
        }
    }

    /**
     * 取消关注权益
     */

    public void cancelFocusRight(ViewNetCallBack callBack, int rightId) {
        try {
            ConnectTool.httpRequest(HttpConfig.focus, new MapBuilder<String, Object>()
                    .add("uid", UserPreference.getUid())
                    .add("fid", rightId)
                    .add("ftype", "rights")
                    .add("status", 0)

                    .getMap(), callBack, RightModels.class);
        } catch (Exception e) {
        }
    }

    /**
     * 查看关注状态
     */

    public void focusStatus(ViewNetCallBack callBack, int rightId) {
        try {
            ConnectTool.httpRequest(HttpConfig.focusStatus, new MapBuilder<String, Object>()
                    .add("uid", UserPreference.getUid())
                    .add("fid", rightId)
                    .add("ftype", "rights")
                    .getMap(), callBack, Integer.class);
        } catch (Exception e) {
        }
    }
    /**
     * 精选权益
     */
    public void selectRight(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(HttpConfig.selectRight, new MapBuilder<String, Object>()
                    .getMap(), callBack, RightModels.class);
        } catch (Exception e) {
        }
    }


    /**
     * 权益详情
     */
    public void rightDetail(ViewNetCallBack callBack,int rightsId) {
        try {
            ConnectTool.httpRequest(HttpConfig.rightDetail, new MapBuilder<String, Object>()
                    .add("rightsId", rightsId)
                    .getMap(), callBack, RightModel.class);
        } catch (Exception e) {
        }
    }
}
