package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.ReportModel;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yanbo on 2016/7/20.
 */
public class ReportListByTypeAdapter extends BasePlatAdapter<ReportModel> {
    Context mContext;
    public ReportListByTypeAdapter(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        ReportModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_report_item_new, null);
            vh.time = (TextView)itemView.findViewById(R.id.report_item_time);
            vh.title = (TextView)itemView.findViewById(R.id.report_item_title);
            vh.content = (TextView)itemView.findViewById(R.id.report_item_content);
            vh.number = (TextView)itemView.findViewById(R.id.report_item_bumber);
            vh.image = (ImageView)itemView.findViewById(R.id.report_item_image);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        vh.title.setText(model.getName());
        ImageLoader.getInstance(mContext,R.drawable.default_error).displayImage(model.getCover(),vh.image);
        vh.content.setText(model.getSummary());


        vh.number.setText(model.getReadCount()+"");
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1 = formatter.format(currentTime);
        String date2=DateTools.timestamp2Date(String.valueOf(model.getCtime()));
//        String date2 =DateTools.formatData("yyyy-MM-dd HH:mm:ss", DateTools.timestamp2Date(String.valueOf(model.getCtime())));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = df.parse(date1);
            Date d2 = df.parse(date2);
            if(((d1.getTime() - d2.getTime())/(60*60*1000))<=1) {
                vh.time.setText("刚刚");
//            }else if(((d1.getTime() - d2.getTime())/(24*60*60*1000))<=1){
//                Logger.e("昨天");
//                vh.time.setText("昨天");
            }else{
                String date=DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5,model.getCtime());
                date=date.substring(0,4)+"."+date.substring(5,7)+"."+date.substring(8,11);
                vh.time.setText("复华资产  "+date);
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return itemView;
    }
    class ViewHolder {
        TextView title, content,time,number;
        ImageView image;
    }
}
