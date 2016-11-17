package com.fhzc.app.android.android.ui.activity.systemservice;

import android.app.Dialog;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.framwork.cach.preferce.PreferceManager;
import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.StringTools;
import com.base.view.view.dialog.factory.DialogFacory;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CountDownTimeButton;
import com.fhzc.app.android.android.ui.view.widget.PhoneTextIdentify;
import com.fhzc.app.android.android.ui.view.widget.XCDropDownListView;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.LoginController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.LoginModel;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.CommonUtil;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 登录
 * Created by lenovo on 2016/7/11.
 */
public class FhLoginActivity extends BaseActivity implements View.OnClickListener {


    String identifyNum;
    String phoneNum;
    String vercode;
    @Bind(R.id.loginBackImage)
    ImageView loginBackImage;
    @Bind(R.id.loginTitleBack)
    ImageView loginTitleBack;
    @Bind(R.id.loginTitleText)
    TextView loginTitleText;
    @Bind(R.id.loginContentText)
    TextView loginContentText;
    @Bind(R.id.topLay)
    RelativeLayout topLay;
    @Bind(R.id.credentialTypeText)
    TextView credentialTypeText;
    @Bind(R.id.drop_down_list_view)
    XCDropDownListView dropDownListView;
    @Bind(R.id.credentialNumberType)
    TextView credentialNumberType;
    @Bind(R.id.identifyNumEditText)
    EditText identifyNumEditText;
    @Bind(R.id.phoneText)
    TextView phoneText;
    @Bind(R.id.phoneNumEditText)
    EditText phoneNumEditText;
    @Bind(R.id.vercodeText)
    TextView vercodeText;
    @Bind(R.id.newforgetPassText)
    CountDownTimeButton newforgetPassText;
    @Bind(R.id.vercodeEditText)
    EditText vercodeEditText;
    @Bind(R.id.newloginTextView)
    TextView newloginTextView;
    @Bind(R.id.newloginLayout)
    LinearLayout newloginLayout;
    @Bind(R.id.zhangText)
    TextView zhangText;
    @Bind(R.id.userNameEditText)
    EditText userNameEditText;
    @Bind(R.id.miText)
    TextView miText;
    @Bind(R.id.mimablankView)
    View mimablankView;
    @Bind(R.id.maTextView)
    TextView maTextView;
    @Bind(R.id.forgetPassText)
    TextView forgetPassText;
    @Bind(R.id.pswEditText)
    EditText pswEditText;
    @Bind(R.id.loginTextView)
    TextView loginTextView;
    @Bind(R.id.loginLayout)
    LinearLayout loginLayout;
    @Bind(R.id.phoneLoginText)
    TextView phoneLoginText;
    @Bind(R.id.vercodeLoginText)
    TextView vercodeLoginText;
    @Bind(R.id.servicephone)
    TextView servicephone;
    @Bind(R.id.phoneicon)
    ImageView phoneicon;
    @Bind(R.id.servicephonenumber)
    TextView servicephonenumber;
    @Bind(R.id.bottomLay)
    RelativeLayout bottomLay;

    private boolean TimeCodeType = false;
    private Dialog dialog;
    private boolean notFinish;
    private Bitmap btp;
    int flagNumber=-1;
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!StringTools.isNullOrEmpty(PreferceManager.getInsance().getValueBYkey(Constant.LOGIN_TIME))) {
            IntentTools.startMain(this);
            finish();
            return;
        }
//        boolean noAnim = getIntent().getBooleanExtra("noAnim", false);
//        if (!noAnim) {
//            IntentTools.startLoadingAnim(this, true);
//        }

        initBar();
        initOldLogin();
//        new DecodeBitmapTask().execute();

        AssetManager asm = getAssets();
        try {
            InputStream is = asm.open("login_bg.png");
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            btp = BitmapFactory.decodeStream(is, null, opt);
            loginBackImage.setImageBitmap(btp);

        } catch (Exception e) {

        }
        if (!StringTools.isNullOrEmpty(UserPreference.getLastLogin()))
//            userNameEditText.setText(UserPreference.getLastLogin());
        dialog = DialogFacory.getInstance().createRunDialog(this, "请稍后");
        forgetPassText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        forgetPassText.setTextColor(getResources().getColor(R.color.appColorBlue));
        phoneLoginText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        phoneLoginText.setTextColor(getResources().getColor(R.color.numberphoneColor));
        vercodeLoginText.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        vercodeLoginText.setTextColor(getResources().getColor(R.color.numberphoneColor));
    }

    @Override
    protected void initEvent() {

        phoneLoginText.setOnClickListener(this);
        vercodeLoginText.setOnClickListener(this);
        loginTextView.setOnClickListener(this);
        newloginTextView.setOnClickListener(this);
        bottomLay.setOnClickListener(this);
        forgetPassText.setOnClickListener(this);
        newforgetPassText.setStartCountLisener(new CountDownTimeButton.StartCountLisener() {
            @Override
            public boolean startCount() {
                String code = identifyNumEditText.getText().toString();
                String mobile = phoneNumEditText.getText().toString();

                if (StringTools.isNullOrEmpty(code)) {
                    ToastTool.show(R.string.login_code_null);
                    return false;
                }
                if (StringTools.isNullOrEmpty(mobile)) {
                    ToastTool.show(R.string.login_phone_null);
                    return false;
                }
                if (!PhoneTextIdentify.isMobileNO(mobile)) {
                    ToastTool.show("手机号码格式不正确");
                    return false;
                }
                newforgetPassText.setBackgroundColor(getResources().getColor(R.color.selected_line_color));
                LoginController.getInstance().getSms(FhLoginActivity.this, mobile);
                return true;
            }
        });
        newforgetPassText.setCycle(60);

    }

    @Override
    protected void initData() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("身份证");
        list.add("护照");
        list.add("港澳台通行证");
        list.add("台胞证");
        list.add("香港身份证");
        list.add("营业执照");
        list.add("基金会法人登记证书");
        dropDownListView.setItemsData(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_fh;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phoneLoginText:
                switchToOldLogin();
                break;
            case R.id.vercodeLoginText:
                newforgetPassText.setTextColor(getResources().getColor(R.color.white));
                newforgetPassText.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
                newforgetPassText.setCanRun();
                initNewLogin();
                notFinish = true;
                break;
            case R.id.loginTextView:
                doOldLogin();
//                CommonUtil.getNetIp();

                break;
            case R.id.newloginTextView:
                doNewLogin();
//                Logger.e("fuwen  localIp="+CommonUtil.getLocalIpAddress());
                break;

            case R.id.bottomLay:
                CommonUtil.call(FhLoginActivity.this, servicephonenumber.getText().toString());
                break;
            case R.id.forgetPassText:
                IntentTools.startForgetPass(this);
                break;

        }

    }

    private void switchToOldLogin() {
        newforgetPassText.stopCount();
        initOldLogin();
        notFinish = false;
    }


    private void doNewLogin() {
        identifyNum = identifyNumEditText.getText().toString();
        phoneNum = phoneNumEditText.getText().toString();
        vercode = vercodeEditText.getText().toString();
        if (StringTools.isNullOrEmpty(identifyNum)) {
            ToastTool.show("证件号码不能为空");
            return;
        }
        if (StringTools.isNullOrEmpty(phoneNum)) {
            ToastTool.show("手机号码不能为空");
            return;
        }
//        if (PhoneTextIdentify.isMobileNO(phoneNum)) {
//            ToastTool.show("手机号码格式不正确");
//            return;
//        }
        if (StringTools.isNullOrEmpty(vercode)) {
            ToastTool.show("验证码不能为空");
            return;
        }

        if(dropDownListView.getText().equals("身份证")){
            flagNumber=1;
        }else if(dropDownListView.getText().equals("护照")){
            flagNumber=2;
        }else if(dropDownListView.getText().equals("港澳台通行证")){
            flagNumber=3;
        }else if(dropDownListView.getText().equals("台胞证")){
            flagNumber=4;
        }else if(dropDownListView.getText().equals("香港身份证")){
            flagNumber=5;
        }else if(dropDownListView.getText().equals("营业执照")){
            flagNumber=6;
        }else if(dropDownListView.getText().equals("基金会法人登记证书")){
            flagNumber=7;
        }
        LoginController.getInstance().loginNewUser(this, flagNumber, identifyNum, phoneNum, vercode);
    }

    private void doOldLogin() {
//        IntentTools.startMain(FhLoginActivity.this);
//        finish();
        String userName = userNameEditText.getText().toString();
        String passWord = pswEditText.getText().toString();
        if (StringTools.isNullOrEmpty(userName)) {
            ToastTool.show("用户名不能为空");
            return;
        }
        if (StringTools.isNullOrEmpty(passWord)) {
            ToastTool.show("密码不能为空");
            return;
        }

        LoginController.getInstance().loginOldUser(this, userName, passWord);
    }

    private void initNewLogin() {
        phoneLoginText.setVisibility(View.VISIBLE);
        vercodeLoginText.setVisibility(View.GONE);
        loginLayout.setVisibility(View.GONE);
        newloginLayout.setVisibility(View.VISIBLE);
    }

    private void initOldLogin() {
        phoneLoginText.setVisibility(View.GONE);
        vercodeLoginText.setVisibility(View.VISIBLE);
        loginLayout.setVisibility(View.VISIBLE);
        newloginLayout.setVisibility(View.GONE);
    }


    @Override
    public void
    onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.loginOldUser.getType()) {
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    LoginModel model = (LoginModel) result;
//                    if(model.getIsFirst()==0){
//                        ToastTool.show("您是首次登录,请用手机验证登录!");
//                    }else if(model.getIsFirst()==1){
                        ToastTool.show("登录成功");
                        UserPreference.saveLastLogin(userNameEditText.getText().toString());
                        UserPreference.setLoginUser(model);
                        PreferceManager.getInsance().saveValueBYkey(Constant.LOGIN_TIME, Constant.LOGIN_TIME);
                        IntentTools.startMain(FhLoginActivity.this, false);
                        finish();
//                    }
                } else {
                    if (null != dialog && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    ToastTool.show(object.getString("message"));
                }

            } catch (Exception e) {
                if (null != dialog && dialog.isShowing()) {
                    dialog.dismiss();
                }
                ToastTool.show("登录失败");
            }

        }else if (flag == HttpConfig.loginNewUser.getType()) {
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
//                    int isFirst = object.optJSONObject("map").optInt("isFirst");
//                    if(isFirst ==0 ){
                        IntentTools.startSetPsw(FhLoginActivity.this, flagNumber, identifyNum, phoneNum);
//                    }else if(isFirst == 1){
//                        ToastTool.show("您已不是首次登录，请使用账号密码登录");
//                        switchToOldLogin();
//                    }
                } else {
                    ToastTool.show(object.getString("message"));
                }

            } catch (Exception e) {
                ToastTool.show("登录失败");
            }
        }else if (flag == HttpConfig.getSms.getType()) {
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") != 200) {
                    ToastTool.show(object.getString("message"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onConnectStart() {
        super.onConnectStart();
        if (null != dialog && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onConnectEnd() {
        super.onConnectEnd();
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
        ToastTool.show("登录失败");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        newforgetPassText.stopCount();
        if (btp != null && !btp.isRecycled()) {
            btp.recycle(); //回收图片所占的内存
            System.gc();  //提醒系统及时回收
        }
    }


    @Override
    public void onBackPressed() {
        if (notFinish) {
            switchToOldLogin();
        } else {
            super.onBackPressed();
        }
    }
}
