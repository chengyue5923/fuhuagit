package com.fhzc.app.android.android.ui.activity;

import android.support.v4.app.Fragment;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;

import java.io.Serializable;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * @deprecated
 * @param context
 */
public class CustomerRightSelectActivity extends BaseActivity {
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        getSupportFragmentManager().beginTransaction().show(fragment).commit();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_select_member_benefits;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

}
