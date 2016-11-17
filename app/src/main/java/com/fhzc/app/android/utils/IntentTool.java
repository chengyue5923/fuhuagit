package com.fhzc.app.android.utils;

import android.app.Activity;
import android.content.Intent;

import com.fhzc.app.android.android.ui.activity.ChatActivity;

/**
 * Created by User on 2016/7/16.
 */
public class IntentTool {
    public static void chat(Activity ac, String sessionId,int fromUserId) {


        Intent intent = new Intent(ac, ChatActivity.class);
        intent.putExtra("sessionId", sessionId);
        intent.putExtra("fromUserId", fromUserId);
        ac.startActivity(intent);
    }
}
