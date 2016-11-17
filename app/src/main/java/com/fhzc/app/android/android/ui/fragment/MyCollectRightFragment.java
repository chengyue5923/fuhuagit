package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.RightsListAdapter;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.event.CancelCollectEvent;
import com.fhzc.app.android.models.CollectMapModel;
import com.fhzc.app.android.models.RightModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/7/28.
 */
public class MyCollectRightFragment extends Fragment implements AdapterView.OnItemClickListener, ViewNetCallBack {
    @Bind(R.id.myActivityList)
    ListView myActivityList;
    RightsListAdapter adapter;
    @Bind(R.id.listProductView)
    ScrollView listProductView;
    @Bind(R.id.product_empty_view)
    ImageView productEmptyView;
    @Bind(R.id.peoduct_empty_text)
    TextView peoductEmptyText;
    @Bind(R.id.EmptyProductView)
    RelativeLayout EmptyProductView;
    @Bind(R.id.swipe_containerddd)
    SwipeRefreshLayout swipeContainerddd;
    @Bind(R.id.rightAvtivityView)
    TextView rightAvtivityView;
    private List<RightModel> rightModels = new ArrayList<>();
    EmptyLayout emptyLayout;
    SwipeRefreshLayout swipeLayout;

    public void setList(List<RightModel> list) {
        this.rightModels = list;
        if (adapter != null) {
            emptyLayout.showSuccess(rightModels.size() <= 0);
            adapter.setRes(list);
            if (rightModels.size() <= 0) {
                listProductView.setVisibility(View.GONE);
                EmptyProductView.setVisibility(View.VISIBLE);
            } else {
                listProductView.setVisibility(View.VISIBLE);
                EmptyProductView.setVisibility(View.GONE);
                adapter.setRes(rightModels);
            }
        }
    }

    public List<RightModel> getRightModels() {
        return rightModels;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_right_activity, container, false);
        ButterKnife.bind(this, view);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_containerddd);
        emptyLayout = new EmptyLayout(getActivity(), myActivityList);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 2000);
                intidatas();
            }
        });
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);

        adapter = new RightsListAdapter(getActivity(), true);
        myActivityList.setAdapter(adapter);
        initData();
        rightAvtivityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentTools.startRightBenfitsNumbrtList(getActivity());
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void intidatas() {
        UserController.getInstance().myCollection(MyCollectRightFragment.this);
    }

    private void initData() {
        myActivityList.setOnItemClickListener(this);
        emptyLayout.showSuccess(rightModels.size() <= 0);
        if (rightModels.size() <= 0) {
            listProductView.setVisibility(View.GONE);
            EmptyProductView.setVisibility(View.VISIBLE);
        } else {
            listProductView.setVisibility(View.VISIBLE);
            EmptyProductView.setVisibility(View.GONE);
            adapter.setRes(rightModels);
        }
    }

    public void onEventMainThread(CancelCollectEvent event) {
        if (null != event && event.type.equals(Constant.COLLECTTYPE_RIGHT)) {
            if (event.isCollect) {
                adapter.append((RightModel) event.serializable);
                rightModels.add((RightModel) event.serializable);
            } else {
                adapter.removeByPosition(event.position);
                rightModels.remove(event.position);
            }
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        IntentTools.startNewRightDetail(getActivity(), rightModels.get(position).getId(), position, 0 + "");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
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
        swipeLayout.setRefreshing(false);
        {
            CollectMapModel model = (CollectMapModel) result;
            setList(model.getRights());
        }
    }

}
