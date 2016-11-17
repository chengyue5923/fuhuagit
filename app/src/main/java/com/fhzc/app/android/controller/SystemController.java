package com.fhzc.app.android.controller;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.android.Logger;
import com.base.platform.utils.java.MapBuilder;
import com.fhzc.app.android.BuildConfig;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.models.AppVersionModel;
import com.fhzc.app.android.models.SystemNoticeModel;
import com.fhzc.app.android.utils.net.ConnectTool;

/**
 * Created by yanbo on 2016/7/28.
 */
public class SystemController {
    private static SystemController instance;


    public static SystemController getInstance() {
        if (instance == null)
            instance = new SystemController();
        return instance;
    }
    /**
     * 系统公告
     */
    public void systemNotice(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.systemNotice,
                    new MapBuilder<String, Object>()
                            .getMap(),callBack, SystemNoticeModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }
    /**
     * 版本更新
     */
    public void latestApp(ViewNetCallBack callBack) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.latestApp,
                    new MapBuilder<String, Object>()
                            .add("version", BuildConfig.VERSION_NAME)
                            .getMap(),callBack, AppVersionModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }
    /**
     *设备信息
     *
     * @param callBack
     */
    public void DeviceInfo(ViewNetCallBack callBack,String model,String osVersion,String resolution) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.deviceInfo,
                    new MapBuilder<String, Object>()
                            .add("version", BuildConfig.VERSION_NAME)
                            .add("model", model)
                            .add("osVersion", osVersion)
                            .add("resolution", resolution)
                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }

    }
}
