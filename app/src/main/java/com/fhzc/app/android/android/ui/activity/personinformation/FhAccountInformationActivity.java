package com.fhzc.app.android.android.ui.activity.personinformation;

import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.AccountInformationAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.models.AccountInformationModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 到账消息
 * Created by yanbo on 2016/7/21.
 */
public class FhAccountInformationActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.systemMessageToolBar)
    CommonToolBar systemMessageToolBar;
    @Bind(R.id.yearList)
    ListView yearList;
    @Bind(R.id.messageLayout)
    RelativeLayout messageLayout;
    private AccountInformationAdapter adapter;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        adapter = new AccountInformationAdapter(this);
        yearList.setAdapter(adapter);
        if(getIntent().getStringExtra("customerid")!=null){
            UserController.getInstance().AccountInfo(this,getIntent().getStringExtra("customerid"));
        }
//        List<AccountInformationModel> list = new ArrayList<>();
//        list.add(new AccountInformationModel());
//        list.add(new AccountInformationModel());
//        list.add(new AccountInformationModel());
//        adapter.setRes(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_account_information_message;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

        if(flag== HttpConfig.accountInfo.getType()){
            List<AccountInformationModel> model=(List<AccountInformationModel>)result;
            if(model.size()==0){
                messageLayout.setVisibility(View.VISIBLE);
                yearList.setVisibility(View.GONE);
            }else{
                adapter.setRes(model);
            }
        }
    }

    @Override
    public void onClick(View v) {

    }


}
