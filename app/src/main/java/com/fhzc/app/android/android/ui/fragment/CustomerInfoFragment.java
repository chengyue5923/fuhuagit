package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.DateTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.models.CustomerModel;
import com.fhzc.app.android.models.MemberModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/8/1.
 */
public class CustomerInfoFragment extends Fragment implements ViewNetCallBack {


    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.level)
    TextView level;
    @Bind(R.id.riskLevel)
    TextView riskLevel;
    @Bind(R.id.certificatesType)
    TextView certificatesType;
    @Bind(R.id.certificatesNum)
    TextView certificatesNum;
    @Bind(R.id.certificatesAddress)
    TextView certificatesAddress;
    @Bind(R.id.birthday)
    TextView birthday;
    @Bind(R.id.gender)
    TextView gender;
    @Bind(R.id.mobilePhoneNum)
    TextView mobilePhoneNum;
    @Bind(R.id.email)
    TextView email;
    @Bind(R.id.phoneNum)
    TextView phoneNum;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.remark)
    TextView remark;
    @Bind(R.id.remarkTextView)
    RelativeLayout remarkTextView;
    @Bind(R.id.genderText)
    TextView genderText;
    private MemberModel model;

    public static CustomerInfoFragment newInstance(MemberModel model) {
        CustomerInfoFragment customerInfoFragment = new CustomerInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        customerInfoFragment.setArguments(bundle);
        return customerInfoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_info, container, false);
        ButterKnife.bind(this, view);
        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        remark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentTools.startRemarkActivity(getActivity(), model);
            }
        });
        remarkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentTools.startRemarkActivity(getActivity(),model);
            }
        });
    }

    private void initData() {
        Bundle bundle = getArguments();
        model = (MemberModel) bundle.getSerializable("model");
        if (model == null)
            return;
        UserController.getInstance().customerInfoDetail(this, model.getCustomerId());

    }

    private void setData(CustomerModel model) {
        name.setText(model.getName());
//        type.setText(model.getCustomer_level());
//        level.setText(model.getLevel());
//        riskLevel.setText(model.getRisk_level());
        type.setText(model.getType());
        level.setText(model.getLevel());
        riskLevel.setText(model.getRisk());
        remark.setText(model.getMemo());
        certificatesType.setText(model.getPassportType());
        certificatesNum.setText(model.getPassportCode());
        certificatesAddress.setText(model.getPassportAddress());
        mobilePhoneNum.setText(model.getMobile());
        email.setText(model.getEmail());
        phoneNum.setText(this.model.getPhone());
        address.setText(model.getAddress());
        genderText.setText(model.getGender());
        birthday.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getBirthday()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        setData((CustomerModel) result);
    }

    @Override
    public void onFail(Exception e) {

    }
}
