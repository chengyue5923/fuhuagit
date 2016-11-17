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
 * 历年排名
 * Created by yanbo on 2016/7/21.
 */
public class FhYearRankActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.yearToolBar)
    CommonToolBar yearToolBar;
    @Bind(R.id.yearList)
    ListView yearList;
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
        adapter = new YearAndMonthRankAdapter(this,false);
        yearList.setAdapter(adapter);
//        List<RankModel> list = new ArrayList<>();
//        list.add(new RankModel());
//        list.add(new RankModel());
//        list.add(new RankModel());
//        adapter.setRes(list);
        UserController.getInstance().RankSearch(FhYearRankActivity.this, "2016-01-01", "2016-12-31");

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_year_item;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        adapter.setRes((List<RankbackDataModel>)result);
    }

    @Override
    public void onClick(View v) {

    }


}
