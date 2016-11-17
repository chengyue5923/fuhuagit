package com.fhzc.app.android.android.ui.activity.systemservice;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.ListUtil;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.CustomerMyAttentionAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.models.CollectMapModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 系统服务 我的关注
 * Created by lenovo on 2016/7/11.
 */
public class CustomerMyAttention extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {


    private static final String POSITION = "position";

    CustomerMyAttentionAdapter pagerAdapter;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.sliding_tabs)
    TabLayout tabLayout;
    @Bind(R.id.activityDetailToolbar)
    CommonToolBar activityDetailToolbar;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    private EmptyLayout emptyLayout;
    CollectMapModel model;
//    SwipeRefreshLayout swipeLayout;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        viewPager.setOffscreenPageLimit(20);
        String[] strings = getResources().getStringArray(R.array.my_attention);
        pagerAdapter = new CustomerMyAttentionAdapter(getSupportFragmentManager(), strings);
        viewPager.setAdapter(pagerAdapter);
//        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_containersss);
        emptyLayout = new EmptyLayout(this, rootLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(pagerAdapter);

//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 5000);
//                initData();
//            }
//        });
//        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, viewPager.getCurrentItem());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

    @Override
    protected void initEvent() {
        activityDetailToolbar.setClickRightListener(this);
    }

    @Override
    protected void initData() {
        activityDetailToolbar.setTitle(getIntent().getStringExtra("title"));
        UserController.getInstance().myCollection(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fh_my_collect_activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }
    }

    @Override
    public void onConnectStart() {
        super.onConnectStart();
        emptyLayout.showLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
        emptyLayout.showError();

    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        model = (CollectMapModel) result;
        emptyLayout.showSuccess(false);
//        swipeLayout.setRefreshing(false);
        pagerAdapter.setData(model);

    }

    @Override
    public void OnClickRight(View view) {
        if (model == null)
            return;
        CollectMapModel model = pagerAdapter.getModelByPosition(viewPager.getCurrentItem());
        if (viewPager.getCurrentItem() == 0&&ListUtil.isNullOrEmpty(model.getProduct())) {
            ToastTool.show("关注的产品为空，不能编辑");
            return;
        }
        if (viewPager.getCurrentItem() == 1&&ListUtil.isNullOrEmpty(model.getReport())) {
            ToastTool.show("关注的报告为空，不能编辑");
            return;
        }
        if (viewPager.getCurrentItem() == 2&&ListUtil.isNullOrEmpty(model.getActivity())) {
            ToastTool.show("关注的活动为空，不能编辑");
            return;
        }
        if (viewPager.getCurrentItem() == 3&&ListUtil.isNullOrEmpty(model.getRights())) {
            ToastTool.show("关注的权益为空，不能编辑");
            return;
        }
        IntentTools.startEditCollect(this, model,viewPager.getCurrentItem());
    }
}
