package com.fhzc.app.android.android.ui.view.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.fhzc.app.android.android.base.FuhuaApplication;

import java.io.File;


/**
 * Created by lenovo on 2015/8/5.
 */
public class CommonUtils {
    public static String takePhoto(Context context, int requestCode) {
        Intent intent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE, null);
        String filePath = FuhuaApplication.cachePath
                + File.separator + String.valueOf(System.currentTimeMillis()) + "camera" + ".png";
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri
                .fromFile(new File(filePath)));
        ((Activity) context).startActivityForResult(intent, requestCode);
        return filePath;
    }

    public static void albumPhoto(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }
}
