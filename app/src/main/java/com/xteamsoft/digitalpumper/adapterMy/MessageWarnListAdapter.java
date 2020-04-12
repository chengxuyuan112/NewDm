package com.xteamsoft.digitalpumper.adapterMy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xteamsoft.baselibrary.base.AdapterBase;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.bean.MessageBean;

public class MessageWarnListAdapter extends AdapterBase<MessageBean.MessageBeanContent> {
    private Context context;

    public MessageWarnListAdapter(Context paramContext) {
        super(paramContext);
        this.context = paramContext;
    }

    protected View getExView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        ViewHolder viewHolder;
        if (paramView == null) {
            viewHolder = new ViewHolder();
            paramView = this.mInflater.inflate(R.layout.item_messagelist_warning, null);
            viewHolder.news_head = (ImageView) paramView.findViewById(R.id.news_head);
            viewHolder.news_title = (TextView) paramView.findViewById(R.id.news_title);
            viewHolder.news_text = (TextView) paramView.findViewById(R.id.news_text);
            viewHolder.isread = (TextView) paramView.findViewById(R.id.tv_read);
            paramView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) paramView.getTag();
        }
        MessageBean.MessageBeanContent messageBeanContent = this.arrayList.get(paramInt);
        viewHolder.news_title.setText(messageBeanContent.getTitle());
        viewHolder.news_text.setText(messageBeanContent.getCreate_time());
        if (messageBeanContent.getIs_read().equals("Y")) {
            viewHolder.isread.setText("readed");
            viewHolder.isread.setTextColor(this.context.getResources().getColor(R.color.readed));
            return paramView;
        }
        if (messageBeanContent.getIs_read().equals("N")) {
            viewHolder.isread.setText("unread");
            viewHolder.isread.setTextColor(this.context.getResources().getColor(R.color.unread));
            return paramView;
        }
        return paramView;
    }

    protected void onReachBottom() {
    }

    class ViewHolder {
        TextView isread;

        ImageView news_head;

        TextView news_text;

        TextView news_title;
    }
}
