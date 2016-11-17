package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.content.Intent;
import android.os.Build;
import android.os.Handler;
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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.DateTools;
import com.base.platform.utils.java.StringTools;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.HtmlHandler;
import com.fhzc.app.android.android.ui.HtmlRunnable;
import com.fhzc.app.android.android.ui.view.dialog.ProduceCancelReservationDialog;
import com.fhzc.app.android.android.ui.view.dialog.ProduceSuccessReservationDialog;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.ScrollViewExtend;
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

import org.json.JSONObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fw on 16/10/26.
 */

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener, ScrollViewExtend.ObservableScrollViewCallbacks, CommonToolBar.ClickRightListener{


    @Bind(R.id.tv_abbreviation)
    TextView tvName;
    @Bind(R.id.tv_totalname)
    TextView tvTotalname;
    @Bind(R.id.tv_annualyield)
    TextView tvAnnualyield;
    @Bind(R.id.interestProgress)
    ProgressBar interestProgress;
    @Bind(R.id.tv_cycle)
    TextView tvCycle;
    @Bind(R.id.tv_remain)
    TextView tvRemain;
    @Bind(R.id.tv_producttype)
    TextView tvProducttype;
    @Bind(R.id.tv_minsubscription)
    TextView tvMinsubscription;
    @Bind(R.id.linebelowTitleView)
    View linebelowTitleView;
    @Bind(R.id.informationTextView)
    TextView informationTextView;
    @Bind(R.id.bottomInfoView)
    View bottomInfoView;
    @Bind(R.id.infomationlayout)
    RelativeLayout infomationlayout;
    @Bind(R.id.producthighlightsText)
    TextView producthighlightsText;
    @Bind(R.id.producthighlightsView)
    View producthighlightsView;
    @Bind(R.id.producthighlightsLayout)
    RelativeLayout producthighlightsLayout;
    @Bind(R.id.productDetailText)
    TextView productDetailText;
    @Bind(R.id.productDetailView)
    View productDetailView;
    @Bind(R.id.productDetailLayout)
    RelativeLayout productDetailLayout;
    @Bind(R.id.itemTextView)
    LinearLayout itemTextView;
    @Bind(R.id.faxingmoshi)
    TextView faxingmoshi;
    @Bind(R.id.chanpintouziqixian)
    TextView chanpintouziqixian;
    @Bind(R.id.chanpincunxuqixian)
    TextView chanpincunxuqixian;
    @Bind(R.id.jijinguanliren)
    TextView jijinguanliren;
    @Bind(R.id.jijintuoguanren)
    TextView jijintuoguanren;
    @Bind(R.id.beianyufou)
    TextView beianyufou;
    @Bind(R.id.image)
    ImageView basicInfo;
    @Bind(R.id.shouyifenpeifangshi)
    TextView shouyifenpeifangshi;
    @Bind(R.id.chanpinliangdian)
    TextView chanpinliangdian;
    @Bind(R.id.touzifangxiang)
    TextView touzifangxiang;
    @Bind(R.id.zengxincuoshi)
    TextView zengxincuoshi;
    @Bind(R.id.ll_chanpinliangdian)
    LinearLayout llChanpinliangdian;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.textviewtext)
    TextView textviewtext;
    @Bind(R.id.produceDetailLayout)
    LinearLayout produceDetailLayout;
    @Bind(R.id.producelayout)
    LinearLayout producelayout;
    @Bind(R.id.scrollview)
    ScrollViewExtend scrollview;
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
    @Bind(R.id.productDetailTextTop)
    TextView productDetailTextTop;
    @Bind(R.id.productDetailViewTop)
    View productDetailViewTop;
    @Bind(R.id.productDetailLayoutTop)
    RelativeLayout productDetailLayoutTop;
    @Bind(R.id.linebelowLayout)
    LinearLayout linebelowLayout;
    @Bind(R.id.topLayout)
    RelativeLayout topLayout;
    @Bind(R.id.tv_order)
    TextView tvOrder;
    @Bind(R.id.contactTextView)
    TextView contactTextView;
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
    @Bind(R.id.ll_jibenxinxi)
    LinearLayout llJiBenXinXi;
    @Bind(R.id.tv_IncomeDistributionType)
    TextView tvIncomeDistributionType;
    @Bind(R.id.tv_jijinguanlifei)
    TextView tvJiJinGuanLiFei;
    @Bind(R.id.tv_jijinrengoufei)
    TextView tvJiJinRenGouFei;
    @Bind(R.id.tv_paixiriqi)
    TextView tvPaiXiRiQi;
    @Bind(R.id.tv_nianhuashouyilv)
    TextView tvNianHuaShouYiLv;



    public static Handler htmlHandler;
    private int productId;
    private int position;
    private int mParallaxImageHeight;
    private ProductModel productModel;
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);

    @Override
    protected void initView() {
        ButterKnife.bind(this);

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
        scrollview.setCallbacks(this);

        htmlHandler = new HtmlHandler(this);

        if (UserPreference.isCustomer()) {
            concelLayout.setVisibility(View.VISIBLE);
        } else {
            shareLayout.setVisibility(View.VISIBLE);
        }
    }

    private WebViewClient webviewClient = new WebViewClient() {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fixedThreadPool.shutdown();
        htmlHandler.removeCallbacksAndMessages(null);
        ButterKnife.unbind(this);
    }

    @Override
    protected void initEvent() {
        producesDetailToolbar.setClickRightListener(this);
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
        return R.layout.activity_productdetail;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.produceDetail.getType()) {
            productModel = (ProductModel) result;
            if (UserPreference.isCustomer()) {
                if ((String.valueOf(productModel.getReservationId()) == null) || productModel.getReservationResult().equals("cancel")) {
                    //可预约
                    tvOrder.setText("预约");

//                    concelLayout.setVisibility(View.GONE);
//                    shareLayout.setVisibility(View.GONE);
//                    bottomLayouts.setVisibility(View.VISIBLE);
                } else if (productModel.getReservationResult().equals("success")) {
//                 不预约
//                    concelLayout.setVisibility(View.VISIBLE);
//                    shareLayout.setVisibility(View.GONE);
//                    bottomLayouts.setVisibility(View.GONE);
                }
                else {
//                    concelLayout.setVisibility(View.GONE);
//                    shareLayout.setVisibility(View.GONE);
//                    bottomLayouts.setVisibility(View.VISIBLE);
                }
            }
            else {
//                concelLayout.setVisibility(View.GONE);
//                shareLayout.setVisibility(View.VISIBLE);
//                bottomLayouts.setVisibility(View.GONE);
            }
            ImageLoader.getInstance(this).displayImage(productModel.getDesc(),basicInfo);
            producesDetailToolbar.setCollect(productModel.getFocusStatus() == 1);
            try{

                if(productModel.getDetailContent().equals("")){
                    webview.setVisibility(View.VISIBLE);
                    textviewtext.setVisibility(View.GONE);
                    webview.loadUrl(productModel.getDetailUrl());
                }else{
                    webview.setVisibility(View.GONE);
                    textviewtext.setVisibility(View.VISIBLE);

                    String html=productModel.getDetailContent();

                    WindowManager wm = ProductDetailActivity.this.getWindowManager();

                    int width = wm.getDefaultDisplay().getWidth();
                    int height = wm.getDefaultDisplay().getHeight();

                    String newHtml="img  style=\""+"\\"+"width:"+width+"px;"+"height:auto\"";
                    String newStr2 = html.replaceAll("img", newHtml);

                    //展示html标签内容
                    fixedThreadPool.execute(new HtmlRunnable(htmlHandler,productModel.getDetailContent(),this,R.id.textviewtext));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            tvName.setText(productModel.getName());
            tvTotalname.setText(productModel.getName());

            String annualInterval = productModel.getAnnualInterval();
            if(StringTools.isNullOrEmpty(annualInterval)){
                annualInterval = productModel.getAnnualYield();
            }
            tvAnnualyield.setText(annualInterval);
            BigDecimal bd = new BigDecimal(productModel.getInvestThreshold());
            double qiTouJinE = Double.parseDouble(bd.toPlainString()) / 10000;
            tvMinsubscription.setText(qiTouJinE + "万元");
            chanpintouziqixian.setText(productModel.getInvestTerm());
            faxingmoshi.setText(productModel.getIssueType() + "");


            Spanned shouyifengpeifangshi = Html.fromHtml(productModel.getIncomeDistributionType());
            tvIncomeDistributionType.setText(shouyifengpeifangshi);
            tvJiJinGuanLiFei.setText(productModel.getFundManagementFee());
            tvJiJinRenGouFei.setText(productModel.getFundSubscriptionFee());
            tvPaiXiRiQi.setText(Html.fromHtml(productModel.getDividendDay()));
            tvNianHuaShouYiLv.setText(Html.fromHtml(productModel.getAnnualYield()));

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
            tvProducttype.setText(productModel.getProductTypeName());
            int day = DateTools.friendlyDayTime(productModel.getCollectEnd());
            int day1 = DateTools.differDayTime(productModel.getCollectStart(), productModel.getCollectEnd());
            int day2 = DateTools.friendlyDayTime(productModel.getCollectStart());
            interestProgress.setProgress(day < 0 ? 100 : day2<0?Math.abs(day2) * 100 / day1:0);
            tvCycle.setText(DateTools.dateFormatterOfDateTime(DateTools.DATE_FORMAT_STYLE_3,productModel.getCollectStart())+"-"+DateTools.dateFormatterOfDateTime(DateTools.DATE_FORMAT_STYLE_3,productModel.getCollectEnd()));
            tvRemain.setText(day < 0 ? "募集已结束" : "还剩" + day + "天结束");
            chanpincunxuqixian.setText(productModel.getRenewDeadline());
            jijinguanliren.setText(productModel.getFundManager());
            jijintuoguanren.setText(productModel.getCustodian());

            chanpinliangdian.setText(Html.fromHtml(productModel.getHighlights()));
            touzifangxiang.setText(Html.fromHtml(productModel.getInvestmentOrientation()));
            zengxincuoshi.setText(Html.fromHtml(productModel.getCredit()));



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
                    tvOrder.setText("预约");
                } else {
                    ToastTool.show(object.getString("message"));
                }
            } catch (Exception e) {
                ToastTool.show("取消预约失败");
            }
        }

    }


    public void ChangeTpye(int flag) {
        informationTextView.setTextColor(getResources().getColor(R.color.titleTextColor));
        bottomInfoView.setBackgroundColor(getResources().getColor(R.color.gray));
        producthighlightsText.setTextColor(getResources().getColor(R.color.titleTextColor));
        producthighlightsView.setBackgroundColor(getResources().getColor(R.color.gray));
        productDetailText.setTextColor(getResources().getColor(R.color.titleTextColor));
        productDetailView.setBackgroundColor(getResources().getColor(R.color.gray));

        informationTextViewTop.setTextColor(getResources().getColor(R.color.titleTextColor));
        bottomInfoViewTop.setBackgroundColor(getResources().getColor(R.color.gray));
        produceDetailTextTop.setTextColor(getResources().getColor(R.color.titleTextColor));
        produceDetailViewTop.setBackgroundColor(getResources().getColor(R.color.gray));
        productDetailTextTop.setTextColor(getResources().getColor(R.color.titleTextColor));
        productDetailViewTop.setBackgroundColor(getResources().getColor(R.color.gray));
        if (flag == 0) {
            informationTextViewTop.setTextColor(getResources().getColor(R.color.appColorBlue));
            bottomInfoViewTop.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            informationTextView.setTextColor(getResources().getColor(R.color.appColorBlue));
            bottomInfoView.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            llJiBenXinXi.setVisibility(View.VISIBLE);
            llChanpinliangdian.setVisibility(View.GONE);
            produceDetailLayout.setVisibility(View.GONE);
        } else if(flag ==1) {
            producthighlightsText.setTextColor(getResources().getColor(R.color.appColorBlue));
            producthighlightsView.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            produceDetailTextTop.setTextColor(getResources().getColor(R.color.appColorBlue));
            produceDetailViewTop.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            llChanpinliangdian.setVisibility(View.VISIBLE);
            llJiBenXinXi.setVisibility(View.GONE);
            produceDetailLayout.setVisibility(View.GONE);
        } else {
            productDetailText.setTextColor(getResources().getColor(R.color.appColorBlue));
            productDetailView.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            productDetailTextTop.setTextColor(getResources().getColor(R.color.appColorBlue));
            productDetailViewTop.setBackgroundColor(getResources().getColor(R.color.appColorBlue));
            produceDetailLayout.setVisibility(View.VISIBLE);
            llChanpinliangdian.setVisibility(View.GONE);
            llJiBenXinXi.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.tv_abbreviation, R.id.tv_totalname, R.id.tv_annualyield, R.id.interestProgress, R.id.tv_cycle, R.id.tv_remain, R.id.tv_producttype, R.id.tv_minsubscription, R.id.linebelowTitleView, R.id.informationTextView, R.id.bottomInfoView, R.id.infomationlayout, R.id.producthighlightsText, R.id.producthighlightsView, R.id.producthighlightsLayout, R.id.productDetailText, R.id.productDetailView, R.id.productDetailLayout, R.id.itemTextView, R.id.faxingmoshi, R.id.chanpintouziqixian, R.id.chanpincunxuqixian, R.id.jijinguanliren, R.id.jijintuoguanren, R.id.beianyufou, R.id.image, R.id.shouyifenpeifangshi, R.id.chanpinliangdian, R.id.touzifangxiang, R.id.zengxincuoshi, R.id.ll_chanpinliangdian, R.id.webview, R.id.textviewtext, R.id.produceDetailLayout, R.id.producelayout, R.id.scrollview, R.id.commonToolBar, R.id.informationTextViewTop, R.id.bottomInfoViewTop, R.id.infomationlayoutTop, R.id.produceDetailTextTop, R.id.produceDetailViewTop, R.id.producedetailLayoutTop, R.id.productDetailTextTop, R.id.productDetailViewTop, R.id.productDetailLayoutTop, R.id.linebelowLayout, R.id.topLayout, R.id.tv_order, R.id.contactTextView, R.id.concelLayout, R.id.signUpTextView, R.id.shareLayout, R.id.inputNUmberText, R.id.contextText, R.id.producereservaText, R.id.bottomLayouts})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_abbreviation:
                break;
            case R.id.tv_totalname:
                break;
            case R.id.tv_annualyield:
                break;
            case R.id.interestProgress:
                break;
            case R.id.tv_cycle:
                break;
            case R.id.tv_remain:
                break;
            case R.id.tv_producttype:
                break;
            case R.id.tv_minsubscription:
                break;
            case R.id.linebelowTitleView:
                break;
            case R.id.informationTextView:
                ChangeTpye(0);
                break;
            case R.id.bottomInfoView:
                break;
            case R.id.infomationlayout:
                break;
            case R.id.producthighlightsText:
                ChangeTpye(1);
                break;
            case R.id.producthighlightsView:
                break;
            case R.id.producthighlightsLayout:
                break;
            case R.id.productDetailText:
                ChangeTpye(2);
                break;
            case R.id.productDetailView:
                break;
            case R.id.productDetailLayout:
                break;
            case R.id.itemTextView:
                break;
            case R.id.faxingmoshi:
                break;
            case R.id.chanpintouziqixian:
                break;
            case R.id.chanpincunxuqixian:
                break;
            case R.id.jijinguanliren:
                break;
            case R.id.jijintuoguanren:
                break;
            case R.id.beianyufou:
                break;
            case R.id.image:
                break;
            case R.id.shouyifenpeifangshi:
                break;
            case R.id.chanpinliangdian:
                break;
            case R.id.touzifangxiang:
                break;
            case R.id.zengxincuoshi:
                break;
            case R.id.ll_chanpinliangdian:
                break;
            case R.id.webview:
                break;
            case R.id.textviewtext:
                break;
            case R.id.produceDetailLayout:
                break;
            case R.id.producelayout:
                break;
            case R.id.scrollview:
                break;
            case R.id.commonToolBar:
                break;
            case R.id.informationTextViewTop:
                ChangeTpye(0);
                break;
            case R.id.bottomInfoViewTop:
                break;
            case R.id.infomationlayoutTop:
                break;
            case R.id.produceDetailTextTop:
                ChangeTpye(1);
                break;
            case R.id.produceDetailViewTop:
                break;
            case R.id.producedetailLayoutTop:
                break;
            case R.id.productDetailTextTop:
                ChangeTpye(2);
                break;
            case R.id.productDetailViewTop:
                break;
            case R.id.productDetailLayoutTop:
                break;
            case R.id.linebelowLayout:
                break;
            case R.id.topLayout:
                break;
            case R.id.tv_order:
                if(tvOrder.getText().equals("预约")){
                    int status = productModel.getStatus();
                    if(status !=1 && status !=2){
                        ToastTool.show("该产品不可预约!");
                    }else{
                        IntentTools.startOrderProductForResult(this,productModel.getName(),productModel.getAnnualInterval(),productModel.getInvestThreshold(),productId);
                    }
                }else{
                    ReservaCancelPopUpDialog();
                }
                break;
            case R.id.contactTextView:
                if (UserPreference.getPlannerUid() == 0) {
                    ToastTool.show("您没有理财师");
                    return;
                }
                IntentTool.chat(ProductDetailActivity.this, null, UserPreference.getPlannerUid());
                break;
            case R.id.concelLayout:
                break;
            case R.id.signUpTextView:
                //分享
                IntentTools.startClientList(ProductDetailActivity.this, "product", productId);
                break;
            case R.id.shareLayout:
                break;
            case R.id.inputNUmberText:
                break;
            case R.id.contextText:
                if (UserPreference.getPlannerUid() == 0) {
                    ToastTool.show("您没有理财师");
                    return;
                }
                IntentTool.chat(ProductDetailActivity.this, null, UserPreference.getPlannerUid());
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
            case R.id.bottomLayouts:
                break;
        }
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

    @Override
    public void onScrollBottom() {

    }

    ProduceCancelReservationDialog PopUpDialog;

    public void ReservaCancelPopUpDialog() {   //取消预约商品
        PopUpDialog = new ProduceCancelReservationDialog(this);
        PopUpDialog.setDialogListener(new ProduceCancelReservationDialog.DialogListener() {
            @Override
            public void onclickConfirm() {
                ProductController.getInstance().cancelOrderProduct(ProductDetailActivity.this, productModel.getReservationId());
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean success = data.getBooleanExtra("success",false);
        if(success){
            tvOrder.setText("取消预约");
        }
    }
}
