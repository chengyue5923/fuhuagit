package com.fhzc.app.android.utils.net;

import com.base.framwork.net.lisener.NetCallback;
import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.framwork.utils.GsonTool;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.configer.enums.HttpManager;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.event.LogoutEvent;
import com.fhzc.app.android.models.HttpConfigBean;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;


/**
 * 对底层接口的 对接类
 */
public class BaseNetImpl implements NetCallback {


    ViewNetCallBack listener;
    Class entityClass;
    HttpConfigBean config;
    Map<String, Object> param;
    HttpConfig con;

    public BaseNetImpl(ViewNetCallBack listener, Class entityClass, HttpConfig config) {
        this.listener = listener;
        this.entityClass = entityClass;
        this.config = HttpManager.getInstance().getConifgById(config);
        con = config;

    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    @Override
    public void dealReslut(String jsonstring) {
        try {
//            if (config.isCach()) {
//                String url = MapUtils.map2UrlParams(config.getActioin(), param);
//                PreferceManager.getInsance().saveValueBYkey(MD5Util.string2MD5(url), jsonstring);
//            }
            JSONObject jsonObject = new JSONObject(jsonstring);
            if (jsonObject.getInt("code") == 401&&config.getId()!=HttpConfig.yapull.getType()) {
                EventManager.getInstance().post(new LogoutEvent());
                return;
            }
            if(config.getId()==HttpConfig.rankBetween.getType()||config.getId()==HttpConfig.rightList.getType()){
                listener.onData((Serializable) GsonTool.jsonToArrayEntity(jsonObject.getString(config.getType()), entityClass), config.getId(), true, jsonstring,param );
                return;
            }
            try {
                listener.onData((Serializable) GsonTool.jsonToEntity(jsonObject.getString(config.getType()), entityClass), config.getId(), true, jsonstring,param );
            } catch (Exception e) {
                listener.onData((Serializable) GsonTool.jsonToArrayEntity(jsonObject.getString(config.getType()), entityClass), config.getId(), true, jsonstring, param);
            }

        } catch (Exception e) {
            listener.onFail(e);
        }
    }

    @Override
    public void dealFailer(Exception e, String jsonstring) {
        try {
            JSONObject jsonObject = new JSONObject(jsonstring);
            if (jsonObject.getInt("code") == 401&&config.getId()!=HttpConfig.yapull.getType()) {

                EventManager.getInstance().post(new LogoutEvent());
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

//        if (config.isCach()) {
//            String url = MapUtils.map2UrlParams(config.getActioin(), param);
//            try {
//                String jsonstring = PreferceManager.getInsance().getValueBYkey(MD5Util.string2MD5(url));
//                JSONObject jsonObject = new JSONObject(jsonstring);
//                String data = jsonObject.getString("data");
//                try {
//                    listener.onData((Serializable) GsonTool.jsonToEntity(data, entityClass), config.getId(), true, jsonstring);
//                } catch (Exception e2) {
//                    listener.onData((Serializable) GsonTool.jsonToArrayEntity(data, entityClass), config.getId(), true, jsonstring);
//                }
//                return;
//            } catch (Exception e1) {
//
//            }
//        }
        listener.onFail(e);
    }

    @Override
    public void onstart() {

        listener.onConnectStart();
//        String url = MapUtils.map2UrlParams(config.getActioin(), param);
//        String jsonstring = PreferceManager.getInsance().getValueBYkey(MD5Util.string2MD5(url));
//        if (!StringTools.isNullOrEmpty(jsonstring) && config.isCach()) {
//            dealReslut(jsonstring);
//        }


    }

    @Override
    public void onEnd() {
        listener.onConnectEnd();
    }
}
