package com.fhzc.app.android.models;

import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.db.UserPreference;

import java.io.Serializable;

/**
 * @author : yingmu on 14-12-31.
 * @email : yingmu@mogujie.com.
 */
public class ImageMessage extends MessageEntity implements Serializable {

    /**
     * 本地保存的path
     */
    private String path = "";
    /**
     * 图片的网络地址
     */
    private String url = "";

    public ImageMessage() {
    }

    /**
     * 消息拆分的时候需要
     */
    public ImageMessage(MessageEntity entity) {
        /**父类的id*/
        userId = entity.getFromId();
        sessionId = entity.getSessionKey();
        content = entity.getContent();
        messageType = entity.getDisplayType();
        statu = entity.getStatus();
        sendTime = entity.getCreated();
        locId =entity.getLocId();
    }

    public static ImageMessage parseFromNet(ImageMessage text, MessageEntity entity) {
        text.setContent(entity.getContent());
        text.setStatus(Constant.MSG_SUCCESS);
        text.setDisplayType(Constant.SHOW_IMAGE_TYPE);
        text.setId(entity.getId());
        text.setCreated(entity.getCreated());
        text.setFromId(entity.getFromId());
        text.setSessionKey(entity.getSessionKey());
        return text;
    }

    public static ImageMessage parseFromSend(String text, String sessionId) {
        ImageMessage textMessage = new ImageMessage();
        textMessage.setContent(text);
        textMessage.setRead(1);
        textMessage.setDisplayType(Constant.SHOW_IMAGE_TYPE);
        textMessage.setStatus(Constant.MSG_SENDING);
        textMessage.setFromId(UserPreference.getUid());
        textMessage.setCreated((int) (System.currentTimeMillis() / 1000));
        textMessage.setSessionKey(sessionId);
        long id = new MessageDao().insertRaw(textMessage);
        textMessage.setLocId((int) id);
        return textMessage;
    }


    /**
     * -----------------------set/get------------------------
     */
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
