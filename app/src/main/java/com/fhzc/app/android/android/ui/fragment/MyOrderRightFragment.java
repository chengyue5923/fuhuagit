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
import com.fhzc.app.android.android.ui.view.adapter.OrderRightsListAdapter;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.configer.enums.MyOrderRightType;
import com.fhzc.app.android.controller.RightController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.OrderRightModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/7/25.
 */
public class MyOrderRightFragment extends Fragment {
    @Bind(R.id.myOrderRightList)
    ListView myOrderRightList;
    OrderRightsListAdapter myOrderRightAdapter;

    EmptyLayout emptyLayout;
    int status;
    List<OrderRightModel> list = new ArrayList<>();
    private boolean flag;
    SwipeRefreshLayout swipeLayout;
    public void setRightModels(HashMap<Integer, List<OrderRightModel>> map) {
        list = map.get(status);
        if (myOrderRightAdapter != null) {
            emptyLayout.showSuccess(list.size() <= 0);
            myOrderRightAdapter.setRes(list);
        }
    }

    public static MyOrderRightFragment newInstance(int status,boolean flags) {
        MyOrderRightFragment squareFragmentV2 = new MyOrderRightFragment();
        Bundle bundle = new Bundle();

        bundle.putInt("status", status);
        bundle.putBoolean("flag", flags);
        squareFragmentV2.setArguments(bundle);
        return squareFragmentV2;
    }
    public void setStatus(int status){
        this.status = status;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_order_right, container, false);
        ButterKnife.bind(this, view);
        status = getArguments().getInt("status");
        flag=getArguments().getBoolean("flag");
        myOrderRightAdapter = new OrderRightsListAdapter(getActivity(),status);
        myOrderRightList.setAdapter(myOrderRightAdapter);
        myOrderRightList.setFocusable(false);
        emptyLayout = new EmptyLayout(getActivity(), myOrderRightList);
        myOrderRightList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(status==0||status==1){
                    IntentTools.startNewRightDetail(getActivity(), myOrderRightAdapter.getItem(position).getRights_id(),status,String.valueOf(1));
                }else{
                    IntentTools.startNewRightDetail(getActivity(), myOrderRightAdapter.getItem(position).getRights_id(),status,String.valueOf(0));

                }
            }
        });
        emptyLayout.showSuccess(list.size() <= 0);
        myOrderRightAdapter.setRes(list);
        swipeLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 2000);

//                initData();
                RightController.getInstance().myOrderRight(new ViewNetCallBack() {
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
                        List<OrderRightModel> list = (List<OrderRightModel>) result;
                        List<OrderRightModel> orderList = new ArrayList<>();
                        List<OrderRightModel> successList = new ArrayList<>();
                        List<OrderRightModel> finishList = new ArrayList<>();
                        for (OrderRightModel model : list) {
                            if (model.getStatus() == 0) {
                                orderList.add(model);
                            } else if (model.getStatus() == 1) {
                                successList.add(model);
                            } else {
                                finishList.add(model);
                            }
                        }
                        if(status== MyOrderRightType.ORDER_RIGHT_TYPE_ORDER.getType()){
                            myOrderRightAdapter.setRes(orderList);
                        }else if(status==MyOrderRightType.ORDER_RIGHT_TYPE_SUCCESS.getType()){
                            myOrderRightAdapter.setRes(successList);
                        }else{
                            myOrderRightAdapter.setRes(finishList);
                        }
                    }
                }, UserPreference.getRoleId());
            }
        });
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
