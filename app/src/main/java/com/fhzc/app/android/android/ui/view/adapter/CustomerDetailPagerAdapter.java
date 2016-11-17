package com.fhzc.app.android.android.ui.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fhzc.app.android.android.ui.fragment.CustomerInfoFragment;
import com.fhzc.app.android.android.ui.fragment.CustomerOrderFragment;
import com.fhzc.app.android.android.ui.fragment.CustomerWealthFragment;
import com.fhzc.app.android.models.MemberModel;

/**
 * Created by User on 2016/7/21.
 */
public class CustomerDetailPagerAdapter extends FragmentPagerAdapter {
    private String[] title;
    private CustomerWealthFragment customerWealthFragment;
    private CustomerOrderFragment customerOrderFragment;
    private CustomerInfoFragment customerInfoFragment;
    private MemberModel model;

    public CustomerDetailPagerAdapter(FragmentManager fm, String[] title, MemberModel model) {
        super(fm);
        this.title = title;
        this.model = model;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (customerWealthFragment == null)
                    customerWealthFragment = CustomerWealthFragment.newInstance(model);
                return customerWealthFragment;
            case 1:
                if (customerOrderFragment == null)
                    customerOrderFragment = CustomerOrderFragment.newInstance(model);
                return customerOrderFragment;
            case 2:
                if (customerInfoFragment == null)
                    customerInfoFragment = CustomerInfoFragment.newInstance(model);
                return customerInfoFragment;
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
