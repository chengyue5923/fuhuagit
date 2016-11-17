/*
 * Copyright 2014 trinea.cn All right reserved. This software is the confidential and proprietary information of
 * trinea.cn ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into with trinea.cn.
 */
package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.base.platform.utils.java.ListUtil;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.BannerModel;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * ImagePagerAdapter
 */
public class CommodityDetailPagerAdapter extends RecyclingPagerAdapter {

    private Context context;
    private List<BannerModel> imagePaths;
    private LayoutInflater inflater;

    public CommodityDetailPagerAdapter(Context context) {
        this.context = context;
        imagePaths = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        // Infinite loop
//        return null == imagePaths ? 0 : imagePaths.size();
        return ListUtil.isNullOrEmpty(imagePaths) ? 0 : imagePaths.size();
    }

    public void setRes(List<BannerModel> list) {
        if (ListUtil.isNullOrEmpty(list)) {
            return;
        }
        imagePaths.clear();
        imagePaths.addAll(list);
        notifyDataSetChanged();
    }

    public BannerModel getItem(int position) {
        try {
            return imagePaths.get(position);

        } catch (Exception E) {
            return null;
        }
    }


    @Override
    public View getView(final int position, View view, ViewGroup container) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.commondity_detail_banner_item, null);
            holder.imageView = (ImageView) view.findViewById(R.id.commondity_detail_banner_item_iv);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Logger.e("-----------------imagePaths---------position---------" + v.getTag(R.id.viewBannerImage).toString());
//                    ;
                    try {
                        mListener.click(imagePaths.get(position));
                    }catch (Exception E){

                    }
                }
            });
            view.setTag(R.id.viewBanner,holder);
        } else {
            holder = (ViewHolder) view.getTag(R.id.viewBanner);
        }
         holder.imageView.setTag(R.id.viewBannerImage,position);

        ImageLoader.getInstance(context,R.drawable.selected_default).displayImage(imagePaths.get(position).getCover(),holder.imageView);
        return view;
    }


    private static class ViewHolder {
        ImageView imageView;
    }

    private ClickListener mListener;

    public void setListener(ClickListener mListener) {
        this.mListener = mListener;
    }

    public interface ClickListener {
        public void click(BannerModel model);
    }

}
