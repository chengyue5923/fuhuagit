package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.MyActivityListAdapter;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.controller.ActivityController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.ActivityStatusModel;
import com.fhzc.app.android.models.OrderActivityModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/7/21.
 */
public class CustomerMyActivityFragment extends Fragment implements AdapterView.OnItemClickListener, ViewNetCallBack {
    @Bind(R.id.myActivityList)
    ListView myActivityList;
    MyActivityListAdapter myActivityListAdapter;
    private List<Object> objects = new ArrayList<>();
    private EmptyLayout emptyLayout;
    SwipeRefreshLayout swipeLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_adapter_activity, container, false);
        ButterKnife.bind(this, view);
        myActivityListAdapter = new MyActivityListAdapter(getActivity());
        myActivityList.setAdapter(myActivityListAdapter);
        myActivityList.setFocusable(false);
        emptyLayout = new EmptyLayout(getActivity(), myActivityList);
        swipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 5000);

                initData();
            }
        });
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);
        return view;
    }

    private void initData() {
        objects.clear();
        ActivityController.getInstance().myOrderActivity(this, UserPreference.getRoleId());
        myActivityList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (objects.get(position) instanceof ActivityStatusModel)
            return;
        IntentTools.startActivityDetail(getActivity(), ((OrderActivityModel) objects.get(position)).getActivityId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        List<OrderActivityModel> list = (List<OrderActivityModel>) result;
        swipeLayout.setRefreshing(false);
        List<OrderActivityModel> tempList = new ArrayList<>();
        emptyLayout.showSuccess(list.size() <= 0);
        for (OrderActivityModel model : list) {
            if (model.getStatus() != 1&&model.getStatus()!=0) {
                tempList.add(model);
            } else {
                objects.add(model);
            }
        }
        if (tempList.size()>0){
            objects.add(new ActivityStatusModel());
            objects.addAll(tempList);
        }
        myActivityListAdapter.setRes(objects);
    }

    @Override
    public void onConnectStart() {
        emptyLayout.showLoading();
    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onFail(Exception e) {
        emptyLayout.showError();
    }
}
