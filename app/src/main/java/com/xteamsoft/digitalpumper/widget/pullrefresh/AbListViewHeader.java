package com.xteamsoft.digitalpumper.widget.pullrefresh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.utils.DisplayUtil;
import com.xteamsoft.digitalpumper.widget.ProgressWheel;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

public class AbListViewHeader extends LinearLayout {
    public static final int STATE_NORMAL = 0;

    public static final int STATE_READY = 1;

    public static final int STATE_REFRESHING = 2;

    public static final String dateFormatHMS = "HH:mm:ss";

    private final int ROTATE_ANIM_DURATION = 180;

    private Bitmap arrowImage = null;

    private ImageView arrowImageView;

    private int headerHeight;

    private ProgressWheel headerProgressBar;

    private TextView headerTimeView;

    private LinearLayout headerView;

    private String lastRefreshTime = null;

    private Context mContext;

    private Animation mRotateDownAnim;

    private Animation mRotateUpAnim;

    private int mState = -1;

    private TextView tipsTextview;

    public AbListViewHeader(Context paramContext) {
        super(paramContext);
        initView(paramContext);
    }

    public AbListViewHeader(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        initView(paramContext);
    }

    private void initView(Context paramContext) {
        this.mContext = paramContext;
        this.headerView = new LinearLayout(paramContext);
        this.headerView.setOrientation(LinearLayout.HORIZONTAL);
        this.headerView.setGravity(17);
        DisplayUtil.setPadding((View)this.headerView, 0, 10, 0, 10);
        FrameLayout frameLayout = new FrameLayout(paramContext);
        this.arrowImageView = new ImageView(paramContext);
        this.arrowImage = BitmapFactory.decodeResource(getResources(), R.mipmap.play_module_refresh);
        this.arrowImageView.setImageBitmap(this.arrowImage);
        this.headerProgressBar = new ProgressWheel(paramContext);
        this.headerProgressBar.setVisibility(View.GONE);
        this.headerProgressBar.spin();
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        layoutParams2.width = DisplayUtil.scaleValue(this.mContext, 25.0F);
        layoutParams2.height = DisplayUtil.scaleValue(this.mContext, 25.0F);
        frameLayout.addView((View)this.arrowImageView, (ViewGroup.LayoutParams)layoutParams2);
        frameLayout.addView((View)this.headerProgressBar, (ViewGroup.LayoutParams)layoutParams2);
        LinearLayout linearLayout2 = new LinearLayout(paramContext);
        this.tipsTextview = new TextView(paramContext);
        this.headerTimeView = new TextView(paramContext);
        linearLayout2.setOrientation(LinearLayout.VERTICAL);
        linearLayout2.setGravity(16);
        DisplayUtil.setPadding((View)linearLayout2, 0, 0, 0, 0);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        linearLayout2.addView((View)this.tipsTextview, (ViewGroup.LayoutParams)layoutParams3);
        linearLayout2.addView((View)this.headerTimeView, (ViewGroup.LayoutParams)layoutParams3);
        this.tipsTextview.setTextColor(Color.rgb(107, 107, 107));
        this.headerTimeView.setTextColor(Color.rgb(107, 107, 107));
        DisplayUtil.setTextSize(this.tipsTextview, 14.0F);
        DisplayUtil.setTextSize(this.headerTimeView, 14.0F);
        layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams3.gravity = 17;
        layoutParams3.rightMargin = DisplayUtil.scaleValue(this.mContext, 10.0F);
        LinearLayout linearLayout1 = new LinearLayout(paramContext);
        linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout1.setGravity(17);
        linearLayout1.addView((View)frameLayout, (ViewGroup.LayoutParams)layoutParams3);
        linearLayout1.addView((View)linearLayout2, (ViewGroup.LayoutParams)layoutParams3);
        LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams1.gravity = 80;
        this.headerView.addView((View)linearLayout1, (ViewGroup.LayoutParams)layoutParams1);
        addView((View)this.headerView, (ViewGroup.LayoutParams)layoutParams1);
        DisplayUtil.measureView((View)this);
        this.headerHeight = getMeasuredHeight();
        this.mRotateUpAnim = (Animation)new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
        this.mRotateUpAnim.setDuration(180L);
        this.mRotateUpAnim.setFillAfter(true);
        this.mRotateDownAnim = (Animation)new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
        this.mRotateDownAnim.setDuration(180L);
        this.mRotateDownAnim.setFillAfter(true);
        setState(0);
    }

    public ImageView getArrowImageView() {
        return this.arrowImageView;
    }

    public String getCurrentDate(String paramString) {
        try {
            return (new SimpleDateFormat(paramString, Locale.CHINA)).format((new GregorianCalendar()).getTime());
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public int getHeaderHeight() {
        return this.headerHeight;
    }

    public ProgressWheel getHeaderProgressBar() {
        return this.headerProgressBar;
    }

    public LinearLayout getHeaderView() {
        return this.headerView;
    }

    public int getState() {
        return this.mState;
    }

    public int getVisiableHeight() {
        return ((LinearLayout.LayoutParams)this.headerView.getLayoutParams()).height;
    }

    public void setArrowImage(int paramInt) {
        this.arrowImageView.setImageResource(paramInt);
    }

    public void setBackgroundColor(int paramInt) {
        this.headerView.setBackgroundColor(paramInt);
    }

    public void setHeaderProgressBarColor(int paramInt) {
        if (this.headerProgressBar != null)
            this.headerProgressBar.setBarColor(paramInt);
    }

    public void setRefreshTime(String paramString) {
        this.headerTimeView.setText(paramString);
    }

    public void setState(int paramInt) {
        if (paramInt == this.mState)
            return;
        if (paramInt == 2) {
            this.arrowImageView.clearAnimation();
            this.arrowImageView.setVisibility(View.INVISIBLE);
            this.headerProgressBar.setVisibility(View.VISIBLE);
        } else {
            this.arrowImageView.setVisibility(View.VISIBLE);
            this.headerProgressBar.setVisibility(View.INVISIBLE);
        }
        switch (paramInt) {
            default:
                this.mState = paramInt;
                return;
            case 0:
                if (this.mState == 1)
                    this.arrowImageView.startAnimation(this.mRotateDownAnim);
                if (this.mState == 2)
                    this.arrowImageView.clearAnimation();
                this.tipsTextview.setText("下拉刷新");
                if (this.lastRefreshTime == null) {
                    this.lastRefreshTime = getCurrentDate("HH:mm:ss");
                    this.headerTimeView.setText("刷新時間"+ this.lastRefreshTime);
                } else {
                    this.headerTimeView.setText("上次刷新时间"+ this.lastRefreshTime);
                }
            case 1:
                if (this.mState != 1) {
                    this.arrowImageView.clearAnimation();
                    this.arrowImageView.startAnimation(this.mRotateUpAnim);
                    this.tipsTextview.setText("松开时间");
                    this.headerTimeView.setText("上次刷新时间"+ this.lastRefreshTime);
                    this.lastRefreshTime = getCurrentDate("HH:mm:ss");
                }
            case 2:
                break;
        }
        this.tipsTextview.setText("正在刷新...");
        this.headerTimeView.setText("本次刷新时间"+ this.lastRefreshTime);
    }

    public void setStateTextSize(int paramInt) {
        this.tipsTextview.setTextSize(paramInt);
    }

    public void setTextColor(int paramInt) {
        this.tipsTextview.setTextColor(paramInt);
        this.headerTimeView.setTextColor(paramInt);
    }

    public void setTimeTextSize(int paramInt) {
        this.headerTimeView.setTextSize(paramInt);
    }

    public void setVisiableHeight(int paramInt) {
        int i = paramInt;
        if (paramInt < 0)
            i = 0;
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)this.headerView.getLayoutParams();
        layoutParams.height = i;
        this.headerView.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
    }
}
