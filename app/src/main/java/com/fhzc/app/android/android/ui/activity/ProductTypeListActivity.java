package com.fhzc.app.android.android.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.HotProductListAdapter;
import com.fhzc.app.android.android.ui.view.adapter.TourismProductAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.ProductController;
import com.fhzc.app.android.models.ProductModel;
import com.fhzc.app.android.models.ProductModels;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fw on 16/10/22.
 */

public class ProductTypeListActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, CommonToolBar.ClickRightListener {
    @Bind(R.id.toolBar)
    CommonToolBar toolBar;
    @Bind(R.id.hotProductLv)
    NoScrollListView hotProductLv;
    List<ProductModel> hotList;
    @Bind(R.id.mScrollView)
    ScrollViewExtend mScrollView;
    @Bind(R.id.iv_top)
    ImageView ivTop;
    @Bind(R.id.tv_top)
    TextView tvTop;
    @Bind(R.id.tv_readMore)
    TextView tvReadMore;


    private BasePlatAdapter hotAdapter;
    private EmptyLayout emptyLayout;
    SwipeRefreshLayout swipeLayout;
    private int cid, page = 1;
    private boolean unfold;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        emptyLayout = new EmptyLayout(this, mScrollView);
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
                isFirst = true;
                initData();
            }
        });
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);

    }

    @Override
    protected void initEvent() {
        hotProductLv.setOnItemClickListener(this);

    }

    @Override
    protected void initData() {
        cid = getIntent().getIntExtra("cid", 1);
        String title = "";
        String text = "";
        switch (cid) {
            case 3:
                text = "在全球化时代，具备全球化投资眼光已经成为财富管理的必备。复华通过和全球知名的机构合作，用全球化的视角提供包括不动产、移民和保险类的产品，满足中国投资人的全球化资产配置需求。";
                title = "海外投资";
                ivTop.setImageResource(R.drawable.ptl_hwtz);
                break;
            case 4:
                text = "金融市场产品由于其期限和收益方式的灵活性能够满足不同风险偏好和流动性需求的投资者需求。这让金融产品，特别是权益投资成为投资人的主要需求之一。复华通过和相关金融机构，特别是知名的证券基金类金融机构合作，发行此类产品，通过精心的产品架构设计能满足投资人的不同的风险和期限偏好。";
                title = "权益投资";
                ivTop.setImageResource(R.drawable.ptl_qytz);
                break;
            case 1:
                text = "除了以上三类常规的产品提供以外，复华还能满足投资人的另类投资需求。当前复华提供的另类投资产品包括满足现金管理类需求的母基金，风险投资（VC）和股权投资（PE包括新三板投资）等。";
                title = "另类投资";
                ivTop.setImageResource(R.drawable.ptl_lltz);
                break;
            case 2:
                text = "中国旅游形态较之国际水平相比发展迟缓，目前市场正从以观光为主转向观光与休闲度假相结合转变，市场发展空间巨大。2013年中国人均GDP为5414美元，根据国际经验，人均GDP超过5000美元，将进入旅游度假的快速增长期。旅游度假需求增长过程中，旅游地产也随着进入快速发展的阶段，相关金融产品的供给和需求均处于一个快速爆发的阶段。复华精心设计此类投资方向上的产品推荐给投资人，满足投资人资产配置的需求。";
                title = "旅游地产";
                ivTop.setImageResource(R.drawable.ptl_lydc);
                break;
        }
        tvTop.setText(text);
        tvReadMore.setOnClickListener(this);
        toolBar.setTitle(title);
        ProductController.getInstance().getProductTypeList(this, cid, page);
        if (cid == 2) {
            hotAdapter = new TourismProductAdapter(this);
        } else {
            hotAdapter = new HotProductListAdapter(this);
        }

        hotProductLv.setAdapter(hotAdapter);
        hotProductLv.setFocusable(false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_producttypelist;
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

    private boolean isFirst = true;

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        swipeLayout.setRefreshing(false);
        if (flag == HttpConfig.getProductTypeList.getType()) {
            List<ProductModel> list = ((ProductModels) result).getItems();
            hotList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                ProductModel model = list.get(i);
                if (model.getStatus() > 2) {
                    if (isFirst) {
                        isFirst = false;
                        if (hotAdapter instanceof HotProductListAdapter) {
                            ((HotProductListAdapter) hotAdapter).setFirstFinish(i);
                        } else {
                            ((TourismProductAdapter)hotAdapter).setFirstFinish(i);
                        }
                    }
                }
                hotList.add(model);
            }
            emptyLayout.showSuccess(list.size() <= 0);
            hotAdapter.setRes(hotList);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_readMore:
                if (unfold) {
                    tvTop.setMaxLines(3);
                    unfold = false;
                } else {
                    tvTop.setMaxLines(100);
                    unfold = true;
                }
                break;
        }
//        IntentTools.startServationActivity(ProductTypeListActivity.this, UserPreference.getRoleId());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (cid == 2) {
            IntentTools.startTourismProduceDetail(ProductTypeListActivity.this,hotList.get(position).getPid());
//            IntentTools.startProductDetail(ProductTypeListActivity.this, hotList.get(position).getPid());
        } else {
            IntentTools.startProductDetail(ProductTypeListActivity.this, hotList.get(position).getPid());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(this);
    }
}
