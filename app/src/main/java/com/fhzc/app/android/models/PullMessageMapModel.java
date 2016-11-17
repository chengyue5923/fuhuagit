package com.fhzc.app.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanbo on 16-7-15.
 */
public class PullMessageMapModel implements Serializable {
    List<PullMessageModel> groups;
    long version;

    public List<PullMessageModel> getGroups() {
        return groups;
    }

    public void setGroups(List<PullMessageModel> groups) {
        this.groups = groups;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "PullMessageMapModel{" +
                "groups=" + groups +
                ", version=" + version +
                '}';
    }
}
