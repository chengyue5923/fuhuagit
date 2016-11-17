package com.fhzc.app.android.android.ui.activity.personinformation;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.controller.LoginController;
import com.fhzc.app.android.utils.android.IntentTools;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 更改密码
 * Created by lenovo on 2016/7/11.
 */
public class FhChangePasswordActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.activityDetailToolbar)
    CommonToolBar activityDetailToolbar;
    @Bind(R.id.changeswordText)
    EditText changeswordText;
    @Bind(R.id.changePassNext)
    TextView changePassNext;
    String password;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        changePassNext.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.changePassNext:
                doVerifi();
                break;

        }
    }

    private void doVerifi() {
        password = changeswordText.getText().toString();
//        if (CommonUtil.isPassword(password))
            LoginController.getInstance().preModify(this, changeswordText.getText().toString());
    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
        ToastTool.show("验证失败");
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        try {
            JSONObject object = new JSONObject(((String) o));
            if (object.getInt("code") == 200) {
                IntentTools.startchangePasswordActivity(FhChangePasswordActivity.this,password);
            } else {
                ToastTool.show(object.getString("message"));
            }
        } catch (Exception e) {
            ToastTool.show("验证失败");
        }

    }

}
