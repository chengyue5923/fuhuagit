package com.fhzc.app.android.android.ui.activity.systemservice;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.CountDownTimeButton;
import com.fhzc.app.android.controller.LoginController;
import com.fhzc.app.android.utils.android.IntentTools;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 系统服务 忘记密码
 * Created by lenovo on 2016/7/11.
 */
public class FhForgetPasswordActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.activityDetailToolbar)
    CommonToolBar activityDetailToolbar;
    @Bind(R.id.passwordText)
    EditText passwordText;
    @Bind(R.id.identifyCodeText)
    TextView identifyCodeText;
    @Bind(R.id.sentCodeText)
    CountDownTimeButton sentCodeText;
    @Bind(R.id.passwordAgainText)
    EditText passwordAgainText;
    @Bind(R.id.forgetPassNext)
    TextView forgetPassNext;


    int identificode;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        forgetPassNext.setOnClickListener(this);
        sentCodeText.setStartCountLisener(new CountDownTimeButton.StartCountLisener() {
            @Override
            public boolean startCount() {
                String mobile = passwordText.getText().toString();
                if (StringTools.isNullOrEmpty(mobile)) {
                    ToastTool.show(R.string.login_phone_null);
                    return false;
                }
                if(!mobile.matches("^1[3|4|5|7|8]\\d{9}$")){
//                    sentCodeText.setBackgroundColor(getResources().getColor(R.color.selected_line_color));
//                   SmsController.getInstance().sendIdentifyCode(UserRegisterActivity.this, accountEt.getText().toString());
//                    return true;
//                }else{
                    ToastTool.show("请输入正确的手机号码");
                    return false;
                }
                LoginController.getInstance().getSms(FhForgetPasswordActivity.this, mobile);
                return true;
            }
        });
        sentCodeText.setCycle(60);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fh_forget_password_activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.forgetPassNext:
                if(StringTools.isNullOrEmpty(passwordAgainText.getText().toString())){
                    ToastTool.show("验证码不能为空?");
                    return ;
                }
                if(identificode==Integer.parseInt(passwordAgainText.getText().toString())){
                    IntentTools.startForgetPassAgain(this,passwordText.getText().toString());
                }else{
                    ToastTool.show("您输入的验证码不正确");
                }
                break;

        }
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        try {
            JSONObject object = new JSONObject(o.toString());
            if (object.getInt("code") == 200) {

                identificode=0;
                identificode =object.getInt("message");
            }else{
                ToastTool.show(object.getString("message"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
