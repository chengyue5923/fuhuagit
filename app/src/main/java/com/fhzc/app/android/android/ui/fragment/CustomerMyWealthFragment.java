package com.fhzc.app.android.android.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.MyFragmentAdapter;
import com.fhzc.app.android.android.ui.view.adapter.MyWealthPieChartAdapter;
import com.fhzc.app.android.android.ui.view.adapter.ProductCycleNewAdapter;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.ProductController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.MyProductModel;
import com.fhzc.app.android.models.SuggestAssetsModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/7/21.
 */
public class CustomerMyWealthFragment extends Fragment implements View.OnClickListener, ViewNetCallBack, MyWealthPieChartAdapter.OnClickItem {


    @Bind(R.id.wealthNum)
    TextView wealthNum;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.pieChartLv)
    NoScrollListView pieChartLv;
    MyWealthPieChartAdapter mAdapter;
    @Bind(R.id.productCycleLv)
    NoScrollListView productCycleLv;
    @Bind(R.id.historyProductLay)
    LinearLayout historyProductLay;
    @Bind(R.id.myProductLay)
    LinearLayout myProductLay;
    @Bind(R.id.myFinancialPlannerLay)
    LinearLayout myFinancialPlannerLay;
    @Bind(R.id.myAttentionLay)
    LinearLayout myAttentionLay;
    ProductCycleNewAdapter productCycleAdapter;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
//    @Bind(R.id.myRefresh)
//    SwipeRefreshLayout myRefresh;
    private MyFragmentAdapter fragmentAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_wealth, container, false);
        ButterKnife.bind(this, view);
        initView();
        initEvent();
        initData();
        return view;
    }

    private void initView() {
        productCycleLv.setFocusable(false);
        pieChartLv.setFocusable(false);

        fragmentAdapter = new MyFragmentAdapter(getActivity());
//        myRefresh.setColorSchemeResources(R.color.appColorBlue);
        //饼状图
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                title.setText(position == 0 ? "资产配置比例" : "资产推荐比例");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageMargin(-240);
        //波浪进度
        productCycleAdapter = new ProductCycleNewAdapter(getActivity());
        productCycleLv.setAdapter(productCycleAdapter);
        //文字
        mAdapter = new MyWealthPieChartAdapter(getActivity(), pieChartLv);
        mAdapter.setOnClickItem(this);
        pieChartLv.setAdapter(mAdapter);
    }

    private void initData() {
        try{
            ProductController.getInstance().suggestAssets(this);
            ProductController.getInstance().myProduct(this, UserPreference.getRoleId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initEvent() {
//        myRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                ProductController.getInstance().myProduct(CustomerMyWealthFragment.this, UserPreference.getRoleId());
//            }
//        });
        historyProductLay.setOnClickListener(this);
        myProductLay.setOnClickListener(this);
        myFinancialPlannerLay.setOnClickListener(this);
        myAttentionLay.setOnClickListener(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.historyProductLay:
                IntentTools.starthistoryActivity(getActivity());
                break;
            case R.id.myProductLay:
                IntentTools.startServationActivity(getActivity(), UserPreference.getRoleId());
                break;
            case R.id.myFinancialPlannerLay:
                IntentTools.startAccountionDetailActivity(getActivity(), true);
                break;
            case R.id.myAttentionLay:
                IntentTools.startCollectionActivity(getActivity(), "我的关注");
                break;
        }
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {
//        myRefresh.setRefreshing(false);
    }

    @Override
    public void onFail(Exception e) {

    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.myAssets.getType()) {
            List<MyProductModel> list = (List<MyProductModel>) result;
            int wealth = 0;
            List<MyProductModel> resultList = new ArrayList<>();
            resultList.clear();
            HashMap<String, MyProductModel> wrongMap = new HashMap<>();
//            for (MyProductModel model : list) {
//                if (!StringTools.isNullOrEmpty(model.getCid())) {
//                    if (model.getAssetType().equals("redemption")) {
//                        wealth -= model.getAmount();
//                        wrongMap.put(model.getCid(), model);
//                    } else {
//                        wealth += model.getAmount();
//                    }
//                }
//            }
//            HashMap<String, List<MyProductModel>> finalMap = new HashMap<>();
//            for (MyProductModel model : list) {
//                if (!StringTools.isNullOrEmpty(model.getCid())) {
//                    if (!wrongMap.containsKey(model.getCid())) {
//                        List<MyProductModel> list1 = finalMap.get(model.getCid());
//                        if (list1 == null) {
//                            list1 = new ArrayList<>();
//                        }
//                        list1.add(model);
//                        finalMap.put(model.getCid(), list1);
//                    }
//                }
//            }
//            for (String key : finalMap.keySet()) {
//                List<MyProductModel> list1 = finalMap.get(key);
//                int amount = 0;
//                MyProductModel model = new MyProductModel();
//                for (MyProductModel model1 : list1) {
//                    amount += model1.getAmount();
//                    if (model1.getAssetType().equals("purchase"))
//                        model = model1;
//                }
//                model.setAmount(amount);
//                resultList.add(model);
//            }

            for (MyProductModel model : list) {
                if (!StringTools.isNullOrEmpty(model.getSerial())) {
                    if (model.getAssetType().equals("redemption")) {
                        wealth -= model.getAmount();
                        wrongMap.put(model.getSerial(), model);
                    } else if(model.getAssetType().equals("dividend")) {
//                        wealth += model.getAmount();
                    }else{
                        wealth += model.getAmount();
                    }
                }
            }
            HashMap<String, List<MyProductModel>> finalMap = new HashMap<>();
            for (MyProductModel model : list) {
                if (!StringTools.isNullOrEmpty(model.getSerial())) {
                    if (!wrongMap.containsKey(model.getSerial())) {
                        List<MyProductModel> list1 = finalMap.get(model.getSerial());
                        if (list1 == null) {
                            list1 = new ArrayList<>();
                        }
                        list1.add(model);
                        finalMap.put(model.getSerial(), list1);
                    }
                }
            }
            for (String key : finalMap.keySet()) {
                List<MyProductModel> list1 = finalMap.get(key);
                int amount = 0;
                MyProductModel model = new MyProductModel();
                for (MyProductModel model1 : list1) {
                    if(!model1.getAssetType().equals("dividend")){
                        amount += model1.getAmount();
                        if (model1.getAssetType().equals("purchase"))
                            model = model1;
                    }
                }
                model.setAmount(amount);
                resultList.add(model);
            }
            double youIn = Double.parseDouble(String.valueOf(wealth));
            double l = 10000;
            DecimalFormat df = new DecimalFormat("0.00");
            wealthNum.setText(df.format(youIn/l) + "万元");
            mAdapter.setAdapterData(resultList);
            productCycleAdapter.setRes(resultList);
            fragmentAdapter.setMyProductData(resultList, wealth);

        }
        if (flag == HttpConfig.suggestAssets.getType()) {
            List<SuggestAssetsModel> list = (List<SuggestAssetsModel>) result;
            fragmentAdapter.setSuggestData(list);
        }
    }

    @Override
    public void click(MyProductModel model) {
        IntentTools.startProduceDynamic(getActivity(), model, false);
    }
}
