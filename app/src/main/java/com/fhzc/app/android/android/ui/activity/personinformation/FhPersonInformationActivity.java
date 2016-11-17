package com.fhzc.app.android.android.ui.activity.personinformation;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.android.ToastTool;
import com.bumptech.glide.Glide;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.activity.PersonInfoActivity;
import com.fhzc.app.android.android.ui.view.widget.BottomDialog;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.configer.enums.IConstant;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.UserModel;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.CommonUtil;
import com.fhzc.app.android.utils.im.ImageLoader;

import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 个人信息
 * Created by lenovo on 2016/7/11.
 */
public class FhPersonInformationActivity extends BaseActivity implements View.OnClickListener, BottomDialog.TransferValue {


    @Bind(R.id.personDetailToolbar)
    CommonToolBar personDetailToolbar;
    @Bind(R.id.loginNameText)
    TextView loginNameText;
    @Bind(R.id.nameTextView)
    TextView nameTextView;
    @Bind(R.id.customerText)
    TextView customerText;
    @Bind(R.id.phoneTextView)
    TextView phoneTextView;
    @Bind(R.id.IDNumberText)
    TextView IDNumberText;
    @Bind(R.id.gradesTextView)
    TextView gradesTextView;
    @Bind(R.id.changeLoginLayout)
    RelativeLayout changeLoginLayout;
    @Bind(R.id.changeavarterLayout)
    RelativeLayout changeavarterLayout;
    @Bind(R.id.changePaswLayout)
    RelativeLayout changePaswLayout;
    @Bind(R.id.iconIVback)
    ImageView iconIVback;
    UserModel model;
    String picPath;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        changePaswLayout.setOnClickListener(this);
        changeavarterLayout.setOnClickListener(this);
        changeLoginLayout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        if (!UserPreference.isCustomer()) {
            findViewById(R.id.rl_rolediff).setVisibility(View.GONE);
            findViewById(R.id.rl_rolediff2).setVisibility(View.GONE);
            findViewById(R.id.line_divider).setVisibility(View.GONE);

        }
        if(UserPreference.getAvatar()!=null&&UserPreference.getAvatar().length()>1){
            ImageLoader.getInstance(this, R.drawable.selected_peroninfo).displayImage(UserPreference.getAvatar(), iconIVback);
        }
        try {
            if (!UserPreference.getName().isEmpty()) {
                nameTextView.setText(UserPreference.getName());
            }
            if (!UserPreference.getLoginName().isEmpty()) {
                loginNameText.setText(UserPreference.getLoginName());
            }
            if (!UserPreference.getPhone().isEmpty()) {
                phoneTextView.setText(CommonUtil.getPhoneNum(UserPreference.getPhone()));
//                phoneTextView.setText(UserPreference.getPhone());
            }

            if (!UserPreference.getPassport().isEmpty()) {
                IDNumberText.setText(CommonUtil.getIDNum(UserPreference.getPassport()));
//                IDNumberText.setText(UserPreference.getPassport());
            }

            if (UserPreference.isCustomer()) {
                if (!UserPreference.getCBUid().isEmpty()) {
                    customerText.setText(CommonUtil.getPhoneNum(UserPreference.getCBUid()));
//                customerText.setText(UserPreference.getCBUid());
                }
                if (!UserPreference.getLevel().isEmpty()) {
                    gradesTextView.setText(UserPreference.getLevel());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fh_person_information_activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.changeLoginLayout:
                if (UserPreference.isCustomer())
                    IntentTools.startChangeLoginNamelActivity(this, loginNameText.getText().toString());
                else
                    ToastTool.show("理财师不能修改用户名");
                break;
            case R.id.changeavarterLayout:

                FragmentManager fm = getSupportFragmentManager();
                BottomDialog bottomDialog = new BottomDialog(this, "", IConstant.PHOTOPICTURE);
                bottomDialog.show(fm, "fragment_dialog");

                break;
            case R.id.changePaswLayout:
                IntentTools.startChangePassActivity(FhPersonInformationActivity.this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == IConstant.TAKE_PHOTO) {

            Glide.with(this).load(picPath).centerCrop().into(iconIVback);

//            UserController.getInstance().changeAvarter(this, picPath);
            UserController.getInstance().upLoadFile(new upLoadFile(), new File(picPath), HttpConfig.changeAvatar);
        } else if (resultCode == Activity.RESULT_OK && requestCode == IConstant.ALBUM_PHOTO) {
            if (null != data) {
                Uri photoUri = data.getData();
                String[] pojo = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(photoUri, pojo, null, null, null);
                if (cursor != null) {
                    int columnIndex = cursor.getColumnIndexOrThrow(pojo[0]);
                    cursor.moveToFirst();
                    picPath = cursor.getString(columnIndex);
                    com.base.platform.utils.android.Logger.e("setPicPath" + picPath);
                    cursor.close();
                }
                Glide.with(this).load(picPath).centerCrop().into(iconIVback);
                UserController.getInstance().upLoadFile(new upLoadFile(), new File(picPath), HttpConfig.changeAvatar);
            }
        }
    }

    class upLoadFile implements ViewNetCallBack {

        @Override
        public void onConnectStart() {

        }

        @Override
        public void onConnectEnd() {
        }

        @Override
        public void onFail(Exception e) {
        }

        @Override
        public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") != 200) {
                    ToastTool.show(object.getString("message"));
                } else {
                    ToastTool.show("更换头像成功");
                    UserPreference.setAvatar(object.getString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
//        if (flag == HttpConfig.userInfo.getType()) {
//            model = (UserModel) result;
//            Logger.e("modelpp" + model.toString());
//            UserPreference.setUser(model);
//        }
    }

    @Override
    public void transferValue(String value) {
        picPath = value;
    }
}
