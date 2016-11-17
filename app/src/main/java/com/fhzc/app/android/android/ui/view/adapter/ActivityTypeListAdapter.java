package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.ActivityModel;
import com.fhzc.app.android.utils.im.ImageLoader;

/**
 * Created by fw on 16/10/18.
 */

public class ActivityTypeListAdapter extends BasePlatAdapter<ActivityModel> {
    Context mContext;
    private int firstFinish;

    public int getFirstFinish() {
        return firstFinish;
    }

    public void setFirstFinish(int firstFinish) {
        this.firstFinish = firstFinish;
    }
    public ActivityTypeListAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        ActivityModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_activitytypelist, null);
            vh.time = (TextView)itemView.findViewById(R.id.activity_item_time);
            vh.add = (TextView)itemView.findViewById(R.id.activity_item_add);
            vh.status = (ImageView)itemView.findViewById(R.id.activity_item_status);
            vh.image = (ImageView)itemView.findViewById(R.id.activity_item_image);
            vh.divideLayout = (LinearLayout) itemView.findViewById(R.id.ll_dividelayout);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        vh.time.setText("报名截止时间：" + DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getApplyEndTime()));
        vh.add.setText("活动地点: "+model.getAddress());
        ImageLoader.getInstance(mContext,R.drawable.default_error_long).displayImage(model.getCover(), vh.image);
        if(model.getCover()!=null){
            if(model.getStatus()==0){
                vh.status.setVisibility(View.VISIBLE);
                if(model.getIsApplyed()==1){
                    vh.status.setImageResource(R.drawable.activity_underway);
                }else{
                    vh.status.setImageResource(R.drawable.activity_notstrart);
                }
            }else if(model.getStatus()==1){
                vh.status.setVisibility(View.GONE);
            }
        }
        if (model.getStatus() ==1 && position == firstFinish) {
            vh.divideLayout.setVisibility(View.VISIBLE);
        } else {
            vh.divideLayout.setVisibility(View.GONE);
        }
        return itemView;
    }
    class ViewHolder {
        TextView add,time;
        ImageView image,status;
        LinearLayout divideLayout;
    }
}