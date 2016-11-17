package com.fhzc.app.android.android.ui.activity.personinformation;

import android.view.View;
import android.widget.ListView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.YearAndMonthRankAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.models.RankbackDataModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 历月排名
 * Created by yanbo on 2016/7/21.
 */
public class FhMonthRankActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.monthToolBar)
    CommonToolBar monthToolBar;
    @Bind(R.id.monthList)
    ListView monthList;
    private YearAndMonthRankAdapter adapter;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        monthToolBar.setTitle(getIntent().getStringExtra("title"));
        adapter = new YearAndMonthRankAdapter(this,true);
        monthList.setAdapter(adapter);
//        List<RankModel> list = new ArrayList<>();
//        list.add(new RankModel());
//        list.add(new RankModel());
//        list.add(new RankModel());
//        UserController.getInstance().mouthRank(this);
        if (getIntent().getStringExtra("title").equals("历月排名")){
            UserController.getInstance().RankSearch(FhMonthRankActivity.this, "2016-01-01",  "2016-12-31");
        }else if(getIntent().getStringExtra("title").equals("季度排名")){
            UserController.getInstance().RankSearch(FhMonthRankActivity.this, "2016-01-01",  "2016-12-31");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_month_item;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        adapter.setRes((List<RankbackDataModel>) result);
    }

    @Override
    public void onClick(View v) {

    }
}
