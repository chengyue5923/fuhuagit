package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.CollectProductModel;


/**
 * 选择套餐Adapter
 */
public class CollectProductAdapter extends BasePlatAdapter<CollectProductModel> {

    public CollectProductAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.adapter_product_item, null);
            vh.titleNameText = (TextView) itemView.findViewById(R.id.titleNameText);
            vh.interestNumber = (TextView) itemView.findViewById(R.id.interestNumber);
            vh.productStatus = (ImageView) itemView.findViewById(R.id.productStatus);
            vh.timeText = (TextView) itemView.findViewById(R.id.timeText);
            vh.distributionMode = (TextView) itemView.findViewById(R.id.distributionMode);
            vh.endTimeText = (TextView) itemView.findViewById(R.id.endTimeText);
            vh.interestProgress = (ProgressBar) itemView.findViewById(R.id.interestProgress);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        CollectProductModel model = getItem(position);
        vh.productStatus.setImageResource(model.getStatus() == 1 ? R.drawable.product_preheat : model.getStatus() == 2 ? R.drawable.collecticon : R.drawable.product_end);

//        vh.productStatus.setImageResource(model.getStatus() == 1 ? R.drawable.product_preheat : model.getStatus()==2?R.drawable.product_haveinhand:0);
        vh.titleNameText.setText(model.getName());
//        vh.interestNumber.setText(">" + model.getExpected_min() + "%");

        String annualInterval = model.getAnnualInterval();
        if(StringTools.isNullOrEmpty(annualInterval)){
            annualInterval = model.getAnnualYield();
        }
        vh.interestNumber.setText(annualInterval);

        vh.timeText.setText(model.getInvestTerm());
        vh.distributionMode.setText(model.getIncomeDistributionType());
        int day = DateTools.friendlyDayTime(model.getCollect_end());
        int day1 = DateTools.differDayTime(model.getCollect_start(), model.getCollect_end());
        int day2 = DateTools.friendlyDayTime(model.getCollect_start());
        vh.interestProgress.setProgress(day < 0 ? 100 : day2<0?Math.abs(day2) * 100 / day1:0);
        vh.endTimeText.setText(day < 0 ? "募集已结束" : "还剩" + day + "天募集结束");
        return itemView;
    }

    class ViewHolder {
        ProgressBar interestProgress;
        ImageView productStatus;
        TextView titleNameText, interestNumber, timeText, distributionMode, endTimeText;
    }
}
