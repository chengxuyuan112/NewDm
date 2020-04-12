package com.xteamsoft.digitalpumper.mainWells;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
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
import com.xteamsoft.baselibrary.base.BaseActivity;
import com.xteamsoft.baselibrary.utils.TimeUtils;
import com.xteamsoft.baselibrary.utilsLT.MaxPageReturn;
import com.xteamsoft.baselibrary.utilsLT.StringGsonToObject;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.adapterWells.ChartTimeListAdapter;
import com.xteamsoft.digitalpumper.adapterWells.PopWellAdapter;
import com.xteamsoft.digitalpumper.bean.ChartData;
import com.xteamsoft.digitalpumper.bean.WellBean;
import com.xteamsoft.digitalpumper.manager.MainManager;
import com.xteamsoft.digitalpumper.widget.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import org.litepal.crud.DataSupport;

public class ChartActivity extends BaseActivity {
    private ChartData.MessageChart chartBean;

   // private TextView chartTime;

    private LinearLayout chooseTime;

    private ImageView chooseWell;

    private CustomDatePicker customDatePicker;

    private int displyStart = 0;

    private String etime;

    private LineChartView lineChart;

    private List<AxisValue> mAxisXValues = new ArrayList<AxisValue>();

    private List<PointValue> mPointValues = new ArrayList<PointValue>();

    private ProgressDialog mProgressDialog;

    private int maxPage = 0;

    private List<ChartData.MessageChart> mlist;

    private int nextPage = 1;

    private int rows = 15;

    private ImageView showTimeList;

    private String stime;

    public List<ChartData.MessageChart> timelist = new ArrayList<ChartData.MessageChart>();

    private String token;

    private Toolbar toolbar;

    private TextView toolbar_title;

    private TextView tvMax;

    private TextView tvMin;

    private TextView tvPoints;

    private TextView tvSamp;

    private TextView tvSpeed;

    private WellBean wellBean;

    //private TextView wellName;

    private String wellid;

    private List<WellBean> welllist;

    private Integer[] xarr;

    private Integer[] yarr;

    private void chooseTimeDialog() {
//        final AlertDialog alertDialog = (new AlertDialog.Builder((Context) this, 0)).create();
//        alertDialog.show();
//        alertDialog.setContentView(R.layout.alertdialog_time_indicator);
//        Window window = alertDialog.getWindow();
//        window.setLayout(-1, -2);
//        window.setGravity(80);
//        //window.setWindowAnimations(R.style);
//        final TextView starttime = (TextView) window.findViewById(R.id.starttime);
//        final TextView endtime = (TextView) window.findViewById(R.id.endtime);
//        textView1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                ChartActivity.this.initDatePicker(starttime);
//                ChartActivity.this.customDatePicker.show(starttime.getText().toString());
//            }
//        });
//        textView2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                ChartActivity.this.initDatePicker(endtime);
//                ChartActivity.this.customDatePicker.show(endtime.getText().toString());
//            }
//        });
//        window.findViewById(2131886491).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                Intent intent = new Intent((Context) ChartActivity.this, TimeListActivity.class);
//                intent.putExtra("wellid", ChartActivity.this.wellid);
//                intent.putExtra("start", starttime.getText().toString() + ":00");
//                intent.putExtra("end", endtime.getText().toString() + ":00");
//                ChartActivity.this.startActivity(intent);
//                alertDialog.dismiss();
//            }
//        });
//        window.findViewById(2131886469).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                alertDialog.dismiss();
//            }
//        });
    }

    private void chooseTimeListDialog() {
        final AlertDialog alertDialog = (new AlertDialog.Builder((Context) this, 0)).create();
        alertDialog.show();
        alertDialog.setContentView(R.layout.alertdialog_chart_timelist);
        Window window = alertDialog.getWindow();
        window.setLayout(-1, -2);
        window.setGravity(80);
        // window.setWindowAnimations(2131493067);
        PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) window.findViewById(R.id.popTimeList);
        ListView listView = (ListView) pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
        ChartTimeListAdapter chartTimeListAdapter = new ChartTimeListAdapter((Context) this);
        chartTimeListAdapter.appendToList(this.timelist);
        listView.setAdapter((ListAdapter) chartTimeListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
                // ChartActivity.access$1302(ChartActivity.this, ChartActivity.this.timelist.get(param1Int - 1));
                Object[] arrayOfObject = ChartActivity.splitAry(ChartActivity.this.chartBean.get_loaddata(), 8);
                ArrayList<Integer> arrayList1 = new ArrayList();
                ArrayList<Integer> arrayList2 = new ArrayList();
                int j = arrayOfObject.length;
                for (param1Int = 0; param1Int < j; param1Int++) {
                    int[] arrayOfInt = (int[]) arrayOfObject[param1Int];
                    for (int k = 0; k < arrayOfInt.length; k++) {
                        if (k < 4) {
                            arrayList1.add(Integer.valueOf(arrayOfInt[k]));
                        } else if (k >= 4 && k < 8) {
                            arrayList2.add(Integer.valueOf(arrayOfInt[k]));
                        }
                    }
                }
                // ChartActivity.access$1502(ChartActivity.this, arrayList1.<Integer>toArray(new Integer[arrayList1.size()]));
                // ChartActivity.access$1602(ChartActivity.this, arrayList2.<Integer>toArray(new Integer[arrayList2.size()]));
                Log.e("x", "" + Arrays.toString((Object[]) ChartActivity.this.xarr));
                Log.e("y", "" + Arrays.toString((Object[]) ChartActivity.this.yarr));
                param1Int = ((Integer) Collections.<Integer>min(Arrays.asList(ChartActivity.this.yarr))).intValue();
                int i = ((Integer) Collections.<Integer>max(Arrays.asList(ChartActivity.this.yarr))).intValue();
                ChartActivity.this.tvPoints.setText(ChartActivity.this.chartBean.get_loadpointnum());
                ChartActivity.this.tvSpeed.setText(ChartActivity.this.chartBean.get_loadspeed());
                ChartActivity.this.tvMax.setText(i + "");
                ChartActivity.this.tvMin.setText(param1Int + "");
                ChartActivity.this.tvSamp.setText(ChartActivity.this.chartBean.get_loadsampling());
                ChartActivity.this.getAxisXLables(ChartActivity.this.xarr);
                ChartActivity.this.getAxisPoints(ChartActivity.this.yarr);
                ChartActivity.this.initLineChart();
                String str = TimeUtils.millis2String(Long.parseLong(ChartActivity.this.chartBean.get_loaddatetime().substring(5, ChartActivity.this.chartBean.get_loaddatetime().length() - 6)));
             //   ChartActivity.this.chartTime.setText(str.substring(0, str.length() - 9));
                alertDialog.dismiss();
            }
        });
//        window.findViewById(2131886469).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                alertDialog.dismiss();
//            }
//        });
    }

    private void chooseWellDialog() {
//        final AlertDialog alertDialog = (new AlertDialog.Builder((Context) this, 0)).create();
//        alertDialog.show();
//        alertDialog.setContentView(R.layout.dialog);
//        Window window = alertDialog.getWindow();
//        window.setLayout(-1, -2);
//        window.setGravity(80);
//       // window.setWindowAnimations(2131493067);
//        PullToRefreshListView pullToRefreshListView = (PullToRefreshListView) window.findViewById(2131886488);
//        ListView listView = (ListView) pullToRefreshListView.getRefreshableView();
//        pullToRefreshListView.setMode(PullToRefreshBase.Mode.DISABLED);
//        PopWellAdapter popWellAdapter = new PopWellAdapter((Context) this);
//        popWellAdapter.appendToList(this.welllist);
//        listView.setAdapter((ListAdapter) popWellAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
//                ChartActivity.access$302(ChartActivity.this, ChartActivity.this.welllist.get(param1Int - 1));
//                ChartActivity.this.wellName.setText(ChartActivity.this.wellBean.get_wellname());
//                ChartActivity.access$602(ChartActivity.this, ChartActivity.this.wellBean.get_wellid());
//                ChartActivity.this.mlist.clear();
//                ChartActivity.this.timelist.clear();
//                ChartActivity.this.timelist.addAll(ChartActivity.this.mlist);
//                ChartActivity.this.queryData(ChartActivity.this.wellid, ChartActivity.this.stime, ChartActivity.this.etime);
//                alertDialog.dismiss();
//            }
//        });
//        window.findViewById(2131886469).setOnClickListener(new View.OnClickListener() {
//            public void onClick(View param1View) {
//                alertDialog.dismiss();
//            }
//        });
    }

    private void getAxisPoints(Integer[] paramArrayOfInteger) {
        this.mPointValues.clear();
        for (int i = 0; i < paramArrayOfInteger.length; i++)
            this.mPointValues.add(new PointValue(i, Float.parseFloat(String.valueOf(paramArrayOfInteger[i]))));
    }

    private void getAxisXLables(Integer[] paramArrayOfInteger) {
        this.mAxisXValues.clear();
        for (int i = 0; i < paramArrayOfInteger.length; i++)
            this.mAxisXValues.add((new AxisValue(i)).setLabel(String.valueOf(paramArrayOfInteger[i])));
    }

    private void initCtrl() {
        //    this.toolbar.setNavigationIcon(2130903169);
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ChartActivity.this.finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.chooseWell.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ChartActivity.this.chooseWellDialog();
            }
        });
        this.chooseTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ChartActivity.this.chooseTimeDialog();
            }
        });
        this.showTimeList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                ChartActivity.this.chooseTimeListDialog();
            }
        });
    }

    private void initData() {
        this.token = getSharedPreferences("UserData", 0).getString("token", "");
        this.mlist = new ArrayList<ChartData.MessageChart>();
        this.timelist = new ArrayList<ChartData.MessageChart>();
        this.welllist = new ArrayList<WellBean>();
        List<? extends WellBean> list = DataSupport.findAll(WellBean.class, new long[0]);
        this.welllist.addAll(list);
        list = DataSupport.findAll(WellBean.class, new long[0]);
        if (!list.isEmpty()) {
            this.wellid = ((WellBean) list.get(0)).get_wellid();
         //   this.wellName.setText(((WellBean) list.get(0)).get_wellname());
            String str = (new SimpleDateFormat("yyyy-MM-dd", Locale.US)).format(new Date());
            this.stime = str + " 00:00:00";
            this.etime = str + " 23:59:59";
        }
        queryData(this.wellid, this.stime, this.etime);
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

    private void initLineChart() {
        Axis axis1 = (new Axis()).setHasLines(true);
        Axis axis2 = new Axis();
        axis1.setName("lb");
        axis2.setName("ft");
        axis2.setValues(this.mAxisXValues);
        axis2.setLineColor(-1);
        axis1.setLineColor(-1);
        axis2.setTextColor(-1);
        axis1.setTextColor(-1);
        axis2.setTextSize(8);
        axis2.setTypeface(Typeface.DEFAULT);
        axis2.setHasTiltedLabels(true);
        axis2.setHasLines(false);
        axis1.setHasLines(false);
        axis2.setHasSeparationLine(true);
        axis2.setInside(false);
        ArrayList<Line> arrayList = new ArrayList();
        Line line = new Line(this.mPointValues);
        line.setColor(-65536);
        line.setStrokeWidth(1);
        line.setFilled(false);
        line.setCubic(false);
        line.setPointColor(-65536);
        line.setPointRadius(5);
        line.setHasLabels(true);
        line.setHasLines(true);
        line.setHasPoints(true);
        line.setShape(ValueShape.CIRCLE);
        line.setHasLabelsOnlyForSelected(false);
        arrayList.add(line);
        LineChartData lineChartData = new LineChartData(arrayList);
        lineChartData.setAxisYLeft(axis1);
        lineChartData.setAxisXBottom(axis2);
        lineChartData.setBaseValue(20.0F);
        lineChartData.setValueLabelBackgroundAuto(false);
        lineChartData.setValueLabelBackgroundColor(-16776961);
        lineChartData.setValueLabelBackgroundEnabled(false);
        lineChartData.setValueLabelsTextColor(-1);
        lineChartData.setValueLabelTextSize(15);
        lineChartData.setValueLabelTypeface(Typeface.MONOSPACE);
        this.lineChart.setLineChartData(lineChartData);
        this.lineChart.setZoomEnabled(true);
        this.lineChart.setInteractive(true);
        this.lineChart.setZoomType(ZoomType.HORIZONTAL);
        Viewport viewport = new Viewport(this.lineChart.getMaximumViewport());
        viewport.left = 0.0F;
        viewport.right = 7.0F;
        this.lineChart.setCurrentViewport(viewport);
    }

    private void initView() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
        this.toolbar_title = (TextView) findViewById(R.id.toolbar_transparent_title);
        this.toolbar_title.setText("Indicator diagram");
        this.lineChart = (LineChartView) findViewById(R.id.line_chart);
        this.chooseTime = (LinearLayout) findViewById(R.id.wellLinear_buttom);
        this.chooseWell = (ImageView) findViewById(R.id.oneWellDetail_choose);
        this.showTimeList = (ImageView) findViewById(R.id.oneWellTime_img);
       // this.wellName = (TextView) findViewById(2131886344);
        this.tvPoints = (TextView) findViewById(R.id.tv1);
        this.tvSpeed = (TextView) findViewById(R.id.tv2);
        this.tvMax = (TextView) findViewById(R.id.tv3);
        this.tvMin = (TextView) findViewById(R.id.tv4);
        this.tvSamp = (TextView) findViewById(R.id.tv5);
     //   this.chartTime = (TextView) findViewById(2131886340);
    }

    private void queryData(String paramString1, String paramString2, String paramString3) {
        ChartData chartData = new ChartData();
        chartData.setId(paramString1);
        chartData.setBegin(paramString2);
        chartData.setEnd(paramString3);
        chartData.setsEcho(this.nextPage + "");
        chartData.setiDisplayStart(this.displyStart + "");
        chartData.setiDisplayLength(this.rows + "");
        chartData.setToken(this.token);
        String str = (new Gson()).toJson(chartData);
        Log.e("sendmsg ", "" + JSON.toJSONString(str));
       // MainManager.getInstance().queryChart(paramString1, paramString2, paramString3, this.nextPage + "", this.displyStart + "", this.rows + "", this.token, str, (Subscriber) getSubscriber(8));
        this.mProgressDialog = ProgressDialog.show((Context) this, null, getString(R.string.cast_expanded_controller_loading));
        this.mProgressDialog.setCanceledOnTouchOutside(true);
    }

    private static Object[] splitAry(int[] paramArrayOfint, int paramInt) {
        int i;
        if (paramArrayOfint.length % paramInt == 0) {
            i = paramArrayOfint.length / paramInt;
        } else {
            i = paramArrayOfint.length / paramInt + 1;
        }
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList();
        for (int j = 0; j < i; j++) {
            ArrayList<Integer> arrayList1 = new ArrayList();
            int m = 0;
            int k;
            for (k = j * paramInt; m < paramInt && k < paramArrayOfint.length; k++) {
                arrayList1.add(Integer.valueOf(paramArrayOfint[k]));
                m++;
            }
            arrayList.add(arrayList1);
        }
        Object[] arrayOfObject = new Object[arrayList.size()];
        for (paramInt = 0; paramInt < arrayList.size(); paramInt++) {
            List list = arrayList.get(paramInt);
            int[] arrayOfInt = new int[list.size()];
            for (i = 0; i < list.size(); i++)
                arrayOfInt[i] = ((Integer) list.get(i)).intValue();
            arrayOfObject[paramInt] = arrayOfInt;
        }
        return arrayOfObject;
    }

    public void onCompleted(int paramInt) {
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_chart);
        if (Build.VERSION.SDK_INT > 18) {
            int i = 0;
            int j = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (j > 0)
                i = getResources().getDimensionPixelSize(j);
//            ((LinearLayout) findViewById(2131886761)).setLayoutParams((ViewGroup.LayoutParams) new RelativeLayout.LayoutParams(getWindowManager().getDefaultDisplay().getWidth(), i));
        }
        initView();
        initData();
        initCtrl();
    }

    @SuppressLint("WrongConstant")
    public void onError(Throwable paramThrowable, int paramInt) {
        if (this.mProgressDialog != null)
            this.mProgressDialog.dismiss();
        Toast.makeText((Context) this, getString(R.string.exit_current_num), 1).show();
    }

    protected void onNewIntent(Intent paramIntent) {
        super.onNewIntent(paramIntent);
        this.timelist.clear();
        this.chartBean = (ChartData.MessageChart) paramIntent.getSerializableExtra("intentMsg");
        this.timelist = paramIntent.getParcelableArrayListExtra("intentList");
        Log.e("intent ", "" + this.chartBean);
        Object[] arrayOfObject = splitAry(this.chartBean.get_loaddata(), 8);
        ArrayList<Integer> arrayList1 = new ArrayList();
        ArrayList<Integer> arrayList2 = new ArrayList();
        int k = arrayOfObject.length;
        int i;
        for (i = 0; i < k; i++) {
            int[] arrayOfInt = (int[]) arrayOfObject[i];
            for (int m = 0; m < arrayOfInt.length; m++) {
                if (m < 4) {
                    arrayList1.add(Integer.valueOf(arrayOfInt[m]));
                } else if (m >= 4 && m < 8) {
                    arrayList2.add(Integer.valueOf(arrayOfInt[m]));
                }
            }
        }
        this.xarr = arrayList1.<Integer>toArray(new Integer[arrayList1.size()]);
        this.yarr = arrayList2.<Integer>toArray(new Integer[arrayList2.size()]);
        Log.e("x", "" + Arrays.toString((Object[]) this.xarr));
        Log.e("y", "" + Arrays.toString((Object[]) this.yarr));
        i = ((Integer) Collections.<Integer>min(Arrays.asList(this.yarr))).intValue();
        int j = ((Integer) Collections.<Integer>max(Arrays.asList(this.yarr))).intValue();
        this.tvPoints.setText(this.chartBean.get_loadpointnum());
        this.tvSpeed.setText(this.chartBean.get_loadspeed());
        this.tvMax.setText(j + "");
        this.tvMin.setText(i + "");
        this.tvSamp.setText(this.chartBean.get_loadsampling());
        getAxisXLables(this.xarr);
        getAxisPoints(this.yarr);
        initLineChart();
        String str = TimeUtils.millis2String(Long.parseLong(this.chartBean.get_loaddatetime().substring(5, this.chartBean.get_loaddatetime().length() - 6)));
       // this.chartTime.setText(str.substring(0, str.length() - 9));
    }

    public void onNext(Object paramObject, int paramInt) {
//        if (paramInt == 8) {
//            paramObject = paramObject;
//            if (paramObject.getMessage().length() > 0) {
//                List<? extends ChartData.MessageChart> list = StringGsonToObject.stringToList(paramObject.getMessage().replace("\\/", "").trim(), ChartData.MessageChart.class);
//                if (this.maxPage == 0)
//                    this.maxPage = MaxPageReturn.returnMaxPage(this.rows, paramObject.getiTotalRecords());
//                this.mlist.addAll(list);
//                this.timelist.addAll(this.mlist);
//                paramObject = TimeUtils.millis2String(Long.parseLong(((ChartData.MessageChart) this.mlist.get(this.mlist.size() - 1)).get_loaddatetime().substring(5, ((ChartData.MessageChart) this.mlist.get(this.mlist.size() - 1)).get_loaddatetime().length() - 6)));
//                this.chartTime.setText(paramObject.substring(0, paramObject.length() - 9));
//                paramObject = splitAry(((ChartData.MessageChart) this.mlist.get(this.mlist.size() - 1)).get_loaddata(), 8);
//                list = new ArrayList<ChartData.MessageChart>();
//                ArrayList<Integer> arrayList = new ArrayList();
//                int j = paramObject.length;
//                for (paramInt = 0; paramInt < j; paramInt++) {
//                    int[] arrayOfInt = (int[]) paramObject[paramInt];
//                    for (int k = 0; k < arrayOfInt.length; k++) {
//                        if (k < 4) {
//                            list.add(Integer.valueOf(arrayOfInt[k]));
//                        } else if (k >= 4 && k < 8) {
//                            arrayList.add(Integer.valueOf(arrayOfInt[k]));
//                        }
//                    }
//                }
//                this.xarr = list.<Integer>toArray(new Integer[list.size()]);
//                this.yarr = arrayList.<Integer>toArray(new Integer[arrayList.size()]);
//                paramInt = ((Integer) Collections.<Integer>min(Arrays.asList(this.yarr))).intValue();
//                int i = ((Integer) Collections.<Integer>max(Arrays.asList(this.yarr))).intValue();
//                Log.e("x", "" + Arrays.toString((Object[]) this.xarr));
//                Log.e("y", "" + Arrays.toString((Object[]) this.yarr));
//                this.tvPoints.setText(((ChartData.MessageChart) this.mlist.get(this.mlist.size() - 1)).get_loadpointnum());
//                this.tvSpeed.setText(((ChartData.MessageChart) this.mlist.get(this.mlist.size() - 1)).get_loadspeed());
//                this.tvMax.setText(i + "");
//                this.tvMin.setText(paramInt + "");
//                this.tvSamp.setText(((ChartData.MessageChart) this.mlist.get(this.mlist.size() - 1)).get_loadsampling());
//                getAxisXLables(this.xarr);
//                getAxisPoints(this.yarr);
//                initLineChart();
//                if (this.mProgressDialog != null)
//                    this.mProgressDialog.dismiss();
//            }
//        }
    }
}
