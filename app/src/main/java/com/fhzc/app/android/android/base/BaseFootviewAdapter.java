package com.fhzc.app.android.android.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.platform.utils.android.Logger;
import com.fhzc.app.android.R;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseFootviewAdapter<E> extends BaseAdapter {

    public static final int DEFAULT_SHOW_COUNT = 2;

    protected Context mContext;
    protected ListView mListView;
    protected LayoutInflater inflater;
    protected LinearLayout headView;
    protected RelativeLayout btn_loadmore;
    protected List<E> mShowObjects = new ArrayList<E>();
    protected List<E> mAllObjects = null;
    protected boolean shrink = true;
    protected TextView text_loadmore;

    @SuppressWarnings("unused")
    private BaseFootviewAdapter() {
    }

    @SuppressLint("InflateParams")
    public BaseFootviewAdapter(Context mContext, ListView mListView) {
        this.mContext = mContext;
        this.mListView = mListView;
        inflater = LayoutInflater.from(mContext);
        headView = (LinearLayout) inflater.inflate(R.layout.view_footer_button, null);
        btn_loadmore = (RelativeLayout) headView.findViewById(R.id.btn_loadmore);
        text_loadmore = (TextView) headView.findViewById(R.id.text_loadmore);
        btn_loadmore.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                changeShow();
            }
        });

    }

    public void setAdapterData(List<E> mAllObjects) {
        this.mAllObjects = mAllObjects;
        mShowObjects.clear();
        if (mAllObjects != null) {
            if (mAllObjects.size() <= DEFAULT_SHOW_COUNT) {
                mShowObjects.addAll(mAllObjects);
            } else {
                mListView.addFooterView(headView, null, false);
                headView.setVisibility(View.VISIBLE);
                for (int i = 0; i < DEFAULT_SHOW_COUNT; i++) {
                    mShowObjects.add(mAllObjects.get(i));
                }
            }
        }
        notifyDataSetChanged();
//        setListViewHeightBasedOnChildren(mListView);
    }

    @Override
    public int getCount() {
        int showCount = 0;
        if (mShowObjects != null) {
            showCount = mShowObjects.size();
        }
        return showCount;
    }

    @Override
    public E getItem(int position) {
        E object = null;
        if (mShowObjects != null) {
            object = mShowObjects.get(position);
        }
        return object;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void changeShow() {
        try{
            if (headView.getVisibility() == View.GONE) {
                headView.setVisibility(View.VISIBLE);
            }
            mShowObjects.clear();

            Logger.e("e,get"+shrink);
            if (shrink) {
                shrink = false;
                mShowObjects.addAll(mAllObjects);
                text_loadmore.setText("收起");
            } else {
                shrink = true;
                for (int i = 0; i < DEFAULT_SHOW_COUNT; i++) {
                    mShowObjects.add(mAllObjects.get(i));
                }
                text_loadmore.setText("展开");
            }
            notifyDataSetChanged();
//            setListViewHeightBasedOnChildren(mListView);
        }catch (Exception e){
            Logger.e("exception"+e.getMessage());
        }
    }

    /**
     * 当ListView外层有ScrollView时，需要动态设置ListView高度
     *
     * @param listView
     */
    protected void setListViewHeightBasedOnChildren(ListView listView) {
        if (listView == null) return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}