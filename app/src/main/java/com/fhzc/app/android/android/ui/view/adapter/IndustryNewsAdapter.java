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
import com.fhzc.app.android.models.NewsModel;
import com.fhzc.app.android.utils.im.ImageLoader;

/**
 * Created by fw on 16/10/14.
 */

public class IndustryNewsAdapter extends BasePlatAdapter<NewsModel> {
    private Context mContext;

    public boolean isIndex() {
        return isIndex;
    }

    public void setIndex(boolean index) {
        isIndex = index;
    }

    private boolean isIndex;
    public IndustryNewsAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        NewsModel model = getItem(position);
        if(convertView == null){
            vh = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.adapter_industrynews,null);
            vh.ivCover = (ImageView)convertView.findViewById(R.id.iv_cover);
            vh.tvDate = (TextView)convertView.findViewById(R.id.tv_date);
            vh.tvTitle = (TextView)convertView.findViewById(R.id.tv_title);
            vh.tvSummary = (TextView)convertView.findViewById(R.id.tv_summary);
            vh.tvReadCount = (TextView)convertView.findViewById(R.id.tv_readcount);
            vh.tvCompany = (TextView)convertView.findViewById(R.id.tv_company);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        if(model!=null){
            String date = DateTools.dateFormatterOfDateTime(DateTools.DATE_FORMAT_STYLE_3,model.getCtime());
//            Logger.e("fuwen date="+date);
            vh.tvDate.setText(date);
            vh.tvCompany.setText(model.getPublisher());
            vh.tvTitle.setText(model.getTitle());
            vh.tvSummary.setText(model.getSummary());
            vh.tvReadCount.setText(String.valueOf(model.getReadCount()));
            if(isIndex){
                switch (position){
                    case 0:
                        vh.ivCover.setImageResource(R.drawable.index_gsxw);
                        break;
                    case 1:
                        vh.ivCover.setImageResource(R.drawable.index_hyxw);
                        break;
                    case 2:
                        vh.ivCover.setImageResource(R.drawable.index_jkxw);
                        break;
                }
                vh.tvSummary.setVisibility(View.GONE);
            }else{
                ImageLoader.getInstance(mContext,R.drawable.default_error).displayImage(model.getCover(),vh.ivCover);
            }
        }
        return convertView;
    }

    class ViewHolder {
        TextView tvDate,tvTitle,tvSummary,tvContent,tvReadCount,tvCompany;
        ImageView ivCover;
    }
}
