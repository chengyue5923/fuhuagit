package com.fhzc.app.android.android.ui.view.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fhzc.app.android.android.ui.fragment.MyCollectActivityFragment;
import com.fhzc.app.android.android.ui.fragment.MyCollectProductFragment;
import com.fhzc.app.android.android.ui.fragment.MyCollectReportFragment;
import com.fhzc.app.android.android.ui.fragment.MyCollectRightFragment;
import com.fhzc.app.android.models.CollectMapModel;

/**
 * 系统服务 我的关注Adapter
 * Created by lenovo on 2016/7/19.
 */
public class CustomerMyAttentionAdapter extends FragmentPagerAdapter {


    private String[] title;
    private MyCollectProductFragment productFragment;
    private MyCollectRightFragment rightFragment;
    private MyCollectActivityFragment activityFragment;
    private MyCollectReportFragment reportFragment;

    public void setData(CollectMapModel model) {
        productFragment.setList(model.getProduct());
        rightFragment.setList(model.getRights());
        activityFragment.setList(model.getActivity());
        reportFragment.setList(model.getReport());
    }

    public CollectMapModel getModelByPosition(int position) {
        CollectMapModel model = new CollectMapModel();
        switch (position) {
            case 0:
                model.setProduct(productFragment.getList());
                break;
            case 1:
                model.setReport(reportFragment.getmList());
                break;
            case 2:
                model.setActivity(activityFragment.getList());
                break;
            case 3:
                model.setRights(rightFragment.getRightModels());
        }
        return model;
    }

    public CustomerMyAttentionAdapter(FragmentManager fm, String[] title) {
        super(fm);

        this.title = title;
        productFragment = new MyCollectProductFragment();
        reportFragment = new MyCollectReportFragment();
        activityFragment = new MyCollectActivityFragment();
        rightFragment = new MyCollectRightFragment();

    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (null == productFragment)
                    productFragment = new MyCollectProductFragment();
                return productFragment;
            case 1:
                if (null == reportFragment)
                    reportFragment = new MyCollectReportFragment();
                return reportFragment;
            case 2:
                if (null == activityFragment)
                    activityFragment = new MyCollectActivityFragment();
                return activityFragment;
            case 3:
                if (null == rightFragment)
                    rightFragment = new MyCollectRightFragment();
                return rightFragment;

        }
        return null;

    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
