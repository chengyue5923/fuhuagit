package com.fhzc.app.android.android.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.WindowManager;

import com.fhzc.app.android.utils.im.CommonUtil;

/**
 * Created by fw on 16/11/5.
 */

public class HtmlImgGetter implements Html.ImageGetter {
    protected Activity activity;


    public HtmlImgGetter(Activity activity){
        this.activity = activity;

    }



    @Override
    public Drawable getDrawable(String source) {
        WindowManager wm = activity.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        Drawable drawable = CommonUtil.getImageFromNetwork(source);
        drawable.setBounds(0, 0, width, drawable.getIntrinsicHeight() * 4);
        return drawable;
    }
}
