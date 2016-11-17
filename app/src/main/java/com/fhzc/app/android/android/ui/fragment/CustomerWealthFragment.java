package com.fhzc.app.android.android.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.MyFragmentAdapter;
import com.fhzc.app.android.android.ui.view.adapter.MyWealthPieChartAdapter;
import com.fhzc.app.android.android.ui.view.adapter.ProductCycleAdapter;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.configer.enums.CustomerType;
import com.fhzc.app.android.configer.enums.CustomerTypeNew;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.ProductController;
import com.fhzc.app.android.models.MemberModel;
import com.fhzc.app.android.models.MyProductModel;
import com.fhzc.app.android.models.SuggestAssetsModel;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/7/21.
 */
public class CustomerWealthFragment extends Fragment implements View.OnClickListener, MyWealthPieChartAdapter.OnClickItem, ViewNetCallBack {


    ProductCycleAdapter productCycleAdapter;
    MyWealthPieChartAdapter mAdapter;
    @Bind(R.id.customerAvatar)
    ImageView customerAvatar;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.pieChartLv)
    NoScrollListView pieChartLv;
    @Bind(R.id.productCycleLv)
    NoScrollListView productCycleLv;
    @Bind(R.id.customerName)
    TextView customerName;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.customerScore)
    TextView customerScore;
    @Bind(R.id.customerLevel)
    ImageView customerLevel;
//    @Bind(R.id.historyListView)
//    ListView historyListView;
//    @Bind(R.id.historyLayout)
//    RelativeLayout historyLayout;
    @Bind(R.id.historyProductLay)
    LinearLayout historyProductLay;
    @Bind(R.id.arrivalMessageLayout)
    LinearLayout arrivalMessageLayout;
    private MyFragmentAdapter fragmentAdapter;
    private MemberModel model;
//    HistoryFinancialCustomerAdapter historyAdapter;


    public static CustomerWealthFragment newInstance(MemberModel model) {
        CustomerWealthFragment customerWealthFragment = new CustomerWealthFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("model", model);
        customerWealthFragment.setArguments(bundle);
        return customerWealthFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_wealth, container, false);
        ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initData() {
        Bundle bundle = getArguments();
        model = (MemberModel) bundle.getSerializable("model");
        if (model == null)
            return;

        fragmentAdapter = new MyFragmentAdapter(getActivity());
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
        productCycleAdapter = new ProductCycleAdapter(getActivity());
        productCycleLv.setAdapter(productCycleAdapter);
        //文字
        mAdapter = new MyWealthPieChartAdapter(getActivity(), pieChartLv);
        mAdapter.setOnClickItem(this);
        pieChartLv.setAdapter(mAdapter);

        ImageLoader.getInstance(getActivity(), R.drawable.im_default_user_portrait_corner).displayImage(model.getAvatar(), customerAvatar);
        customerLevel.setImageResource(CustomerTypeNew.getCustomerByType(model.getLevel()).getClientListBg());
        customerName.setText(model.getRealname());
        customerScore.setText("客户积分：  " + model.getScore());
        //历史投资
//        historyAdapter = new HistoryFinancialCustomerAdapter(getActivity());
//        historyListView.setAdapter(historyAdapter);
//        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                IntentTools.startProduceDynamic(getActivity(), historyAdapter.getItem(i), false);
//            }
//        });
//        historyLayout.setOnClickListener(this);
        historyProductLay.setOnClickListener(this);

        arrivalMessageLayout.setOnClickListener(this);
        ProductController.getInstance().myProduct(this, model.getCustomerId());
        ProductController.getInstance().suggestAssets(this);
    }

    private void initView() {
        productCycleLv.setFocusable(false);
        pieChartLv.setFocusable(false);
//        historyListView.setFocusable(false);
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
                IntentTools.starthistoryActivity(getActivity(), model.getCustomerId());
                break;
            case R.id.arrivalMessageLayout:
                IntentTools.startAccountInformationActivity(getActivity(), model.getCustomerId());
                break;
        }
    }

    @Override
    public void click(MyProductModel model) {
        try{
            IntentTools.startProduceDynamic(getActivity(), model, false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectStart() {

    }

    @Override
    public void onConnectEnd() {

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
            HashMap<String, MyProductModel> wrongMap = new HashMap<>();
//        for (MyProductModel model : list) {
//            if (!StringTools.isNullOrEmpty(model.getCid())) {
//                if (model.getAssetType().equals("redemption")) {
//                    wealth -= model.getAmount();
//                    wrongMap.put(model.getCid(), model);
//                } else {
//                    wealth += model.getAmount();
//                }
//            }
//        }
//        HashMap<String, List<MyProductModel>> finalMap = new HashMap<>();
//        for (MyProductModel model : list) {
//            if (!StringTools.isNullOrEmpty(model.getCid())) {
//                if (!wrongMap.containsKey(model.getCid())) {
//                    List<MyProductModel> list1 = finalMap.get(model.getCid());
//                    if (list1 == null) {
//                        list1 = new ArrayList<>();
//                    }
//                    list1.add(model);
//                    finalMap.put(model.getCid(), list1);
//                }
//            }
//        }
//        for (String key : finalMap.keySet()) {
//            List<MyProductModel> list1 = finalMap.get(key);
//            int amount = 0;
//            MyProductModel model = new MyProductModel();
//            for (MyProductModel model1 : list1) {
//                amount += model1.getAmount();
//                if (model1.getAssetType().equals("purchase"))
//                    model = model1;
//            }
//            model.setAmount(amount);
//            resultList.add(model);
//        }
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
                    if(!model1.getAssetType().equals("dividend")) {
                        amount += model1.getAmount();
                        if (model1.getAssetType().equals("purchase"))
                            model = model1;
                    }
                }
                model.setAmount(amount);
                resultList.add(model);
            }
            mAdapter.setAdapterData(resultList);
            productCycleAdapter.setRes(resultList);
            fragmentAdapter.setMyProductData(resultList, wealth);
//        historyAdapter.setRes(resultList);
        }
        if (flag == HttpConfig.suggestAssets.getType()) {
            List<SuggestAssetsModel> list = (List<SuggestAssetsModel>) result;
            fragmentAdapter.setSuggestData(list);
        }
    }
}
