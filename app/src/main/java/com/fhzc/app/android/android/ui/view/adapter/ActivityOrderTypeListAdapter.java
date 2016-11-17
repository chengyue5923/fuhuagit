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
import com.fhzc.app.android.models.OrderActivityModel;
import com.fhzc.app.android.utils.im.ImageLoader;

/**
 * Created by fw on 16/10/18.
 */

public class ActivityOrderTypeListAdapter extends BasePlatAdapter<OrderActivityModel> {
    Context mContext;
    public ActivityOrderTypeListAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        OrderActivityModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_activitytypelist, null);
            vh.time = (TextView)itemView.findViewById(R.id.activity_item_time);
            vh.add = (TextView)itemView.findViewById(R.id.activity_item_add);
            vh.status = (ImageView)itemView.findViewById(R.id.activity_item_status);
//            vh.title = (TextView)itemView.findViewById(R.id.activity_item_title);
//            vh.detail = (TextView)itemView.findViewById(R.id.activity_item_detail);
            vh.image = (ImageView)itemView.findViewById(R.id.activity_item_image);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

//        vh.title.setText(model.getName());
        vh.time.setText("报名截止时间：" + DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getApplyEndTime()));
        vh.add.setText("活动地点: "+model.getAddress());
        ImageLoader.getInstance(mContext,R.drawable.default_error_long).displayImage(model.getCover(), vh.image);
        if(model.getCover()!=null){
            if(model.getStatus()==0){
                vh.status.setVisibility(View.VISIBLE);
//                vh.detail.setText("查看详情");
                vh.status.setImageResource(R.drawable.activity_notstrart);
            }else if(model.getStatus()==1){
                vh.status.setVisibility(View.VISIBLE);
//                vh.detail.setText("立即参加");
                vh.status.setImageResource(R.drawable.activity_underway);
            }else if(model.getStatus()==2){
//                vh.detail.setText("报名结束");
                vh.status.setVisibility(View.VISIBLE);
                vh.status.setImageResource(R.drawable.complete);

            }else if(model.getStatus()==3){
//                vh.detail.setText("活动结束");
                vh.status.setVisibility(View.VISIBLE);
                vh.status.setImageResource(R.drawable.complete);
            }
        }
        return itemView;
    }
    class ViewHolder {
//        TextView title,detail;
        TextView add,time;
        ImageView image,status;
    }
}