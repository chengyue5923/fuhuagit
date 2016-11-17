package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.PointRecordDetail;

/**
 * Created by apple on 16/8/3.
 */
public class PointRecordDetailAdapter extends BasePlatAdapter<PointRecordDetail> {


    private String format;
    private DateFormat dateFormat;
    public PointRecordDetailAdapter(Context context) {
        super(context);
        this.context = context;
        format = "yyyy-MM-dd";
        dateFormat = new DateFormat();
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view == null){
            view  = mLayoutInflater.inflate(R.layout.lvadapter_pointrecord,null);
            vh = new ViewHolder();
            vh.tvContent = (TextView)view.findViewById(R.id.tv_describe);
            vh.tvDate = (TextView)view.findViewById(R.id.tv_date);
            vh.tvPoint = (TextView)view.findViewById(R.id.tv_point);
            vh.tvStatus = (TextView)view.findViewById(R.id.tv_status);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }

        PointRecordDetail detail = getItem(i);
        if(detail!=null){
            vh.tvPoint.setText(detail.getScore()+"");
            String status = detail.getStatus();
            if(status.equals("add")){
                status = "增加";
            }else if(status.equals("consume")){
                status = "消费";
            }else if(status.equals("frozen")){
                status = "冻结";
            }else if (status.equals("expire")) {
                status = "过期";
            }else if(status.equals("willExpired")){
                //即将过期积分  写死 并非服务器返回status
                status = "即将过期";
            }
            vh.tvStatus.setText(status);

            if(status!="即将过期"){
                vh.tvDate.setText(dateFormat.format(format,detail.getCtime()));
                String content = detail.getDetail();
                if(content.contains("名称")){
                    content = content.substring(content.indexOf("名称")+3);
                }
                vh.tvContent.setText(content);
            }else {
                vh.tvContent.setText("即将到期的积分");
                vh.tvDate.setText("");
            }
        }

        return view;
    }

    private class ViewHolder {
        TextView  tvDate,tvStatus,tvContent,tvPoint;

    }
}
