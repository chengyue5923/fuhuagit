package com.fhzc.app.android.android.ui.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fhzc.app.android.R;

/**
 * Created by yanbo on 2016/7/21.
 */
public class ProductDateView extends LinearLayout {
    private Context mContext;
    private ImageView tag;
    private TextView time;

    public ProductDateView(Context context) {
        this(context, null);
    }

    public ProductDateView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_product_date, this);
        tag = (ImageView) view.findViewById(R.id.tagTv);
        time = (TextView) view.findViewById(R.id.timeTv);

    }

    public void bindData(int type, String date) {
        if (type == 0) {
            //成立日
            tag.setImageResource(R.drawable.create_date_time_view);
            time.setText(date);
        } else if (type == 1) {
            //到期日
            tag.setImageResource(R.drawable.end_date_view);
            time.setText(date);
        } else if (type == 2) {
            //派息日
            tag.setImageResource(R.drawable.date_taime_view);
            time.setText(date);
        }
    }

}
