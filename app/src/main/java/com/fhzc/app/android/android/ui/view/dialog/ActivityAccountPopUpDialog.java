package com.fhzc.app.android.android.ui.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.widget.CountDownTimeButton;
import com.fhzc.app.android.android.ui.view.widget.PhoneTextIdentify;
import com.fhzc.app.android.controller.LoginController;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * 活动理财师弹框
 * Created by lenovo on 2016/7/12.
 */
public class ActivityAccountPopUpDialog extends DialogFragment implements View.OnClickListener{

    private ImageView delteforotherImage;
    private EditText nameForOtherEt,phoneForOtherEt,numberForOtherEt,codeForOtherEt;
    private CountDownTimeButton countPassText;
    private TextView sureforOtherButton;
    public ActivityAccountPopUpDialog() {
    }

    private Context context;
    @SuppressLint("ValidFragment")
    public ActivityAccountPopUpDialog(Context context) {
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        WindowManager.LayoutParams localLayoutParams = getDialog().getWindow().getAttributes();
        localLayoutParams.gravity = Gravity.BOTTOM;
        localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity(), R.style.MyDialogStyleBottom);
        View view = View.inflate(getActivity(), R.layout.activity_account_popup_window, null);
        dialog.setContentView(view);
        delteforotherImage=(ImageView)view.findViewById(R.id.delteforotherImage);
        nameForOtherEt=(EditText)view.findViewById(R.id.nameForOtherEt);
        phoneForOtherEt=(EditText)view.findViewById(R.id.phoneForOtherEt);
        numberForOtherEt=(EditText)view.findViewById(R.id.numberForOtherEt);
        codeForOtherEt=(EditText)view.findViewById(R.id.codeForOtherEt);
        countPassText=(CountDownTimeButton)view.findViewById(R.id.countPassText);
        sureforOtherButton=(TextView)view.findViewById(R.id.sureforOtherButton);
        delteforotherImage.setOnClickListener(this);
        sureforOtherButton.setOnClickListener(this);
        countPassText.setStartCountLisener(new CountDownTimeButton.StartCountLisener() {
            @Override
            public boolean startCount() {
                String code = nameForOtherEt.getText().toString();
                String mobile = phoneForOtherEt.getText().toString();
                String mobilenumber = numberForOtherEt.getText().toString();

                if (StringTools.isNullOrEmpty(code)) {
                    ToastTool.show("请输入姓名");
                    return false;
                }
                if (StringTools.isNullOrEmpty(mobile)) {
                    ToastTool.show("请输入手机号");
                    return false;
                }
                if(!PhoneTextIdentify.isMobileNO(mobile)){
                    ToastTool.show("手机号码格式不正确");
                    return false;
                }
                if (StringTools.isNullOrEmpty(mobilenumber)) {
                    ToastTool.show("请输入人数");
                    return false;
                }
                LoginController.getInstance().getSmsWithoutCheck(new ViewNetCallBack() {
                    @Override
                    public void onConnectStart() {

                    }

                    @Override
                    public void onConnectEnd() {

                    }

                    @Override
                    public void onFail(Exception e) {

                    }

                    @Override
                    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
                        try {
                            JSONObject object = new JSONObject(o.toString());
                            if (object.getInt("code") != 200) {
                                ToastTool.show(object.getString("message"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, mobile);
                return true;
            }

        });
        countPassText.setCycle(60);
        return dialog;
    }
    private DialogPersonListener dialogListener;
    public void setDialogListener(DialogPersonListener dialogListener) {
        this.dialogListener = dialogListener;
    }


    public interface DialogPersonListener{
        void onclickConfirm(String name,String phone,String number, String code);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.delteforotherImage:
//                countPassText.stopCount();
//                countPassText.setCanRun();
                dismiss();
                break;
            case R.id.sureforOtherButton:
//                countPassText.stopCount();

                dialogListener.onclickConfirm(nameForOtherEt.getText().toString(), phoneForOtherEt.getText().toString(),numberForOtherEt.getText().toString(),codeForOtherEt.getText().toString());
                break;
        }

    }

}
