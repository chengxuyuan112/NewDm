package com.xteamsoft.baselibrary.utils;

import android.content.Context;

public class Utils {
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Context getContext() {
        if (context != null)
            return context;
        throw new NullPointerException("u should init first");
    }

    public static void init(Context paramContext) {
        context = paramContext.getApplicationContext();
    }
}
