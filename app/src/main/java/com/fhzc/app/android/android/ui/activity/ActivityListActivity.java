package com.fhzc.app.android.android.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.ActivityListAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.controller.ActivityController;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.ActivityModel;
import com.fhzc.app.android.models.ActivityModels;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 活动列表
 * Created by yanbo on 2016/7/21.
 */
public class ActivityListActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {
    @Bind(R.id.activityListToolBar)
    CommonToolBar activityListToolBar;
    @Bind(R.id.mScrollView)
    ScrollViewExtend mScrollView;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.activityList)
    NoScrollListView activityList;
    private ActivityListAdapter adapter;
    private EmptyLayout mEmptyLayout;
    SwipeRefreshLayout swipeLayout;
    int page = 1;
    boolean canLoad = true;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        mEmptyLayout = new EmptyLayout(this, mScrollView);
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
                page = 1;
                canLoad = true;
                ActivityController.getInstance().activityList(ActivityListActivity.this,page);
            }
        });
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);

    }

    @Override
    protected void initEvent() {
        activityListToolBar.setClickRightListener(this);
        mScrollView.setCallbacks(new ScrollViewExtend.ObservableScrollViewCallbacks() {
            @Override
            public void onScrollChanged(int scrollY) {

            }

            @Override
            public void onScrollBottom() {
                if (!canLoad)
                    return;
                canLoad = false;
                swipeLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        loadActivity();
                        swipeLayout.setRefreshing(true);

                    }
                });

            }
        });
    }

    public void loadActivity(){

        ActivityController.getInstance().activityList(new ViewNetCallBack() {
            @Override
            public void onConnectStart() {

            }

            @Override
            public void onConnectEnd() {
                swipeLayout.setRefreshing(false);
            }

            @Override
            public void onFail(Exception e) {
                mEmptyLayout.showError();
                canLoad = true;
            }

            @Override
            public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
                ActivityModels models = (ActivityModels) result;
                swipeLayout.setRefreshing(false);
                mEmptyLayout.showSuccess(models.getItems().size() <= 0);
                if(page==1){
                    adapter.setRes(models.getItems());
                }else{
                    for(ActivityModel mo:models.getItems()){
                        adapter.append(mo);
                    }
                }
                if (models.getItems().size() >= 25) {
                    canLoad = true;
                    page++;
                }
            }
        },page);
    }
    public void onEventMainThread(IMEvent event) {
        if (null != event) {
            activityListToolBar.setRedImage(new MessageDao().getUnReadCount());

        }
    }

    @Override
    protected void initData() {
        activityListToolBar.setRedImage(new MessageDao().getUnReadCount());
        adapter = new ActivityListAdapter(this);
        activityList.setAdapter(adapter);
        ActivityController.getInstance().activityList(this,page);
        activityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentTools.startActivityDetail(ActivityListActivity.this, adapter.getItem(i).getId());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_activity_list;
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
        mEmptyLayout.showLoading();
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        ActivityModels models = (ActivityModels) result;
        swipeLayout.setRefreshing(false);
        mEmptyLayout.showSuccess(models.getItems().size() <= 0);
        adapter.setRes(models.getItems());
    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
        mEmptyLayout.showError();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(this);
    }

}
