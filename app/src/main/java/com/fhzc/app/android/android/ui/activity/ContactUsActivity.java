package com.fhzc.app.android.android.ui.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.models.ContactUsModel;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/8/3.
 */
public class ContactUsActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.systemMessageToolBar)
    CommonToolBar systemMessageToolBar;
    @Bind(R.id.phontcontact)
    WebView phontcontact;


    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void initData() {
        UserController.getInstance().aboutUs(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_contact_us;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        ContactUsModel model=(ContactUsModel)result;
//        phontcontact.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
//        phontcontact.setText(Html.fromHtml(model.getIntroduction()));
        phontcontact.loadDataWithBaseURL(null, model.getIntroduction(), "text/html", "utf-8", null);
        phontcontact.getSettings().setJavaScriptEnabled(true);
        phontcontact.setWebChromeClient(new WebChromeClient());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.phontcontact:

                break;
        }
    }
}
