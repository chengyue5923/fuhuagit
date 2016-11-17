package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.DividendRemendModel;

/**
 * Created by apple on 16/8/3.
 */
public class FinancialRedementAdapter extends BasePlatAdapter<DividendRemendModel> {


    private String format;
    private DateFormat dateFormat;
    public FinancialRedementAdapter(Context context) {
        super(context);
        this.context = context;
        format = "yyyy-MM-dd";
        dateFormat = new DateFormat();
    }

    boolean flag=true;
    public void setFlag(boolean flag){
        this.flag=flag;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view == null){
            view  = mLayoutInflater.inflate(R.layout.divided_record_view,null);
            vh = new ViewHolder();
            vh.tvDate = (TextView)view.findViewById(R.id.dateText);
            vh.tvContent = (TextView)view.findViewById(R.id.parcentText);
            vh.tvPoint = (TextView)view.findViewById(R.id.numberText);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
        DividendRemendModel detail = getItem(i);
        vh.tvContent.setText(detail.getEarningRate());
        vh.tvDate.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3,detail.getPaymentDate()));
        if(flag){
            vh.tvPoint.setText(detail.getDistributeEarning());
        }else{
            vh.tvPoint.setText(detail.getPayment());
        }
        return view;
    }

    private class ViewHolder {
        TextView  tvDate,tvContent,tvPoint;

    }
}
