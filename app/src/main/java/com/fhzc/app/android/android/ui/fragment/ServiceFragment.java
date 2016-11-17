package com.fhzc.app.android.android.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.CommodityDetailPagerAdapter;
import com.fhzc.app.android.android.ui.view.widget.AutoScrollViewPager;
import com.fhzc.app.android.android.ui.view.widget.CirclePageIndicator;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.android.ui.view.widget.RoundRectBackImageView;
import com.fhzc.app.android.android.ui.view.widget.RoundRectBackRightImageView;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.LoginController;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.ActivityModel;
import com.fhzc.app.android.models.BannerModel;
import com.fhzc.app.android.models.IndexMapModel;
import com.fhzc.app.android.models.ProductModel;
import com.fhzc.app.android.models.ReportModel;
import com.fhzc.app.android.models.RightModel;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.CommonUtil;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**@deprecated 服务详情
 * Created by yanbo on 16-7-11.
 */
public class ServiceFragment extends Fragment implements View.OnClickListener, CommonToolBar.ClickRightListener, ViewNetCallBack, CommodityDetailPagerAdapter.ClickListener {


    View view;
    @Bind(R.id.reportDetailToolbar)
    CommonToolBar reportDetailToolbar;
    @Bind(R.id.image_slide_page)
    AutoScrollViewPager imageSlidePage;
    @Bind(R.id.indicator)
    CirclePageIndicator indicator;
    @Bind(R.id.fhfinancialImageView)
    RoundRectBackRightImageView fhfinancialImageView;
    @Bind(R.id.fhmemberImageView)
    RoundRectBackImageView fhmemberImageView;
    @Bind(R.id.fhreportImageView)
    RoundRectBackRightImageView fhreportImageView;
    @Bind(R.id.fhactivityImageView)
    RoundRectBackImageView fhactivityImageView;
    @Bind(R.id.titleNameText)
    TextView titleNameText;
    @Bind(R.id.interestNumber)
    TextView interestNumber;
    @Bind(R.id.distributionMode)
    TextView distributionMode;
    @Bind(R.id.timeText)
    TextView timeText;
    //    @Bind(R.id.startPriceText)
//    TextView startPriceText;
    @Bind(R.id.productStatus)
    ImageView productStatus;
    @Bind(R.id.interestProgress)
    ProgressBar interestProgress;
    @Bind(R.id.endTimeText)
    TextView endTimeText;
    @Bind(R.id.foundationLayout)
    LinearLayout foundationLayout;
    @Bind(R.id.lastedRight)
    ImageView lastedRight;
    @Bind(R.id.rightLayout)
    LinearLayout rightLayout;
    @Bind(R.id.id_gallery)
    LinearLayout idGallery;
    @Bind(R.id.activityLayout)
    LinearLayout activityLayout;
    @Bind(R.id.repostImageView)
    ImageView repostImageView;
    @Bind(R.id.repotrTitleText)
    TextView repotrTitleText;
    @Bind(R.id.reportContextTextView)
    TextView reportContextTextView;
    @Bind(R.id.reportContextTime)
    TextView reportContextTime;
    @Bind(R.id.reportLayout)
    LinearLayout reportLayout;
    @Bind(R.id.dataRootLayout)
    LinearLayout dataRootLayout;
    @Bind(R.id.scrollview)
    ScrollViewExtend allSv;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    @Bind(R.id.srLayout)
    SwipeRefreshLayout serviceRefresh;


    private LinearLayout mGallery;
    private RightModel rightModel;
    private ReportModel reportModel;
    private ProductModel productModel;
    private LayoutInflater mInflater;
    private EmptyLayout emptyLayout;
    CommodityDetailPagerAdapter bannerAdapter;
    public final static int BANNER_AUTO_SCROLL_INTERVAL = 3000;

    public static ServiceFragment newInstance() {
        ServiceFragment squareFragmentV2 = new ServiceFragment();
        return squareFragmentV2;
    }

    public void onEventMainThread(IMEvent event) {
        if (null != event) {
            reportDetailToolbar.setRedImage(new MessageDao().getUnReadCount());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //透明状态栏
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        view = inflater.inflate(R.layout.service_fragment_layout, null);
        ButterKnife.bind(this, view);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        mInflater = LayoutInflater.from(getActivity());
        mGallery = (LinearLayout) view.findViewById(R.id.id_gallery);
        serviceRefresh.setColorSchemeResources(R.color.appColorBlue);
        serviceRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        initData();
        initView();

        return view;
    }


    public void initData() {
        reportDetailToolbar.setRedImage(new MessageDao().getUnReadCount());
        initPager();
        LoginController.getInstance().myBank(this);
        mGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                mGallery.getChildAt(Integer.parseInt(view.getTag().toString()));
            }
        });
        reportDetailToolbar.setClickRightListener(this);
    }

    private void initPager() {

        bannerAdapter = new CommodityDetailPagerAdapter(getActivity());
        bannerAdapter.setListener(this);
        imageSlidePage.setAdapter(bannerAdapter);
        imageSlidePage.setAutoScrollDurationFactor(3.0f);
        imageSlidePage.setInterval(BANNER_AUTO_SCROLL_INTERVAL);
        imageSlidePage.startAutoScroll();
        indicator.setViewPager(imageSlidePage);
    }

    public void initView() {

        if (CommonUtil.isUpperKK()) {
            View view = new View(getActivity());
            view.setBackgroundColor(getResources().getColor(R.color.common_title));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight(getActivity()));
            view.setLayoutParams(layoutParams);
            rootLayout.addView(view, 0);
        }

        emptyLayout = new EmptyLayout(getActivity(), dataRootLayout);
        foundationLayout.setOnClickListener(this);
        rightLayout.setOnClickListener(this);
        activityLayout.setOnClickListener(this);
        reportLayout.setOnClickListener(this);

        fhfinancialImageView.setOnClickListener(this);
        fhmemberImageView.setOnClickListener(this);
        fhreportImageView.setOnClickListener(this);
        fhactivityImageView.setOnClickListener(this);

    }

    @Override
    public void onConnectStart() {
        emptyLayout.showLoading();
    }

    @Override
    public void onFail(Exception e) {
        emptyLayout.showError();
    }

    @Override
    public void onConnectEnd() {
        serviceRefresh.setRefreshing(false);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.foundationLayout:
                if (productModel == null)
                    return;
                IntentTools.startProduceDetail(getActivity(), productModel.getPid());
                break;
            case R.id.rightLayout:
                if (rightModel == null)
                    return;
                IntentTools.startNewRightDetail(getActivity(), rightModel.getId());
                break;
            case R.id.activityLayout:
                IntentTools.startActivityList(getActivity());
                break;
            case R.id.reportLayout:
                if (reportModel == null)
                    return;
                IntentTools.startReportDetail(getActivity(), reportModel.getId());
                break;
            case R.id.fhfinancialImageView:
                IntentTools.startProductList(getActivity());
                break;
            case R.id.fhmemberImageView:
                IntentTools.startNumbrtList(getActivity());
                IntentTools.startRightBenfitsNumbrtList(getActivity());
                break;
            case R.id.fhreportImageView:
                IntentTools.startReportList(getActivity());
//                IntentTools.startNewReportList(getActivity());
                break;
            case R.id.fhactivityImageView:
                IntentTools.startActivityList(getActivity());
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        CommonUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
        CommonUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }

    private void initProduct(ProductModel model) {
        productModel = model;
        titleNameText.setText(model.getName());

        String annualInterval = productModel.getAnnualInterval();
        if(StringTools.isNullOrEmpty(annualInterval)){
            annualInterval = productModel.getAnnualYield();
        }
        interestNumber.setText(annualInterval);
//        interestNumber.setText(model.getAnnualYield() == null || model.getAnnualYield().equals("null") ? "0%" : model.getAnnualYield());

        timeText.setText(model.getInvestTerm());

        int day = DateTools.friendlyDayTime(model.getCollectEnd());
        int day1 = DateTools.differDayTime(model.getCollectStart(), model.getCollectEnd());
        int day2 = DateTools.friendlyDayTime(model.getCollectStart());
        interestProgress.setProgress(day < 0 ? 100 : day2 < 0 ? Math.abs(day2) * 100 / day1 : 0);
        endTimeText.setText(day < 0 ? "募集已结束" : "还剩" + day + "天募集结束");

        distributionMode.setText(model.getIncomeDistributionType());
        productStatus.setImageResource(model.getStatus() == 1 ? R.drawable.product_preheat : model.getStatus() == 2 ? R.drawable.collecticon : R.drawable.product_end);

    }

    private void initRight(RightModel model) {
        rightModel = model;
        ImageLoader.getInstance(getActivity(), R.drawable.default_error_long).displayImage(model.getCover(), lastedRight);
    }

    private void initActivity(List<ActivityModel> list) {
        mGallery.removeAllViews();
        for (int i = 0; i < list.size(); i++) {
            final ActivityModel model = list.get(i);
            View view = mInflater.inflate(R.layout.activity_index_gallery_item,
                    mGallery, false);
            view.setTag(i);
            ImageView img = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);
            ImageView status = (ImageView) view
                    .findViewById(R.id.activity_item_status);
            if (model.getStatus() == 0) {
                status.setImageResource(R.drawable.activity_notstrart);
            } else if (model.getStatus() == 1) {
                status.setImageResource(R.drawable.activity_underway);
            } else if (model.getStatus() == 2) {
                status.setImageResource(R.drawable.complete);
            } else if (model.getStatus() == 3) {
                status.setImageResource(R.drawable.complete);
            }
            ImageLoader.getInstance(getActivity(), R.drawable.default_error_long).displayImage(model.getCover(), img);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentTools.startActivityDetail(getActivity(), model.getId());
                }
            });
            mGallery.addView(view);
        }
    }

    private void initReport(ReportModel model) {
        reportModel = model;
        reportContextTextView.setText(model.getSummary());
        repotrTitleText.setText(model.getName());
        reportContextTime.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getCtime()));
        ImageLoader.getInstance(getActivity(), R.drawable.default_error).displayImage(model.getCover(), repostImageView);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        emptyLayout.showSuccess(false);
        IndexMapModel model = (IndexMapModel) result;
        if (model.getProduct() != null && !model.getProduct().isEmpty()) {
            List<ProductModel> tempList = new ArrayList<>();

            for (ProductModel model1 : model.getProduct()) {
                if (model1.getStatus() != 5) {
                    tempList.add(model1);
                }
            }
            if (tempList != null && !tempList.isEmpty()) {
                initProduct(tempList.get(0));
            }
        }
        if (model.getRights() != null && !model.getRights().isEmpty()) {
            initRight(model.getRights().get(0));
        }
        if (model.getActivity() != null && !model.getActivity().isEmpty()) {
            initActivity(model.getActivity());
        }
        if (model.getReport() != null && !model.getReport().isEmpty()) {
            initReport(model.getReport().get(0));
        }
        initBanner(model);
    }

    private void initBanner(IndexMapModel mapModel) {
        if (mapModel.getBanner_pic() != null && !mapModel.getBanner_pic().isEmpty()) {
            bannerAdapter.setRes(mapModel.getBanner_pic());
            if (mapModel.getBanner_pic().size() == 1) {
                imageSlidePage.stopAutoScroll();
            } else {
                imageSlidePage.startAutoScroll();
            }
        }
    }

    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        imageSlidePage.stopAutoScroll();
    }

    @Override
    public void click(BannerModel model) {
        switch (model.getFromType()) {
            case "activity":
                IntentTools.startActivityDetail(getActivity(), Integer.parseInt(model.getFromId()));
                break;
            case "rights":
                IntentTools.startNewRightDetail(getActivity(), Integer.parseInt(model.getFromId()));
                break;
            case "product":
                IntentTools.startProduceDetail(getActivity(), Integer.parseInt(model.getFromId()));
                break;
            case "report":
                IntentTools.startReportDetail(getActivity(), Integer.parseInt(model.getFromId()));
                break;
        }
    }
}
