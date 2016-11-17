package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.RightModel;
import com.fhzc.app.android.utils.im.ImageLoader;

/**
 * Created by yanbo on 2016/7/25.
 */
public class MyOrderRightAdapter extends BasePlatAdapter<RightModel> {
    private Context mContext;

    public MyOrderRightAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        RightModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_my_order_right_item, null);
            vh.time = (TextView)itemView.findViewById(R.id.order_right_time);

            vh.image = (ImageView)itemView.findViewById(R.id.order_right_image);
            vh.status = (ImageView)itemView.findViewById(R.id.order_right_status);


            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        vh.time.setText(model.getCtime()+"");
        ImageLoader.getInstance(mContext,R.drawable.default_error_long).displayImage(model.getCover(),vh.image);
        return itemView;
    }
    class ViewHolder {
        TextView  time;
        ImageView image,status;
    }
}
