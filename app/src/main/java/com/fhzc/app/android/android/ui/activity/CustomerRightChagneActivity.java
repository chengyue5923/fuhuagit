package com.fhzc.app.android.android.ui.activity;

import android.support.v4.app.Fragment;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 权益
 * Created by lenovo on 2016/10/25.
 */
public class CustomerRightChagneActivity extends BaseActivity {
    @Bind(R.id.Toolbar)
    CommonToolBar Toolbar;

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
        return R.layout.activity_customer_point;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }
}
