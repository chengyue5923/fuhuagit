package com.fhzc.app.android.android.ui.activity.systemservice;

import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.AccountProductAdapter;
import com.fhzc.app.android.models.RightFinancialDetailModel;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 *  我的理财师
 * Created by lenovo on 2016/7/11.
 */
public class FhMyAccountantActivity extends BaseActivity implements View.OnClickListener{


    @Bind(R.id.accountListView)
    ListView accountListView;

    AccountProductAdapter historyAdapter;
    @Override
    protected void initView() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        ButterKnife.bind(this);

    }

    @Override
    protected void initEvent() {
        accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentTools.startAccountionDetailActivity(FhMyAccountantActivity.this,true);
            }
        });
    }

    @Override
    protected void initData() {
        historyAdapter =new AccountProductAdapter(this);

        List<RightFinancialDetailModel> listmodel=new ArrayList<>();
        for(int i=0;i<10;i++){
            listmodel.add(new RightFinancialDetailModel());
        }
        historyAdapter.setRes(listmodel);

        accountListView.setAdapter(historyAdapter);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_accountant_financial;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){

        }
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }
}
