package com.xteamsoft.baselibrary.utils.PoptitleLt;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ActionItem {
    public Drawable mDrawable;

    public CharSequence mTitle;

    public ActionItem(Context paramContext, int paramInt1, int paramInt2) {
        this.mTitle = paramContext.getResources().getText(paramInt1);
        this.mDrawable = paramContext.getResources().getDrawable(paramInt2);
    }

    public ActionItem(Context paramContext, CharSequence paramCharSequence, int paramInt) {
        this.mTitle = paramCharSequence;
        this.mDrawable = paramContext.getResources().getDrawable(paramInt);
    }

    public ActionItem(Drawable paramDrawable, CharSequence paramCharSequence) {
        this.mDrawable = paramDrawable;
        this.mTitle = paramCharSequence;
    }
}
