package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.PointRecordDetail;

/**
 * Created by yanbo on 2016/7/25.
 */
public class MyOrderPointListAdapter extends BasePlatAdapter<PointRecordDetail> {
    private Context mContext;

    public MyOrderPointListAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_my_point_list_item, null);
            vh.adapter_content_item = (TextView)itemView.findViewById(R.id.adapter_content_item);
            vh.adapter_time_item = (TextView)itemView.findViewById(R.id.adapter_time_item);
            vh.adapter_point_item = (TextView)itemView.findViewById(R.id.adapter_point_item);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        PointRecordDetail model=getItem(position);
        vh.adapter_content_item.setText(model.getDetail()+"");

        try
        {
            String date=DateTools.dateFormatterOfDateTime(DateTools.DATE_FORMAT_STYLE_5,model.getCtime());
            String datess=date.substring(0,4)+"."+date.substring(5,7)+"."+date.substring(8,10);
            vh.adapter_time_item.setText(datess);
            vh.adapter_point_item.setText(model.getScore()+"");
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemView;
    }
    class ViewHolder {
        TextView  adapter_content_item,adapter_time_item,adapter_point_item;
    }
}
