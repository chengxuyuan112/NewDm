package com.xteamsoft.digitalpumper.adapterWells;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xteamsoft.baselibrary.base.AdapterBase;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.bean.WellBean;

public class PopWellAdapter extends AdapterBase<WellBean> {
    private Context context;

    public PopWellAdapter(Context paramContext) {
        super(paramContext);
        this.context = paramContext;
    }

    protected View getExView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        if (paramView == null) {
            ViewHolder viewHolder1 = new ViewHolder();
            paramView = this.mInflater.inflate(R.layout.item_pop_well_list, null);
            viewHolder1.well_img = (ImageView)paramView.findViewById(R.id.oilWell_img);
            viewHolder1.well_name = (TextView)paramView.findViewById(R.id.oilWell_name);
            paramView.setTag(viewHolder1);
            WellBean wellBean1 = this.arrayList.get(paramInt);
            Log.e("========== ", "" + wellBean1);
            viewHolder1.well_name.setText(wellBean1.get_wellname());
            return paramView;
        }
        ViewHolder viewHolder = (ViewHolder)paramView.getTag();
        WellBean wellBean = this.arrayList.get(paramInt);
        Log.e("========== ", "" + wellBean);
        viewHolder.well_name.setText(wellBean.get_wellname());
        return paramView;
    }

    protected void onReachBottom() {}

    class ViewHolder {
        ImageView well_img;

        TextView well_name;
    }
}
