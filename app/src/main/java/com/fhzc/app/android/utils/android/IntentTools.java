package com.fhzc.app.android.utils.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.fhzc.app.android.android.ui.activity.AboutActivity;
import com.fhzc.app.android.android.ui.activity.ActivitiesForcastActivity;
import com.fhzc.app.android.android.ui.activity.ActivityListActivity;
import com.fhzc.app.android.android.ui.activity.ActivityTypeListActivity;
import com.fhzc.app.android.android.ui.activity.AnimActivity;
import com.fhzc.app.android.android.ui.activity.AssetManagementActivity;
import com.fhzc.app.android.android.ui.activity.ChatListActivity;
import com.fhzc.app.android.android.ui.activity.ClientListActivity;
import com.fhzc.app.android.android.ui.activity.ClientSelectedListActivity;
import com.fhzc.app.android.android.ui.activity.ContactUsActivity;
import com.fhzc.app.android.android.ui.activity.CustomerDetailActivity;
import com.fhzc.app.android.android.ui.activity.CustomerDetailNewActivity;
import com.fhzc.app.android.android.ui.activity.CustomerOrderActivityActivity;
import com.fhzc.app.android.android.ui.activity.CustomerPointActivity;
import com.fhzc.app.android.android.ui.activity.CustomerRightChagneActivity;
import com.fhzc.app.android.android.ui.activity.CustomerRightSelectActivity;
import com.fhzc.app.android.android.ui.activity.EditCollectActivity;
import com.fhzc.app.android.android.ui.activity.FeedbackActivity;
import com.fhzc.app.android.android.ui.activity.FeedbackDetailActivity;
import com.fhzc.app.android.android.ui.activity.FhServiceActivity;
import com.fhzc.app.android.android.ui.activity.FhVipActivity;
import com.fhzc.app.android.android.ui.activity.FullScreenImageActivity;
import com.fhzc.app.android.android.ui.activity.HotNewsActivity;
import com.fhzc.app.android.android.ui.activity.HotProductsActivity;
import com.fhzc.app.android.android.ui.activity.MainTabActivity;
import com.fhzc.app.android.android.ui.activity.MultiImageSelectorActivity;
import com.fhzc.app.android.android.ui.activity.MyOrderRightActivity;
import com.fhzc.app.android.android.ui.activity.MyWorkActivity;
import com.fhzc.app.android.android.ui.activity.NewsDeatilActiity;
import com.fhzc.app.android.android.ui.activity.PersonInfoActivity;
import com.fhzc.app.android.android.ui.activity.PersonInfoNewActivity;
import com.fhzc.app.android.android.ui.activity.PersonalTailorActivity;
import com.fhzc.app.android.android.ui.activity.PointRecordActivity;
import com.fhzc.app.android.android.ui.activity.ProductDynamicDetail;
import com.fhzc.app.android.android.ui.activity.ProductListActivity;
import com.fhzc.app.android.android.ui.activity.ProductTypeListActivity;
import com.fhzc.app.android.android.ui.activity.ReportListActivity;
import com.fhzc.app.android.android.ui.activity.ReportListNewActivity;
import com.fhzc.app.android.android.ui.activity.RiskAssessmentActivity;
import com.fhzc.app.android.android.ui.activity.SetPasswordActivity;
import com.fhzc.app.android.android.ui.activity.SystemChatActivity;
import com.fhzc.app.android.android.ui.activity.SystemMessageActivity;
import com.fhzc.app.android.android.ui.activity.SystemMessageDetailActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.ActivityDetailActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.FhExclusiveFinancialActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.FhMemberBenefitsActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.FhMemberBenefitsRightActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.FhNewActivityDetailActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.FhNewRightsDetailActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.FhProductDetailActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.FhReportDetailActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.FhRightsDetailActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.FhRightsListActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.FhRightsPopdialogActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.KnowAboutUsActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.OrderProductActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.ProductDetailActivity;
import com.fhzc.app.android.android.ui.activity.fuhuaservice.TourismProductDetailActivity;
import com.fhzc.app.android.android.ui.activity.personinformation.FhAccountInformationActivity;
import com.fhzc.app.android.android.ui.activity.personinformation.FhChangeLoginNameActivity;
import com.fhzc.app.android.android.ui.activity.personinformation.FhChangePasswordActivity;
import com.fhzc.app.android.android.ui.activity.personinformation.FhChangePasswordAgainActivity;
import com.fhzc.app.android.android.ui.activity.personinformation.FhMonthRankActivity;
import com.fhzc.app.android.android.ui.activity.personinformation.FhPersonInformationActivity;
import com.fhzc.app.android.android.ui.activity.personinformation.FhPersonRemarkActivity;
import com.fhzc.app.android.android.ui.activity.personinformation.FhRecordSearchActivity;
import com.fhzc.app.android.android.ui.activity.personinformation.FhYearRankActivity;
import com.fhzc.app.android.android.ui.activity.systemservice.CustomerMyAttention;
import com.fhzc.app.android.android.ui.activity.systemservice.CustomerReservedProductActivity;
import com.fhzc.app.android.android.ui.activity.systemservice.FhForgetPasswordActivity;
import com.fhzc.app.android.android.ui.activity.systemservice.FhForgetPasswordAgainActivity;
import com.fhzc.app.android.android.ui.activity.systemservice.FhInvestmentHistoryActivity;
import com.fhzc.app.android.android.ui.activity.systemservice.FhLoginActivity;
import com.fhzc.app.android.android.ui.activity.systemservice.FhMyAccountantDetailActivity;
import com.fhzc.app.android.models.CollectMapModel;
import com.fhzc.app.android.models.MemberModel;
import com.fhzc.app.android.models.MyProductModel;
import com.fhzc.app.android.models.RightModel;
import com.fhzc.app.android.utils.WebViewActivity;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/7/11.
 */
public class IntentTools {

    /**
     * @deprecated
     * @param context
     */
    public static void startReportDetailActivity(Context context) {
        Intent intent = new Intent(context, FhReportDetailActivity.class);
        context.startActivity(intent);
    }

    public static void startMain(Context context) {
        Intent intent = new Intent(context, MainTabActivity.class);
        context.startActivity(intent);
    }

    public static void startMain(Context context, boolean needAnim) {
        Intent intent = new Intent(context, MainTabActivity.class);
        intent.putExtra("needAnim", needAnim);
        context.startActivity(intent);
    }

    public static void startSetPsw(Context context, int identity, String identifyNum, String phoneNum) {
        Intent intent = new Intent(context, SetPasswordActivity.class);
        intent.putExtra("identity", identity);
        intent.putExtra("identityNum", identifyNum);
        intent.putExtra("phoneNum", phoneNum);
        context.startActivity(intent);
    }

    public static void startCollectionActivity(Context context, String title) {
        Intent intent = new Intent(context, CustomerMyAttention.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    public static void startChatList(Context context) {
        Intent intent;
        intent = new Intent(context, ChatListActivity.class);
        context.startActivity(intent);
    }

    public static void startSystemMessage(Context context) {
        Intent intent = new Intent(context, SystemMessageActivity.class);
        context.startActivity(intent);
    }


    public static void startProduceDetail(Context context, int productId) {
        Intent intent = new Intent(context, FhProductDetailActivity.class);
        intent.putExtra("productId", productId);
        context.startActivity(intent);
    }

    public static int startOrderProductForResult(Activity context,String suoxie,String annualInterval,double investThresHold,int productId){
        Intent intent = new Intent(context, OrderProductActivity.class);
        intent.putExtra("suoxie",suoxie);
        intent.putExtra("annualInterval",annualInterval);
        intent.putExtra("investThresHold",investThresHold);
        intent.putExtra("productId",productId);
        context.startActivityForResult(intent,0);
        return 0;
    }

    public static void startTourismProduceDetail(Context context, int productId) {
        Intent intent = new Intent(context, TourismProductDetailActivity.class);
        intent.putExtra("productId", productId);
        context.startActivity(intent);
    }

    public static void startTourismProduceDetail(Context context, int productId, int position) {
        Intent intent = new Intent(context, TourismProductDetailActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    public static void startProductDetail(Context context, int productId) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("productId", productId);
        context.startActivity(intent);
    }

    public static void startProductDetail(Context context, int productId, int position) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    /**
     * @deprecated
     * @param context
     */
    public static void startProduceDetail(Context context, int productId, int position) {
        Intent intent = new Intent(context, FhProductDetailActivity.class);
        intent.putExtra("productId", productId);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }


    public static void startProduceDynamic(Context context, MyProductModel productModel, boolean isHistory) {
        Intent intent = new Intent(context, ProductDynamicDetail.class);
        intent.putExtra("productModels", productModel);
        intent.putExtra("isHistory", isHistory);
        context.startActivity(intent);
    }


    public static void startActivityDetail(Context context, int activityId) {
//        Intent intent = new Intent(context, FhActivityDetailActivity.class);
        Intent intent = new Intent(context, ActivityDetailActivity.class);
        intent.putExtra("activityId", activityId);
        context.startActivity(intent);
    }
    public static void startNewActivityDetail(Context context, int activityId) {
        Intent intent = new Intent(context, FhNewActivityDetailActivity.class);
        intent.putExtra("activityId", activityId);
        context.startActivity(intent);
    }

    public static void startActivityDetail(Context context, int activityId, int position) {
//        Intent intent = new Intent(context, FhActivityDetailActivity.class);
        Intent intent = new Intent(context, ActivityDetailActivity.class);
        intent.putExtra("activityId", activityId);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }


    public static void startReportDetail(Context context, int reportId) {
        Intent intent = new Intent(context, FhReportDetailActivity.class);
        intent.putExtra("reportId", reportId);
        context.startActivity(intent);
    }

    public static void startNewsDetail(Context context, int newsId) {
        Intent intent = new Intent(context, NewsDeatilActiity.class);
        intent.putExtra("newsId", newsId);
        context.startActivity(intent);
    }


    public static void startReportDetail(Context context, int reportId, int position) {
        Intent intent = new Intent(context, FhReportDetailActivity.class);
        intent.putExtra("reportId", reportId);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    /**
     * @deprecated
     * @param context
     */
    public static void startRightDetail(Context context) {
        Intent intent = new Intent(context, FhRightsDetailActivity.class);
        context.startActivity(intent);
    }

    /**
     * @deprecated
     * @param context
     */
    public static void startCustomerRight(Context context) {
        Intent intent = new Intent(context, CustomerRightSelectActivity.class);
        context.startActivity(intent);
    }
    public static void startCustomerRightChange(Context context) {
        Intent intent = new Intent(context, CustomerRightChagneActivity.class);
        context.startActivity(intent);
    }
    public static void startCustomerPoint(Context context) {
        Intent intent = new Intent(context, CustomerPointActivity.class);
        context.startActivity(intent);
    }

    public static void startRightDetail(Context context, int rightId) {
        Intent intent = new Intent(context, FhRightsDetailActivity.class);
        intent.putExtra("rightId", rightId);
        context.startActivity(intent);
    }
    public static void startNewRightDetail(Context context, int rightId,String status) {
        Intent intent = new Intent(context, FhNewRightsDetailActivity.class);
        intent.putExtra("rightId", rightId);
        intent.putExtra("isright", status);
        context.startActivity(intent);
    }
    public static void startNewRightDetail(Context context, int rightId) {
        Intent intent = new Intent(context, FhNewRightsDetailActivity.class);
        intent.putExtra("rightId", rightId);
        intent.putExtra("isright", 0);
        context.startActivity(intent);
    }
    public static void startRightDetail(Context context, int rightId, int position) {
        Intent intent = new Intent(context, FhRightsDetailActivity.class);
        intent.putExtra("rightId", rightId);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }
    public static void startNewRightDetail(Context context, int rightId, int position,String status) {
        Intent intent = new Intent(context, FhNewRightsDetailActivity.class);
        intent.putExtra("rightId", rightId);
        intent.putExtra("position", position);
        intent.putExtra("isright", status);
        context.startActivity(intent);
    }
    public static void startForgetPass(Context context) {
        Intent intent = new Intent(context, FhForgetPasswordActivity.class);
        context.startActivity(intent);
    }

    public static void startForgetPassAgain(Context context,String mobile) {
        Intent intent = new Intent(context, FhForgetPasswordAgainActivity.class);
        intent.putExtra("mobile", mobile);
        context.startActivity(intent);
    }

    public static void startReportList(Context context) {
        Intent intent = new Intent(context, ReportListActivity.class);
        context.startActivity(intent);
    }
    public static void startNewReportList(Context context) {
        Intent intent = new Intent(context, ReportListNewActivity.class);
        context.startActivity(intent);
    }

    public static void startSystemChat(Context context) {
        Intent intent = new Intent(context, SystemChatActivity.class);
        context.startActivity(intent);
    }

    public static void startLogin(Context context) {
        Intent intent = new Intent(context, FhLoginActivity.class);
        intent.putExtra("noAnim", true);
        context.startActivity(intent);
    }

    public static void startActivitiesFocast(Context context) {
        Intent intent = new Intent(context, ActivitiesForcastActivity.class);
        context.startActivity(intent);
    }

    public static void startActivityList(Context context) {
        Intent intent = new Intent(context, ActivityListActivity.class);
        context.startActivity(intent);
    }

    public static void startActivityTypeList(Context context,int cid,String title) {
        Intent intent = new Intent(context, ActivityTypeListActivity.class);
        intent.putExtra("cid",cid);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    public static void startPersonInfo(Context context) {
        Intent intent = new Intent(context, PersonInfoActivity.class);
        context.startActivity(intent);
    }
    public static void startPersonNewInfo(Context context) {
        Intent intent = new Intent(context, PersonInfoNewActivity.class);
        context.startActivity(intent);
    }

    public static void startNumbrtList(Context context) {
        Intent intent = new Intent(context, FhMemberBenefitsActivity.class);
        context.startActivity(intent);
    }

    public static void startRightBenfitsNumbrtList(Context context) {
        Intent intent = new Intent(context, FhMemberBenefitsRightActivity.class);
        context.startActivity(intent);
    }
    public static void startPersonalTailor(Context context) {
        Intent intent = new Intent(context, PersonalTailorActivity.class);
        context.startActivity(intent);
    }public static void startAssentManagement(Context context) {
        Intent intent = new Intent(context, AssetManagementActivity.class);
        context.startActivity(intent);
    }
    public static void startRightList(Context context, String rightnumber, int cid) {
        Intent intent = new Intent(context, FhRightsListActivity.class);
        intent.putExtra("rightNumber", rightnumber);
        intent.putExtra("cid", cid);
        context.startActivity(intent);
    }

    /**
     * @deprecated
     * @param context
     */
    public static void startFinancialActivity(Context context) {
        Intent intent = new Intent(context, FhExclusiveFinancialActivity.class);

        context.startActivity(intent);
    }
    /**
     * @deprecated
     * @param context
     */
    public static void startSystemMessageDetail(Context context) {
        Intent intent = new Intent(context, SystemMessageDetailActivity.class);

        context.startActivity(intent);
    }

    public static void startKnowAboutUs(Context context,String url,String title) {
        Intent intent = new Intent(context, KnowAboutUsActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    /**
     * @deprecated
     * @param context
     */
    public static void startProductList(Context context) {
        Intent intent = new Intent(context, ProductListActivity.class);

        context.startActivity(intent);
    }

    public static void startProductTypeList(Context context,int cid) {
        Intent intent = new Intent(context, ProductTypeListActivity.class);
        intent.putExtra("cid",cid);
        context.startActivity(intent);
    }
    public static void startWebView(Context context,String url,String title) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("webUrl",url);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }
    public static void startWebView(Context context,String url,String title,boolean flag) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra("webUrl",url);
        intent.putExtra("title",title);
        intent.putExtra("flag",flag);
        context.startActivity(intent);
    }


    public static void startOrderRight(Context context, int uid) {
        Intent intent = new Intent(context, MyOrderRightActivity.class);
        intent.putExtra("uid", uid);
        context.startActivity(intent);
    }
    public static void startOrderRight(Context context, int uid,String name) {
        Intent intent = new Intent(context, MyOrderRightActivity.class);
        intent.putExtra("uid", uid);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    /**
     * @deprecated
     * @param context
     */
    public static void startCustomerOrderActivity(Context context, int uid) {
        Intent intent = new Intent(context, CustomerOrderActivityActivity.class);
        intent.putExtra("uid", uid);
        context.startActivity(intent);
    }
    public static void startCustomerOrderActivity(Context context, int uid,String name) {
        Intent intent = new Intent(context, CustomerOrderActivityActivity.class);
        intent.putExtra("uid", uid);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    public static void startPointRecord(Context context) {
        Intent intent = new Intent(context, PointRecordActivity.class);
        context.startActivity(intent);
    }

    /**
     * 打开新闻热点界面
     * @param context
     */
    public static void startHotNews(Context context) {
        Intent intent = new Intent(context, HotNewsActivity.class);
        context.startActivity(intent);
    }

    public static void startHotProducts(Context context) {
        Intent intent = new Intent(context, HotProductsActivity.class);
        context.startActivity(intent);
    }


    /**
     * 登录 用户名密码
     *
     * @param context
     */
    public static void startLoginActivity(Context context) {
        Intent intent = new Intent(context, FhLoginActivity.class);
        context.startActivity(intent);
    }


    public static void starthistoryActivity(Context context) {
        Intent intent = new Intent(context, FhInvestmentHistoryActivity.class);
        context.startActivity(intent);
    }
    public static void starthistoryActivity(Context context,int customid) {
        Intent intent = new Intent(context, FhInvestmentHistoryActivity.class);
        intent.putExtra("customid", customid);
        context.startActivity(intent);
    }

    public static void startServationActivity(Context context, int uid,String name) {
        Intent intent = new Intent(context, CustomerReservedProductActivity.class);
        intent.putExtra("uid", uid);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }
    public static void startServationActivity(Context context, int uid) {
        Intent intent = new Intent(context, CustomerReservedProductActivity.class);
        intent.putExtra("uid", uid);
        context.startActivity(intent);
    }
    public static void startCustomerDetail(Context context, MemberModel model) {
        Intent intent = new Intent(context, CustomerDetailActivity.class);
        intent.putExtra("model", model);
        context.startActivity(intent);
    }
    public static void startCustomerNewDetail(Context context, MemberModel model) {
        Intent intent = new Intent(context, CustomerDetailNewActivity.class);
        intent.putExtra("model", model);
        context.startActivity(intent);
    }
    public static void startClientList(Context context, String type, int typeId) {
        Intent intent = new Intent(context, ClientSelectedListActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("typeId", typeId);
        context.startActivity(intent);
    }


    /**
     * 我的理财师
     *
     * @param context
     */
    public static void startAccountionDetailActivity(Context context,boolean showContactPlanner) {
        Intent intent = new Intent(context, FhMyAccountantDetailActivity.class);
        intent.putExtra("showContactPlanner",showContactPlanner);
        context.startActivity(intent);
    }

    public static void startsureDetailActivity(Activity context, RightModel rightmodel, int flag) {
        Intent intent = new Intent(context, FhRightsPopdialogActivity.class);
        intent.putExtra("rightmodel", rightmodel);
        context.startActivityForResult(intent, flag);
    }


    public static void startChangeLoginNamelActivity(Context context, String name) {
        Intent intent = new Intent(context, FhChangeLoginNameActivity.class);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

    public static void startchangePasswordActivity(Context context, String password) {
        Intent intent = new Intent(context, FhChangePasswordAgainActivity.class);
        intent.putExtra("password", password);
        context.startActivity(intent);
    }

    /**
     * 到账消息
     *
     * @param context
     */
    public static void startAccountInformationActivity(Context context,int customerid) {
        Intent intent = new Intent(context, FhAccountInformationActivity.class);
        intent.putExtra("customerid", customerid);
        context.startActivity(intent);
    }

    /**
     * 更改密码
     *
     * @param context
     */
    public static void startChangePassActivity(Context context) {
        Intent intent = new Intent(context, FhChangePasswordActivity.class);
        context.startActivity(intent);
    }

    /**
     * 历月业绩
     *
     * @param context
     */
    public static void startMonthActivity(Context context, String title) {
        Intent intent = new Intent(context, FhMonthRankActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    /**
     * 历月业绩
     *
     * @param context
     */
    public static void startYearActivity(Context context) {
        Intent intent = new Intent(context, FhYearRankActivity.class);
        context.startActivity(intent);
    }

    /**
     * 个人信息
     *
     * @param context
     */
    public static void startPersonInformationActivity(Activity context) {
        Intent intent = new Intent(context, FhPersonInformationActivity.class);
        context.startActivity(intent);
    }

    /**
     * 备注
     *
     * @param context
     */
    public static void startRemarkActivity(Context context, MemberModel model) {
        Intent intent = new Intent(context, FhPersonRemarkActivity.class);
        intent.putExtra("model", model);
        context.startActivity(intent);
    }

    /**
     * 业绩查询
     *
     * @param context
     */
    public static void startRecordSearchActivity(Context context) {
        Intent intent = new Intent(context, FhRecordSearchActivity.class);
        context.startActivity(intent);
    }

    /**
     * 我的工作
     *@deprecated
     * @param context
     */
    public static void startMyWorkActivity(Context context) {
        Intent intent = new Intent(context, MyWorkActivity.class);
        context.startActivity(intent);
    }

    /**
     * 我的客户
     *
     * @param context
     */
    public static void startMyClientActivity(Context context) {
        Intent intent = new Intent(context, ClientListActivity.class);
        context.startActivity(intent);
    }

    /**
     * 风险评测
     *
     * @param context
     */
    public static void startRiskAssessment(Activity context) {
        Intent intent = new Intent(context, RiskAssessmentActivity.class);
        context.startActivity(intent);
    }

    /**
     * 关于
     *
     * @param context
     */
    public static void startAboutActivity(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }
    /**
     * 声明
     *
     * @param context
     */
    public static void startshengActivity(Context context) {
        Intent intent = new Intent(context, FhServiceActivity.class);
        context.startActivity(intent);
    }
    /**
     * 积分规则
     *
     * @param context
     */
    public static void startPointActivity(Context context) {
        Intent intent = new Intent(context, FhVipActivity.class);
        context.startActivity(intent);
    }
    /**
     * 联系我们
     *
     * @param context
     */
    public static void startContactUs(Context context) {
        Intent intent = new Intent(context, ContactUsActivity.class);
        context.startActivity(intent);
    }

    /**
     * 编辑关注
     *
     * @param context
     */
    public static void startEditCollect(Context context, CollectMapModel list, int position) {
        Intent intent = new Intent(context, EditCollectActivity.class);
        intent.putExtra("model", list);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    /**
     * 编辑关注
     *
     * @param context
     */
    public static void startFeedback(Context context) {
        Intent intent = new Intent(context, FeedbackActivity.class);

        context.startActivity(intent);
    }

    /**
     * 启动动画
     *
     * @param context
     */
    public static void startLoadingAnim(Context context, boolean isLogin) {
        Intent intent = new Intent(context, AnimActivity.class);
        intent.putExtra("isLogin", isLogin);
        context.startActivity(intent);
    }

    /**
     * 反馈详细
     *
     * @param context
     */

    public static void startFeedBackDetail(Context context ,String type) {
        Intent intent = new Intent(context, FeedbackDetailActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    /**
     * 详情大图界面
     *
     * @param context
     */

    public static void startScreenFullActivity(Context context,String url) {
        Intent intent = new Intent(context, FullScreenImageActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    /****
     * 打开选择图片的页面
     *
     * @param activity
     * @param mSelectPath
     */
    public static void openImageChooseActivity(Activity activity, ArrayList<String> mSelectPath) {
        Intent intent = new Intent(activity, MultiImageSelectorActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大可选择图片数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 4);
        // 选择模式
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        // 默认选择
        if (mSelectPath != null && mSelectPath.size() > 0) {
            intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);
        }


        activity.startActivityForResult(intent, MultiImageSelectorActivity.REQUEST_CODE);
    }

}
