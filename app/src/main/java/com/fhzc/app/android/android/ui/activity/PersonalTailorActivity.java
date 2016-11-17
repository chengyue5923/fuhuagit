package com.fhzc.app.android.android.ui.activity;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
public class PersonalTailorActivity extends BaseActivity implements ScrollViewExtend.ObservableScrollViewCallbacks, View.OnClickListener {
    @Bind(R.id.backimages)
    RelativeLayout backimages;
    @Bind(R.id.productTailorlayout)
    ImageView productTailorlayout;
    @Bind(R.id.healthTailorLayout)
    ImageView healthTailorLayout;
    @Bind(R.id.travelTailorlayout)
    ImageView travelTailorlayout;
    @Bind(R.id.homeTailorLayout)
    ImageView homeTailorLayout;
    @Bind(R.id.personal_title_text)
    ImageView personalTitleText;
    @Bind(R.id.personalTailorText)
    TextView personalTailorText;
    @Bind(R.id.Toolbar)
    RelativeLayout Toolbar;
    @Bind(R.id.scrollLayout)
    ScrollViewExtend scrollLayout;
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
        imageLayout.setOnClickListener(this);
        backimages.setOnClickListener(this);
        productTailorlayout.setOnClickListener(this);
        healthTailorLayout.setOnClickListener(this);
        travelTailorlayout.setOnClickListener(this);
        homeTailorLayout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Toolbar.setVisibility(View.GONE);
        scrollLayout.setCallbacks(this);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.top_image_height);
        SpannableStringBuilder builder = new SpannableStringBuilder(personalTailorText.getText().toString());
        ForegroundColorSpan redSpan = new ForegroundColorSpan(getResources().getColor(R.color.personaltailor));
        builder.setSpan(redSpan, 6, 18, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        personalTailorText.setText(builder);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal_tailor;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.productTailorlayout:
                IntentTools.startWebView(this, UrlRes.getInstance().getUrl() + "/h5/product_made.html ", "产品定制");
                break;
            case R.id.healthTailorLayout:
                IntentTools.startWebView(this, UrlRes.getInstance().getUrl() + "/h5/health_made.html ", "健康定制");
                break;
            case R.id.travelTailorlayout:
                IntentTools.startWebView(this, UrlRes.getInstance().getUrl() + "/h5/personal-make.html ", "旅行定制");
                break;
            case R.id.homeTailorLayout:
                IntentTools.startWebView(this, UrlRes.getInstance().getUrl() + "/h5/family_inherit.html ", "家族定制");
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
