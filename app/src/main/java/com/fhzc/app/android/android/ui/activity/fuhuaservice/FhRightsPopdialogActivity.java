package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.widget.timepicker.TimePickerView;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.RightController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.RightModel;

import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import butterknife.ButterKnife;

/**
 * 权益确认dialog
 * Created by lenovo on 2016/7/11.
 */
public class FhRightsPopdialogActivity extends Activity implements View.OnClickListener,ViewNetCallBack {


    TextView sureSignUpTextView;
    ImageView rightBacksImageView;
    TextView condentionText;
    RelativeLayout condentionLayout;
    TextView knowText;
    RelativeLayout knowLayout;
    TextView busnessText;
    RelativeLayout busnessLayout;
    TextView pointText;
    RelativeLayout pointLayout;
    TextView timeText;
    RelativeLayout timeLayout;
    TextView sureButtonText;

    TimePickerView pvTime;
    RightModel rightModel;
    private int resultCode = 0;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_forother_window);
        initView();
        initData();
    }

    protected void initView() {
        condentionText=(TextView)findViewById(R.id.condentionText);
        pointText=(TextView)findViewById(R.id.pointText);
        knowText=(TextView)findViewById(R.id.knowText);
        busnessText=(TextView)findViewById(R.id.busnessText);
        timeText=(TextView)findViewById(R.id.timeText);
        sureButtonText=(TextView)findViewById(R.id.sureButtonText);
        rightBacksImageView=(ImageView)findViewById(R.id.rightBacksImageView);
        timeLayout=(RelativeLayout)findViewById(R.id.timeLayout);
        ButterKnife.bind(this);
        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        pvTime.setTime(new Date());
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        sureButtonText.setOnClickListener(this);
        rightBacksImageView.setOnClickListener(this);
        timeLayout.setOnClickListener(this);
        // 时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                timeText.setText(getTime(date));
                time=timeText.getText().toString();
            }
        });
        Window window = getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        //设置窗口的大小及透明度
        layoutParams.width = WindowManager.LayoutParams.FILL_PARENT;
        layoutParams.height = layoutParams.WRAP_CONTENT;
        window.setAttributes(layoutParams);



    }
    public static String getTime(Date date)
    {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        return format.format(date);
    }
    protected void initData() {
        rightModel=(RightModel)getIntent().getSerializableExtra("rightmodel");
//        condentionText.setText(rightModel.getLevel() + "");
        if(String.valueOf(rightModel.getLevel())!=null){
            if(rightModel.getLevel()==0){
                condentionText.setText("投资人");
            }else if(rightModel.getLevel()==1){
                condentionText.setText("投资人");
            }else if(rightModel.getLevel()==2){
                condentionText.setText("准会员");
            }else if(rightModel.getLevel()==3){
                condentionText.setText("银卡");
            }else if(rightModel.getLevel()==4){
                condentionText.setText("金卡");
            }else if(rightModel.getLevel()==5){
                condentionText.setText("黑金卡");
            }
        }
        pointText.setText(rightModel.getSpendScore()+"");
        knowText.setText(rightModel.getNotice());
        busnessText.setText(rightModel.getSupply());
        time=getTime(new Date(rightModel.getCtime()));

        int advanceDay = 0;
        if(!StringTools.isNullOrEmpty(rightModel.getAdvanceDay())){
            advanceDay = Integer.parseInt(rightModel.getAdvanceDay());
        }
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH,advanceDay);

        timeText.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, calendar.getTime().getTime()));
        pvTime.setTime(calendar.getTime());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.timeLayout:
                pvTime.show();
                break;
            case R.id.rightBacksImageView:
                finish();
                break;
            case R.id.sureButtonText:
                RightController.getInstance().exchangeRight(this, UserPreference.getRoleId(),rightModel.getId(),time,null,null,null);
                break;
        }
    }

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
        if(flag==HttpConfig.exchangeRight.getType()){
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    ToastTool.show("预约成功");
                    Intent mIntent = new Intent();
                    // 设置结果，并进行传送
                    this.setResult(resultCode, mIntent);
                    this.finish();
                } else {
                    ToastTool.show(object.getString("message"));
                }

            } catch (Exception e) {
                ToastTool.show("预约失败");
            }
        }
    }
}
