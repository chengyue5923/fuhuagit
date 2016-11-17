package com.fhzc.app.android.utils;

import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.activity.HotProductsActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by lenovo on 2016/10/25.
 */
public class WebViewActivity extends BaseActivity implements ScrollViewExtend.ObservableScrollViewCallbacks {
    @Bind(R.id.Toolbar)
    CommonToolBar Toolbar;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.backFragmentlayout)
    RelativeLayout backFragmentlayout;
    @Bind(R.id.rootLayout)
    RelativeLayout rootLayout;
    @Bind(R.id.scrollLayout)
    ScrollViewExtend scrollLayout;
    @Bind(R.id.imageLayout)
    ImageView imageLayout;
    @Bind(R.id.titleTextView)
    TextView titleTextView;
    @Bind(R.id.ToolbarLayout)
    LinearLayout ToolbarLayout;
    @Bind(R.id.backImageView)
    ImageView backImageView;
    private boolean isProduct = false;
    private int mParallaxImageHeight;
    private boolean ifchangeTitle=false;


    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        imageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webview.canGoBack()) {
                    webview.goBack();//返回上一页面
                } else {
                    if (webview != null) {
                        rootLayout.removeView(webview);
                        webview.destroy();
                        webview = null;
                    }
                    finish();
                }
            }
        });
        backFragmentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webview.canGoBack()) {
                    webview.goBack();//返回上一页面
                } else {
                    if (webview != null) {
                        rootLayout.removeView(webview);
                        webview.destroy();
                        webview = null;
                    }
                    finish();
                }
            }
        });
        scrollLayout.setCallbacks(this);
        if(webview.canGoBack()){
            ToolbarLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        if (webview != null) {
            webview.onResume();
        }
        webview.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})()");

        super.onResume();
    }

    @Override
    protected void onPause() {
        if (webview != null) {
            webview.onPause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        if (webview != null) {
            rootLayout.removeView(webview);
            webview.destroy();
            webview = null;
        }
        super.onDestroy();
    }

    @Override
    protected void initData() {
        ToolbarLayout.setVisibility(View.GONE);
        backImageView.setVisibility(View.VISIBLE);
        mParallaxImageHeight = getResources().getDimensionPixelSize(R.dimen.top_image_height);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        initBar();
        ifchangeTitle=false;
        String webUrl = getIntent().getStringExtra("webUrl");
        String title = getIntent().getStringExtra("title");
        if ( webUrl != null && title!= null) {
            if(getIntent().getStringExtra("title").equals("健康管理")){
                ifchangeTitle=true;
            }
            webview.setWebViewClient(new webViewClient());
            webview.loadUrl(webUrl);
            Toolbar.setTitle(title);
            titleTextView.setText(title);
            if (getIntent().getBooleanExtra("flag", false)) {
                isProduct = true;
            }
        }
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview.canGoBack()) {
                webview.goBack();//返回上一页面
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void onScrollChanged(int scrollY) {
        if(ifchangeTitle&&!webview.canGoBack()){
            float alpha = 1 - (float) Math.max(0, mParallaxImageHeight - scrollY) / mParallaxImageHeight;
            if (alpha > 0.2) {
                ToolbarLayout.setVisibility(View.VISIBLE);
                backImageView.setVisibility(View.GONE);
            } else {
                ToolbarLayout.setVisibility(View.GONE);
                backImageView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onScrollBottom() {

    }


    private class webViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            webview.loadUrl("javascript:(function() { var videos = document.getElementsByTagName('video'); for(var i=0;i<videos.length;i++){videos[i].play();}})()");
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // 跳转到另外的activity
            if (isProduct) {
                Intent intent = new Intent(getApplication(),
                        HotProductsActivity.class);
                startActivity(intent);
            }
            ToolbarLayout.setVisibility(View.GONE);
            backImageView.setVisibility(View.VISIBLE);
            initBarActivity(false);
            return super.shouldOverrideUrlLoading(view,url);
        }
    }
}
