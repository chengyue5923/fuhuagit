package com.fhzc.app.android.android.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.CustomerDetailPagerAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.models.MemberModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/8/1.
 */
public class CustomerDetailActivity extends BaseActivity implements View.OnClickListener,CommonToolBar.ClickRightListener {
    @Bind(R.id.customerDetailToolBar)
    CommonToolBar customerDetailToolBar;
    @Bind(R.id.customerDetailTabLayout)
    TabLayout customerDetailTabLayout;
    @Bind(R.id.customerDetailPager)
    ViewPager customerDetailPager;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    CustomerDetailPagerAdapter pagerAdapter;
    MemberModel model;
    @Override
    protected void initView() {
    ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        customerDetailToolBar.setClickRightListener(this);
    }

    @Override
    protected void initData() {
        model = (MemberModel)getIntent().getSerializableExtra("model");
        if (null==model)
            return;
        customerDetailToolBar.dismissLine();
        customerDetailToolBar.setBackRoot();
//        customerDetailToolBar.setTitle(model.getRealname());
        String[] strings = getResources().getStringArray(R.array.customer_detail);
        pagerAdapter = new CustomerDetailPagerAdapter(getSupportFragmentManager(), strings,model);

        customerDetailPager.setAdapter(pagerAdapter);
        customerDetailTabLayout.setupWithViewPager(customerDetailPager);
        customerDetailTabLayout.setTabsFromPagerAdapter(pagerAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_detail;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnClickRight(View view) {
//        IntentTool.chat(this,null,model.getUid());
        IntentTools.startChatList(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
