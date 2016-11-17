package com.fhzc.app.android.android.ui.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fhzc.app.android.android.ui.fragment.MyOrderRightFragment;
import com.fhzc.app.android.configer.enums.MyOrderRightType;
import com.fhzc.app.android.models.OrderRightModel;

import java.util.HashMap;
import java.util.List;

/**
 * Created by User on 2016/7/21.
 */
public class MyOrderRightPagerAdapter extends FragmentPagerAdapter {
    private String[] title;
    private MyOrderRightFragment firstRightFragment;
    private MyOrderRightFragment secondRightFragment;
    private MyOrderRightFragment thirdRightFragment;

    public void setData(HashMap<Integer, List<OrderRightModel>> map) {
        firstRightFragment.setRightModels(map);
        secondRightFragment.setRightModels(map);
        thirdRightFragment.setRightModels(map);
    }

    public MyOrderRightPagerAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.title = title;
        firstRightFragment = MyOrderRightFragment.newInstance(MyOrderRightType.ORDER_RIGHT_TYPE_ORDER.getType(),false);
        firstRightFragment.setStatus(MyOrderRightType.ORDER_RIGHT_TYPE_ORDER.getType());
        secondRightFragment = MyOrderRightFragment.newInstance(MyOrderRightType.ORDER_RIGHT_TYPE_SUCCESS.getType(),false);
        secondRightFragment.setStatus(MyOrderRightType.ORDER_RIGHT_TYPE_SUCCESS.getType());
        thirdRightFragment = MyOrderRightFragment.newInstance(MyOrderRightType.ORDER_RIGHT_TYPE_FINISH.getType(),true);
        thirdRightFragment.setStatus(MyOrderRightType.ORDER_RIGHT_TYPE_FINISH.getType());
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (null == firstRightFragment)
                    firstRightFragment = MyOrderRightFragment.newInstance(MyOrderRightType.ORDER_RIGHT_TYPE_ORDER.getType(),false);
                return firstRightFragment;
            case 1:
                if (null == secondRightFragment)
                    secondRightFragment = MyOrderRightFragment.newInstance(MyOrderRightType.ORDER_RIGHT_TYPE_SUCCESS.getType(),false);
                return secondRightFragment;
            case 2:
                if (null == thirdRightFragment)
                    thirdRightFragment = MyOrderRightFragment.newInstance(MyOrderRightType.ORDER_RIGHT_TYPE_FINISH.getType(),true);
                return thirdRightFragment;


        }
        return firstRightFragment;
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
