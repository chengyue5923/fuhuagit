package com.fhzc.app.android.controller;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.MapBuilder;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.models.NewsIndexMapModel;
import com.fhzc.app.android.models.NewsItemModel;
import com.fhzc.app.android.models.NewsModel;
import com.fhzc.app.android.utils.net.ConnectTool;

import java.util.Map;

/**
 * Created by fw on 16/10/13.
 */

public class NewsController {
    private static NewsController instance;

    public static NewsController getInstance(){
        if(instance == null){
            instance = new NewsController();
        }
        return instance;
    }
    public void getAllNews(ViewNetCallBack callBack){
        try{
            ConnectTool.httpRequest(HttpConfig.getAllNews,new MapBuilder<String,Object>().getMap(),callBack, NewsModel.class);
        }catch(Exception e){

        }
    }

    public void getNewsIndex(ViewNetCallBack callBack){

        try {
            ConnectTool.httpRequest(HttpConfig.getNewsIndex, new MapBuilder<String, Object>().getMap(),callBack, NewsIndexMapModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getNewsTypeList(ViewNetCallBack callBack,int cid){

        try {
            ConnectTool.httpRequest(HttpConfig.getNewsTypeList, new MapBuilder<String, Object>()
                    .add("cid",cid).getMap(),callBack, NewsItemModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getNewsDetail(ViewNetCallBack callBack,int newsId){

        try {
            ConnectTool.httpRequest(HttpConfig.getNewsDetail, new MapBuilder<String, Object>()
                    .add("newsId",newsId).getMap(),callBack, NewsModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNewsReadcount(ViewNetCallBack callBack,int newsId){

        try {
            ConnectTool.httpRequest(HttpConfig.getNewsUpdate, new MapBuilder<String, Object>()
                    .add("newsId",newsId).getMap(),callBack, NewsModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
