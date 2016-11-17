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
import android.widget.TextView;

import com.fhzc.app.android.R;

/**
 *
 * 权益取消预约弹框
 * Created by lenovo on 2016/7/12.
 */
public class RightsCancelPopUpDialog extends DialogFragment implements View.OnClickListener{

    private TextView rightnocancelTextView,rightcancelTextView;


    Context context;
    public RightsCancelPopUpDialog(){

    }
    @SuppressLint("ValidFragment")
    public RightsCancelPopUpDialog(Context context){
        this.context=context;
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
    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity(), R.style.MyDialogStyleBottom);
        View view = View.inflate(getActivity(), R.layout.activity_right_cancelwindow, null);
        rightnocancelTextView=(TextView)view.findViewById(R.id.rightnocancelTextView);
        rightcancelTextView=(TextView)view.findViewById(R.id.rightcancelTextView);
        rightcancelTextView.setOnClickListener(this);
        rightnocancelTextView.setOnClickListener(this);
        dialog.setContentView(view);
        return dialog;
    }

    private DialogListener dialogListener;
    public void setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }


    public interface DialogListener{
        void onclickConfirm();
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){

            case R.id.rightnocancelTextView:
                dismiss();
                break;
            case R.id.rightcancelTextView:
                dialogListener.onclickConfirm();
                break;
        }

    }
}
