package com.fhzc.app.android.android.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.MineTabAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.utils.im.CommonUtil;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 16-7-11.
 */
public class CustomerMineNewFragment extends Fragment implements ViewNetCallBack, CommonToolBar.ClickRightListener {
    @Bind(R.id.myTabLayout)
    TabLayout myTabLayout;
    @Bind(R.id.messageIv)
    ImageView messageIv;
    @Bind(R.id.messageRedImage)
    TextView messageRedImage;
    @Bind(R.id.messageLayout)
    RelativeLayout messageLayout;
    @Bind(R.id.my_pager)
    ViewPager myPager;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;

    MineTabAdapter tabAdapter;
    public static CustomerMineNewFragment newInstance() {
        CustomerMineNewFragment squareFragmentV2 = new CustomerMineNewFragment();
        return squareFragmentV2;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wealth_new, container, false);
        ButterKnife.bind(this, view);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        try{
            String[] strings = getResources().getStringArray(R.array.mine_tab);
            tabAdapter = new MineTabAdapter(getActivity().getSupportFragmentManager(), strings);
            myPager.setAdapter(tabAdapter);
            myTabLayout.setupWithViewPager(myPager);
            myTabLayout.setTabsFromPagerAdapter(tabAdapter);
            myPager.setOffscreenPageLimit(20);
        }catch (Exception e){
            e.printStackTrace();
        }

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        CommonUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
        CommonUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void OnClickRight(View view) {
    }
}
