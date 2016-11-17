package com.fhzc.app.android.models;


public class MessageEntity implements java.io.Serializable {
    protected String sessionId;
    protected int id;
    protected int userId;
    protected int read = 1; //0:未读 1：已读

    protected String content;

    protected String messageType;
    protected int statu;
    protected String duration;
    protected long sendTime;
    protected int locId;

    // KEEP FIELDS - put your custom fields here


    // KEEP FIELDS END

    public MessageEntity() {
    }

    public int getUserId() {
        return userId;
    }

    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MessageEntity(int id, int fromId, String sessionKey, String content, String msgType, int created) {
        this.userId = fromId;
        this.sessionId = sessionKey;
        this.content = content;
        this.messageType = msgType;
        this.id = id;

        this.sendTime = created;

    }

    public int getFromId() {
        return userId;
    }

    public void setFromId(int fromId) {
        this.userId = fromId;
    }


    /**
     * Not-null value.
     */
    public String getSessionKey() {
        return sessionId;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setSessionKey(String sessionKey) {
        this.sessionId = sessionKey;
    }

    /**
     * Not-null value.
     */
    public String getContent() {
        return content;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setContent(String content) {
        this.content = content;
    }


    public String getDisplayType() {
        return messageType;
    }

    public void setDisplayType(String displayType) {
        this.messageType = displayType;
    }

    public int getStatus() {
        return statu;
    }

    public void setStatus(int status) {
        this.statu = status;
    }

    public int getCreated() {
        if ((sendTime + "").length() > 11) {
            return (int) (sendTime / 1000);
        } else {
            return (int) sendTime;
        }

    }

    public void setCreated(int created) {
        this.sendTime = created;
    }


}
