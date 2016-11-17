package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.OrderActivityRightsListAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.RightController;
import com.fhzc.app.android.models.OrderRightModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 私行权益列表（会员权益下）
 * Created by lenovo on 2016/7/11.
 */
public class FhRightsListActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.rightListView)
    ListView rightListView;
    @Bind(R.id.rightListToolbar)
    CommonToolBar rightListToolbar;
    List<OrderRightModel> models = new ArrayList<>();

    OrderActivityRightsListAdapter rightsListAdapter;
    private EmptyLayout mEmptyLayout;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        mEmptyLayout = new EmptyLayout(this, rightListView);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 5000);

                initData();
            }
        });
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);
    }


//    public void onEventMainThread(IMEvent event) {
//        if (null != event) {
//            rightListToolbar.setRedImage(new MessageDao().getUnReadCount());
//        }
//    }

    @Override
    protected void initEvent() {
        rightListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentTools.startNewRightDetail(FhRightsListActivity.this, Integer.parseInt(models.get(i).getId()));
            }
        });
    }

    @Override
    protected void initData() {
        String title = getIntent().getStringExtra("rightNumber");
        int cid = getIntent().getIntExtra("cid", -1);
        rightListToolbar.setTitle(title);
        rightsListAdapter = new OrderActivityRightsListAdapter(this,false);
        rightListView.setAdapter(rightsListAdapter);
        RightController.getInstance().rightList(this, cid);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_right_list_detail;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public void onConnectStart() {
        super.onConnectStart();
        mEmptyLayout.showLoading();
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

        List<OrderRightModel> list = (  List<OrderRightModel>) result;
        swipeLayout.setRefreshing(false);
        mEmptyLayout.showSuccess(list.size() <= 0);
        models.addAll(list);
        rightsListAdapter.setRes(list);

    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
        mEmptyLayout.showError();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }
}
