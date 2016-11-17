package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.dialog.ActivityDetailSignUpDialog;
import com.fhzc.app.android.android.ui.view.dialog.ActivitySignUpCancelDialog;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.UrlRes;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.ActivityController;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.event.CancelCollectEvent;
import com.fhzc.app.android.models.ActivityModel;
import com.fhzc.app.android.models.CollectActivityModel;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.ImageLoader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fw on 16/10/18.
 */

public class ActivityDetailActivity extends BaseActivity implements View.OnClickListener, CommonToolBar.ClickRightListener {


    @Bind(R.id.signUpActivityTextView)
    TextView signUpActivityTextView;
    @Bind(R.id.activityImageView)
    ImageView activityImageView;

    @Bind(R.id.activityTextView)
    TextView activityTextView;
    @Bind(R.id.activityDetailToolbar)
    CommonToolBar activityDetailToolbar;
    @Bind(R.id.time)
    TextView time;
    @Bind(R.id.signUpTimeText)
    TextView signUpTimeText;
    @Bind(R.id.activitytime)
    TextView activitytime;
    @Bind(R.id.activityTimeText)
    TextView activityTimeText;
    @Bind(R.id.avtivityPlace)
    TextView avtivityPlace;
    @Bind(R.id.activityPlaceText)
    TextView activityPlaceText;
    @Bind(R.id.activityContextText)
    TextView activityContextText;
    @Bind(R.id.tv_connectplanner)
    TextView signUpForOtherActivityTextView;

    @Bind(R.id.shareActivityTextView)
    TextView shareActivityTextView;
    @Bind(R.id.singupLayout)
    LinearLayout singupLayout;
    @Bind(R.id.shouurl)
    WebView shouurl;
    @Bind(R.id.ll_cancle_signup)
    LinearLayout cancleSignUp;
    @Bind(R.id.tv_readMore)
    TextView tvReadMore;
    @Bind(R.id.activitySummaryText)
    TextView tvSummary;

//    private Handler mHandler;
    private Thread t;

    private int activityId;
    private int position;
    private ActivityModel model;
    private boolean unfold;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        if (!UserPreference.isCustomer()) {
            signUpActivityTextView.setText("分享客户");
        }

//        mHandler=new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                if (msg.what == 0x105) {
//                    activityContextText.setText((CharSequence) msg.obj);
//                }
//                return false;
//            }
//        });

        activityDetailToolbar.setClickRightListener(this);
        WebSettings wv_setttig = shouurl.getSettings();
        wv_setttig.setDefaultTextEncodingName("UTF-8");
        shouurl.setWebViewClient(webviewClient);
        wv_setttig.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
//        wv_setttig.setUseWideViewPort(true);
//        wv_setttig.setBuiltInZoomControls(false);
//        wv_setttig.setSupportZoom(false);
//        wv_setttig.setDisplayZoomControls(false);
        wv_setttig.setJavaScriptEnabled(true);
    }
    private WebViewClient webviewClient = new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    @Override
    protected void initEvent() {
        signUpActivityTextView.setOnClickListener(this);
        shareActivityTextView.setOnClickListener(this);
        signUpForOtherActivityTextView.setOnClickListener(this);
        tvReadMore.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        activityId = getIntent().getIntExtra("activityId", -1);
        position = getIntent().getIntExtra("position", -1);
        if (activityId == -1)
            return;
//        ActivityController.getInstance().focusStatus(this, activityId);
        ActivityController.getInstance().activityDetail(this, activityId);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_activitydetail;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signUpActivityTextView:
                    if (UserPreference.isCustomer()) {
                        if(model.getStatus()==2){
                            ToastTool.show("报名结束");
                        }else if(model.getStatus()==3){
                            ToastTool.show("活动结束");
//                        }else if(cancleSignUp.getVisibility()==View.VISIBLE){
//                            ToastTool.show("您已经报名");
                        }else{
                            PopUpSignUpDialog();
                        }
                    }
                break;
            case R.id.tv_connectplanner:
                IntentTools.startChatList(this);

                break;

            case R.id.shareActivityTextView:

                IntentTools.startClientList(ActivityDetailActivity.this, "activity", activityId);
                break;
            case R.id.ll_cancle_signup:
                AccountCancelPopUpDialog();
                break;
            case R.id.tv_readMore:
                if(unfold){
                    tvSummary.setMaxLines(3);
                    unfold = false;
                }else{
                    tvSummary.setMaxLines(100);
                    unfold = true;
                }
                break;

        }
    }

    private ActivityDetailSignUpDialog signUpDialog;
    public void PopUpSignUpDialog(){
        signUpDialog = new ActivityDetailSignUpDialog(this,model,false);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        signUpDialog.setDialogListener(new ActivityDetailSignUpDialog.DialogListener() {
            @Override
            public void onclickConfirm(int personnumber) {
                dialogFlag = true;
                ActivityController.getInstance().joinActivity(ActivityDetailActivity.this, activityId, personnumber);
            }

            @Override
            public void onclickConfirm(String name, String phone, String number, String code) {
                dialogFlag = false;
                ActivityController.getInstance().joinActivityforOther(ActivityDetailActivity.this, activityId, name, phone, number, code);

            }

            @Override
            public void onclickOtner() {
                signUpDialog.dismiss();
//                AccountPopUpDialog();
            }
        });
        signUpDialog.show(fragmentTransaction, "ActivityDetailSignUpDialog");

    }


    boolean dialogFlag;



    ActivitySignUpCancelDialog PopUpOtherCancelDialog;

    public void AccountCancelPopUpDialog() {
        PopUpOtherCancelDialog = new ActivitySignUpCancelDialog(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PopUpOtherCancelDialog.setDialogListener(new ActivitySignUpCancelDialog.DialogListener() {
            @Override
            public void onclickConfirm() {
                ActivityController.getInstance().cancelJoinActivity(ActivityDetailActivity.this, model.getApplyId());
            }
        });
        PopUpOtherCancelDialog.show(fragmentTransaction, "ActivityAccountPopUpDialog");
    }


    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.activityDetail.getType()) {
            model = (ActivityModel) result;
            if (UserPreference.isCustomer()) {
                singupLayout.setVisibility(View.VISIBLE);
                if (model.getActivityResult() != null) {
                    if (model.getActivityResult().equals("0")) {

                    } else if (!StringTools.isNullOrEmpty(model.getActivityResult())&&Integer.parseInt(model.getActivityResult()) > 0) {
                        cancleSignUp.setVisibility(View.VISIBLE);
                        cancleSignUp.setOnClickListener(this);
                    }

                }
            } else {
                singupLayout.setVisibility(View.GONE);
                shareActivityTextView.setVisibility(View.VISIBLE);
                shareActivityTextView.setOnClickListener(this);
            }

            ImageLoader.getInstance(this, R.drawable.default_error_long).displayImage(model.getCover(), activityImageView);
//            if (model.getCover() != null) {
            Object status = model.getFocusStatus();
            if (StringTools.isNullOrEmpty(status.toString()) || status.toString().equals("0.0")) {
                activityDetailToolbar.setCollect(false);
            } else {
                activityDetailToolbar.setCollect(true);
            }
            activityTextView.setText(model.getName());
            signUpTimeText.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getBeginTime()) + "至" + DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getEndTime()));
            activityTimeText.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getApplyBeginTime()) + "至" + DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, model.getApplyEndTime()));
            activityPlaceText.setText(model.getAddress());
            tvSummary.setText(model.getSummary());

            if(model.getContent().equals("")){
//                activityContextText.setVisibility(View.GONE);
//                shouurl.setVisibility(View.VISIBLE);
                shouurl.loadUrl(model.getUrl());
            }else{
//                activityContextText.setVisibility(View.VISIBLE);
//                shouurl.setVisibility(View.GONE);
//                try{
//                    t=new Thread(new Runnable() {
//                        Message msg = Message.obtain();
//                        @Override
//                        public void run() {
//                            Html.ImageGetter imageGetter=new Html.ImageGetter() {
//                                @Override
//                                public Drawable getDrawable(String source) {
//                                    WindowManager wm = ActivityDetailActivity.this.getWindowManager();
//                                    int width = wm.getDefaultDisplay().getWidth();
//                                    int height = wm.getDefaultDisplay().getHeight();
//                                    Drawable drawable =getImageFromNetwork(source);
//                                    drawable.setBounds(0,0,width,drawable.getIntrinsicHeight()*4);
//                                    return drawable;
//                                }
//                            };
//                            CharSequence charSequence = Html.fromHtml(model.getContent(), imageGetter, null);
//                            msg.what = 0x105;
//                            msg.obj = charSequence;
//                            mHandler.sendMessage(msg);
//                        }
//                    });
//                    t.start();
//                }catch (Exception e){
//                    Logger.e("e.getmessasge"+e.getMessage());
//                }

                WebSettings settings = shouurl.getSettings();
                settings.setJavaScriptEnabled(true);
                String htmlContent = model.getContent();
                htmlContent = htmlContent+ "<script type=\"text/javascript\">"+
                        "var imgs = document.getElementsByTagName('img');" + // 找到table标签
                        "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                        "imgs[i].style.width = '100%';" +  // 宽度改为100%
                        "imgs[i].style.height = 'auto';" +
                        "}" +
                        "</script>";
//                htmlContent = "<!DOCTYPE html> <html><head><style> *{max-width:100%;height:auto}</style></head><body>"+htmlContent+"</body></html>";
                shouurl.loadDataWithBaseURL(null,htmlContent,"text/html","utf-8",null);

            }

        }else if (flag == HttpConfig.cancelJoinActivity.getType()) {
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    PopUpOtherCancelDialog.dismiss();
                    ToastTool.show("取消报名成功");
                    cancleSignUp.setVisibility(View.GONE);
                } else {
                    ToastTool.show(object.getString("message"));
                }

            } catch (Exception e) {
                ToastTool.show("取消报名失败");
            }
        }else if (flag == HttpConfig.focusStatus.getType()) {
            int status = (int) result;
            if (status == 0) {
                activityDetailToolbar.setCollect(false);
            } else {
                activityDetailToolbar.setCollect(true);
            }
        }else if (flag == HttpConfig.joinActivity.getType()) {
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    if(object.getJSONObject("map").getString("type").equals("invite")){
                        ToastTool.show("为他人报名成功");
                        signUpDialog.dismiss();
                    }else{
                        signUpDialog.dismiss();
                        ToastTool.show("为自己报名成功");
                        cancleSignUp.setVisibility(View.VISIBLE);
                        cancleSignUp.setOnClickListener(this);
                    }

                } else {
                    ToastTool.show(object.getString("message"));
                }
            } catch (Exception e) {
                ToastTool.show("报名失败");
            }

        }else if (flag == HttpConfig.focus.getType()) {
            if (activityDetailToolbar.isCollect()) {
                activityDetailToolbar.setCollect(false);
                EventManager.getInstance().post(new CancelCollectEvent(Constant.COLLECTTYPE_ACTIVITY, position, false, null));
                ToastTool.show("取消关注");
            } else {
                activityDetailToolbar.setCollect(true);
                EventManager.getInstance().post(new CancelCollectEvent(Constant.COLLECTTYPE_ACTIVITY, position, true, CollectActivityModel.getModel(model)));
                ToastTool.show("已关注");
            }
        }
    }
    Drawable getImageFromNetwork(String imageUrl) {
        URL myFileUrl = null;
        Drawable drawable = null;
        try {
            if(imageUrl.contains("/opt/fhzc/system")){
                myFileUrl = new URL(UrlRes.getInstance().getPictureUrl()+imageUrl);
            }else{
                myFileUrl = new URL(imageUrl);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) myFileUrl
                    .openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            drawable = Drawable.createFromStream(is, null);
            if(is!=null){
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;
    }

    public class MyImageGetter implements Html.ImageGetter {

        private Context context;
        private TextView tv;

        public MyImageGetter(Context context, TextView tv) {
            this.context = context;
            this.tv = tv;
        }

        @Override
        public Drawable getDrawable(String source) {
            // TODO Auto-generated method stub
            // 将source进行MD5加密并保存至本地
            String imageName = source;
            String sdcardPath = Environment.getExternalStorageDirectory()
                    .toString(); // 获取SDCARD的路径
            // 获取图片后缀名
            String[] ss = source.split("\\.");
            String ext = ss[ss.length - 1];

            // 最终图片保持的地址
            String savePath = sdcardPath + "/" + context.getPackageName() + "/"
                    + imageName + "." + ext;

            File file = new File(savePath);
            if (file.exists()) {
                WindowManager wm = ActivityDetailActivity.this.getWindowManager();

                int width = wm.getDefaultDisplay().getWidth();
                int height = wm.getDefaultDisplay().getHeight();
                // 如果文件已经存在，直接返回
                Drawable drawable = Drawable.createFromPath(savePath);
                drawable.setBounds(0, 0, width,
                        drawable.getIntrinsicHeight()*5);
                return drawable;
            }

            // 不存在文件时返回默认图片，并异步加载网络图片
            Resources res = context.getResources();
            ActivityDetailActivity.MyImageGetter.URLDrawable drawable = new ActivityDetailActivity.MyImageGetter.URLDrawable(
                    res.getDrawable(R.drawable.default_check));
            new ActivityDetailActivity.MyImageGetter.ImageAsync(drawable).execute(savePath, source);
            return drawable;

        }

        private class ImageAsync extends AsyncTask<String, Integer, Drawable> {

            private ActivityDetailActivity.MyImageGetter.URLDrawable drawable;

            public ImageAsync(ActivityDetailActivity.MyImageGetter.URLDrawable drawable) {
                this.drawable = drawable;
            }

            @Override
            protected Drawable doInBackground(String... params) {
                // TODO Auto-generated method stub
                String savePath = params[0];
                String url = params[1];
                url= UrlRes.getInstance().getPictureUrl()+url;
                InputStream in = null;
                try {
                    // 获取网络图片
                    HttpGet http = new HttpGet(url);
                    HttpClient client = new DefaultHttpClient();
                    HttpResponse response = (HttpResponse) client.execute(http);
                    BufferedHttpEntity bufferedHttpEntity = new BufferedHttpEntity(
                            response.getEntity());
                    in = bufferedHttpEntity.getContent();

                } catch (Exception e) {
                    try {
                        if (in != null)
                            in.close();
                    } catch (Exception e2) {
                        // TODO: handle exception
                    }
                }

                if (in == null)
                    return drawable;

                try {
                    File file = new File(savePath);
                    String basePath = file.getParent();
                    File basePathFile = new File(basePath);
                    if (!basePathFile.exists()) {
                        basePathFile.mkdirs();
                    }
                    file.createNewFile();
                    FileOutputStream fileout = new FileOutputStream(file);
                    byte[] buffer = new byte[4 * 1024];
                    while (in.read(buffer) != -1) {
                        fileout.write(buffer);
                    }
                    fileout.flush();

                    Drawable mDrawable = Drawable.createFromPath(savePath);
                    return mDrawable;
                } catch (Exception e) {
                    // TODO: handle exception
                }
                return drawable;
            }

            @Override
            protected void onPostExecute(Drawable result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                if (result != null) {
                    drawable.setDrawable(result);
                    tv.setText(tv.getText()); // 通过这里的重新设置 TextView 的文字来更新UI
                }
            }

        }

        public class URLDrawable extends BitmapDrawable {

            private Drawable drawable;

            public URLDrawable(Drawable defaultDraw) {
                setDrawable(defaultDraw);
            }

            private void setDrawable(Drawable nDrawable) {
                drawable = nDrawable;
                WindowManager wm = ActivityDetailActivity.this.getWindowManager();

                int width = wm.getDefaultDisplay().getWidth();
                int height = wm.getDefaultDisplay().getHeight();
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight());
                setBounds(0, 0, width,
                        drawable.getIntrinsicHeight()*5);
            }

            @Override
            public void draw(Canvas canvas) {
                // TODO Auto-generated method stub
                drawable.draw(canvas);
            }

        }
    }

    @Override
    public void OnClickRight(View view) {
        if (activityDetailToolbar.isCollect()) {
            ActivityController.getInstance().cancelFocusRight(this, activityId);
        } else {
            ActivityController.getInstance().focusRight(this, activityId);
        }
    }
}
