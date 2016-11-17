package com.fhzc.app.android.controller;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.android.Logger;
import com.base.platform.utils.java.MapBuilder;
import com.fhzc.app.android.BuildConfig;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.AccountInformationModel;
import com.fhzc.app.android.models.AchievementModel;
import com.fhzc.app.android.models.CollectMapModel;
import com.fhzc.app.android.models.ContactUsModel;
import com.fhzc.app.android.models.CustomerModel;
import com.fhzc.app.android.models.MemberModel;
import com.fhzc.app.android.models.PlannerDetailModel;
import com.fhzc.app.android.models.PointModel;
import com.fhzc.app.android.models.RankModel;
import com.fhzc.app.android.models.RankbackDataModel;
import com.fhzc.app.android.models.UserModel;
import com.fhzc.app.android.utils.net.ConnectTool;

import java.io.File;
import java.util.HashMap;

/**
 * Created by yanbo on 2016/7/28.
 */
public class UserController {
    private static UserController instance;


    public static UserController getInstance() {
        if (instance == null)
            instance = new UserController();
        return instance;
    }

    public void userInfo(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.userInfo,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, UserModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 我的关注
     *
     * @param callBack
     */
    public void myCollection(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.myCollection,
                    new MapBuilder<String, Object>()
                            .add("uid", UserPreference.getUid())
                            .getMap(), callBack, CollectMapModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 我的
     *
     * @param callBack
     */
    public void mine(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.mine,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, CollectMapModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 我的
     *
     * @param callBack
     */
    public void customerList(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.customerList,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, MemberModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 我的
     *
     * @param callBack
     */
    public void aboutUs(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.aboutUS,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, ContactUsModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 我的
     *
     * @param callBack
     */
    public void aboutApp(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.aboutAPP,
                    new MapBuilder<String, Object>()
//                            .add("ve 不能rsion", BuildConfig.VERSION_NAME)
                            .add("version", BuildConfig.VERSION_CODE)
//                            .add("version", 146)
                            .getMap(), callBack, ContactUsModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 用户积分
     *
     * @param callBack
     */
    public void personalScore(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.pointRecord,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, PointModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 用户积分
     *
     * @param callBack
     */
    public void shareForCustomer(ViewNetCallBack callBack, String customerId, String type, int typeId) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.shareForCostomer,
                    new MapBuilder<String, Object>()
                            .add("toUserId[]", customerId)
                            .add("type", type)
                            .add("id", typeId)
                            .getMap(), callBack, PointModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 客户信息详情
     *
     * @param callBack
     */
    public void customerInfoDetail(ViewNetCallBack callBack, int customerId) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.customerDetail,
                    new MapBuilder<String, Object>()
                            .add("customer_id", customerId)
                            .getMap(), callBack, CustomerModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 更改客户备注
     *
     * @param callBack
     */
    public void changeCustomerRemark(ViewNetCallBack callBack, int customerId, String remark) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.changeCustomserRemark,
                    new MapBuilder<String, Object>()
                            .add("id", customerId)
                            .add("memo", remark)
                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 理财师详情
     *
     * @param callBack
     */
    public void plannerDetail(ViewNetCallBack callBack, int plannerId) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.plannerDetail,
                    new MapBuilder<String, Object>()
                            .add("planner_id", plannerId)
                            .getMap(), callBack, PlannerDetailModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 取消关注
     *
     * @param callBack
     */
    public void cancelFocusAttention(ViewNetCallBack callBack, int type, String typeid) {
        try {
            String producttype = "productIds[]";
            String reporttype = "reportIds[]";
            String activitytype = "activityIds[]";
            String rightstype = "rightsIds[]";
            String producttypeid = "";
            String reporttypeid = "";
            String activitytypeid = "";
            String rightstypeid = "";

            if (type == 0) {
                producttypeid = typeid;
            } else if (type == 1) {
                reporttypeid = typeid;
            } else if (type == 2) {
                activitytypeid = typeid;
            } else if (type == 3) {
                rightstypeid = typeid;
            }
            ConnectTool.httpRequest(
                    HttpConfig.noFocusOther,
                    new MapBuilder<String, Object>()
                            .add(producttype, producttypeid)
                            .add(reporttype, reporttypeid)
                            .add(activitytype, activitytypeid)
                            .add(rightstype, rightstypeid)
                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 理财师业绩
     *
     * @param callBack
     */
    public void achievement(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.achievement,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, AchievementModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 历月排名
     *
     * @param callBack
     */
    public void mouthRank(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.mouthRank,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, RankModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 历月排名
     *
     * @param callBack
     */
    public void rankTen(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.rankTen,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, RankModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 历年排名
     *
     * @param callBack
     */
    public void rankYear(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.yearRank,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, RankModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 搜索
     *
     * @param callBack
     */
    public void rankSearch(ViewNetCallBack callBack, String year) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.RankByYear,
                    new MapBuilder<String, Object>()
                            .add("year", year)
                            .getMap(), callBack, RankModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 意见反馈
     *
     * @param callBack
     */
    public void suggestAdd(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.suggestAdd,
                    new MapBuilder<String, Object>()

                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }
    /**
     * 更换头像
     *
     * @param
     */

    public void upLoadFile(final ViewNetCallBack viewNetCallBack, File file, HttpConfig httpconfig) {
        try {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("file", file);
            hashMap.put("enctype",  "multipart/form-data");
            hashMap.put("userId", UserPreference.getUid());

            ConnectTool.httpRequest(
                    httpconfig,
                    hashMap,
                    viewNetCallBack,
                    String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }
    public void changeAvarter(ViewNetCallBack callBack,String url) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.changeAvatar,
                    new MapBuilder<String, Object>()
                             .add("image",url)
                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }

    /**
     * 风险测评
     *
     * @param callBack
     */
    public void riskTest(ViewNetCallBack callBack,int cusid,String risk) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.RiskTest,
                    new MapBuilder<String, Object>()
                            .add("customer_id",cusid)
                            .add("risk", risk)
                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }
    /**
     * 理财师业绩查询
     *
     * @param callBack
     */
    public void RankSearch(ViewNetCallBack callBack,String start,String end) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.rankBetween,
                    new MapBuilder<String, Object>()
                            .add("start",start)
                            .add("end", end)
                            .getMap(), callBack, RankbackDataModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);


        }

    }
    /**
     * 退出
     *
     * @param callBack
     */
    public void Logout(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.logout,
                    new MapBuilder<String, Object>()
                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }
    /**
     *到账信息
     *
     * @param callBack
     */
    public void AccountInfo(ViewNetCallBack callBack,String customerud) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.accountInfo,
                    new MapBuilder<String, Object>()
                            .add("customerId",customerud)
                            .getMap(), callBack, AccountInformationModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }
}
