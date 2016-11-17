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
 *
 * 活动详情    取消报名
 * Created by lenovo on 2016/7/12.
 */
public class ActivitySignUpCancelDialog extends DialogFragment implements View.OnClickListener {


    private TextView cancelNotText,cancelText;

    private ImageView activityBacksIv;
    private TextView cancelNotTextView,cancelTextView,signUpSureTextView;

    private DialogListener dialogListener;

    public void setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
    public ActivitySignUpCancelDialog(){

    }
    public interface DialogListener{
        void onclickConfirm();
    }

    @SuppressLint("ValidFragment")
    public ActivitySignUpCancelDialog(Context context){
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
        View view = View.inflate(getActivity(), R.layout.activity_signup_cancel_window, null);
        dialog.setContentView(view);
        cancelNotTextView=(TextView)view.findViewById(R.id.cancelNotTextView);
        cancelTextView=(TextView)view.findViewById(R.id.cancelTextView);
        cancelNotTextView.setOnClickListener(this);
        cancelTextView.setOnClickListener(this);
        return dialog;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.cancelNotTextView:
                dismiss();
                break;
            case R.id.cancelTextView:
                dialogListener.onclickConfirm();
                break;
        }
    }
}
