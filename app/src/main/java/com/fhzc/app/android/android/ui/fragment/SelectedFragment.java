package com.fhzc.app.android.android.ui.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.ListUtil;
import com.base.platform.utils.java.StringTools;
import com.base.view.view.dialog.factory.DialogFacory;
import com.fhzc.app.android.BuildConfig;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.service.UpdateService;
import com.fhzc.app.android.android.ui.view.adapter.CommodityDetailPagerAdapter;
import com.fhzc.app.android.android.ui.view.adapter.IndustryNewsAdapter;
import com.fhzc.app.android.android.ui.view.adapter.SelectedProductAdapter;
import com.fhzc.app.android.android.ui.view.adapter.SelectedReportListAdapter;
import com.fhzc.app.android.android.ui.view.widget.AutoVerticalScrollTextView;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.android.ui.view.widget.ImagePagerView;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.android.ui.view.widget.RoundImageView;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.configer.UrlRes;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.LoginController;
import com.fhzc.app.android.controller.NewsController;
import com.fhzc.app.android.controller.ReportController;
import com.fhzc.app.android.controller.SystemController;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.ActivityModel;
import com.fhzc.app.android.models.AppVersionModel;
import com.fhzc.app.android.models.BannerModel;
import com.fhzc.app.android.models.BannerTextModel;
import com.fhzc.app.android.models.IndexMapModel;
import com.fhzc.app.android.models.NewsModel;
import com.fhzc.app.android.models.ProductModel;
import com.fhzc.app.android.models.ReportModel;
import com.fhzc.app.android.models.RightModel;
import com.fhzc.app.android.models.SystemNoticeModel;
import com.fhzc.app.android.models.UserModel;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.CommonUtil;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 16-7-11.
 */
public class SelectedFragment extends Fragment implements View.OnClickListener, ViewNetCallBack, ScrollViewExtend.ObservableScrollViewCallbacks, CommodityDetailPagerAdapter.ClickListener {

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.textview_auto_roll)
    AutoVerticalScrollTextView textviewAutoRoll;
    @Bind(R.id.productLay)
    LinearLayout productLay;
    @Bind(R.id.rightLay)
    LinearLayout rightLay;
    @Bind(R.id.activityLay)
    LinearLayout activityLay;
    @Bind(R.id.attentionLay)
    LinearLayout attentionLay;
    @Bind(R.id.selltv)
    TextView selltv;
    @Bind(R.id.productRightArrow)
    ImageView productRightArrow;
    @Bind(R.id.topProduceLayout)
    RelativeLayout topProduceLayout;
    @Bind(R.id.selectedProductLv)
    NoScrollListView selectedProductLv;
    @Bind(R.id.activityRightArrow)
    ImageView activityRightArrow;
    @Bind(R.id.selectedActivityLay)
    RelativeLayout selectedActivityLay;
    @Bind(R.id.activityContainerLayout)
    LinearLayout activityContainerLayout;
    @Bind(R.id.repotRightArrow)
    ImageView repotRightArrow;
    @Bind(R.id.selectedInvestmentLay)
    RelativeLayout selectedInvestmentLay;
    @Bind(R.id.selectedInvestmentLv)
    NoScrollListView selectedInvestmentLv;
    @Bind(R.id.scrollView)
    ScrollViewExtend scrollView;
    @Bind(R.id.selectedRefresh)
    SwipeRefreshLayout selectedRefresh;
    @Bind(R.id.personInfoIv)
    ImageView personInfoIv;
    @Bind(R.id.messageIv)
    ImageView messageIv;
    @Bind(R.id.systemMessageIv)
    ImageView systemMessageIv;
    @Bind(R.id.systemMessageRedImage)
    TextView systemMessageRedImage;
//    @Bind(R.id.toolLayoutopLay
    @Bind(R.id.messageRedImage)
    TextView messageRedImage;
    @Bind(R.id.topLay)
    RelativeLayout topLay;
    @Bind(R.id.rootLayout)
    RelativeLayout rootLayout;
    @Bind(R.id.pager)
    ImagePagerView pager;

    @Bind(R.id.personLayout)
    LinearLayout personLayout;
    @Bind(R.id.systemMessageLayout)
    RelativeLayout systemMessageLayout;
    @Bind(R.id.messageLayout)
    RelativeLayout messageLayout;
    @Bind(R.id.selectedNewsLv)
    NoScrollListView newsListView;
    @Bind(R.id.tv_right_title1)
    TextView rightTitle1;
    @Bind(R.id.tv_right_title2)
    TextView rightTitle2;
    @Bind(R.id.tv_right_title3)
    TextView rightTitle3;
    @Bind(R.id.tv_right_title4)
    TextView rightTitle4;
    @Bind(R.id.tv_right_content1)
    TextView rightContent1;
    @Bind(R.id.tv_right_content2)
    TextView rightContent2;
    @Bind(R.id.tv_right_content3)
    TextView rightContent3;
    @Bind(R.id.tv_right_content4)
    TextView rightContent4;
    @Bind(R.id.iv_right1)
    ImageView ivRight1;
    @Bind(R.id.iv_right2)
    ImageView ivRight2;
    @Bind(R.id.iv_right3)
    ImageView ivRight3;
    @Bind(R.id.iv_right4)
    ImageView ivRight4;
    @Bind(R.id.ll_right1)
    LinearLayout llRight1;
    @Bind(R.id.ll_right2)
    LinearLayout llRight2;
    @Bind(R.id.ll_right3)
    LinearLayout llRight3;
    @Bind(R.id.ll_right4)
    LinearLayout llRight4;

    private SelectedReportListAdapter adapter;
    private SelectedProductAdapter productAdapter;
    List<String> paths = new ArrayList<>();
    private int mParallaxImageHeight;
    EmptyLayout emptyLayout;
    public final static int BANNER_AUTO_SCROLL_INTERVAL = 3000;

    private int number = 0;
    private boolean isRunning = true;

    private static String[] strings = {"", ""};
    private IndexMapModel model;
    private IndustryNewsAdapter newsAdapter;



    public static SelectedFragment newInstance() {
        SelectedFragment squareFragmentV2 = new SelectedFragment();
        return squareFragmentV2;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected, container,
                false);
        ButterKnife.bind(this, view);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        emptyLayout = new EmptyLayout(getActivity(), scrollView);
        selectedRefresh.setColorSchemeResources(R.color.appColorBlue);


        initData();
        initEvent();
//        CommonUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);

//        systemMessageRedImage.setVisibility(View.GONE);
        //垂直滚动的TextView
        textviewAutoRoll.setText(strings[0]);
        if (strings.length > 1) {
            new Thread() {
                @Override
                public void run() {
                    while (isRunning) {
                        SystemClock.sleep(3000);
                        handler.sendEmptyMessage(199);
                    }
                }
            }.start();
        }
        SystemController.getInstance().systemNotice(this);
        return view;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == 199) {
                try {
                    textviewAutoRoll.next();
                    number++;
                    textviewAutoRoll.setText(strings[number % strings.length]);
                    number = number % strings.length;
                } catch (Exception e) {
                }

            }

        }
    };

    private void initEvent() {
        pager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        selectedRefresh.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        selectedRefresh.setEnabled(true);
                        break;
                }
                return false;
            }
        });
        personLayout.setOnClickListener(this);
        systemMessageLayout.setOnClickListener(this);
        messageLayout.setOnClickListener(this);
        messageIv.setOnClickListener(this);
        attentionLay.setOnClickListener(this);
        rightLay.setOnClickListener(this);
        activityLay.setOnClickListener(this);
        selectedInvestmentLay.setOnClickListener(this);
        selectedActivityLay.setOnClickListener(this);
        systemMessageIv.setOnClickListener(this);
        personInfoIv.setOnClickListener(this);
        productLay.setOnClickListener(this);
        topProduceLayout.setOnClickListener(this);
        scrollView.setCallbacks(this);
        textviewAutoRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (model.getBanner_text() == null || model.getBanner_text().size() <= 0)
                    return;
                if (model.getBanner_text().size() == 1) {
                    BannerTextclick(model.getBanner_text().get(0));
                } else {
                    for (BannerTextModel mtext : model.getBanner_text()) {
                        if (strings[number].equals(mtext.getText())) {
                            BannerTextclick(mtext);
                        }
                    }
                }
            }
        });
        selectedRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                pager.stopService();
            }
        });
        pager.setOnItemClickLisener(new ImagePagerView.OnItemClickLisener() {
            @Override
            public void onItemClick(View view, int position) {
                Bannerclick(model.getBanner_pic().get(pager.currentItem));
            }
        });

    }

    public void onEventMainThread(IMEvent event) {
        try{
            if (null != event) {
                int count = new MessageDao().getUnReadCount();
                messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
//                messageRedImage.setText(String.valueOf(count));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onScrollChanged(int scrollY) {
        int baseColor = getResources().getColor(R.color.common_title);
        float alpha = 1 - (float) Math.max(0, mParallaxImageHeight - scrollY) / mParallaxImageHeight;
        int sdk = Build.VERSION.SDK_INT;
        if (alpha > 0.2) {//隐藏 y>34dp
            CommonUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
            CommonUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
            messageIv.setImageDrawable(getResources().getDrawable(R.drawable.title_message));
            systemMessageIv.setImageDrawable(getResources().getDrawable(R.drawable.selected_systemmsg_black));
//            personInfoIv.setImageDrawable(getResources().getDrawable(R.drawable.selected_peroninfo));
            if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
                topLay.setBackgroundDrawable(null);
            } else {
                topLay.setBackground(null);
            }

        } else {//显示
            CommonUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), false);
            CommonUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), false);
            messageIv.setImageDrawable(getResources().getDrawable(R.drawable.selected_message));
            systemMessageIv.setImageDrawable(getResources().getDrawable(R.drawable.selected_systemmsg));
//            personInfoIv.setImageDrawable(getResources().getDrawable(R.drawable.selected_peroninfo));

            if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
                topLay.setBackgroundDrawable(getResources().getDrawable(R.color.common_title));
            } else {
                topLay.setBackground(getResources().getDrawable(R.color.common_title));
            }
        }
        topLay.setBackgroundColor(CommonUtil.getColorWithAlpha(alpha, baseColor));
    }

    @Override
    public void onScrollBottom() {

    }

    private void initData() {
        int count = new MessageDao().getUnReadCount();
        messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
//        messageRedImage.setText(String.valueOf(count));
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.top_image_height);
        LoginController.getInstance().index(this);
        UserController.getInstance().userInfo(this);
        SystemController.getInstance().latestApp(this);
    }

    @Override
    public void onResume() {
        super.onResume();
//        int count = new MessageDao().getUnReadCount();
//        messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
//        messageRedImage.setText(String.valueOf(count));
//        UserController.getInstance().userInfo(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.messageIv:
                IntentTools.startChatList(getActivity());
                break;
            case R.id.attentionLay:
                IntentTools.startCollectionActivity(getActivity(), "我的关注");
                break;
//            case R.id.selectedInvestmentLay:
//                IntentTools.startReportList(getActivity());
//                break;
            case R.id.rightLay:
//                IntentTools.startNumbrtList(getActivity());
                IntentTools.startCustomerRightChange(getActivity());
                break;
//            case R.id.selectedActivityLay:
            case R.id.activityLay:
//                IntentTools.startActivityList(getActivity());
                IntentTools.startActivitiesFocast(getActivity());
                break;
            case R.id.personInfoIv:
                IntentTools.startPersonNewInfo(getActivity());
                break;
            case R.id.systemMessageIv:
                systemMessageRedImage.setVisibility(View.GONE);
                IntentTools.startSystemMessage(getActivity());
                break;
            case R.id.productLay:
//                IntentTools.startProductList(getActivity());
                IntentTools.startHotProducts(getActivity());
                break;
//            case R.id.topProduceLayout:
//                IntentTools.startFinancialActivity(getActivity());
//                break;

            case R.id.personLayout:
                IntentTools.startPersonInfo(getActivity());
                break;
            case R.id.messageLayout:
                IntentTools.startChatList(getActivity());
                break;
            case R.id.systemMessageLayout:
                systemMessageRedImage.setVisibility(View.GONE);
                IntentTools.startSystemMessage(getActivity());
                break;

        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setData(IndexMapModel mapModel) {
        try{
            selectedRefresh.setRefreshing(false);
            initActivity(mapModel);
            initReport(mapModel);
            initProduct(mapModel);
            initBanner(mapModel);
            initNews(mapModel);
            initRights(mapModel);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initRights(IndexMapModel mapModel){
        final List<RightModel> models = mapModel.getRights();
        rightTitle1.setText(models.get(0).getType());
        rightContent1.setText(models.get(0).getRightsName());
        ImageLoader.getInstance(getContext()).displayImage(models.get(0).getCover(),ivRight1);
        llRight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentTools.startNewRightDetail(getActivity(),models.get(0).getId());
            }
        });

        rightTitle2.setText(models.get(1).getType());
        rightContent2.setText(models.get(1).getRightsName());
        ImageLoader.getInstance(getContext()).displayImage(models.get(1).getCover(),ivRight2);
        llRight2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentTools.startNewRightDetail(getActivity(),models.get(1).getId());
            }
        });

        rightTitle3.setText(models.get(2).getType());
        rightContent3.setText(models.get(2).getRightsName());
        ImageLoader.getInstance(getContext()).displayImage(models.get(2).getCover(),ivRight3);
        llRight3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentTools.startNewRightDetail(getActivity(),models.get(2).getId());
            }
        });

        rightTitle4.setText(models.get(3).getType());
        rightContent4.setText(models.get(3).getRightsName());
        ImageLoader.getInstance(getContext()).displayImage(models.get(3).getCover(),ivRight4);
        llRight4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentTools.startNewRightDetail(getActivity(),models.get(3).getId());
            }
        });

    }

    private void initBanner(IndexMapModel mapModel) {
        List<BannerModel> banner_pic = mapModel.getBanner_pic();
        List<BannerTextModel> banner_text = mapModel.getBanner_text();
        paths.clear();
        if (banner_pic != null) {
            for (BannerModel m : banner_pic) {
                paths.add(UrlRes.getInstance().getPictureUrl() + m.getCover());
            }
        }
        pager.initData(paths);
        if (banner_text == null) {
            banner_text = new ArrayList<>();
        }
        for (int i = 0; i < banner_text.size(); i++) {
            strings[i] = banner_text.get(i).getText();
        }
    }

    private void initProduct(IndexMapModel mapModel) {
        productAdapter = new SelectedProductAdapter(getActivity());
        List<ProductModel> product = mapModel.getProduct();
        selectedProductLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentTools.startProduceDetail(getActivity(), productAdapter.getItem(position).getPid());
            }
        });
        selectedProductLv.setFocusable(false);
        selectedProductLv.setAdapter(productAdapter);
        List<ProductModel> tempProduct = new ArrayList<>();

        if (product == null) {
            product = new ArrayList<>();
        }
        /**
         * status  1预热 2募集中 3募集结束
         */
        Collections.sort(product,new SortByStatus());
//        for (ProductModel model : product) {
//            if (model.getStatus() == 2) {
//                tempProduct.add(model);
//            }
//        }

        productAdapter.setRes(product);
    }

    private void initNews(IndexMapModel mapModel){
        newsAdapter = new IndustryNewsAdapter(getActivity());
        List<NewsModel> newsList = mapModel.getNews();
        newsListView.setFocusable(false);
        newsAdapter.setIndex(true);
        newsListView.setAdapter(newsAdapter);
        newsAdapter.setRes(newsList);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int newsId = ((NewsModel)(parent.getAdapter().getItem(position))).getId();
                NewsController.getInstance().updateNewsReadcount(SelectedFragment.this,newsId);
                IntentTools.startNewsDetail(getActivity(),newsId);
            }
        });
    }

    class SortByStatus implements Comparator {
        public int compare(Object o1, Object o2) {
            ProductModel s1 = (ProductModel) o1;
            ProductModel s2 = (ProductModel) o2;
            if (s1.getStatus() < s2.getStatus()){
                return -1;
            }else if(s1.getStatus()>s2.getStatus()){
                return 1;
            }else{
                return 0;
            }
        }
    }

    private void initActivity(IndexMapModel mapModel) {
        activityContainerLayout.removeAllViews();
        List<ActivityModel> activity = mapModel.getActivity();
        if (activity != null && !activity.isEmpty()) {
            for (final ActivityModel activityModel : activity) {
                View v = getActivity().getLayoutInflater().inflate(R.layout.view_selected_product, null);
                RoundImageView imageIv = (RoundImageView) v.findViewById(R.id.roundImage);
                TextView name = (TextView)v.findViewById(R.id.activity_item_name);
                TextView time = (TextView)v.findViewById(R.id.activity_item_time);
                TextView add = (TextView)v.findViewById(R.id.activity_item_add);
                name.setText(activityModel.getName().trim());
                time.setText("报名截止时间：" + DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, activityModel.getApplyEndTime()));
                add.setText("活动地点: "+activityModel.getAddress());

//                去掉活动右上角的状态角标
//                ImageView status = (ImageView) v
//                        .findViewById(R.id.activity_item_status);
//                if (activityModel.getStatus() == 0) {
//                    status.setImageResource(R.drawable.activity_notstrart);
//                } else if (activityModel.getStatus() == 1) {
//                    status.setImageResource(R.drawable.activity_underway);
//                } else if (activityModel.getStatus() == 2) {
//                    status.setImageResource(R.drawable.complete);
//                } else if (activityModel.getStatus() == 3) {
//                    status.setImageResource(R.drawable.complete);
//                }
                ImageLoader.getInstance(getContext(), R.drawable.default_error_long).displayImage(activityModel.getCover(), imageIv);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IntentTools.startActivityDetail(getActivity(), activityModel.getId());
                    }
                });
                activityContainerLayout.addView(v);
            }
        }
    }

    private void initReport(IndexMapModel mapModel) {
        adapter = new SelectedReportListAdapter(getActivity());
        selectedInvestmentLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IntentTools.startReportDetail(getActivity(), adapter.getItem(position).getId());
                ReportController.getInstance().reportTypeClickDetail(SelectedFragment.this,adapter.getItem(position).getId());
            }
        });
        selectedInvestmentLv.setFocusable(false);
        selectedInvestmentLv.setAdapter(adapter);
        List<ReportModel> report = mapModel.getReport();
        if (report == null) {
            report = new ArrayList<>();
        }
        if(report.size()<=3){
            adapter.setRes(report);
        }else{
            List<ReportModel> reportmodel=new ArrayList<>();
            for(int i=0;i<3;i++){
                reportmodel.add(report.get(i));
            }
            adapter.setRes(reportmodel);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }

    @Override
    public void onConnectStart() {
    }

    @Override
    public void onConnectEnd() {

    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.userInfo.getType()) {
            UserModel model = (UserModel) result;
            UserPreference.setUser(model);

            ImageLoader.getInstance(getContext(), R.drawable.selected_peroninfo).displayImage(model.getAvatar(), personInfoIv);
        }
        if (flag == HttpConfig.index.getType()) {
            emptyLayout.showSuccess(false);
            model = (IndexMapModel) result;
            setData(model);
        }
        if (flag == HttpConfig.latestApp.getType()) {
            AppVersionModel model = (AppVersionModel) result;
            int remoteVersion = Integer.parseInt(model.getVersion().replace(".", ""));
            int currentVersion = BuildConfig.VERSION_CODE;
            if (currentVersion < remoteVersion && !StringTools.isNullOrEmpty(model.getAndroidUrl())) {
                showNewVersionDialog(model.getAndroidUrl());
            }
        }
        if (flag == HttpConfig.systemNotice.getType()) {
            List<SystemNoticeModel> list = (List<SystemNoticeModel>) result;
            if (ListUtil.isNullOrEmpty(list)) {
//                systemMessageRedImage.setVisibility(View.GONE);
            } else {
//                systemMessageRedImage.setVisibility(View.VISIBLE);
//                Logger.e("sixe"+list.size());
//                systemMessageRedImage.setText(list.size()+"");
            }
        }
    }

    @Override
    public void onFail(Exception e) {
//        emptyLayout.showError();
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

    public void Bannerclick(BannerModel model) {
        switch (model.getFromType()) {
            case "activity":
                IntentTools.startActivityDetail(getActivity(), Integer.parseInt(model.getFromId()));
                break;
            case "rights":
                IntentTools.startNewRightDetail(getActivity(), Integer.parseInt(model.getFromId()));
                break;
            case "product":
                //cid2 为旅游地产
                if(model.getCid()==2){
                    IntentTools.startTourismProduceDetail(getActivity(),Integer.parseInt(model.getFromId()));
                }else{
                    IntentTools.startProductDetail(getActivity(), Integer.parseInt(model.getFromId()));
                }
                break;
            case "report":
                IntentTools.startReportDetail(getActivity(), Integer.parseInt(model.getFromId()));
                break;
            case "news":
                IntentTools.startNewsDetail(getActivity(),Integer.parseInt(model.getFromId()));
                break;
        }
    }

    public void BannerTextclick(BannerTextModel model) {
        if (model.getFromType() != null) {
            switch (model.getFromType()) {
                case "activity":
                    IntentTools.startActivityDetail(getActivity(), Integer.parseInt(model.getFromId()));
                    break;
                case "rights":
                    IntentTools.startNewRightDetail(getActivity(), Integer.parseInt(model.getFromId()));
                    break;
//                case "product":
//                    IntentTools.startProduceDetail(getActivity(), Integer.parseInt(model.getFromId()));
//                    break;
                case "report":
                    IntentTools.startReportDetail(getActivity(), Integer.parseInt(model.getFromId()));
                    break;
            }
        }
    }

    private void showNewVersionDialog(final String url) {
        DialogFacory.getInstance().createAlertDialog(getActivity(), "软件升级", "发现新版本,建议立即更新使用.", "更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent updateIntent = new Intent(
                        getActivity(),
                        UpdateService.class);
                updateIntent.putExtra("url",
                        url);
                getActivity().startService(updateIntent);
            }
        }, null).show();
    }
}
