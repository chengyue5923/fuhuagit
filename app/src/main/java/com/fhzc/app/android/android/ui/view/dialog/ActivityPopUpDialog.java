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
import com.fhzc.app.android.models.ActivityModel;

import butterknife.ButterKnife;

/**
 * 活动详情报名弹框
 * Created by lenovo on 2016/7/12.
 */
public class ActivityPopUpDialog extends DialogFragment implements View.OnClickListener {


    private ImageView reduceButton,plusButton,delteImage;
    private TextView shareButton,sureButton,nameTextViews,numberText;
    private TextView numberEditText;
    private View plusAddLayout,reducelayout;
    private Context context;
    private DialogListener dialogListener;

    public void setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }

    public ActivityPopUpDialog() {
    }
    ActivityModel model;
    @SuppressLint("ValidFragment")
    public ActivityPopUpDialog(Context context,ActivityModel model) {
        this.context = context;
        this.model=model;
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
        View view = View.inflate(getActivity(), R.layout.activity_buy_popup_window, null);
        dialog.setContentView(view);
        nameTextViews=(TextView)view.findViewById(R.id.nameTextViews);
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
        return dialog;
    }
    public interface DialogListener{
       void onclickConfirm(int personnumber);
       void onclickOtner();
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
        }

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
