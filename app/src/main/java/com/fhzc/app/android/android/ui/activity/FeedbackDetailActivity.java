package com.fhzc.app.android.android.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.base.framwork.cach.preferce.PreferceManager;
import com.base.framwork.net.configer.Constans;
import com.base.platform.utils.android.ToastTool;
import com.base.platform.utils.java.StringTools;
import com.base.view.view.dialog.factory.DialogFacory;
import com.fhzc.app.android.R;
import com.fhzc.app.android.android.base.BaseActivity;
import com.fhzc.app.android.android.ui.view.adapter.GridAdapter;
import com.fhzc.app.android.android.ui.view.widget.CommonToolBar;
import com.fhzc.app.android.android.ui.view.widget.EditTextMultiLine;
import com.fhzc.app.android.configer.UrlRes;
import com.fhzc.app.android.configer.enums.HttpConfig;
import com.fhzc.app.android.configer.enums.HttpManager;
import com.fhzc.app.android.models.HttpConfigBean;
import com.fhzc.app.android.utils.android.IntentTools;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by User on 2016/8/9.
 */
public class FeedbackDetailActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.systemMessageToolBar)
    CommonToolBar systemMessageToolBar;
    @Bind(R.id.titleEt)
    EditTextMultiLine titleEt;
    @Bind(R.id.mGridView)
    GridView mGridView;
    @Bind(R.id.imageCount)
    TextView imageCount;
    @Bind(R.id.feedbackTextView)
    TextView feedbackTextView;
    @Bind(R.id.editText)
    EditText editText;
    private GridAdapter gridAdapter;
    public static ArrayList<String> bmp;
    private String type = "";
    private Dialog dialog;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initEvent() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (arg2 == bmp.size()) {
                    IntentTools.openImageChooseActivity(FeedbackDetailActivity.this, bmp);
                }
            }
        });
        feedbackTextView.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {

                case MultiImageSelectorActivity.REQUEST_CODE:
                    List<String> selectImages = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    bmp.clear();
                    bmp.addAll(selectImages);
                    imageCount.setText(bmp.size() + "/4");
                    gridAdapter.setBitmaps(bmp);
                    break;

            }
        }
    }

    public void deleteImage(String path) {
        if (bmp.contains(path))
            bmp.remove(path);
        imageCount.setText(bmp.size() + "/4");
        gridAdapter.setBitmaps(bmp);
    }

    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        dialog = DialogFacory.getInstance().createRunDialog(this, "正在提交。。");
        bmp = new ArrayList<String>();
        gridAdapter = new GridAdapter(this, bmp, 4);
        mGridView.setAdapter(gridAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback_detail;
    }

    @Override
    public void onData(Serializable result, int flag, boolean fromNet, Object o, Map<String, Object> param) {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.feedbackTextView:
                if (StringTools.isNullOrEmpty(titleEt.getText().toString())){
                    ToastTool.show("请输入反馈内容");
                    return;
                }
                List<NameValuePair> httpParams = new ArrayList<>();
                httpParams.add(new BasicNameValuePair("type", type));
                httpParams.add(new BasicNameValuePair("content", titleEt.getText().toString()));
                httpParams.add(new BasicNameValuePair("mobile", editText.getText().toString()));
                HttpConfigBean config = HttpManager.getInstance().getConifgById(HttpConfig.suggestAdd);
                new UploadAnswerImageTask(httpParams).execute(bmp,
                        UrlRes.getInstance().getUrl() + config.getActioin());
                break;
        }
    }


    public class UploadAnswerImageTask extends AsyncTask<Object, Void, Integer> {
        JSONObject mJson;
        ResponseHandler mResponseHandler;
        List<NameValuePair> mHttpParams;

        public UploadAnswerImageTask(List<NameValuePair> params) {

            mHttpParams = params;
        }

        @Override
        protected void onPreExecute() {
            if (null != dialog && !dialog.isShowing()) {
                dialog.show();
            }
        }

        @Override
        protected Integer doInBackground(Object... params) {
            try {
                ArrayList<File> imgFiles = new ArrayList<File>();
                if (params[0] instanceof String) {
                    File f = new File((String) params[0]);
                    if (f.exists()) {
                        imgFiles.add(f);
                    }
                } else if (params[0] instanceof List) {
                    List<String> imgFns = (List<String>) params[0];
                    for (String fn : imgFns) {
                        File f = new File(fn);
                        if (f.exists()) {
                            imgFiles.add(f);
                        }
                    }
                }


                final String url = (String) params[1];
                PostMethod method = new PostMethod(url);
                method.addRequestHeader("ACCEPT", "application/json");

                if (!StringTools.isNullOrEmpty(PreferceManager.getInsance().getValueBYkey(Constans.jseesion))) {
                    String session = PreferceManager.getInsance().getValueBYkey(Constans.jseesion);
                    method.addRequestHeader("Cookie", "JSESSIONID=" + session);
                    com.base.platform.utils.android.Logger.e("session = " + session);
                }


                HttpClient client = new HttpClient();
                client.getHttpConnectionManager().getParams().setConnectionTimeout(100000);
                Part[] parts = new Part[mHttpParams.size() + imgFiles.size()];

                for (int i = 0; i < mHttpParams.size(); ++i) {
                    NameValuePair pair = mHttpParams.get(i);
                    parts[i] = new StringPart(pair.getName(), pair.getValue(), "UTF-8");
                }

                for (int i = 0; i < imgFiles.size(); ++i) {
                    parts[mHttpParams.size() + i] = new FilePart("payload", "jpg", imgFiles.get(i), "image/jpeg", null);
                }

                method.setRequestEntity(new MultipartRequestEntity(parts, method.getParams()));
                client.executeMethod(method);
                String responseString = method.getResponseBodyAsString();
                method.releaseConnection();

                if (responseString != null) {
                    mJson = new JSONObject(responseString);
                    return method.getStatusCode();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return HttpStatus.SC_BAD_REQUEST;
        }

        @Override
        protected void onPostExecute(Integer statusCode) {
            if (null != dialog && dialog.isShowing()) {
                dialog.dismiss();
            }
            if (statusCode == HttpStatus.SC_OK && mJson != null) {
                ToastTool.show("反馈成功");
                finish();
            } else if (mJson != null) {
                try {
                    ToastTool.show(mJson.getString("message"));
                } catch (Exception e) {
                }

            }
        }
    }
}
