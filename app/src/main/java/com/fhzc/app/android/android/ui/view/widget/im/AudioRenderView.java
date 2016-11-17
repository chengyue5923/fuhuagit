package com.fhzc.app.android.android.ui.view.widget.im;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.framwork.net.lisener.ViewNetCallBack;
import com.fhzc.app.android.R;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.controller.IMController;
import com.fhzc.app.android.models.AudioMessage;
import com.fhzc.app.android.models.ContactModel;
import com.fhzc.app.android.models.MessageEntity;
import com.fhzc.app.android.utils.im.AudioMessagePlayer;
import com.fhzc.app.android.utils.im.CommonUtil;

import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

/**
 * @author : yingmu on 15-1-9.
 * @email : yingmu@mogujie.com.
 */
public class AudioRenderView extends BaseMsgRenderView {

    /**
     * 可点击的消息体
     */
    private View messageLayout;
    /**
     * 播放动画的view
     */
    private View audioAnttView;

    /**
     * 语音时长
     */
    private TextView audioDuration;

    /**
     * 对外暴露的监听器，点击
     */
    private BtnImageListener btnImageListener;

    public interface BtnImageListener {
        public void onClickUnread();

        public void onClickReaded();
    }

    public void setBtnImageListener(BtnImageListener btnImageListener) {
        this.btnImageListener = btnImageListener;
    }

    public AudioRenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public static AudioRenderView inflater(Context ctx, ViewGroup viewGroup, boolean isMine) {

        int resoure = isMine ? R.layout.im_mine_audio_message_item : R.layout.im_other_audio_message_item;
        //im_other_audio_message_item
        AudioRenderView audioRenderView = (AudioRenderView) LayoutInflater.from(ctx).inflate(resoure, viewGroup, false);
        audioRenderView.setMine(isMine);
        audioRenderView.setParentView(viewGroup);
        return audioRenderView;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        messageLayout = findViewById(R.id.message_layout);
        audioAnttView = findViewById(R.id.audio_antt_view);
        audioDuration = (TextView) findViewById(R.id.audio_duration);
    }


    /**
     * 控件赋值
     * 是不是采用callback的形式
     *
     * @param messageEntity
     * @param userEntity
     */
    @Override
    public void render(final MessageEntity messageEntity, final ContactModel userEntity, final Context ctx) {
        super.render(messageEntity, userEntity, ctx);
        final AudioMessage audioMessage = (AudioMessage) messageEntity;

        final String audioPath = Constant.AUDIO_PATH + File.separator + audioMessage.getId() + ".amr";
        audioDuration.setText(audioMessage.getDuration());
        AudioMessagePlayer.getInstance().setmDelegate(new AudioMessagePlayer.AudioMessagePlayerDelegate() {
            @Override
            public Activity getAudioMessagePlayerActivity() {
                return null;
            }

            @Override
            public void playComplete() {
                stopAnimation();
            }
        });
        messageLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 上层回调事件
                if (!new File(audioPath).exists()) {
                    IMController.getInstance().audioContent(new ViewNetCallBack() {
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
                            try {
                                JSONObject object = new JSONObject((String) o);
                                String code = object.getString("message");
                                CommonUtil.decoderBase64File(code, audioPath);
                                startAnimation();
                                AudioMessagePlayer.getInstance().clickAudioMessageView(audioPath);
                            } catch (Exception e) {
                            }

                        }
                    }, audioMessage.getId());
                } else {
                    startAnimation();
                    AudioMessagePlayer.getInstance().clickAudioMessageView(audioPath);
                }

            }
        });
    }
    // 是否开启播放动画

    public void startAnimation() {
        AnimationDrawable animationDrawable = (AnimationDrawable) audioAnttView.getBackground();
        animationDrawable.start();
    }


    public void stopAnimation() {
        AnimationDrawable animationDrawable = (AnimationDrawable) audioAnttView.getBackground();
        if (animationDrawable.isRunning()) {
            animationDrawable.stop();
            animationDrawable.selectDrawable(0);
        }
    }


    /**
     * ------------------------set/get--------------
     */
    public View getMessageLayout() {
        return messageLayout;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public void setParentView(ViewGroup parentView) {
        this.parentView = parentView;
    }
}
