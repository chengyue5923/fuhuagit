package com.fhzc.app.android.android.base;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Environment;

import com.base.framwork.cach.db.factory.DBFactory;
import com.base.platform.android.application.BaseApplication;
import com.base.platform.utils.android.Logger;
import com.base.platform.utils.java.FileTools;
import com.fhzc.app.android.android.ui.activity.AnimActivity;
import com.fhzc.app.android.android.ui.activity.SetPasswordActivity;
import com.fhzc.app.android.android.ui.activity.systemservice.FhLoginActivity;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.configer.enums.HttpManager;
import com.fhzc.app.android.controller.IMController;
import com.fhzc.app.android.event.ListenNetChangeReceiver;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;

import java.io.File;

/**
 * Created by yanbo on 16-7-4.
 */
public class FuhuaApplication extends BaseApplication {
    public static String cachePath="";
    @Override
    public Bundle getSunBundle() {
        Bundle bundle = new Bundle();
        File file = new File(Constant.LOGPATH);
        if (!file.exists()) {
            file.mkdirs();

        }
        bundle.putString("logPath", Constant.LOGPATH);
        bundle.putBoolean("writeLog", Constant.LOG_DEBUG);
        return bundle;
    }

    @Override
    public void initData() {
        //// TODO: 16-7-5  初始化一些操作
        cachePath = Environment.getExternalStorageDirectory().getPath() + File.separator + this.getCacheDir();
        FileTools.creatDir(cachePath);
        initHttpConfig();
        boolean writeLog = Constant.DEBUG;
        Logger.initDebug(writeLog);
        String audioDir = Constant.AUDIO_PATH;
        String audioTempDir = Constant.AUDIO_TEMP_PATH;
        FileTools.creatDir(audioDir);
        FileTools.creatDir(audioTempDir);
        DBFactory factory = DBFactory.getInstance();
        factory.setContext(getApplicationContext());
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                PushAgent.getInstance(FuhuaApplication.this).onAppStart();
                PushAgent.getInstance(FuhuaApplication.this).enable();
                Logger.e("UmengRegistrar.getRegistrationId(context)" + UmengRegistrar.getRegistrationId(FuhuaApplication.this));
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                Logger.e("onActivityResumed ===" + activity.getClass().getSimpleName());
                if (activity instanceof FhLoginActivity) {
                    return;
                }
                if (activity instanceof SetPasswordActivity) {
                    return;
                }
                if (activity instanceof AnimActivity) {
                    return;
                }
                IMController.getInstance().oneshotSync(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Logger.e("onActivityDestroyed ===" + activity.getClass().getSimpleName());
            }
        });

        ListenNetChangeReceiver receiver = new ListenNetChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver,filter);

    }

    /**
     * 实例化http的配置文件 因为放到了 values 用系统的 string 解析 并且用gson的 解析 速度暂时不考虑
     */
    private void initHttpConfig() {
        new Thread(initConfigRunnable).start();
    }

    Runnable initConfigRunnable = new Runnable() {
        @Override
        public void run() {
            HttpManager.getInstance().init();
        }
    };






}
