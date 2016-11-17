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
import com.fhzc.app.android.models.MyProductModel;


/**
 * 饿哦的投资历史的Adapter
 */
public class HistoryFinancialCustomerAdapter extends BasePlatAdapter<MyProductModel> {

    public HistoryFinancialCustomerAdapter(Context context) {
        super(context);
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.adapter_product_item, null);
            vh.titleNameText = (TextView) itemView.findViewById(R.id.titleNameText);
            vh.productStatus = (ImageView) itemView.findViewById(R.id.productStatus);
            vh.interestNumber = (TextView) itemView.findViewById(R.id.interestNumber);
            vh.timeText = (TextView) itemView.findViewById(R.id.timeText);
            vh.distributionMode = (TextView) itemView.findViewById(R.id.distributionMode);
            vh.endTimeText = (TextView) itemView.findViewById(R.id.endTimeText);
            vh.interestProgress = (ProgressBar) itemView.findViewById(R.id.interestProgress);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        MyProductModel model = getItem(position);
        vh.productStatus.setImageResource(model.getStatus() == 1 ? R.drawable.product_preheat : model.getStatus() == 2 ? R.drawable.product_haveinhand : 0);
        vh.titleNameText.setText(model.getName());

        String annualInterval = model.getAnnualInterval();
        if(StringTools.isNullOrEmpty(annualInterval)){
            annualInterval = model.getAnnualYield();
        }
        vh.interestNumber.setText(annualInterval);

        vh.timeText.setText(model.getPeriod() + "个月");
//        vh.distributionMode.setText(model.getIncomeDistributionType());
        int day = DateTools.friendlyDayTime(model.getExpiryDay());

        vh.interestProgress.setProgress(day < 0 ? 100 : 0);
        vh.endTimeText.setText(day < 0 ? "已结束" : "还剩" + day + "天结束");
        return itemView;
    }

    class ViewHolder {
        ProgressBar interestProgress;
        ImageView productStatus;
        TextView titleNameText, interestNumber, timeText, distributionMode, endTimeText;
    }
}
