package com.fhzc.app.android.android.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/8/9.
 */
public class FeedbackActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.systemMessageToolBar)
    CommonToolBar systemMessageToolBar;
    @Bind(R.id.bugLayout)
    TextView bugLayout;
    @Bind(R.id.productLayout)
    TextView productLayout;
    @Bind(R.id.rightLayout)
    TextView rightLayout;
    @Bind(R.id.reportLayout)
    TextView reportLayout;
    @Bind(R.id.activityLayout)
    TextView activityLayout;
    @Bind(R.id.myContentLayout)
    TextView myContentLayout;
    @Bind(R.id.chatLayout)
    TextView chatLayout;
    @Bind(R.id.otherLayout)
    TextView otherLayout;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        bugLayout.setOnClickListener(this);
        rightLayout.setOnClickListener(this);
        productLayout.setOnClickListener(this);
        reportLayout.setOnClickListener(this);
        activityLayout.setOnClickListener(this);
        myContentLayout.setOnClickListener(this);
        chatLayout.setOnClickListener(this);
        otherLayout.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }


    @Override
    public void onClick(View v) {


        IntentTools.startFeedBackDetail(FeedbackActivity.this, ((TextView) v).getText().toString());


    }


}
