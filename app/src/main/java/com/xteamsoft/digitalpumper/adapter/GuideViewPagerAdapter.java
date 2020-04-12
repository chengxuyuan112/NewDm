package com.xteamsoft.digitalpumper.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
public class GuideViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> Views;
    public GuideViewPagerAdapter(ArrayList<View> paramArrayList) {
        this.Views = paramArrayList;
    }

    public void destroyItem(View paramView, int paramInt, Object paramObject) {
        ((ViewPager)paramView).removeView((View)paramObject);
    }

    public int getCount() {
        return this.Views.size();
    }

    public Object instantiateItem(View paramView, int paramInt) {
        ((ViewPager)paramView).addView(this.Views.get(paramInt));
        return this.Views.get(paramInt);
    }

    public boolean isViewFromObject(View paramView, Object paramObject) {
        return (paramView == paramObject);
    }
}
