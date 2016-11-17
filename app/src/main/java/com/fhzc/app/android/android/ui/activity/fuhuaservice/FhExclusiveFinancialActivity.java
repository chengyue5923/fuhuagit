package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.ListUtil;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.ReservedProductListAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.ProductController;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.ProductModel;
import com.fhzc.app.android.models.ProductModels;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @deprecated
 * @param context
 */
public class FhExclusiveFinancialActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {


    ReservedProductListAdapter financialAdapter;
    EmptyLayout emptyLayout;
    @Bind(R.id.rightDetailToolbar)
    CommonToolBar rightDetailToolbar;
    SwipeRefreshLayout swipeLayout;
    int page = 1;
    boolean canLoad = true;
    @Bind(R.id.scrollViews)
    ScrollViewExtend scrollViews;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.financialListView)
    NoScrollListView financialListView;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        emptyLayout = new EmptyLayout(this, scrollViews);
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
                setData();
            }
        });
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);
    }

    @Override
    protected void initEvent() {
        financialListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentTools.startProduceDetail(FhExclusiveFinancialActivity.this, financialAdapter.getItem(i).getPid());
            }
        });
        rightDetailToolbar.setClickRightListener(this);
        scrollViews.setCallbacks(new ScrollViewExtend.ObservableScrollViewCallbacks() {
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

    public void loadActivity() {

        ProductController.getInstance().selectProduct(new ViewNetCallBack() {
            @Override
            public void onConnectStart() {

            }

            @Override
            public void onConnectEnd() {

            }

            @Override
            public void onFail(Exception e) {
                emptyLayout.showError();
                canLoad = true;
            }

            @Override
            public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
                swipeLayout.setRefreshing(false);
                try{
                    if (flag == HttpConfig.selectProduct.getType()) {

                        List<ProductModel> list = ((ProductModels) result).getItems();
                        financialAdapter.setRes(list);
                        if (ListUtil.isNullOrEmpty(list)) {
                            canLoad = false;
                            return;
                        }
                        if (page == 1) {
                            financialAdapter.setRes(list);
                        }
                        if (page > 1) {
                            for (ProductModel mdoel : list) {
                                financialAdapter.append(mdoel);
                            }
                        }
                        if (list.size() >= 25) {
                            canLoad = true;
                            page++;
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, page);
    }

    public void onEventMainThread(IMEvent event) {
        if (null != event) {
            rightDetailToolbar.setRedImage(new MessageDao().getUnReadCount());

        }
    }

    @Override
    protected void initData() {
        rightDetailToolbar.setRedImage(new MessageDao().getUnReadCount());
        financialAdapter = new ReservedProductListAdapter(this);
        financialListView.setAdapter(financialAdapter);

        setData();  //测试布局页面
    }

    public void setData() {
        ProductController.getInstance().selectProduct(this, page);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_financial_management;
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
        List<ProductModel> list = ((ProductModels) result).getItems();
        swipeLayout.setRefreshing(false);
        emptyLayout.showSuccess(list.size() <= 0);
        financialAdapter.setRes(list);
    }

    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }

}
