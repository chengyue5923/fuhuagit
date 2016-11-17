package com.fhzc.app.android.android.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.MyActivityListAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
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
 * @deprecated
 * @param context
 */
public class CustomerOrderActivityActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.customerActivityToolBar)
    CommonToolBar customerActivityToolBar;
    @Bind(R.id.myActivityList)
    ListView myActivityList;
    MyActivityListAdapter myActivityListAdapter;
    private List<Object> objects = new ArrayList<>();
    private EmptyLayout emptyLayout;
    private int uid;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        myActivityListAdapter = new MyActivityListAdapter(this);
        myActivityList.setAdapter(myActivityListAdapter);
        emptyLayout = new EmptyLayout(this, myActivityList);
        swipeLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container);
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
    }

    @Override
    protected void initEvent() {
        myActivityList.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        uid = getIntent().getIntExtra("uid", -1);
        ActivityController.getInstance().myOrderActivity(this, uid == -1 ? UserPreference.getUid() : uid);

        if(getIntent().getStringExtra("name")!=null){
            customerActivityToolBar.setTitle("客户预约的活动");
        }else{
            customerActivityToolBar.setTitle("我预约的活动");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_customer_activity;
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

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        List<OrderActivityModel> list = (List<OrderActivityModel>) result;
        List<OrderActivityModel> tempList = new ArrayList<>();
        swipeLayout.setRefreshing(false);
        emptyLayout.showSuccess(list.size() <= 0);
        objects.clear();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (objects.get(position) instanceof ActivityStatusModel)
            return;
        IntentTools.startActivityDetail(CustomerOrderActivityActivity.this, ((OrderActivityModel) objects.get(position)).getActivityId());
    }
}
