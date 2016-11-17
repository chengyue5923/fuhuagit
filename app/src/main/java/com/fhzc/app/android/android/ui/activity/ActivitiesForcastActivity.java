package com.fhzc.app.android.android.ui.activity;


import android.view.View;
import android.widget.ImageView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fw on 16/10/18.
 */

public class ActivitiesForcastActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.iv_tzzjy)
    ImageView ivTZZJLB;
    @Bind(R.id.iv_jkys)
    ImageView ivSMGL;
    @Bind(R.id.iv_gdpj)
    ImageView ivSLJLB;
    @Bind(R.id.iv_qzjy)
    ImageView ivJYCC;
    @Bind(R.id.iv_tyjj)
    ImageView ivGEFJLB;
    @Bind(R.id.toolBar)
    CommonToolBar toolbar;


    @Override
    protected void initView() {
        ButterKnife.bind(this);
        toolbar.dismissLine();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initEvent() {
        ivGEFJLB.setOnClickListener(this);
        ivTZZJLB.setOnClickListener(this);
        ivSMGL.setOnClickListener(this);
        ivSLJLB.setOnClickListener(this);
        ivJYCC.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        toolbar.setTitle("活动预报");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_activitiesforcast;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int cid = 0 ;
        String title = "投资者教育";
        switch (id){
            case R.id.iv_tzzjy://投资者教育 cid 5
                cid = 5;
                break;
            case R.id.iv_jkys://健康养生  生命管理 cid 1
                title = "健康养生";
                cid = 1;
                break;
            case R.id.iv_gdpj://高端品鉴 cid 4
                title = "高端品鉴";
                cid = 4;
                break;
            case R.id.iv_qzjy://亲自教育 cid 3
                title = "亲子教育";
                cid = 3;
                break;
            case R.id.iv_tyjj://体育竞技 cid 2
                title = "体育竞技";
                cid = 2;
                break;
        }
        if(cid!=0){
            IntentTools.startActivityTypeList(this,cid,title);

        }

    }
}
