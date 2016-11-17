package com.fhzc.app.android.android.ui.activity;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.NewsController;
import com.fhzc.app.android.models.NewsModel;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fw on 16/10/25.
 */

public class NewsDeatilActiity extends BaseActivity {

    @Bind(R.id.webview)
    WebView webView;
    @Bind(R.id.reportTextView)
    TextView text;
    @Bind(R.id.reportImageView)
    ImageView img;
    @Bind(R.id.ScrollViewLayoutsd)
    ScrollView scrollView;

    private int newsId = 0;
//    private Handler mHandler;


    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        newsId = getIntent().getIntExtra("newsId",0);
//        mHandler=new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                if (msg.what == 0x101) {
//                    text.setText((Spanned) msg.obj);
//                }
//                return false;
//            }
//        });
        NewsController.getInstance().getNewsDetail(this,newsId);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_newsdetail;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if(flag == HttpConfig.getNewsDetail.getType()){
            final NewsModel news = (NewsModel) result;
            if(news.getContent().equals("")){
//                webView.setVisibility(View.VISIBLE);
                webView.loadUrl(news.getUrl());
            }else{
//                scrollView.setVisibility(View.VISIBLE);
//                Thread t=new Thread(new Runnable() {
//                    Message msg = Message.obtain();
//                    @Override
//                    public void run() {
//                        Html.ImageGetter imageGetter=new Html.ImageGetter() {
//                            @Override
//                            public Drawable getDrawable(String source) {
//                                WindowManager wm = NewsDeatilActiity.this.getWindowManager();
//                                int width = wm.getDefaultDisplay().getWidth();
//                                int height = wm.getDefaultDisplay().getHeight();
//                                Drawable drawable =FhReportDetailActivity.getImageFromNetwork(source);
//                                drawable.setBounds(0,0,width,drawable.getIntrinsicHeight()*4);
//                                return drawable;
//                            }
//                        };
//                        Spanned spanned= Html.fromHtml(news.getContent(), imageGetter, null);
//                        msg.what = 0x101;
//                        msg.obj = spanned;
//                        mHandler.sendMessage(msg);
//                    }
//                });
//                t.start();

                webView.getSettings().setJavaScriptEnabled(true);
                String htmlContent = news.getContent();
//                Document doc = Jsoup.parse(htmlContent);
//                Elements ele = doc.getElementsByTag("img");
//                if (ele.size() != 0){
//                    for (Element e_Img : ele) {
//                        e_Img.attr("style", "width:100%");
//                    }
//                }
//                String newHtmlContent=doc.toString();

                htmlContent = htmlContent+ "<script type=\"text/javascript\">"+
                        "var imgs = document.getElementsByTagName('img');" + // 找到table标签
                        "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                        "imgs[i].style.width = '100%';" +  // 宽度改为100%
                        "imgs[i].style.height = 'auto';" +
                        "}" +
                        "</script>";
//                htmlContent = "<!DOCTYPE html> <html><head><style> *{max-width:100%;height:auto}</style></head><body>"+htmlContent+"</body></html>";
                webView.loadDataWithBaseURL(null,htmlContent,"text/html","utf-8",null);
            }
        }
    }
}
