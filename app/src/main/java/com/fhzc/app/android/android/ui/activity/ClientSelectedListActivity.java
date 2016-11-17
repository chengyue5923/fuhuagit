package com.fhzc.app.android.android.ui.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.ClientSelectedListAdapter;
import com.fhzc.app.android.android.ui.view.widget.EmptyLayout;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.models.MemberModel;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 客户列表页
 * Created by lenovo on 2016/7/11.
 */
public class ClientSelectedListActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.clientList)
    ListView clientList;
    @Bind(R.id.confirmShare)
    TextView confirmShare;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    private ClientSelectedListAdapter clientListAdapter;
    private List<MemberModel> list = new ArrayList<>();
    private EmptyLayout emptyLayout;

    String type;

    int typeId;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        clientListAdapter = new ClientSelectedListAdapter(this, list);
        emptyLayout = new EmptyLayout(this, rootLayout);
    }

    @Override
    protected void initEvent() {
        if (getIntent().getSerializableExtra("type") != null && (getIntent().getSerializableExtra("typeId") != null)) {
            type = (String) getIntent().getSerializableExtra("type");
            typeId = (int) getIntent().getSerializableExtra("typeId");
        }
        confirmShare.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        clientList.setAdapter(clientListAdapter);
        UserController.getInstance().customerList(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_client_list;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirmShare:
                if (clientListAdapter.getList().size() == 0) {
                    ToastTool.show("请选择客户");
                } else {
                    String s = "";
                    for (int i = 0; i < clientListAdapter.getList().size(); i++) {
                        if (i == 0) {
                            s = String.valueOf(clientListAdapter.getList().get(0).getUid());
                        } else {
                            s = s + "," + clientListAdapter.getList().get(i).getUid();
                        }
                    }
                    UserController.getInstance().shareForCustomer(this, s, type, typeId);
                }
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
        if (flag == HttpConfig.customerList.getType()) {
           try{
               JSONObject object = new JSONObject(o.toString());
               if (object.getInt("code") == 200) {
                   List<MemberModel> list = (List<MemberModel>) result;
                   emptyLayout.showSuccess(list.size()<0);
                   clientListAdapter.setRes(list);
               } else {
                   ToastTool.show(object.getString("message"));
                   emptyLayout.showSuccess(false);
               }
           }catch (Exception e){
               e.printStackTrace();
           }
        }
        if (flag == HttpConfig.shareForCostomer.getType()) {
            try {
                emptyLayout.showSuccess(false);
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    ToastTool.show("分享成功");
                    clientListAdapter.notifyDataSetChanged();
                } else {
                    ToastTool.show(object.getString("message"));
                }
            } catch (Exception e) {
                ToastTool.show("分享失败");
            }

        }
    }


}
