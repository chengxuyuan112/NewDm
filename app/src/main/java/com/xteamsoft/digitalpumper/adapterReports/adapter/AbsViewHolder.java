package com.xteamsoft.digitalpumper.adapterReports.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class AbsViewHolder {
    private View mConvertView;

    private int mPosition;

    private final SparseArray<View> mViews;

    private AbsViewHolder(Context paramContext, ViewGroup paramViewGroup, int paramInt1, int paramInt2) {
        this.mPosition = paramInt2;
        this.mViews = new SparseArray();
        this.mConvertView = LayoutInflater.from(paramContext).inflate(paramInt1, paramViewGroup, false);
        this.mConvertView.setTag(this);
    }

    public static AbsViewHolder get(Context paramContext, View paramView, ViewGroup paramViewGroup, int paramInt1, int paramInt2) {
        // Byte code:
        //   0: aload_1
        //   1: ifnonnull -> 32
        //   4: ldc com/xteamsoft/digitalpumper/adapterReports/adapter/AbsViewHolder
        //   6: monitorenter
        //   7: aload_1
        //   8: ifnonnull -> 29
        //   11: new com/xteamsoft/digitalpumper/adapterReports/adapter/AbsViewHolder
        //   14: dup
        //   15: aload_0
        //   16: aload_2
        //   17: iload_3
        //   18: iload #4
        //   20: invokespecial <init> : (Landroid/content/Context;Landroid/view/ViewGroup;II)V
        //   23: astore_0
        //   24: ldc com/xteamsoft/digitalpumper/adapterReports/adapter/AbsViewHolder
        //   26: monitorexit
        //   27: aload_0
        //   28: areturn
        //   29: ldc com/xteamsoft/digitalpumper/adapterReports/adapter/AbsViewHolder
        //   31: monitorexit
        //   32: aload_1
        //   33: invokevirtual getTag : ()Ljava/lang/Object;
        //   36: checkcast com/xteamsoft/digitalpumper/adapterReports/adapter/AbsViewHolder
        //   39: areturn
        //   40: astore_0
        //   41: ldc com/xteamsoft/digitalpumper/adapterReports/adapter/AbsViewHolder
        //   43: monitorexit
        //   44: aload_0
        //   45: athrow
        // Exception table:
        //   from	to	target	type
        //   11	27	40	finally
        //   29	32	40	finally
        //   41	44	40	finally
        return null;
    }

    public View getConvertView() {
        return this.mConvertView;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public <T extends View> T getView(int paramInt) {
        return getView(paramInt, (ViewGroup.LayoutParams) null);
    }

    public <T extends View> T getView(int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
        View view2 = (View) this.mViews.get(paramInt);
        View view1 = view2;
        if (view2 == null) {
            view1 = this.mConvertView.findViewById(paramInt);
            if (paramLayoutParams != null)
                view1.setLayoutParams(paramLayoutParams);
            this.mViews.put(paramInt, view1);
        }
        return (T) view1;
    }

    public <T extends View> T getView(int paramInt, boolean paramBoolean) {
        View view2 = (View) this.mViews.get(paramInt);
        View view1 = view2;
        if (view2 == null) {
            view1 = this.mConvertView.findViewById(paramInt);
            if (paramBoolean && view1 instanceof TextView)
                setTxtThru((TextView) view1);
            this.mViews.put(paramInt, view1);
        }
        return (T) view1;
    }

    public AbsViewHolder setImageBitmap(int paramInt, Bitmap paramBitmap) {
        return setImageBitmap(paramInt, paramBitmap, null);
    }

    public AbsViewHolder setImageBitmap(int paramInt, Bitmap paramBitmap, ViewGroup.LayoutParams paramLayoutParams) {
        ((ImageView) getView(paramInt, paramLayoutParams)).setImageBitmap(paramBitmap);
        return this;
    }

    public AbsViewHolder setImageResource(int paramInt1, int paramInt2) {
        return setImageResource(paramInt1, paramInt2, null);
    }

    public AbsViewHolder setImageResource(int paramInt1, int paramInt2, ViewGroup.LayoutParams paramLayoutParams) {
        ((ImageView) getView(paramInt1, paramLayoutParams)).setImageResource(paramInt2);
        return this;
    }

    public AbsViewHolder setText(int paramInt, String paramString) {
        ((TextView) getView(paramInt)).setText(paramString);
        return this;
    }

    public void setTxtThru(TextView paramTextView) {
        paramTextView.getPaint().setFlags(16);
        paramTextView.getPaint().setAntiAlias(true);
    }
}
