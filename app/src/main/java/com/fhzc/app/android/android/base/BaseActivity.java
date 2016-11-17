package com.fhzc.app.android.android.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.base.framwork.cach.preferce.PreferceManager;
import com.base.framwork.net.configer.Constans;
import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.android.application.BaseApplication;
import com.base.platform.utils.android.Logger;
import com.base.view.view.dialog.factory.DialogFacory;
import com.fhzc.app.android.R;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.event.LogoutEvent;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.android.SystemBarTintManager;


/**
 * Activity基类
 * 所有Activity的基类
 * Created by yanbo on 16-7-4.
 */
public abstract class BaseActivity extends AppCompatActivity implements ViewNetCallBack {
    protected Dialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        if (!EventManager.getInstance().isRegister(this))
            EventManager.getInstance().register(this);
        initView();
        dialog = DialogFacory.getInstance().createAlertDialog(this, "提示", "认证过期，请重新登录", "确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int which) {
                if (dialog!=null&&dialog.isShowing()&&!isFinishing()){
                    dialog.dismiss();
                }
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int which) {
                if (dialog!=null&&dialog.isShowing()&&!isFinishing()){
                    dialog.dismiss();
                }
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                logout();
            }
        });
        initEvent();
        initData();

    }




    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.getInstance().addActivity(this);
    }

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract void initData();

    protected abstract int getLayoutId();

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
    /**
     * 实例化沉浸式的bar
     */
    protected void initBarActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatuss(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.appColorBlue);//通知栏所需颜色
    }
    public void initBarActivity(boolean falg) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatuss(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        if(falg){
            tintManager.setStatusBarTintResource(R.color.appColorBlue);//通知栏所需颜色
        }else{
            tintManager.setStatusBarTintResource(R.color.transparence);//通知栏所需颜色
        }
    }
//    @TargetApi(19)
    private void setTranslucentStatuss(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventManager.getInstance().isRegister(this))
            EventManager.getInstance().unregister(this);
    }

    public void onEventMainThread(LogoutEvent event) {
        if (event!=null){
            Logger.e("onEventMainThread=====logout==");
            if (dialog!=null&&!dialog.isShowing()&&!isFinishing()){
                dialog.show();
            }
        }
    }
    @SuppressWarnings("unchecked")
    protected <T extends View> T r2v(View view, int resId) {
        return (T) view.findViewById(resId);
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T r2v(int resId) {
        return (T) findViewById(resId);
    }


    @Override
    public void onConnectStart() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onConnectEnd() {

    }
    /**
     * 实例化沉浸式的bar
     */
    protected void initBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(this, true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setTintResource(R.color.transparence);
        }
    }

    public static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
//            winParams.flags |= WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
//            winParams.flags |= WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);

    }

    protected void logout(){
//        new BaseDao().dropAllTable();

        IntentTools.startLogin(this);
//        PreferceManager.getInsance().saveValueBYkey("version", "");
        PreferceManager.getInsance().saveValueBYkey(Constant.LOGIN_TIME, "");
        PreferceManager.getInsance().saveValueBYkey(Constans.jseesion, "");
        FuhuaApplication.getInstance().clearActivities();
        finish();
    }
}
