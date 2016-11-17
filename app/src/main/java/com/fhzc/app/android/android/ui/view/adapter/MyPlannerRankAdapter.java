package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.RankbackDataModel;
import com.fhzc.app.android.utils.im.ImageLoader;

/**
 * Created by yanbo on 2016/7/25.
 */
public class MyPlannerRankAdapter extends BasePlatAdapter<RankbackDataModel> {
    private Context mContext;

    public MyPlannerRankAdapter(Context context) {
        super(context);
        mContext = context;
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        RankbackDataModel model = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_my_planner_item, null);
            vh.ranglist = (ImageView)itemView.findViewById(R.id.ranglist);
            vh.yeji = (TextView)itemView.findViewById(R.id.yeji);
            vh.personinfoimages = (ImageView)itemView.findViewById(R.id.personinfoimages);
            vh.rankid = (TextView)itemView.findViewById(R.id.rankid);
            vh.nameText = (TextView)itemView.findViewById(R.id.nameText);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        try{
            if(model.getSort()==1){
                vh.ranglist.setVisibility(View.VISIBLE);
                vh.rankid.setVisibility(View.GONE);
                vh.ranglist.setImageDrawable(context.getResources().getDrawable(R.drawable.firstback));
            }else if(model.getSort()==2){
                vh.ranglist.setVisibility(View.VISIBLE);
                vh.rankid.setVisibility(View.GONE);
                vh.ranglist.setImageDrawable(context.getResources().getDrawable(R.drawable.secondback));
            }else if(model.getSort()==3){
                vh.ranglist.setVisibility(View.VISIBLE);
                vh.rankid.setVisibility(View.GONE);
                vh.ranglist.setImageDrawable(context.getResources().getDrawable(R.drawable.thirdback));
            } else{
                vh.ranglist.setVisibility(View.GONE);
                vh.rankid.setVisibility(View.VISIBLE);
                vh.rankid.setText(model.getSort()+"");
            }
            vh.nameText.setText(model.getName());
            vh.yeji.setText(model.getAnnualised() + "");
            ImageLoader.getInstance(mContext,R.drawable.selected_peroninfo).displayImage(model.getAvatar(),vh.personinfoimages);
        }catch (Exception e){
            e.printStackTrace();
        }
       return itemView;
    }
    class ViewHolder {
        ImageView  personinfoimages,ranglist;
        TextView yeji,rankid,nameText;
    }
}
