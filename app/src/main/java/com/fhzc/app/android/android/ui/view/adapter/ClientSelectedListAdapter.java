package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.view.adapter.BasePlatAdapter;
import com.fhzc.app.android.R;
import com.fhzc.app.android.configer.enums.CustomerType;
import com.fhzc.app.android.models.MemberModel;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.util.List;


/**
 * 选择客户Adapter
 */
public class ClientSelectedListAdapter extends BasePlatAdapter<MemberModel> {
    private List<MemberModel> list;

    public ClientSelectedListAdapter(Context context, List<MemberModel> ids) {
        super(context);
        list = ids;
    }

    public List<MemberModel> getList() {
        return list;
    }

    public void setList(List<MemberModel> list) {
        this.list = list;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        final ViewHolder vh;
        final MemberModel memberModel = getItem(position);
        if (itemView == null) {
            vh = new ViewHolder();
            itemView = LayoutInflater.from(context).inflate(R.layout.view_selected_client_item, null);
            vh.name = (TextView) itemView.findViewById(R.id.clientName);
            vh.level = (ImageView) itemView.findViewById(R.id.clientLevel);
            vh.money = (TextView) itemView.findViewById(R.id.clientMoney);
            vh.avatar = (ImageView) itemView.findViewById(R.id.clientAvatar);
            vh.selectIv = (CheckBox) itemView.findViewById(R.id.clientCheckIv);
            itemView.setTag(vh);
        } else {
            vh = (ViewHolder) itemView.getTag();
        }
        vh.level.setImageResource(CustomerType.getCustomerByType(memberModel.getLevel()).getClientListBg());
        vh.name.setText(memberModel.getRealname());
        ImageLoader.getInstance(context, R.drawable.im_default_user_portrait_corner).displayImage(memberModel.getAvatar(), vh.avatar);
        vh.selectIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!vh.selectIv.isChecked()) {
                    list.remove(memberModel);
                } else {
                    list.add(memberModel);
                }

            }
        });
        vh.money.setText((Long.parseLong(memberModel.getAssetsSum()))/10000+"万元");
        return itemView;
    }

    class ViewHolder {
        TextView name, money;
        ImageView avatar, level;
        CheckBox selectIv;
    }
}
