package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.RightFinancialDetailModel;


/**
 * 我的理财师
 */
public class AccountProductAdapter extends BasePlatAdapter<RightFinancialDetailModel> {

    public AccountProductAdapter(Context context) {
        super(context);
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.adapter_servation_layout, null);
            vh.accountImage=(ImageView)itemView.findViewById(R.id.accountImageView);
            vh.accountName=(TextView)itemView.findViewById(R.id.accountName);
            vh.lastDate=(TextView)itemView.findViewById(R.id.lastDate);
            vh.cententText=(TextView)itemView.findViewById(R.id.cententText);
            vh.tab_unread_notify=(TextView)itemView.findViewById(R.id.tab_unread_notify);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        RightFinancialDetailModel model=getItem(position);

        return itemView;
    }
    class ViewHolder {

        ImageView accountImage;
        TextView accountName,lastDate,cententText,tab_unread_notify;
    }
}
