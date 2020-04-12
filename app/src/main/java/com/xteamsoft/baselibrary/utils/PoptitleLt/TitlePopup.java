package com.xteamsoft.baselibrary.utils.PoptitleLt;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xteamsoft.digitalpumper.R;

import java.util.ArrayList;

public class TitlePopup extends PopupWindow {
    protected final int LIST_PADDING = 10;

    private ArrayList<ActionItem> mActionItems = new ArrayList<ActionItem>();

    private Context mContext;

    private boolean mIsDirty;

    private OnItemOnClickListener mItemOnClickListener;

    private ListView mListView;

    private final int[] mLocation = new int[2];

    private Rect mRect = new Rect();

    private int mScreenHeight;

    private int mScreenWidth;

    private int popupGravity = 0;

    public TitlePopup(Context paramContext) {
        this(paramContext, -2, -2);
    }

    public TitlePopup(Context paramContext, int paramInt1, int paramInt2) {
        this.mContext = paramContext;
        setFocusable(true);
        setTouchable(true);
        setOutsideTouchable(true);
        this.mScreenWidth = Util.getScreenWidth(this.mContext);
        this.mScreenHeight = Util.getScreenHeight(this.mContext);
        setWidth(paramInt1);
        setHeight(paramInt2);
        setBackgroundDrawable((Drawable)new BitmapDrawable());
        setContentView(LayoutInflater.from(this.mContext).inflate(R.layout.title_popup, null));
        initUI();
    }

    private void initUI() {
        this.mListView = (ListView)getContentView().findViewById(R.id.title_listAdd);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                TitlePopup.this.dismiss();
                if (TitlePopup.this.mItemOnClickListener != null)
                    TitlePopup.this.mItemOnClickListener.onItemClick(TitlePopup.this.mActionItems.get(param1Int), param1Int);
            }
        });
    }

    private void populateActions() {
        this.mIsDirty = false;
        this.mListView.setAdapter((ListAdapter)new BaseAdapter() {
            public int getCount() {
                return TitlePopup.this.mActionItems.size();
            }

            public Object getItem(int param1Int) {
                return TitlePopup.this.mActionItems.get(param1Int);
            }

            public long getItemId(int param1Int) {
                return param1Int;
            }

            public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
                TextView textView = null;
                if (param1View == null) {
                      textView = new TextView(TitlePopup.this.mContext);
                    textView.setTextColor(TitlePopup.this.mContext.getResources().getColor(R.color.unread));
                    textView.setTextSize(16.0F);
                    textView.setGravity(17);
                    textView.setPadding(0, 10, 0, 10);
                    textView.setSingleLine(true);
                    ActionItem actionItem1 = TitlePopup.this.mActionItems.get(param1Int);
                    textView.setText(actionItem1.mTitle);
                    textView.setCompoundDrawablePadding(10);
                    textView.setCompoundDrawablesWithIntrinsicBounds(actionItem1.mDrawable, null, null, null);
                    return (View)textView;
                }
                //TextView textView = textView;
                ActionItem actionItem = TitlePopup.this.mActionItems.get(param1Int);
                textView.setText(actionItem.mTitle);
                textView.setCompoundDrawablePadding(10);
                textView.setCompoundDrawablesWithIntrinsicBounds(actionItem.mDrawable, null, null, null);
                return (View)textView;
            }
        });
    }

    public void addAction(ActionItem paramActionItem) {
        if (paramActionItem != null) {
            this.mActionItems.add(paramActionItem);
            this.mIsDirty = true;
        }
    }

    public void cleanAction() {
        if (this.mActionItems.isEmpty()) {
            this.mActionItems.clear();
            this.mIsDirty = true;
        }
    }

    public ActionItem getAction(int paramInt) {
        return (paramInt < 0 || paramInt > this.mActionItems.size()) ? null : this.mActionItems.get(paramInt);
    }

    public void setItemOnClickListener(OnItemOnClickListener paramOnItemOnClickListener) {
        this.mItemOnClickListener = paramOnItemOnClickListener;
    }

    public void show(View paramView) {
        paramView.getLocationOnScreen(this.mLocation);
        this.mRect.set(this.mLocation[0], this.mLocation[1], this.mLocation[0] + paramView.getWidth(), this.mLocation[1] + paramView.getHeight());
        if (this.mIsDirty)
            populateActions();
        showAtLocation(paramView, this.popupGravity, this.mScreenWidth - 10 - getWidth() / 2, this.mRect.bottom);
    }

    public static interface OnItemOnClickListener {
        void onItemClick(ActionItem param1ActionItem, int param1Int);
    }
}
