package com.fhzc.app.android.android.ui.window;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.fhzc.app.android.R;

/**
 * Created by apple on 16/7/29.
 */
public class DatePickerPopupWin extends PopupWindow {
    private Context mContext;

    private View view;

    public DatePickerPopupWin(Context mContext, View.OnClickListener itemsOnClick) {

        this.view = LayoutInflater.from(mContext).inflate(R.layout.pw_datepicer, null);

    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 设置弹出窗体显示时的动画，从底部向上弹出
//        this.setAnimationStyle(R.style.take_photo_anim);

    }
}
