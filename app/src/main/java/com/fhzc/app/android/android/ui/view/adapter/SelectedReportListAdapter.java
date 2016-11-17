package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.ReportModel;

/**
 * Created by fw on 16/11/2.
 */

public class SelectedReportListAdapter extends BasePlatAdapter<ReportModel> {
    Context mContext;
    public SelectedReportListAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        ReportModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_selectedreportlist, null);
            vh.time = (TextView)itemView.findViewById(R.id.tv_time);
            vh.title = (TextView)itemView.findViewById(R.id.tv_abbreviation);
            vh.type = (TextView)itemView.findViewById(R.id.tv_type);
            vh.publisher = (TextView)itemView.findViewById(R.id.tv_publisher);
            vh.readcount = (TextView)itemView.findViewById(R.id.tv_readcount);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        vh.title.setText(model.getName());
        vh.type.setText(model.getType());
        vh.time.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3,model.getCtime()));
        vh.publisher.setText(model.getPublisher());
        vh.readcount.setText(String.valueOf(model.getReadCount()));
        return itemView;
    }
    class ViewHolder {
        TextView title, type,time,publisher,readcount;
    }
}