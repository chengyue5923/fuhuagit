package com.fhzc.app.android.android.ui.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.CompanyNewsAdapter;
import com.fhzc.app.android.android.ui.view.adapter.IndustryNewsAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.ImagePagerView;
import com.fhzc.app.android.android.ui.view.widget.NewsAutoScrollBanner;
import com.fhzc.app.android.configer.UrlRes;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.NewsController;
import com.fhzc.app.android.models.BannerModel;
import com.fhzc.app.android.models.NewsIndexMapModel;
import com.fhzc.app.android.models.NewsItemModel;
import com.fhzc.app.android.models.NewsModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fw on 16/10/11.
 */

public class HotNewsActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener{

    @Bind(R.id.myTabLayout)
    TabLayout tabLayout;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.lv_hotnews)
    ListView listView;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.vp_banner)
    NewsAutoScrollBanner banner;

    private CompanyNewsAdapter companyNewsAdapter;
    private IndustryNewsAdapter industryNewsAdapter;
    private List<String> paths = new ArrayList<>();
    private NewsIndexMapModel index;


    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tabLayout.addTab(tabLayout.newTab().setText("公司新闻").setTag(1),true);
        tabLayout.addTab(tabLayout.newTab().setText("行业新闻").setTag(2));
        tabLayout.addTab(tabLayout.newTab().setText("健康新闻").setTag(3));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tag = (int) tab.getTag();
                switch (tag){
                    case 1:
                        NewsController.getInstance().getNewsTypeList(HotNewsActivity.this,1);
                        break;
                    case 2:
                        NewsController.getInstance().getNewsTypeList(HotNewsActivity.this,2);
                        break;
                    case 3:
                        NewsController.getInstance().getNewsTypeList(HotNewsActivity.this,3);
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

        companyNewsAdapter = new CompanyNewsAdapter(this);
        industryNewsAdapter = new IndustryNewsAdapter(this);
        listView.setAdapter(companyNewsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int newsId = ((NewsModel)(parent.getAdapter().getItem(position))).getId();
                NewsController.getInstance().updateNewsReadcount(HotNewsActivity.this,newsId);
                IntentTools.startNewsDetail(HotNewsActivity.this,newsId);
            }
        });

//        swipeRefreshLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.GREEN, Color.BLUE, Color.CYAN);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });

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
                weekStr += "七";
        }


        tvDate.setText(dateStr+weekStr);


    }

    @Override
    protected void initEvent() {
        banner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        swipeRefreshLayout.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        swipeRefreshLayout.setEnabled(true);
                        break;
                }
                return false;
            }
        });

        banner.setOnItemClickLisener(new ImagePagerView.OnItemClickLisener() {
            @Override
            public void onItemClick(View view, int position) {
                Bannerclick(index.getBanner().get(banner.currentItem));
            }
        });
    }

    private void initBanner(NewsIndexMapModel mapModel) {
        List<BannerModel> banner_pic = mapModel.getBanner();
        paths.clear();
        if (banner_pic != null) {
            for (BannerModel m : banner_pic) {
                if(m.getType().equals("index_pic")){
                    paths.add(UrlRes.getInstance().getPictureUrl() + m.getCover());
                }
            }
        }
        banner.initData(paths);
        banner.setImageCount(paths.size());
        if (banner_pic != null) {
            for (int i = 0; i < banner_pic.size(); i++) {
                banner.strings.add(banner_pic.get(i).getTitle());
            }
        }
    }

    public void Bannerclick(BannerModel model) {
        switch (model.getFromType()) {
//            case "activity":
//                IntentTools.startActivityDetail(this, Integer.parseInt(model.getFromId()));
//                break;
//            case "rights":
//                IntentTools.startRightDetail(this, Integer.parseInt(model.getFromId()));
//                break;
////            case "product":
////                IntentTools.startProductDetail(this, Integer.parseInt(model.getFromId()));
////                break;
//            case "report":
//                IntentTools.startReportDetail(this, Integer.parseInt(model.getFromId()));
//                break;
            case "news":
                IntentTools.startNewsDetail(this,Integer.parseInt(model.getFromId()));
                break;

        }
    }
    @Override
    protected void initData() {
        switch (tabLayout.getSelectedTabPosition()){
            case 0:
                NewsController.getInstance().getNewsIndex(this);
                banner.stopService();
                break;
            case 1:
                NewsController.getInstance().getNewsTypeList(this,2);
                break;
            case 2:
                NewsController.getInstance().getNewsTypeList(this,3);
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_hotnews;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        swipeRefreshLayout.setRefreshing(false);
        if(flag == HttpConfig.getNewsIndex.getType()){
            index = (NewsIndexMapModel) result;
            companyNewsAdapter.setRes(index.getNews().getItems());
            initBanner(index);
        }else if(flag == HttpConfig.getNewsTypeList.getType()){
            int cid = (int) param.get("cid");
            switch (cid){
                case 1:
                    NewsItemModel item1 = (NewsItemModel) result;
                    companyNewsAdapter.setRes(item1.getItems());
                    listView.setAdapter(companyNewsAdapter);
                    break;
                case 2:
                    NewsItemModel item2 = (NewsItemModel) result;
                    industryNewsAdapter.setRes(item2.getItems());
                    listView.setAdapter(industryNewsAdapter);
                    break;
                case 3:
                    NewsItemModel item3 = (NewsItemModel) result;
                    industryNewsAdapter.setRes(item3.getItems());
                    listView.setAdapter(industryNewsAdapter);
                    break;
            }
        }
    }

    @Override
    public void OnClickRight(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
