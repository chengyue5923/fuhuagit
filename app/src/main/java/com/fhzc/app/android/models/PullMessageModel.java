package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 16-7-14.
 */
public class PullMessageModel implements Serializable {
    String sessionId;
    List<MessageEntity> messages;
    List<ContactModel> groupInfo;
    List<Integer> party;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }

    public List<ContactModel> getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(List<ContactModel> groupInfo) {
        this.groupInfo = groupInfo;
    }

    public List<Integer> getParty() {
        return party;
    }

    public void setParty(List<Integer> party) {
        this.party = party;
    }
}
