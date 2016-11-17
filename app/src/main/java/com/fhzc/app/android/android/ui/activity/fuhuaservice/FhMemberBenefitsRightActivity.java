package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.RightAndPointListAdapter;
import com.fhzc.app.android.android.ui.view.dialog.ActivityNoticeDialog;
import com.fhzc.app.android.android.ui.view.widget.CustomViewPager;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 新会员权益滑动列表
 * Created by lenovo on 2016/7/11.
 */
public class FhMemberBenefitsRightActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.backImageRight)
    ImageView backImageRight;
    @Bind(R.id.messageImage)
    ImageView messageImage;
    @Bind(R.id.TabLayout)
    TabLayout TabLayoutView;
    @Bind(R.id.viewPager)
    CustomViewPager rightPager;
    RightAndPointListAdapter ListAdapter;
    @Bind(R.id.messageRedImage)
    TextView messageRedImage;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        String[] strings = getResources().getStringArray(R.array.righttab);
        ListAdapter = new RightAndPointListAdapter(getSupportFragmentManager(), strings);
        rightPager.setAdapter(ListAdapter);
        TabLayoutView.setupWithViewPager(rightPager);

            TabLayoutView.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if(tab.getPosition()==1){
                    if(UserPreference.getLevel().equals("投资人")){
                        rightPager.setScanScroll(false);
                            PopUpDialog();
//                        ToastTool.show("尊敬的客户：您好，您目前尚未入会，不能查看会员活动，请联系您的专属理财师或拨打热线电话【400-610-6100】申请入会，谢谢您的支持和配合。");
                        }else{
                        rightPager.setCurrentItem(1);
                    }
                        TabLayoutView.setTabTextColors(getResources().getColor(R.color.personalrigh),getResources().getColor(R.color.personalrightactivity));
                    }else{
                        TabLayoutView.setTabTextColors(getResources().getColor(R.color.personalrigh),getResources().getColor(R.color.personalrightselet));
                        rightPager.setCurrentItem(0);
                    }

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        TabLayoutView.setTabsFromPagerAdapter(ListAdapter);
        rightPager.setOffscreenPageLimit(4);
    }
    @Override
    protected void initEvent() {
        backImageRight.setOnClickListener(this);
        messageImage.setOnClickListener(this);
    }
    public void PopUpDialog() {   //弹框
        ActivityNoticeDialog PopUpDialogs = new ActivityNoticeDialog(FhMemberBenefitsRightActivity.this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PopUpDialogs.show(fragmentTransaction, "ActivityNoticeDialog");
    }
    @Override
    protected void onResume() {
        super.onResume();
//        int count = new MessageDao().getUnReadCount();
//        messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
//        messageRedImage.setText(String.valueOf(count));
    }

    @Override
    protected void initData() {
//        int count = new MessageDao().getUnReadCount();
//        messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
//        messageRedImage.setText(String.valueOf(count));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_benefit_rights;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.messageImage:
                IntentTools.startChatList(this);
                break;
            case R.id.backImageRight:
                finish();
                break;
        }
    }
    public void onEventMainThread(IMEvent event) {
        try{
//            if (null != event) {
//                int count = new MessageDao().getUnReadCount();
//                messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
//                messageRedImage.setText(String.valueOf(count));
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onConnectStart() {
        super.onConnectStart();
    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }
}
