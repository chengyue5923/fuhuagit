package com.fhzc.app.android.android.ui.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.configer.enums.MyCollectType;
import com.fhzc.app.android.models.CollectActivityModel;
import com.fhzc.app.android.models.CollectProductModel;
import com.fhzc.app.android.models.ReportModel;
import com.fhzc.app.android.models.RightModel;
import com.fhzc.app.android.utils.im.ImageLoader;

import java.util.List;

/**
 * Created by User on 2016/8/4.
 */
public class EditCollectAdapter extends BaseAdapter {
    Context mContext;
    List<Object> list;
    private List<Object> ids;
    private OnCheckListener onCheckListener;

    public List<Object> getIds() {
        return ids;
    }

    public void setIds(List<Object> ids) {
        this.ids = ids;
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }

    public interface OnCheckListener {
        void onCheck();
    }

    public EditCollectAdapter(Context context, List<Object> ids) {
        this.ids = ids;
        mContext = context;
    }

    @Override
    public Object getItem(int position) {
        if (position >= getCount() || position < 0) {
            return null;
        }
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        if (null == list) {
            return 0;
        } else {
            return list.size();
        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int typeIndex = getItemViewType(position);
        MyCollectType renderType = MyCollectType.values()[typeIndex];
        switch (renderType) {
            case COLLECT_TYPE_PRODUCT:
                // 直接返回
                convertView = productRender(position, convertView);
                break;
            case COLLECT_TYPE_ACTIVITY:
                convertView = activityRender(position, convertView);
                break;
            case COLLECT_TYPE_RIGHT:
                convertView = rightRender(position, convertView);
                break;
            case COLLECT_TYPE_REPORT:
                convertView = reportRender(position, convertView);
                break;
        }
        return convertView;
    }

    /**
     * 产品的渲染
     */
    private View productRender(int position, View itemView) {
        final ProductHolder productHolder;
        final CollectProductModel model = (CollectProductModel) getItem(position);
        if (null == itemView) {
            productHolder = new ProductHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_product_item, null);
            productHolder.titleNameText = (TextView) itemView.findViewById(R.id.titleNameText);
            productHolder.interestNumber = (TextView) itemView.findViewById(R.id.interestNumber);
            productHolder.timeText = (TextView) itemView.findViewById(R.id.timeText);
            productHolder.productStatus = (ImageView) itemView.findViewById(R.id.productStatus);
            productHolder.distributionMode = (TextView) itemView.findViewById(R.id.distributionMode);
            productHolder.endTimeText = (TextView) itemView.findViewById(R.id.endTimeText);
            productHolder.interestProgress = (ProgressBar) itemView.findViewById(R.id.interestProgress);
            productHolder.productCheckBox = (CheckBox) itemView.findViewById(R.id.productCheckBox);
            itemView.setTag(productHolder);
        } else {
            productHolder = (ProductHolder) itemView.getTag();
        }
        productHolder.productCheckBox.setVisibility(View.VISIBLE);
        productHolder.productCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!productHolder.productCheckBox.isChecked()) {
                    ids.remove(model);
                } else {
                    ids.add(model);
                }
                if (onCheckListener != null)
                    onCheckListener.onCheck();
            }
        });
        productHolder.distributionMode.setText(model.getIncomeDistributionType());
        productHolder.productStatus.setImageResource(model.getStatus() == 1 ? R.drawable.product_preheat : model.getStatus() == 2 ? R.drawable.product_haveinhand : 0);
        productHolder.titleNameText.setText(model.getName());

        String annualInterval = model.getAnnualInterval();
        if(StringTools.isNullOrEmpty(annualInterval)){
            annualInterval = model.getAnnualYield();
        }
        productHolder.interestNumber.setText(annualInterval);
        productHolder.timeText.setText(model.getRenew_deadline() + "个月");

        return itemView;
    }

    /**
     * 活动的渲染
     */
    private View activityRender(int position, View itemView) {
        final ActivityHolder activityViewHolder;
        final CollectActivityModel model = (CollectActivityModel) getItem(position);
        if (null == itemView) {
            activityViewHolder = new ActivityHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_activity_item, null);
            activityViewHolder.time = (TextView) itemView.findViewById(R.id.activity_item_time);
            activityViewHolder.title = (TextView) itemView.findViewById(R.id.activity_item_title);
            activityViewHolder.status = (ImageView) itemView.findViewById(R.id.activity_item_status);
            activityViewHolder.detail = (TextView) itemView.findViewById(R.id.activity_item_detail);
            activityViewHolder.image = (ImageView) itemView.findViewById(R.id.activity_item_image);
            activityViewHolder.activityCheckBox = (CheckBox) itemView.findViewById(R.id.activityCheckBox);
            itemView.setTag(activityViewHolder);
        } else {
            activityViewHolder = (ActivityHolder) itemView.getTag();
        }
        activityViewHolder.activityCheckBox.setVisibility(View.VISIBLE);
        activityViewHolder.activityCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!activityViewHolder.activityCheckBox.isChecked()) {
                    ids.remove(model);
                } else {
                    ids.add(model);
                }
                if (onCheckListener!=null)
                    onCheckListener.onCheck();
            }
        });
        activityViewHolder.title.setText(model.getName());
        activityViewHolder.time.setText("报名截止时间：" + DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getApply_end_time()));
        ImageLoader.getInstance(mContext, R.drawable.default_error_long).displayImage(model.getCover(), activityViewHolder.image);
        return itemView;
    }

    /**
     * 报告的渲染
     */
    private View reportRender(int position, View itemView) {
        final ReportHolder reportHolder;
        final ReportModel model = (ReportModel) getItem(position);
        if (null == itemView) {
            reportHolder = new ReportHolder();
            itemView = LayoutInflater.from(mContext).inflate(R.layout.view_report_item, null);
            reportHolder.time = (TextView) itemView.findViewById(R.id.report_item_time);
            reportHolder.title = (TextView) itemView.findViewById(R.id.report_item_title);
            reportHolder.content = (TextView) itemView.findViewById(R.id.report_item_content);
            reportHolder.image = (ImageView) itemView.findViewById(R.id.report_item_image);
            reportHolder.reportCheckBox = (CheckBox) itemView.findViewById(R.id.reportCheckBox);
            itemView.setTag(reportHolder);
        } else {
            reportHolder = (ReportHolder) itemView.getTag();
        }
        reportHolder.reportCheckBox.setVisibility(View.VISIBLE);
        reportHolder.reportCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!reportHolder.reportCheckBox.isChecked()) {
                    ids.remove(model);
                } else {
                    ids.add(model);
                }
                if (onCheckListener!=null)
                    onCheckListener.onCheck();
            }
        });
        reportHolder.title.setText(model.getName());
        ImageLoader.getInstance(mContext, R.drawable.default_error).displayImage(model.getCover(), reportHolder.image);
        reportHolder.content.setText(model.getSummary());
        reportHolder.time.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_5, model.getCtime()));
        return itemView;
    }

    /**
     * 权益的渲染
     */
    private View rightRender(int position, View itemView) {
       final RightHolder rightHolder;
       final RightModel model = (RightModel) getItem(position);
        if (null == itemView) {
            rightHolder = new RightHolder();

            itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_right_list_layout, null);
            rightHolder.rootLayout = (RelativeLayout) itemView.findViewById(R.id.rootLayoutsssss);
            rightHolder.content = (TextView) itemView.findViewById(R.id.right_item_content);
            rightHolder.iamge = (ImageView) itemView.findViewById(R.id.right_item_image_new);
            rightHolder.title = (TextView) itemView.findViewById(R.id.right_item_title);
            rightHolder.rightCheckbox = (CheckBox) itemView.findViewById(R.id.rightCheckBox);
            rightHolder.levelTextViews = (TextView) itemView.findViewById(R.id.levelTextViews);
            itemView.setTag(rightHolder);
        } else {
            rightHolder = (RightHolder) itemView.getTag();
        }
        rightHolder.rightCheckbox.setVisibility(View.VISIBLE);
        rightHolder.rightCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rightHolder.rightCheckbox.isChecked()) {
                    ids.remove(model);
                } else {
                    ids.add(model);
                }
               if (onCheckListener!=null)
                   onCheckListener.onCheck();
            }
        });

        rightHolder.title.setText(model.getName());
        rightHolder.content.setText(model.getSpend_score()+"");
        try {
            if (model.getLevel() == 5) {
                if (model.getLevelName() == null) {
                    rightHolder.levelTextViews.setText("黑金卡");
                } else {
                    rightHolder.levelTextViews.setText(model.getLevelName());
                }
                rightHolder.levelTextViews.setTextColor(mContext.getResources().getColor(R.color.black));
                rightHolder.levelTextViews.setBackground(mContext.getResources().getDrawable(R.drawable.adapter_right_black_card_bg));
            } else if (model.getLevel() == 4) {
                if (model.getLevelName() == null) {
                    rightHolder.levelTextViews.setText("金卡");
                } else {
                    rightHolder.levelTextViews.setText(model.getLevelName());
                }
                rightHolder.levelTextViews.setTextColor(mContext.getResources().getColor(R.color.goldColor));
                rightHolder.levelTextViews.setBackground(mContext.getResources().getDrawable(R.drawable.adapter_right_glod_card_bg));
            } else if (model.getLevel() == 3) {
                if (model.getLevelName() == null) {
                    rightHolder.levelTextViews.setText("银卡");
                } else {
                    rightHolder.levelTextViews.setText(model.getLevelName());
                }
                rightHolder.levelTextViews.setTextColor(mContext.getResources().getColor(R.color.silverColor));
                rightHolder.levelTextViews.setBackground(mContext.getResources().getDrawable(R.drawable.adapter_right_silver_card_bg));
            } else if (model.getLevel() == 2) {
                if (model.getLevelName() == null) {
                    rightHolder.levelTextViews.setText("准会员");
                } else {
                    rightHolder.levelTextViews.setText(model.getLevelName());
                }
                rightHolder.levelTextViews.setTextColor(mContext.getResources().getColor(R.color.silverColor));
                rightHolder.levelTextViews.setBackground(mContext.getResources().getDrawable(R.drawable.adapter_right_silver_card_bg));
            } else if (model.getLevel() == 1) {
                if (model.getLevelName() == null) {
                    rightHolder.levelTextViews.setText("投资人");
                } else {
                    rightHolder.levelTextViews.setText(model.getLevelName());
                }
                rightHolder.levelTextViews.setTextColor(mContext.getResources().getColor(R.color.silverColor));
                rightHolder.levelTextViews.setBackground(mContext.getResources().getDrawable(R.drawable.adapter_right_silver_card_bg));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        ImageLoader.getInstance(mContext, R.drawable.default_error).displayImage(model.getCover(), rightHolder.iamge);
        return itemView;
    }

    @Override
    public int getItemViewType(int position) {
        int type = MyCollectType.COLLECT_TYPE_PRODUCT.ordinal();
        try {
            Object obj = list.get(position);
            if (obj instanceof CollectProductModel) {
                type = MyCollectType.COLLECT_TYPE_PRODUCT.ordinal();
            } else if (obj instanceof CollectActivityModel) {
                type = MyCollectType.COLLECT_TYPE_ACTIVITY.ordinal();
            } else if (obj instanceof RightModel) {
                type = MyCollectType.COLLECT_TYPE_RIGHT.ordinal();
            } else if (obj instanceof ReportModel) {
                type = MyCollectType.COLLECT_TYPE_REPORT.ordinal();
            }
        } catch (Exception e) {
        }

        return type;
    }

    @Override
    public int getViewTypeCount() {
        return MyCollectType.values().length;
    }

    public void setList(List<Object> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class ProductHolder {
        ProgressBar interestProgress;
        CheckBox productCheckBox;
        ImageView productStatus;
        TextView titleNameText, interestNumber, timeText, distributionMode, endTimeText;
    }

    class ActivityHolder {
        TextView title, time, detail;
        ImageView image, status;
        CheckBox activityCheckBox;
    }

    class ReportHolder {
        TextView title, content, time;
        ImageView image;
        CheckBox reportCheckBox;
    }

    class RightHolder {
        ImageView iamge;
        TextView title, content,levelTextViews;
        CheckBox rightCheckbox;
        RelativeLayout rootLayout;
    }
}
