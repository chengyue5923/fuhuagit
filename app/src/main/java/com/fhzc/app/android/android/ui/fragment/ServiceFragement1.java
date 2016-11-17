package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.CommodityDetailPagerAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.UrlRes;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.BannerModel;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.CommonUtil;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.fhzc.app.android.utils.im.CommonUtil.getStatusBarHeight;

/**
 * Created by apple on 16/9/7.
 */

public class ServiceFragement1 extends Fragment implements View.OnClickListener, CommonToolBar.ClickRightListener, ViewNetCallBack, CommodityDetailPagerAdapter.ClickListener  {
    @Bind(R.id.title)
    CommonToolBar title;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    @Bind(R.id.iv_xwsj)
    ImageView ivXWSJ;
    @Bind(R.id.iv_hdyb)
    ImageView ivHDYB;
    @Bind(R.id.iv_rmcp)
    ImageView ivRMCP;
    @Bind(R.id.iv_tysj)
    ImageView iv_tysj;
    @Bind(R.id.iv_hykj)
    ImageView iv_hykj;
    @Bind(R.id.iv_sryh)
    ImageView iv_sryh;
    @Bind(R.id.iv_zcgl)
    ImageView iv_zcgl;
    @Bind(R.id.iv_title)
    ImageView ivTitle;
    @Bind(R.id.iv_jkgl)
    ImageView iv_jkgl;


    View vessel;


    public static ServiceFragement1 newInstance() {
        ServiceFragement1 squareFragmentV2 = new ServiceFragement1();
        return squareFragmentV2;
    }

    public void onEventMainThread(IMEvent event) {
        if (null != event) {
            title.setRedImage(new MessageDao().getUnReadCount());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //透明状态栏
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        vessel = inflater.inflate(R.layout.fragment_service, null);
        ButterKnife.bind(this, vessel);
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        initData();
        initView();
        return vessel;
    }
    public void initData() {
        title.setRedImage(new MessageDao().getUnReadCount());
//        LoginController.getInstance().myBank(this);
        title.setClickRightListener(this);
    }

    public void initView() {

        if (CommonUtil.isUpperKK()) {
            View view = new View(getActivity());
            view.setBackgroundColor(getResources().getColor(R.color.common_title));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(getActivity()));
            view.setLayoutParams(layoutParams);
            rootLayout.addView(view, 0);
        }

        title.dismissLine();

        ivXWSJ.setOnClickListener(this);
        ivHDYB.setOnClickListener(this);
        ivRMCP.setOnClickListener(this);
        iv_tysj.setOnClickListener(this);
        iv_hykj.setOnClickListener(this);
        iv_sryh.setOnClickListener(this);
        iv_zcgl.setOnClickListener(this);
        ivTitle.setOnClickListener(this);
        iv_jkgl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.iv_xwsj:
                IntentTools.startHotNews(getActivity());
                break;
            case R.id.iv_hdyb:
                IntentTools.startActivitiesFocast(getActivity());
                break;
            case R.id.iv_rmcp:
                IntentTools.startHotProducts(getActivity());
                break;
            case R.id.iv_tysj:
                IntentTools.startNewReportList(getActivity());
                break;
            case R.id.iv_hykj:
                IntentTools.startRightBenfitsNumbrtList(getActivity());
                break;
            case R.id.iv_sryh:
                IntentTools.startPersonalTailor(getActivity());
                break;
            case R.id.iv_zcgl:
                IntentTools.startAssentManagement(getActivity());
                break;
            case R.id.iv_title:
                IntentTools.startKnowAboutUs(getActivity(),"http://wx4cc926faafd1d4b3.m.weimob.com/weisite/list?pid=55605950&bid=56330875&wechatid=fromUsername&ltid=1540623&wxref=mp.weixin.qq.com","走进我们");
                break;
            case R.id.iv_jkgl:
                IntentTools.startWebView(getActivity(), UrlRes.getInstance().getUrl() + "/h5/life-manage-home.html ", "生命管理");
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

    }

    @Override
    public void click(BannerModel model) {

    }

    @Override
    public void OnClickRight(View view) {
        IntentTools.startChatList(getActivity());
    }
}
