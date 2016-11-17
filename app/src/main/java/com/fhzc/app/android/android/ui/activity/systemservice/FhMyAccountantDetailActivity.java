package com.fhzc.app.android.android.ui.activity.systemservice;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.PlannerDetailModel;
import com.fhzc.app.android.utils.IntentTool;
import com.fhzc.app.android.utils.im.CommonUtil;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 我的理财师详情
 * Created by lenovo on 2016/7/11.
 */
public class FhMyAccountantDetailActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.accountDetailToolbar)
    CommonToolBar accountDetailToolbar;

    @Bind(R.id.person_info_avatar)
    ImageView person_info_avatar;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    @Bind(R.id.plannerName)
    TextView plannerName;
    @Bind(R.id.plannerPhone)
    TextView plannerPhone;
    @Bind(R.id.contactPlanner)
    RelativeLayout contactPlanner;
    @Bind(R.id.callLayout)
    RelativeLayout callLayout;
    @Bind(R.id.plannerLevel)
    TextView plannerLevel;
    @Bind(R.id.department)
    TextView department;
    boolean showContactPlanner = true;
    @Override
    protected void initView() {

        ButterKnife.bind(this);
        initBar();
        if (CommonUtil.isUpperKK()) {
            View view = new View(this);
            view.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonUtil.getStatusBarHeight(this));
            view.setLayoutParams(layoutParams);
            rootLayout.addView(view, 0);
        }
        accountDetailToolbar.setBottomLineVisible(View.GONE);
    }

    @Override
    protected void initEvent() {
        callLayout.setOnClickListener(this);
        contactPlanner.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        showContactPlanner = getIntent().getBooleanExtra("showContactPlanner",true);
        contactPlanner.setVisibility(showContactPlanner?View.VISIBLE:View.GONE);
        accountDetailToolbar.setTitle(UserPreference.getPlannerName());
        plannerName.setText(UserPreference.getPlannerName());
        if (UserPreference.getPlannerId() != 0) {
            UserController.getInstance().plannerDetail(this, UserPreference.getPlannerId());
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_detail;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.callLayout:
                CommonUtil.call(FhMyAccountantDetailActivity.this, plannerPhone.getText().toString());
                break;
            case R.id.contactPlanner:
                if (UserPreference.getPlannerUid() == 0) {
                    ToastTool.show("您没有理财师");
                    return;
                }
                IntentTool.chat(FhMyAccountantDetailActivity.this, null, UserPreference.getPlannerUid());
                break;
        }
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.plannerDetail.getType()) {
            PlannerDetailModel model = (PlannerDetailModel) result;
            setData(model);
        }
    }

    private void setData(PlannerDetailModel model) {
        ImageLoader.getInstance(this, R.drawable.chat_avatar_planner).displayImage(model.getAvatar(), person_info_avatar);
        plannerName.setText(model.getName());
        plannerPhone.setText(model.getMobile());
        department.setText(model.getDepartmentId());
        plannerLevel.setText(model.getJobTitleCn());


    }

}
