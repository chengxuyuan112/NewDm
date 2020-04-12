package com.xteamsoft.digitalpumper.adapterFacility;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xteamsoft.baselibrary.base.AdapterBase;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.bean.BasicData;
import com.xteamsoft.digitalpumper.bean.WellBean;
import java.util.List;
import org.litepal.crud.DataSupport;

public class GasAdapter extends AdapterBase<BasicData.MessageBasic> {
    private Context context;

    public GasAdapter(Context paramContext) {
        super(paramContext);
        this.context = paramContext;
    }

    protected View getExView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        ViewHolder viewHolder;
        if (paramView == null) {
            viewHolder = new ViewHolder();
            paramView = this.mInflater.inflate(R.layout.item_gaslist, null);
            viewHolder.fgas_item_name = (TextView)paramView.findViewById(R.id.fgas_item_name);
            viewHolder.fgas_item_m1 = (TextView)paramView.findViewById(R.id.fgas_item_m1);
            viewHolder.fgas_item_m2 = (TextView)paramView.findViewById(R.id.fgas_item_m2);
            paramView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)paramView.getTag();
        }
        BasicData.MessageBasic messageBasic = this.arrayList.get(paramInt);
        List<WellBean> list = DataSupport.where(new String[] { "_wellid = ?", messageBasic.get_wellid() }).find(WellBean.class);
        if (!list.isEmpty())
            viewHolder.fgas_item_name.setText(((WellBean)list.get(0)).get_wellname());
        viewHolder.fgas_item_m1.setText(messageBasic.get_accumflow());
        viewHolder.fgas_item_m2.setText(messageBasic.get_currentflow());
        return paramView;
    }

    protected void onReachBottom() {}

    class ViewHolder {
        TextView fgas_item_m1;

        TextView fgas_item_m2;

        TextView fgas_item_name;
    }
}
