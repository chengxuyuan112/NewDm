package com.xteamsoft.digitalpumper.adapterWells;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xteamsoft.baselibrary.base.AdapterBase;
import com.xteamsoft.baselibrary.utils.TimeUtils;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.bean.ChartData;

public class ChartTimeListAdapter extends AdapterBase<ChartData.MessageChart> {
    private Context context;

    public ChartTimeListAdapter(Context paramContext) {
        super(paramContext);
        this.context = paramContext;
    }

    protected View getExView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        if (paramView == null) {
            ViewHolder viewHolder1 = new ViewHolder();
            paramView = this.mInflater.inflate(R.layout.item_time_timelist, null);
            viewHolder1.well_img = (ImageView) paramView.findViewById(R.id.oilWell_img);
            viewHolder1.well_name = (TextView) paramView.findViewById(R.id.oilWell_name);
            paramView.setTag(viewHolder1);
            ChartData.MessageChart messageChart1 = this.arrayList.get(paramInt);
            Log.e("========== ", "" + messageChart1);
            String str1 = TimeUtils.millis2String(Long.parseLong(messageChart1.get_loaddatetime().substring(5, messageChart1.get_loaddatetime().length() - 6)));
            viewHolder1.well_name.setText(str1);
            return paramView;
        }
        ViewHolder viewHolder = (ViewHolder) paramView.getTag();
        ChartData.MessageChart messageChart = this.arrayList.get(paramInt);
        Log.e("========== ", "" + messageChart);
        String str = TimeUtils.millis2String(Long.parseLong(messageChart.get_loaddatetime().substring(5, messageChart.get_loaddatetime().length() - 6)));
        viewHolder.well_name.setText(str);
        return paramView;
    }

    protected void onReachBottom() {
    }

    class ViewHolder {
        ImageView well_img;

        TextView well_name;
    }
}
