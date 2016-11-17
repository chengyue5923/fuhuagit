package com.fhzc.app.android.android.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.framwork.cach.preferce.PreferceManager;
import com.base.framwork.net.configer.Constans;
import com.base.platform.utils.android.ToastTool;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.base.FuhuaApplication;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.configer.enums.CustomerType;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.UserModel;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.ImageLoader;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/7/21.
 */
public class PersonInfoActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {


    @Bind(R.id.personInfoToolBar)
    CommonToolBar personInfoToolBar;
    @Bind(R.id.person_info_exit)
    TextView personInfoExit;
    @Bind(R.id.person_info_avatar)
    ImageView personInfoAvatar;
    @Bind(R.id.person_info_name)
    TextView personInfoName;
    @Bind(R.id.person_info_level)
    TextView personInfoLevel;
    @Bind(R.id.person_info_right_arrow)
    ImageView personInfoRightArrow;
    @Bind(R.id.messageLayout)
    RelativeLayout messageLayout;
    @Bind(R.id.systemMessageLay)
    RelativeLayout systemMessageLay;
    @Bind(R.id.person_info_top_layout)
    RelativeLayout personInfoTopLayout;
    @Bind(R.id.changePassLayout)
    LinearLayout changePassLayout;
    @Bind(R.id.riskAssessmentLayout)
    LinearLayout riskAssessmentLayout;
    @Bind(R.id.contactUsLayout)
    LinearLayout contactUsLayout;
    @Bind(R.id.aboutAppLayout)
    LinearLayout aboutAppLayout;
    @Bind(R.id.personInfo)
    TextView personInfo;
    @Bind(R.id.viewlayout)
    View viewlayout;
    UserModel model;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if(!UserPreference.isCustomer()){
            riskAssessmentLayout.setVisibility(View.GONE);
            findViewById(R.id.view_fengxiangpingce).setVisibility(View.GONE);
            riskAssessmentLayout.setVisibility(View.GONE);
        }
    }
    public void onEventMainThread(IMEvent event) {
        if (null != event) {
            personInfoToolBar.setRedImage(new MessageDao().getUnReadCount());

        }
    }
    @Override
    protected void initEvent() {
        messageLayout.setOnClickListener(this);
        systemMessageLay.setOnClickListener(this);
        personInfoExit.setOnClickListener(this);
        riskAssessmentLayout.setOnClickListener(this);
        contactUsLayout.setOnClickListener(this);
        aboutAppLayout.setOnClickListener(this);
        personInfoTopLayout.setOnClickListener(this);
        changePassLayout.setOnClickListener(this);
        personInfoToolBar.setClickRightListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initData() {
        personInfoToolBar.setRedImage(new MessageDao().getUnReadCount());
        UserController.getInstance().userInfo(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    private void setData(UserModel model) {
        CustomerType type;
        if (UserPreference.isCustomer()) {
            type = CustomerType.getCustomerByType(model.getLevel());
            personInfoLevel.setText(model.getLevel());
        } else {
            type = CustomerType.Planner;
            personInfoLevel.setText(model.getPosition());
        }
        personInfoName.setTextColor(getResources().getColor(type.getTextColor()));
        personInfoLevel.setTextColor(getResources().getColor(type.getTextColor()));
        personInfo.setTextColor(getResources().getColor(type.getTextColor()));
        personInfoName.setText(model.getRealname());
        personInfoTopLayout.setBackgroundResource(type.getPersoninfoBg());
//        personInfoAvatar.setImageResource(type.getAvatarBg());
            ImageLoader.getInstance(this, R.drawable.selected_peroninfo).displayImage(model.getAvatar(), personInfoAvatar);
    }
    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.userInfo.getType()) {
            model = (UserModel) result;
            UserPreference.setUser(model);
            setData(model);
        }
        if(flag==HttpConfig.logout.getType()){
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    IntentTools.startLogin(PersonInfoActivity.this);
//                new BaseDao().dropAllTable();
                    PreferceManager.getInsance().saveValueBYkey("version", "");
                    PreferceManager.getInsance().saveValueBYkey(Constant.LOGIN_TIME, "");
                    PreferceManager.getInsance().saveValueBYkey(Constans.jseesion, "");
                    FuhuaApplication.getInstance().clearActivities();
                    finish();
                } else {
                    ToastTool.show(object.getString("message"));
                }

            } catch (Exception e) {
                if (null != dialog && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastTool.show("退出失败");
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.messageLayout:
                IntentTools.startChatList(PersonInfoActivity.this);
                break;
            case R.id.systemMessageLay:
                IntentTools.startSystemMessage(PersonInfoActivity.this);
                break;
            case R.id.changePassLayout:
                IntentTools.startChangePassActivity(PersonInfoActivity.this);
                break;
            case R.id.person_info_top_layout:
                IntentTools.startPersonInformationActivity(PersonInfoActivity.this);
                break;
            case R.id.aboutAppLayout:
                IntentTools.startAboutActivity(PersonInfoActivity.this);
                break;
            case R.id.riskAssessmentLayout:
                IntentTools.startRiskAssessment(PersonInfoActivity.this);
                break;
            case R.id.contactUsLayout:
                IntentTools.startContactUs(PersonInfoActivity.this);
                break;
            case R.id.person_info_exit:
                UserController.getInstance().Logout(this);
                break;
        }
    }

    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(this);
    }


}
