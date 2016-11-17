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
import com.fhzc.app.android.models.ReportModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/7/21.
 */
public class ReportNewListWeekDetailFragment extends Fragment implements ViewNetCallBack {

     public static ReportNewListWeekDetailFragment newInstance(String cid) {
        Bundle args = new Bundle();
         args.putString("cid",cid);
        ReportNewListWeekDetailFragment fragment = new ReportNewListWeekDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }
    String cid;
    ReportListByTypeAdapter adapter;
    @Bind(R.id.myActivityList)
    NoScrollListView myActivityList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_new_activity, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        cid = (String) bundle.getSerializable("cid");
//        ReportController.getInstance().reportTypeListByTypeDetail(ReportNewListWeekDetailFragment.this, cid);
        adapter=new ReportListByTypeAdapter(getActivity());
        myActivityList.setAdapter(adapter);
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
        adapter.setRes((List<ReportModel>)result);
    }
}
