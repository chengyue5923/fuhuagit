package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.ContactUsModel;

/**
 * Created by yanbo on 2016/7/20.
 */
public class ContactUsListAdapter extends BasePlatAdapter<ContactUsModel> {
    Context mContext;
    public ContactUsListAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        ContactUsModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.contact_us_activity_item, null);
            vh.numbercontactus=(TextView)itemView.findViewById(R.id.numbercontactus);
            vh.phontcontactTv=(TextView)itemView.findViewById(R.id.phontcontactTv);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        return itemView;
    }
    class ViewHolder {
        TextView phontcontactTv,numbercontactus;
    }
}
