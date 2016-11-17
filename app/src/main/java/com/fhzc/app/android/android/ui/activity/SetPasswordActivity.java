package com.fhzc.app.android.android.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.framwork.cach.preferce.PreferceManager;
import com.base.framwork.net.configer.Constans;
import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.LoginController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.LoginModel;
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
public class SetPasswordActivity extends BaseActivity implements View.OnClickListener {


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
    private int identity;
    private String identityNum;
    private String phoneNum;
    private String cPassword;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        activityDetailToolbar.setTitle("设置密码");
    }

    @Override
    protected void initEvent() {
        forgetPassNext.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        identity = getIntent().getIntExtra("identity", -1);
        identityNum = getIntent().getStringExtra("identityNum");
        phoneNum = getIntent().getStringExtra("phoneNum");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fh_forget_password_again_activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgetPassNext:
                doSetPsw();
                break;

        }
    }

    private void doSetPsw() {
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

        cPassword = password;
        LoginController.getInstance().setPsw(this, identity, identityNum, password, confirmPsw);
    }

    private void doLogin() {
        UserPreference.setLoginName(phoneNum);
        PreferceManager.getInsance().saveValueBYkey(Constant.LOGIN_TIME, "");
        PreferceManager.getInsance().saveValueBYkey(Constans.jseesion, "");
        LoginController.getInstance().loginOldUser(this, phoneNum, cPassword);
//        LoginController.getInstance().loginOldUser(this, phoneNum, cPassword);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.setPsw.getType()) {
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    ToastTool.show("密码设置成功");
                    UserPreference.saveLastLogin(phoneNum);
                    doLogin();
                } else {
                    ToastTool.show(object.getString("message"));
                }
            } catch (Exception e) {

            }

        }
        if (flag == HttpConfig.loginOldUser.getType()) {
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
//                    ToastTool.show("登录成功");
//                    Logger.e("ew"+result);
//                    FuhuaApplication.getInstance().clearActivities();
//                    LoginModel model = (LoginModel) result;
//                    Logger.e("moodels"+model);
//                    UserPreference.setLoginUser(model);
//                    IntentTools.startMain(SetPasswordActivity.this,false);
//                    finish();
                    UserPreference.saveLastLogin(identityNum);
                    LoginModel model = (LoginModel) result;
                    UserPreference.setLoginUser(model);
                    PreferceManager.getInsance().saveValueBYkey(Constant.LOGIN_TIME, Constant.LOGIN_TIME);
                    IntentTools.startLoginActivity(SetPasswordActivity.this);
                    finish();
                } else {
                    ToastTool.show(object.getString("message"));
                }

            } catch (Exception e) {
                ToastTool.show("登录失败");
            }

        }
    }


}
