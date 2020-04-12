package com.xteamsoft.digitalpumper.mainWells;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xteamsoft.baselibrary.base.BaseActivity;
import com.xteamsoft.baselibrary.utils.TimeUtils;
import com.xteamsoft.baselibrary.utilsLT.MaxPageReturn;
import com.xteamsoft.baselibrary.utilsLT.StringGsonToObject;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.adapterReports.adapter.AbsCommonAdapter;
import com.xteamsoft.digitalpumper.adapterReports.adapter.AbsViewHolder;
import com.xteamsoft.digitalpumper.bean.WellBean;
import com.xteamsoft.digitalpumper.beanReports.ReportsData;
import com.xteamsoft.digitalpumper.manager.MainManager;
import com.xteamsoft.digitalpumper.widget.CustomDatePicker;
import com.xteamsoft.digitalpumper.widget.SyncHorizontalScrollView;
import com.xteamsoft.digitalpumper.widget.pullrefresh.AbPullToRefreshView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.litepal.crud.DataSupport;

public class ReportDetailActivity extends BaseActivity {
    private LinearLayout chooseTime;

    private SyncHorizontalScrollView contentHorScv;

    private CustomDatePicker customDatePicker;

    private int displyStart = 0;

    private String etime;

    private ListView leftListView;

    private AbsCommonAdapter<ReportsData.MessageReports> mLeftAdapter;

    private ProgressDialog mProgressDialog;

    private AbsCommonAdapter<ReportsData.MessageReports> mRightAdapter;

    private SparseArray<TextView> mTitleTvArray;

    private int maxPage = 0;

    private List<ReportsData.MessageReports> mlist;

    private int nextPage = 1;

    private AbPullToRefreshView pulltorefreshview;

    private TextView reportTime;

    private ListView rightListView;

    private LinearLayout right_title_container;

    private int rows = 9999;

    private String stime;

    private SyncHorizontalScrollView titleHorScv;

    private String token;

    private Toolbar toolbar;

    private TextView toolbar_title;

    private TextView tv_table_title_left;

    private void chooseTimeDialog() {
//        final AlertDialog alertDialog = (new AlertDialog.Builder((Context)this, 2131493126)).create();
//        alertDialog.show();
//        alertDialog.setContentView(R.layout);
//        Window window = alertDialog.getWindow();
//        window.setLayout(-1, -2);
//        window.setGravity(80);
//        window.setWindowAnimations(2131493067);
//        final TextView starttime = (TextView)window.findViewById(2131886489);
//        final TextView endtime = (TextView)window.findViewById(2131886490);
//        textView1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                ReportDetailActivity.this.initDatePicker(starttime);
//                ReportDetailActivity.this.customDatePicker.show(starttime.getText().toString());
//            }
//        });
//        textView2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                ReportDetailActivity.this.initDatePicker(endtime);
//                ReportDetailActivity.this.customDatePicker.show(endtime.getText().toString());
//            }
//        });
//        window.findViewById(2131886492).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                if (starttime.getText().equals("") || endtime.getText().equals("")) {
//                    Toast.makeText((Context)ReportDetailActivity.this, "please choose Strattime or Endtime", 1).show();
//                    return;
//                }
//                ReportDetailActivity.access$302(ReportDetailActivity.this, starttime.getText().toString() + ":00");
//                ReportDetailActivity.access$402(ReportDetailActivity.this, endtime.getText().toString() + ":00");
//                ReportDetailActivity.this.mlist.clear();
//                ReportDetailActivity.this.queryData(ReportDetailActivity.this.stime, ReportDetailActivity.this.etime);
//                alertDialog.dismiss();
//            }
//        });
//        window.findViewById(2131886493).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                String str = (new SimpleDateFormat("yyyy-MM-dd", Locale.US)).format(new Date());
//                ReportDetailActivity.access$302(ReportDetailActivity.this, str + " 00:00:00");
//                ReportDetailActivity.access$402(ReportDetailActivity.this, str + " 23:59:59");
//                ReportDetailActivity.this.mlist.clear();
//                ReportDetailActivity.this.queryData(ReportDetailActivity.this.stime, ReportDetailActivity.this.etime);
//                alertDialog.dismiss();
//            }
//        });
//        window.findViewById(2131886469).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                alertDialog.dismiss();
//            }
//        });
    }

    private void findTitleTextViewIds() {
//        this.mTitleTvArray = new SparseArray();
//        for (int i = 0; i <= 20; i++) {
//            try {
//                int j = R.id.class.getField("tv_table_title_0").getInt(new R.id());
//                TextView textView = (TextView) findViewById(j);
//                this.mTitleTvArray.put(j, textView);
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        }
    }

    private double getTwoDecimal(double paramDouble) {
        return Double.valueOf((new DecimalFormat("#.00")).format(paramDouble)).doubleValue();
    }

    private void initCtrl() {
        //    this.toolbar.setNavigationIcon(2130903169);
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ReportDetailActivity.this.finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.chooseTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ReportDetailActivity.this.chooseTimeDialog();
            }
        });
    }

    private void initData() {
        this.token = getSharedPreferences("UserData", 0).getString("token", "");
        this.mlist = new ArrayList<ReportsData.MessageReports>();
        String str = (new SimpleDateFormat("yyyy-MM-dd", Locale.US)).format(new Date());
        this.stime = str + " 00:00:00";
        this.etime = str + " 23:59:59";
        queryData(this.stime, this.etime);
    }

    private void initDatePicker(final TextView tv) {
        String str = (new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US)).format(new Date());
        tv.setText(str);
        this.customDatePicker = new CustomDatePicker((Context) this, new CustomDatePicker.ResultHandler() {
            public void handle(String param1String) {
                tv.setText(param1String);
            }
        }, "2010-01-01 00:00", str);
        this.customDatePicker.showSpecificTime(true);
        this.customDatePicker.setIsLoop(true);
    }

    private void initView() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
        this.toolbar_title = (TextView) findViewById(R.id.toolbar_transparent_title);
        this.toolbar_title.setText("Data Report");
        this.pulltorefreshview = (AbPullToRefreshView) findViewById(R.id.pulltorefreshview);
        this.pulltorefreshview.setPullRefreshEnable(false);
        this.pulltorefreshview.setLoadMoreEnable(false);
        this.tv_table_title_left = (TextView) findViewById(R.id.tv_table_title_left);
        this.tv_table_title_left.setText("Well");
        this.leftListView = (ListView) findViewById(R.id.left_container_listview);
        this.rightListView = (ListView) findViewById(R.id.right_container_listview);
        this.right_title_container = (LinearLayout) findViewById(R.id.right_title_container);
        // getLayoutInflater().inflate(2130968737, (ViewGroup) this.right_title_container);
        this.titleHorScv = (SyncHorizontalScrollView) findViewById(R.id.title_horsv);
        this.contentHorScv = (SyncHorizontalScrollView) findViewById(R.id.content_horsv);
        this.titleHorScv.setScrollView((View) this.contentHorScv);
        this.contentHorScv.setScrollView((View) this.titleHorScv);
        this.chooseTime = (LinearLayout) findViewById(R.id.wellLinear_buttom);
        this.reportTime = (TextView) findViewById(R.id.oneWellTime_name);
        findTitleTextViewIds();
        initTableView();
    }

    private void queryData(String paramString1, String paramString2) {
        ReportsData reportsData = new ReportsData();
        reportsData.setId("-1");
        reportsData.setBegin(paramString1);
        reportsData.setEnd(paramString2);
        reportsData.setsEcho(this.nextPage + "");
        reportsData.setiDisplayStart(this.displyStart + "");
        reportsData.setiDisplayLength(this.rows + "");
        reportsData.setToken(this.token);
        String str = (new Gson()).toJson(reportsData);
        Log.e("sendmsg ", "" + JSON.toJSONString(str));
        //    MainManager.getInstance().queryReports("-1", paramString1, paramString2, this.nextPage + "", this.displyStart + "", this.rows + "", this.token, str, (Subscriber) getSubscriber(9));
        this.mProgressDialog = ProgressDialog.show((Context) this, null, "Loading...");
        this.mProgressDialog.setCanceledOnTouchOutside(true);
    }

    private void setTable(List<ReportsData.MessageReports> paramList) {
        ArrayList<String> arrayList = new ArrayList();
        int i;
        for (i = 0; i < paramList.size(); i++) {
            List<WellBean> list = DataSupport.where(new String[]{"_wellid = ?", ((ReportsData.MessageReports) paramList.get(i)).get_wellid()}).find(WellBean.class);
            if (!list.isEmpty())
                arrayList.add(((WellBean) list.get(0)).get_wellname());
        }
        if (arrayList.size() > 0) {
            ArrayList<ReportsData.MessageReports> arrayList1 = new ArrayList();
            WellBean wellBean = null;
            for (i = 0; i < paramList.size(); i++) {
                List<WellBean> list = DataSupport.where(new String[]{"_wellid = ?", ((ReportsData.MessageReports) paramList.get(i)).get_wellid()}).find(WellBean.class);
                if (!list.isEmpty())
                    wellBean = list.get(0);
                ReportsData.MessageReports messageReports = new ReportsData.MessageReports();
                messageReports.setOrgCode(arrayList.get(i));
                messageReports.setLeftTitle(arrayList.get(i));
                double d1 = Double.valueOf(wellBean.get_oiltank1area()).doubleValue();
                double d2 = Double.valueOf(wellBean.get_oiltank1height()).doubleValue();
                double d3 = Double.valueOf(wellBean.get_oiltank2area()).doubleValue();
                double d4 = Double.valueOf(wellBean.get_oiltank2height()).doubleValue();
                double d5 = Double.valueOf(wellBean.get_watertank1area()).doubleValue();
                double d6 = Double.valueOf(wellBean.get_watertank1height()).doubleValue();
                double d7 = Double.valueOf(wellBean.get_watertank2area()).doubleValue();
                double d8 = Double.valueOf(wellBean.get_watertank2height()).doubleValue();
                messageReports.set_drepotank1fpos(getTwoDecimal(d1 * d2 + d3 * d4) + "");
                messageReports.set_drepaccflow(((ReportsData.MessageReports) paramList.get(i)).get_drepaccflow());
                messageReports.set_drepwtank1fpos(getTwoDecimal(d5 * d6 + d7 * d8) + "");
                messageReports.set_dreptubepres(((ReportsData.MessageReports) paramList.get(i)).get_dreptubepres());
                messageReports.set_drepcaspres(((ReportsData.MessageReports) paramList.get(i)).get_drepcaspres());
                messageReports.set_drepfluidlpos(((ReportsData.MessageReports) paramList.get(i)).get_drepfluidlpos());
                messageReports.set_drepdate(TimeUtils.millis2String(Long.parseLong(((ReportsData.MessageReports) paramList.get(i)).get_drepdate().substring(5, ((ReportsData.MessageReports) paramList.get(i)).get_drepdate().length() - 6))));
                arrayList1.add(messageReports);
            }
            this.mLeftAdapter.addData(arrayList1, false);
            this.mRightAdapter.addData(arrayList1, false);
            arrayList1.clear();
        }
    }

    public void initTableView() {
        this.mLeftAdapter = new AbsCommonAdapter<ReportsData.MessageReports>((Context) this, 2130968735) {
            public void convert(AbsViewHolder param1AbsViewHolder, ReportsData.MessageReports param1MessageReports, int param1Int) {
                TextView textView = (TextView) param1AbsViewHolder.getView(2131886736);
                LinearLayout linearLayout = (LinearLayout) param1AbsViewHolder.getView(2131886735);
                textView.setText(param1MessageReports.getLeftTitle());
            }
        };
        this.mRightAdapter = new AbsCommonAdapter<ReportsData.MessageReports>((Context) this, 2130968736) {
            public void convert(AbsViewHolder param1AbsViewHolder, ReportsData.MessageReports param1MessageReports, int param1Int) {
                TextView textView1 = (TextView) param1AbsViewHolder.getView(2131886738);
                TextView textView2 = (TextView) param1AbsViewHolder.getView(2131886739);
                TextView textView3 = (TextView) param1AbsViewHolder.getView(2131886740);
                TextView textView4 = (TextView) param1AbsViewHolder.getView(2131886741);
                TextView textView5 = (TextView) param1AbsViewHolder.getView(2131886742);
                TextView textView6 = (TextView) param1AbsViewHolder.getView(2131886743);
                TextView textView7 = (TextView) param1AbsViewHolder.getView(2131886744);
                LinearLayout linearLayout = (LinearLayout) param1AbsViewHolder.getView(2131886737);
                textView1.setText(param1MessageReports.get_drepotank1fpos());
                textView2.setText(param1MessageReports.get_drepaccflow());
                textView3.setText(param1MessageReports.get_drepwtank1fpos());
                textView4.setText(param1MessageReports.get_dreptubepres());
                textView5.setText(param1MessageReports.get_drepcaspres());
                textView6.setText(param1MessageReports.get_drepfluidlpos());
                textView7.setText(param1MessageReports.get_drepdate());
            }
        };
        this.leftListView.setAdapter((ListAdapter) this.mLeftAdapter);
        this.rightListView.setAdapter((ListAdapter) this.mRightAdapter);
    }

    public void onCompleted(int paramInt) {
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_report_detail);
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

    public void onError(Throwable paramThrowable, int paramInt) {
        if (this.mProgressDialog != null)
            this.mProgressDialog.dismiss();
        Toast.makeText((Context) this, getString(R.string.confirm_alarm_error), Toast.LENGTH_LONG).show();
    }

    public void onNext(Object paramObject, int paramInt) {
//        if (paramInt == 9) {
//            paramObject = paramObject;
//            if (paramObject.getMessage().length() > 0) {
//                List<? extends ReportsData.MessageReports> list = StringGsonToObject.stringToList(paramObject.getMessage().replace("\\/", "").trim(), ReportsData.MessageReports.class);
//                if (this.maxPage == 0)
//                    this.maxPage = MaxPageReturn.returnMaxPage(this.rows, paramObject.getiTotalRecords());
//                this.mlist.addAll(list);
//                paramObject = TimeUtils.millis2String(Long.parseLong(((ReportsData.MessageReports) this.mlist.get(0)).get_drepdate().substring(5, ((ReportsData.MessageReports) this.mlist.get(0)).get_drepdate().length() - 6)));
//                this.reportTime.setText(paramObject.substring(0, paramObject.length() - 9));
//                setTable(this.mlist);
//                this.mLeftAdapter.notifyDataSetChanged();
//                this.mRightAdapter.notifyDataSetChanged();
//                if (this.mProgressDialog != null)
//                    this.mProgressDialog.dismiss();
//            }
//        }
    }
}
