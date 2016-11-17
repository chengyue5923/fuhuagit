package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.NewsModel;
import com.fhzc.app.android.utils.im.ImageLoader;

/**
 * Created by fw on 16/10/13.
 */

public class CompanyNewsAdapter extends BasePlatAdapter <NewsModel>{
    private Context mContext;
    public CompanyNewsAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        NewsModel model = getItem(position);
        if(convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_companynews,null);
            vh.ivCover = (ImageView)convertView.findViewById(R.id.iv_cover);
            vh.tvSummary = (TextView)convertView.findViewById(R.id.tv_content);
            vh.tvMonth = (TextView)convertView.findViewById(R.id.tv_month);
            vh.tvDay = (TextView)convertView.findViewById(R.id.tv_day);
            vh.tvTitle = (TextView)convertView.findViewById(R.id.tv_title);
            vh.tvOtherTitle = (TextView)convertView.findViewById(R.id.tv_summary);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        if(model!=null){
            String date = DateTools.dateFormatterOfDateTime(DateTools.DATE_FORMAT_STYLE_10,model.getCtime());
            vh.tvDay.setText(date.substring(8,10));
            vh.tvDay.setTypeface(Typeface.MONOSPACE,Typeface.BOLD_ITALIC);
            vh.tvMonth.setText(date.substring(5,8));
//            Spanned spanned = Html.fromHtml(model.getContent());
            vh.tvSummary.setText(model.getSummary());
            vh.tvTitle.setText(model.getTitle());
            vh.tvOtherTitle.setText(model.getOtherTitle());
            ImageLoader.getInstance(mContext,R.drawable.default_error).displayImage(model.getCover(),vh.ivCover);
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvMonth,tvDay,tvTitle, tvOtherTitle, tvSummary;
        ImageView ivCover;
    }
}
