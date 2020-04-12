package com.xteamsoft.baselibrary.utils.PoptitleLt;

import android.content.Context;

public class Util {
    public static int dip2px(Context paramContext, float paramFloat) {
        return (int)((paramFloat * getScreenDensity(paramContext)) + 0.5D);
    }

    public static float getScreenDensity(Context paramContext) {
        return (paramContext.getResources().getDisplayMetrics()).density;
    }

    public static int getScreenHeight(Context paramContext) {
        return (paramContext.getResources().getDisplayMetrics()).heightPixels;
    }

    public static int getScreenWidth(Context paramContext) {
        return (paramContext.getResources().getDisplayMetrics()).widthPixels;
    }
}
