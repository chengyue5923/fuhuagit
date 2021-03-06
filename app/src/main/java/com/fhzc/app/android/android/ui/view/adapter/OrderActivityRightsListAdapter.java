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
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.models.OrderRightModel;
import com.fhzc.app.android.utils.im.ImageLoader;


/**
 * 权益列表Adapter
 */
public class OrderActivityRightsListAdapter extends BasePlatAdapter<OrderRightModel> {
    private Context mContext;
    public OrderActivityRightsListAdapter(Context context, boolean flags) {
        super(context);
        mContext = context;
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.adapter_right_right_layout, null);
            vh.content=(TextView)itemView.findViewById(R.id.right_item_content_new);
            vh.iamge=(ImageView)itemView.findViewById(R.id.right_item_image_new);
            vh.dradleImage=(ImageView)itemView.findViewById(R.id.dradleImage);

            vh.title=(TextView)itemView.findViewById(R.id.right_item_title_new);
            vh.levelTextViews=(TextView)itemView.findViewById(R.id.leaval_text);
            vh.statexTextView=(TextView)itemView.findViewById(R.id.statexTextView);
            vh.right_item_content_new_statues=(TextView)itemView.findViewById(R.id.right_item_content_new_statues);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        OrderRightModel model=getItem(position);
        vh.title.setText(model.getName());
        ImageLoader.getInstance(mContext).displayImage(model.getCover(),vh.iamge);
        try {
            if (String.valueOf(model.getSpendScore()) != null) {
                vh.content.setText(model.getSpendScore() + "积分");
            }
            if (!String.valueOf(model.getLevelName()).equals("")) {
                 if (model.getLevel() .equals("5") ) {
                    vh.dradleImage.setBackground(context.getResources().getDrawable(R.drawable.platinum_gold_view_right));
                     if(!model.getLevelName().equals("")){
                         vh.levelTextViews.setText(model.getLevelName()+"以上专享");
                     }
                } else if (model.getLevel() .equals("4") ) {
                    vh.dradleImage.setBackground(context.getResources().getDrawable(R.drawable.gold_view_right_view));
                     if(!model.getLevelName().equals("")) {
                         vh.levelTextViews.setText(model.getLevelName() + "以上专享");
                     }
                } else if(model.getLevel().equals("3")){
                    vh.dradleImage.setBackground(context.getResources().getDrawable(R.drawable.silver_rigth_view_view));
                     if(!model.getLevelName().equals("")) {
                         vh.levelTextViews.setText(model.getLevelName() + "以上专享");
                     }
                }else if(model.getLevel().equals("2")){
                    vh.dradleImage.setBackground(context.getResources().getDrawable(R.drawable.vip_right_view_view));
                     if(!model.getLevelName().equals("")) {
                         vh.levelTextViews.setText(model.getLevelName() + "以上专享");
                     }
                }else if(model.getLevel().equals("1")){
                    vh.dradleImage.setBackground(context.getResources().getDrawable(R.drawable.fincnial_right_view));
                     if(!model.getLevelName().equals("")) {
                         vh.levelTextViews.setText(model.getLevelName() + "以上专享");
                     }
                }
            }else{
                vh.levelTextViews.setText("");
            }
            if(UserPreference.isCustomer()){
                try{
                    if(model.getIsReserved()==0){
                        vh.right_item_content_new_statues.setVisibility(View.VISIBLE);
                        String date=DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getMark_date());
                        String s=date.substring(0,4)+"."+date.substring(5,7)+"."+date.substring(8);
                        vh.right_item_content_new_statues.setText("已预约："+ s);
                    }else if(model.getIsReserved()==1){
                        vh.right_item_content_new_statues.setVisibility(View.VISIBLE);
                        String date=DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getMark_date());
                        String s=date.substring(0,4)+"."+date.substring(5,7)+"."+date.substring(8);
                        vh.right_item_content_new_statues.setText("待使用："+s);
                    }else{
                        vh.right_item_content_new_statues.setVisibility(View.GONE);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                vh.right_item_content_new_statues.setVisibility(View.GONE);
            }


//            if(String.valueOf(model.getStatus())!=null&&flag){
//                Logger.e("model.getStates()"+model.getStatus());
//                if(model.getStatus()==2){
//                    vh.statexTextView.setVisibility(View.VISIBLE);
//                    vh.statexTextView.setText("预约失败");
//                }else if(model.getStatus()==3){
//                    vh.statexTextView.setVisibility(View.VISIBLE);
//                    vh.statexTextView.setText("客户取消预约");
//                }else if(model.getStatus()==4){
//                    vh.statexTextView.setVisibility(View.VISIBLE);
//                    vh.statexTextView.setText("客户消费");
//                }else if(model.getStatus()==5){
//                    vh.statexTextView.setVisibility(View.VISIBLE);
//                    vh.statexTextView.setText("客户缺席");
//                }
//            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemView;
    }
    class ViewHolder {
        ImageView iamge,dradleImage;
        TextView title,statexTextView,content,timeTextViews,levelTextViews,right_item_content_new_statues;
    }

}
