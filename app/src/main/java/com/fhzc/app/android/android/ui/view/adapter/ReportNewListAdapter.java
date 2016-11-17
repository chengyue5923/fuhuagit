package com.fhzc.app.android.android.ui.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fhzc.app.android.android.ui.fragment.ReportNewListDetailFragment;
import com.fhzc.app.android.android.ui.fragment.ReportNewListFinancialDetailFragment;
import com.fhzc.app.android.android.ui.fragment.ReportNewListSearchDetailFragment;
import com.fhzc.app.android.android.ui.fragment.ReportNewListWeekDetailFragment;
import com.fhzc.app.android.models.ReportTypeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016/7/21.
 */
public class ReportNewListAdapter extends FragmentPagerAdapter {
    private String[] title;
    private String[] cid;
    ReportNewListDetailFragment adjustFragment;
    ReportNewListFinancialDetailFragment financialFragment;
    ReportNewListWeekDetailFragment  reportWeekFragment;
    ReportNewListSearchDetailFragment searchFragment;

    List<ReportTypeModel> model=new ArrayList<>();
    public ReportNewListAdapter(FragmentManager fm, String[] title,  List<ReportTypeModel> model) {
        super(fm);
        this.title = title;
        this.model=model;
    }
    public void setModel(List<ReportTypeModel> model){
        this.model=model;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (null == financialFragment)
                    financialFragment = ReportNewListFinancialDetailFragment.newInstance(model.get(position).getId());
                return financialFragment;
            case 1:
                return reportWeekFragment == null ? ReportNewListWeekDetailFragment.newInstance(model.get(position).getId()): reportWeekFragment;
            case 2:
                return searchFragment == null ?   ReportNewListSearchDetailFragment.newInstance(model.get(position).getId()) : searchFragment;
            case 3:
                return adjustFragment == null ?   ReportNewListDetailFragment.newInstance(model.get(position).getId()) : adjustFragment;
        }
        return null;
    }
    @Override
    public int getCount() {
        return title.length;
    }

//    @Override
//    public long getItemId(int position) {
//        return super.getItemId(position);
//    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
