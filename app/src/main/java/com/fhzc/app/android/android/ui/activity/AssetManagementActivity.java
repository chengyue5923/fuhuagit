package com.fhzc.app.android.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.configer.UrlRes;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.CommonUtil;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/10/25.
 * 私人订制
 */
public class AssetManagementActivity extends BaseActivity implements ScrollViewExtend.ObservableScrollViewCallbacks, View.OnClickListener {

    @Bind(R.id.backimages)
    RelativeLayout backimages;
    @Bind(R.id.asset_list)
    ImageView assetList;
    @Bind(R.id.asset_tictacs)
    ImageView assetTictacs;
    @Bind(R.id.personal_title_text)
    ImageView personalTitleText;
    @Bind(R.id.personalTailorText)
    TextView personalTailorText;
    @Bind(R.id.Toolbar)
    RelativeLayout Toolbar;
    @Bind(R.id.ScrollViewExtendlayout)
    ScrollViewExtend ScrollViewExtendlayout;
    @Bind(R.id.imageLayout)
    ImageView imageLayout;
    private int mParallaxImageHeight;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        CommonUtil.FlymeSetStatusBarLightMode(getWindow(), false);
        CommonUtil.MIUISetStatusBarLightMode(getWindow(), false);
    }

    @Override
    protected void initEvent() {
        backimages.setOnClickListener(this);
        assetList.setOnClickListener(this);
        assetTictacs.setOnClickListener(this);
        imageLayout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Toolbar.setVisibility(View.GONE);
        ScrollViewExtendlayout.setCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.top_image_height);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_asset_management_tailor;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.asset_list:
                IntentTools.startWebView(this, UrlRes.getInstance().getUrl() + "/h5/product_sort.html ", "产品分类",true);
                break;
            case R.id.asset_tictacs:
                IntentTools.startWebView(this, UrlRes.getInstance().getUrl() + "/h5/investment_strategy.html ", "投资策略");
                break;
            case R.id.imageLayout:
            case R.id.backimages:
                finish();
                break;
        }
    }

    @Override
    public void onScrollChanged(int scrollY) {
        float alpha = 1 - (float) Math.max(0, mParallaxImageHeight - scrollY) / mParallaxImageHeight;
        if (alpha > 0.2) {
            Toolbar.setVisibility(View.VISIBLE);
            backimages.setVisibility(View.GONE);
        } else {
            Toolbar.setVisibility(View.GONE);
            backimages.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onScrollBottom() {

    }
}

