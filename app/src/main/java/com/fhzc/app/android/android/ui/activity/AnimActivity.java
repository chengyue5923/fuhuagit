package com.fhzc.app.android.android.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fhzc.app.android.R;
import com.fhzc.app.android.utils.android.IntentTools;

import java.io.InputStream;

/**
 * Created by apple on 16/8/4.
 */
public class AnimActivity extends Activity {

    private FrameLayout logoRecLayout, logoBallLayout;
    private ImageView logoRec, logoBall;
    private LinearLayout title, slogan, sloganEnglish;
    private RelativeLayout rootView;
    private boolean isLogin;
    private ImageView animBack;
    Bitmap btp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = getLayoutInflater().inflate(R.layout.activity_anim, null);
        setContentView(view);
//        view.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_bg));

        isLogin = getIntent().getBooleanExtra("isLogin", false);

        animBack = (ImageView) findViewById(R.id.animBack);
        logoRecLayout = (FrameLayout) findViewById(R.id.loadinganim_logoreclayout);//logo framelayout
        logoBallLayout = (FrameLayout) findViewById(R.id.loadinganim_logoballlayout);//qiu fragmetnlayout
        logoRec = (ImageView) findViewById(R.id.loadinganim_logorec);//kuang
        logoBall = (ImageView) findViewById(R.id.loadinganim_logoball);//qiu
        title = (LinearLayout) findViewById(R.id.ll_title);//title
        slogan = (LinearLayout) findViewById(R.id.ll_slogan);//zhongwen
        sloganEnglish = (LinearLayout) findViewById(R.id.ll_sloganenglish);//yingwen
        rootView = (RelativeLayout) findViewById(R.id.rl_root);
        AssetManager asm = getAssets();
        try {
            InputStream is = asm.open("loadinganim_bg.jpg");
            BitmapFactory.Options opt = new BitmapFactory.Options();
            opt.inPreferredConfig = Bitmap.Config.RGB_565;
            opt.inPurgeable = true;
            opt.inInputShareable = true;
            btp = BitmapFactory.decodeStream(is, null, opt);
            animBack.setImageBitmap(btp);
        } catch (Exception e) {

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        float titleCurTra = title.getTranslationY();
        float sloganCurTra = title.getTranslationY();
        float sloganEngCurTra = title.getTranslationY();
        float logoBallTranslationY = this.logoBall.getTranslationY();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;


        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        getWindowManager().getDefaultDisplay().getRealSize(point);

        ObjectAnimator titleAnimDrop = ObjectAnimator.ofFloat(title, "translationY", -1500f, titleCurTra);
        titleAnimDrop.setDuration(1300);
        ObjectAnimator sloganAnimTrans = ObjectAnimator.ofFloat(slogan, "translationX", -1200f, sloganCurTra);
        sloganAnimTrans.setDuration(1300);
        ObjectAnimator sloganEnglishAnimTrans = ObjectAnimator.ofFloat(sloganEnglish, "translationX", 1200f, sloganEngCurTra);
        sloganEnglishAnimTrans.setDuration(1300);
        ObjectAnimator logoRecLayoutAnimAlpha1 = ObjectAnimator.ofFloat(logoRecLayout, "alpha", 0f, 1.5f);
        logoRecLayoutAnimAlpha1.setDuration(1000);
        ObjectAnimator logoBallLayoutAnimAlph1 = ObjectAnimator.ofFloat(logoBallLayout, "alpha", 0f, 1.5f);
        logoBallLayoutAnimAlph1.setDuration(1000);

        ObjectAnimator logoRecAnimAlph1 = ObjectAnimator.ofFloat(logoRec, "alpha", 1f, 0f);
        logoRecAnimAlph1.setDuration(200);

        float destination = height - dip2px(this, 170);
        Log.e("test", "  from=" + logoBallTranslationY + " des = " + destination);
        ObjectAnimator logoBallLayoutAnimTrans = ObjectAnimator.ofFloat(logoBallLayout, "translationY", logoBallTranslationY, destination);
        logoBallLayoutAnimTrans.setDuration(3000);
        ObjectAnimator logoBallLayoutAnimScalY = ObjectAnimator.ofFloat(logoBallLayout, "scaleY", 1f, 0.5f);
        logoBallLayoutAnimScalY.setDuration(3000);
        ObjectAnimator logoBallLayoutAnimScalX = ObjectAnimator.ofFloat(logoBallLayout, "scaleX", 1f, 0.5f);
        logoBallLayoutAnimScalX.setDuration(3000);
        ObjectAnimator animatorRoot = ObjectAnimator.ofFloat(rootView, "alpha", 1f, 0f);
        animatorRoot.setDuration(4000);

        ObjectAnimator logoBallLayoutAnimScalX2 = ObjectAnimator.ofFloat(logoBallLayout, "scaleX", 1f, 1.5f);
        logoBallLayoutAnimScalX2.setDuration(500);
        ObjectAnimator logoBallLayoutAnimScalY2 = ObjectAnimator.ofFloat(logoBallLayout, "scaleY", 1f, 1.5f);
        logoBallLayoutAnimScalY2.setDuration(500);
        ObjectAnimator logoBallLayoutAnimAlpah2 = ObjectAnimator.ofFloat(logoBallLayout, "alpha", 1, 0);
        logoBallLayoutAnimAlpah2.setDuration(500);

        titleAnimDrop.start();
        sloganAnimTrans.start();
        sloganEnglishAnimTrans.start();
        logoRecLayoutAnimAlpha1.setStartDelay(1300);//logo framelayout
        logoBallLayoutAnimAlph1.setStartDelay(1300);//qiu framelayout
        logoRecLayoutAnimAlpha1.start();
        logoBallLayoutAnimAlph1.start();


        logoBallLayoutAnimAlph1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                rootView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        IntentTools.startLoginActivity(AnimActivity.this);
                        AnimActivity.this.finish();
                    }
                }, 500);
            }
        });


//        if (isLogin) {
//            logoBallLayoutAnimAlph1.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                    rootView.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            AnimActivity.this.finish();
//                        }
//                    }, 500);
//                }
//            });
//        } else {
//            logoRecAnimAlph1.setStartDelay(2300);//kuang
//            logoRecAnimAlph1.start();
//
//            logoBallLayoutAnimTrans.setStartDelay(2500);//qiu framelayout
//            logoBallLayoutAnimScalY.setStartDelay(2500);
//            logoBallLayoutAnimScalX.setStartDelay(2500);
//            animatorRoot.setStartDelay(2500);//bg anim
//            logoBallLayoutAnimTrans.start();
//            logoBallLayoutAnimScalY.start();
//            logoBallLayoutAnimScalX.start();
//            animatorRoot.start();
//
//            logoBallLayoutAnimScalX2.setStartDelay(5500);
//            logoBallLayoutAnimScalY2.setStartDelay(5500);
//            logoBallLayoutAnimAlpah2.setStartDelay(5500);
//            logoBallLayoutAnimScalX2.start();
//            logoBallLayoutAnimScalY2.start();
//            logoBallLayoutAnimAlpah2.start();
//
//            animatorRoot.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                    finish();
//                }
//            });
//        }
    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (btp != null && !btp.isRecycled()) {
            btp.recycle(); //回收图片所占的内存
            System.gc();  //提醒系统及时回收
        }
    }
}
