package com.fhzc.app.android.android.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.ReportListAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.ReportController;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.ReportModel;
import com.fhzc.app.android.models.ReportModels;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/7/20.
 */
public class ReportListActivity extends BaseActivity implements View.OnClickListener,CommonToolBar.ClickRightListener {
    @Bind(R.id.reportListToolBar)
    CommonToolBar reportListToolBar;
    @Bind(R.id.reportList)
    ListView reportList;
    private ReportListAdapter adapter;
    private EmptyLayout emptyLayout;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        emptyLayout = new EmptyLayout(this, swipeLayout);
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
        reportList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentTools.startReportDetail(ReportListActivity.this, adapter.getItem(i).getId());
            }
        });
        reportListToolBar.setClickRightListener(this);

    }

    public void onEventMainThread(IMEvent event) {
        if (null != event) {
            reportListToolBar.setRedImage(new MessageDao().getUnReadCount());
        }
    }

    @Override
    protected void initData() {
        reportListToolBar.setRedImage(new MessageDao().getUnReadCount());
        adapter = new ReportListAdapter(this);
        reportList.setAdapter(adapter);
        ReportController.getInstance().reportList(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_report_list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
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
        List<ReportModel> list = ((ReportModels) result).getItems();
        swipeLayout.setRefreshing(false);
        emptyLayout.showSuccess(list.size() <= 0);
        adapter.setRes(list);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(this);
    }

}
