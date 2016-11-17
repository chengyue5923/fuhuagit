package com.fhzc.app.android.android.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.base.platform.utils.android.Logger;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.ChatAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.dao.SessionDao;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.SessionModel;
import com.fhzc.app.android.utils.IntentTool;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 16-7-14.
 */
public class ChatListActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.chat_list)
    ListView chatList;
    @Bind(R.id.systemMessageToolBar)
    CommonToolBar systemMessageToolBar;
    @Bind(R.id.systemChatLayout)
    LinearLayout systemChatLayout;
    private ChatAdapter chatAdapter;

    public void onEventMainThread(IMEvent event) {
        try{
            if (null != event) {
                List<SessionModel> list = new SessionDao().getAllSession();
                chatAdapter.setRes(list);
                chatAdapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        try{
            super.onResume();
            chatAdapter = new ChatAdapter(this);
            chatList.setAdapter(chatAdapter);
            List<SessionModel> list = new SessionDao().getAllSession();
            Logger.e("mesg"+list);
            chatAdapter.setRes(list);
            chatAdapter.notifyDataSetChanged();
        }catch (Exception e){
            Logger.e("exception"+e.getMessage());
        }
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                IntentTool.chat(ChatListActivity.this, chatAdapter.getItem(i).getSessionId(), -1);
            }
        });
        systemChatLayout.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_list;
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }

    @Override
    public void onConnectStart() {
        super.onConnectStart();
    }

    @Override
    public void onFail(Exception e) {
        super.onFail(e);
    }

    @Override
    public void onConnectEnd() {
        super.onConnectEnd();
    }

    @Override
    public void onClick(View v) {
        IntentTools.startSystemChat(ChatListActivity.this);
    }
}
