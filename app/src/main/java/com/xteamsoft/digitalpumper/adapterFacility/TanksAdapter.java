package com.xteamsoft.digitalpumper.adapterFacility;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xteamsoft.baselibrary.base.AdapterBase;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.bean.BasicData;
import com.xteamsoft.digitalpumper.bean.WellBean;

import java.text.DecimalFormat;
import java.util.List;

import org.litepal.crud.DataSupport;

public class TanksAdapter extends AdapterBase<BasicData.MessageBasic> {
    private Context context;

    public TanksAdapter(Context paramContext) {
        super(paramContext);
        this.context = paramContext;
    }

    private double getTwoDecimal(double paramDouble) {
        return Double.valueOf((new DecimalFormat("#.00")).format(paramDouble)).doubleValue();
    }

    protected View getExView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        ViewHolder viewHolder;
        if (paramView == null) {
            viewHolder = new ViewHolder();
            paramView = this.mInflater.inflate(R.layout.item_thankslist, null);
            viewHolder.fthanks_item_name = (TextView) paramView.findViewById(R.id.fthanks_item_name);
            viewHolder.fthanks_item_backimg = (LinearLayout) paramView.findViewById(R.id.fthanks_item_backimg);
            viewHolder.fthanks_item_img = (ImageView) paramView.findViewById(R.id.fthanks_item_img);
            viewHolder.fthanks_item_m1 = (TextView) paramView.findViewById(R.id.fthanks_item_m1);
            viewHolder.fthanks_item_m2 = (TextView) paramView.findViewById(R.id.fthanks_item_m2);
            paramView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) paramView.getTag();
        }
        BasicData.MessageBasic messageBasic = this.arrayList.get(paramInt);
        List<WellBean> list = DataSupport.where(new String[]{"_wellid = ?", messageBasic.get_wellid()}).find(WellBean.class);
        if (!list.isEmpty())
            viewHolder.fthanks_item_name.setText(((WellBean) list.get(0)).get_wellname());
        if (messageBasic.getType().equals("oil1")) {
            viewHolder.fthanks_item_backimg.setBackgroundResource(R.mipmap.img_oil);
            viewHolder.fthanks_item_img.setImageResource(R.mipmap.img_oil);
            viewHolder.fthanks_item_m1.setText(getTwoDecimal(Double.valueOf(((WellBean) list.get(0)).get_oiltank1area()).doubleValue() * Double.valueOf(messageBasic.get_otank1fluidpos()).doubleValue()) + "ms");
            viewHolder.fthanks_item_m2.setText(messageBasic.get_otank1fluidpos());
            return paramView;
        }
        if (messageBasic.getType().equals("oil2")) {
            viewHolder.fthanks_item_backimg.setBackgroundResource(R.mipmap.img_oil);
            viewHolder.fthanks_item_img.setImageResource(R.mipmap.img_oil);
            viewHolder.fthanks_item_m1.setText(getTwoDecimal(Double.valueOf(((WellBean) list.get(0)).get_oiltank2area()).doubleValue() * Double.valueOf(messageBasic.get_otank2fluidpos()).doubleValue()) + "ms");
            viewHolder.fthanks_item_m2.setText(messageBasic.get_otank2fluidpos());
            return paramView;
        }
        if (messageBasic.getType().equals("water1")) {
            viewHolder.fthanks_item_backimg.setBackgroundResource(R.mipmap.img_water);
            viewHolder.fthanks_item_img.setImageResource(R.mipmap.img_water);
            viewHolder.fthanks_item_m1.setText(getTwoDecimal(Double.valueOf(((WellBean) list.get(0)).get_watertank1area()).doubleValue() * Double.valueOf(messageBasic.get_wtank1fluidpos()).doubleValue()) + "");
            viewHolder.fthanks_item_m2.setText(messageBasic.get_wtank1fluidpos());
            return paramView;
        }
        if (messageBasic.getType().equals("water2")) {
            viewHolder.fthanks_item_backimg.setBackgroundResource(R.mipmap.img_water);
            viewHolder.fthanks_item_img.setImageResource(R.mipmap.img_water);
            viewHolder.fthanks_item_m1.setText(getTwoDecimal(Double.valueOf(((WellBean) list.get(0)).get_watertank2area()).doubleValue() * Double.valueOf(messageBasic.get_wtank2fluidpos()).doubleValue()) + "");
            viewHolder.fthanks_item_m2.setText(messageBasic.get_wtank2fluidpos());
            return paramView;
        }
        return paramView;
    }

    protected void onReachBottom() {
    }

    class ViewHolder {
        LinearLayout fthanks_item_backimg;

        ImageView fthanks_item_img;

        TextView fthanks_item_m1;

        TextView fthanks_item_m2;

        TextView fthanks_item_name;
    }
}
