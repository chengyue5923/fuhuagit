package com.fhzc.app.android.android.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.base.platform.utils.java.MapBuilder;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.ClipViewPager;
import com.fhzc.app.android.android.ui.view.ScalePageTransformer;
import com.fhzc.app.android.android.ui.view.adapter.PointRecordDetailAdapter;
import com.fhzc.app.android.android.ui.view.adapter.PointTypeAdapter;
import com.fhzc.app.android.android.ui.window.DatePickerPopupWin;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.models.PointModel;
import com.fhzc.app.android.models.PointRecordDetail;
import com.fhzc.app.android.utils.net.ConnectTool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.carbswang.android.numberpickerview.library.NumberPickerView;

/**
 * Created by apple on 16/7/27.
 */
public class PointRecordActivity extends BaseActivity implements View.OnClickListener, NumberPickerView.OnValueChangeListener {

    @Bind(R.id.clipViewPager)
    ClipViewPager mViewPager;
    @Bind(R.id.iv_datepointtype)
    ImageView btnDate;
    @Bind(R.id.lv_pointtyperecord)
    ListView listView;
    @Bind(R.id.pointtype)
    TextView tvPointType;
    @Bind(R.id.ll_pr_pointchangerecord)
    LinearLayout llPointChangeRecord;

    private PointTypeAdapter mPagerAdapter;
    private PointRecordDetailAdapter mRecordsAdapter;
    private DatePickerPopupWin datePicker;

    private NumberPickerView mPickerViewY;
    private NumberPickerView mPickerViewM;
    private NumberPickerView mPickerViewD;
    private TextView btnSure, tvDateFrom, tvDateTo;
    private LinearLayout btnDateFrom, btnDateTo;

    private String fromDateText, toDateText;

    private int year, month, day;
    private Handler handler;

    public static final int UPDATE_FROM_DATE = 10;
    public static final int UPDATE_TO_DATE = 11;

    private List<PointRecordDetail> allList;
    private List<PointRecordDetail> availableList;
    private List<PointRecordDetail> frozenList;
    private List<PointRecordDetail> willList;
    private String pointType = "all";


    @Override
    protected void initView() {
        ButterKnife.bind(this);

        findViewById(R.id.page_container).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mViewPager.dispatchTouchEvent(event);
            }
        });

        btnDate.setOnClickListener(this);


    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        tvPointType.setText("积分变动记录");
        mPagerAdapter = new PointTypeAdapter(this,new PointModel());
        mRecordsAdapter = new PointRecordDetailAdapter(this);

        allList = new ArrayList<PointRecordDetail>();
        mRecordsAdapter.setRes(allList);
        listView.setAdapter(mRecordsAdapter);



        mViewPager.setPageTransformer(true, new ScalePageTransformer());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                if(llPointChangeRecord.getVisibility()==View.GONE){
//                    llPointChangeRecord.setVisibility(View.VISIBLE);
//                }
                tvPointType.setText("积分变动记录");
                boolean unRequest = false;
                switch (position){
                    case 0:
                        pointType = "all";
                        if(!allList.isEmpty()){
                            unRequest = true;
                            mRecordsAdapter.setRes(allList);
                        }
                        break;
                    case 1:
                        pointType = "available";
                        if(availableList==null){
                            availableList = new ArrayList<PointRecordDetail>();
                        }

                        if(!availableList.isEmpty()){
                            unRequest = true;
                            mRecordsAdapter.setRes(availableList);
                        }
                        break;
                    case 2:
                        pointType = "frozen";
                        if(frozenList==null){
                            frozenList = new ArrayList<PointRecordDetail>();
                        }

                        if(!frozenList.isEmpty()){
                            unRequest = true;
                            mRecordsAdapter.setRes(frozenList);
                        }
                        break;
                    case 3:
                        tvPointType.setText("即将过期");
                        pointType = "will";
                        if(willList==null){
                            willList = new ArrayList<PointRecordDetail>();
                            PointRecordDetail recordDetail = new PointRecordDetail();
                            recordDetail.setScore(mPagerAdapter.records.getWillExpired());
                            recordDetail.setStatus("willExpired");
                            willList.add(recordDetail);
                        }

                        /**
                         * 接口不支持 做隐藏处理
                         */
                        unRequest = true;
                        mRecordsAdapter.setRes(willList);
//                        llPointChangeRecord.setVisibility(View.GONE);

//                        if(!willList.isEmpty()){
//                            unRequest = true;
//                            mRecordsAdapter.setRes(willList);
//                        }
                        break;
                }
                requestPointRecords(pointType,unRequest);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /**
         * 假数据
         */
//        PointRecord records = new PointRecord();
//        records.setAvailable(666);
//        records.setExpired(168);
//        records.setFrozen(222);
//        records.setYours(888);
//        mPagerAdapter.records = records;
//        mPagerAdapter.notifyDataSetChanged();
//        for(int i=0;i<10;i++){
//            PointRecordDetail record = new PointRecordDetail();
//            record.setDetail("【复华奥瑞澳洲机会基金】");
//            record.setCtime(1462377600000l);
//            record.setStatus("已发放");
//            record.setScore(123);
//            allList.add(record);
//        }
//        mRecordsAdapter.setRes(allList);


        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                String date = message.getData().getString("date");
                switch (message.what) {
                    case UPDATE_FROM_DATE:
                        tvDateFrom.setText(date);
                        break;
                    case UPDATE_TO_DATE:
                        tvDateTo.setText(date);
                        break;
                }
                return true;
            }
        });
        initTime();
        try {
            ConnectTool.httpRequest(HttpConfig.pointRecord, new MapBuilder<String,Object>().getMap(), this, PointModel.class);
            requestPointRecords(pointType,false);
        } catch (Exception exception) {

        }
    }

    private void requestPointRecords(String pointType,boolean unRequest)  {
        try {
            if(!unRequest){
                ConnectTool.httpRequest(HttpConfig.pointRecordDetail,new MapBuilder<String,Object>().add("type",pointType).add("start",fromDateText).add("end",toDateText).getMap(),this, PointRecordDetail.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pointrecord;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.pointRecord.getType()) {
            PointModel point = (PointModel) result;
            mPagerAdapter.setRes(point);
        }else if (flag == HttpConfig.pointRecordDetail.getType()) {
            switch (mViewPager.getCurrentItem()){
                case 0:
                    allList = (List<PointRecordDetail>) result;
                    mRecordsAdapter.setRes(allList);
                    break;
                case 1:
                    availableList = (List<PointRecordDetail>) result;
                    mRecordsAdapter.setRes(availableList);
                    break;
                case 2:
                    frozenList = (List<PointRecordDetail>) result;
                    mRecordsAdapter.setRes(frozenList);
                    break;
                case 3:
                    willList = (List<PointRecordDetail>) result;
                    mRecordsAdapter.setRes(willList);
                    break;
            }

        }

    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.iv_datepointtype:
                showDatePickerWindow();
                break;
            case R.id.tv_btnsure:
                datePicker.dismiss();

                break;
            case R.id.ll_btndatefrom:
                btnDateFrom.setSelected(true);
                btnDateTo.setSelected(false);
                break;
            case R.id.ll_btndateto:
                btnDateFrom.setSelected(false);
                btnDateTo.setSelected(true);
                break;
        }
    }

    public void showDatePickerWindow() {
        if (datePicker != null && datePicker.isShowing()) {
            return;
        } else {
            datePicker = new DatePickerPopupWin(this, this);
            View view = datePicker.getContentView();

            mPickerViewY = (NumberPickerView) view.findViewById(R.id.picker_year);
            mPickerViewM = (NumberPickerView) view.findViewById(R.id.picker_month);
            mPickerViewD = (NumberPickerView) view.findViewById(R.id.picker_day);

            mPickerViewY.setOnValueChangedListener(this);
            mPickerViewM.setOnValueChangedListener(this);
            mPickerViewD.setOnValueChangedListener(this);

            tvDateFrom = (TextView) view.findViewById(R.id.tv_datefromselected);
            tvDateTo = (TextView) view.findViewById(R.id.tv_datetoselected);
            btnSure = (TextView) view.findViewById(R.id.tv_btnsure);
            btnDateFrom = (LinearLayout) view.findViewById(R.id.ll_btndatefrom);
            btnDateTo = (LinearLayout) view.findViewById(R.id.ll_btndateto);

            btnDateFrom.setOnClickListener(this);
            btnDateTo.setOnClickListener(this);
            btnSure.setOnClickListener(this);
            btnDateFrom.setSelected(true);
            btnDateTo.setSelected(false);
            /**
            * 初始化控件的时间值
            * */
            int maxDays = getMaxDays(year, month);
            setData(mPickerViewY, 2006, 2029, year);
            setData(mPickerViewM, 1, 12, month);
            setData(mPickerViewD, 1, maxDays, day);

            DisplayMetrics mertrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(mertrics);
            int screenHeight = mertrics.heightPixels;

            datePicker.getContentView().measure(0, View.MeasureSpec.UNSPECIFIED);
            int yOff = datePicker.getContentView().getMeasuredHeight();
//            datePicker.showAtLocation(getWindow().getDecorView(), Gravity.CENTER,0,0);
            Log.e("fuwen", screenHeight + " " + yOff);

            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.alpha = 0.7f;
            getWindow().setAttributes(lp);
            datePicker.setOnDismissListener(new PopupWindow.OnDismissListener() {

                @Override
                public void onDismiss() {
                    WindowManager.LayoutParams lp = getWindow().getAttributes();
                    lp.alpha = 1f;
                    getWindow().setAttributes(lp);
                }
            });

            datePicker.showAsDropDown(getWindow().getDecorView(), 0, -yOff);
        }
    }

    private void initTime() {
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);


        fromDateText = formatDate(year-1, month, day);
        toDateText = formatDate(year, month, day);


    }

    private String formatDate(int year, int month, int day) {
        StringBuffer sb = new StringBuffer();
        sb.append(year).append("-");
        if (month < 10) {
            sb.append("0").append(month);
        } else {
            sb.append(month);
        }
        sb.append("-");
        if (day < 10) {
            sb.append("0").append(day);
        } else {
            sb.append(day);
        }
        return sb.toString();
    }

    private int getMaxDays(int y, int m) {
        int maxDays = 0;
        switch (m) {
            case 2:
                if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {
                    maxDays = 29;
                } else {
                    maxDays = 28;
                }
                break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                maxDays = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                maxDays = 30;
                break;
        }
        return maxDays;
    }

    private void setData(NumberPickerView picker, int minValue, int maxValue, int value) {
        picker.setMinValue(minValue);
        picker.setMaxValue(maxValue);
        picker.setValue(value);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 当月份被重置的时候 要先取得当前年月日的值去计算该月份最大天数，然后更新日控件最大天数，如果最大天数<当前日期，则要把当前日期变为最大天数，因为设置最大天数会默认把当前日期置最小，所以还要把当前日期调回来
     *
     * @param picker
     * @param oldVal
     * @param newVal
     */
    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {

        Message message = new Message();
        Bundle bundle = new Bundle();
        switch (picker.getId()) {
            case R.id.picker_year:
                year = newVal;
                String date = year + "年" + month + "月" + day + "日";
                if (btnDateFrom.isSelected()) {
                    fromDateText = formatDate(year, month, day);
                    message.what = UPDATE_FROM_DATE;
                    bundle.putString("date", date);
                    message.setData(bundle);
                    handler.sendMessage(message);
                } else {
                    toDateText = formatDate(year, month, day);
                    message.what = UPDATE_TO_DATE;
                    bundle.putString("date", date);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
                break;
            case R.id.picker_month:

//                int y = Integer.parseInt(mPickerViewY.getContentByCurrValue());
//                int m = Integer.parseInt(mPickerViewM.getContentByCurrValue());
                month = newVal;
                int day = Integer.parseInt(mPickerViewD.getContentByCurrValue());
                int maxDays = getMaxDays(year, month);
                mPickerViewD.setMaxValue(maxDays);
                if (maxDays < day) {
                    mPickerViewD.setValue(maxDays);
                } else {
                    mPickerViewD.setValue(day);
                }


                this.day = Integer.parseInt(mPickerViewD.getContentByCurrValue());
                String date2 = year + "年" + month + "月" + this.day + "日";
                if (btnDateFrom.isSelected()) {
                    fromDateText = formatDate(year, month, this.day);
//                    tvDateFrom.setText(date2);
                    message.what = UPDATE_FROM_DATE;
                    bundle.putString("date", date2);
                    message.setData(bundle);
                    handler.sendMessage(message);

                } else {
                    toDateText = formatDate(year, month, this.day);
//                    tvDateTo.setText(date2);
                    message.what = UPDATE_TO_DATE;
                    bundle.putString("date", date2);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }

                break;
            case R.id.picker_day:

                day = newVal;
                String date3 = year + "年" + month + "月" + day + "日";
                if (btnDateFrom.isSelected()) {
                    fromDateText = formatDate(year, month, day);
//                    tvDateFrom.setText(date3);
                    message.what = UPDATE_FROM_DATE;
                    bundle.putString("date", date3);
                    message.setData(bundle);
                    handler.sendMessage(message);
                } else {
                    toDateText = formatDate(year, month, day);
//                    tvDateTo.setText(date3);
                    message.what = UPDATE_TO_DATE;
                    bundle.putString("date", date3);
                    message.setData(bundle);
                    handler.sendMessage(message);
                }
                break;
        }
    }
}
