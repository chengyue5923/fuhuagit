package com.fhzc.app.android.android.ui.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.MessageAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.MessageEntity;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.CommonUtil;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 16-7-12.
 */
public class SystemChatActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.systemChatToolBar)
    CommonToolBar systemChatToolBar;
    @Bind(R.id.message_list)
    ListView messageList;
    @Bind(R.id.feedbackLayout)
    RelativeLayout feedbackLayout;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    @Bind(R.id.contactUstext)
    LinearLayout contactUstext;
    private MessageAdapter mAdapter;
    String sessionId = "e572abb3-1b95-4695-b10a-698b8688af9d";

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        feedbackLayout.setOnClickListener(this);
        contactUstext.setOnClickListener(this);
    }

    public void onEventMainThread(IMEvent event) {
        List<MessageEntity> list = new MessageDao().getAllUnReadMessagesBySessionId(sessionId);
        for (MessageEntity entity : list) {
            entity.setRead(1);
            new MessageDao().upadte(entity);
            mAdapter.addItem(entity);
        }
        if (list.size() > 0)
            scrollToBottomListItem();
    }

    /**
     * @Description 滑动到列表底部
     */
    private void scrollToBottomListItem() {
        if (messageList != null) {
            messageList.setSelection(mAdapter.getCount() + 1);
        }
    }

    @Override
    protected void initData() {
        mAdapter = new MessageAdapter(this);
        messageList.setAdapter(mAdapter);
        MessageDao dao = new MessageDao();
        dao.updateMessageToReadedBySessionId(sessionId);
        List<MessageEntity> list = dao.getAllMessagesBySessionId(sessionId);
        Collections.sort(list, new ChatActivity.MessageTimeComparator());
        for (MessageEntity entity : list) {
            mAdapter.addItem(entity);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_system_chat;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.feedbackLayout:
                IntentTools.startFeedback(SystemChatActivity.this);
                break;
            case R.id.contactUstext:
                CommonUtil.call(SystemChatActivity.this, "400-610-6100");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
    }
}
