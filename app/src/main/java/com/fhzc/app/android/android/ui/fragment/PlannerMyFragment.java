package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.bumptech.glide.Glide;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.widget.CircleImageView;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.RankbackDataModel;
import com.fhzc.app.android.models.UserModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/8/1.
 */
public class PlannerMyFragment extends Fragment implements View.OnClickListener,ViewNetCallBack {


    @Bind(R.id.personInfoIv)
    CircleImageView personInfoIv;
    @Bind(R.id.messageIv)
    ImageView messageIv;
    @Bind(R.id.messageRedImage)
    TextView messageRedImage;
    @Bind(R.id.messageLayout)
    RelativeLayout messageLayout;
    @Bind(R.id.vipPlannerView)
    ImageView vipPlannerView;
    @Bind(R.id.person_info_avatar)
    CircleImageView personInfoAvatar;
    @Bind(R.id.plannerNameText)
    TextView plannerNameText;
    @Bind(R.id.plannerLine)
    View plannerLine;
    @Bind(R.id.plannerPosition)
    TextView plannerPosition;
    @Bind(R.id.plannerCompany)
    TextView plannerCompany;
    @Bind(R.id.infoLayout)
    LinearLayout infoLayout;
    @Bind(R.id.achievementText)
    TextView achievementText;
    @Bind(R.id.acievementText)
    TextView acievementText;
    @Bind(R.id.arrangeText)
    TextView arrangeText;
    @Bind(R.id.arrangePlannerText)
    TextView arrangePlannerText;
    @Bind(R.id.achevementText)
    LinearLayout achevementText;
    @Bind(R.id.myWorkerText)
    ImageView myWorkerText;
    @Bind(R.id.myCollectText)
    ImageView myCollectText;
    @Bind(R.id.rootLayout)
    RelativeLayout rootLayout;
    @Bind(R.id.myWorkLayout)
    RelativeLayout myWorkLayout;
    @Bind(R.id.MyCollectLayout)
    RelativeLayout MyCollectLayout;

    public static PlannerMyFragment newInstance() {
        PlannerMyFragment plannerMyFragment = new PlannerMyFragment();
        return plannerMyFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_planner_new_my, container, false);

        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        ButterKnife.bind(this, view);
        initView();
        initData();
        initEvent();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void onEventMainThread(IMEvent event) {
        if (null != event) {
            int count = new MessageDao().getUnReadCount();
            messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
//            messageRedImage.setText(String.valueOf(count));
        }
    }

    private void initData() {
        int count = new MessageDao().getUnReadCount();
        messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
//        messageRedImage.setText(String.valueOf(count));
        UserController.getInstance().RankSearch(new ViewNetCallBack() {
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
                if(flag== HttpConfig.rankBetween.getType()){
                    List<RankbackDataModel> model =(List<RankbackDataModel>)result;
                    for(RankbackDataModel m:model){
                        if(String.valueOf(m.getPlannerUid()).equals(String.valueOf(UserPreference.getUid()))){
//                            acievementText.setText(m.getAnnualised()+"");
                            double youIn = Double.parseDouble(String.valueOf(m.getAnnualised()));
                            double l = 10000;
                            DecimalFormat df = new DecimalFormat("0.00");
                            acievementText.setText(df.format(youIn / l) + "万元");
                            arrangePlannerText.setText(m.getSort()+"");
                        }
                    }
                }
            }
        }, "2016-01-01", "2016-12-31");

        plannerNameText.setText(UserPreference.getName()+",您好");
        plannerPosition.setText(UserPreference.getPosition());
        plannerCompany.setText(UserPreference.getDepartment());
        UserController.getInstance().userInfo(this);
    }

    private void initView() {
    }

    private void initEvent() {
        messageLayout.setOnClickListener(this);
        myWorkLayout.setOnClickListener(this);
        achevementText.setOnClickListener(this);
        MyCollectLayout.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
//        CommonUtil.FlymeSetStatusBarLightMode(getActivity().getWindow(), true);
//        CommonUtil.MIUISetStatusBarLightMode(getActivity().getWindow(), true);
//        int count = new MessageDao().getUnReadCount();
//        messageRedImage.setVisibility(count > 0 ? View.VISIBLE : View.GONE);
//        messageRedImage.setText(String.valueOf(count));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MyCollectLayout:
                IntentTools.startCollectionActivity(getActivity(), "我的收藏");
                break;
            case R.id.achevementText:
//                IntentTools.startMyWorkActivity(getActivity());
                IntentTools.startRecordSearchActivity(getActivity());
                break;
            case R.id.myWorkLayout:
                IntentTools.startMyClientActivity(getActivity());
                break;
            case R.id.messageLayout:
                IntentTools.startChatList(getActivity());
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
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
        if (flag == HttpConfig.userInfo.getType()) {
            UserModel model = (UserModel) result;
            UserPreference.setUser(model);
            Glide.with(getActivity())
                    .load(model.getAvatar())
                    .error(R.drawable.selected_peroninfo)
                    .into(personInfoAvatar);
//            ImageLoader.getInstance(getContext(), R.drawable.selected_peroninfo).displayHtmlImage(model.getAvatar(), personInfoAvatar);
        }
    }
}
