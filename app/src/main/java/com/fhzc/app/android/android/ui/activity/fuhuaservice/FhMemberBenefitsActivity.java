package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.android.ui.view.widget.PullableScrollView;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.RightController;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.event.IMEvent;
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
 * 会员权益
 * Created by lenovo on 2016/7/11.
 */
public class FhMemberBenefitsActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {


    @Bind(R.id.item_gallery)
    LinearLayout item_gallery;
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
    @Bind(R.id.rightDetailToolbar)
    CommonToolBar rightDetailToolbar;
    @Bind(R.id.scrollview)
    PullableScrollView allSv;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;

    private LayoutInflater mInflater;
    private EmptyLayout emptyLayout;
    SwipeRefreshLayout swipeLayout;
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        mInflater = LayoutInflater.from(this);
        emptyLayout = new EmptyLayout(this, allSv);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
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

    }

    @Override
    protected void initEvent() {
        healthImageView.setOnClickListener(this);
        tourismImageView.setOnClickListener(this);
        artImageView.setOnClickListener(this);
        busTourismImageView.setOnClickListener(this);
        liveImageView.setOnClickListener(this);
        sportImageView.setOnClickListener(this);
        collegeImageView.setOnClickListener(this);
        cubImageView.setOnClickListener(this);
        rightDetailToolbar.setClickRightListener(this);
    }

    public void onEventMainThread(IMEvent event) {
        if (null != event) {
            rightDetailToolbar.setRedImage(new MessageDao().getUnReadCount());

        }
    }

    @Override
    protected void initData() {
        rightDetailToolbar.setRedImage(new MessageDao().getUnReadCount());
        RightController.getInstance().selectRight(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_benefits;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.healthImageView:
                IntentTools.startRightList(this, "健康管理", 1);
                break;
            case R.id.tourismImageView:
                IntentTools.startRightList(this, "旅行管家", 2);
                break;
            case R.id.artImageView:
                IntentTools.startRightList(this, "艺术管家", 3);
                break;
            case R.id.busTourismImageView:
                IntentTools.startRightList(this, "商旅通", 4);
                break;
            case R.id.liveImageView:
                IntentTools.startRightList(this, "奢生活", 5);
                break;
            case R.id.sportImageView:
                IntentTools.startRightList(this, "爱体育", 6);
                break;
            case R.id.collegeImageView:
                IntentTools.startRightList(this, "商学院", 7);
                break;
            case R.id.cubImageView:
                IntentTools.startRightList(this, "俱乐部", 8);
                break;

        }
    }

    @Override
    public void onConnectStart() {
        super.onConnectStart();
        emptyLayout.showLoading();
    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
        emptyLayout.showError();
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        final List<RightModel> list = ((RightModels) result).getItems();
        swipeLayout.setRefreshing(false);
        emptyLayout.showSuccess(list.size() <= 0);
        for (int i = 0; i < list.size(); i++) {
            final RightModel model = list.get(i);
            View view = mInflater.inflate(R.layout.view_my_order_right_item,
                    item_gallery, false);
            view.setTag(i);
            ImageView img = (ImageView) view
                    .findViewById(R.id.order_right_image);
            TextView time = (TextView) view
                    .findViewById(R.id.order_right_time);
            ImageView levelImage = (ImageView) view.findViewById(R.id.levelImageas);
            if (model.getLevel() + "" != null) {
                switch (model.getLevel() - 1) {
                    case 0:
                        levelImage.setBackgroundResource(R.drawable.clientlist_investor);
                        break;
                    case 1:
                        levelImage.setBackgroundResource(R.drawable.clientlist_associate);
                        break;
                    case 2:
                        levelImage.setBackgroundResource(R.drawable.clientlist_silvervip);
                        break;
                    case 3:
                        levelImage.setBackgroundResource(R.drawable.clientlist_goldvip);
                        break;
                    case 4:
                        levelImage.setBackgroundResource(R.drawable.clientlist_balckvip);
                        break;
                }
            }
            ImageLoader.getInstance(FhMemberBenefitsActivity.this, R.drawable.default_error).displayImage(model.getCover(), img);
            time.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getCtime()));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    IntentTools.startNewRightDetail(FhMemberBenefitsActivity.this, model.getId(),0+"");
                }
            });
            item_gallery.addView(view);
        }
    }

    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }
}
