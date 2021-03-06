package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.ReportListByTypeAdapter;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/7/21.
 */
public class ReportNewListFinancialDetailFragment extends Fragment implements ViewNetCallBack {

     public static ReportNewListFinancialDetailFragment newInstance(String cid) {
        Bundle args = new Bundle();
         args.putString("Financialcid",cid);
        ReportNewListFinancialDetailFragment fragment = new ReportNewListFinancialDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    String cid;
    ReportListByTypeAdapter adapter;
    @Bind(R.id.myActivityList)
    NoScrollListView myActivityList;
    private Map<String, String> paramsValue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_new_activity, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        cid = (String) bundle.getSerializable("Financialcid");
//        ReportController.getInstance().reportTypeListByTypeDetail(ReportNewListFinancialDetailFragment.this, cid);
        adapter=new ReportListByTypeAdapter(getActivity());
        myActivityList.setAdapter(adapter);
        paramsValue=new HashMap<String, String>();
        paramsValue.put("cid", cid);
        return view;
    }

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
//        adapter.setRes((List<ReportModel>)result);
    }
}
