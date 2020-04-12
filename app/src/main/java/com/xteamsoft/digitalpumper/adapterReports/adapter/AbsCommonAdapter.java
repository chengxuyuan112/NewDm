package com.xteamsoft.digitalpumper.adapterReports.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.xteamsoft.digitalpumper.MyAplication;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbsCommonAdapter<T> extends BaseAdapter {
    protected MyAplication app;

    protected Context mContext;

    protected List<T> mDatas;

    protected LayoutInflater mInflater;

    protected final int mItemLayoutId;

    public AbsCommonAdapter(Context paramContext, int paramInt) {
        this(paramContext, paramInt, null);
    }

    public AbsCommonAdapter(Context paramContext, int paramInt, MyAplication paramMyAplication) {
        this.mContext = paramContext;
        this.mInflater = LayoutInflater.from(this.mContext);
        this.mItemLayoutId = paramInt;
        this.app = paramMyAplication;
        this.mDatas = new ArrayList<T>();
    }

    private AbsViewHolder getViewHolder(int paramInt, View paramView, ViewGroup paramViewGroup) {
        return AbsViewHolder.get(this.mContext, paramView, paramViewGroup, this.mItemLayoutId, paramInt);
    }

    public void addData(List<T> paramList, boolean paramBoolean) {
        if (paramBoolean) {
            if (paramList != null)
                this.mDatas.addAll(paramList);
        } else {
            this.mDatas.clear();
            if (paramList != null)
                this.mDatas.addAll(paramList);
        }
        notifyDataSetChanged();
    }

    public void addItemData(T paramT, int paramInt, boolean paramBoolean) {
        this.mDatas.add(paramInt, paramT);
        if (paramBoolean)
            notifyDataSetChanged();
    }

    public void addItemData(T paramT, boolean paramBoolean) {
        this.mDatas.add(paramT);
        if (paramBoolean)
            notifyDataSetChanged();
    }

    public void addItemData(Collection<? extends T> paramCollection, int paramInt, boolean paramBoolean) {
        this.mDatas.addAll(paramInt, paramCollection);
        if (paramBoolean)
            notifyDataSetChanged();
    }

    public void addItemData(Collection<? extends T> paramCollection, boolean paramBoolean) {
        this.mDatas.addAll(paramCollection);
        if (paramBoolean)
            notifyDataSetChanged();
    }

    public void clearData(boolean paramBoolean) {
        if (paramBoolean && this.mDatas != null && this.mDatas.size() > 0) {
            this.mDatas.clear();
            notifyDataSetChanged();
        }
    }

    public abstract void convert(AbsViewHolder paramAbsViewHolder, T paramT, int paramInt);

    public int getCount() {
        return this.mDatas.size();
    }

    public List<T> getDatas() {
        return this.mDatas;
    }

    public T getItem(int paramInt) {
        return (this.mDatas != null && paramInt >= 0 && paramInt <= this.mDatas.size() - 1) ? this.mDatas.get(paramInt) : null;
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        AbsViewHolder absViewHolder = getViewHolder(paramInt, paramView, paramViewGroup);
        convert(absViewHolder, getItem(paramInt), paramInt);
        return absViewHolder.getConvertView();
    }

    public void remove(int paramInt) {
        if (this.mDatas != null && this.mDatas.size() > 0) {
            this.mDatas.remove(paramInt);
            notifyDataSetChanged();
        }
    }
}
