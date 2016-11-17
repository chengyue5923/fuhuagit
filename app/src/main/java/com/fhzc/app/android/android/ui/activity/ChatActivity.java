package com.fhzc.app.android.android.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.MessageAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.im.MessageSendViewNew;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.IMController;
import com.fhzc.app.android.dao.ContactDao;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.dao.SessionDao;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.event.IMEvent;
import com.fhzc.app.android.models.AudioMessage;
import com.fhzc.app.android.models.ContactModel;
import com.fhzc.app.android.models.ImageMessage;
import com.fhzc.app.android.models.MemberModel;
import com.fhzc.app.android.models.MessageEntity;
import com.fhzc.app.android.models.SessionModel;
import com.fhzc.app.android.models.TextMessage;
import com.fhzc.app.android.utils.CacheUtil;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.AudioRecorder;
import com.fhzc.app.android.utils.im.CommonUtil;

import java.io.File;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 16-7-12.
 */
public class ChatActivity extends BaseActivity implements View.OnClickListener,
        MessageSendViewNew.MessageSendViewDelegate, CommonToolBar.ClickRightListener {


    @Bind(R.id.chatToolBar)
    CommonToolBar chatToolBar;
    @Bind(R.id.message_list)
    ListView messageList;
    @Bind(R.id.swipe_Refresh)
    SwipeRefreshLayout swipeRefresh;
    @Bind(R.id.message_send_view)
    MessageSendViewNew messageSendView;
    @Bind(R.id.rootLayout)
    LinearLayout rootLayout;
    private MessageAdapter mAdapter;
    private String audioFileName;
    private float y1, y2;
    private Dialog soundVolumeDialog = null;
    private ImageView soundVolumeImg = null;
    private LinearLayout soundVolumeLayout = null;
    private Uri mImageCaptureUri;
    private long mAudioRecordStartTimeMS;
    private MediaRecorder recorder;
    private String sessionId;
    private int fromUserId;
    private static final int SELECT_IMAGE_RESULT_CODE = 999;
    private static final int CAPTURE_IMAGE_RESULT_CODE = 1000;


    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        messageSendView.setDelegate(this);
        initSoundVolumeDlg();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    protected void initEvent() {
        if (!EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().register(this);
        }
        chatToolBar.setClickRightListener(this);
    }

    @Override
    protected void initData() {
        initRecord();
        mAdapter = new MessageAdapter(this);
        messageList.setAdapter(mAdapter);
        sessionId = getIntent().getStringExtra("sessionId");


        if (!StringTools.isNullOrEmpty(sessionId)) {
            fromUserId = new SessionDao().getFromIdBySessionId(sessionId);
        } else {
            fromUserId = getIntent().getIntExtra("fromUserId", -1);
            sessionId = new SessionDao().getSessionIdByUid(fromUserId);
        }
        if (fromUserId == -1 || StringTools.isNullOrEmpty(sessionId))
            return;
        MessageDao dao = new MessageDao();
        dao.updateMessageToReadedBySessionId(sessionId);
        chatToolBar.setTitle(new ContactDao().getUserById(fromUserId).getName());
        List<MessageEntity> list = dao.getAllMessagesBySessionId(sessionId);
        Collections.sort(list, new MessageTimeComparator());
        for (MessageEntity entity : list) {
            mAdapter.addItem(entity);
        }
        scrollToBottomListItem();
    }


    public static class MessageTimeComparator implements Comparator<MessageEntity> {
        @Override
        public int compare(MessageEntity lhs, MessageEntity rhs) {
            if (lhs.getCreated() == rhs.getCreated()) {
                return lhs.getId() - rhs.getId();
            }
            return lhs.getCreated() - rhs.getCreated();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        MessageEntity entity = (MessageEntity) result;
        new MessageDao().insert(entity);

        SessionModel sessionModel = new SessionModel();
        sessionModel.setFromId(fromUserId);
        sessionModel.setSessionId(entity.getSessionKey());
        sessionModel.setUnReadCount(0);
        new SessionDao().update(sessionModel);
        mAdapter.addItem(entity);
        scrollToBottomListItem();
    }

    @Override
    public void onClick(View view) {
    }

    private void start(String path) {
        try {
            recorder.setOutputFile(path);
            recorder.prepare();
            recorder.start();
        } catch (Exception e) {
        }
    }

    private void initRecord() {
        try {
            recorder = new MediaRecorder();
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            recorder.setAudioChannels(2);
            recorder.setAudioSamplingRate(8000);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                Log.e(AudioRecorder.class.getName(), e.getMessage());
            } else {
                Log.e(AudioRecorder.class.getName(),
                        "Unknown error occured while initializing recording");
            }
        }
    }

    /**
     * @Description 初始化音量对话框
     */
    private void initSoundVolumeDlg() {
        soundVolumeDialog = new Dialog(this, R.style.SoundVolumeStyle);
        soundVolumeDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        soundVolumeDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        soundVolumeDialog.setContentView(R.layout.im_sound_volume_dialog);
        soundVolumeDialog.setCanceledOnTouchOutside(true);
        soundVolumeImg = (ImageView) soundVolumeDialog.findViewById(R.id.sound_volume_img);
        soundVolumeLayout = (LinearLayout) soundVolumeDialog.findViewById(R.id.sound_volume_bk);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (EventManager.getInstance().isRegister(this)) {
            EventManager.getInstance().unregister(this);
        }
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
    public void touchSendTextMessageButton(String text) {
        final TextMessage messageEntity = TextMessage.parseFromSend(text, sessionId);

        IMController.getInstance().publish(new ViewNetCallBack() {
            @Override
            public void onConnectStart() {
                mAdapter.addItem(messageEntity);
            }

            @Override
            public void onConnectEnd() {

            }

            @Override
            public void onFail(Exception e) {
                messageEntity.setStatus(Constant.MSG_FAILURE);
                new MessageDao().updateStatus(messageEntity);
                mAdapter.updateItemState(messageEntity, false);
            }

            @Override
            public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
                MessageEntity entity = (MessageEntity) result;
//                MessageEntity entity = model.getData();
                TextMessage message = TextMessage.parseFromNet(messageEntity, entity);
                new MessageDao().upadte(message);

                SessionModel sessionModel = new SessionModel();
                sessionModel.setFromId(fromUserId);
                sessionModel.setSessionId(entity.getSessionKey());
                sessionModel.setUnReadCount(0);
                new SessionDao().update(sessionModel);
                mAdapter.updateItemState(message, true);
            }
        }, fromUserId, text, Constant.SHOW_ORIGIN_TEXT_TYPE, 0);
    }


    @Override
    public void touchSwitchButton() {

    }

    @Override
    public void touchDownAudioRecordButton(MotionEvent event) {
        if (soundVolumeDialog.isShowing()) {
            return;
        }
        audioFileName = Constant.AUDIO_TEMP_PATH + File.separator + new Date().getTime() + ".amr";
        //stop video and audio player
        mAudioRecordStartTimeMS = System.currentTimeMillis();
        start(audioFileName);
        y1 = event.getY();
        soundVolumeImg.setImageResource(R.drawable.im_sound_volume_01);
        soundVolumeImg.setVisibility(View.VISIBLE);
        soundVolumeLayout.setBackgroundResource(R.drawable.i_sound_volume_default_bk);
        soundVolumeDialog.show();
    }

    @Override
    public void touchMoveAudioRecordButton(MotionEvent event) {
        y2 = event.getY();
        if (y1 - y2 > 180) {
            soundVolumeImg.setVisibility(View.GONE);
            soundVolumeLayout.setBackgroundResource(R.drawable.im_sound_volume_cancel_bk);
        } else {
            soundVolumeImg.setVisibility(View.VISIBLE);
            soundVolumeLayout.setBackgroundResource(R.drawable.i_sound_volume_default_bk);
        }
    }


    @Override
    public void touchUpAudioRecordButton(MotionEvent event) {
//        y2 = event.getY();
        if (soundVolumeDialog.isShowing()) {
            soundVolumeDialog.dismiss();
        }
        if (Math.abs(y1 - y2) <= 180) {
            final long msDuration = System.currentTimeMillis() - mAudioRecordStartTimeMS;
            if (msDuration >= 1000) {
                if (msDuration < Constant.MAX_SOUND_RECORD_TIME) {
                    //// TODO: 2016/7/13 发送
//                    recorder.release();
                    new UploadAudioTask(audioFileName, (int) msDuration / 1000).execute();
                }
            } else {
                soundVolumeImg.setVisibility(View.GONE);
                soundVolumeLayout
                        .setBackgroundResource(R.drawable.im_sound_volume_short_tip_bk);
                soundVolumeDialog.show();

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    public void run() {
                        if (soundVolumeDialog.isShowing())
                            soundVolumeDialog.dismiss();
                        this.cancel();
                    }
                }, 500);
            }
        }
    }

    @Override
    public void touchPickCameraPhotoButton() {
        selectTakeCameraIndex();
    }

    @Override
    public void touchLibraryPhotoButton() {
        selectFileFromLocal();
    }

    private class UploadAudioTask extends AsyncTask<String[], Boolean, String> {

        String path;
        int msDuration;

        public UploadAudioTask(String path, int msDuration) {
            this.path = path;
            this.msDuration = msDuration;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String[]... params) {
            try {
                return CommonUtil.encodeBase64File(path);
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String o) {
            super.onPostExecute(o);
            final AudioMessage messageEntity = AudioMessage.parseFromSend(msDuration, sessionId);
            IMController.getInstance().publish(new ViewNetCallBack() {
                @Override
                public void onConnectStart() {
                    mAdapter.addItem(messageEntity);
                }

                @Override
                public void onConnectEnd() {

                }

                @Override
                public void onFail(Exception e) {
                    messageEntity.setStatus(Constant.MSG_FAILURE);
                    new MessageDao().updateStatus(messageEntity);
                    mAdapter.updateItemState(messageEntity, false);
                }

                @Override
                public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
                    MessageEntity entity = (MessageEntity) result;
//                    MessageEntity entity = model.getData();
                    AudioMessage message = AudioMessage.parseFromNet(messageEntity, entity);
                    new MessageDao().upadte(message);

                    SessionModel sessionModel = new SessionModel();
                    sessionModel.setFromId(fromUserId);
                    sessionModel.setSessionId(entity.getSessionKey());
                    sessionModel.setUnReadCount(0);
                    new SessionDao().update(sessionModel);
                    mAdapter.updateItemState(message, true);
                }
            }, fromUserId, o, Constant.SHOW_AUDIO_TYPE, msDuration);
        }
    }

    /**
     * 选择文件
     */
    private void selectFileFromLocal() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, SELECT_IMAGE_RESULT_CODE);
    }

    private void selectTakeCameraIndex() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mImageCaptureUri = Uri.fromFile(new File(CacheUtil.getCacheDirectory(this),
                "pick_photo_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        startActivityForResult(intent, CAPTURE_IMAGE_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = "";
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_IMAGE_RESULT_CODE) {
                path = getPath(data.getData());
            }
            if (requestCode == CAPTURE_IMAGE_RESULT_CODE) {
                path = mImageCaptureUri.getPath();
            }
            if (!new File(path).exists()) {
                return;
            }

            publishConversationPhotoMessage(path, fromUserId);
        }

    }

    public void publishConversationPhotoMessage(
            String photoFile, int toUserId
            ) {
        try {
            final ImageMessage message = ImageMessage.parseFromSend(photoFile, sessionId);
            IMController.getInstance().publishImage(new ViewNetCallBack() {
                @Override
                public void onConnectStart() {

                    mAdapter.addItem(message);
                }

                @Override
                public void onConnectEnd() {

                }

                @Override
                public void onFail(Exception e) {
                    message.setStatus(Constant.MSG_FAILURE);
                    new MessageDao().updateStatus(message);
                    mAdapter.updateItemState(message, false);
                }

                @Override
                public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
                     MessageEntity model = (MessageEntity)result;
                    ImageMessage imageMessage = ImageMessage.parseFromNet(message, model);
                    new MessageDao().upadte(imageMessage);
                    SessionModel sessionModel = new SessionModel();
                    sessionModel.setFromId(fromUserId);
                    sessionModel.setSessionId(model.getSessionKey());
                    sessionModel.setUnReadCount(0);
                    new SessionDao().update(sessionModel);
                    mAdapter.updateItemState(imageMessage, true);
                }
            }, toUserId, "", "image", 0, new File(photoFile));
        } catch (Exception e) {
        }
    }

    public String getPath(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    @Override
    public void OnClickRight(View view) {
        if (UserPreference.isCustomer()) {
            IntentTools.startAccountionDetailActivity(ChatActivity.this,false);
        } else {
            ContactModel model = new ContactDao().getUserById(fromUserId);
            MemberModel memberModel = new MemberModel();
            if (model != null) {
                memberModel.setUid(fromUserId);
                memberModel.setAvatar(model.getAvatar());
                memberModel.setRealname(model.getName());
            }
            IntentTools.startCustomerDetail(ChatActivity.this,memberModel);
        }
    }
}
