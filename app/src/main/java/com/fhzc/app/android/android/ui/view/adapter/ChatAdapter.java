package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.dao.ContactDao;
import com.fhzc.app.android.dao.MessageDao;
import com.fhzc.app.android.models.ContactModel;
import com.fhzc.app.android.models.MessageEntity;
import com.fhzc.app.android.models.SessionModel;
import com.fhzc.app.android.utils.DateUtil;
import com.fhzc.app.android.utils.im.CommonUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yanbo on 16-7-14.
 */
public class ChatAdapter extends BasePlatAdapter<SessionModel> {
    List<SessionModel> list = new ArrayList<>();
    Context mContext;

    public ChatAdapter(Context context) {
        super(context);
        mContext = context;
    }


    @Override
    public View getView(int i, View itemView, ViewGroup viewGroup) {
        ViewHolder vh;
        SessionModel model = getItem(i);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.im_item_chat, null);

            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        vh.nameTv = (TextView) itemView.findViewById(R.id.shop_name);
        vh.message = (TextView) itemView.findViewById(R.id.message_body);
        vh.time = (TextView) itemView.findViewById(R.id.message_time);
        vh.countTv = (TextView) itemView.findViewById(R.id.message_count_notify);
        vh.avatar = (ImageView) itemView.findViewById(R.id.contact_portrait);
        ContactModel contactModel = new ContactDao().getUserById(model.getFromId());
        vh.nameTv.setText(contactModel.getName());
        MessageEntity entity = new MessageDao().getLastMessageBySessionId(model.getSessionId());

        Date msgTimeDate = new Date((long) entity.getCreated() * 1000);
        if (entity.getDisplayType().equals(Constant.SHOW_ORIGIN_TEXT_TYPE)) {
            if (CommonUtil.isShareText(entity.getContent()))
                vh.message.setText(Constant.DISPLAY_FOR_SHARE);
            else
                vh.message.setText(entity.getContent());
        } else if (entity.getDisplayType().equals(Constant.SHOW_AUDIO_TYPE)) {
            vh.message.setText(Constant.DISPLAY_FOR_AUDIO);
        } else {
            vh.message.setText(Constant.DISPLAY_FOR_IMAGE);
        }
        vh.time.setText(DateUtil.getTimeDiffDesc(msgTimeDate));

        if (model.getUnReadCount() > 0) {
            vh.countTv.setVisibility(View.VISIBLE);
            vh.countTv.setText(String.valueOf(model.getUnReadCount()));
        }else{
            vh.countTv.setVisibility(View.GONE);
        }
//        ImageLoader.getInstance().displayImage(contactModel.getAvatar(), vh.avatar);
//        ImageLoaderUtil.getImageLoaderInstance().displayImage(contactModel.getAvatar(), vh.avatar);
        return itemView;
    }

    class ViewHolder {
        TextView nameTv, countTv, message, time;
        ImageView avatar;
    }

}
