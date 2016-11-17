package com.fhzc.app.android.android.ui.activity.systemservice;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.ReservedProductListAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.controller.ProductController;
import com.fhzc.app.android.models.ProductModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我预约的产品
 * Created by lenovo on 2016/7/11.
 */
public class CustomerReservedProductActivity extends BaseActivity implements View.OnClickListener {


    EmptyLayout emptyLayout;
    ReservedProductListAdapter historyAdapter;
    @Bind(R.id.rightDetailToolbar)
    CommonToolBar rightDetailToolbar;
    @Bind(R.id.reservationListView)
    ListView reservationListView;
    private int uid = -1;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        emptyLayout = new EmptyLayout(this, reservationListView);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
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
        reservationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    IntentTools.startTourismProduceDetail(CustomerReservedProductActivity.this, historyAdapter.getItem(i).getPid());



                } catch (Exception e) {
                }
            }
        });
    }

    @Override
    protected void initData() {
        uid = getIntent().getIntExtra("uid", -1);
        if(getIntent().getStringExtra("name")!=null){
            rightDetailToolbar.setTitle("客户预约的产品");
        }else{
            rightDetailToolbar.setTitle("我预约的产品");
        }
        historyAdapter = new ReservedProductListAdapter(this);
        reservationListView.setAdapter(historyAdapter);
        ProductController.getInstance().myOrderProduct(this,uid);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reservation_product_financial;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Override
    public void onConnectStart() {
        super.onConnectStart();
        emptyLayout.showLoading();
    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
        emptyLayout.showEmpty();
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        List<ProductModel> list = (List<ProductModel>) result;
        swipeLayout.setRefreshing(false);
        emptyLayout.showSuccess(list.size() <= 0);
        historyAdapter.setRes(list);
    }

}
