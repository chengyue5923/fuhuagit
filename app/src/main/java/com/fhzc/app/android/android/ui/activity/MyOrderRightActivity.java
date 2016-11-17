package com.fhzc.app.android.android.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.MyOrderRightPagerAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.configer.enums.MyOrderRightType;
import com.fhzc.app.android.controller.RightController;
import com.fhzc.app.android.models.OrderRightModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/7/25.
 */
public class MyOrderRightActivity extends BaseActivity {
    @Bind(R.id.myOrderRightToolBar)
    CommonToolBar myOrderRightToolBar;
    @Bind(R.id.myOrderRightTabLayout)
    TabLayout myOrderRightTabLayout;
    @Bind(R.id.myOrderRightPager)
    ViewPager myOrderRightPager;
    MyOrderRightPagerAdapter pagerAdapter;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    EmptyLayout emptyLayout;
    private int uid = -1;
//    SwipeRefreshLayout swipeLayout;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        myOrderRightPager.setOffscreenPageLimit(5);
        emptyLayout = new EmptyLayout(this, rootLayout);
//        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
//        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
////                new Handler().postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        swipeLayout.setRefreshing(false);
////                    }
////                }, 5000);
//
//                initData();
//            }
//        });
//        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        uid = getIntent().getIntExtra("uid", -1);
        if(getIntent().getStringExtra("name")!=null){
            myOrderRightToolBar.setTitle("客户预约的权益");
        }else{
            myOrderRightToolBar.setTitle("我预约的权益");
        }
        String[] strings = getResources().getStringArray(R.array.my_order_right);
        pagerAdapter = new MyOrderRightPagerAdapter(getSupportFragmentManager(), strings);

        myOrderRightPager.setAdapter(pagerAdapter);
        myOrderRightTabLayout.setupWithViewPager(myOrderRightPager);
        myOrderRightTabLayout.setTabsFromPagerAdapter(pagerAdapter);
//        RightController.getInstance().myOrderRight(this, uid == -1 ? UserPreference.getPrefKeyCustomerid() : uid);
        RightController.getInstance().myOrderRight(this,uid);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order_right;
    }

    @Override
    public void onConnectStart() {
        super.onConnectStart();
        emptyLayout.showLoading();
    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
        emptyLayout.showError();
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        emptyLayout.showSuccess(false);
//        swipeLayout.setRefreshing(false);
        List<OrderRightModel> list = (List<OrderRightModel>) result;
        List<OrderRightModel> orderList = new ArrayList<>();
        List<OrderRightModel> successList = new ArrayList<>();
        List<OrderRightModel> finishList = new ArrayList<>();
        for (OrderRightModel model : list) {
            if (model.getStatus() == 0) {
                orderList.add(model);
            } else if (model.getStatus() == 1) {
                successList.add(model);
            } else {
                finishList.add(model);
            }
        }
        HashMap<Integer, List<OrderRightModel>> listHashMap = new HashMap<>();
        listHashMap.put(MyOrderRightType.ORDER_RIGHT_TYPE_ORDER.getType(), orderList);
        listHashMap.put(MyOrderRightType.ORDER_RIGHT_TYPE_SUCCESS.getType(), successList);
        listHashMap.put(MyOrderRightType.ORDER_RIGHT_TYPE_FINISH.getType(), finishList);
        pagerAdapter.setData(listHashMap);
    }


}
