package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.MyOrderRightPagerAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.configer.enums.MyOrderRightType;
import com.fhzc.app.android.controller.RightController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.OrderRightModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/7/21.
 */
public class CustomerMyRightFragment extends Fragment implements  ViewNetCallBack {
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
    //    SwipeRefreshLayout swipeLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_my_order_new_right, container, false);
        ButterKnife.bind(this, view);
        initData();
        myOrderRightPager.setOffscreenPageLimit(5);
        emptyLayout = new EmptyLayout(getActivity(), rootLayout);
        return view;
    }
    protected void initData() {
        myOrderRightToolBar.setTitle("我预约的权益");
        String[] strings = getResources().getStringArray(R.array.my_order_right);
        pagerAdapter = new MyOrderRightPagerAdapter(getChildFragmentManager(), strings);

        myOrderRightPager.setAdapter(pagerAdapter);
        myOrderRightTabLayout.setupWithViewPager(myOrderRightPager);
        myOrderRightTabLayout.setTabsFromPagerAdapter(pagerAdapter);
//        RightController.getInstance().myOrderRight(this, uid == -1 ? UserPreference.getPrefKeyCustomerid() : uid);
        RightController.getInstance().myOrderRight(this,UserPreference.getRoleId());
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onConnectStart() {
        emptyLayout.showLoading();
    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {
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
