package com.fhzc.app.android.models;

import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.db.UserPreference;

import java.io.Serializable;

/**
 * @author : yingmu on 14-12-31.
 * @email : yingmu@mogujie.com.
 */
public class AudioMessage extends MessageEntity implements Serializable {

    private String audioPath = "";
    private static String duration;
    private int readStatus = Constant.AUDIO_UNREAD;


    public AudioMessage(MessageEntity entity) {
        // 父类主键

        userId = entity.getFromId();
        id = entity.getId();
        content = entity.getContent();
        messageType = entity.getDisplayType();
        sessionId = entity.getSessionKey();
        duration = entity.getDuration();
        statu = entity.getStatus();
        sendTime = entity.getCreated();
        locId =entity.getLocId();
    }

    public AudioMessage() {
    }

    public static AudioMessage parseFromNet(AudioMessage text, MessageEntity entity) {

        text.setStatus(Constant.MSG_SUCCESS);
        text.setDisplayType(Constant.SHOW_AUDIO_TYPE);
        text.setId(entity.getId());
        text.setCreated(entity.getCreated());
        text.setFromId(entity.getFromId());
        text.setSessionKey(entity.getSessionKey());
        return text;
    }

    public static AudioMessage parse(MessageEntity entity) {
        if (!entity.getDisplayType() .equals(Constant.SHOW_AUDIO_TYPE) ) {
            throw new RuntimeException("#AudioMessage# parseFromDB,not SHOW_AUDIO_TYPE");
        }
        AudioMessage audioMessage = new AudioMessage(entity);
        audioMessage.setAudioPath(Constant.AUDIO_PATH);
        audioMessage.setAudiolength(duration);
        return audioMessage;
    }

    public static AudioMessage parseFromSend(int duration,String sessionId) {
        AudioMessage textMessage = new AudioMessage();
        textMessage.setDuration(String.valueOf(duration));
        textMessage.setRead(1);

        textMessage.setDisplayType(Constant.SHOW_AUDIO_TYPE);
        textMessage.setStatus(Constant.MSG_SENDING);
        textMessage.setFromId(UserPreference.getUid());
        textMessage.setCreated((int)(System.currentTimeMillis()/1000));
        textMessage.setSessionKey(sessionId);
        long id = new MessageDao().insertRaw(textMessage);
        textMessage.setLocId((int) id);
        return textMessage;
    }

    /***
     * -------------------------------set/get----------------------------------
     */
    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getAudiolength() {
        return duration;
    }

    public void setAudiolength(String audiolength) {
        this.duration = audiolength;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }


}
