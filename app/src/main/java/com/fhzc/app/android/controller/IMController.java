package com.fhzc.app.android.controller;

import android.app.Activity;

import com.base.framwork.cach.preferce.PreferceManager;
import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.android.Logger;
import com.base.platform.utils.java.MapBuilder;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.dao.ContactDao;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.dao.SessionDao;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.ContactModel;
import com.fhzc.app.android.models.MessageEntity;
import com.fhzc.app.android.models.PullMessageMapModel;
import com.fhzc.app.android.models.PullMessageModel;
import com.fhzc.app.android.models.SessionModel;
import com.fhzc.app.android.utils.net.ConnectTool;

import java.io.File;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by User on 2016/7/14.
 */
public class IMController {
    private static IMController instance;
    private ScheduledExecutorService mSyncScheduler;

    public static IMController getInstance() {
        if (instance == null)
            instance = new IMController();
        return instance;
    }

    public void oneshotSync(final Activity context) {
        if (mSyncScheduler != null) {
            return;
        }
        mSyncScheduler = Executors.newSingleThreadScheduledExecutor();

        final Runnable syncRequest = new Runnable() {

            @Override
            public void run() {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            pull(Long.parseLong(PreferceManager.getInsance().getValueBYkey("version"+UserPreference.getUid())));
                        } catch (Exception e) {
                            pull(0);
                        }

                    }
                });
            }
        };

        mSyncScheduler.scheduleAtFixedRate(syncRequest, 0, Constant.MESSSAGE_SYNCING_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * 轮询
     *
     * @param version
     */
    public void pull(long version) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.yapull,
                    new MapBuilder<String, Object>()
                            .add("version", version)
                            .add("userId", UserPreference.getUid())
                            .getMap(), new PullCallBack(), PullMessageMapModel.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 发送消息
     *
     * @param toUserId
     * @param text
     * @param type
     * @param duration
     */
    public void publish(ViewNetCallBack callBack, int toUserId, String text, String type, int duration) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.publish,
                    new MapBuilder<String, Object>()
                            .add("toUserId", toUserId)
                            .add("userId", UserPreference.getUid())
                            .add("text", text)
                            .add("type", type)
                            .add("duration", duration)
                            .getMap(), callBack, MessageEntity.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 发送消息
     *
     * @param toUserId
     * @param text
     * @param type
     * @param duration
     */
    public void publishImage(ViewNetCallBack callBack, int toUserId, String text, String type, int duration, File file) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.publish,
                    new MapBuilder<String, Object>()
                            .add("toUserId", toUserId)
                            .add("userId", UserPreference.getUid())
                            .add("text", text)
                            .add("type", type)
                            .add("duration", duration)
                            .add("file", file)
                            .getMap(), callBack, MessageEntity.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    public void audioContent(ViewNetCallBack callBack, int id) {
        try {
            ConnectTool.httpRequest(
                    HttpConfig.audioContent,
                    new MapBuilder<String, Object>()
                            .add("messageId", id)
                            .getMap(), callBack, String.class);
        } catch (Exception e) {
            Logger.e(e.getLocalizedMessage(), e);
        }
    }

    private class PullCallBack implements ViewNetCallBack {
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
            PullMessageMapModel model = (PullMessageMapModel) result;
//            if (model.getGroups().size() > 0) {
                new Thread(new DataRunnable(model)).start();
//            }
        }
    }

    class DataRunnable implements Runnable {
        PullMessageMapModel model;

        public DataRunnable(PullMessageMapModel model) {
            this.model = model;
        }


        @Override
        public void run() {
            MessageDao dao = new MessageDao();
            ContactDao contactDao = new ContactDao();
            SessionDao sessionDao = new SessionDao();
            PreferceManager.getInsance().saveValueBYkey("version"+UserPreference.getUid(), String.valueOf(model.getVersion()));
            int messageCount = 0;
            for (PullMessageModel messageModel : model.getGroups()) {
                messageCount = messageModel.getMessages().size();
                for (MessageEntity messageEntity : messageModel.getMessages()) {
                    messageEntity.setRead(0);
                    messageEntity.setStatus(Constant.MSG_SUCCESS);
                    dao.insert(messageEntity);
                }
                for (ContactModel contactModel : messageModel.getGroupInfo()) {
                    if (contactModel.getUid() != UserPreference.getUid()) {
                        SessionModel model1 = new SessionModel();
                        model1.setFromId(contactModel.getUid());
                        model1.setSessionId(messageModel.getSessionId());
                        model1.setUnReadCount(dao.getUnReadCountBySessionId(model1.getSessionId()));
                        sessionDao.update(model1);
                    }
                    contactDao.update(contactModel);
                }
            }
            EventManager.getInstance().post(new IMEvent(messageCount));
        }

    }


}
