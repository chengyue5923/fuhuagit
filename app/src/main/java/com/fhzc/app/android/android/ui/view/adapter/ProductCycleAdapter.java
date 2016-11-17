package com.fhzc.app.android.android.ui.view.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.platform.utils.android.Common;
import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.widget.ProductDateView;
import com.fhzc.app.android.models.MyProductModel;
import com.fhzc.app.android.utils.im.CommonUtil;

/**
 * Created by yanbo on 2016/7/20.
 */
public class ProductCycleAdapter extends BasePlatAdapter<MyProductModel> {
    Context mContext;

    public ProductCycleAdapter(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        MyProductModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.view_product_cycle_item, null);
            vh.container = (RelativeLayout) itemView.findViewById(R.id.container);
            vh.grayBg = (ImageView) itemView.findViewById(R.id.grayBg);
            vh.rootLayout = (RelativeLayout) itemView.findViewById(R.id.rootLayout);
            vh.productName = (TextView) itemView.findViewById(R.id.productName);
            vh.my_progress = (ProgressBar) itemView.findViewById(R.id.my_progressww);
            vh.pointViewLayout = (RelativeLayout) itemView.findViewById(R.id.pointLayout);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        vh.productName.setText(model.getName());
        //进度条
        int width = Common.screenWidth((Activity) mContext) * 2 ;
//                + CommonUtil.dip2px(mContext, 11);
        LinearLayout.LayoutParams rootParams = new LinearLayout.LayoutParams(width, CommonUtil.dip2px(mContext, 14));
        vh.rootLayout.setLayoutParams(rootParams);
        //日期布局的父布局
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        vh.container.setLayoutParams(containerParams);

        //今天距成立日天数
        int todayDifferFound = DateTools.todayDifferDay(model.getFoundDay());
        //成立日距到期日天数
        int totalDay = DateTools.differDayTime(model.getExpireDay(),model.getFoundDay());
        //今天到成立日百分比
        float toadyDifferFoundPercent = (float) todayDifferFound / totalDay;
        if (toadyDifferFoundPercent<0){
            toadyDifferFoundPercent=0;
        }else if(toadyDifferFoundPercent>1){
            toadyDifferFoundPercent = 1;
        }
        vh.my_progress.setSecondaryProgress(Math.round(toadyDifferFoundPercent*100));
        //灰色遮盖的长度
        RelativeLayout.LayoutParams grayParams = new RelativeLayout.LayoutParams((int) (width-(width * toadyDifferFoundPercent)), ViewGroup.LayoutParams.MATCH_PARENT);
        grayParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        vh.grayBg.setLayoutParams(grayParams);
        vh.container.removeAllViews();
        vh.pointViewLayout.removeAllViews();
        //添加成立日
        ProductDateView foundView = new ProductDateView(mContext);
        foundView.bindData(0, DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getFoundDay()));
        vh.container.addView(foundView);
        //添加到期日
        ProductDateView view = new ProductDateView(mContext);
        RelativeLayout.LayoutParams deadParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        deadParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        view.bindData(1, DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getExpireDay()));
        vh.container.addView(view, deadParams);
        try {
            //添加派息日
            String dividends = model.getDividendDay();
            if (!StringTools.isNullOrEmpty(dividends)){
                String[] strings = dividends.split(",");
                for (int i = 0; i < strings.length; i++) {
                        if(DateTools.getTime(strings[i], DateTools.DATE_FORMAT_STYLE_3)!=model.getExpireDay()){
                            int day = DateTools.differDayTime(DateTools.getTime(strings[i], DateTools.DATE_FORMAT_STYLE_3), model.getFoundDay());
                            float percent = (float) day / totalDay;
                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            layoutParams.setMargins(((int) (width * percent)) - CommonUtil.dip2px(mContext, 35), 0, 0, 0);
                            ProductDateView dividendView = new ProductDateView(mContext);
                            dividendView.bindData(2, strings[i]);
                            vh.container.addView(dividendView, layoutParams);
                            if(percent<toadyDifferFoundPercent){
                                View vi=LayoutInflater.from(context).inflate(R.layout.activity_point_view, null);
                                RelativeLayout.LayoutParams pointgrayParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                                pointgrayParams.setMargins(((int) (width * percent)) - CommonUtil.dip2px(mContext,5), 0, 0, 0);
                                vh.pointViewLayout.addView(vi,pointgrayParams);
                            }
                        }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return itemView;
    }

    class ViewHolder {
        ProgressBar my_progress;
        RelativeLayout container;
        ImageView grayBg;
        RelativeLayout rootLayout;
        TextView productName;
        RelativeLayout pointViewLayout;
    }
}
