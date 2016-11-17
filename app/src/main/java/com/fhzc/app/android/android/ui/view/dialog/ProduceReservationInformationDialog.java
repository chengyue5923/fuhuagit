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

import com.fhzc.app.android.R;

/**
 *
 * 产品详情中预约信息的dialog
 * Created by lenovo on 2016/7/12.
 */
public class ProduceReservationInformationDialog extends DialogFragment implements View.OnClickListener {


    private ImageView backImageView,activityBacksImageView;
    private EditText nameTextView,phoneNumberTextView,identifyCodeText;
    private TextView getIdentifyCodeTextView,signUpSureOtherTextView;


    public ProduceReservationInformationDialog(){

    }
    @SuppressLint("ValidFragment")
    public ProduceReservationInformationDialog(Context context){
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        WindowManager.LayoutParams localLayoutParams = getDialog().getWindow().getAttributes();
        localLayoutParams.gravity = Gravity.BOTTOM;
        localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new Dialog(getActivity(), R.style.MyDialogStyleBottom);
        View view = View.inflate(getActivity(), R.layout.produce_reservation_information_window, null);
        dialog.setContentView(view);
        return dialog;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

        }

    }
}
