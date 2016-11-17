package com.fhzc.app.android.controller;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.MapBuilder;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.LoginResultModel;
import com.fhzc.app.android.models.ReportItemModel;
import com.fhzc.app.android.models.ReportListMapModel;
import com.fhzc.app.android.models.ReportModel;
import com.fhzc.app.android.models.ReportModels;
import com.fhzc.app.android.models.ReportTypeModel;
import com.fhzc.app.android.models.RightModels;
import com.fhzc.app.android.utils.net.ConnectTool;

/**
 * Created by yanbo on 2016/7/22.
 */
public class ReportController {
    private static ReportController instance;


    public static ReportController getInstance() {
        if (instance == null)
            instance = new ReportController();
        return instance;
    }
    public void reportList(ViewNetCallBack callBack){
        try {
            ConnectTool.httpRequest(HttpConfig.reportList, new MapBuilder<String, Object>()

                    .getMap(), callBack, ReportModels.class);
        } catch (Exception e) {
        }
    }
    /**
     * 关注报告
     */
    public void focusReport(ViewNetCallBack callBack, int reportId) {
        try {
            ConnectTool.httpRequest(HttpConfig.focus, new MapBuilder<String, Object>()
                    .add("uid", UserPreference.getUid())
                    .add("fid", reportId)
                    .add("ftype", "report")
                    .add("status", 1)

                    .getMap(), callBack, RightModels.class);
        } catch (Exception e) {
        }
    }

    /**
     * 取消关注报告
     */

    public void cancelFocusReport(ViewNetCallBack callBack, int reportId) {
        try {
            ConnectTool.httpRequest(HttpConfig.focus, new MapBuilder<String, Object>()
                    .add("uid", UserPreference.getUid())
                    .add("fid", reportId)
                    .add("ftype", "report")
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
                    .add("ftype", "report")
                    .getMap(), callBack, Integer.class);
        } catch (Exception e) {
        }
    }

    /**
     * 查看关注状态
     */

    public void reportDetail(ViewNetCallBack callBack, int reportId) {
        try {
            ConnectTool.httpRequest(HttpConfig.reportDetail, new MapBuilder<String, Object>()
                    .add("reportId", reportId)
                    .getMap(), callBack, ReportModel.class);
        } catch (Exception e) {
        }
    }
    /**
     * 查看获取报告类别分类
     */

    public void reportTypeListDetail(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(HttpConfig.getReportType, new MapBuilder<String, Object>()
                    .getMap(), callBack, ReportTypeModel.class);
        } catch (Exception e) {
        }
    }
    /**
     * 查看获取报告列表通过类别分类
     */

    public void reportTypeListByTypeDetail(ViewNetCallBack callBack, int reportId) {
        try {
            ConnectTool.httpRequest(HttpConfig.getReportListByType, new MapBuilder<String, Object>()
                    .add("cid", reportId)
                    .getMap(), callBack, ReportItemModel.class);
        } catch (Exception e) {
        }
    }
    /**
     * 查看获取报告列表
     */

    public void reportListSDetail(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(HttpConfig.getReportLists, new MapBuilder<String, Object>()
                    .getMap(), callBack, ReportListMapModel.class);
        } catch (Exception e) {
        }
    }
    /**
     * 查看获取报告
     */

    public void reportTypeClickDetail(ViewNetCallBack callBack, int reportId) {
        try {
            ConnectTool.httpRequest(HttpConfig.getReportClick, new MapBuilder<String, Object>()
                    .add("reportId", reportId)
                    .getMap(), callBack, ReportItemModel.class);
        } catch (Exception e) {
        }
    }

}
