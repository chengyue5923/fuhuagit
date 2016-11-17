package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.dialog.ProduceCancelReservationDialog;
import com.fhzc.app.android.android.ui.view.dialog.ProduceSuccessReservationDialog;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
import com.fhzc.app.android.configer.UrlRes;
import com.fhzc.app.android.configer.constants.Constant;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.EventManager;
import com.fhzc.app.android.controller.ProductController;
import com.fhzc.app.android.db.UserPreference;
import com.fhzc.app.android.event.CancelCollectEvent;
import com.fhzc.app.android.models.CollectProductModel;
import com.fhzc.app.android.models.ProductModel;
import com.fhzc.app.android.utils.IntentTool;
import com.fhzc.app.android.utils.android.IntentTools;
import com.fhzc.app.android.utils.im.CommonUtil;
import com.fhzc.app.android.utils.im.ImageLoader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @deprecated
 * @param context
 */
public class FhProductDetailActivity extends BaseActivity implements View.OnClickListener, ScrollViewExtend.ObservableScrollViewCallbacks, CommonToolBar.ClickRightListener {


    @Bind(R.id.produceImageView)
    ImageView produceImageView;
    @Bind(R.id.produceTextView)
    TextView produceTextView;
    @Bind(R.id.expectteReturnTextView)
    TextView expectteReturnTextView;
    @Bind(R.id.investmentAmountText)
    TextView investmentAmountText;
    @Bind(R.id.startPriceTextew)
    TextView startPriceTextew;
    @Bind(R.id.investmentTimeTextView)
    TextView investmentTimeTextView;
    @Bind(R.id.inserttimeText)
    TextView inserttimeText;
    @Bind(R.id.titleContentLayout)
    LinearLayout titleContentLayout;
    @Bind(R.id.linebelowTitleView)
    View linebelowTitleView;
    @Bind(R.id.informationTextView)
    TextView informationTextView;
    @Bind(R.id.bottomInfoView)
    View bottomInfoView;
    @Bind(R.id.infomationlayout)
    RelativeLayout infomationlayout;
    @Bind(R.id.produceDetailText)
    TextView produceDetailText;
    @Bind(R.id.produceDetailView)
    View produceDetailView;
    @Bind(R.id.producedetailLayout)
    RelativeLayout producedetailLayout;
    @Bind(R.id.itemTextView)
    LinearLayout itemTextView;
    @Bind(R.id.foundDayText)
    TextView foundDayText;
    @Bind(R.id.expiryDayText)
    TextView expiryDayText;
    @Bind(R.id.issueTypeText)
    TextView issueTypeText;
    @Bind(R.id.productTypeText)
    TextView productTypeText;
    @Bind(R.id.renewDeadlineText)
    TextView renewDeadlineText;
    @Bind(R.id.dividendDayText)
    TextView dividendDayText;
    @Bind(R.id.annualYieldText)
    TextView annualYieldText;
    @Bind(R.id.incomeTypeText)
    TextView incomeTypeText;
    @Bind(R.id.investmentOrientationText)
    TextView investmentOrientationText;
    @Bind(R.id.fundmanagementText)
    TextView fundmanagementText;
    @Bind(R.id.fundSubscriptionText)
    TextView fundSubscriptionText;
    @Bind(R.id.fundManagerText)
    TextView fundManagerText;
    @Bind(R.id.custodianText)
    TextView custodianText;
    @Bind(R.id.isRecordText)
    TextView isRecordText;
    @Bind(R.id.statusText)
    TextView statusText;
    @Bind(R.id.controlText)
    TextView controlText;
    @Bind(R.id.creditText)
    TextView creditText;
    @Bind(R.id.descText)
    TextView descText;
    @Bind(R.id.highlightsText)
    TextView highlightsText;
    @Bind(R.id.essentialInfoLayout)
    LinearLayout essentialInfoLayout;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.produceDetailLayout)
    LinearLayout produceDetailLayout;
    @Bind(R.id.producelayout)
    LinearLayout producelayout;
    @Bind(R.id.scrollview)
    ScrollViewExtend allSv;
    @Bind(R.id.commonToolBar)
    CommonToolBar producesDetailToolbar;
    @Bind(R.id.informationTextViewTop)
    TextView informationTextViewTop;
    @Bind(R.id.bottomInfoViewTop)
    View bottomInfoViewTop;
    @Bind(R.id.infomationlayoutTop)
    RelativeLayout infomationlayoutTop;
    @Bind(R.id.produceDetailTextTop)
    TextView produceDetailTextTop;
    @Bind(R.id.produceDetailViewTop)
    View produceDetailViewTop;
    @Bind(R.id.producedetailLayoutTop)
    RelativeLayout producedetailLayoutTop;
    @Bind(R.id.linebelowLayout)
    LinearLayout linebelowLayout;
    @Bind(R.id.topLayout)
    RelativeLayout topLayout;
    @Bind(R.id.tv_order)
    TextView concelTextView;
    @Bind(R.id.concelLayout)
    RelativeLayout concelLayout;
    @Bind(R.id.signUpTextView)
    TextView signUpTextView;
    @Bind(R.id.shareLayout)
    RelativeLayout shareLayout;
    @Bind(R.id.inputNUmberText)
    EditText inputNUmberText;
    @Bind(R.id.contextText)
    TextView contextText;
    @Bind(R.id.producereservaText)
    TextView producereservaText;
    @Bind(R.id.bottomLayouts)
    LinearLayout bottomLayouts;
    @Bind(R.id.contactTextView)
    TextView contactTextView;
    @Bind(R.id.textviewtext)
    TextView textviewtext;
    private int productId;
    private int position;
    private int mParallaxImageHeight;
    private ProductModel productModel;

    private Handler textviewtextmHandler;
    private Handler descTextmHandler;
    private Handler handler = new Handler();

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        informationTextView = r2v(R.id.informationTextView);
        bottomInfoView = r2v(R.id.bottomInfoView);
        produceDetailText = r2v(R.id.produceDetailText);
        produceDetailView = r2v(R.id.produceDetailView);

        producesDetailToolbar.dismissLine();
        WebSettings wv_setttig = webview.getSettings();
        wv_setttig.setDefaultTextEncodingName("UTF-8");
        webview.setWebViewClient(webviewClient);
        wv_setttig.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        wv_setttig.setUseWideViewPort(true);
//        wv_setttig.setLoadsImagesAutomatically(true);
        wv_setttig.setLoadWithOverviewMode(true);
//        wv_setttig.setBuiltInZoomControls(true);
//        wv_setttig.setSupportZoom(true);
//        wv_setttig.setDisplayZoomControls(false);
        wv_setttig.setJavaScriptEnabled(true);
        allSv.setCallbacks(this);

        textviewtextmHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 0x101) {
                    if(msg.obj!=null){
                        textviewtext.setText((Spanned) msg.obj);
                    }
                }
                return false;
            }
        });
        descTextmHandler=new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == 0x102) {
                    if(msg.obj!=null){
                        descText.setText((Spanned) msg.obj);
                    }
                }
                return false;
            }
        });
    }

    private WebViewClient webviewClient = new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    @Override
    protected void initEvent() {
        informationTextView.setOnClickListener(this);
        produceDetailText.setOnClickListener(this);
        producedetailLayoutTop.setOnClickListener(this);
        infomationlayoutTop.setOnClickListener(this);
        producereservaText.setOnClickListener(this);
        producesDetailToolbar.setClickRightListener(this);
        concelTextView.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
        contextText.setOnClickListener(this);
        contactTextView.setOnClickListener(this);
    }


    @Override
    protected void initData() {
        productId = getIntent().getIntExtra("productId", -1);
        position = getIntent().getIntExtra("position", -1);
        if (productId == -1)
            return;
        ProductController.getInstance().getProduceDetail(this, productId);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mParallaxImageHeight = linebelowTitleView.getTop() - producesDetailToolbar.getHeight();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.produce_detail_activity;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.informationTextView:
                ChangeTpye(0);
                break;
            case R.id.produceDetailText:
                ChangeTpye(1);
                break;
            case R.id.infomationlayoutTop:
                ChangeTpye(0);
                break;
            case R.id.producedetailLayoutTop:
                ChangeTpye(1);
                break;
            case R.id.contactTextView:
                //// TODO: 2016/7/31 联系理财师
                if (UserPreference.getPlannerUid() == 0) {
                    ToastTool.show("您没有理财师");
                    return;
                }
                IntentTool.chat(FhProductDetailActivity.this, null, UserPreference.getPlannerUid());
                break;
            case R.id.contextText:
                //// TODO: 2016/7/31 联系理财师
                if (UserPreference.getPlannerUid() == 0) {
                    ToastTool.show("您没有理财师");
                    return;
                }
                IntentTool.chat(FhProductDetailActivity.this, null, UserPreference.getPlannerUid());
                break;
            case R.id.signUpTextView:
                //分享
                IntentTools.startClientList(FhProductDetailActivity.this, "product", productId);
                break;
            case R.id.tv_order:
                //// TODO: 2016/7/31 取消预约
                ReservaCancelPopUpDialog();
                break;
            case R.id.producereservaText:

                String amount = inputNUmberText.getText().toString();

                if (amount.equals("")) {
                    ToastTool.show("请输入预约金额");
                    return;
                }
                int qiTouJinE = Integer.parseInt(inputNUmberText.getText().toString()) * 10000;
                if (qiTouJinE < productModel.getInvestThreshold()) {
                    ToastTool.show("金额不能少于起投金额");
                    return;
                }
                ProductController.getInstance().orderProduct(this, productId, UserPreference.getRoleId(), UserPreference.getPlannerId(), qiTouJinE);

                break;
        }
    }

    public void ChangeTpye(int flag) {
        informationTextView.setTextColor(getResources().getColor(R.color.titleTextColor));
        bottomInfoView.setBackgroundColor(getResources().getColor(R.color.gray));
        produceDetailText.setTextColor(getResources().getColor(R.color.titleTextColor));
        produceDetailView.setBackgroundColor(getResources().getColor(R.color.gray));
        informationTextViewTop.setTextColor(getResources().getColor(R.color.titleTextColor));
        bottomInfoViewTop.setBackgroundColor(getResources().getColor(R.color.gray));
        produceDetailTextTop.setTextColor(getResources().getColor(R.color.titleTextColor));
        produceDetailViewTop.setBackgroundColor(getResources().getColor(R.color.gray));
        if (flag == 0) {
            informationTextViewTop.setTextColor(getResources().getColor(R.color.appColorBlue));
            bottomInfoViewTop.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            informationTextView.setTextColor(getResources().getColor(R.color.appColorBlue));
            bottomInfoView.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            essentialInfoLayout.setVisibility(View.VISIBLE);
            produceDetailLayout.setVisibility(View.GONE);
        } else {
            produceDetailTextTop.setTextColor(getResources().getColor(R.color.appColorBlue));
            produceDetailViewTop.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            produceDetailText.setTextColor(getResources().getColor(R.color.appColorBlue));
            produceDetailView.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            essentialInfoLayout.setVisibility(View.GONE);
            produceDetailLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.produceDetail.getType()) {
            productModel = (ProductModel) result;
            if (UserPreference.isCustomer()) {
                if ((String.valueOf(productModel.getReservationId()) == null) || productModel.getReservationResult().equals("cancel")) {
                    //可预约
//                    concelLayout.setVisibility(View.VISIBLE);
//                    shareLayout.setVisibility(View.GONE);
//                    bottomLayouts.setVisibility(View.GONE);

                    concelLayout.setVisibility(View.GONE);
                    shareLayout.setVisibility(View.GONE);
                    bottomLayouts.setVisibility(View.VISIBLE);
                } else if (productModel.getReservationResult().equals("success")) {
//                 不可逾越
//                     concelLayout.setVisibility(View.GONE);
//                    shareLayout.setVisibility(View.GONE);
//                    bottomLayouts.setVisibility(View.VISIBLE);
                    concelLayout.setVisibility(View.VISIBLE);
                    shareLayout.setVisibility(View.GONE);
                    bottomLayouts.setVisibility(View.GONE);
                } else {
                    concelLayout.setVisibility(View.GONE);
                    shareLayout.setVisibility(View.GONE);
                    bottomLayouts.setVisibility(View.VISIBLE);
                }
            } else {
                concelLayout.setVisibility(View.GONE);
                shareLayout.setVisibility(View.VISIBLE);
                bottomLayouts.setVisibility(View.GONE);
            }
            producesDetailToolbar.setCollect(productModel.getFocusStatus() == 1);
//            if(productModel.getCover()!=null&&!productModel.getCover().equals("")){
            produceImageView.setVisibility(View.VISIBLE);
            ImageLoader.getInstance(this, R.drawable.default_error_long).displayImage(productModel.getCover(), produceImageView);
//            }else{
//                produceImageView.setVisibility(View.GONE);
//            }
            try{

                if(productModel.getDetailContent().equals("")){
                    webview.setVisibility(View.VISIBLE);
                    textviewtext.setVisibility(View.GONE);
                    webview.loadUrl(productModel.getDetailUrl());
                }else{
                    webview.setVisibility(View.GONE);
                    textviewtext.setVisibility(View.VISIBLE);

                    String html=productModel.getDetailContent();

                    WindowManager wm = FhProductDetailActivity.this.getWindowManager();

                    int width = wm.getDefaultDisplay().getWidth();
                    int height = wm.getDefaultDisplay().getHeight();

//                    int demp=drawable.getIntrinsicHeight()/drawable.getIntrinsicWidth();
//                    Logger.e("  drawable.;"+width+height+demp);
//                    style=\"width: 410px; height: 757px;\"
                    String newHtml="img  style=\""+"\\"+"width:"+width+"px;"+"height:auto\"";
                    String newStr2 = html.replaceAll("img", newHtml);

//                    String newhtml=html+"<script type='text/javascript'>"+
//                    "window.onload = function(){\n"+
//                    "var $img = document.getElementsByTagName('img');\n"+
//                    "for(var p in  $img){\n"+
//                    " $img[p].style.width ="+width+";\n"+
//                    "$img[p].style.height ='auto'\n"+
//                    "}"+
//                    "</script>";
//
                    new HtmlSummerTesk(productModel.getDetailContent()).execute();
//                    fixedThreadPool.execute(new Runnable() {
//                        Message msg = Message.obtain();
//                        @Override
//                        public void run() {
////                             Spanned spanned = Html.fromHtml(productModel.getDetailContent(), new MyImageGetter(FhProductDetailActivity.this,
////                             textviewtext), null);
////                             textviewtext.setText(spanned);
////                             textviewtext.setMovementMethod(LinkMovementMethod.getInstance());
//                            Html.ImageGetter imageGetter=new Html.ImageGetter() {
//                                @Override
//                                public Drawable getDrawable(String source) {
//                                    WindowManager wm = FhProductDetailActivity.this.getWindowManager();
//                                    int width = wm.getDefaultDisplay().getWidth();
//                                    int height = wm.getDefaultDisplay().getHeight();
//                                    Drawable drawable =getImageFromNetwork(source);
//                                    drawable.setBounds(0,0,width,drawable.getIntrinsicHeight()*4);
//                                    return drawable;
//                                }
//                            };
//                            Spanned spanned = Html.fromHtml(productModel.getDetailContent(), imageGetter, null);
//                            msg.what = 0x101;
//                            msg.obj = spanned;
//                            textviewtextmHandler.sendMessage(msg);
//                        }
//                    });
//                    Spanned spanned = Html.fromHtml(html, new MyImageGetter(this,
//                            textviewtext), null);
//                    textviewtext.setText(spanned);
//                    textviewtext.setMovementMethod(LinkMovementMethod.getInstance());
//                    textviewtext.setText(Html.fromHtml(newStr2, imageGetter, null));

//                    new Thread(new Runnable() {
//                        Message msg = Message.obtain();
//                        @Override
//                        public void run() {
//                            Html.ImageGetter imageGetter=new Html.ImageGetter() {
//                                @Override
//                                public Drawable getDrawable(String source) {
//                                    WindowManager wm = FhProductDetailActivity.this.getWindowManager();
//
//                                    int width = wm.getDefaultDisplay().getWidth();
//                                    int height = wm.getDefaultDisplay().getHeight();
////                                        drawable.setBounds(0, 0, width,
////                                                drawable.getIntrinsicHeight()*5);
//
//
//                                    Drawable drawable =getImageFromNetwork(source);
//                                    drawable.setBounds(0,0,width,drawable.getIntrinsicHeight()*4);
//                                    return drawable;
//                                }
//                            };
//                            CharSequence charSequence = Html.fromHtml(productModel.getDetailContent(), imageGetter, null);
//                            msg.what = 0x101;
//                            msg.obj = charSequence;
//                            textviewtextmHandler.sendMessage(msg);
//                        }
//                    }).start();
//
//
                }
                produceTextView.setText(productModel.getName());
            }catch (Exception e){
            }

            String annualInterval = productModel.getAnnualInterval();
            if(StringTools.isNullOrEmpty(annualInterval)){
                annualInterval = productModel.getAnnualYield();
            }
            expectteReturnTextView.setText(annualInterval);
//            expectteReturnTextView.setText(productModel.getAnnualYield());
            BigDecimal bd = new BigDecimal(productModel.getInvestThreshold());
            double qiTouJinE = Double.parseDouble(bd.toPlainString()) / 10000;
            startPriceTextew.setText(qiTouJinE + "万元");
//            startPriceTextew.setText(String.valueOf(productModel.getInvestThreshold()));
//            inserttimeText.setText(productModel.getInvestTermMin() + "-" + productModel.getInvestTermMax() + "个月");
            inserttimeText.setText(productModel.getInvestTerm());
            if(String.valueOf(productModel.getFoundDay())!=null){
                foundDayText.setText((DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, productModel.getFoundDay())));
            }
            if(String.valueOf(productModel.getExpiryDay())!=null){
                expiryDayText.setText(DateTools.formatDateWithSecondSince1970(DateTools.DATE_FORMAT_STYLE_3, productModel.getExpiryDay()));
            }
            issueTypeText.setText(productModel.getIssueType() + "");

            //产品类型
//            if (!productModel.getCid().equals("")) {
//                if (productModel.getCid() == 1) {
//                    productTypeText.setText("开放式契约型");
//                } else if ((productModel.getCid() == 2)) {
//                    productTypeText.setText("封闭式有限合伙私募基金");
//                } else if ((productModel.getCid() == 3)) {
//                    productTypeText.setText("封闭式契约型私募基金");
//                }
//            }
            productTypeText.setText(productModel.getProductTypeName());
            renewDeadlineText.setText(productModel.getRenewDeadline());
            dividendDayText.setText(productModel.getDividendDay());
            annualYieldText.setText(productModel.getAnnualYield());
            incomeTypeText.setText(productModel.getIncomeDistributionType());
            investmentOrientationText.setText(productModel.getInvestmentOrientation());
            fundmanagementText.setText(productModel.getFundManagementFee() + "%");
            fundSubscriptionText.setText(productModel.getFundSubscriptionFee() + "%");
            fundManagerText.setText(productModel.getFundManager());
            custodianText.setText(productModel.getCustodian());
            if(String.valueOf(productModel.getIsRecord())!=null){
                isRecordText.setText(productModel.getIsRecord() == 1 ? "是" : "否");
            }
            String s = "未知";
            if(String.valueOf(productModel.getStatus())!=null){
                if (productModel.getStatus() == 0) {
                    s = "未知";
                } else if (productModel.getStatus() == 1) {
                    s = "产品预热";
                } else if (productModel.getStatus() == 2) {
                    s = "募集中";
                } else if (productModel.getStatus() == 3) {
                    s = "募集结束";
                } else if (productModel.getStatus() == 4) {
                    s = "募集失败";
                } else if (productModel.getStatus() == 5) {
                    s = "产品成立";
                } else if (productModel.getStatus() == 6) {
                    s = "产品到期";
                } else if (productModel.getStatus() == 7) {
                    s = "提前结束";
                }
                statusText.setText(s);
            }
            creditText.setText(productModel.getCredit());

//ggg
//            Spanned spanned = Html.fromHtml(productModel.getDesc(), new MyImageGetter(this,
//                    descText), null);
//            descText.setText(spanned);
//            descText.setMovementMethod(LinkMovementMethod.getInstance());

            new HtmlAsyncTesk(productModel.getDesc()).execute();
//            new Thread(new Runnable() {
//                Message msg = Message.obtain();
//                @Override
//                public void run() {
//                    Html.ImageGetter imageGetter=new Html.ImageGetter() {
//                        @Override
//                        public Drawable getDrawable(String source) {
//                            WindowManager wm = FhProductDetailActivity.this.getWindowManager();
//
//                            int width = wm.getDefaultDisplay().getWidth();
//                            int height = wm.getDefaultDisplay().getHeight();
////                                        drawable.setBounds(0, 0, width,
////                                                drawable.getIntrinsicHeight()*5);
//
//
//                            Drawable drawable =getImageFromNetwork(source);
//                            drawable.setBounds(0,0,width,drawable.getIntrinsicHeight()*4);
//                            return drawable;
//                        }
//                    };
//                    Spanned spanned = Html.fromHtml(productModel.getDesc(), imageGetter, null);
//                    msg.what = 0x102;
//                    msg.obj = spanned;
//                    descTextmHandler.sendMessage(msg);
//                }
//            }).start();
//            descText.setMovementMethod(ScrollingMovementMethod.getInstance());//滚动
//            descText.setText(Html.fromHtml(productModel.getDesc()));gggg
            highlightsText.setText(productModel.getHighlights());
        }
        if (flag == HttpConfig.orderProduct.getType()) {
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    initData();
                    ReservaSuccessPopUpDialog();
                } else {
                    ToastTool.show(object.getString("message"));
                }
            } catch (Exception e) {
                ToastTool.show("预约失败");
            }

        }
        if (flag == HttpConfig.focus.getType()) {
            if (producesDetailToolbar.isCollect()) {
                producesDetailToolbar.setCollect(false);
                ToastTool.show("取消关注");
                EventManager.getInstance().post(new CancelCollectEvent(Constant.COLLECTTYPE_PRODUCT, position, false, null));
            } else {
                producesDetailToolbar.setCollect(true);
                EventManager.getInstance().post(new CancelCollectEvent(Constant.COLLECTTYPE_PRODUCT, position, true, CollectProductModel.getModel(productModel)));
                ToastTool.show("已关注");
            }
        }
        if (flag == HttpConfig.cancelOrderProduct.getType()) {
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    PopUpDialog.dismiss();
                    ToastTool.show("取消预约成功");
                    initData();
                } else {
                    ToastTool.show(object.getString("message"));
                }
            } catch (Exception e) {
                ToastTool.show("取消预约失败");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private class HtmlAsyncTesk extends AsyncTask<String[], Boolean, String>{
        String desc="";
        public HtmlAsyncTesk(String desc) {
            this.desc=desc;
        }

        @Override
        protected String doInBackground(String[]... strings) {

            Message msg = Message.obtain();
            Html.ImageGetter imageGetter=new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    WindowManager wm = FhProductDetailActivity.this.getWindowManager();

                    int width = wm.getDefaultDisplay().getWidth();
                    int height = wm.getDefaultDisplay().getHeight();
//                                        drawable.setBounds(0, 0, width,
//                                                drawable.getIntrinsicHeight()*5);


                    Drawable drawable =getImageFromNetwork(source);
                    drawable.setBounds(0,0,width,drawable.getIntrinsicHeight()*4);
                    return drawable;
                }
            };
            Spanned spanned = Html.fromHtml(desc, imageGetter, null);
            msg.what = 0x102;
            msg.obj = spanned;
            descTextmHandler.sendMessage(msg);
            return null;
        }
    }
    private class HtmlSummerTesk extends AsyncTask<String[], Boolean, String>{
        String summer="";
        public HtmlSummerTesk(String summer) {
            this.summer=summer;
        }

        @Override
        protected String doInBackground(String[]... strings) {
            Message msg = Message.obtain();
//                             Spanned spanned = Html.fromHtml(productModel.getDetailContent(), new MyImageGetter(FhProductDetailActivity.this,
//                             textviewtext), null);
//                             textviewtext.setText(spanned);
//                             textviewtext.setMovementMethod(LinkMovementMethod.getInstance());
                Html.ImageGetter imageGetter=new Html.ImageGetter() {
                    @Override
                    public Drawable getDrawable(String source) {
                        WindowManager wm = FhProductDetailActivity.this.getWindowManager();
                        int width = wm.getDefaultDisplay().getWidth();
                        int height = wm.getDefaultDisplay().getHeight();
                        Drawable drawable =getImageFromNetwork(source);
                        drawable.setBounds(0,0,width,drawable.getIntrinsicHeight()*4);
                        return drawable;
                    }
                };
                Spanned spanned = Html.fromHtml(summer, imageGetter, null);
                msg.what = 0x101;
                msg.obj = spanned;
                textviewtextmHandler.sendMessage(msg);
            return null;
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

            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drawable;
    }
//
//    Bitmap getImageFromNetwork(String imageUrl) {
//        URL myFileUrl = null;
//        Drawable drawable = null;
//        Bitmap bitmap=null;
//        try {
//            myFileUrl = new URL(imageUrl);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        try {
//            HttpURLConnection conn = (HttpURLConnection) myFileUrl
//                    .openConnection();
//            conn.setDoInput(true);
//
//            conn.connect();
//            InputStream is = conn.getInputStream();
//
//            byte[] bitmapbyte=readInputStream(is);
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
////            drawable = Drawable.createFromStream(is, null);
//            bitmap=BitmapFactory.decodeByteArray(bitmapbyte, 0 , bitmapbyte.length, options);
//            is.close();
//            return bitmap;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return  bitmap;
//    }
    public byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024 ];
        int length=-1 ;
        try {
            while((length=is.read(buffer))!=-1 ){
                baos.write(buffer, 0 , length);
            }
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] data=baos.toByteArray();
        try {
            is.close();
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void onScrollBottom() {

    }

    @Override
    public void onScrollChanged(int scrollY) {
        int baseColor = getResources().getColor(R.color.selected_line_color);
        float alpha = 1 - (float) Math.max(0, mParallaxImageHeight - scrollY) / mParallaxImageHeight;
        int sdk = Build.VERSION.SDK_INT;
        if (alpha > 0.2) {//隐藏
            producesDetailToolbar.setTitleTextColor(R.color.common_title_text);
            producesDetailToolbar.setRightImage(producesDetailToolbar.isCollect() ? R.drawable.collect_y : R.drawable.collect_n);
            producesDetailToolbar.setBackImage(R.drawable.back_default);
            if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
                topLayout.setBackgroundDrawable(null);
            } else {
                topLayout.setBackground(null);
            }

        } else {//显示
            producesDetailToolbar.setTitleTextColor(R.color.white);
            producesDetailToolbar.setRightImage(producesDetailToolbar.isCollect() ? R.drawable.collect_y : R.drawable.title_collect_white);
            producesDetailToolbar.setBackImage(R.drawable.title_back_white);
            if (sdk < Build.VERSION_CODES.JELLY_BEAN) {
                topLayout.setBackgroundDrawable(getResources().getDrawable(R.color.white));
            } else {
                topLayout.setBackground(getResources().getDrawable(R.color.white));
            }
        }
        topLayout.setBackgroundColor(CommonUtil.getColorWithAlpha(alpha, baseColor));
        if (scrollY >= mParallaxImageHeight) {
            linebelowLayout.setVisibility(View.VISIBLE);
            itemTextView.setVisibility(View.GONE);
        } else {
            linebelowLayout.setVisibility(View.GONE);
            itemTextView.setVisibility(View.VISIBLE);
        }

    }

    ProduceCancelReservationDialog PopUpDialog;

    public void ReservaCancelPopUpDialog() {   //取消预约商品
        PopUpDialog = new ProduceCancelReservationDialog(this);
        PopUpDialog.setDialogListener(new ProduceCancelReservationDialog.DialogListener() {
            @Override
            public void onclickConfirm() {
                ProductController.getInstance().cancelOrderProduct(FhProductDetailActivity.this, productModel.getReservationId());
            }
        });
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PopUpDialog.show(fragmentTransaction, "ProduceCancelReservationDialog");
    }

    public void ReservaSuccessPopUpDialog() {   //预约商品成功
        ProduceSuccessReservationDialog PopUpDialog = new ProduceSuccessReservationDialog(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PopUpDialog.show(fragmentTransaction, "ProduceSuccessReservationDialog");
    }


    @Override
    public void OnClickRight(View view) {
        if (productModel == null) {
            return;
        }
        if (producesDetailToolbar.isCollect()) {
            ProductController.getInstance().cancelFocusProduct(this, productId);
        } else {
            ProductController.getInstance().focusProduct(this, productId);
        }
    }

//    final Html.ImageGetter imageGetter = new Html.ImageGetter() {
//
//        public Drawable getDrawable(String source) {
//            Drawable drawable = null;
//            try {
//                String urls=UrlRes.getInstance().getPictureUrl()+source;
//                URL url=new URL(urls);
//                Logger.e("urls"+url);
//                drawable = Drawable.createFromStream(url.openStream(), "");
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            }
//            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//            return  drawable;
//        }
//    };

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
            String imageName = source;
            String sdcardPath = Environment.getExternalStorageDirectory()
                    .toString();
            String[] ss = source.split("\\.");
            String ext = ss[ss.length - 1];
            String savePath = sdcardPath + "/" + context.getPackageName() + "/"
                    + imageName + "." + ext;

            File file = new File(savePath);
            if (file.exists()) {
                Drawable drawable = Drawable.createFromPath(savePath);
                WindowManager wm = FhProductDetailActivity.this.getWindowManager();

                int width = wm.getDefaultDisplay().getWidth();
                int height = wm.getDefaultDisplay().getHeight();

                float demp=drawable.getIntrinsicHeight()/drawable.getIntrinsicWidth();

//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
//                        drawable.getIntrinsicHeight());
                drawable.setBounds(0, 0, width,
                        drawable.getIntrinsicHeight()*5);
                return drawable;
            }
            Resources res = context.getResources();
            URLDrawable drawable = new URLDrawable(
                    res.getDrawable(R.drawable.default_error_long));
            new ImageAsync(drawable).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,savePath, source);
            return drawable;

        }

        private class ImageAsync extends AsyncTask<String, Integer, Drawable> {

            private URLDrawable drawable;

            public ImageAsync(URLDrawable drawable) {
                this.drawable = drawable;
            }

            @Override
            protected Drawable doInBackground(String... params) {
                // TODO Auto-generated method stub
                String savePath = params[0];
                String url = params[1];
                url=UrlRes.getInstance().getPictureUrl()+url;
                InputStream in = null;
                try {
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
                    if(tv.getText()!=null){
                        tv.setText(tv.getText());
                    }
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
                drawable.getIntrinsicHeight();
                WindowManager wm = FhProductDetailActivity.this.getWindowManager();

                int width = wm.getDefaultDisplay().getWidth();
                int height = wm.getDefaultDisplay().getHeight();

                int demp=drawable.getIntrinsicHeight()/drawable.getIntrinsicWidth();

                drawable.setBounds(0, 0, width,
                        drawable.getIntrinsicHeight()*5);
                setBounds(0, 0, width,
                        drawable.getIntrinsicHeight()*5);

            }

            @Override
            public void draw(Canvas canvas) {
                // TODO Auto-generated method stub
                drawable.draw(canvas);
//                new ImageThread().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"");
            }
        }
    }
//    class ImageThread extends AsyncTask{
//        @Override
//        protected Object doInBackground(Object[] objects) {
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//    }
}
