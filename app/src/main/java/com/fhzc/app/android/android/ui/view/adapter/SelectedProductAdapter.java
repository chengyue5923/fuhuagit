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
import com.fhzc.app.android.models.ProductModel;


/**
 *
 */
public class SelectedProductAdapter extends BasePlatAdapter<ProductModel> {

    public SelectedProductAdapter(Context context) {
        super(context);
    }

    @Override
    public int getCount() {
        if (listData == null) {
            return 0;
        }else if(listData.size()>3){
            return 3;
        }else{
            return listData.size();
        }
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.adapter_selected_product_item, null);
            vh.titleNameText = (TextView) itemView.findViewById(R.id.titleNameText);
            vh.productStatus = (ImageView) itemView.findViewById(R.id.productStatus);
            vh.interestNumber = (TextView) itemView.findViewById(R.id.interestNumber);
            vh.endTimeText = (TextView) itemView.findViewById(R.id.endTimeText);
            vh.interestProgress = (ProgressBar) itemView.findViewById(R.id.interestProgress);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        ProductModel model = getItem(position);

        if (null == model)
            return itemView;
        if(model.getStatus()==1){
            vh.productStatus.setImageResource( R.drawable.product_preheat );
        }else if(model.getStatus()==2){
            vh.productStatus.setImageResource( R.drawable.product_haveinhand );
        }else{
            vh.productStatus.setImageResource( R.drawable.product_end );
        }
        vh.titleNameText.setText(model.getName());

        String annualInterval = model.getAnnualInterval();
        if(StringTools.isNullOrEmpty(annualInterval)){
            annualInterval = model.getAnnualYield();
        }
        vh.interestNumber.setText(annualInterval);

        int day = DateTools.friendlyDayTime(model.getCollectEnd());
        int day1 = DateTools.differDayTime(model.getCollectStart(), model.getCollectEnd());
        int day2 = DateTools.friendlyDayTime(model.getCollectStart());
        vh.interestProgress.setProgress(day < 0 ? 100 : day2<0?Math.abs(day2) * 100 / day1:0);
        vh.endTimeText.setText(day < 0 ? "募集已结束" : "还剩" + day + "天募集结束");
        return itemView;
    }

    class ViewHolder {
        ProgressBar interestProgress;
        ImageView productStatus;
        TextView titleNameText, interestNumber, endTimeText;
    }
}
