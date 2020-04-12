package com.xteamsoft.digitalpumper.widget.pullrefresh;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.utils.DisplayUtil;
import com.xteamsoft.digitalpumper.widget.ProgressWheel;

public class AbListViewFooter extends LinearLayout {
    public static final int STATE_EMPTY = 4;

    public static final int STATE_LOADING = 2;

    public static final int STATE_NO = 3;

    public static final int STATE_READY = 1;

    private int footerHeight;

    private ProgressWheel footerProgressBar;

    private TextView footerTextView;

    private LinearLayout footerView;

    private Context mContext;

    private int mState = -1;

    public AbListViewFooter(Context paramContext) {
        super(paramContext);
        initView(paramContext);
    }

    public AbListViewFooter(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        initView(paramContext);
        setState(1);
    }

    private void initView(Context paramContext) {
        this.mContext = paramContext;
        this.footerView = new LinearLayout(paramContext);
        this.footerView.setOrientation(LinearLayout.HORIZONTAL);
        this.footerView.setGravity(17);
        this.footerView.setMinimumHeight(DisplayUtil.scaleValue(this.mContext, 60.0F));
        this.footerTextView = new TextView(paramContext);
        this.footerTextView.setGravity(16);
        setTextColor(Color.rgb(107, 107, 107));
        DisplayUtil.setTextSize(this.footerTextView, 14.0F);
        DisplayUtil.setPadding((View)this.footerView, 0, 10, 0, 10);
        this.footerProgressBar = new ProgressWheel(paramContext);
        this.footerProgressBar.setVisibility(View.GONE);
        this.footerProgressBar.setBarColor(this.mContext.getResources().getColor(R.color.readed));
        this.footerProgressBar.spin();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        layoutParams.width = DisplayUtil.scaleValue(this.mContext, 25.0F);
        layoutParams.height = DisplayUtil.scaleValue(this.mContext, 25.0F);
        layoutParams.rightMargin = DisplayUtil.scaleValue(this.mContext, 10.0F);
        this.footerView.addView((View)this.footerProgressBar, (ViewGroup.LayoutParams)layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.footerView.addView((View)this.footerTextView, (ViewGroup.LayoutParams)layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        addView((View)this.footerView, (ViewGroup.LayoutParams)layoutParams);
        DisplayUtil.measureView((View)this);
        this.footerHeight = getMeasuredHeight();
    }

    public int getFooterHeight() {
        return this.footerHeight;
    }

    public ProgressWheel getFooterProgressBar() {
        return this.footerProgressBar;
    }

    public int getState() {
        return this.mState;
    }

    public int getVisiableHeight() {
        return ((LinearLayout.LayoutParams)this.footerView.getLayoutParams()).height;
    }

    public void hide() {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.footerView.getLayoutParams();
        layoutParams.height = 0;
        this.footerView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        this.footerView.setVisibility(View.GONE);
    }

    public void setBackgroundColor(int paramInt) {
        this.footerView.setBackgroundColor(paramInt);
    }

    public void setState(int paramInt) {
        if (paramInt == 1) {
            this.footerView.setVisibility(View.VISIBLE);
            this.footerTextView.setVisibility(View.VISIBLE);
            this.footerProgressBar.setVisibility(View.GONE);
            this.footerTextView.setText("载入更多");
        } else if (paramInt == 2) {
            this.footerView.setVisibility(View.VISIBLE);
            this.footerTextView.setVisibility(View.VISIBLE);
            this.footerProgressBar.setVisibility(View.VISIBLE);
            this.footerTextView.setText("正在加载...");
        } else if (paramInt == 3) {
            this.footerView.setVisibility(View.GONE);
            this.footerTextView.setVisibility(View.VISIBLE);
            this.footerProgressBar.setVisibility(View.GONE);
            this.footerTextView.setText("没有了");
        } else if (paramInt == 4) {
            this.footerView.setVisibility(View.GONE);
            this.footerTextView.setVisibility(View.GONE);
            this.footerProgressBar.setVisibility(View.GONE);
            this.footerTextView.setText("没有数据");
        }
        this.mState = paramInt;
    }

    public void setTextColor(int paramInt) {
        this.footerTextView.setTextColor(paramInt);
    }

    public void setTextSize(int paramInt) {
        this.footerTextView.setTextSize(paramInt);
    }

    public void setVisiableHeight(int paramInt) {
        int i = paramInt;
        if (paramInt < 0)
            i = 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.footerView.getLayoutParams();
        layoutParams.height = i;
        this.footerView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }

    public void show() {
        this.footerView.setVisibility(View.VISIBLE);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.footerView.getLayoutParams();
        layoutParams.height = -2;
        this.footerView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }
}
