package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.ProductModel;
import com.fhzc.app.android.utils.im.ImageLoader;

/**
 * Created by fw on 16/10/24.
 */

public class TourismProductAdapter extends BasePlatAdapter<ProductModel> {

    private int firstFinish;

    public int getFirstFinish() {
        return firstFinish;
    }

    public void setFirstFinish(int firstFinish) {
        this.firstFinish = firstFinish;
    }

    public TourismProductAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.adapter_tourismproduct, null);
            vh.titleNameText = (TextView) itemView.findViewById(R.id.tv_abbreviation);
            vh.annualInterval = (TextView) itemView.findViewById(R.id.annualInterval);
            vh.remain = (TextView) itemView.findViewById(R.id.tv_remain);
            vh.renewDeadline = (TextView) itemView.findViewById(R.id.tv_renewDeadline);
            vh.incomeDistributionType = (TextView) itemView.findViewById(R.id.tv_incomeDistributionType);
            vh.cover = (ImageView) itemView.findViewById(R.id.iv_cover);
            vh.productStatus = (ImageView) itemView.findViewById(R.id.productStatus);
            vh.productStatus1 = (ImageView) itemView.findViewById(R.id.productStatus1);
            vh.divideLayout = (LinearLayout) itemView.findViewById(R.id.ll_dividelayout);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        ProductModel model = getItem(position);
        if (model.getStatus() > 2 && position == firstFinish) {
            vh.divideLayout.setVisibility(View.VISIBLE);
        } else {
            vh.divideLayout.setVisibility(View.GONE);
        }
        if (model.getStatus() == 1) {
            vh.productStatus1.setImageResource(R.drawable.ptl_yure);
            vh.productStatus.setImageResource(0);
        } else if (model.getStatus() == 2) {
            vh.productStatus1.setImageResource(R.drawable.collecticon);
            vh.productStatus.setImageResource(0);
        } else {
            vh.productStatus.setImageResource(R.drawable.product_end);
            vh.productStatus1.setImageResource(0);
        }

        vh.titleNameText.setText(model.getName());
        vh.incomeDistributionType.setText(Html.fromHtml(model.getIncomeDistributionType()));
        String annualInterval = model.getAnnualInterval();
        if (StringTools.isNullOrEmpty(annualInterval)) {
            annualInterval = model.getAnnualYield();
        }
        vh.annualInterval.setText(Html.fromHtml(annualInterval));
        ImageLoader.getInstance(context, R.drawable.default_error_long).displayImage(model.getCover(), vh.cover);
        vh.renewDeadline.setText(model.getRenewDeadline());

        int day = DateTools.friendlyDayTime(model.getCollectEnd());
        vh.remain.setText(day < 0 ? "已售罄" : "还剩" + day + "天结束");
        return itemView;
    }

    class ViewHolder {
        ImageView cover,productStatus1,productStatus;
        TextView titleNameText, annualInterval, remain, renewDeadline, incomeDistributionType;
        LinearLayout divideLayout;
    }
}
