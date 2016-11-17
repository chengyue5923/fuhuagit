package com.fhzc.app.android.event;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.base.platform.android.application.BaseApplication;
import com.fhzc.app.android.configer.UrlRes;
import com.fhzc.app.android.utils.im.CommonUtil;

/**
 * Created by fw on 16/11/16.
 */

public class ListenNetChangeReceiver extends BroadcastReceiver {

    private ConnectivityManager mConnectivityManager;

    private NetworkInfo netInfo;

    @Override
    public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {


        mConnectivityManager = (ConnectivityManager) (BaseApplication.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE));
        netInfo = mConnectivityManager.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isAvailable()) {

            /////////////网络连接
            String name = netInfo.getTypeName();

            if(netInfo.getType()==ConnectivityManager.TYPE_WIFI){
                /////WiFi网络
                /**
                 * 如果是公司wifi访问内网ip 否则访问外网ip
                 */
                String wifiName = CommonUtil.getWifiName();
                if(wifiName.equals("Foriseassets")||wifiName.equals("\"Foriseassets\"")){
                    UrlRes.getInstance().setVariableURL("http://10.0.182.50/");
                }

            }else if(netInfo.getType()==ConnectivityManager.TYPE_ETHERNET){
                /////有线网络

            }else if(netInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                //蜂窝网络
                UrlRes.getInstance().setVariableURL(null);
            }
        } else {
            ////////网络断开
            UrlRes.getInstance().setVariableURL(null);
        }
//            }
    }
}
