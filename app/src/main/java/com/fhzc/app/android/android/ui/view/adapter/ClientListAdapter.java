package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.MemberModel;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.text.DecimalFormat;


/**
 * 选择客户Adapter
 */
public class ClientListAdapter extends BasePlatAdapter<MemberModel> {


    public ClientListAdapter(Context context) {
        super(context);

    }
    boolean flag=false;
    public ClientListAdapter(Context context,boolean flag) {
        super(context);
        this.flag=flag;

    }
    public void SetFlag(boolean fl){
        flag=fl;
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        final ViewHolder vh;
        final MemberModel memberModel = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.view_client_item, null);
            vh.name = (TextView) itemView.findViewById(R.id.clientName);
            vh.level = (ImageView) itemView.findViewById(R.id.clientLevel);
            vh.money = (TextView) itemView.findViewById(R.id.clientMoney);
            vh.avatar = (ImageView) itemView.findViewById(R.id.clientAvatar);
            vh.textLayout = (LinearLayout) itemView.findViewById(R.id.textLayout);
            vh.Customertext = (TextView) itemView.findViewById(R.id.Customertext);

            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
//        vh.money.setText(memberModel.getAssetsSum()+"元");
        double youIn = Double.parseDouble(String.valueOf(memberModel.getAssetsSum()));
        double l = 10000;
        DecimalFormat df = new DecimalFormat("0.00");
        vh.money.setText(df.format(youIn / l) + "万元");
        try{
            if(flag){
                if(memberModel.getMemo().equals("")){
                    vh.textLayout.setVisibility(View.GONE);
                }else{
                    vh.textLayout.setVisibility(View.VISIBLE);
                    vh.Customertext.setText(memberModel.getMemo());
                }
            }else{
                vh.textLayout.setVisibility(View.GONE);
            }
            if(memberModel.getLevel().equals("铂金卡")){
                vh.level.setImageResource(R.drawable.blgck_gold_sign_view);
            }else if(memberModel.getLevel().equals("金卡")){
                vh.level.setImageResource(R.drawable.gold_sign_bg_view);
            }else if(memberModel.getLevel().equals("银卡")){
                vh.level.setImageResource(R.drawable.silver_sign_view);
            }else if(memberModel.getLevel().equals("投资人")){
                vh.level.setImageResource(R.drawable.finacnal_bg_dign_view);
            }else{
                vh.level.setImageResource(R.drawable.fincnail_sign_view);
            }
//            vh.level.setImageResource(CustomerType.getCustomerByType(memberModel.getLevel()).getClientListBg());
            vh.name.setText(memberModel.getRealname());
            ImageLoader.getInstance(context, R.drawable.im_default_user_portrait_corner).displayImage(memberModel.getAvatar(), vh.avatar);
        }catch (Exception e){
        }
        return itemView;
    }

    class ViewHolder {
        LinearLayout textLayout;
        TextView name, money,Customertext;
        ImageView avatar, level;

    }
}
