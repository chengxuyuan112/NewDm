package com.xteamsoft.digitalpumper.adapterMap;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xteamsoft.baselibrary.base.AdapterBase;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.bean.SheetData;

public class SheetMainAdapter extends AdapterBase<SheetData> {
    private Context context;

    public SheetMainAdapter(Context paramContext) {
        super(paramContext);
        this.context = paramContext;
    }

    protected View getExView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        ViewHolder viewHolder;
        if (paramView == null) {
            viewHolder = new ViewHolder();
            paramView = this.mInflater.inflate(R.layout.item_map_set_main_sheet, null);
            viewHolder.item = (LinearLayout) paramView.findViewById(R.id.ll_item);
            viewHolder.title = (TextView) paramView.findViewById(R.id.title);
            viewHolder.unit = (TextView) paramView.findViewById(R.id.unit);
            viewHolder.number = (TextView) paramView.findViewById(R.id.number);
            paramView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) paramView.getTag();
        }
        SheetData sheetData = this.arrayList.get(paramInt);
        if (sheetData.isIschecked()) {
            viewHolder.title.setText(sheetData.getTitle());
            viewHolder.unit.setText(sheetData.getUnit());
            viewHolder.number.setText(sheetData.getNumber());
        }
        return paramView;
    }

    protected void onReachBottom() {
    }

    class ViewHolder {
        LinearLayout item;

        TextView number;

        TextView title;

        TextView unit;
    }
}
