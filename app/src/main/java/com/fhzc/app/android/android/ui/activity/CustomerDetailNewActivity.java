package com.fhzc.app.android.android.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.fragment.CustomerInfoFragment;
import com.fhzc.app.android.android.ui.fragment.CustomerOrderFragment;
import com.fhzc.app.android.android.ui.fragment.CustomerWealthFragment;
import com.fhzc.app.android.android.ui.view.adapter.MyViewPagerAdapter;
import com.fhzc.app.android.android.ui.view.widget.CircleImageView;
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
public class CustomerDetailNewActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {
    @Bind(R.id.person_info_avatar)
    CircleImageView personInfoAvatar;
    @Bind(R.id.customerNameText)
    TextView customerNameText;
    @Bind(R.id.CustomerPointText)
    TextView CustomerPointText;
    @Bind(R.id.cudtomerMoneyText)
    TextView cudtomerMoneyText;
    @Bind(R.id.noticelayout)
    TextView noticelayout;
    @Bind(R.id.notivityImage)
    ImageView notivityImage;
    @Bind(R.id.noticeTextView)
    TextView noticeTextView;
    @Bind(R.id.EditNoticeText)
    TextView EditNoticeText;
    @Bind(R.id.rel)
    RelativeLayout rel;
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;
    MemberModel model;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        model = (MemberModel)getIntent().getSerializableExtra("model");
        if (null==model)
            return;
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(CustomerWealthFragment.newInstance(model), "客户财富");
        viewPagerAdapter.addFragment(CustomerOrderFragment.newInstance(model), "客户预约");
        viewPagerAdapter.addFragment( CustomerInfoFragment.newInstance(model), "客户资料");
        viewpager.setAdapter(viewPagerAdapter);//设置适配器

        tabLayout.addTab(tabLayout.newTab().setText("客户财富"));
        tabLayout.addTab(tabLayout.newTab().setText("客户预约"));
        tabLayout.addTab(tabLayout.newTab().setText("客户资料"));
        tabLayout.setupWithViewPager(viewpager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_detail_new;
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
