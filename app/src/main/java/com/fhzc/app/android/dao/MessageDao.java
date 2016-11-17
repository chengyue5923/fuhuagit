package com.fhzc.app.android.dao;

import android.content.ContentValues;

import com.base.framwork.cach.db.InsertRequest;
import com.base.framwork.cach.db.SqlRequest;
import com.base.platform.utils.java.ListUtil;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.MessageEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yanbo on 16-7-14.
 */
public class MessageDao extends BaseDao {
    public MessageDao() {
        super();
        createTable();
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS  message( " +
                " id        INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " msgId        INTEGER, " +
                " fromId       INTEGER, " +
                " userId       INTEGER, " +
                " type         VARCHAR, " +
                " duration     VARCHAR, " +
                " sessionId          VARCHAR," +
                " content      VARCHAR," +
                " creatTime      VARCHAR," +
                " read      INTEGER," +
                " status      INTEGER" +
                " )";
        opration.execSql(new SqlRequest(sql, new Object[]{}));
    }

    public int getUnReadCountBySessionId(String sessionId) {

        String sql = "select count(*) from message where sessionId = ? and read = 0";
        return opration.queryCount(new SqlRequest(sql, new String[]{sessionId}));
    }

    public int getUnReadCount() {

        String sql = "select count(*) from message where read = 0 and userId = ?";
        return opration.queryCount(new SqlRequest(sql, new String[]{String.valueOf(UserPreference.getUid())}));
    }

    public List<MessageEntity> getAllUnReadMessagesBySessionId(String sessionId) {
        List<MessageEntity> res = new ArrayList<MessageEntity>();
        String sql = "select * from message where sessionId = ? and read = 0";
        List<Map<String, String>> maps = opration.querySql(new SqlRequest(sql, new String[]{sessionId}));
        if (ListUtil.isNullOrEmpty(maps)) {
            return res;
        }
        for (Map<String, String> map : maps) {
            try {
                MessageEntity model = new MessageEntity();
//                model.setDuration(map.get("duration"));
                model.setId(Integer.valueOf(map.get("msgId")));
                model.setLocId(Integer.valueOf(map.get("id")));
                model.setCreated(Integer.valueOf(map.get("creatTime")));
                model.setFromId(Integer.valueOf(map.get("fromId")));
                model.setSessionKey(map.get("sessionId"));
                model.setContent(map.get("content"));
                model.setDisplayType(map.get("type"));
                model.setStatus(Integer.parseInt(map.get("status")));
                res.add(model);
            } catch (Exception e) {
                MessageEntity model = new MessageEntity();
                model.setContent(map.get("content"));
                model.setDisplayType(map.get("type"));

                model.setCreated(Integer.valueOf(map.get("creatTime")));
                model.setStatus(Integer.parseInt(map.get("status")));
                model.setFromId(Integer.valueOf(map.get("fromId")));
                model.setDuration(map.get("duration"));
                model.setLocId(Integer.valueOf(map.get("id")));
                res.add(model);
            }
        }

        return res;
    }

    public List<MessageEntity> getAllMessagesBySessionId(String sessionId) {
        List<MessageEntity> res = new ArrayList<MessageEntity>();
        String sql = "select * from message where sessionId = ?";
        List<Map<String, String>> maps = opration.querySql(new SqlRequest(sql, new String[]{sessionId}));
        if (ListUtil.isNullOrEmpty(maps)) {
            return res;
        }
        for (Map<String, String> map : maps) {
            try {
                MessageEntity model = new MessageEntity();
                model.setDuration(map.get("duration"));
                model.setId(Integer.valueOf(map.get("msgId")));
                model.setLocId(Integer.valueOf(map.get("id")));
                model.setCreated(Integer.valueOf(map.get("creatTime")));
                model.setFromId(Integer.valueOf(map.get("fromId")));
                model.setSessionKey(map.get("sessionId"));
                model.setContent(map.get("content"));
                model.setDisplayType(map.get("type"));
                model.setStatus(Integer.parseInt(map.get("status")));
                model.setLocId(Integer.parseInt(map.get("id")));
                res.add(model);
            } catch (Exception e) {
                MessageEntity model = new MessageEntity();
                model.setId(Integer.valueOf(map.get("msgId")));
                model.setContent(map.get("content"));
                model.setDisplayType(map.get("type"));
                model.setCreated(Integer.valueOf(map.get("creatTime")));
                model.setStatus(Integer.parseInt(map.get("status")));
                model.setFromId(Integer.valueOf(map.get("fromId")));
                model.setDuration(map.get("duration"));
                model.setLocId(Integer.parseInt(map.get("id")));
                res.add(model);
            }
        }

        return res;
    }

    public boolean updateMessageToReadedBySessionId(String sessionId) {
        if (StringTools.isNullOrEmpty(sessionId)) {
            return false;
        }

        String sql = "update message set read = 1 where sessionId = ?";
        SqlRequest sqlRequest = new SqlRequest(sql, new Object[]{sessionId});
        return opration.execSql(sqlRequest);
    }

    public MessageEntity getLastMessageBySessionId(String sessionId) {
        if (StringTools.isNullOrEmpty(sessionId)) {
            return new MessageEntity();
        }
        MessageEntity entity = new MessageEntity();
        String sql = "select * from message where sessionId = ? order by creatTime desc limit 0,1";
        List<Map<String, String>> maps = opration.querySql(new SqlRequest(sql, new String[]{sessionId}));
        if (!maps.isEmpty()) {
            Map<String, String> map = maps.get(0);
            try {
                entity.setLocId(Integer.valueOf(map.get("id")));
                entity.setDuration(map.get("duration"));
                entity.setId(Integer.valueOf(map.get("msgId")));
                entity.setCreated(Integer.valueOf(map.get("creatTime")));
                entity.setFromId(Integer.valueOf(map.get("fromId")));
                entity.setSessionKey(map.get("sessionId"));
                entity.setContent(map.get("content"));
                entity.setDisplayType(map.get("type"));

            } catch (Exception e) {

                entity.setContent(map.get("content"));
                entity.setDisplayType(map.get("type"));
                entity.setCreated(Integer.valueOf(map.get("creatTime")));
                entity.setStatus(Integer.parseInt(map.get("status")));
                entity.setFromId(Integer.valueOf(map.get("fromId")));
                entity.setDuration(map.get("duration"));
                entity.setLocId(Integer.valueOf(map.get("id")));
            }
        }
        return entity;
    }


    public boolean upadte(MessageEntity model) {
        if (model == null) {
            return false;
        }
        String sql = "replace into message (id,userId,msgId,fromId,type,sessionId,content,read,creatTime,duration,status) values (?,?,?,?,?,?,?,?,?,?,?)";
        SqlRequest sqlRequest = new SqlRequest(sql, new Object[]{model.getLocId(), UserPreference.getUid(), model.getId(), model.getFromId(), model.getDisplayType(), model.getSessionKey(), model.getContent(), model.getRead(), model.getCreated(), model.getDuration(), model.getStatus()});
        return opration.execSql(sqlRequest);
    }

    public boolean updateStatus(MessageEntity model) {
        if (model == null) {
            return false;
        }
        String sql = "update message set status = 2 where id = ?";
        SqlRequest sqlRequest = new SqlRequest(sql, new Object[]{model.getLocId()});
        return opration.execSql(sqlRequest);
    }

    public boolean insert(MessageEntity model) {
        if (model == null) {
            return false;
        }
        String sql = "replace into message (msgId,userId,fromId,type,sessionId,content,read,creatTime,duration,status) values (?,?,?,?,?,?,?,?,?,?)";
        SqlRequest sqlRequest = new SqlRequest(sql, new Object[]{model.getId(), UserPreference.getUid(), model.getFromId(), model.getDisplayType(), model.getSessionKey(), model.getContent(), model.getRead(), model.getCreated(), model.getDuration(), model.getStatus()});
        return opration.execSql(sqlRequest);
    }

    public long insertRaw(MessageEntity model) {
        if (model == null) {
            return -1;
        }
        ContentValues cv = new ContentValues();
        cv.put("type", model.getDisplayType());
        cv.put("duration", model.getDuration());
        cv.put("content", model.getContent());
        cv.put("fromId", model.getFromId());
        cv.put("sessionId", model.getSessionKey());
        cv.put("creatTime", model.getCreated());
        cv.put("status", model.getCreated());
        cv.put("userId", UserPreference.getUid());
        InsertRequest sqlRequest = new InsertRequest("message", cv);
        return opration.insert(sqlRequest);
    }
}
