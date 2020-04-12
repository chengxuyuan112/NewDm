package com.xteamsoft.baselibrary.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.xteamsoft.digitalpumper.R;

public class CircleImageView extends AppCompatImageView {
    private static final Bitmap.Config BITMAP_CONFIG;

    private static final int COLORDRAWABLE_DIMENSION = 1;

    private static final ImageView.ScaleType SCALE_TYPE = ImageView.ScaleType.CENTER_CROP;

    private Bitmap mBitmap;

    private int mBitmapHeight;

    private final Paint mBitmapPaint = new Paint();

    private BitmapShader mBitmapShader;

    private int mBitmapWidth;

    private int mBorderColor;

    private final Paint mBorderPaint = new Paint();

    private float mBorderRadius;

    private final RectF mBorderRect = new RectF();

    private int mBorderWidth=10;

    private float mDrawableRadius;

    private final RectF mDrawableRect = new RectF();

    private boolean mReady;

    private boolean mSetupPending;

    private final Matrix mShaderMatrix = new Matrix();

    static {
        BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    }

    public CircleImageView(Context paramContext) {
        super(paramContext);
        init();
    }

    public CircleImageView(Context paramContext, AttributeSet paramAttributeSet) {
        this(paramContext, paramAttributeSet, 0);
        init();
    }

    public CircleImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CircleImageView, paramInt, 0);
        this.mBorderWidth = typedArray.getDimensionPixelSize(R.styleable.CircleImageView_border_width, 0);
        this.mBorderColor = typedArray.getColor(R.styleable.CircleImageView_border_color, 0);
        typedArray.recycle();
        init();
    }

    private Bitmap getBitmapFromDrawable(Drawable paramDrawable) {
        if (paramDrawable == null)
            return null;
        if (paramDrawable instanceof BitmapDrawable)
            return ((BitmapDrawable)paramDrawable).getBitmap();
        try {
            if (paramDrawable instanceof android.graphics.drawable.ColorDrawable) {
                Bitmap bitmap1 = Bitmap.createBitmap(1, 1, BITMAP_CONFIG);
                Canvas canvas1 = new Canvas(bitmap1);
                paramDrawable.setBounds(0, 0, canvas1.getWidth(), canvas1.getHeight());
                paramDrawable.draw(canvas1);
                return bitmap1;
            }
            Bitmap bitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), BITMAP_CONFIG);
            Canvas canvas = new Canvas(bitmap);
            paramDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            paramDrawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError outOfMemoryError) {
            return null;
        }
    }

    private void init() {
        super.setScaleType(SCALE_TYPE);
        this.mReady = true;
        if (this.mSetupPending) {
            setup();
            this.mSetupPending = false;
        }
    }

    private void setup() {
        if (!this.mReady) {
            this.mSetupPending = true;
            return;
        }
        if (this.mBitmap != null) {
            this.mBitmapShader = new BitmapShader(this.mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            this.mBitmapPaint.setAntiAlias(true);
            this.mBitmapPaint.setShader((Shader)this.mBitmapShader);
            this.mBorderPaint.setStyle(Paint.Style.STROKE);
            this.mBorderPaint.setAntiAlias(true);
            this.mBorderPaint.setColor(this.mBorderColor);
            this.mBorderPaint.setStrokeWidth(this.mBorderWidth);
            this.mBitmapHeight = this.mBitmap.getHeight();
            this.mBitmapWidth = this.mBitmap.getWidth();
            this.mBorderRect.set(0.0F, 0.0F, getWidth(), getHeight());
            this.mBorderRadius = Math.min((this.mBorderRect.height() - this.mBorderWidth) / 2.0F, (this.mBorderRect.width() - this.mBorderWidth) / 2.0F);
            this.mDrawableRect.set(this.mBorderWidth, this.mBorderWidth, this.mBorderRect.width() - this.mBorderWidth, this.mBorderRect.height() - this.mBorderWidth);
            this.mDrawableRadius = Math.min(this.mDrawableRect.height() / 2.0F, this.mDrawableRect.width() / 2.0F);
            updateShaderMatrix();
            invalidate();
            return;
        }
    }

    private void updateShaderMatrix() {
        float f3;
        float f1 = 0.0F;
        float f2 = 0.0F;
        this.mShaderMatrix.set(null);
        if (this.mBitmapWidth * this.mDrawableRect.height() > this.mDrawableRect.width() * this.mBitmapHeight) {
            f3 = this.mDrawableRect.height() / this.mBitmapHeight;
            f1 = (this.mDrawableRect.width() - this.mBitmapWidth * f3) * 0.5F;
        } else {
            f3 = this.mDrawableRect.width() / this.mBitmapWidth;
            f2 = (this.mDrawableRect.height() - this.mBitmapHeight * f3) * 0.5F;
        }
        this.mShaderMatrix.setScale(f3, f3);
        this.mShaderMatrix.postTranslate(((int)(f1 + 0.5F) + this.mBorderWidth), ((int)(f2 + 0.5F) + this.mBorderWidth));
        this.mBitmapShader.setLocalMatrix(this.mShaderMatrix);
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public int getBorderWidth() {
        return this.mBorderWidth;
    }

    public ImageView.ScaleType getScaleType() {
        return SCALE_TYPE;
    }

    protected void onDraw(Canvas paramCanvas) {
        if (getDrawable() != null) {
            paramCanvas.drawCircle((getWidth() / 2), (getHeight() / 2), this.mDrawableRadius, this.mBitmapPaint);
            if (this.mBorderWidth != 0) {
                paramCanvas.drawCircle((getWidth() / 2), (getHeight() / 2), this.mBorderRadius, this.mBorderPaint);
                return;
            }
        }
    }

    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
        setup();
    }

    public void setBorderColor(int paramInt) {
        if (paramInt == this.mBorderColor)
            return;
        this.mBorderColor = paramInt;
        this.mBorderPaint.setColor(this.mBorderColor);
        invalidate();
    }

    public void setBorderWidth(int paramInt) {
        if (paramInt == this.mBorderWidth)
            return;
        this.mBorderWidth = paramInt;
        setup();
    }

    public void setImageBitmap(Bitmap paramBitmap) {
        super.setImageBitmap(paramBitmap);
        this.mBitmap = paramBitmap;
        setup();
    }

    public void setImageDrawable(Drawable paramDrawable) {
        super.setImageDrawable(paramDrawable);
        this.mBitmap = getBitmapFromDrawable(paramDrawable);
        setup();
    }

    public void setImageResource(int paramInt) {
        super.setImageResource(paramInt);
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    public void setImageURI(Uri paramUri) {
        super.setImageURI(paramUri);
        this.mBitmap = getBitmapFromDrawable(getDrawable());
        setup();
    }

    public void setScaleType(ImageView.ScaleType paramScaleType) {
        if (paramScaleType != SCALE_TYPE)
            throw new IllegalArgumentException(String.format("ScaleType %s not supported.", new Object[] { paramScaleType }));
    }
}
