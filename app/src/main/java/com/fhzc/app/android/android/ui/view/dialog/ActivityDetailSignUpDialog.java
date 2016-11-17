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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.widget.CountDownTimeButton;
import com.fhzc.app.android.android.ui.view.widget.PhoneTextIdentify;
import com.fhzc.app.android.controller.LoginController;
import com.fhzc.app.android.models.ActivityModel;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by fw on 16/10/19.
 */

public class ActivityDetailSignUpDialog extends DialogFragment implements View.OnClickListener  {

    private ImageView reduceButton,plusButton,delteImage;
    private TextView shareButton,sureButton,nameTextViews,numberText;
    private TextView numberEditText;
    private View plusAddLayout,reducelayout;
    private Context context;

    private EditText nameForOtherEt,phoneForOtherEt,numberForOtherEt,codeForOtherEt;
    private CountDownTimeButton countPassText;
    private TextView sureforOtherButton,tvMe,tvOthers;
    private boolean isForOthers;
    private LinearLayout llMe,llOthers;



    private ActivityModel model;

    @SuppressLint("ValidFragment")
    public ActivityDetailSignUpDialog(Context context,ActivityModel model,boolean isForOthers) {
        this.context = context;
        this.model=model;
        this.isForOthers = isForOthers;
    }
    public ActivityDetailSignUpDialog() {

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
        View view = View.inflate(getActivity(), R.layout.dialog_activitydetailsignup, null);
        llMe = (LinearLayout)view.findViewById(R.id.ll_forme);
        llOthers = (LinearLayout)view.findViewById(R.id.ll_forother);
        if(isForOthers){
            llMe.setVisibility(View.GONE);
            llOthers.setVisibility(View.VISIBLE);
        }else{
            llOthers.setVisibility(View.GONE);
            llMe.setVisibility(View.VISIBLE);
        }
        dialog.setContentView(view);
        nameTextViews=(TextView)view.findViewById(R.id.nameTextViews);
        tvMe=(TextView)view.findViewById(R.id.tv_forme);
        tvOthers=(TextView)view.findViewById(R.id.tv_forothers);
        nameTextViews.setText(model.getName());
        numberText=(TextView)view.findViewById(R.id.numberText);
        numberText.setText(model.getUserReq());
        reduceButton=(ImageView)view.findViewById(R.id.reduceButton);
        plusButton=(ImageView)view.findViewById(R.id.plusButton);
        delteImage=(ImageView)view.findViewById(R.id.delteImage);
//        shareButton=(TextView)view.findViewById(R.id.shareButton);
        sureButton=(TextView)view.findViewById(R.id.sureButton);
        plusAddLayout=(View)view.findViewById(R.id.plusAddLayout);
        reducelayout=(View)view.findViewById(R.id.reducelayout);
        numberEditText=(TextView)view.findViewById(R.id.numberEditTexts);
        reduceButton.setOnClickListener(this);
        delteImage.setOnClickListener(this);
        plusAddLayout.setOnClickListener(this);
        reducelayout.setOnClickListener(this);
//        shareButton.setOnClickListener(this);
        sureButton.setOnClickListener(this);
        tvMe.setOnClickListener(this);
        tvOthers.setOnClickListener(this);

        nameForOtherEt=(EditText)view.findViewById(R.id.nameForOtherEt);
        phoneForOtherEt=(EditText)view.findViewById(R.id.phoneForOtherEt);
        numberForOtherEt=(EditText)view.findViewById(R.id.numberForOtherEt);
        codeForOtherEt=(EditText)view.findViewById(R.id.codeForOtherEt);
        countPassText=(CountDownTimeButton)view.findViewById(R.id.countPassText);
        sureforOtherButton=(TextView)view.findViewById(R.id.sureforOtherButton);
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
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reducelayout:
                ChangeNumber(false);
                break;
            case R.id.plusAddLayout:
                ChangeNumber(true);
                break;
            case R.id.delteImage:
                dismiss();
                break;
//            case R.id.shareButton:
//                if (dialogListener!=null){
//                    dialogListener.onclickOtner();
//                }
//                break;
            case R.id.sureButton:
                if (dialogListener!=null){
                    dialogListener.onclickConfirm(Integer.parseInt(numberEditText.getText().toString()));
                }
                break;
            case R.id.sureforOtherButton:
//                countPassText.stopCount();
                dialogListener.onclickConfirm(nameForOtherEt.getText().toString(), phoneForOtherEt.getText().toString(),numberForOtherEt.getText().toString(),codeForOtherEt.getText().toString());
                break;
            case R.id.tv_forme:
                llOthers.setVisibility(View.GONE);
                llMe.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_forothers:
                llMe.setVisibility(View.GONE);
                llOthers.setVisibility(View.VISIBLE);
                break;
        }

    }

    public interface DialogListener{
        void onclickConfirm(int personnumber);//自己
        void onclickConfirm(String name,String phone,String number, String code);//他人
        void onclickOtner();
    }

    private DialogListener dialogListener;

    public void setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }


    public void ChangeNumber(boolean type){
        int number=Integer.parseInt(numberEditText.getText().toString());

        if(type&&!numberEditText.getText().toString().equals("")){
            number++;
            numberEditText.setText(number+"");
        }else{
            if(number>1){
                number--;
                numberEditText.setText(number+"");
            }
        }
    }
}
