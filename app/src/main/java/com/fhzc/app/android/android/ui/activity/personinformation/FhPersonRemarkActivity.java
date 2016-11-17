package com.fhzc.app.android.android.ui.activity.personinformation;

import android.view.View;
import android.widget.EditText;

import com.base.platform.utils.android.ToastTool;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.models.MemberModel;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 备注
 * Created by lenovo on 2016/7/11.
 */
public class FhPersonRemarkActivity extends BaseActivity implements View.OnClickListener{


    MemberModel model;
    @Bind(R.id.personDetailremarkToolbar)
    CommonToolBar personDetailremarkToolbar;
    @Bind(R.id.remarkText)
    EditText remarkText;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        personDetailremarkToolbar.showFinish();
        personDetailremarkToolbar.setClickRightListener(new CommonToolBar.ClickRightListener() {
            @Override
            public void OnClickRight(View view) {
                if(view.getId()==R.id.finishTextView){
                    if(remarkText.getText().equals("")){
                        ToastTool.show("请输入备注");
                    }else{
                        UserController.getInstance().changeCustomerRemark(FhPersonRemarkActivity.this,model.getCustomerId(),remarkText.getText().toString());
                    }
                }
            }
        });
    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void initData() {
        model = (MemberModel) getIntent().getSerializableExtra("model");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_remark;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if(flag== HttpConfig.changeCustomserRemark.getType()){
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    ToastTool.show("备注成功");
                    finish();
                } else {
                    ToastTool.show(object.getString("message"));
                }

            } catch (Exception e) {
                ToastTool.show("备注失败");
            }
        }
    }


}