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
import com.fhzc.app.android.android.ui.view.widget.CircleImageView;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.configer.enums.CustomerTypeNew;
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
public class PersonInfoNewActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {

    View viewlayout;
    UserModel model;
    @Bind(R.id.personInfoToolBar)
    CommonToolBar personInfoToolBar;
    @Bind(R.id.person_info_exit)
    TextView personInfoExit;
    @Bind(R.id.person_info_avatar)
    CircleImageView personInfoAvatar;
    @Bind(R.id.person_info_name)
    TextView personInfoName;
    @Bind(R.id.person_info_level)
    TextView personInfoLevel;
    @Bind(R.id.person_info_top_layout)
    RelativeLayout personInfoTopLayout;
    @Bind(R.id.messageLayout)
    RelativeLayout messageLayout;
    @Bind(R.id.systemMessageLay)
    RelativeLayout systemMessageLay;
    @Bind(R.id.myPlannerLayout)
    LinearLayout myPlannerLayout;
    @Bind(R.id.myCollectionLayout)
    LinearLayout myCollectionLayout;
    @Bind(R.id.myCnnectionLayout)
    LinearLayout myCnnectionLayout;
    @Bind(R.id.myAboutLayout)
    LinearLayout myAboutLayout;
    @Bind(R.id.myReserveLayout)
    LinearLayout myReserveLayout;
    @Bind(R.id.levelImageView)
    ImageView levelImageView;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!UserPreference.isCustomer()) {
            myPlannerLayout.setVisibility(View.GONE);
        }else{
            myPlannerLayout.setVisibility(View.VISIBLE);
        }
    }

    public void onEventMainThread(IMEvent event) {
        if (null != event) {
            personInfoToolBar.setRedImage(new MessageDao().getUnReadCount());
        }
    }

    @Override
    protected void initEvent() {
        personInfoToolBar.setClickRightListener(this);
        personInfoExit.setOnClickListener(this);
        messageLayout.setOnClickListener(this);
        systemMessageLay.setOnClickListener(this);
        myCnnectionLayout.setOnClickListener(this);
        myAboutLayout.setOnClickListener(this);
        myReserveLayout.setOnClickListener(this);
        myPlannerLayout.setOnClickListener(this);
        myCollectionLayout.setOnClickListener(this);
        personInfoTopLayout.setOnClickListener(this);
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
        return R.layout.activity_person_info_new;
    }

    private void setData(UserModel model) {
        CustomerTypeNew type ;
        if (UserPreference.isCustomer()) {
            type = CustomerTypeNew.getCustomerByType(model.getLevel());
            personInfoLevel.setText(model.getLevel());
            levelImageView.setVisibility(View.VISIBLE);
        } else {
            levelImageView.setVisibility(View.GONE);
            type = CustomerTypeNew.Planner;
            personInfoLevel.setText(model.getPosition());
        }

        personInfoTopLayout.setBackgroundResource(type.getPersoninfoBg());
        levelImageView.setBackgroundResource(type.getClientListBg());
        ImageLoader.getInstance(this, R.drawable.customer_bg_view).displayImage(model.getAvatar(), personInfoAvatar);
        personInfoName.setText(model.getRealname());
        personInfoLevel.setText("登录ID: "+model.getMobile());
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
                    IntentTools.startLogin(PersonInfoNewActivity.this);
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
        switch (v.getId()){
            case R.id.person_info_exit:
                UserController.getInstance().Logout(this);
                break;
            case R.id.messageLayout:
                IntentTools.startChatList(PersonInfoNewActivity.this);
                break;
            case R.id.systemMessageLay:
                IntentTools.startSystemMessage(PersonInfoNewActivity.this);
                break;
            case R.id.myPlannerLayout:
                IntentTools.startAccountionDetailActivity(PersonInfoNewActivity.this, true);
                break;
            case R.id.myCollectionLayout:
                IntentTools.startCollectionActivity(PersonInfoNewActivity.this, "我的关注");
                break;
            case R.id.myCnnectionLayout:
                IntentTools.startContactUs(PersonInfoNewActivity.this);
                break;
            case R.id.myAboutLayout:
                IntentTools.startAboutActivity(PersonInfoNewActivity.this);
                break;
            case R.id.myReserveLayout:
                IntentTools.startshengActivity(PersonInfoNewActivity.this);
                break;
            case R.id.person_info_top_layout:
                IntentTools.startPersonInformationActivity(PersonInfoNewActivity.this);
                break;
        }

    }

    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(this);
    }

}
