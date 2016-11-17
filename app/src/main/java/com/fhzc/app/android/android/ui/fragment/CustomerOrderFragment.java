package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fhzc.app.android.R;
import com.fhzc.app.android.models.MemberModel;
import com.fhzc.app.android.utils.android.IntentTools;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/8/1.
 */
public class CustomerOrderFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.customerOrderProduct)
    LinearLayout customerOrderProduct;
    @Bind(R.id.customerOrderRight)
    LinearLayout customerOrderRight;
    @Bind(R.id.customerOrderActivity)
    LinearLayout customerOrderActivity;
    private MemberModel model;

    public static CustomerOrderFragment newInstance(MemberModel model) {
        CustomerOrderFragment customerOrderFragment = new CustomerOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        customerOrderFragment.setArguments(bundle);
        return customerOrderFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_order, container, false);
        ButterKnife.bind(this, view);
        initData();
        initEvent();
        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        model = (MemberModel) bundle.getSerializable("model");

    }

    private void initEvent() {
        customerOrderProduct.setOnClickListener(this);
        customerOrderRight.setOnClickListener(this);
        customerOrderActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.customerOrderProduct:
                IntentTools.startServationActivity(getActivity(), model.getCustomerId(),model.getRealname());
                break;
            case R.id.customerOrderRight:
                IntentTools.startOrderRight(getActivity(), model.getCustomerId(),model.getRealname());
                break;
            case R.id.customerOrderActivity:
                IntentTools.startCustomerOrderActivity(getActivity(), model.getCustomerId(),model.getRealname());
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
