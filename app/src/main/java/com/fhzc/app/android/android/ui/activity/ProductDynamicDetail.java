package com.fhzc.app.android.android.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.FinancialRedementAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.ProductController;
import com.fhzc.app.android.models.DividendRemendModel;
import com.fhzc.app.android.models.MyProductModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 产品动态
 * Created by yanbo on 2016/7/25.
 */
public class ProductDynamicDetail extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.productDynamicTitle)
    CommonToolBar productDynamicTitle;
    @Bind(R.id.AnnualIncomeTv)
    TextView AnnualIncomeTv;
    @Bind(R.id.interestNumber)
    TextView interestNumber;
    @Bind(R.id.timeText)
    TextView timeText;
    @Bind(R.id.startPriceText)
    TextView startPriceText;
    @Bind(R.id.openDayLayout)
    LinearLayout openDayLayout;
    @Bind(R.id.productName)
    TextView productName;
    @Bind(R.id.productStatus)
    TextView productStatus;
    @Bind(R.id.foundDay)
    TextView foundDay;
    @Bind(R.id.dividendDayLayout)
    LinearLayout dividendDayLayout;
    @Bind(R.id.redeemDayLayout)
    LinearLayout redeemDayLayout;
    @Bind(R.id.deadDate)
    TextView deadDate;
    @Bind(R.id.subscriptionTime)
    TextView subscriptionTime;
    @Bind(R.id.paymentDate)
    TextView paymentDate;
    @Bind(R.id.productAnnouncementLayout)
    LinearLayout productAnnouncementLayout;
    @Bind(R.id.recordProofLayout)
    LinearLayout recordProofLayout;

    @Bind(R.id.divideTextView)
    TextView divideTextView;
    @Bind(R.id.changeTextView)
    TextView changeTextView;
    @Bind(R.id.timeTextView)
    TextView timeTextView;
    @Bind(R.id.ragneTextView)
    TextView ragneTextView;
    @Bind(R.id.FinancialActivityList)
    NoScrollListView FinancialActivityList;
    @Bind(R.id.numberTextView)
    TextView numberTextView;

    @Bind(R.id.listViewlayout)
    LinearLayout listViewlayout;

    private boolean isHistory;
    private MyProductModel productModels;
    FinancialRedementAdapter adapter;
    List<DividendRemendModel> DividendModel=new ArrayList<>();
    List<DividendRemendModel> RemodendModel=new ArrayList<>();
    @Override
    protected void initView() {
        ButterKnife.bind(this);

    }

    @Override
    protected void initEvent() {
        recordProofLayout.setOnClickListener(this);
        productAnnouncementLayout.setOnClickListener(this);
        divideTextView.setOnClickListener(this);
        changeTextView.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        isHistory = getIntent().getBooleanExtra("isHistory", false);
        productModels = (MyProductModel) getIntent().getSerializableExtra("productModels");
        productDynamicTitle.setTitle(isHistory ? "产品历史详情" : "产品动态详情");

        //兑付记录 派息记录
        ProductController.getInstance().DividendDate(this,productModels.getProductId());
        ProductController.getInstance().Redmemption(this,productModels.getProductId());
        //派息Item
        adapter=new FinancialRedementAdapter(this);
        FinancialActivityList.setAdapter(adapter);
        FinancialActivityList.setFocusable(false);
//        List<PointRecordDetail> model=new ArrayList<>();
//        model.add(new PointRecordDetail());
//        model.add(new PointRecordDetail());
//        model.add(new PointRecordDetail());
//        model.add(new PointRecordDetail());
//        adapter.setRes(model);
        setData();
    }
    private void setData() {
        try{
            productName.setText(productModels.getName());
            startPriceText.setText(((float) productModels.getAmount() / 10000) + "万");
            foundDay.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, productModels.getFoundDay()));
            long daoQiRi = 0l;
            String annualInterval = productModels.getAnnualInterval();
            if(StringTools.isNullOrEmpty(annualInterval)){
                annualInterval = "";
            }
            String annualOldYield = productModels.getAnnualOldYield();
            if(StringTools.isNullOrEmpty(annualOldYield)){
                annualOldYield = "";
            }else{
                annualOldYield = new DecimalFormat("0.00").format(Float.parseFloat(annualOldYield)*100f)+"%";
            }
            if(isHistory){
                interestNumber.setText(annualOldYield);
                daoQiRi = productModels.getDeadDate();
            }else{
                interestNumber.setText(annualInterval);
                daoQiRi = productModels.getExpireDay();
            }
            deadDate.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, daoQiRi));
            paymentDate.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, daoQiRi));
//        interestNumber.setText(productModels.getAnnualYield());
            subscriptionTime.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, productModels.getBuyTime()));
            //派息日
            String dividends = productModels.getDividendDay();
            if (null != dividends) {
                String[] strings = dividends.split(",");
                for (int i = 0; i < strings.length; i++) {
                    TextView view = new TextView(this);
                    view.setText(strings[i]);
                    view.setTextSize(16);
                    view.setTextColor(Color.parseColor("#46464B"));
                    view.setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.tenDp));
                    dividendDayLayout.addView(view);
                }
            }
            String redeem = productModels.getDividendDay();
            //赎回日
            if (null != redeem) {
                String[] strings = redeem.split(",");
                for (int i = 0; i < strings.length; i++) {
                    TextView view = new TextView(this);
                    view.setText(strings[i]);
                    view.setTextSize(16);
                    view.setTextColor(Color.parseColor("#46464B"));
                    view.setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.tenDp));
                    redeemDayLayout.addView(view);
                }
            }
            //开放日
            String open = productModels.getBuyDay();
            if (null != redeem) {
                String[] strings = open.split(",");
                for (int i = 0; i < strings.length; i++) {
                    TextView view = new TextView(this);
                    view.setText(strings[i]);
                    view.setTextSize(16);
                    view.setTextColor(Color.parseColor("#46464B"));
                    view.setPadding(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.tenDp));
                    openDayLayout.addView(view);
                }
            }

        }catch (Exception e){
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_product_dynamic_detail;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if(flag== HttpConfig.dividend.getType()){
             DividendModel=(List<DividendRemendModel>)result;
             adapter.setRes(DividendModel);
        }else {
            RemodendModel=(List<DividendRemendModel>)result;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.productAnnouncementLayout:
                if (StringTools.isNullOrEmpty(productModels.getNotice())) {
                    ToastTool.show("暂无成立公告");
                    return;
                }
                IntentTools.startScreenFullActivity(this, productModels.getNotice());
                break;
            case R.id.recordProofLayout:
                if (StringTools.isNullOrEmpty(productModels.getProveUrl())) {
                    ToastTool.show("暂无备案证明");
                    return;
                }
                IntentTools.startScreenFullActivity(this, productModels.getProveUrl());
                break;
            case R.id.changeTextView:
                ChangeStates(false);
                break;
            case R.id.divideTextView:
                ChangeStates(true);
                break;
        }
    }
    public void ChangeStates(boolean flag){

        if(flag){
            divideTextView.setTextColor(getResources().getColor(R.color.appColorBlue));
            changeTextView.setTextColor(getResources().getColor(R.color.titleTextColor));

            timeTextView.setText("派息时间");
            ragneTextView.setText("派息收益率");
            numberTextView.setText("派息金额");
            if(DividendModel.size()>0){
                FinancialActivityList.setVisibility(View.VISIBLE);
                FinancialActivityList.setFocusable(true);
                adapter.setFlag(true);
                adapter.setRes(DividendModel);
                adapter.notifyDataSetChanged();

            }else{
                FinancialActivityList.setVisibility(View.GONE);
            }
        }else{
            divideTextView.setTextColor(getResources().getColor(R.color.titleTextColor));
            changeTextView.setTextColor(getResources().getColor(R.color.appColorBlue));

            timeTextView.setText("兑付时间");
            ragneTextView.setText("兑付收益率");
            numberTextView.setText("兑付金额");

            if(RemodendModel.size()>0){
                FinancialActivityList.setVisibility(View.VISIBLE);
                FinancialActivityList.setFocusable(true);
                adapter.setFlag(false);
                adapter.setRes(RemodendModel);
                adapter.notifyDataSetChanged();
            }else{
                FinancialActivityList.setVisibility(View.GONE);
            }
        }

    }
}
