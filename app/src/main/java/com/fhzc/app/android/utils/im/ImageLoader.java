package com.fhzc.app.android.utils.im;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.base.platform.utils.java.StringTools;
import com.bumptech.glide.Glide;
import com.fhzc.app.android.R;
import com.fhzc.app.android.configer.UrlRes;

/**
 * 图片缓存类
 *
 * @author linxi
 *         异步加载图片
 */
public class ImageLoader {


    Context context;

    int stub_id;
    static ImageLoader instance;

    public static ImageLoader getInstance(Context context, int iconDefaultResId) {
        if (instance == null) {
            return new ImageLoader(context, iconDefaultResId);
        }
        return instance;
    }
    public static ImageLoader getInstance(Context context) {
        if (instance == null) {
            return new ImageLoader(context, 0);
        }
        return instance;
    }
    public ImageLoader(Context context) {
        this(context, 0);
    }

    public ImageLoader(Context context, int iconDefaultResId) {
        // 默认图片
        this.context = context;
        stub_id = iconDefaultResId;


    }

    public void displayImage(String url, ImageView imageView) {
        if (StringTools.isNullOrEmpty(url))
            return;
        Glide.with(context)
                .load(getUrl(url))
                .centerCrop()
                .placeholder(stub_id)
                .into(imageView);

    }
    public void displayHtmlImage(String url, ImageView imageView) {
        if (StringTools.isNullOrEmpty(url))
            return;
        Glide.with(context)
                .load(url)
                .centerCrop()
                .placeholder(stub_id)
                .into(imageView);

    }
    private String getUrl(String url) {
        if (url.startsWith("http")) {
            return url;
        }
        return UrlRes.getInstance().getPictureUrl() + url;
    }


    public void loadLocalImage(String path, ImageView imageView) {

        if (TextUtils.isEmpty(path)
                || null == imageView) {
            return;
        }

        Glide.with(context)
                .load(path)
                .centerCrop()
                .placeholder(R.drawable.default_error)
                .into(imageView);
    }


}


