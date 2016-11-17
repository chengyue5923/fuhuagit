package com.fhzc.app.android.android.ui.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.fhzc.app.android.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fw on 16/10/17.
 */

public class NewsAutoScrollBanner extends ImagePagerView {

    public TextView tvBannerText;
    public List<String> strings = new ArrayList<>();

    public NewsAutoScrollBanner(Context context) {
        super(context);
    }

    public NewsAutoScrollBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewsAutoScrollBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void initUI(Context context) {
        super.initUI(context);
        tvBannerText = (TextView) findViewById(R.id.tv_summary);
    }

    @Override
    public synchronized void onPageSelected(int pos) {
        super.onPageSelected(pos);
        findViewById(R.id.ll_summary).setVisibility(View.VISIBLE);
        tvBannerText.setText(strings.get(pos));

    }

    @Override
    public void setLayoutId() {
        this.layoutId = R.layout.view_newsautoscrollbanner;
    }
}
