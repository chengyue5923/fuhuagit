package com.fhzc.app.android.android.ui.view.widget.im;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.fhzc.app.android.R;


public class MessageSendViewToolbar extends FrameLayout {
    private ImageButton mSwitchButton;
    private ViewGroup mContentView;
    private View mDivider;
    private boolean mShowDivider = false;

    public MessageSendViewToolbar(Context context) {
        super(context);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_conversation_toolbar, this);

        setSwitchButton((ImageButton) findViewById(R.id.toolbar_switch_btn));
        setContentView((ViewGroup) findViewById(R.id.toolbar_content));
        mDivider = findViewById(R.id.toolbar_divider);
    }

    public ImageButton getSwitchButton() {
        return mSwitchButton;
    }

    public void setSwitchButton(ImageButton switchButton) {
        mSwitchButton = switchButton;
    }

    public ViewGroup getContentView() {
        return mContentView;
    }

    public void setContentView(ViewGroup contentView) {
        mContentView = contentView;
    }

    public void show(boolean showOrHidden) {
        if (showOrHidden) {
            setVisibility(View.VISIBLE);
        } else {
            if (mShowDivider) {
                setVisibility(View.GONE);
            } else {
                setVisibility(View.INVISIBLE);
            }
        }
    }

    public void showDivider(boolean showOrHidden) {
        mShowDivider = showOrHidden;
        if (showOrHidden) {
            mDivider.setVisibility(View.VISIBLE);
        } else {
            mDivider.setVisibility(View.GONE);
        }
    }

    public void hideSwitchButton() {
        mSwitchButton.setVisibility(View.GONE);
    }
}
