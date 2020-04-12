package com.xteamsoft.baselibrary.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.List;

public abstract class AdapterBase<T> extends BaseAdapter {
    protected List<T> arrayList = new ArrayList();

    protected Context mContext;

    protected LayoutInflater mInflater;

    public AdapterBase(Context paramContext) {
        this.mInflater = LayoutInflater.from(paramContext);
        this.mContext = paramContext;
    }

    public void appendToList(List<T> paramList) {
        if (paramList == null)
            return;
        this.arrayList = paramList;
    }

    public void appendToTopList(List<T> paramList) {
        if (paramList == null)
            return;
        this.arrayList.addAll(0, paramList);
    }

    public void clear() {
        this.arrayList.clear();
    }

    public int getCount() {
        return this.arrayList.size();
    }

    protected abstract View getExView(int paramInt, View paramView, ViewGroup paramViewGroup);

    public Object getItem(int paramInt) {
        return (paramInt > this.arrayList.size() - 1) ? null : this.arrayList.get(paramInt);
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public List<T> getList() {
        return this.arrayList;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        if (paramInt == getCount() - 1)
            onReachBottom();
        return getExView(paramInt, paramView, paramViewGroup);
    }

    protected abstract void onReachBottom();
}
