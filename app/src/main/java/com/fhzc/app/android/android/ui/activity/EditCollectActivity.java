package com.fhzc.app.android.android.ui.activity;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.EditCollectAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.controller.UserController;
import com.fhzc.app.android.models.CollectMapModel;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/8/4.
 */
public class EditCollectActivity extends BaseActivity implements CommonToolBar.ClickRightListener,View.OnClickListener {


    @Bind(R.id.editCollectToolBar)
    CommonToolBar editCollectToolBar;
    @Bind(R.id.editCollectList)
    ListView editCollectList;
    @Bind(R.id.selectedCount)
    TextView selectedCount;
    @Bind(R.id.cancelAttention)
    TextView cancelAttention;
    private EditCollectAdapter adapter;
    CollectMapModel model;
    int position;
    List<Object> list = new ArrayList<>();
    private List<Object> ids = new ArrayList<>();

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        cancelAttention.setOnClickListener(this);

    }

    @Override
    protected void initEvent() {
        editCollectToolBar.setClickRightListener(this);
    }

    @Override
    protected void initData() {
        model = (CollectMapModel) getIntent().getSerializableExtra("model");
        position = getIntent().getIntExtra("position", -1);
        if (model == null || position == -1)
            return;
        adapter = new EditCollectAdapter(this, ids);
        adapter.setOnCheckListener(new EditCollectAdapter.OnCheckListener() {
            @Override
            public void onCheck() {
                selectedCount.setText(String.valueOf(ids.size()));
                cancelAttention.setBackground(getResources().getDrawable(R.drawable.common_blue_selector_bg));
            }
        });
        editCollectList.setAdapter(adapter);
        switch (position) {
            case 0:
                list.addAll(model.getProduct());
                break;
            case 1:
                list.addAll(model.getReport());
                break;
            case 2:
                list.addAll(model.getActivity());
                break;
            case 3:
                list.addAll(model.getRights());
                break;
        }
        adapter.setList(list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_collect;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

        try {
            JSONObject object = new JSONObject(o.toString());
            if (object.getInt("code") == 200) {
                ToastTool.show("取消成功");
                finish();
            } else {
                ToastTool.show(object.getString("message"));
            }

        } catch (Exception e) {
            ToastTool.show("取消失败");
        }
    }

    @Override
    public void OnClickRight(View view) {
        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancelAttention:
                    switch (position) {
                        case 0:
                            if(adapter.getIds().size()==0){
                                ToastTool.show("请选择相应的产品");
                                break;
                            }
                            String typeid="";
                            for(int i=0;i<adapter.getIds().size();i++){
                                if(i==0){
                                    typeid=model.getProduct().get(0).getId()+"";
                                }else{
                                    typeid=typeid+","+model.getProduct().get(i).getId();
                                }
                            }
                            UserController.getInstance().cancelFocusAttention(this,0,typeid);
                            break;
                        case 1:
                            if(adapter.getIds().size()==0){
                                ToastTool.show("请选择相应的报告");
                                break;
                            }
                            String typereportid="";
                            for(int i=0;i<adapter.getIds().size();i++){
                                if(i==0){
                                    typereportid=model.getReport().get(0).getId()+"";
                                }else{
                                    typereportid=typereportid+","+model.getReport().get(i).getId();
                                }
                            }
                            UserController.getInstance().cancelFocusAttention(this,1,typereportid);
                            break;
                        case 2:
                            if(adapter.getIds().size()==0){
                                ToastTool.show("请选择相应的活动");
                                break;
                            }
                            String typeactivityid="";
                            for(int i=0;i<adapter.getIds().size();i++){
                                if(i==0){
                                    typeactivityid=model.getActivity().get(0).getId()+"";
                                }else{
                                    typeactivityid=typeactivityid+","+model.getActivity().get(i).getId();
                                }
                            }
                            UserController.getInstance().cancelFocusAttention(this,2,typeactivityid);
                            break;
                        case 3:
                            if(adapter.getIds().size()==0){
                                ToastTool.show("请选择相应的权益");
                                break;
                            }
                            String typerightid="";
                            for(int i=0;i<adapter.getIds().size();i++){
                                if(i==0){
                                    typerightid=model.getRights().get(0).getId()+"";
                                }else{
                                    typerightid=typerightid+","+model.getRights().get(i).getId();
                                }
                            }
                            UserController.getInstance().cancelFocusAttention(this,3,typerightid);
                            break;
                    }

                break;
        }
    }
}
