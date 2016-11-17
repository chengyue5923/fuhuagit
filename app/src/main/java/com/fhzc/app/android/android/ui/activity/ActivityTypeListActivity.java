package com.fhzc.app.android.android.ui.activity;

import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.ActivityTypeListAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.controller.ActivityController;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.models.ActivityModel;
import com.fhzc.app.android.models.ActivityModels;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fw on 16/10/18.
 */

public class ActivityTypeListActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {
    @Bind(R.id.activityListToolBar)
    CommonToolBar activityListToolBar;
    @Bind(R.id.mScrollView)
    ScrollViewExtend mScrollView;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.activityList)
    NoScrollListView activityList;
    private ActivityTypeListAdapter adapter;
    private EmptyLayout mEmptyLayout;
    SwipeRefreshLayout swipeLayout;
    int page = 1;
    boolean canLoad = true;
    private int cid;

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        mEmptyLayout = new EmptyLayout(this, mScrollView);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                canLoad = true;
                ActivityController.getInstance().activityTypeList(ActivityTypeListActivity.this,cid,page);
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

        ActivityController.getInstance().activityTypeList(new ViewNetCallBack() {
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
                    List<ActivityModel> list = setFirstFinish((ActivityModels) result);

                    adapter.setRes(list);
                }else{
                    List<ActivityModel> list = setFirstFinish((ActivityModels) result);
                    for(ActivityModel mo:list){
                        adapter.append(mo);
                    }
                }
                if (models.getItems().size() >= 25) {
                    canLoad = true;
                    page++;
                }
            }
        },cid,page);
    }

    @Override
    protected void initData() {
        cid = getIntent().getIntExtra("cid",1);
        activityListToolBar.setTitle(getIntent().getStringExtra("title"));
        adapter = new ActivityTypeListAdapter(this);
        activityList.setAdapter(adapter);
        ActivityController.getInstance().activityTypeList(this,cid,page);
        activityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentTools.startActivityDetail(ActivityTypeListActivity.this, adapter.getItem(i).getId());
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_activitytypelist;
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

    private boolean isFirst = true;
    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        swipeLayout.setRefreshing(false);
        mEmptyLayout.showSuccess(((ActivityModels) result).getItems().size() <= 0);

        List<ActivityModel> list = setFirstFinish((ActivityModels) result);

        adapter.setRes(list);
    }

    @NonNull
    private List<ActivityModel> setFirstFinish(ActivityModels result) {
        List<ActivityModel> list = result.getItems();

        for (int i = 0; i < list.size(); i++) {
            ActivityModel model = list.get(i);
            if (model.getStatus() == 1) {
                if (isFirst) {
                    isFirst = false;
                    adapter.setFirstFinish(i);
                }
            }
        }
        return list;
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