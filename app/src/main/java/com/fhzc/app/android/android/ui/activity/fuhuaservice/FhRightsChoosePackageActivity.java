package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.RightsPackageAdapter;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * 私行权益选择套餐
 * Created by lenovo on 2016/7/11.
 */
public class FhRightsChoosePackageActivity extends BaseActivity implements View.OnClickListener{


    RightsPackageAdapter packageAdapter;
    @Bind(R.id.reportDetailToolbar)
    Toolbar reportDetailToolbar;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.right_detail_activity;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

        }
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }
}
