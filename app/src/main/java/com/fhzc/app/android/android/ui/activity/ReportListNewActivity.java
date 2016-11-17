package com.fhzc.app.android.android.ui.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.ReportListByTypeAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.ImagePagerViewPointRight;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.configer.UrlRes;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.ReportController;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.ReportListMapModel;
import com.fhzc.app.android.models.ReportModel;
import com.fhzc.app.android.models.ReportbannerModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/7/20.
 */
public class ReportListNewActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {
    @Bind(R.id.reportnewListToolBar)
    CommonToolBar reportnewListToolBar;
    @Bind(R.id.ImagePagerView)
    ImagePagerViewPointRight ImagePagerView;
    @Bind(R.id.myTabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.lv_reportlist)
    NoScrollListView lv_reportlist;
    @Bind(R.id.mScrollView)
    ScrollViewExtend mScrollView;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;

    @Bind(R.id.nonedate)
    TextView nonedate;
    List<ReportModel> financialList=new ArrayList<>();
    List<ReportModel> SecondList=new ArrayList<>();
    List<ReportModel> ThirdList=new ArrayList<>();
    List<ReportModel> FourList=new ArrayList<>();
    ReportListByTypeAdapter adapter;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }

        String dateStr = DateTools.dateFormatterOfDateTimeForNow(DateTools.DATE_FORMAT_STYLE_3);
        int week = Integer.parseInt(DateTools.getDayOfWeek(dateStr,DateTools.DATE_FORMAT_STYLE_3,""));
        String weekStr = "  星期";
        switch (week){
            case 1:
                weekStr += "一";
                break;
            case 2:
                weekStr += "二";
                break;
            case 3:
                weekStr += "三";
                break;
            case 4:
                weekStr += "四";
                break;
            case 5:
                weekStr += "五";
                break;
            case 6:
                weekStr += "六";
                break;
            case 7:
                weekStr += "日";
        }

        tvDate.setText(dateStr+weekStr);

    }

    @Override
    protected void initEvent() {
//        reportnewListToolBar.setClickRightListener(this);
        ImagePagerView.setOnItemClickLisener(new ImagePagerViewPointRight.OnItemClickLisener() {
            @Override
            public void onItemClick(View view, int position) {
                Bannerclick(model.getBanner().get(position));
            }
        });
    }
    public void Bannerclick(ReportbannerModel model) {
        switch (model.getFromType()) {
            case "activity":
                IntentTools.startActivityDetail(this, Integer.parseInt(model.getFromId()));
                break;
            case "rights":
                IntentTools.startNewRightDetail(this, Integer.parseInt(model.getFromId()));
                break;
//            case "product":
//                IntentTools.startProduceDetail(this, Integer.parseInt(model.getFromId()));
//                break;
            case "report":
                IntentTools.startReportDetail(this, Integer.parseInt(model.getFromId()));
                break;
        }
    }
    @Override
    public void onClick(View v) {
    }

    public void onEventMainThread(IMEvent event) {
        if (null != event) {
//            reportnewListToolBar.setRedImage(new MessageDao().getUnReadCount());
        }
    }

    @Override
    protected void initData() {
//        reportnewListToolBar.setRedImage(new MessageDao().getUnReadCount());
        ReportController.getInstance().reportListSDetail(this);
        tabLayout.addTab(tabLayout.newTab().setText("财经视角").setTag(1),true);
        tabLayout.addTab(tabLayout.newTab().setText("投研周报").setTag(2));
        tabLayout.addTab(tabLayout.newTab().setText("研究报告").setTag(3));
        tabLayout.addTab(tabLayout.newTab().setText("配置建议").setTag(4));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tag = (int) tab.getTag();
                switch (tag){
                    case 1:
                        adapter.setRes(financialList);
                        adapter.notifyDataSetChanged();
                        break;
                    case 2:
                        adapter.setRes(SecondList);
                        adapter.notifyDataSetChanged();
                        break;
                    case 3:
                        adapter.setRes(ThirdList);
                        adapter.notifyDataSetChanged();
                        break;
                    case 4:
                        adapter.setRes(FourList);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        swipeContainer.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                ReportController.getInstance().reportTypeListDetail(ReportListNewActivity.this);
                ReportController.getInstance().reportListSDetail(ReportListNewActivity.this);
            }
        });
        nonedate.setVisibility(View.GONE);
        lv_reportlist.setVisibility(View.VISIBLE);
        adapter=new ReportListByTypeAdapter(this);
        lv_reportlist.setAdapter(adapter);
        lv_reportlist.setFocusable(false);
        lv_reportlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentTools.startReportDetail(ReportListNewActivity.this,adapter.getItem(i).getId());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_report_new_list;
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
//        emptyLayout.showLoading();
    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
//        emptyLayout.showError();
    }
    ReportListMapModel model;
    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        swipeContainer.setRefreshing(false);

         model=(ReportListMapModel)result;
        List<String> paths = new ArrayList<>();
        List<String> textpaths = new ArrayList<>();
        paths.clear();
        for(ReportbannerModel m:model.getBanner()){
            if (m.getCover() != null) {
                textpaths.add(m.getTitle());
                paths.add(UrlRes.getInstance().getPictureUrl() + m.getCover());
            }
        }
        ImagePagerView.initData(paths,textpaths);


        for(ReportModel  mo:model.getReport().getItems()){

            switch (mo.getCid()){
                case 1:
                    financialList.add(mo);
                    break;
                case 2:
                    SecondList.add(mo);
                    break;
                case 3:
                    ThirdList.add(mo);
                    break;
                case 4:
                    FourList.add(mo);
                    break;
            }
        }
        adapter.setRes(financialList);
    }


    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(this);
    }

}
