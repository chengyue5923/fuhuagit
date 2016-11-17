package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fw on 16/10/27.
 */

public class KnowAboutUsActivity extends BaseActivity {


    @Bind(R.id.commonToolBar)
    CommonToolBar toolBar;
    @Bind(R.id.webview)
    WebView webview;
    private String url,title;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.loadUrl(url);
        toolBar.setTitle(title);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_knowaboutus;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }


    @OnClick({R.id.commonToolBar, R.id.webview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.commonToolBar:
                break;
            case R.id.webview:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()){
            webview.goBack();
            return true;
        }else{
            return super.onKeyDown(keyCode, event);
        }
    }
}
