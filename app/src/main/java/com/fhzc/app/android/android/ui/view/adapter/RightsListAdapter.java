package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.RightModel;
import com.fhzc.app.android.utils.im.ImageLoader;


/**
 * 权益列表Adapter
 */
public class RightsListAdapter extends BasePlatAdapter<RightModel> {
    private Context mContext;
    boolean isCollect;

    public RightsListAdapter(Context context, boolean isCollect) {
        super(context);
        mContext = context;
        this.isCollect = isCollect;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.adapter_right_list_layout, null);
//            vh.content = (TextView) itemView.findViewById(R.id.right_item_content);
//            vh.iamge = (ImageView) itemView.findViewById(R.id.right_item_image);
//            vh.title = (TextView) itemView.findViewById(R.id.right_item_title);
//            vh.timeTextViews = (TextView) itemView.findViewById(R.id.timeTextViews);
//            vh.levelTextViews = (TextView) itemView.findViewById(R.id.levelTextViews);
            vh.right_item_image_new = (ImageView) itemView.findViewById(R.id.right_item_image_new);
            vh.dradleImage = (ImageView) itemView.findViewById(R.id.dradleImage);
            vh.leaval_text = (TextView) itemView.findViewById(R.id.leaval_text);
            vh.right_item_title_new = (TextView) itemView.findViewById(R.id.right_item_title_new);
            vh.right_item_content_new_statues = (TextView) itemView.findViewById(R.id.right_item_content_new_statues);
            vh.right_item_content_new = (TextView) itemView.findViewById(R.id.right_item_content_new);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        RightModel model = getItem(position);
        vh.right_item_title_new.setText(model.getName());
        ImageLoader.getInstance(mContext).displayImage(model.getCover(),vh.right_item_image_new);

        try{
            if (model.getLevel()==5) {
                vh.dradleImage.setBackground(context.getResources().getDrawable(R.drawable.black_leavel));
                vh.leaval_text.setText("黑金卡以上专享");
            } else if (model.getLevel()==4) {
                vh.dradleImage.setBackground(context.getResources().getDrawable(R.drawable.gold_leveal));
                vh.leaval_text.setText("金卡以上专享");
            } else if(model.getLevel()==3){
                vh.dradleImage.setBackground(context.getResources().getDrawable(R.drawable.silver_leavel));
                vh.leaval_text.setText("银卡以上专享");
            }else if(model.getLevel()==2){
                vh.dradleImage.setBackground(context.getResources().getDrawable(R.drawable.vip_leavel));
                vh.leaval_text.setText("会员以上专享");
            }else if(model.getLevel()==1){
                vh.dradleImage.setBackground(context.getResources().getDrawable(R.drawable.finalcial_leavel));
                    vh.leaval_text.setText("投资人以上专享");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        vh.right_item_content_new.setText(model.getSpend_score()+"积分");
//        try{
//            Logger.e("modeld-------------" + model.getName() + model.getIsReserved() + DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getMarkDate()));
//            if(model.getIsReserved()==0){
//                vh.right_item_content_new.setVisibility(View.VISIBLE);
//                String date=DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getMarkDate());
//                Logger.e("date" + date);
//                String s=date.substring(0,4)+"."+date.substring(5,7)+"."+date.substring(8);
//                Logger.e("date" + s);
//                vh.right_item_content_new.setText("已预约："+ s);
//            }else if(model.getIsReserved()==1){
//                vh.right_item_content_new.setVisibility(View.VISIBLE);
//                String date=DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getMarkDate());
//                String s=date.substring(0,4)+"."+date.substring(5,7)+"."+date.substring(8);
//                vh.right_item_content_new.setText("待使用："+s);
//            }else{
//                vh.right_item_content_new.setVisibility(View.GONE);
//            }
//        }catch (Exception e){
//            Logger.e("eeee"+e.getMessage());
//        }


//        vh.title.setText(model.getName());
//        vh.timeTextViews.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getCtime()));
//
//        vh.content.setText(isCollect ? model.getSpend_score() + "" : model.getSpendScore() + "");
//        try {
//            if (model.getLevel() == 5) {
//                if (model.getLevelName() == null) {
//                    vh.levelTextViews.setText("黑金卡");
//                } else {
//                    vh.levelTextViews.setText(model.getLevelName());
//                }
//                vh.levelTextViews.setTextColor(mContext.getResources().getColor(R.color.black));
//                vh.levelTextViews.setBackground(mContext.getResources().getDrawable(R.drawable.adapter_right_black_card_bg));
//            } else if (model.getLevel() == 4) {
//                if (model.getLevelName() == null) {
//                    vh.levelTextViews.setText("金卡");
//                } else {
//                    vh.levelTextViews.setText(model.getLevelName());
//                }
//                vh.levelTextViews.setTextColor(mContext.getResources().getColor(R.color.goldColor));
//                vh.levelTextViews.setBackground(mContext.getResources().getDrawable(R.drawable.adapter_right_glod_card_bg));
//            } else if (model.getLevel() == 3) {
//                if (model.getLevelName() == null) {
//                    vh.levelTextViews.setText("银卡");
//                } else {
//                    vh.levelTextViews.setText(model.getLevelName());
//                }
//                vh.levelTextViews.setTextColor(mContext.getResources().getColor(R.color.silverColor));
//                vh.levelTextViews.setBackground(mContext.getResources().getDrawable(R.drawable.adapter_right_silver_card_bg));
//            } else if (model.getLevel() == 2) {
//                if (model.getLevelName() == null) {
//                    vh.levelTextViews.setText("准会员");
//                } else {
//                    vh.levelTextViews.setText(model.getLevelName());
//                }
//                vh.levelTextViews.setTextColor(mContext.getResources().getColor(R.color.silverColor));
//                vh.levelTextViews.setBackground(mContext.getResources().getDrawable(R.drawable.adapter_right_silver_card_bg));
//            } else if (model.getLevel() == 1) {
//                if (model.getLevelName() == null) {
//                    vh.levelTextViews.setText("投资人");
//                } else {
//                    vh.levelTextViews.setText(model.getLevelName());
//                }
//                vh.levelTextViews.setTextColor(mContext.getResources().getColor(R.color.silverColor));
//                vh.levelTextViews.setBackground(mContext.getResources().getDrawable(R.drawable.adapter_right_silver_card_bg));
//            }
//
//        } catch (Exception e) {
//            Logger.e("models22222" + model.toString());
//            Logger.e(e.getMessage());
//        }
//        ImageLoader.getInstance(mContext, R.drawable.default_error).displayImage(model.getCover(), vh.iamge);

        return itemView;
    }

    class ViewHolder {
        ImageView iamge,right_item_image_new,dradleImage;
        TextView title, content, timeTextViews, levelTextViews,leaval_text,right_item_title_new,right_item_content_new_statues,right_item_content_new;



    }
}
