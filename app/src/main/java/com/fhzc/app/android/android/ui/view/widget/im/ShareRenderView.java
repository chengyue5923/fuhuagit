package com.fhzc.app.android.android.ui.view.widget.im;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.models.MessageEntity;
import com.fhzc.app.android.models.ShareModel;
import com.fhzc.app.android.models.TextMessage;
import com.fhzc.app.android.utils.im.CommonUtil;


/**
 * @author : yingmu on 15-1-9.
 * @email : yingmu@mogujie.com.
 * <p/>
 * 样式根据mine 与other不同可以分成两个
 */
public class ShareRenderView extends RelativeLayout {
    /**
     * 文字消息体
     */
    private TextView messageTitle,messageContent,typeText;
    private LinearLayout contentLayout;

    public static ShareRenderView inflater(Context context, ViewGroup viewGroup, boolean isMine) {
        int resource = isMine ? R.layout.im_mine_share_message_item : R.layout.im_other_share_message_item;
        ShareRenderView textRenderView = (ShareRenderView) LayoutInflater.from(context).inflate(resource, viewGroup, false);

        return textRenderView;
    }

    public ShareRenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        messageTitle = (TextView) findViewById(R.id.share_view_name);
        typeText = (TextView) findViewById(R.id.share_view_type);
        messageContent = (TextView) findViewById(R.id.share_view_introd);
        contentLayout = (LinearLayout) findViewById(R.id.content_layout);
    }

    /**
     * 控件赋值
     *
     * @param messageEntity

     */

    public void render(MessageEntity messageEntity, final Context context) {

        TextMessage textMessage = (TextMessage) messageEntity;
        // 按钮的长按也是上层设定的
        // url 路径可以设定 跳转哦哦
        String content = textMessage.getContent();

        final ShareModel model = CommonUtil.getShareModel(content);

        if(model.getType().equals("rights")){
//            messageContent.setTextSize(CommonUtil.dip2px(context,15f));
            messageContent.setTextSize(25);
            messageContent.setTextColor(0xffff943e);
        }else{
            messageContent.setTextSize(10);
            messageContent.setTextColor(0xffa2a2a7);
        }
        if(model.getType().equals("activity")){
            messageContent.setText("报名截止时间:"+model.getIntroduce());
        }else if(model.getType().equals("rights")){
            messageContent.setText("积分  "+model.getIntroduce());
        }else{
            messageContent.setText(model.getIntroduce());

        }
        messageTitle.setText(model.getTitle());
        typeText.setText(CommonUtil.getShareTextByType(model.getType()));
        contentLayout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.onShareClick(context, model);
            }
        });


    }


    /**
     * ----------------set/get---------------------------------
     */
    public TextView getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(TextView messageTitle) {
        this.messageTitle = messageTitle;
    }


}
