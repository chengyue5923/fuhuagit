package com.fhzc.app.android.android.ui.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.models.AchievementModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @deprecated
 * @param context
 */
public class MyWorkActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.monthAchievement)
    TextView monthAchievement;
    @Bind(R.id.yearAchievement)
    TextView yearAchievement;
    @Bind(R.id.monthRankLayout)
    LinearLayout monthRankLayout;
    @Bind(R.id.quarterRankLayout)
    LinearLayout quarterRankLayout;
    @Bind(R.id.yearRankLayout)
    LinearLayout yearRankLayout;
    @Bind(R.id.achievementQueryLayout)
    LinearLayout achievementQueryLayout;
    @Bind(R.id.monthMyRank)
    TextView monthMyRank;
    @Bind(R.id.monthDeptMyRank)
    TextView monthDeptMyRank;
    @Bind(R.id.yearMyRank)
    TextView yearMyRank;
    @Bind(R.id.yearDeptMyRank)
    TextView yearDeptMyRank;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        monthRankLayout.setOnClickListener(this);
        quarterRankLayout.setOnClickListener(this);
        yearRankLayout.setOnClickListener(this);
        achievementQueryLayout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        UserController.getInstance().achievement(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_work;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        AchievementModel model = (AchievementModel) result;
        monthAchievement.setText(String.valueOf(model.getMonthSale() / 10000));
        yearAchievement.setText(String.valueOf(model.getYearSale() / 10000));
        monthMyRank.setText(model.getMonthMyRank()==0?"暂无排名":"第"+model.getMonthMyRank()+"名");
        monthDeptMyRank.setText(model.getMonthMyRank()==0?"暂无排名":"第"+model.getMonthDeptMyRank()+"名");
        yearMyRank.setText(model.getMonthMyRank()==0?"暂无排名":"第"+model.getYearMyRank()+"名");
        yearDeptMyRank.setText(model.getMonthMyRank()==0?"暂无排名":"第"+model.getYearDeptMyRank()+"名");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.monthRankLayout:
                IntentTools.startMonthActivity(MyWorkActivity.this, "历月排名");
                break;
            case R.id.quarterRankLayout:
                IntentTools.startMonthActivity(MyWorkActivity.this, "季度排名");
                break;
            case R.id.yearRankLayout:
                IntentTools.startYearActivity(MyWorkActivity.this);
                break;
            case R.id.achievementQueryLayout:
                IntentTools.startRecordSearchActivity(MyWorkActivity.this);
                break;
        }
    }


}
