package com.fhzc.app.android.android.ui.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fhzc.app.android.android.ui.fragment.CustomerMyActivityFragment;
import com.fhzc.app.android.android.ui.fragment.CustomerMyRightAndPointFragment;
import com.fhzc.app.android.android.ui.fragment.CustomerMyWealthFragment;

/**
 * Created by lenovo on 2016/10/14.
 */
public class MineTabAdapter extends FragmentPagerAdapter {
    private String[] title;

    CustomerMyWealthFragment newCustomerFragment;
    CustomerMyRightAndPointFragment newPointFragment;
    CustomerMyActivityFragment newRightFragment;
    CustomerMyActivityFragment newActivityFragment;

    public MineTabAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.title=title;
        newCustomerFragment=new CustomerMyWealthFragment();
        newPointFragment=new CustomerMyRightAndPointFragment();
        newRightFragment=new CustomerMyActivityFragment();
        newActivityFragment=new CustomerMyActivityFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                if (null == newCustomerFragment)
                    newCustomerFragment = new CustomerMyWealthFragment();
                return newCustomerFragment;
            case 1:
                if (null == newPointFragment)
                    newPointFragment = new CustomerMyRightAndPointFragment();
                return newPointFragment;
            case 2:
                if (null == newRightFragment)
                    newRightFragment = new CustomerMyActivityFragment();
                return newRightFragment;
            case 3:
                if (null == newActivityFragment)
                    newActivityFragment = new CustomerMyActivityFragment();
                return newActivityFragment;
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
