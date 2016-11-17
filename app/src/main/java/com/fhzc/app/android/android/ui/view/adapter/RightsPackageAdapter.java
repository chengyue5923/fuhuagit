package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.models.ChoosePackageModel;


/**
 * 选择套餐Adapter
 */
public class RightsPackageAdapter extends BasePlatAdapter<ChoosePackageModel> {

    public RightsPackageAdapter(Context context) {
        super(context);
    }
    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        ViewHolder vh;
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.view_selected_client_item, null);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }

        return itemView;
    }
    class ViewHolder {
        ImageView chooseFlagImage;
        TextView lockyInteralText,digressAboveText,interalNumberText,packageNumberText;
    }
}
