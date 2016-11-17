package com.fhzc.app.android.android.ui.activity.personinformation;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.MyPlannerRankAdapter;
import com.fhzc.app.android.android.ui.view.widget.CircleImageView;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.RankbackDataModel;
import com.fhzc.app.android.utils.im.ImageLoader;
import com.github.mikephil.charting.data.BarData;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 业绩查询
 * Created by yanbo on 2016/7/21.
 */
public class FhRecordSearchActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.nametext)
    TextView nametext;
    @Bind(R.id.topLayout)
    LinearLayout topLayout;
    @Bind(R.id.bottomLayout)
    LinearLayout bottomLayout;
    @Bind(R.id.searchTextView)
    TextView searchTextView;
    @Bind(R.id.backImageView)
    ImageView backImageView;
    @Bind(R.id.backimagedd)
    ImageView backimagedd;
    @Bind(R.id.personinfoimage)
    CircleImageView personinfoimage;
    ImageView personaver;
    @Bind(R.id.listViewyear)
    NoScrollListView listViewyear;
    @Bind(R.id.listViewjidu)
    NoScrollListView listViewjidu;
    @Bind(R.id.listViewmonth)
    NoScrollListView listViewmonth;
    @Bind(R.id.rangeListView)
    NoScrollListView rangeListView;
    @Bind(R.id.scrollview)
    ScrollViewExtend allSv;

    @Bind(R.id.yejiText)
    TextView yejiText;
    @Bind(R.id.rankId)
    TextView rankId;
    @Bind(R.id.NoDataText)
    TextView NoDataText;
    @Bind(R.id.backimagelayout)
    LinearLayout backimagelayout;
    @Bind(R.id.titleRightIV)
    ImageView titleRightIV;
    private BarData mBarData;

    private String[] year = new String[]{"2014年", "2015年", "2016年"};
    private String[][] jidu = new String[][]{{"2014全年", "第一季度", "第二季度", "第三季度", "第四季度"}, {"2015全年", "第一季度", "第二季度", "第三季度", "第四季度"}, {"2016全年", "第一季度", "第二季度", "第三季度", "第四季度"}};
    private String[][] month = new String[][]{{"第一季度", "1月", "2月", "3月"}, {"第二季度", "4月", "5月", "6月"}, {"第三季度", "7月", "8月", "9月"}, {"第四季度", "10月", "11月", "12月"}};
    private String[] item = new String[]{};
    private int yearid;
    private int jiduid;
    private int monthid;
    private String monthitemtotal = "";

    public  static  String starttime="";
    public  static String endtime="";
    MyPlannerRankAdapter plannerRankAdapter;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        backimagelayout.setOnClickListener(this);
        backImageView.setOnClickListener(this);
        searchTextView.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        ImageLoader.getInstance(this, R.drawable.selected_peroninfo).displayImage(UserPreference.getAvatar(), personinfoimage);
        nametext.setText(UserPreference.getName());
        plannerRankAdapter=new MyPlannerRankAdapter(this);
        rangeListView.setAdapter(plannerRankAdapter);

        UserController.getInstance().RankSearch(FhRecordSearchActivity.this, "2016-01-01",  "2016-12-31");

        listViewyear.setVisibility(View.GONE);
        listViewjidu.setVisibility(View.GONE);
        listViewmonth.setVisibility(View.GONE);
        listViewyear.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, year));
        listViewyear.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    yearid = i;
                    listViewjidu.setVisibility(View.GONE);
                    listViewmonth.setVisibility(View.GONE);
                    listViewjidu.setAdapter(new ArrayAdapter<String>(FhRecordSearchActivity.this, android.R.layout.simple_list_item_1, jidu[i]));
                    listViewjidu.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        listViewjidu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    jiduid = i;
                    listViewmonth.setVisibility(View.GONE);
                    if (i == 0) {
                        listViewyear.setVisibility(View.GONE);
                        listViewjidu.setVisibility(View.GONE);
                        monthitemtotal = jidu[yearid][i];
                        searchTextView.setText(monthitemtotal);
                        titleRightIV.setBackgroundResource(R.drawable.hapess);
                        getTime(monthitemtotal);
                    } else {
                        listViewmonth.setAdapter(new ArrayAdapter<String>(FhRecordSearchActivity.this, android.R.layout.simple_list_item_1, month[i - 1]));
                        listViewmonth.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        listViewmonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                monthid = i;
                listViewyear.setVisibility(View.GONE);
                listViewjidu.setVisibility(View.GONE);
                listViewmonth.setVisibility(View.GONE);
                if (i == 0) {
                    monthitemtotal = year[yearid] + jidu[yearid][jiduid];
                    getTimes(year[yearid], jidu[yearid][jiduid]);
                } else {
                    monthitemtotal = year[yearid] + month[jiduid - 1][i];
                    getTimew(year[yearid], jidu[yearid][jiduid], month[jiduid - 1][i]);
                }
                searchTextView.setText(monthitemtotal);
                titleRightIV.setBackgroundResource(R.drawable.hapess);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_record_search;
    }

    public  void getTime(String search){
        if(search.equals("2014全年")){
            starttime="2014-01-01";
            endtime="2014-12-31";
        }else if(search.equals("2015全年")){
            starttime="2015-01-01";
            endtime="2015-12-31";
        }else if(search.equals("2016全年")){
            starttime="2016-01-01";
            endtime="2016-12-31";
        }
        rangeListView.setVisibility(View.GONE);
        yejiText.setText("暂无业绩");
        rankId.setText("无排名");
        UserController.getInstance().RankSearch(FhRecordSearchActivity.this,starttime,endtime);
    }
    public  void getTimes(String year,String jidu){

        if(year.equals("2014年")){
            if(jidu.equals("第一季度")){
                starttime="2014-01-01";
                endtime="2014-04-01";
            }else if(jidu.equals("第二季度")){
                starttime="2014-04-01";
                endtime="2014-07-01";
            }else if(jidu.equals("第三季度")){
                starttime="2014-07-01";
                endtime="2014-10-01";
            }else if(jidu.equals("第四季度")){
                starttime="2014-10-01";
                endtime="2015-01-01";
            }
        }else if(year.equals("2015年")){
            if(jidu.equals("第一季度")){
                starttime="2015-01-01";
                endtime="2015-04-01";
            }else if(jidu.equals("第二季度")){
                starttime="2015-04-01";
                endtime="2015-07-01";
            }else if(jidu.equals("第三季度")){
                starttime="2015-07-01";
                endtime="2015-9-31";
            }else if(jidu.equals("第四季度")){
                starttime="2015-10-01";
                endtime="2016-01-01";
            }
        }else if(year.equals("2016年")){
            if(jidu.equals("第一季度")){
                starttime="2016-01-01";
                endtime="2016-04-01";
            }else if(jidu.equals("第二季度")){
                starttime="2016-04-01";
                endtime="2016-07-01";
            }else if(jidu.equals("第三季度")){
                starttime="2016-07-01";
                endtime="2016-10-01";
            }else if(jidu.equals("第四季度")){
                starttime="2016-10-01";
                endtime="2017-01-01";
            }
        }
        rangeListView.setVisibility(View.GONE);
        yejiText.setText("暂无业绩");
        rankId.setText("无排名");
        UserController.getInstance().RankSearch(FhRecordSearchActivity.this,starttime,endtime);
    }
    public  void getTimew(String year,String jidu,String month){
        if(year.equals("2014年")){
            if(jidu.equals("第一季度")){
                if(month.equals("1月份")){
                    starttime="2014-01-01";
                    endtime="2014-02-01";
                }else if(month.equals("2月份")){
                    starttime="2014-02-01";
                    endtime="2014-03-01";
                }else if(month.equals("3月份")){
                    starttime="2014-03-01";
                    endtime="2014-04-01";
                }
            }else if(jidu.equals("第二季度")){
                if(month.equals("4月份")){
                    starttime="2014-04-01";
                    endtime="2014-05-01";
                }else if(month.equals("5月份")){
                    starttime="2014-05-01";
                    endtime="2014-06-01";
                }else if(month.equals("6月份")){
                    starttime="2014-06-01";
                    endtime="2014-07-01";
                }
            }else if(jidu.equals("第三季度")){
                if(month.equals("7月份")){
                    starttime="2014-07-01";
                    endtime="2014-08-01";
                }else if(month.equals("8月份")){
                    starttime="2014-08-01";
                    endtime="2014-09-01";
                }else if(month.equals("9月份")){
                    starttime="2014-09-01";
                    endtime="2014-10-01";
                }
            }else if(jidu.equals("第四季度")){
                if(month.equals("10月份")){
                    starttime="2014-10-01";
                    endtime="2014-11-01";
                }else if(month.equals("11月份")){
                    starttime="2014-11-01";
                    endtime="2014-12-01";
                }else if(month.equals("12月份")){
                    starttime="2014-12-01";
                    endtime="2015-01-01";
                }
            }
        }else if(year.equals("2015年")){
            if(jidu.equals("第一季度")){
                if(month.equals("1月份")){
                    starttime="2015-01-01";
                    endtime="2015-02-01";
                }else if(month.equals("2月份")){
                    starttime="2015-02-01";
                    endtime="2015-03-01";
                }else if(month.equals("3月份")){
                    starttime="2015-03-01";
                    endtime="2015-04-01";
                }
            }else if(jidu.equals("第二季度")){
                if(month.equals("4月份")){
                    starttime="2015-04-01";
                    endtime="2015-05-01";
                }else if(month.equals("5月份")){
                    starttime="2015-05-01";
                    endtime="2015-06-01";
                }else if(month.equals("6月份")){
                    starttime="2015-06-01";
                    endtime="2015-07-01";
                }
            }else if(jidu.equals("第三季度")){
                if(month.equals("7月份")){
                    starttime="2015-07-01";
                    endtime="2015-08-01";
                }else if(month.equals("8月份")){
                    starttime="2015-08-01";
                    endtime="2015-09-01";
                }else if(month.equals("9月份")){
                    starttime="2015-09-01";
                    endtime="2015-10-01";
                }
            }else if(jidu.equals("第四季度")){
                if(month.equals("10月份")){
                    starttime="2015-10-01";
                    endtime="2015-11-01";
                }else if(month.equals("11月份")){
                    starttime="2015-11-01";
                    endtime="2015-12-01";
                }else if(month.equals("12月份")){
                    starttime="2015-12-01";
                    endtime="2016-01-01";
                }
            }
        }else if(year.equals("2016年")) {
            if (jidu.equals("第一季度")) {
                if (month.equals("1月份")) {
                    starttime = "2016-01-01";
                    endtime = "2016-02-01";
                } else if (month.equals("2月份")) {
                    starttime = "2016-02-01";
                    endtime = "2016-03-01";
                } else if (month.equals("3月份")) {
                    starttime = "2016-03-01";
                    endtime = "2016-04-01";
                }
            } else if (jidu.equals("第二季度")) {
                if (month.equals("4月份")) {
                    starttime = "2016-04-01";
                    endtime = "2016-05-01";
                } else if (month.equals("5月份")) {
                    starttime = "2016-05-01";
                    endtime = "2016-06-01";
                } else if (month.equals("6月份")) {
                    starttime = "2016-06-01";
                    endtime = "2016-07-01";
                }
            } else if (jidu.equals("第三季度")) {
                if (month.equals("7月份")) {
                    starttime = "2016-07-01";
                    endtime = "2016-08-01";
                } else if (month.equals("8月份")) {
                    starttime = "2016-08-01";
                    endtime = "2016-09-01";
                } else if (month.equals("9月份")) {
                    starttime = "2016-09-01";
                    endtime = "2016-10-01";
                }
            } else if (jidu.equals("第四季度")) {
                if (month.equals("10月份")) {
                    starttime = "2016-10-01";
                    endtime = "2016-11-01";
                } else if (month.equals("11月份")) {
                    starttime = "2016-11-01";
                    endtime = "2016-12-01";
                } else if (month.equals("12月份")) {
                    starttime = "2016-12-01";
                    endtime = "2017-01-01";
                }
            }
        }
        rangeListView.setVisibility(View.GONE);
        yejiText.setText("暂无业绩");
        rankId.setText("无排名");
        UserController.getInstance().RankSearch(FhRecordSearchActivity.this,starttime,endtime);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if(flag==HttpConfig.rankBetween.getType()){
            List<RankbackDataModel> model =(List<RankbackDataModel>)result;
            List<RankbackDataModel> showmodel=new ArrayList<>();
            if(model.size()>10){
                for(int i=0;i<10;i++){
                    showmodel.add(model.get(i));
                }
                plannerRankAdapter.setRes(showmodel);
            }else{
                plannerRankAdapter.setRes(model);
            }
            if(model.size()>0){
                NoDataText.setVisibility(View.GONE);
                rangeListView.setVisibility(View.VISIBLE);
            }else{
                NoDataText.setVisibility(View.VISIBLE);
                rangeListView.setVisibility(View.GONE);
            }
            for(RankbackDataModel m:model){
                if(String.valueOf(m.getPlannerUid()).equals(String.valueOf(UserPreference.getUid()))){
//                    yejiText.setText(m.getAnnualised()+"");
                    double youIn = Double.parseDouble(String.valueOf(m.getAnnualised()));
                    double l = 10000;
                    DecimalFormat df = new DecimalFormat("0.00");
                    yejiText.setText(df.format(youIn / l) + "万元");
                    rankId.setText(m.getSort()+"");
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchTextView:
                if (listViewyear.getVisibility() == View.VISIBLE) {
                    bottomLayout.setBackgroundColor(getResources().getColor(R.color.white));
                    listViewyear.setVisibility(View.GONE);
                    listViewjidu.setVisibility(View.GONE);
                    topLayout.setVisibility(View.GONE);
                    listViewmonth.setVisibility(View.GONE);
                    titleRightIV.setBackgroundResource(R.drawable.hapess);
                } else {
                    topLayout.setVisibility(View.VISIBLE);
                    bottomLayout.setBackgroundColor(getResources().getColor(R.color.sckground));
                    listViewyear.setVisibility(View.VISIBLE);
                    titleRightIV.setBackgroundResource(R.drawable.hapessup);
                }
                break;
            case R.id.backImageView:
                finish();
                break;
            case R.id.backimagelayout:
                finish();
                break;
        }

    }

}
