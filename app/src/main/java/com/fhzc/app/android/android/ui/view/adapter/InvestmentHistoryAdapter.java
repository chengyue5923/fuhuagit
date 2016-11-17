package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.MyProductModel;

import java.text.DecimalFormat;


/**
 * 投资历史的Adapter
 */
public class InvestmentHistoryAdapter extends BasePlatAdapter<MyProductModel> {

    public InvestmentHistoryAdapter(Context context) {
        super(context);
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.adapter_financial_layout, null);
            vh.titleNameText=(TextView)itemView.findViewById(R.id.titleNameText);
            vh.interestNumber=(TextView)itemView.findViewById(R.id.interestNumber);
            vh.timeText=(TextView)itemView.findViewById(R.id.timeText);
            vh.startPriceText=(TextView)itemView.findViewById(R.id.startPriceText);
            vh.accountdata=(TextView)itemView.findViewById(R.id.accountdata);
            vh.priceText=(TextView)itemView.findViewById(R.id.priceText);
            vh.endTimeText=(TextView)itemView.findViewById(R.id.endTimeText);
            vh.accountdata=(TextView)itemView.findViewById(R.id.accountdata);
            vh.priceTimeText=(TextView)itemView.findViewById(R.id.priceTimeText);
            vh.interestProgress=(ProgressBar)itemView.findViewById(R.id.interestProgress);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        MyProductModel model=getItem(position);
        vh.titleNameText.setText(model.getName());

        String annualOldYield = model.getAnnualOldYield();
        if(StringTools.isNullOrEmpty(annualOldYield)){
            annualOldYield = "";
        }else{
            annualOldYield = new DecimalFormat("0.00").format(Float.parseFloat(annualOldYield)*100f)+"%";
        }

        vh.interestNumber.setText(annualOldYield);

        double youIn = Double.parseDouble(String.valueOf(model.getAmount()));
        double l = 10000;
        DecimalFormat df = new DecimalFormat("0.00");
        vh.priceText.setText(df.format(youIn/l) + "万元");
        vh.startPriceText.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getBuyTime()));
        vh.accountdata.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3,model.getDeadDate()));
        int day = DateTools.friendlyDayTime(model.getDeadDate());


        String date=DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5,model.getBuyTime());
        date=date.substring(0,4)+"."+date.substring(5,7)+"."+date.substring(8,11);
        vh.priceTimeText.setText(date);

//        int day = DateTools.friendlyDayTime(model.getCollectEnd());
//        int day1 = DateTools.differDayTime(model.getCollectStart(), model.getCollectEnd());
//        int day2 = DateTools.friendlyDayTime(model.getCollectStart());
//        vh.interestProgress.setProgress(day < 0 ? 100 : day2<0?Math.abs(day2) * 100 / day1:0);
          vh.interestProgress.setVisibility(View.GONE);
//        vh.endTimeText.setText(day<0?"已结束":"还有"+day+"天结束");


        return itemView;
    }
    class ViewHolder {
        ProgressBar interestProgress;
        TextView titleNameText,interestNumber,timeText,startPriceText,endTimeText,priceText,accountdata,priceTimeText;
    }
}
