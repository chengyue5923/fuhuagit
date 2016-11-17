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
import android.widget.ImageView;
import android.widget.TextView;

import com.fhzc.app.android.R;

/**
 * Created by lenovo on 2016/7/12.
 */
public class ActivitySignUpSuccessDialog extends DialogFragment implements View.OnClickListener {


    private ImageView activityBacksIv;
    private TextView rightsTextView,demandTextView,signUpSureTextView,stateTextView;
    private String text;

    public ActivitySignUpSuccessDialog(){

    }
    @SuppressLint("ValidFragment")
    public ActivitySignUpSuccessDialog(Context context,String text){    // text 报名成功 取消报名
    this.text=text;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        WindowManager.LayoutParams localLayoutParams = getDialog().getWindow().getAttributes();
        localLayoutParams.gravity = Gravity.CENTER;
        localLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        localLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity(), R.style.MyDialogStyleBottom);
        View view = View.inflate(getActivity(), R.layout.activity_signup_success_window, null);
        dialog.setContentView(view);
        stateTextView=(TextView)dialog.findViewById(R.id.stateTextView);
        stateTextView.setText(text);
        return dialog;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
        }
    }
}
