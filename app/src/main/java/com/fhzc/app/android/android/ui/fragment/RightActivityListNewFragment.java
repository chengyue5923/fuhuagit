package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.ListUtil;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.ActivityController;
import com.fhzc.app.android.models.ActivityModel;
import com.fhzc.app.android.models.ActivityModels;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/8/1.
 */
public class RightActivityListNewFragment extends Fragment implements ViewNetCallBack {


    @Bind(R.id.rightActivityImage)
    View rightActivityImage;
    @Bind(R.id.item_gallery)
    LinearLayout itemGallery;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    @Bind(R.id.ScrollViewLayout)
    ScrollViewExtend ScrollViewLayout;
    SwipeRefreshLayout swipeLayout;
    private LayoutInflater mInflater;
    int page = 1;
    boolean canLoad = true;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_activity_list, container, false);
        ButterKnife.bind(this, view);
        mInflater = LayoutInflater.from(getActivity());
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 5000);
                page = 1;
                canLoad = true;
                initData();
            }
        });
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);

        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        ScrollViewLayout.setCallbacks(new ScrollViewExtend.ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int scrollY) {

            }

            @Override
            public void onScrollBottom() {
                if (!canLoad)
                    return;
                canLoad = false;
                swipeLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        loadActivity();
                        swipeLayout.setRefreshing(true);

                    }
                });

            }
        });
    }

    private void initData() {
        ActivityController.getInstance().activityList(this,page);
    }

    public void loadActivity(){
        ActivityController.getInstance().activityList(new ViewNetCallBack() {
            @Override
            public void onConnectStart() {

            }

            @Override
            public void onConnectEnd() {
                swipeLayout.setRefreshing(false);
            }

            @Override
            public void onFail(Exception e) {
//                emptyLayout.showError();
                canLoad = true;
            }

            @Override
            public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

                if(flag== HttpConfig.activityList.getType()){
                    ActivityModels models = (ActivityModels) result;
                    if (ListUtil.isNullOrEmpty(models.getItems())) {
                        canLoad = false;
                        return;
                    }

                    if(page!=1){
                        AddView(models);
                    }

                    if(models.getItems().size()>=25){
                        canLoad = true;
                        page++;
                    }
                }
            }
        }, page);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {

    }

    public void AddView(ActivityModels models){
        for (int i = 0; i < models.getItems().size(); i++) {
            final ActivityModel model = models.getItems().get(i);
            View view = mInflater.inflate(R.layout.view_activity_new_item,
                    itemGallery, false);
            view.setTag(i);
            ImageView backImg = (ImageView) view
                    .findViewById(R.id.activity_item_image);
            ImageView statuImg = (ImageView) view
                    .findViewById(R.id.activity_item_status);
            TextView textViewtime = (TextView) view.findViewById(R.id.activity_item_time);
            TextView textViewaddress = (TextView) view.findViewById(R.id.activity_item_address);
            if (!String.valueOf(model.getIsApplyed()).equals("")) {
                if (model.getIsApplyed() == 0) {
                    statuImg.setImageResource(R.drawable.activity_notstrart);
                } else if (model.getIsApplyed() == 1) {
                    statuImg.setImageResource(R.drawable.activity_underway);
                }
//                else if (model.getStatus() == 2) {
//                    statuImg.setImageResource(R.drawable.activity_input);
//                } else if (model.getStatus() == 3) {
//                    statuImg.setImageResource(R.drawable.complete);
//                }
            }
            ImageLoader.getInstance(getActivity(), R.drawable.default_error).displayImage(model.getCover(), backImg);
            String date=DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getApplyEndTime());
            String dates=date.substring(0,4)+"."+date.substring(5,7)+"."+date.substring(8,11);
            textViewtime.setText("报名截止日期：" + dates);
            textViewaddress.setText("活动地点：" + model.getAddress());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentTools.startActivityDetail(getActivity(), model.getId());
                }
            });
            itemGallery.addView(view);
        }
    }
    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        ActivityModels models = (ActivityModels) result;
        swipeLayout.setRefreshing(false);
        itemGallery.removeAllViews();

        List<ActivityModel> listModel=new ArrayList<>();
        listModel.clear();
        List<ActivityModel> object=new ArrayList<>();
        object.clear();
        for (int i = 0; i < models.getItems().size(); i++){
            final ActivityModel model =models.getItems().get(i);
            if(model.getStatus()!=3){
                listModel.add(model);
            }else{
                model.setIssign(false);
                object.add(model);
            }
        }
        ActivityModel mo=new ActivityModel();
        mo.setStatus(10000);
        listModel.add(mo);
        if(object.size()>0){
            listModel.addAll(object);
        }
        for(int i=0;i<listModel.size();i++){
        }

        for (int i = 0; i < listModel.size(); i++) {
            if(listModel.get(i).getStatus()==10000){
                View viewss = mInflater.inflate(R.layout.view_my_activity_status,
                        itemGallery, false);
                itemGallery.addView(viewss);
            }else{
                final ActivityModel model =listModel.get(i);
                View view = mInflater.inflate(R.layout.view_activity_new_item,
                        itemGallery, false);
                view.setTag(i);
                ImageView backImg = (ImageView) view
                        .findViewById(R.id.activity_item_image);
                ImageView statuImg = (ImageView) view
                        .findViewById(R.id.activity_item_status);
                TextView textViewtime=(TextView)view.findViewById(R.id.activity_item_time);
                TextView textViewaddress=(TextView)view.findViewById(R.id.activity_item_address);
                if(model.issign()){
                        if(model.getIsApplyed()==0){
//                ("查看详情");
                            statuImg.setImageResource(R.drawable.activity_now);
                        }else if(model.getStatus()==1){
//                 ("立即参加");
                            statuImg.setImageResource(R.drawable.activity_underway);
                        }
//                    else if(model.getStatus()==2){
////                   ("报名结束");
//                        statuImg.setImageResource(R.drawable.activity_now);
//
//                    }else if(model.getStatus()==3){
////                   ("活动结束");
////                        statuImg.setImageResource(R.drawable.activity_signup);
//                    }
                }
                ImageLoader.getInstance(getActivity(), R.drawable.default_error).displayImage(model.getCover(), backImg);
                String date=DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getApplyEndTime());
                String dates=date.substring(0,4)+"."+date.substring(5,7)+"."+date.substring(8,11);

                textViewtime.setText("报名截止日期："+dates);
                textViewaddress.setText("活动地点："+model.getAddress());
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentTools.startActivityDetail(getActivity(), model.getId());
                    }
                });
                itemGallery.addView(view);
            }
        }
    }
}
