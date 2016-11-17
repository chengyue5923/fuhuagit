package com.fhzc.app.android.android.ui.activity.systemservice;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.base.FuhuaApplication;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.controller.LoginController;
import com.fhzc.app.android.utils.android.IntentTools;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 系统服务 忘记密码again
 * Created by lenovo on 2016/7/11.
 */
public class FhForgetPasswordAgainActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.activityDetailToolbar)
    CommonToolBar activityDetailToolbar;
    @Bind(R.id.passwordText)
    EditText passwordText;
    @Bind(R.id.identifyCodeText)
    TextView identifyCodeText;
    @Bind(R.id.passwordAgainText)
    EditText passwordAgainText;
    @Bind(R.id.forgetPassNext)
    TextView forgetPassNext;

    String mobile="";
    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        forgetPassNext.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if(getIntent().getStringExtra("mobile")!=null){
            mobile=getIntent().getStringExtra("mobile");
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fh_forget_password_again_activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgetPassNext:
                doResetPsw();
                break;

        }
    }


    private void doResetPsw() {
        String password = passwordText.getText().toString();
        String confirmPsw = passwordAgainText.getText().toString();
        if (StringTools.isNullOrEmpty(password)) {
            ToastTool.show("密码不能为空");
            return;
        }
        if (!StringTools.isEqual(password, confirmPsw)) {
            ToastTool.show("两次密码输入不一致");
            return;
        }
        if(!password.matches("^[\\@A-Za-z0-9\\!\\#\\$\\%\\^\\&\\*\\.\\~]{6,22}$")){
            ToastTool.show("密码不符合规则");
            return;
        }
        LoginController.getInstance().forgetPsw(this,mobile, password, confirmPsw);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        try {
            JSONObject object = new JSONObject(((String) o));
            if (object.getInt("code") == 200) {
                ToastTool.show("密码重置成功");
                FuhuaApplication.getInstance().clearActivities();
                IntentTools.startLogin(this);
            } else {
                ToastTool.show(object.getString("message"));
            }
        } catch (Exception e) {
            ToastTool.show("密码重置失败");
        }
    }

}
