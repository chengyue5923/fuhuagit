package com.fhzc.app.android.android.ui.activity.personinformation;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.controller.LoginController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.utils.im.CommonUtil;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 更改密码again
 * Created by lenovo on 2016/7/11.
 */
public class FhChangePasswordAgainActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.activityDetailToolbar)
    CommonToolBar activityDetailToolbar;
    @Bind(R.id.passwordText)
    EditText passwordText;
    @Bind(R.id.identifyCodeText)
    TextView identifyCodeText;
    @Bind(R.id.passwordAgainText)
    EditText passwordAgainText;
    @Bind(R.id.changePassNext)
    TextView changePassNext;
    String oldPassword;

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
        oldPassword = getIntent().getStringExtra("password");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_password_again;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.changePassNext:
                doSetPsw();
                break;
        }
    }

    private void doSetPsw() {
        String password = passwordText.getText().toString();
        String comfirm = passwordAgainText.getText().toString();
        if (CommonUtil.isPassword(password) && CommonUtil.isPassword(comfirm) && CommonUtil.isPasswordEqul(password, comfirm)) {
            LoginController.getInstance().resetPsw(this, password, comfirm);
        }

    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        try {
            JSONObject object = new JSONObject(((String) o));
            if (object.getInt("code") == 200) {
                ToastTool.show("密码修改成功");
                logout();
            } else {
                ToastTool.show(object.getString("message"));
            }
        } catch (Exception e) {
            ToastTool.show("密码修改失败");
        }
    }

}
