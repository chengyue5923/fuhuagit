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
import com.fhzc.app.android.android.ui.view.adapter.CustomerMinePagerAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.CommonUtil;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 16-7-11.
 */
public class CustomerMineFragment extends Fragment implements ViewNetCallBack,CommonToolBar.ClickRightListener{

    CustomerMinePagerAdapter pagerAdapter;
    @Bind(R.id.myTabLayout)
    TabLayout myTabLayout;
    @Bind(R.id.my_pager)
    ViewPager myPager;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    @Bind(R.id.messageLayout)
    RelativeLayout messageLayout;
    @Bind(R.id.messageIv)
    ImageView messageIv;
    @Bind(R.id.messageRedImage)
    TextView messageRedImage;


    public static CustomerMineFragment newInstance() {
        CustomerMineFragment squareFragmentV2 = new CustomerMineFragment();
        return squareFragmentV2;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wealth, container, false);
        ButterKnife.bind(this, view);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        initView();
        initData();
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

    private void initView() {
        if (CommonUtil.isUpperKK()) {
            View view = new View(getActivity());
            view.setBackgroundColor(getResources().getColor(R.color.common_title));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight(getActivity()));
            view.setLayoutParams(layoutParams);
            rootLayout.addView(view, 0);
        }
        myPager.setOffscreenPageLimit(4);
    }

    private void initData() {
        int count = new MessageDao().getUnReadCount();
        messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
        messageRedImage.setText(String.valueOf(count));
//        myToolBar.setRedImage(new MessageDao().getUnReadCount());
        String[] strings = getResources().getStringArray(R.array.my_tab);
        pagerAdapter = new CustomerMinePagerAdapter(getChildFragmentManager(), strings);

        myPager.setAdapter(pagerAdapter);
        myTabLayout.setupWithViewPager(myPager);
        myTabLayout.setTabsFromPagerAdapter(pagerAdapter);
//        UserController.getInstance().mine(this);
//        myToolBar.setClickRightListener(this);
        messageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentTools.startChatList(getActivity());
            }
        });
        messageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentTools.startChatList(getActivity());
            }
        });
    }
    public void onEventMainThread(IMEvent event) {
        try{
            if (null != event) {
                int count = new MessageDao().getUnReadCount();
                messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
                messageRedImage.setText(String.valueOf(count));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        int count = new MessageDao().getUnReadCount();
        messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
        messageRedImage.setText(String.valueOf(count));
        CommonUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
        CommonUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(getActivity());
    }
}
