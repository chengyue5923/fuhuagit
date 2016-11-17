package com.fhzc.app.android.android.ui.activity;

import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import com.fhzc.app.android.BuildConfig;
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
public class AboutActivity extends BaseActivity {

    @Bind(R.id.systemMessageToolBar)
    CommonToolBar systemMessageToolBar;
    @Bind(R.id.imagesView)
    ImageView imagesView;
    @Bind(R.id.fuhua)
    TextView fuhua;
    @Bind(R.id.introText)
    TextView introText;
    @Bind(R.id.bottomtext)
    TextView bottomtext;
    @Bind(R.id.tv_version)
    TextView tvVersion;

    @Override
    protected void initView() {

        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        UserController.getInstance().aboutApp(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        ContactUsModel model = (ContactUsModel) result;
        if (model != null) {
//        introText.setText(model.getIntroduction());
            introText.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
            introText.setText(Html.fromHtml(model.getIntroduction()));
        }
        tvVersion.setText(BuildConfig.VERSION_NAME);
    }

}
