package com.xteamsoft.digitalpumper.adapterWells;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xteamsoft.baselibrary.base.AdapterBase;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.bean.WellAreaBean;
import com.xteamsoft.digitalpumper.bean.WellBean;
import com.xteamsoft.digitalpumper.mainWells.RedetectActivity;

import java.io.Serializable;
import java.util.List;

import org.litepal.crud.DataSupport;

public class ContralListAdapter extends AdapterBase<WellBean> {
    private Context context;

    public ContralListAdapter(Context paramContext) {
        super(paramContext);
        this.context = paramContext;
    }

    protected View getExView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        ViewHolder viewHolder;
        if (paramView == null) {
            viewHolder = new ViewHolder();
            paramView = this.mInflater.inflate(R.layout.item_contrallist, null);
            viewHolder.contral_head = (ImageView) paramView.findViewById(R.id.contral_head);
            viewHolder.contral_title = (TextView) paramView.findViewById(R.id.contral_title);
            viewHolder.contral_text = (TextView) paramView.findViewById(R.id.contral_text);
            viewHolder.contral_level = (TextView) paramView.findViewById(R.id.contral_level);
            paramView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) paramView.getTag();
        }
        final WellBean wellBean = this.arrayList.get(paramInt);
        viewHolder.contral_title.setText(wellBean.get_wellname());
        List<WellAreaBean> list = DataSupport.where(new String[]{"_wellareaid = ?", wellBean.get_wellareaid()}).find(WellAreaBean.class);
        if (!list.isEmpty())
            viewHolder.contral_text.setText(((WellAreaBean) list.get(0)).get_wellareaname());
        viewHolder.contral_level.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                Intent intent = new Intent(ContralListAdapter.this.context, RedetectActivity.class);
                intent.putExtra("intentMsg", (Serializable) wellBean);
                ContralListAdapter.this.context.startActivity(intent);
            }
        });
        return paramView;
    }

    protected void onReachBottom() {
    }

    class ViewHolder {
        ImageView contral_head;

        TextView contral_level;

        TextView contral_text;

        TextView contral_title;
    }
}
