package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.ActivityModel;
import com.fhzc.app.android.utils.im.ImageLoader;

/**
 * Created by yanbo on 2016/7/20.
 */
public class ActivityListAdapter extends BasePlatAdapter<ActivityModel> {
    Context mContext;
    public ActivityListAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        ActivityModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_activity_item, null);
            vh.time = (TextView)itemView.findViewById(R.id.activity_item_time);
            vh.title = (TextView)itemView.findViewById(R.id.activity_item_title);
            vh.status = (ImageView)itemView.findViewById(R.id.activity_item_status);
            vh.detail = (TextView)itemView.findViewById(R.id.activity_item_detail);
            vh.image = (ImageView)itemView.findViewById(R.id.activity_item_image);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        vh.title.setText(model.getName());
        vh.time.setText("报名截止时间：" + DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getApplyEndTime()));
        ImageLoader.getInstance(mContext,R.drawable.default_error_long).displayImage(model.getCover(), vh.image);
        if(model.getCover()!=null){
            if(model.getStatus()==0){
                vh.status.setVisibility(View.VISIBLE);
                vh.detail.setText("查看详情");
                vh.status.setImageResource(R.drawable.activity_notstrart);
            }else if(model.getStatus()==1){
                vh.status.setVisibility(View.VISIBLE);
                vh.detail.setText("立即参加");
                vh.status.setImageResource(R.drawable.activity_underway);
            }else if(model.getStatus()==2){
                vh.detail.setText("报名结束");
                vh.status.setVisibility(View.VISIBLE);
                vh.status.setImageResource(R.drawable.complete);

            }else if(model.getStatus()==3){
                vh.detail.setText("活动结束");
                vh.status.setVisibility(View.VISIBLE);
                vh.status.setImageResource(R.drawable.complete);
            }
        }
        return itemView;
    }
    class ViewHolder {
        TextView title, time,detail;
        ImageView image,status;
    }
}
