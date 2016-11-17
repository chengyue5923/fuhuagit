package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.MapBuilder;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.MyOrderPointListAdapter;
import com.fhzc.app.android.android.ui.view.widget.NoScrollListView;
import com.fhzc.app.android.configer.enums.CustomerType;
import com.fhzc.app.android.configer.enums.CustomerTypeNew;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.PointModel;
import com.fhzc.app.android.models.PointRecordDetail;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.net.ConnectTool;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.fhzc.app.android.R.color.appColorBlue;

/**
 * Created by User on 2016/7/21.
 */
public class CustomerMyRightAndPointFragment extends Fragment implements View.OnClickListener, ViewNetCallBack {


    @Bind(R.id.totalPoint)
    TextView totalPoint;
    @Bind(R.id.availablePoint)
    TextView availablePoint;
    @Bind(R.id.frozenPoint)
    TextView frozenPoint;
    @Bind(R.id.willExpiredPoint)
    TextView willExpiredPoint;
    @Bind(R.id.pointDetailLayout)
    LinearLayout pointDetailLayout;
    @Bind(R.id.levelImage)
    ImageView levelImage;
    @Bind(R.id.orderRightLayout)
    LinearLayout orderRightLayout;
    @Bind(R.id.pointRecordLayout)
    LinearLayout pointRecordLayout;
    @Bind(R.id.sr_randp_rootlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.my_progress)
    ProgressBar myProgress;
    @Bind(R.id.finalText)
    TextView finalText;
    @Bind(R.id.viptext)
    TextView viptext;
    @Bind(R.id.silverText)
    TextView silverText;
    @Bind(R.id.goldtext)
    TextView goldtext;
    @Bind(R.id.blackgoldtext)
    TextView blackgoldtext;
    @Bind(R.id.numberFinancialText)
    TextView numberFinancialText;
    @Bind(R.id.pointListView)
    NoScrollListView pointListView;
    @Bind(R.id.allTextView)
    TextView allTextView;
    @Bind(R.id.availableTextView)
    TextView availableTextView;
    @Bind(R.id.useTextView)
    TextView useTextView;
    @Bind(R.id.financialView)
    View financialView;
    @Bind(R.id.vipView)
    View vipView;
    @Bind(R.id.silverView)
    View silverView;
    @Bind(R.id.goldView)
    View goldView;
    @Bind(R.id.blackgoldView)
    View blackgoldView;
    @Bind(R.id.pointDetailView)
    ImageView pointDetailView;
    private String fromDateText, toDateText;
    MyOrderPointListAdapter adapter;
    private int year, month, day;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_right_point, container, false);
        ButterKnife.bind(this, view);
        swipeRefreshLayout.setColorSchemeResources(appColorBlue);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                UserController.getInstance().personalScore(CustomerMyRightAndPointFragment.this);
            }
        });
        orderRightLayout.setOnClickListener(this);
        pointRecordLayout.setOnClickListener(this);
        levelImage.setImageResource(CustomerTypeNew.getCustomerByType(UserPreference.getLevel()).getPointBg());
        UserController.getInstance().personalScore(this);

        financialView.setVisibility(View.GONE);
        vipView.setVisibility(View.GONE);
        silverView.setVisibility(View.GONE);
        goldView.setVisibility(View.GONE);
        blackgoldView.setVisibility(View.GONE);

        myProgress.setMax(100);
        if (UserPreference.getLevel() != null && UserPreference.getLevel().length() > 1) {

            if (UserPreference.getLevel().equals("投资人")) {
                financialView.setVisibility(View.VISIBLE);
                myProgress.setSecondaryProgress(0);
                finalText.setTextColor(getActivity().getResources().getColor(R.color.apptextBlue));
                viptext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                silverText.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                goldtext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                blackgoldtext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
            } else if (UserPreference.getLevel().equals("准会员")) {
                vipView.setVisibility(View.VISIBLE);

                myProgress.setSecondaryProgress(30);
                finalText.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                viptext.setTextColor(getActivity().getResources().getColor(R.color.apptextBlue));
                silverText.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                goldtext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                blackgoldtext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));

            } else if (UserPreference.getLevel().equals("银卡")) {
                silverView.setVisibility(View.VISIBLE);

                myProgress.setSecondaryProgress(50);
                finalText.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                viptext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                silverText.setTextColor(getActivity().getResources().getColor(R.color.apptextBlue));
                goldtext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                blackgoldtext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));

            } else if (UserPreference.getLevel().equals("金卡")) {
                goldView.setVisibility(View.VISIBLE);

                myProgress.setSecondaryProgress(70);
                finalText.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                viptext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                silverText.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                goldtext.setTextColor(getActivity().getResources().getColor(R.color.apptextBlue));
                blackgoldtext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));

            } else if (UserPreference.getLevel().equals("铂金卡")) {
                blackgoldView.setVisibility(View.VISIBLE);

//                myProgress.setProgress(50);
                myProgress.setSecondaryProgress(100);
                finalText.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                viptext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                silverText.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                goldtext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                blackgoldtext.setTextColor(getActivity().getResources().getColor(R.color.apptextBlue));

            }else{
                financialView.setVisibility(View.VISIBLE);
                myProgress.setSecondaryProgress(0);
                finalText.setTextColor(getActivity().getResources().getColor(R.color.apptextBlue));
                viptext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                silverText.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                goldtext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
                blackgoldtext.setTextColor(getActivity().getResources().getColor(R.color.appouttextBlue));
            }
        }
        try {
            ConnectTool.httpRequest(HttpConfig.pointRecordDetail, new MapBuilder<String, Object>().add("type", "all").add("start", fromDateText).add("end", toDateText).getMap(), this, PointRecordDetail.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter = new MyOrderPointListAdapter(getActivity());
        pointListView.setAdapter(adapter);
        pointListView.setFocusable(false);
        initEvent();
        initTime();
        return view;
    }

    public void initEvent() {
        allTextView.setOnClickListener(this);
        availableTextView.setOnClickListener(this);
        useTextView.setOnClickListener(this);
        pointDetailView.setOnClickListener(this);
    }

    private void initTime() {
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);


        fromDateText = formatDate(year - 1, month, day);
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

    public void GetDate(String statues) {
        try {
            ConnectTool.httpRequest(HttpConfig.pointRecordDetail, new MapBuilder<String, Object>().add("type", statues).add("start", fromDateText).add("end", toDateText).getMap(), this, PointRecordDetail.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orderRightLayout:
                IntentTools.startOrderRight(getActivity(), UserPreference.getRoleId());
                break;
            case R.id.pointRecordLayout:
                IntentTools.startPointRecord(getActivity());
                break;
            case R.id.allTextView:
                ChangeStatues(0);
                break;
            case R.id.availableTextView:
                ChangeStatues(1);
                break;
            case R.id.useTextView:
                ChangeStatues(2);
                break;
            case R.id.pointDetailView:
                IntentTools.startPointActivity(getActivity());

                break;
        }
    }

    public void ChangeStatues(int flag) {
        if (flag == 0) {
            allTextView.setTextColor(getResources().getColor(R.color.appColorBlue));
            availableTextView.setTextColor(getResources().getColor(R.color.appouttextBlue));
            useTextView.setTextColor(getResources().getColor(R.color.appouttextBlue));
            GetDate("all");
        } else if (flag == 1) {
            allTextView.setTextColor(getResources().getColor(R.color.appouttextBlue));
            availableTextView.setTextColor(getResources().getColor(R.color.appColorBlue));
            useTextView.setTextColor(getResources().getColor(R.color.appouttextBlue));
            GetDate("available");
        } else if (flag == 2) {
            allTextView.setTextColor(getResources().getColor(R.color.appouttextBlue));
            availableTextView.setTextColor(getResources().getColor(R.color.appouttextBlue));
            useTextView.setTextColor(getResources().getColor(R.color.appColorBlue));
            GetDate("frozen");
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
        if (flag == HttpConfig.pointRecordDetail.getType()) {
            swipeRefreshLayout.setRefreshing(false);
            List<PointRecordDetail> model = (List<PointRecordDetail>) result;
            adapter.setRes(model);
        } else {
            swipeRefreshLayout.setRefreshing(false);
            PointModel model = (PointModel) result;
            totalPoint.setText(String.valueOf(model.getYours()));
            willExpiredPoint.setText(String.valueOf(model.getWillExpired()));
            frozenPoint.setText(String.valueOf(model.getFrozen()));
            availablePoint.setText(String.valueOf(model.getAvailable()));
        }
    }
}
