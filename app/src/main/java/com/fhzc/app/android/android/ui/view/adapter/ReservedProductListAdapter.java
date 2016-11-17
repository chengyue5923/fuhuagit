package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.ProductModel;

import java.text.DecimalFormat;


/**
 * 预约产品adapter
 */
public class ReservedProductListAdapter extends BasePlatAdapter<ProductModel> {

    public ReservedProductListAdapter(Context context) {
        super(context);
    }

    private int firstFinish;

    public int getFirstFinish() {
        return firstFinish;
    }

    public void setFirstFinish(int firstFinish) {
        this.firstFinish = firstFinish;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.adapter_product_new_item, null);
            vh.titleNameText = (TextView) itemView.findViewById(R.id.titleNameText);
            vh.interestNumber = (TextView) itemView.findViewById(R.id.interestNumber);
            vh.priceText = (TextView) itemView.findViewById(R.id.priceText);
            vh.priceTimeText = (TextView) itemView.findViewById(R.id.priceTimeText);
            vh.productStatus = (ImageView) itemView.findViewById(R.id.productStatus);

            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        ProductModel model = getItem(position);
//        if (model.getStatus() > 2 && position == firstFinish) {
//            vh.divideLayout.setVisibility(View.VISIBLE);
//        } else {
//            vh.divideLayout.setVisibility(View.GONE);
//        }
        if (model.getStatus() == 1) {
            vh.productStatus.setImageResource(R.drawable.ptl_yure);
        } else if (model.getStatus() == 2) {
            vh.productStatus.setImageResource(R.drawable.collecticon);
        } else {
            vh.productStatus.setImageResource(R.drawable.product_end);
        }
        vh.titleNameText.setText(model.getName());
        String annualInterval = model.getAnnualInterval();
        if (StringTools.isNullOrEmpty(annualInterval)) {
            annualInterval = model.getAnnualYield();
        }
        vh.interestNumber.setText(annualInterval);
        double youIn = Double.parseDouble(String.valueOf(model.getInvestThreshold()));
        double l = 10000;
        DecimalFormat df = new DecimalFormat("0.00");
        vh.priceText.setText(df.format(youIn/l) + "万元");
        String date=DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5,model.getCtime());
        date=date.substring(0,4)+"."+date.substring(5,7)+"."+date.substring(8,11);
        vh.priceTimeText.setText(date);

//        int day = DateTools.friendlyDayTime(model.getCollectEnd());
//        int day1 = DateTools.differDayTime(model.getCollectStart(), model.getCollectEnd());
//        int day2 = DateTools.friendlyDayTime(model.getCollectStart());
//        vh.interestProgress.setProgress(day < 0 ? 100 : day2 < 0 ? Math.abs(day2) * 100 / day1 : 0);
//        vh.endTimeText.setText(day < 0 ? "募集已结束" : "还剩" + day + "天募集结束");
        return itemView;
    }

    class ViewHolder {
//        ProgressBar interestProgress;
//        ImageView productStatus;
//        TextView titleNameText, interestNumber, timeText, distributionMode, endTimeText;
//        LinearLayout divideLayout;

        TextView titleNameText,interestNumber,priceText,priceTimeText;
        ImageView productStatus;



    }
}
