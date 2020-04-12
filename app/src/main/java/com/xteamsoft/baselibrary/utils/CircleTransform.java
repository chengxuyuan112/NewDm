package com.xteamsoft.baselibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import com.squareup.picasso.Transformation;

public class CircleTransform implements Transformation {
    public String key() {
        return "circle";
    }

    public Bitmap transform(Bitmap paramBitmap) {
        int i = Math.min(paramBitmap.getWidth(), paramBitmap.getHeight());
        Bitmap bitmap = Bitmap.createBitmap(paramBitmap, (paramBitmap.getWidth() - i) / 2, (paramBitmap.getHeight() - i) / 2, i, i);
        if (bitmap != paramBitmap)
            paramBitmap.recycle();
        paramBitmap = Bitmap.createBitmap(i, i, paramBitmap.getConfig());
        Canvas canvas = new Canvas(paramBitmap);
        Paint paint = new Paint();
        paint.setShader((Shader)new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float f = i / 2.0F;
        canvas.drawCircle(f, f, f, paint);
        bitmap.recycle();
        return paramBitmap;
    }
}
