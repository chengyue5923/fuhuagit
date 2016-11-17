package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.models.PointModel;

import java.util.Locale;


/**
 * Created by apple on 16/7/27.
 */
public class PointTypeAdapter extends RecyclingPagerAdapter {
    private final Context mContext;
    public PointModel records;



    public PointTypeAdapter(Context context,PointModel records) {
        mContext = context;
        this.records = records;
    }

    public void setRes(PointModel point){
        this.records = point;
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public View getView(int position, View view, ViewGroup container) {
        ViewHolder holder;
        if(view == null){
            view = LayoutInflater.from(mContext).inflate(R.layout.pointtype,null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        String title = "";
        int lable = 0 ;
        if(records !=null){
            switch (position){
                case 0:
                    title = "您的积分";
                    lable = records.getYours();
                    break;
                case 1:
                    title = "可用积分";
                    lable = records.getAvailable();
                    break;
                case 2:
                    title = "冻结积分";
                    lable = records.getFrozen();
                    break;
                case 3:
                    title = "即将过期";
                    lable = records.getWillExpired();
                    break;
            }
        }
        holder.title .setText(title);
        holder.content = (TextView)view.findViewById(R.id.pointtype_content);
        holder.content.setText(String.format(Locale.CHINESE,"%d",lable));
        return view;
    }

    private class ViewHolder{
        public TextView title, content;
        public ViewHolder(View view){
            title = (TextView)view.findViewById(R.id.pointtype_title);
            content = (TextView)view.findViewById(R.id.pointtype_content);
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
