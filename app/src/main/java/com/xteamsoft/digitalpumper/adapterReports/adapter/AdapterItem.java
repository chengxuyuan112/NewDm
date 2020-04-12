package com.xteamsoft.digitalpumper.adapterReports.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;

public interface AdapterItem<T> {
    void bindViews(View paramView);

    @LayoutRes
    int getLayoutResId();

    void handleData(T paramT, int paramInt);

    void setViews();
}
