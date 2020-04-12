package com.xteamsoft.digitalpumper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

public class SyncHorizontalScrollView extends HorizontalScrollView {
    private View mView;

    public SyncHorizontalScrollView(Context paramContext) {
        super(paramContext);
    }

    public SyncHorizontalScrollView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
    }

    protected void onScrollChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onScrollChanged(paramInt1, paramInt2, paramInt3, paramInt4);
        if (this.mView != null)
            this.mView.scrollTo(paramInt1, paramInt2);
    }

    public void setScrollView(View paramView) {
        this.mView = paramView;
    }
}
