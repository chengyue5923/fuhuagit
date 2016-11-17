package com.fhzc.app.android.configer;

import android.content.Context;

import com.base.platform.utils.android.Logger;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.configer.constants.Constant;


/**
 * 从资源中获取的rul 的res
 */
public class UrlRes {


    Context context;

    private String variableURL;

    public String getVariableURL() {
        return variableURL;
    }

    public void setVariableURL(String variableURL) {
        this.variableURL = variableURL;
        Logger.e("URL change to "+variableURL);
    }

    public static UrlRes getInstance() {

        return SingleClearCach.instance;

    }


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public String getUrl() {
        if(StringTools.isNullOrEmpty(variableURL)){
            return Constant.APP_URL;
        }else{
            return variableURL;
        }
    }


    public String getPictureUrl() {
        if(StringTools.isNullOrEmpty(variableURL)){
            return Constant.APP_PIC_URL;
        }else{
            return variableURL;
        }
    }

    static class SingleClearCach {
        static UrlRes instance = new UrlRes();
    }
}
