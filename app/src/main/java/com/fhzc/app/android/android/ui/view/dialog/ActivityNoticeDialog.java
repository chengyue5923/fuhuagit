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

import butterknife.ButterKnife;

/**
 * 活动详情报名弹框
 * Created by lenovo on 2016/7/12.
 */
@SuppressLint("ValidFragment")
public class ActivityNoticeDialog extends DialogFragment implements View.OnClickListener {


    private ImageView delteImage;
    private TextView sureButton;
    private Context context;
    @SuppressLint("ValidFragment")
    public ActivityNoticeDialog(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        WindowManager.LayoutParams localLayoutParams = getDialog().getWindow().getAttributes();
        localLayoutParams.gravity = Gravity.CENTER;
        localLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity(), R.style.MyDialogStyleBottom);
        View view = View.inflate(getActivity(), R.layout.activity_notice_window, null);
        dialog.setContentView(view);
        delteImage=(ImageView)view.findViewById(R.id.delteImage);
        sureButton=(TextView)view.findViewById(R.id.sureButton);
        delteImage.setOnClickListener(this);
        sureButton.setOnClickListener(this);
        return dialog;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delteImage:
                dismiss();
                break;
            case R.id.sureButton:
                dismiss();
                break;
        }

    }

}
