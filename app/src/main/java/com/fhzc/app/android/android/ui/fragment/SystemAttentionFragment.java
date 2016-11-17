package com.fhzc.app.android.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.fhzc.app.android.R;
import com.fhzc.app.android.android.ui.view.adapter.AttentionProduceAdapter;
import com.fhzc.app.android.android.ui.view.adapter.AttentionReportAdapter;
import com.fhzc.app.android.models.ChoosePackageModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 *
 * 我的关注子Fragment
 * Created by lenovo on 2016/7/19.
 */
public class SystemAttentionFragment extends Fragment {
    private int index = 0;
    private LayoutInflater mInflater;

    AttentionProduceAdapter produceAdapter;  //理财产品adapter
    AttentionReportAdapter  reportAdapter;  //投研报告

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private int[] mImgIds;

    @Bind(R.id.collectionListView)
    ListView collectionListView;
    @Bind(R.id.system_page_gallery)
    LinearLayout system_page_gallery;

    public static SystemAttentionFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SystemAttentionFragment pageFragment = new SystemAttentionFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        mInflater = LayoutInflater.from(getActivity());
    }

    private void setLayoutData(){

        mImgIds = new int[]{R.mipmap.backimage,  R.mipmap.backimage, R.mipmap.backimage, R.mipmap.backimage, R.mipmap.backimage, R.mipmap.backimage, R.mipmap.backimage, R.mipmap.backimage, R.mipmap.backimage,R.mipmap.backimage, R.mipmap.backimage, R.mipmap.backimage};
        for (int i = 0; i < mImgIds.length; i++) {

            View view = mInflater.inflate(R.layout.activity_number_gallery_item,
                    system_page_gallery, false);
            view.setTag(i);
            ImageView img = (ImageView) view
                    .findViewById(R.id.id_number_gallery_item_image);
            img.setBackgroundResource(mImgIds[i]);
            system_page_gallery.addView(view);
        }



    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, null);
        ButterKnife.bind(this,view);

        produceAdapter=new AttentionProduceAdapter(getContext());
        reportAdapter=new AttentionReportAdapter(getContext());
        if(mPage==1){   //理财产品
            collectionListView.setVisibility(View.VISIBLE);
            system_page_gallery.setVisibility(View.GONE);
            List<ChoosePackageModel> mp=new ArrayList<>();
            ChoosePackageModel model1=new ChoosePackageModel();
            ChoosePackageModel model2=new ChoosePackageModel();
            ChoosePackageModel model3=new ChoosePackageModel();
            ChoosePackageModel model4=new ChoosePackageModel();
            ChoosePackageModel model5=new ChoosePackageModel();
            ChoosePackageModel model6=new ChoosePackageModel();
            ChoosePackageModel model7=new ChoosePackageModel();
            ChoosePackageModel model8=new ChoosePackageModel();
            ChoosePackageModel model9=new ChoosePackageModel();
            ChoosePackageModel model0=new ChoosePackageModel();
            mp.add(model1);
            mp.add(model2);
            mp.add(model3);
            mp.add(model4);
            mp.add(model5);
            mp.add(model6);
            mp.add(model7);
            mp.add(model9);
            mp.add(model8);
            mp.add(model0);
            produceAdapter.setRes(mp);
            collectionListView.setAdapter(produceAdapter);
        }else if(mPage==2){   // 投研报告
            collectionListView.setVisibility(View.VISIBLE);
            system_page_gallery.setVisibility(View.GONE);
            List<ChoosePackageModel> mp=new ArrayList<>();
            ChoosePackageModel model1=new ChoosePackageModel();
            ChoosePackageModel model2=new ChoosePackageModel();
            ChoosePackageModel model3=new ChoosePackageModel();
            ChoosePackageModel model4=new ChoosePackageModel();
            ChoosePackageModel model5=new ChoosePackageModel();
            ChoosePackageModel model6=new ChoosePackageModel();
            ChoosePackageModel model7=new ChoosePackageModel();
            ChoosePackageModel model8=new ChoosePackageModel();
            mp.add(model1);
            mp.add(model2);
            mp.add(model3);
            mp.add(model4);
            mp.add(model5);
            mp.add(model6);
            mp.add(model7);
            mp.add(model8);
            reportAdapter.setRes(mp);
            collectionListView.setAdapter(reportAdapter);
        }else if(mPage==3){   // 活动列表
            collectionListView.setVisibility(View.GONE);
            system_page_gallery.setVisibility(View.VISIBLE);

            setLayoutData();
        }else{    // 权益列表
            collectionListView.setVisibility(View.VISIBLE);
            system_page_gallery.setVisibility(View.GONE);
            List<ChoosePackageModel> mp=new ArrayList<>();
            ChoosePackageModel model1=new ChoosePackageModel();
            ChoosePackageModel model2=new ChoosePackageModel();
            ChoosePackageModel model3=new ChoosePackageModel();
            ChoosePackageModel model4=new ChoosePackageModel();
            ChoosePackageModel model5=new ChoosePackageModel();
            mp.add(model1);
            mp.add(model2);
            mp.add(model3);
            mp.add(model4);
            mp.add(model5);
            reportAdapter.setRes(mp);
            collectionListView.setAdapter(reportAdapter);

        }
        return view;
    }

}
