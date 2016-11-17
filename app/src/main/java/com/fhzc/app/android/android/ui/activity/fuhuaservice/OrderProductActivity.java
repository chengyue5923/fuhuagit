package com.fhzc.app.android.android.ui.activity.fuhuaservice;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.platform.utils.android.ToastTool;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.dialog.ProduceSuccessReservationDialog;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.controller.ProductController;
import com.fhzc.app.android.db.UserPreference;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fw on 16/11/3.
 */

public class OrderProductActivity extends BaseActivity {
    @Bind(R.id.titleBar)
    CommonToolBar titleBar;
    @Bind(R.id.tv_abbreviation)
    TextView tvAbbreviation;
    @Bind(R.id.tv_annualyield)
    TextView tvAnnualyield;
    @Bind(R.id.tv_qitoujine)
    TextView tvQitoujine;
    @Bind(R.id.ll_summary)
    LinearLayout llSummary;
    @Bind(R.id.tv_order)
    TextView tvOrder;
    @Bind(R.id.inputNUmberText)
    EditText inputNUmberText;

    private String suoxie, annualInterval;
    private double investThresHold;
    private int productId;


    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {
        titleBar.setTitle("预约产品");
        suoxie = getIntent().getStringExtra("suoxie");
        annualInterval = getIntent().getStringExtra("annualInterval");
        investThresHold = getIntent().getDoubleExtra("investThresHold", 100d);
        productId = getIntent().getIntExtra("productId", -1);

        tvAbbreviation.setText(suoxie);
        tvAnnualyield.setText("参考业绩标准 "+annualInterval);
        tvQitoujine.setText(String.valueOf("起投金额 "+investThresHold));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_orderproduct;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {
        if (flag == HttpConfig.orderProduct.getType()) {
//            boolean success = false;
            try {
                JSONObject object = new JSONObject(o.toString());
                if (object.getInt("code") == 200) {
                    ReservaSuccessPopUpDialog();
//                    success = true;
                } else {
                    ToastTool.show(object.getString("message"));
                }
            } catch (Exception e) {
                ToastTool.show("预约失败");
            }

//            Intent intent = new Intent();
//            intent.putExtra("success",success);
//            setResult(Activity.RESULT_OK,intent);

        }
    }

    public void ReservaSuccessPopUpDialog() {   //预约商品成功
        ProduceSuccessReservationDialog PopUpDialog = new ProduceSuccessReservationDialog(this);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        PopUpDialog.show(fragmentTransaction, "ProduceSuccessReservationDialog");
    }


    @OnClick({R.id.titleBar, R.id.tv_abbreviation, R.id.tv_annualyield, R.id.tv_qitoujine, R.id.ll_summary,R.id.tv_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titleBar:
                break;
            case R.id.tv_abbreviation:
                break;
            case R.id.tv_annualyield:
                break;
            case R.id.tv_qitoujine:
                break;
            case R.id.ll_summary:
                break;
            case R.id.tv_order:
                String amount = inputNUmberText.getText().toString();

                if (amount.equals("")) {
                    ToastTool.show("请输入预约金额");
                    return;
                }
                int qiTouJinE = Integer.parseInt(inputNUmberText.getText().toString()) * 10000;
                if (qiTouJinE < investThresHold) {
                    ToastTool.show("金额不能少于起投金额");
                    return;
                }
                ProductController.getInstance().orderProduct(this, productId, UserPreference.getRoleId(), UserPreference.getPlannerId(), qiTouJinE);
                break;
        }
    }

    @OnClick(R.id.inputNUmberText)
    public void onClick() {
    }
}
