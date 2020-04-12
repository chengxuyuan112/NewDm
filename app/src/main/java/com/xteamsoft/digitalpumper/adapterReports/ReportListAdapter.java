package com.xteamsoft.digitalpumper.adapterReports;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xteamsoft.baselibrary.base.AdapterBase;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.beanReports.ReportsData;

public class ReportListAdapter extends AdapterBase<ReportsData.MessageReports> {
    private Context context;

    public ReportListAdapter(Context paramContext) {
        super(paramContext);
        this.context = paramContext;
    }

    protected View getExView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        if (paramView == null) {
            ViewHolder viewHolder1 = new ViewHolder();
            paramView = this.mInflater.inflate(R.layout.item_reportmainlist, null);
            viewHolder1.oilWell_img = (ImageView) paramView.findViewById(R.id.oilWell_img);
            viewHolder1.oilWell_name = (TextView) paramView.findViewById(R.id.oilWell_name);
            paramView.setTag(viewHolder1);
            ReportsData.MessageReports messageReports1 = this.arrayList.get(paramInt);
            viewHolder1.oilWell_name.setText(messageReports1.get_drepid());
            return paramView;
        }
        ViewHolder viewHolder = (ViewHolder) paramView.getTag();
        ReportsData.MessageReports messageReports = this.arrayList.get(paramInt);
        viewHolder.oilWell_name.setText(messageReports.get_drepid());
        return paramView;
    }

    protected void onReachBottom() {
    }

    class ViewHolder {
        ImageView oilWell_img;

        TextView oilWell_name;
    }
}
