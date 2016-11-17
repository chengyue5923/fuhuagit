package com.fhzc.app.android.android.ui.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fhzc.app.android.android.ui.fragment.RightActivityListNewFragment;
import com.fhzc.app.android.android.ui.fragment.RightListNewFragment;

/**
 * Created by lenovo on 2016/10/23.
 */
public class RightAndPointListAdapter extends FragmentPagerAdapter {
    private String[] title;
    RightListNewFragment rightListNewFragment;
    RightActivityListNewFragment rightActivityListNewFragment;
    public RightAndPointListAdapter(FragmentManager fm) {
        super(fm);
        rightListNewFragment=new RightListNewFragment();
        rightActivityListNewFragment=new RightActivityListNewFragment();
    }
    public RightAndPointListAdapter(FragmentManager fm,String[] title) {
        super(fm);
        this.title=title;
        rightListNewFragment=new RightListNewFragment();
        rightActivityListNewFragment=new RightActivityListNewFragment();
    }
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               if(rightListNewFragment!=null){
                   rightListNewFragment=new RightListNewFragment();
               }
                   return rightListNewFragment;
           case 1:
               if(rightActivityListNewFragment!=null){
                   rightActivityListNewFragment=new RightActivityListNewFragment();
               }
               return rightActivityListNewFragment;
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
