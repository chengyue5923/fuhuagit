package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.AccountInformationModel;

/**
 * Created by yanbo on 2016/7/20.
 */
public class AccountInformationAdapter extends BasePlatAdapter<AccountInformationModel> {
    Context mContext;
    public AccountInformationAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        AccountInformationModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_account_information_item, null);
            vh.time_item_time = (TextView)itemView.findViewById(R.id.time_item_time);
            vh.productAddress = (TextView)itemView.findViewById(R.id.productAddress);
            vh.getAccountTime = (TextView)itemView.findViewById(R.id.getAccountTime);
            vh.numberText = (TextView)itemView.findViewById(R.id.numberText);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        vh.time_item_time.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getPaymentDate()));
        vh.numberText.setText("到账金额："+model.getAmount());
        vh.productAddress.setText("购买产品：" + model.getProductName());
        vh.getAccountTime.setText("到账时间："+ DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getPaymentDate()));
        return itemView;
    }
    class ViewHolder {
        TextView time_item_time, productAddress,getAccountTime,numberText;
    }
}
