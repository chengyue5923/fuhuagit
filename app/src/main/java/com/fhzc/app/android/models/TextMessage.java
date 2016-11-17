package com.fhzc.app.android.models;

import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.db.UserPreference;

import java.io.Serializable;


/**
 * @author : yingmu on 14-12-31.
 * @email : yingmu@mogujie.com.
 */
public class TextMessage extends MessageEntity implements Serializable {

    public TextMessage(MessageEntity entity) {
        /**父类的id*/
        messageType = entity.getDisplayType();
        userId = entity.getFromId();
        sessionId = entity.getSessionKey();
        content = entity.getContent();
        statu = entity.getStatus();
        sendTime = entity.getCreated();
        locId = entity.getLocId();
    }

    public TextMessage() {
    }

    public static TextMessage parseFromNet(TextMessage text, MessageEntity entity) {

        text.setStatus(Constant.MSG_SUCCESS);
        text.setDisplayType(Constant.SHOW_ORIGIN_TEXT_TYPE);
        text.setId(entity.getId());
        text.setCreated(entity.getCreated());
        text.setFromId(entity.getFromId());
        text.setSessionKey(entity.getSessionKey());
        return text;
    }


    public static TextMessage parseFromSend(String text, String sessionId) {
        TextMessage textMessage = new TextMessage();
        textMessage.setContent(text);
        textMessage.setRead(1);
        textMessage.setDisplayType(Constant.SHOW_ORIGIN_TEXT_TYPE);
        textMessage.setStatus(Constant.MSG_SENDING);
        textMessage.setFromId(UserPreference.getUid());
        textMessage.setCreated((int) (System.currentTimeMillis() / 1000));
        textMessage.setSessionKey(sessionId);
        long id = new MessageDao().insertRaw(textMessage);
        textMessage.setLocId((int) id);
        return textMessage;
    }




}
