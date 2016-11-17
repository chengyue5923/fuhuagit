package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.CollectActivityModel;
import com.fhzc.app.android.utils.im.ImageLoader;

/**
 * Created by yanbo on 2016/7/20.
 */
public class CollectActivityListAdapter extends BasePlatAdapter<CollectActivityModel> {
    Context mContext;

    public CollectActivityListAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        CollectActivityModel model = getItem(position);
        if (model.getStatus() == 10000001) {
            convertView = statusRender(position, convertView);
        } else {
            convertView = activityRender(position, convertView);
        }
//            if (itemView == null) {
//                vh = new ViewHolder();
//                itemView = LayoutInflater.from(mContext).inflate(R.layout.view_activity_item, null);
//                vh.time = (TextView) itemView.findViewById(R.id.activity_item_time);
//                vh.title = (TextView) itemView.findViewById(R.id.activity_item_title);
//                vh.status = (ImageView) itemView.findViewById(R.id.activity_item_status);
//                vh.detail = (TextView) itemView.findViewById(R.id.activity_item_detail);
//                vh.image = (ImageView) itemView.findViewById(R.id.activity_item_image);
//                itemView.setTag(vh);
//            } else {
//                vh = (ViewHolder) itemView.getTag();
//            }
//            if (model.getStatus() == 0 || model.getStatus() == 1 || model.getStatus() == 2) {
//                vh.status.setVisibility(View.VISIBLE);
//            }
//            vh.title.setText(model.getName());
//            vh.time.setText("报名截止时间：" + DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getApply_end_time()));
//            ImageLoader.getInstance(mContext, R.drawable.default_error_long).displayImage(model.getCover(), vh.image);
//

        return convertView;
    }

    /**
     * 状态的渲染
     */
    private View statusRender(int position, View convertView) {
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_my_activity_status, null);
        }
        return convertView;
    }

    /**
     * 状态的渲染
     */
    private View activityRender(int position, View convertView) {
        ViewHolder vh;
        CollectActivityModel model = getItem(position);
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_activity_item, null);
            vh.time = (TextView) convertView.findViewById(R.id.activity_item_time);
            vh.title = (TextView) convertView.findViewById(R.id.activity_item_title);
            vh.status = (ImageView) convertView.findViewById(R.id.activity_item_status);
            vh.detail = (TextView) convertView.findViewById(R.id.activity_item_detail);
            vh.image = (ImageView) convertView.findViewById(R.id.activity_item_image);

            vh.activity_item_status = (ImageView) convertView.findViewById(R.id.activity_item_status);
            vh.timeTextView = (TextView) convertView.findViewById(R.id.timeTextView);
            vh.addressTextView = (TextView) convertView.findViewById(R.id.addressTextView);
            vh.activity_item_image = (ImageView) convertView.findViewById(R.id.activity_item_image);


            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        try{
            if (model.getStatus() == 0) {
                vh.activity_item_status.setImageResource(R.drawable.activity_notstrart);
            } else if (model.getStatus() == 1) {
                vh.activity_item_status.setImageResource(R.drawable.activity_underway);
            } else if (model.getStatus() == 2) {
                vh.activity_item_status.setImageResource(R.drawable.activity_underway);
            } else if (model.getStatus() == 3) {
                vh.activity_item_status.setImageResource(R.drawable.activity_underway);
            }
//            vh.timeTextView.setText("报名截止日期："+DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getApplyEndTime()));

            if(model.getAddress()==null||model.getAddress().equals("")){
                vh.addressTextView.setText("活动地点：暂无");
            }else{
                vh.addressTextView.setText("活动地点："+model.getAddress());

            }
            ImageLoader.getInstance(mContext).displayImage(model.getCover(), vh.activity_item_image);
        }catch (Exception e){
            e.printStackTrace();
        }

//        if (model.getStatus() == 0) {
//            vh.status.setVisibility(View.VISIBLE);
//            vh.status.setImageResource(R.drawable.activity_notstrart);
//        } else if (model.getStatus() == 1) {
//            vh.status.setVisibility(View.VISIBLE);
//            vh.status.setImageResource(R.drawable.activity_underway);
//        } else if (model.getStatus() == 2) {
//            vh.status.setVisibility(View.VISIBLE);
//            vh.status.setImageResource(R.drawable.complete);
//
//        } else if (model.getStatus() == 3) {
//            vh.status.setVisibility(View.VISIBLE);
//            vh.status.setImageResource(R.drawable.complete);
//        if (model.getStatus() == 0 || model.getStatus() == 1 || model.getStatus() == 2) {
//            vh.status.setVisibility(View.VISIBLE);
//        }
//        }
//            vh.title.setText(model.getName());
//            vh.time.setText("报名截止时间：" + DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getApply_end_time()));
//            ImageLoader.getInstance(mContext, R.drawable.default_error_long).displayImage(model.getCover(), vh.image);
            return convertView;
    }

    class ViewHolder {
        TextView title, time, detail,timeTextView,addressTextView;
        ImageView image, status,activity_item_status,activity_item_image;
    }
}
