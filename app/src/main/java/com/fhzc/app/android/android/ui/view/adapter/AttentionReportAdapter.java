package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.ChoosePackageModel;

/**
 *理财产品--- 投研报告 adapter -----
 */
public class AttentionReportAdapter extends BasePlatAdapter<ChoosePackageModel> {

    public AttentionReportAdapter(Context context) {
        super(context);
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.fh_attention_report_adapter, null);

            vh.reportAdapterImge=(ImageView)itemView.findViewById(R.id.reportAdapterImge);
            vh.reportTitleAdaperText=(TextView)itemView.findViewById(R.id.reportTitleAdaperText);
            vh.reportPointText=(TextView)itemView.findViewById(R.id.reportPointText);
            vh.bottomtimeTextView=(TextView)itemView.findViewById(R.id.bottomtimeTextView);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }



        return itemView;
    }
    class ViewHolder {
        ImageView reportAdapterImge;
        TextView reportTitleAdaperText,reportPointText,bottomtimeTextView;
    }
}
