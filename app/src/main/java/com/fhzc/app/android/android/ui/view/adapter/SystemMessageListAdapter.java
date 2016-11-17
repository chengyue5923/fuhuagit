package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.SystemNoticeModel;

/**
 * Created by yanbo on 2016/7/20.
 */
public class SystemMessageListAdapter extends BasePlatAdapter<SystemNoticeModel> {
    Context mContext;

    public SystemMessageListAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        SystemNoticeModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_system_message_item, null);
            vh.time = (TextView) itemView.findViewById(R.id.system_msg_item_time);
            vh.title = (TextView) itemView.findViewById(R.id.system_msg_item_title);
            vh.content = (TextView) itemView.findViewById(R.id.system_msg_item_content);
            vh.detailTime = (TextView) itemView.findViewById(R.id.system_msg_item_time_detail);
            vh.detail = (LinearLayout) itemView.findViewById(R.id.system_msg_item_detail);
            vh.rootLayout = (LinearLayout) itemView.findViewById(R.id.rootLayout);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        vh.time.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getPublishTime()));
        vh.detailTime.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getPublishTime()));
        vh.title.setText(model.getTitle());
        vh.content.setText(model.getContent());
        vh.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                IntentTools.startSystemMessageDetail(context);
            }
        });
        return itemView;
    }

    class ViewHolder {
        TextView title, time, detailTime, content;
        LinearLayout detail,rootLayout;
    }
}
