package com.xteamsoft.digitalpumper.mainWells;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.leaking.slideswitch.SlideSwitch;
import com.xteamsoft.baselibrary.base.BaseActivity;
import com.xteamsoft.baselibrary.utilsLT.MaxPageReturn;
import com.xteamsoft.baselibrary.utilsLT.StringGsonToObject;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.adapterWells.ContralListAdapter;
import com.xteamsoft.digitalpumper.bean.CmdData;
import com.xteamsoft.digitalpumper.bean.WellAreaBean;
import com.xteamsoft.digitalpumper.bean.WellBean;
import com.xteamsoft.digitalpumper.bean.WellData;
import com.xteamsoft.digitalpumper.manager.MainManager;
import java.util.ArrayList;
import java.util.List;
import org.litepal.crud.DataSupport;

public class ControlActivity extends BaseActivity {
    private ListView ContralList;

    private ContralListAdapter adapter;

    private ImageView btnSearch;

    private AlertDialog controlalertDialog;

    private int displyStart = 0;

    private EditText etSearch;

    Handler handler = new Handler();

    private boolean ischecked;

    private List<WellBean> list;

    private ProgressDialog mProgressDialog;

    private PullToRefreshListView mPullRefreshListView;

    private int maxPage = 0;

    private int nextPage = 1;

    private int rows = 15;

    private int rstatus = 0;

    Runnable runnable = new Runnable() {
        public void run() {
            try {
                ControlActivity.this.handler.postDelayed(this, 1000L);
                ControlActivity.this.rOpen(ControlActivity.this.xfWell, 35);
                System.out.println("do...");
                return;
            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.println("exception...");
                return;
            }
        }
    };

    Runnable runnable2 = new Runnable() {
        public void run() {
            try {
                ControlActivity.this.handler.postDelayed(this, 1000L);
                ControlActivity.this.rClose(ControlActivity.this.xfWell, 36);
                System.out.println("do...");
                return;
            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.println("exception...");
                return;
            }
        }
    };

    private List<WellBean> searchlist;

    private String str;

    private String token;

    private Toolbar toolbar;

    private TextView toolbar_title;

    private TextView tv_cancel;

    private WellBean xfWell;

    private int xfstatus = 1;

    private void LockedDialog(final WellBean data) {
        this.controlalertDialog = (new AlertDialog.Builder((Context)this, 0)).create();
        this.controlalertDialog.show();
        this.controlalertDialog.setContentView(R.layout.dialog_locked);
        Window window = this.controlalertDialog.getWindow();
        window.setLayout(-2, -2);
        window.setGravity(17);
     //   window.setWindowAnimations(2131493067);
        TextView textView2 = (TextView)window.findViewById(R.id.contral_title);
        TextView textView1 = (TextView)window.findViewById(R.id.contral_text);
        textView2.setText(data.get_wellname());
        List<WellAreaBean> list = DataSupport.where(new String[] { "_wellareaid = ?", data.get_wellareaid() }).find(WellAreaBean.class);
        if (!list.isEmpty())
            textView1.setText(((WellAreaBean)list.get(0)).get_wellareaname());
        final SlideSwitch slideSwitch = (SlideSwitch)window.findViewById(R.id.contral_btn);
        this.ischecked = true;
        slideSwitch.setSlideListener(new SlideSwitch.SlideListener() {
            public void close() {
                slideSwitch.setSlideable(true);
             //   ControlActivity.access$1502(ControlActivity.this, false);
            }

            public void open() {
                slideSwitch.setSlideable(true);
              //  ControlActivity.access$1502(ControlActivity.this, true);
            }
        });
        window.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ControlActivity.this.xfRelease(ControlActivity.this.xfWell, 39);
                ControlActivity.this.controlalertDialog.dismiss();
            }
        });
        window.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
               // ControlActivity.access$1802(ControlActivity.this, ProgressDialog.show((Context)ControlActivity.this, null, ControlActivity.this.getString(2131296434)));
                ControlActivity.this.mProgressDialog.setCanceledOnTouchOutside(true);
                if (ControlActivity.this.ischecked) {
                    ControlActivity.this.xfOpen(data, 35);
                    return;
                }
                ControlActivity.this.xfClose(data, 36);
            }
        });
    }

    @SuppressLint("WrongConstant")
    private void getBloodData(int paramInt) {
        if (paramInt == 0) {
            this.nextPage = 1;
            this.displyStart = 0;
            this.list.clear();
            queryData();
            return;
        }
        if (paramInt == 1) {
            this.nextPage++;
            if (this.nextPage > this.maxPage) {
                Toast.makeText((Context)this, getString(R.string.exit_current_num), 1).show();
                (new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        ControlActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                ControlActivity.this.mPullRefreshListView.onRefreshComplete();
                            }
                        });
                    }
                })).start();
                return;
            }
            this.displyStart += this.rows;
            queryData();
            return;
        }
    }

    private void hintKbTwo() {
        @SuppressLint("WrongConstant")
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService("input_method");
        if (inputMethodManager.isActive() && getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null)
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
    }

    private void initCtrl() {
       //this.toolbar.setNavigationIcon(2130903169);
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ControlActivity.this.finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        this.mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
//            public void onRefresh(PullToRefreshBase<ListView> param1PullToRefreshBase) {
//                if (param1PullToRefreshBase.isHeaderShown()) {
//                    ControlActivity.this.mPullRefreshListView.getLoadingLayoutProxy(true, false).setRefreshingLabel(ControlActivity.this.getString(2131296437));
//                    ControlActivity.this.mPullRefreshListView.getLoadingLayoutProxy(true, false).setPullLabel(ControlActivity.this.getString(2131296436));
//                    ControlActivity.this.mPullRefreshListView.getLoadingLayoutProxy(true, false).setReleaseLabel(ControlActivity.this.getString(2131296438));
//                    ControlActivity.this.getBloodData(0);
//                    return;
//                }
//                ControlActivity.this.mPullRefreshListView.getLoadingLayoutProxy(false, true).setRefreshingLabel(ControlActivity.this.getString(2131296481));
//                ControlActivity.this.mPullRefreshListView.getLoadingLayoutProxy(false, true).setPullLabel(ControlActivity.this.getString(2131296480));
//                ControlActivity.this.mPullRefreshListView.getLoadingLayoutProxy(false, true).setReleaseLabel(ControlActivity.this.getString(2131296482));
//                ControlActivity.this.getBloodData(1);
//            }
//        });
        this.ContralList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                ControlActivity.this.isLockedDialog(ControlActivity.this.list.get(param1Int - 1));
            }
        });
        this.btnSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ControlActivity.this.etSearch.setVisibility(View.VISIBLE);
                ControlActivity.this.tv_cancel.setVisibility(View.VISIBLE);
                ControlActivity.this.btnSearch.setVisibility(View.INVISIBLE);
            }
        });
        this.tv_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ControlActivity.this.etSearch.setVisibility(View.GONE);
                ControlActivity.this.etSearch.setText("");
                ControlActivity.this.tv_cancel.setVisibility(View.INVISIBLE);
                ControlActivity.this.btnSearch.setVisibility(View.VISIBLE);
                ControlActivity.this.hintKbTwo();
            }
        });
        this.etSearch.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable param1Editable) {}

            public void beforeTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
                ControlActivity.this.searchlist.clear();
            }

            public void onTextChanged(CharSequence param1CharSequence, int param1Int1, int param1Int2, int param1Int3) {
             //   ControlActivity.access$902(ControlActivity.this, ControlActivity.this.etSearch.getText().toString());
                if (ControlActivity.this.str.equals("")) {
                    ControlActivity.this.searchlist.clear();
                    ControlActivity.this.list.clear();
                    ControlActivity.this.queryData();
                    return;
                }
                if (ControlActivity.this.adapter != null) {
                    ControlActivity.this.searchlist.clear();
                    ControlActivity.this.list.clear();
                    List list = DataSupport.where(new String[] { "_wellname like ?", "%" + "aaaa" + "%" }).find(WellBean.class);
                    ControlActivity.this.searchlist.addAll(list);
                    ControlActivity.this.list.addAll(ControlActivity.this.searchlist);
                    ControlActivity.this.adapter.appendToList(ControlActivity.this.list);
                    ControlActivity.this.ContralList.setAdapter((ListAdapter)ControlActivity.this.adapter);
                    ControlActivity.this.adapter.notifyDataSetChanged();
                    return;
                }
            }
        });
    }

    private void initData() {
        this.token = getSharedPreferences("UserData", 0).getString("token", "");
        this.list = new ArrayList<WellBean>();
        this.searchlist = new ArrayList<WellBean>();
        this.adapter = new ContralListAdapter((Context)this);
        this.ContralList.setAdapter((ListAdapter)this.adapter);
        this.mProgressDialog = ProgressDialog.show((Context)this, null, getString(R.string.cast_expanded_controller_loading));
        this.mProgressDialog.setCanceledOnTouchOutside(true);
        queryData();
    }

    private void initView() {
        this.toolbar = (Toolbar)findViewById(R.id.toolbar_transparent);
        this.toolbar_title = (TextView)findViewById(R.id.toolbar_transparent_title);
       // this.toolbar_title.setText(getResources().getString(2131296476));
        this.mPullRefreshListView = (PullToRefreshListView)findViewById(R.id.contralList);
        this.ContralList = (ListView)this.mPullRefreshListView.getRefreshableView();
        this.mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        this.btnSearch = (ImageView)findViewById(R.id.search_wells);
        this.etSearch = (EditText)findViewById(R.id.et_search);
        this.tv_cancel = (TextView)findViewById(R.id.tv_cancel);
    }

    private void isLockedDialog(final WellBean data) {
        final AlertDialog alertDialog = (new AlertDialog.Builder((Context)this, 0)).create();
        alertDialog.show();
        alertDialog.setContentView(R.layout.dialog_islocked);
        Window window = alertDialog.getWindow();
        window.setLayout(-2, -2);
        window.setGravity(17);
    //    window.setWindowAnimations(2131493067);
        ((TextView)window.findViewById(R.id.message)).setText("Is the oil " + data.get_wellname() + " locked?");
        window.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
              //  ControlActivity.access$1302(ControlActivity.this, data);
                ControlActivity.this.xfControl(ControlActivity.this.xfWell, 38);
                alertDialog.dismiss();
            }
        });
    }

    private void isUnLockedDialog() {
        final AlertDialog alertDialog = (new AlertDialog.Builder((Context)this, 0)).create();
        alertDialog.show();
        alertDialog.setContentView(R.layout.dialog_isunlocked);
        Window window = alertDialog.getWindow();
        window.setLayout(-2, -2);
        window.setGravity(17);
     //   window.setWindowAnimations(2131493067);
        ((TextView)window.findViewById(R.id.message)).setText("This well has been locked by other users !");
        window.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                alertDialog.dismiss();
            }
        });
    }

    private void queryData() {
        WellData wellData = new WellData();
        wellData.setAreaID("-1");
        wellData.setWellname("");
        wellData.setsEcho(this.nextPage + "");
        wellData.setiDisplayStart(this.displyStart + "");
        wellData.setiDisplayLength(this.rows + "");
        wellData.setToken(this.token);
        String str = (new Gson()).toJson(wellData);
        Log.e("sendmsg ", "" + JSON.toJSONString(wellData));
     //   MainManager.getInstance().queryWell("-1", "", this.nextPage + "", this.displyStart + "", this.rows + "", this.token, str, (Subscriber)getSubscriber(3));
    }

    private void rClose(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[] { "_wellareaaddr" }).where(new String[] { "_wellareaid = ?", paramWellBean.get_wellareaid() }).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean)list.get(0)).get_wellareaaddr();
            CmdData.RcmdData rcmdData = new CmdData.RcmdData(paramInt, paramWellBean.get_wellid() + "#" + paramWellBean.get_welladdr() + "#" + str3 + "#" + paramWellBean.get_wellname() + "#" + '\001', "null", 4);
            Gson gson = new Gson();
            String str2 = gson.toJson(rcmdData);
            Log.e("sendmsg ", "" + str2);
            CmdData cmdData = new CmdData();
            cmdData.setJson(str2);
            cmdData.setStatus(this.rstatus);
            cmdData.setToken(this.token);
            String str1 = gson.toJson(cmdData);
            Log.e("sendmsg ", "" + JSON.toJSONString(cmdData));
         //   MainManager.getInstance().cmdPost(str2, this.rstatus, this.token, str1, (Subscriber)getSubscriber(23));
            return;
        }
        Log.e("sendmsg ", "");
    }

    private void rControl(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[] { "_wellareaaddr" }).where(new String[] { "_wellareaid = ?", paramWellBean.get_wellareaid() }).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean)list.get(0)).get_wellareaaddr();
            CmdData.RcmdData rcmdData = new CmdData.RcmdData(paramInt, paramWellBean.get_wellid() + "#" + paramWellBean.get_welladdr() + "#" + str3 + "#" + paramWellBean.get_wellname(), "null", 4);
            Gson gson = new Gson();
            String str2 = gson.toJson(rcmdData);
            Log.e("sendmsg ", "" + str2);
            CmdData cmdData = new CmdData();
            cmdData.setJson(str2);
            cmdData.setStatus(this.rstatus);
            cmdData.setToken(this.token);
            String str1 = gson.toJson(cmdData);
            Log.e("sendmsg ", "" + JSON.toJSONString(cmdData));
        //    MainManager.getInstance().cmdPost(str2, this.rstatus, this.token, str1, (Subscriber)getSubscriber(19));
            return;
        }
        Log.e("sendmsg ", "");
    }

    private void rOpen(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[] { "_wellareaaddr" }).where(new String[] { "_wellareaid = ?", paramWellBean.get_wellareaid() }).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean)list.get(0)).get_wellareaaddr();
            CmdData.RcmdData rcmdData = new CmdData.RcmdData(paramInt, paramWellBean.get_wellid() + "#" + paramWellBean.get_welladdr() + "#" + str3 + "#" + paramWellBean.get_wellname() + "#" + '\001', "null", 4);
            Gson gson = new Gson();
            String str2 = gson.toJson(rcmdData);
            Log.e("sendmsg ", "" + str2);
            CmdData cmdData = new CmdData();
            cmdData.setJson(str2);
            cmdData.setStatus(this.rstatus);
            cmdData.setToken(this.token);
            String str1 = gson.toJson(cmdData);
            Log.e("sendmsg ", "" + JSON.toJSONString(cmdData));
         //   MainManager.getInstance().cmdPost(str2, this.rstatus, this.token, str1, (Subscriber)getSubscriber(21));
            return;
        }
        Log.e("sendmsg ", "");
    }

    private void xfClose(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[] { "_wellareaaddr" }).where(new String[] { "_wellareaid = ?", paramWellBean.get_wellareaid() }).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean)list.get(0)).get_wellareaaddr();
            CmdData.RcmdData rcmdData = new CmdData.RcmdData(paramInt, paramWellBean.get_wellid() + "#" + paramWellBean.get_welladdr() + "#" + str3 + "#" + paramWellBean.get_wellname() + "#" + '\001', "null", 4);
            Gson gson = new Gson();
            String str2 = gson.toJson(rcmdData);
            Log.e("sendmsg ", "" + str2);
            CmdData cmdData = new CmdData();
            cmdData.setJson(str2);
            cmdData.setStatus(this.xfstatus);
            cmdData.setToken(this.token);
            String str1 = gson.toJson(cmdData);
            Log.e("sendmsg ", "" + JSON.toJSONString(cmdData));
        //    MainManager.getInstance().cmdPost(str2, this.xfstatus, this.token, str1, (Subscriber)getSubscriber(22));
            return;
        }
        Log.e("sendmsg ", "");
    }

    private void xfControl(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[] { "_wellareaaddr" }).where(new String[] { "_wellareaid = ?", paramWellBean.get_wellareaid() }).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean)list.get(0)).get_wellareaaddr();
            CmdData.RcmdData rcmdData = new CmdData.RcmdData(paramInt, paramWellBean.get_wellid() + "#" + paramWellBean.get_welladdr() + "#" + str3 + "#" + paramWellBean.get_wellname(), "null", 4);
            Gson gson = new Gson();
            String str2 = gson.toJson(rcmdData);
            Log.e("sendmsg ", "" + str2);
            CmdData cmdData = new CmdData();
            cmdData.setJson(str2);
            cmdData.setStatus(this.xfstatus);
            cmdData.setToken(this.token);
            String str1 = gson.toJson(cmdData);
            Log.e("sendmsg ", "" + JSON.toJSONString(cmdData));
         //   MainManager.getInstance().cmdPost(str2, this.xfstatus, this.token, str1, (Subscriber)getSubscriber(10));
            return;
        }
        Log.e("sendmsg ", "");
    }

    private void xfOpen(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[] { "_wellareaaddr" }).where(new String[] { "_wellareaid = ?", paramWellBean.get_wellareaid() }).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean)list.get(0)).get_wellareaaddr();
            CmdData.RcmdData rcmdData = new CmdData.RcmdData(paramInt, paramWellBean.get_wellid() + "#" + paramWellBean.get_welladdr() + "#" + str3 + "#" + paramWellBean.get_wellname() + "#" + '\001', "null", 4);
            Gson gson = new Gson();
            String str2 = gson.toJson(rcmdData);
            Log.e("sendmsg ", "" + str2);
            CmdData cmdData = new CmdData();
            cmdData.setJson(str2);
            cmdData.setStatus(this.xfstatus);
            cmdData.setToken(this.token);
            String str1 = gson.toJson(cmdData);
            Log.e("sendmsg ", "" + JSON.toJSONString(cmdData));
         //   MainManager.getInstance().cmdPost(str2, this.xfstatus, this.token, str1, (Subscriber)getSubscriber(20));
            return;
        }
        Log.e("sendmsg ", "");
    }

    private void xfRelease(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.where(new String[] { "_wellareaid = ?", paramWellBean.get_wellareaid() }).find(WellAreaBean.class);
        if (!list.isEmpty()) {
            String str3 = ((WellAreaBean)list.get(0)).get_wellareaaddr();
            CmdData.RcmdData rcmdData = new CmdData.RcmdData(paramInt, paramWellBean.get_wellid() + "#" + paramWellBean.get_welladdr() + "#" + str3 + "#" + paramWellBean.get_wellname(), "null", 4);
            Gson gson = new Gson();
            String str2 = gson.toJson(rcmdData);
            Log.e("sendmsg ", "" + str2);
            CmdData cmdData = new CmdData();
            cmdData.setJson(str2);
            cmdData.setStatus(this.xfstatus);
            cmdData.setToken(this.token);
            String str1 = gson.toJson(cmdData);
            Log.e("sendmsg ", "" + JSON.toJSONString(cmdData));
         //   MainManager.getInstance().cmdPost(str2, this.xfstatus, this.token, str1, (Subscriber)getSubscriber(24));
            return;
        }
        Log.e("sendmsg ", "");
    }

    public void onCompleted(int paramInt) {
        this.mPullRefreshListView.onRefreshComplete();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_control);
        if (Build.VERSION.SDK_INT > 18) {
            int i = 0;
            int j = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (j > 0)
                i = getResources().getDimensionPixelSize(j);
//            ((LinearLayout)findViewById(2131886761)).setLayoutParams((ViewGroup.LayoutParams)new RelativeLayout.LayoutParams(getWindowManager().getDefaultDisplay().getWidth(), i));
        }
        initView();
        initData();
        initCtrl();
    }

    protected void onDestroy() {
        super.onDestroy();
        hintKbTwo();
    }

    @SuppressLint("WrongConstant")
    public void onError(Throwable paramThrowable, int paramInt) {
        if (this.mProgressDialog != null)
            this.mProgressDialog.dismiss();
        Toast.makeText((Context)this, getString(R.string.exit_current_num), 1).show();
    }

    public void onNext(Object paramObject, int paramInt) {
//        if (paramInt == 3) {
//            if (this.mProgressDialog != null)
//                this.mProgressDialog.dismiss();
//            paramObject = paramObject;
//            if (paramObject.getMessage().length() > 0) {
//                List<? extends WellBean> list = StringGsonToObject.stringToList(paramObject.getMessage().trim(), WellBean.class);
//                if (this.maxPage == 0)
//                    this.maxPage = MaxPageReturn.returnMaxPage(this.rows, paramObject.getiTotalRecords());
//                this.list.addAll(list);
//                this.adapter.appendToList(this.list);
//                this.adapter.notifyDataSetChanged();
//            }
//            return;
//        }
//        if (paramInt == 10) {
//            if (((CmdData)paramObject).getResult().equals("1")) {
//                Log.e("action ", ");
//                        rControl(this.xfWell, 38);
//                return;
//            }
//            return;
//        }
//        if (paramInt == 19) {
//            if (((CmdData)paramObject).getMessageExt().equals("6")) {
//                Log.e("action ", ");
//                        LockedDialog(this.xfWell);
//                return;
//            }
//            isUnLockedDialog();
//            return;
//        }
//        if (paramInt == 20) {
//            if (((CmdData)paramObject).getResult().equals("1")) {
//                Log.e("action ", ");
//                this.handler.postDelayed(this.runnable, 1000L);
//                return;
//            }
//            return;
//        }
//        if (paramInt == 21) {
//            paramObject = paramObject;
//            if (paramObject.getMessageExt().equals("3") && paramObject.getMessage().equals("1")) {
//                Log.e("action ", ");
//                if (this.mProgressDialog != null)
//                    this.mProgressDialog.dismiss();
//                Toast.makeText((Context)this, "The pumping unit started successfully", 1).show();
//                this.handler.removeCallbacks(this.runnable);
//                this.controlalertDialog.dismiss();
//                xfRelease(this.xfWell, 39);
//                return;
//            }
//            if (paramObject.getMessageExt().equals("1")) {
//                Log.e("action ", ");
//                return;
//            }
//            Log.e("action ", ");
//            if (this.mProgressDialog != null)
//                this.mProgressDialog.dismiss();
//            Toast.makeText((Context)this, "The pumping unit failed to start", 1).show();
//            this.handler.removeCallbacks(this.runnable);
//            this.controlalertDialog.dismiss();
//            xfRelease(this.xfWell, 39);
//            return;
//        }
//        if (paramInt == 22) {
//            if (((CmdData)paramObject).getResult().equals("1")) {
//                Log.e("action ", ");
//                this.handler.postDelayed(this.runnable2, 1000L);
//                return;
//            }
//            return;
//        }
//        if (paramInt == 23) {
//            paramObject = paramObject;
//            if (paramObject.getMessageExt().equals("3") && paramObject.getMessage().equals("1")) {
//                Log.e("action ", ");
//                if (this.mProgressDialog != null)
//                    this.mProgressDialog.dismiss();
//                Toast.makeText((Context)this, "The pumping unit closed successfully", 1).show();
//                this.handler.removeCallbacks(this.runnable2);
//                this.controlalertDialog.dismiss();
//                xfRelease(this.xfWell, 39);
//                return;
//            }
//            if (paramObject.getMessageExt().equals("1")) {
//                Log.e("action ", "");
//                return;
//            }
//            Log.e("action ", "");
//            if (this.mProgressDialog != null)
//                this.mProgressDialog.dismiss();
//            Toast.makeText((Context)this, "The pumping unit failed to close", 1).show();
//            this.handler.removeCallbacks(this.runnable2);
//            this.controlalertDialog.dismiss();
//            xfRelease(this.xfWell, 39);
//            return;
//        }
//        if (paramInt == 24 && ((CmdData)paramObject).getResult().equals("1")) {
//            Log.e("action ", "");
//            return;
//        }
    }

    protected void onResume() {
        super.onResume();
        this.btnSearch.setVisibility(View.VISIBLE);
        this.etSearch.setVisibility(View.GONE);
        this.tv_cancel.setVisibility(View.INVISIBLE);
        hintKbTwo();
    }
}
