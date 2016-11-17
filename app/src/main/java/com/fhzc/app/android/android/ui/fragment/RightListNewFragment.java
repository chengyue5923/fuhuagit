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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.DateTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.RightController;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.PointModel;
import com.fhzc.app.android.models.RightModel;
import com.fhzc.app.android.models.RightModels;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/8/1.
 */
public class RightListNewFragment extends Fragment implements ViewNetCallBack, View.OnClickListener {


    @Bind(R.id.healthImageView)
    RelativeLayout healthImageView;
    @Bind(R.id.tourismImageView)
    RelativeLayout tourismImageView;
    @Bind(R.id.artImageView)
    RelativeLayout artImageView;
    @Bind(R.id.busTourismImageView)
    RelativeLayout busTourismImageView;
    @Bind(R.id.liveImageView)
    RelativeLayout liveImageView;
    @Bind(R.id.sportImageView)
    RelativeLayout sportImageView;
    @Bind(R.id.collegeImageView)
    RelativeLayout collegeImageView;
    @Bind(R.id.cubImageView)
    RelativeLayout cubImageView;
    @Bind(R.id.rightLayout)
    RelativeLayout rightLayout;
    @Bind(R.id.item_gallery)
    LinearLayout itemGallery;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    @Bind(R.id.leavelandpointtext)
    TextView leavelandpointtext;
    @Bind(R.id.selfLayout)
    RelativeLayout selfLayout;
    @Bind(R.id.clubImage)
    ImageView clubImage;
    SwipeRefreshLayout swipeLayout;
    @Bind(R.id.rightOneTitle)
    TextView rightOneTitle;
    @Bind(R.id.rightOneContent)
    TextView rightOneContent;
    @Bind(R.id.rightOneImage)
    ImageView rightOneImage;
    @Bind(R.id.rightOneLayout)
    LinearLayout rightOneLayout;
    @Bind(R.id.rightTwoTitle)
    TextView rightTwoTitle;
    @Bind(R.id.rightTwoContent)
    TextView rightTwoContent;
    @Bind(R.id.rightTwoImage)
    ImageView rightTwoImage;
    @Bind(R.id.rightTwoLayout)
    LinearLayout rightTwoLayout;
    @Bind(R.id.rightThreeTitle)
    TextView rightThreeTitle;
    @Bind(R.id.rightThreeContent)
    TextView rightThreeContent;
    @Bind(R.id.rightThreeImage)
    ImageView rightThreeImage;
    @Bind(R.id.rightThreeLayout)
    LinearLayout rightThreeLayout;
    @Bind(R.id.rightFourTitle)
    TextView rightFourTitle;
    @Bind(R.id.rightFourContent)
    TextView rightFourContent;
    @Bind(R.id.rightFourImage)
    ImageView rightFourImage;
    @Bind(R.id.RightFourLayout)
    LinearLayout RightFourLayout;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    private LayoutInflater mInflater;
     List<RightModel> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_right_list, container, false);
        ButterKnife.bind(this, view);
        if(!UserPreference.isCustomer()){
            selfLayout.setVisibility(View.GONE);
        }else{
            selfLayout.setVisibility(View.VISIBLE);
        }
        mInflater = LayoutInflater.from(getActivity());
        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeLayout.setRefreshing(false);
//                    }
//                }, 5000);

                initData();
            }
        });
        swipeLayout.setColorSchemeResources(R.color.color_bule2, R.color.color_bule, R.color.color_bule2, R.color.color_bule3);

        initData();
        initEvent();
        return view;
    }

    private void initEvent() {
        clubImage.setOnClickListener(this);
        selfLayout.setOnClickListener(this);
        healthImageView.setOnClickListener(this);
        tourismImageView.setOnClickListener(this);
        artImageView.setOnClickListener(this);
        busTourismImageView.setOnClickListener(this);
        liveImageView.setOnClickListener(this);
        sportImageView.setOnClickListener(this);
        collegeImageView.setOnClickListener(this);
        cubImageView.setOnClickListener(this);
        rightOneLayout.setOnClickListener(this);
        rightTwoLayout.setOnClickListener(this);
        rightThreeLayout.setOnClickListener(this);
        RightFourLayout.setOnClickListener(this);
    }

    private void initData() {
        RightController.getInstance().selectRight(this);
        UserController.getInstance().personalScore(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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

    RightModel modelRightOne;
    RightModel modelRightTwo;
    RightModel modelRightThree;
    RightModel modelRightFour;
    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        swipeLayout.setRefreshing(false);
        if(flag== HttpConfig.pointRecord.getType()){
            PointModel model = (PointModel) result;
            leavelandpointtext.setText(UserPreference.getLevel()+" / 可用积分："+String.valueOf(model.getAvailable()));
        }else{

            list = ((RightModels) result).getItems();

            modelRightOne=list.get(0);
            modelRightTwo=list.get(1);
            modelRightThree=list.get(2);
            modelRightFour=list.get(3);

            rightOneTitle.setText(modelRightOne.getName());
            rightOneContent.setText(modelRightOne.getNotice());
            ImageLoader.getInstance(getActivity(), R.drawable.default_error).displayImage(modelRightOne.getCover(), rightOneImage);
            rightTwoTitle.setText(modelRightTwo.getName());
            rightTwoContent.setText(modelRightTwo.getNotice());
            ImageLoader.getInstance(getActivity(), R.drawable.default_error).displayImage(modelRightTwo.getCover(), rightTwoImage);
            rightThreeTitle.setText(modelRightThree.getName());
            rightThreeContent.setText(modelRightThree.getNotice());
            ImageLoader.getInstance(getActivity(), R.drawable.default_error).displayImage(modelRightThree.getCover(), rightThreeImage);
            rightFourTitle.setText(modelRightFour.getName());
            rightFourContent.setText(modelRightFour.getNotice());
            ImageLoader.getInstance(getActivity(), R.drawable.default_error).displayImage(modelRightFour.getCover(), rightFourImage);
            itemGallery.removeAllViews();
            for (int i = 4; i < 7; i++) {
                final RightModel model = list.get(i);
                View view = mInflater.inflate(R.layout.view_my_order_right_item,
                        itemGallery, false);
                view.setTag(i);
                ImageView img = (ImageView) view
                        .findViewById(R.id.order_right_image);
                TextView time = (TextView) view
                        .findViewById(R.id.order_right_time);
                ImageView levelImage = (ImageView) view.findViewById(R.id.levelImageas);
                if (model.getLevel() + "" != null) {
                    switch (model.getLevel() - 1) {
                        case 0:
                            levelImage.setBackgroundResource(R.drawable.fincnial_right_view);
                            break;
                        case 1:
                            levelImage.setBackgroundResource(R.drawable.vip_right_view_view);
                            break;
                        case 2:
                            levelImage.setBackgroundResource(R.drawable.silver_rigth_view_view);
                            break;
                        case 3:
                            levelImage.setBackgroundResource(R.drawable.gold_view_right_view);
                            break;
                        case 4:
                            levelImage.setBackgroundResource(R.drawable.platinum_gold_view_right);
                            break;
                    }
                }
                ImageLoader.getInstance(getActivity(), R.drawable.default_error).displayImage(model.getCover(), img);
                time.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getCtime()));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentTools.startNewRightDetail(getActivity(), model.getId());
                    }
                });
                itemGallery.addView(view);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.healthImageView:
                IntentTools.startRightList(getActivity(), "健康管理", 1);
                break;
            case R.id.tourismImageView:
                IntentTools.startRightList(getActivity(), "旅行管家", 2);
                break;
            case R.id.artImageView:
                IntentTools.startRightList(getActivity(), "艺术管家", 3);
                break;
            case R.id.busTourismImageView:
                IntentTools.startRightList(getActivity(), "商旅通", 4);
                break;
            case R.id.liveImageView:
                IntentTools.startRightList(getActivity(), "奢生活", 5);
                break;
            case R.id.sportImageView:
                IntentTools.startRightList(getActivity(), "爱体育", 6);
                break;
            case R.id.collegeImageView:
                IntentTools.startRightList(getActivity(), "商学院", 7);
                break;
            case R.id.cubImageView:
                IntentTools.startRightList(getActivity(), "俱乐部", 8);
                break;
            case R.id.rightOneLayout:
                IntentTools.startNewRightDetail(getActivity(), modelRightOne.getId());
                break;
            case R.id.rightTwoLayout:
                IntentTools.startNewRightDetail(getActivity(), modelRightTwo.getId());
                break;
            case R.id.rightThreeLayout:
                IntentTools.startNewRightDetail(getActivity(), modelRightThree.getId());
                break;
            case R.id.RightFourLayout:
                IntentTools.startNewRightDetail(getActivity(), modelRightFour.getId());
                break;
            case R.id.selfLayout:
                IntentTools.startCustomerPoint(getActivity());
                break;
            case R.id.clubImage:
//                IntentTools.startWebView(getActivity(),"http://www.maglogmedia.com:8081/11/html/club.html?from=singlemessage&isappinstalled=0","财富俱乐部");
                IntentTools.startKnowAboutUs(getActivity(),"http://www.maglogmedia.com:8081/11/html/club.html?from=singlemessage&isappinstalled=0","财富俱乐部");
                break;

        }

    }
}
