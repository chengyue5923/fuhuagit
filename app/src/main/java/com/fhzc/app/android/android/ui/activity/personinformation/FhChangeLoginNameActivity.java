package com.fhzc.app.android.android.ui.activity.personinformation;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.LoginController;
import com.fhzc.app.android.models.LoginExistModel;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 修改登录名
 * Created by lenovo on 2016/7/11.
 */
public class FhChangeLoginNameActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.personDetailToolbar)
    CommonToolBar personDetailToolbar;
    @Bind(R.id.exitLoginTopline)
    View exitLoginTopline;
    @Bind(R.id.loginnameTextView)
    EditText loginnameTextView;
    @Bind(R.id.loginText)
    TextView loginText;
    @Bind(R.id.exitLoginBottomline)
    View exitLoginBottomline;
    @Bind(R.id.loginRedText)
    TextView loginRedText;
    @Bind(R.id.confirmButton)
    TextView confirmButton;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        loginText.setOnClickListener(this);
        confirmButton.setOnClickListener(this);

        loginnameTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginRedText.setVisibility(View.GONE);
                ShowLine(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {
        String login = getIntent().getStringExtra("name");
        loginnameTextView.setText(login);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_name;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.loginText:
                doVerification();
                break;
            case R.id.confirmButton:
//                doModity();
                doVerification();
                break;
        }
    }

    private void doVerification() {
        String login = loginnameTextView.getText().toString();
        if (StringTools.isNullOrEmpty(login)) {
            ToastTool.show("登录名不能为空");
            return;
        }
        LoginController.getInstance().loginExist(this, login);
    }

    private void doModity() {
        String login = loginnameTextView.getText().toString();
        if (StringTools.isNullOrEmpty(login)) {
            ToastTool.show("登录名不能为空");
            return;
        }
        LoginController.getInstance().modLogin(this, login);
    }

    public void ShowLine(Boolean type) {
        if (type) {
            exitLoginTopline.setVisibility(View.VISIBLE);
            exitLoginBottomline.setVisibility(View.VISIBLE);
            exitLoginBottomline.setVisibility(View.VISIBLE);
        } else {
            exitLoginTopline.setVisibility(View.GONE);
            exitLoginBottomline.setVisibility(View.GONE);
            exitLoginBottomline.setVisibility(View.GONE);
        }

    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.loginExist.getType()) {
            LoginExistModel model = (LoginExistModel) result;
            ShowLine(model.isExist());
            if (model.isExist()) {
                loginRedText.setText("用户名已存在");
                loginRedText.setTextColor(Color.RED);
                loginRedText.setVisibility(View.VISIBLE);
            } else {
                loginRedText.setText("可以使用");
                loginRedText.setTextColor(Color.GREEN);
                loginRedText.setVisibility(View.VISIBLE);
                doModity();
            }
        }
        if (flag == HttpConfig.modLogin.getType()) {
            try {
                JSONObject object = new JSONObject((String) o);
                if (object.getInt("code") == 200) {
                    ToastTool.show("修改成功");
                    finish();
                } else {
                    ToastTool.show(object.getString("message"));
                }
            } catch (Exception e) {
                ToastTool.show("修改失败");
            }

        }
    }

}
