package com.xteamsoft.digitalpumper.widget.pullrefresh;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.xteamsoft.digitalpumper.widget.ProgressWheel;

public class AbPullToRefreshView extends LinearLayout {
    private static final int PULL_DOWN_STATE = 1;

    private static final int PULL_UP_STATE = 0;

    private AdapterView<?> mAdapterView;

    private Context mContext = null;

    private int mCount = 0;

    private boolean mEnableLoadMore = true;

    private boolean mEnablePullRefresh = true;

    private AbListViewFooter mFooterView;

    private int mFooterViewHeight;

    private AbListViewHeader mHeaderView;

    private int mHeaderViewHeight;

    private int mLastMotionX;

    private int mLastMotionY;

    private OnFooterLoadListener mOnFooterLoadListener;

    private OnHeaderRefreshListener mOnHeaderRefreshListener;

    private boolean mPullLoading = false;

    private boolean mPullRefreshing = false;

    private int mPullState;

    private ScrollView mScrollView;

    public AbPullToRefreshView(Context paramContext) {
        super(paramContext);
        init(paramContext);
    }

    public AbPullToRefreshView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init(paramContext);
    }

    private void addFooterView() {
        this.mFooterView = new AbListViewFooter(this.mContext);
        this.mFooterViewHeight = this.mFooterView.getFooterHeight();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.mFooterViewHeight);
        addView((View) this.mFooterView, (ViewGroup.LayoutParams) layoutParams);
    }

    private void addHeaderView() {
        this.mHeaderView = new AbListViewHeader(this.mContext);
        this.mHeaderViewHeight = this.mHeaderView.getHeaderHeight();
        this.mHeaderView.setGravity(80);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, this.mHeaderViewHeight);
        layoutParams.topMargin = -this.mHeaderViewHeight;
        addView((View) this.mHeaderView, (ViewGroup.LayoutParams) layoutParams);
    }

    private void footerLoading() {
        this.mPullLoading = true;
        setHeaderTopMargin(-(this.mHeaderViewHeight + this.mFooterViewHeight));
        if (this.mOnFooterLoadListener != null)
            this.mOnFooterLoadListener.onFooterLoad(this);
    }

    private void footerPrepareToRefresh(int paramInt) {
        if (!this.mPullRefreshing && !this.mPullLoading) {
            paramInt = updateHeaderViewTopMargin(paramInt);
            if (Math.abs(paramInt) >= this.mHeaderViewHeight + this.mFooterViewHeight && this.mFooterView.getState() != 2) {
                this.mFooterView.setState(1);
                return;
            }
            if (Math.abs(paramInt) < this.mHeaderViewHeight + this.mFooterViewHeight) {
                this.mFooterView.setState(2);
                return;
            }
        }
    }

    private int getHeaderTopMargin() {
        return ((LinearLayout.LayoutParams) this.mHeaderView.getLayoutParams()).topMargin;
    }

    private void headerPrepareToRefresh(int paramInt) {
        if (!this.mPullRefreshing && !this.mPullLoading) {
            paramInt = updateHeaderViewTopMargin(paramInt);
            if (paramInt >= 0 && this.mHeaderView.getState() != 2) {
                this.mHeaderView.setState(1);
                return;
            }
            if (paramInt < 0 && paramInt > -this.mHeaderViewHeight) {
                this.mHeaderView.setState(0);
                return;
            }
        }
    }

    private void init(Context paramContext) {
        this.mContext = paramContext;
        setOrientation(LinearLayout.VERTICAL);
        addHeaderView();
    }

    private void initContentAdapterView() {
        int j = getChildCount();
        if (j < 3)
            throw new IllegalArgumentException("this layout must contain 3 child views,and AdapterView or ScrollView must in the second position!");
        for (int i = 0; i < j - 1; i++) {
            View view = getChildAt(i);
            if (view instanceof AdapterView)
                this.mAdapterView = (AdapterView) view;
            if (view instanceof ScrollView)
                this.mScrollView = (ScrollView) view;
        }
        if (this.mAdapterView == null && this.mScrollView == null)
            throw new IllegalArgumentException("must contain a AdapterView or ScrollView in this layout!");
    }

    private boolean isRefreshViewScroll(int paramInt) {
        if (!this.mPullRefreshing && !this.mPullLoading) {
            if (this.mAdapterView != null)
                if (paramInt > 0) {
                    if (this.mEnablePullRefresh) {
                        View view = this.mAdapterView.getChildAt(0);
                        if (view == null) {
                            this.mPullState = 1;
                            return true;
                        }
                        if (this.mAdapterView.getFirstVisiblePosition() == 0 && view.getTop() == 0) {
                            this.mPullState = 1;
                            return true;
                        }
                        int i = view.getTop();
                        int j = this.mAdapterView.getPaddingTop();
                        if (this.mAdapterView.getFirstVisiblePosition() == 0 && Math.abs(i - j) <= 11) {
                            this.mPullState = 1;
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else if (paramInt < 0) {
                    if (this.mEnableLoadMore) {
                        View view = this.mAdapterView.getChildAt(this.mAdapterView.getChildCount() - 1);
                        if (view == null) {
                            this.mPullState = 0;
                            return true;
                        }
                        if (view.getBottom() <= getHeight() && this.mAdapterView.getLastVisiblePosition() == this.mAdapterView.getCount() - 1) {
                            this.mPullState = 0;
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
            if (this.mScrollView != null) {
                View view = this.mScrollView.getChildAt(0);
                if (paramInt > 0 && this.mScrollView.getScrollY() == 0) {
                    if (this.mEnablePullRefresh) {
                        this.mPullState = 1;
                        return true;
                    }
                    return false;
                }
                if (paramInt < 0 && view.getMeasuredHeight() <= getHeight() + this.mScrollView.getScrollY() && this.mEnableLoadMore) {
                    this.mPullState = 0;
                    return true;
                }
            }
        }
        return false;
    }

    private void setFootTopMargin(int paramInt) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mFooterView.getLayoutParams();
        layoutParams.bottomMargin = paramInt;
        this.mFooterView.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
    }

    private void setHeaderFinishAnimation(int paramInt) {
        if (Build.VERSION.SDK_INT >= 11) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(new int[]{0, -this.mHeaderViewHeight});
            valueAnimator.setDuration(paramInt);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator param1ValueAnimator) {
                    if (Build.VERSION.SDK_INT >= 11) {
                        int i = ((Integer) param1ValueAnimator.getAnimatedValue()).intValue();
                        AbPullToRefreshView.this.setHeaderTopMargin(i);
                    }
                }
            });
            valueAnimator.start();
        }
    }

    private void setHeaderTopMargin(int paramInt) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mHeaderView.getLayoutParams();
        layoutParams.topMargin = paramInt;
        this.mHeaderView.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
    }

    private int updateHeaderViewTopMargin(int paramInt) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.mHeaderView.getLayoutParams();
        float f1 = layoutParams.topMargin;
        float f2 = paramInt;
        if (paramInt > 0 && this.mPullState == 0 && Math.abs(layoutParams.topMargin) <= this.mHeaderViewHeight)
            return layoutParams.topMargin;
        if (paramInt < 0 && this.mPullState == 1 && Math.abs(layoutParams.topMargin) >= this.mHeaderViewHeight)
            return layoutParams.topMargin;
        layoutParams.topMargin = (int) (f1 + f2 * 0.3F);
        this.mHeaderView.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        return layoutParams.topMargin;
    }

    public ProgressWheel getFooterProgressBar() {
        return this.mFooterView.getFooterProgressBar();
    }

    public AbListViewFooter getFooterView() {
        return this.mFooterView;
    }

    public ProgressWheel getHeaderProgressBar() {
        return this.mHeaderView.getHeaderProgressBar();
    }

    public AbListViewHeader getHeaderView() {
        return this.mHeaderView;
    }

    public void headerRefreshing() {
        this.mPullRefreshing = true;
        this.mHeaderView.setState(2);
        setHeaderTopMargin(0);
        if (this.mOnHeaderRefreshListener != null)
            this.mOnHeaderRefreshListener.onHeaderRefresh(this);
    }

    public boolean isEnableLoadMore() {
        return this.mEnableLoadMore;
    }

    public boolean isEnablePullRefresh() {
        return this.mEnablePullRefresh;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        addFooterView();
        initContentAdapterView();
    }

    public void onFooterLoadFinish() {
        setHeaderTopMargin(-this.mHeaderViewHeight);
        this.mHeaderView.setState(0);
        if (this.mAdapterView != null) {
            if (this.mAdapterView.getCount() > this.mCount) {
                this.mFooterView.setState(1);
            } else {
                this.mFooterView.setState(3);
            }
        } else {
            this.mFooterView.setState(1);
        }
        this.mPullLoading = false;
    }

    public void onHeaderRefreshFinish() {
        if (this.mHeaderView.getState() == 2) {
            setHeaderFinishAnimation(300);
            this.mHeaderView.setState(0);
            if (this.mAdapterView != null) {
                this.mCount = this.mAdapterView.getCount();
                if (this.mCount > 0) {
                    this.mFooterView.setState(1);
                } else {
                    this.mFooterView.setState(4);
                }
            } else {
                this.mFooterView.setState(1);
            }
            this.mPullRefreshing = false;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
        int i = (int) paramMotionEvent.getX();
        int k = (int) paramMotionEvent.getY();
        switch (paramMotionEvent.getAction()) {
            default:
                return false;
            case 0:
                this.mLastMotionX = i;
                this.mLastMotionY = k;
            case 2:
                break;
        }
        int j = this.mLastMotionX;
        k -= this.mLastMotionY;
        if (Math.abs(i - j) < Math.abs(k) && Math.abs(k) > 10 && isRefreshViewScroll(k))
            return true;
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent paramMotionEvent) {
        int j;
        int i = (int) paramMotionEvent.getY();
        switch (paramMotionEvent.getAction()) {
            default:
                return super.onTouchEvent(paramMotionEvent);
            case 2:
                j = i - this.mLastMotionY;
                if (this.mPullState == 1) {
                    headerPrepareToRefresh(j);
                } else if (this.mPullState == 0) {
                    footerPrepareToRefresh(j);
                }
                this.mLastMotionY = i;
            case 1:
            case 3:
                break;
        }
        i = getHeaderTopMargin();
        if (this.mPullState == 1) {
            if (i >= 0)
                headerRefreshing();
            setHeaderTopMargin(-this.mHeaderViewHeight);
        }
        if (this.mPullState == 0) {
            if (Math.abs(i) >= this.mHeaderViewHeight + this.mFooterViewHeight)
                footerLoading();
            setHeaderTopMargin(-this.mHeaderViewHeight);
        }
        return false;
    }

    public void setLoadMoreEnable(boolean paramBoolean) {
        this.mEnableLoadMore = paramBoolean;
    }

    public void setOnFooterLoadListener(OnFooterLoadListener paramOnFooterLoadListener) {
        this.mOnFooterLoadListener = paramOnFooterLoadListener;
    }

    public void setOnHeaderRefreshListener(OnHeaderRefreshListener paramOnHeaderRefreshListener) {
        this.mOnHeaderRefreshListener = paramOnHeaderRefreshListener;
    }

    public void setPullRefreshEnable(boolean paramBoolean) {
        this.mEnablePullRefresh = paramBoolean;
    }

    public static interface OnFooterLoadListener {
        void onFooterLoad(AbPullToRefreshView param1AbPullToRefreshView);
    }

    public static interface OnHeaderRefreshListener {
        void onHeaderRefresh(AbPullToRefreshView param1AbPullToRefreshView);
    }
}
