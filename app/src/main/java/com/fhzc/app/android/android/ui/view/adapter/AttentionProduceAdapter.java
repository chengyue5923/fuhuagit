package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.ChoosePackageModel;

/**
 *理财产品--- 我的关注 -----
 */
public class AttentionProduceAdapter extends BasePlatAdapter<ChoosePackageModel> {

    public AttentionProduceAdapter(Context context) {
        super(context);
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.fh_attention_produce_adapter, null);

            vh.titleNameText=(TextView)itemView.findViewById(R.id.titleNameText);
            vh.interestNumber=(TextView)itemView.findViewById(R.id.interestNumber);
            vh.timeText=(TextView)itemView.findViewById(R.id.timeText);
            vh.startPriceText=(TextView)itemView.findViewById(R.id.startPriceText);
            vh.endTimeText=(TextView)itemView.findViewById(R.id.endTimeText);
            vh.interestProgress=(ProgressBar)itemView.findViewById(R.id.interestProgress);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }



        return itemView;
    }
    class ViewHolder {
        TextView titleNameText,interestNumber,timeText,startPriceText,endTimeText;
        ProgressBar interestProgress;
    }
}
