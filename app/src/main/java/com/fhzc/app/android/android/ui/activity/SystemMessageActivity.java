package com.fhzc.app.android.android.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.SystemMessageListAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.controller.SystemController;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.SystemNoticeModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 服务公告
 * Created by yanbo on 2016/7/21.
 */
public class SystemMessageActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {
    @Bind(R.id.systemMessageToolBar)
    CommonToolBar systemMessageToolBar;
    @Bind(R.id.systemMessageList)
    ListView systemMessageList;
    @Bind(R.id.showLayout)
    RelativeLayout showLayout;
    private SystemMessageListAdapter adapter;
    EmptyLayout emptyLayout;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        emptyLayout = new EmptyLayout(this,systemMessageList);
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
        systemMessageToolBar.setClickRightListener(this);
    }

    @Override
    protected void initData() {
        systemMessageToolBar.setRedImage(new MessageDao().getUnReadCount());
        adapter = new SystemMessageListAdapter(this);
        systemMessageList.setAdapter(adapter);

        SystemController.getInstance().systemNotice(this);
    }

    public void onEventMainThread(IMEvent event) {
        if (null != event) {
            systemMessageToolBar.setRedImage(new MessageDao().getUnReadCount());

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_message;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        emptyLayout.showSuccess(false);
        swipeLayout.setRefreshing(false);
        List<SystemNoticeModel> list = (List<SystemNoticeModel>) result;
        if(list.size()==0){
            showLayout.setVisibility(View.VISIBLE);
            systemMessageList.setVisibility(View.GONE);
        }else{
            showLayout.setVisibility(View.GONE);
            systemMessageList.setVisibility(View.VISIBLE);
            adapter.setRes(list);
        }
    }

    @Override
    public void onClick(View v) {

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
    public void OnClickRight(View view) {
        IntentTools.startChatList(this);
    }
}
