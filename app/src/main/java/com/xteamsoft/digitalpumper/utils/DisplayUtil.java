package com.xteamsoft.digitalpumper.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayUtil {
    public static final Double BASE_SCREEN_HEIGHT;

    public static final Double BASE_SCREEN_WIDTH = Double.valueOf(320.0D);

    static {
        BASE_SCREEN_HEIGHT = Double.valueOf(480.0D);
    }

    public static float applyDimension(int paramInt, float paramFloat, DisplayMetrics paramDisplayMetrics) {
        float f = paramFloat;
        switch (paramInt) {
            default:
                f = 0.0F;
            case 0:
                return f;
            case 1:
                return paramFloat * paramDisplayMetrics.density;
            case 2:
                return paramFloat * paramDisplayMetrics.scaledDensity;
            case 3:
                return paramDisplayMetrics.xdpi * paramFloat * 0.013888889F;
            case 4:
                return paramFloat * paramDisplayMetrics.xdpi;
            case 5:
                break;
        }
        return paramDisplayMetrics.xdpi * paramFloat * 0.03937008F;
    }

    public static int dip2px(Context paramContext, double paramDouble) {
        return (int)((paramContext.getResources().getDisplayMetrics()).density * paramDouble + 0.5D);
    }

    public static int dip2px(Context paramContext, float paramFloat) {
        return (int)applyDimension(1, paramFloat, getDisplayMetrics(paramContext));
    }

    public static DisplayMetrics getDisplayMetrics(Context paramContext) {
        Resources resources;
        if (paramContext == null) {
              resources = Resources.getSystem();
            return resources.getDisplayMetrics();
        }
       // Resources resources = resources.getResources();
        return paramContext.getResources().getDisplayMetrics();
    }

    public static void measureView(View paramView) {
        ViewGroup.LayoutParams layoutParams2 = paramView.getLayoutParams();
        ViewGroup.LayoutParams layoutParams1 = layoutParams2;
        if (layoutParams2 == null)
            layoutParams1 = new ViewGroup.LayoutParams(-1, -2);
        int j = ViewGroup.getChildMeasureSpec(0, 0, layoutParams1.width);
        int i = layoutParams1.height;
        if (i > 0) {
            i = View.MeasureSpec.makeMeasureSpec(i, View.MeasureSpec.AT_MOST);
        } else {
            i = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }
        paramView.measure(j, i);
    }

    public static float px2dip(Context paramContext, float paramFloat) {
        return paramFloat / (getDisplayMetrics(paramContext)).density;
    }

    public static float px2sp(Context paramContext, float paramFloat) {
        return paramFloat / (getDisplayMetrics(paramContext)).scaledDensity;
    }

    public static int scale(int paramInt1, int paramInt2, float paramFloat) {
        if (paramFloat == 0.0F)
            return 0;
        float f = 1.0F;
        double d = paramInt1;
        try {
            float f1 = Math.min((float)(d / BASE_SCREEN_WIDTH.doubleValue()), (float)(paramInt2 / BASE_SCREEN_HEIGHT.doubleValue()));
            f = f1;
        } catch (Exception exception) {}
        return Math.round(paramFloat * f + 0.5F);
    }

    public static int scaleTextValue(Context paramContext, float paramFloat) {
        DisplayMetrics displayMetrics = getDisplayMetrics(paramContext);
        if (displayMetrics.scaledDensity > 2.0F);
        return scale(displayMetrics.widthPixels, displayMetrics.heightPixels, paramFloat);
    }

    public static int scaleValue(Context paramContext, float paramFloat) {
        DisplayMetrics displayMetrics = getDisplayMetrics(paramContext);
        float f = paramFloat;
        if (displayMetrics.scaledDensity > 2.0F)
            f = paramFloat * (1.1F - 1.0F / displayMetrics.scaledDensity);
        return scale(displayMetrics.widthPixels, displayMetrics.heightPixels, f);
    }

    public static void setPadding(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        paramView.setPadding(scaleValue(paramView.getContext(), paramInt1), scaleValue(paramView.getContext(), paramInt2), scaleValue(paramView.getContext(), paramInt3), scaleValue(paramView.getContext(), paramInt4));
    }

    public static void setTextSize(TextView paramTextView, float paramFloat) {
        paramTextView.setTextSize(0, scaleTextValue(paramTextView.getContext(), paramFloat));
    }

    public static float sp2px(Context paramContext, float paramFloat) {
        return applyDimension(2, paramFloat, getDisplayMetrics(paramContext));
    }
}
