package com.fhzc.app.android.android.ui.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;


public class PullableScrollView extends ScrollView implements Pullable {
    private Context context;

    public PullableScrollView(Context context) {
        super(context);
        this.context = context;
    }

    public PullableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public PullableScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    @Override
    public boolean canPullDown() {
        if (getScrollY() == 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean canPullUp() {
		if (getScrollY() >= (getChildAt(0).getHeight() - getMeasuredHeight()))
			return true;
		else
			return false;
//        return context instanceof AccountCouponActivity;
    }

}
