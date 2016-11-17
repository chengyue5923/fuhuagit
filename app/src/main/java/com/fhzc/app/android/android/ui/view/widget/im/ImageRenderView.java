package com.fhzc.app.android.android.ui.view.widget.im;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.activity.FullScreenImageActivity;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.ContactModel;
import com.fhzc.app.android.models.MessageEntity;
import com.fhzc.app.android.utils.im.ImageLoader;


/**
 * @author : yingmu on 15-1-9.
 * @email : yingmu@mogujie.com.
 */
public class ImageRenderView extends BaseMsgRenderView {


    // 上层必须实现的接口
    private ImageLoadListener imageLoadListener;


    /**
     * 可点击的view
     */
    private View messageLayout;
    /**
     * 图片消息体
     */
    private BubbleImageView messageImage;


    private Context mContext;

    public ImageRenderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public static ImageRenderView inflater(Context context, ViewGroup viewGroup, boolean isMine) {
        int resource = isMine ? R.layout.im_mine_image_message_item : R.layout.im_other_image_message_item;
        ImageRenderView imageRenderView = (ImageRenderView) LayoutInflater.from(context).inflate(resource, viewGroup, false);
        imageRenderView.setMine(isMine);
        imageRenderView.setParentView(viewGroup);
        return imageRenderView;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        messageLayout = findViewById(R.id.message_layout);
        messageImage = (BubbleImageView) findViewById(R.id.message_image);

    }

    /**
     *
     * */

    /**
     * 控件赋值
     *
     * @param messageEntity
     * @param userEntity    对于mine。 图片send_success 就是成功了直接取地址
     *                      对于sending  就是正在上传
     *                      <p/>
     *                      对于other，消息一定是success，接受成功额
     *                      2. 然后分析loadStatus 判断消息的展示状态
     */
    @Override
    public void render(final MessageEntity messageEntity, final ContactModel userEntity, Context ctx) {
        super.render(messageEntity, userEntity, ctx);
        if(messageEntity.getFromId()!= UserPreference.getUid()){
            ImageLoader.getInstance(ctx, R.drawable.im_message_image_default).displayImage(messageEntity.getContent(),messageImage);

        }else{
            if (messageEntity.getStatus()==Constant.MSG_SUCCESS)
            ImageLoader.getInstance(ctx, R.drawable.im_message_image_default).displayImage(messageEntity.getContent(),messageImage);
else
                ImageLoader.getInstance(ctx, R.drawable.im_message_image_default).loadLocalImage(messageEntity.getContent(),messageImage);

        }


        messageImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FullScreenImageActivity.class);
                intent.putExtra("url", messageEntity.getContent());
                mContext.startActivity(intent);
            }
        });

    }


    public interface ImageLoadListener {
        public void onLoadComplete(String path);

        // 应该把exception 返回结构放进去
        public void onLoadFailed();

    }

    public void setImageLoadListener(ImageLoadListener imageLoadListener) {
        this.imageLoadListener = imageLoadListener;
    }

    /**---------------------图片下载相关、以及事件回调 end-----------------------------------*/


    /**
     * ----------------------set/get------------------------------------
     */
    public View getMessageLayout() {
        return messageLayout;
    }

    public ImageView getMessageImage() {
        return messageImage;
    }


    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean isMine) {
        this.isMine = isMine;
    }

    public ViewGroup getParentView() {
        return parentView;
    }

    public void setParentView(ViewGroup parentView) {
        this.parentView = parentView;
    }

}
