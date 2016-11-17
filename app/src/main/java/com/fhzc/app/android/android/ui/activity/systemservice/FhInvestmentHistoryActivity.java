package com.fhzc.app.android.android.ui.activity.systemservice;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.InvestmentHistoryAdapter;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.controller.ProductController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.MyProductModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的投资历史
 * Created by lenovo on 2016/7/11.
 */
public class FhInvestmentHistoryActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.historyListView)
    ListView historyListView;
    List<MyProductModel> models = new ArrayList<>();
    InvestmentHistoryAdapter historyAdapter;
    private EmptyLayout emptyLayout;
    SwipeRefreshLayout swipeLayout;
    @Override

    protected void initView() {
        ButterKnife.bind(this);
        emptyLayout = new EmptyLayout(this, historyListView);
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
        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentTools.startProduceDynamic(FhInvestmentHistoryActivity.this, models.get(i), true);
            }
        });
    }

    @Override
    protected void initData() {
        historyAdapter = new InvestmentHistoryAdapter(this);
        historyListView.setAdapter(historyAdapter);
        historyListView.setFocusable(false);
        if(getIntent().getSerializableExtra("customid")==null){
            ProductController.getInstance().myProduct(this, UserPreference.getRoleId());
        }else{
            ProductController.getInstance().myProduct(this, (int)getIntent().getSerializableExtra("customid"));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_financial;
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
        emptyLayout.showError();
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        List<MyProductModel> list = (List<MyProductModel>) result;
        models.clear();
        for (MyProductModel model : list) {
            if (model.getAssetType().equals("redemption")||model.getAssetType().equals("dividend")) {
                models.add(model);
            }
        }
        swipeLayout.setRefreshing(false);
        emptyLayout.showSuccess(models.size() <= 0);
        historyAdapter.setRes(models);
    }
}
