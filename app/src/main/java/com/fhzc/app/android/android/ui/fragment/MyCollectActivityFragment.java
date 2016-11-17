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
import com.fhzc.app.android.android.ui.view.adapter.CollectActivityListAdapter;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.event.CancelCollectEvent;
import com.fhzc.app.android.models.CollectActivityModel;
import com.fhzc.app.android.models.CollectMapModel;
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
public class MyCollectActivityFragment extends Fragment implements AdapterView.OnItemClickListener, ViewNetCallBack {
    @Bind(R.id.myActivityList)
    ListView myActivityList;
    CollectActivityListAdapter adapter;
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
    @Bind(R.id.activityTextViwa)
    TextView activityTextViwa;
    private List<CollectActivityModel> list = new ArrayList<>();
    EmptyLayout emptyLayout;
    SwipeRefreshLayout swipeLayout;

    public void setList(List<CollectActivityModel> list) {
        this.list = list;
        if (adapter != null) {
            initData();
        }
//        emptyLayout.showSuccess(list.size() <= 0);
//        List<CollectActivityModel> liststarting=new ArrayList<>();
//        List<CollectActivityModel> listComplete=new ArrayList<>();
//        for(CollectActivityModel m:list){
//            if(m.getStatus()==4){
//                listComplete.add(m);
//            }else{
//                liststarting.add(m);
//            }
//        }
//        if(list.size()==liststarting.size()){
//            adapter.setRes(liststarting);
//        }else{
//            CollectActivityModel model=new CollectActivityModel();
//            model.setStatus(10000001);
//            liststarting.add(model);
//            for(CollectActivityModel mo:listComplete){
//                liststarting.add(mo);
//            }
//
//            adapter.setRes(liststarting);
//        }

//        adapter.setRes(list);
    }

    public List<CollectActivityModel> getList() {
        return list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_activity_activity, container, false);
        ButterKnife.bind(this, view);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        emptyLayout = new EmptyLayout(getActivity(), myActivityList);
        adapter = new CollectActivityListAdapter(getActivity());
        myActivityList.setAdapter(adapter);
        initData();
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_containerddd);
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);

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
        activityTextViwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentTools.startRightBenfitsNumbrtList(getActivity());
            }
        });
        return view;
    }

    public void intidatas() {
        UserController.getInstance().myCollection(MyCollectActivityFragment.this);
    }

    private void initData() {
        myActivityList.setOnItemClickListener(this);
        emptyLayout.showSuccess(list.size() <= 0);
        if (list.size() <= 0) {
            listProductView.setVisibility(View.GONE);
            EmptyProductView.setVisibility(View.VISIBLE);
        } else {
            listProductView.setVisibility(View.VISIBLE);
            EmptyProductView.setVisibility(View.GONE);
            List<CollectActivityModel> liststarting = new ArrayList<>();
            List<CollectActivityModel> listComplete = new ArrayList<>();
            for (CollectActivityModel m : list) {
                if (m.getStatus() == 4) {
                    listComplete.add(m);
                } else {
                    liststarting.add(m);
                }
            }
            if (list.size() == liststarting.size()) {
                adapter.setRes(liststarting);
            } else {
                CollectActivityModel model = new CollectActivityModel();
                model.setStatus(10000001);
                liststarting.add(model);
                for (CollectActivityModel mo : listComplete) {
                    liststarting.add(mo);
                }

                adapter.setRes(liststarting);
            }
        }
//        adapter.setRes(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        IntentTools.startActivityDetail(getActivity(), list.get(position).getId(), position);
    }

    public void onEventMainThread(CancelCollectEvent event) {
        if (null != event && event.type.equals(Constant.COLLECTTYPE_ACTIVITY)) {
            if (event.isCollect) {
                adapter.append((CollectActivityModel) event.serializable);
                list.add((CollectActivityModel) event.serializable);
            } else {
                adapter.removeByPosition(event.position);
                list.remove(event.position);
            }

            adapter.notifyDataSetChanged();
        }
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
            setList(model.getActivity());
        }
    }

}
