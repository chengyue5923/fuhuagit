package com.fhzc.app.android.android.ui.activity;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fw on 16/10/21.
 */

public class HotProductsActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener{


    @Bind(R.id.iv_lydc)
    ImageView lvYouDiChan;
    @Bind(R.id.iv_hwtz)
    ImageView haiWaiTouZi;
    @Bind(R.id.iv_qytz)
    ImageView quanYiTouZi;
    @Bind(R.id.iv_lltz)
    ImageView lingLeiTouZi;
    @Bind(R.id.tv_reserve)
    TextView reserve;
    @Bind(R.id.tv_collect)
    TextView collect;




    @Override
    public void onClick(View v) {
        int id = v.getId();
        int cid = 0;
        switch (id){
            case R.id.iv_lydc:
                //旅游地产
                cid = 2;
                IntentTools.startProductTypeList(this,cid);
                break;
            case R.id.iv_hwtz:
                //海外投资
                cid = 3;
                IntentTools.startProductTypeList(this,cid);
                break;
            case R.id.iv_qytz:
                //权益投资
                cid = 4;
                IntentTools.startProductTypeList(this,cid);
                break;
            case R.id.iv_lltz:
                //另类投资
                cid = 1;
                IntentTools.startProductTypeList(this,cid);
                break;
            case R.id.tv_reserve:
                IntentTools.startServationActivity(HotProductsActivity.this, UserPreference.getRoleId());
                break;
            case R.id.tv_collect:
                IntentTools.startCollectionActivity(HotProductsActivity.this, "我的关注");
                break;
        }


    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initEvent() {
        lvYouDiChan.setOnClickListener(this);
        haiWaiTouZi.setOnClickListener(this);
        quanYiTouZi.setOnClickListener(this);
        lingLeiTouZi.setOnClickListener(this);
        reserve.setOnClickListener(this);
        collect.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hotproducts;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void OnClickRight(View view) {

    }
}
