package com.fhzc.app.android.android.ui.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fhzc.app.android.android.ui.fragment.CustomerMyActivityFragment;
import com.fhzc.app.android.android.ui.fragment.CustomerMyRightAndPointFragment;
import com.fhzc.app.android.android.ui.fragment.CustomerMyRightFragment;
import com.fhzc.app.android.android.ui.fragment.CustomerMyWealthFragment;

/**
 * Created by User on 2016/7/21.
 */
public class CustomerMinePagerAdapter extends FragmentPagerAdapter {
    private String[] title;
    private CustomerMyWealthFragment wealthFragment;
    private CustomerMyRightAndPointFragment rightAndPointFragment;
    private CustomerMyRightFragment rightFragment;
    private CustomerMyActivityFragment activityFragment;

    public CustomerMinePagerAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.title = title;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return wealthFragment == null ? new CustomerMyWealthFragment() : wealthFragment;

            case 1:
                return rightAndPointFragment == null ? new CustomerMyRightAndPointFragment() : rightAndPointFragment;

            case 2:
                return rightFragment == null ? new CustomerMyRightFragment() : rightFragment;
            case 3:
                return activityFragment == null ? new CustomerMyActivityFragment() : activityFragment;

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
