package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.RankbackDataModel;

/**
 * Created by yanbo on 2016/7/20.
 */
public class YearAndMonthRankAdapter extends BasePlatAdapter<RankbackDataModel> {
    Context mContext;

    boolean flag;
    public YearAndMonthRankAdapter(Context context,boolean ismonth) {
        super(context);
        mContext = context;
        flag=ismonth;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        RankbackDataModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_year_item, null);
            vh.yearText = (TextView) itemView.findViewById(R.id.yearText);
            vh.numberText = (TextView) itemView.findViewById(R.id.numberText);
            vh.ranktext = (TextView) itemView.findViewById(R.id.ranktext);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        try{
                vh.yearText.setText(model.getName()+"");
            vh.ranktext.setText(model.getSort() + "");
            vh.numberText.setText(model.getAnnualised()+"");
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemView;
    }

    class ViewHolder {
        TextView yearText, numberText, ranktext;
    }
}
