package com.fhzc.app.android.android.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.DJXWebView;
import com.fhzc.app.android.utils.im.CommonUtil;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yanbo on 2016/8/3.
 */
public class RiskAssessmentActivity extends BaseActivity {

    @Bind(R.id.systemMessageToolBar)
    CommonToolBar systemMessageToolBar;
    @Bind(R.id.webviewwe)
    DJXWebView webviewwe;
    @Bind(R.id.webDetailLoading)
    ProgressBar webDetailLoading;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        WebSettings webSettings = webviewwe.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        initWebView(webviewwe);
        webviewwe.loadUrl("http://dev-apptest.foriseassets.com:8080/h5/riskEvaluation.html");
//        webviewwe.loadUrl("http://www.baidu.com");

    }

    private void initWebView(DJXWebView mWebView) {

        webDetailLoading.setVisibility(View.INVISIBLE);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        mWebView.getSettings().setAppCachePath(appCachePath);
        mWebView.getSettings().setAllowFileAccess(true);

        mWebView.getSettings().setAppCacheEnabled(true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            mWebView.getSettings().setDatabasePath(CommonUtil.getWebStorageDirectory(this));
        }


        mWebView.setWebChromeClient(new DJXWebView.DJXWebChromeClient(mWebView) {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                webDetailLoading.setSecondaryProgress(newProgress);
                if (newProgress == 100) {
                    webDetailLoading.setVisibility(View.INVISIBLE);
                } else {
                    webDetailLoading.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                webDetailLoading.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                webDetailLoading.setVisibility(View.VISIBLE);

                view.loadUrl("javascript:if(window.MCSBridge == undefined) {window.MCSBridge={};}");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webDetailLoading.setVisibility(View.GONE);
            }
        });

    }

    @Override
    protected void initEvent() {
    }

    @Override
    protected void initData() {
//        webview.loadUrl("http://dev-apptest.foriseassets.com:8080/h5/riskEvaluation.html");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_risk_assessment;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

//        if(flag== HttpConfig.RiskTest.getType()){
//            try {
//                JSONObject object = new JSONObject(o.toString());
//                if (object.getInt("code")== 200) {
//                    ToastTool.show(object.getString("message"));
//                }
//            } catch (Exception e) {
//                Logger.e(e.getMessage());
//            }
//        }
    }

}
