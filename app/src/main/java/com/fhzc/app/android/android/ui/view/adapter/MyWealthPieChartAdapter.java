package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseFootviewAdapter;
import com.fhzc.app.android.models.MyProductModel;

/**
 * Created by yanbo on 2016/7/25.
 */
public class MyWealthPieChartAdapter extends BaseFootviewAdapter<MyProductModel> {
    Context mContext;
    String[] colors;
    public interface OnClickItem{
       void click(MyProductModel model);
    }
    private OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public MyWealthPieChartAdapter(Context mContext, ListView mListView) {
        super(mContext, mListView);
        this.mContext = mContext;
        colors = mContext.getResources().getStringArray(R.array.chartColor);
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {

        ViewHolder vh;
        final MyProductModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_pie_chart_item, null);
            vh.name = (TextView) itemView.findViewById(R.id.name);
            vh.back = (RelativeLayout) itemView.findViewById(R.id.rootLayout);
            vh.price = (TextView) itemView.findViewById(R.id.price);
            vh.square = (View) itemView.findViewById(R.id.squareView);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        if (position > 0 && position % 2 != 0) {
            vh.back.setBackgroundColor(Color.parseColor("#fff7f0"));
        } else {
            vh.back.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        vh.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItem!=null){
                    onClickItem.click(model);
                }
            }
        });
        vh.name.setText(model.getName());
        vh.price.setText(((float) model.getAmount() / 10000) + "万");
        vh.square.setBackgroundColor(Color.parseColor(colors[position]));
        return itemView;
    }

    class ViewHolder {
        TextView name, price;
        RelativeLayout back;
        View square;
    }
}
