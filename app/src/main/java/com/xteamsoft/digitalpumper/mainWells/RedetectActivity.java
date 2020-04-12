package com.xteamsoft.digitalpumper.mainWells;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.xteamsoft.baselibrary.base.BaseActivity;
import com.xteamsoft.baselibrary.utils.TimeUtils;
import com.xteamsoft.baselibrary.utilsLT.StringGsonToObject;
import com.xteamsoft.digitalpumper.R;
import com.xteamsoft.digitalpumper.bean.BasicData;
import com.xteamsoft.digitalpumper.bean.CmdData;
import com.xteamsoft.digitalpumper.bean.WellAreaBean;
import com.xteamsoft.digitalpumper.bean.WellBean;
import com.xteamsoft.digitalpumper.manager.MainManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.litepal.crud.DataSupport;

public class RedetectActivity extends BaseActivity {
    private TextView btnTest;

    private int displyStart = 0;

    Handler handler = new Handler();

    List<BasicData.MessageBasic> list;

    private ProgressDialog mProgressDialog;

    private int maxPage = 0;

    private int nextPage = 1;

    private String progress;

    private int rows = 9999;

    private int rstatus = 0;

    Runnable runnable = new Runnable() {
        public void run() {
            try {
                RedetectActivity.this.handler.postDelayed(this, 1000L);
                RedetectActivity.this.rRtest(RedetectActivity.this.wellBean, 31);
                System.out.println("do...");
                return;
            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.println("exception...");
                return;
            }
        }
    };

    Runnable runnable1 = new Runnable() {
        public void run() {
            try {
                RedetectActivity.this.handler.postDelayed(this, 1000L);
                RedetectActivity.this.rRead(RedetectActivity.this.wellBean, 30);
                System.out.println("do...");
                return;
            } catch (Exception exception) {
                exception.printStackTrace();
                System.out.println("exception...");
                return;
            }
        }
    };

    private String tankf_luid;

    private String token;

    private Toolbar toolbar;

    private TextView toolbar_title;

    private TextView tv1;

    private TextView tv2;

    private TextView tv3;

    private TextView tvTime;

    private WellBean wellBean;

    private int xfstatus = 1;

    private void initCtrl() {
        //  this.toolbar.setNavigationIcon(2130903169);
        setSupportActionBar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                RedetectActivity.this.finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.btnTest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                RedetectActivity.this.isLockedDialog();
            }
        });
    }

    private void initData() {
        this.token = getSharedPreferences("UserData", 0).getString("token", "");
        this.wellBean = (WellBean) getIntent().getSerializableExtra("intentMsg");
        this.toolbar_title.setText(this.wellBean.get_wellname());
        this.list = new ArrayList<BasicData.MessageBasic>();
    }

    private void initView() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
        //   this.toolbar_title = (TextView) findViewById(2131886762);
        //   this.btnTest = (TextView) findViewById(2131886462);
        //    this.tvTime = (TextView) findViewById(2131886340);
        //    this.tv1 = (TextView) findViewById(2131886347);
        //   this.tv2 = (TextView) findViewById(2131886348);
        //    this.tv3 = (TextView) findViewById(2131886349);
    }

    private void isLockedDialog() {
        final AlertDialog alertDialog = (new AlertDialog.Builder((Context) this, 0)).create();
        alertDialog.show();
        alertDialog.setContentView(R.layout.dialog_locked);
        Window window = alertDialog.getWindow();
        window.setLayout(-2, -2);
        window.setGravity(17);
        //    window.setWindowAnimations(2131493067);
        ((TextView) window.findViewById(R.id.contral_title)).setText("Is the oil " + this.wellBean.get_wellname() + " locked?");
        window.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                alertDialog.dismiss();
            }
        });
        window.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                // RedetectActivity.access$102(RedetectActivity.this, ProgressDialog.show((Context) RedetectActivity.this, null, "Loading..."));
                RedetectActivity.this.mProgressDialog.setCanceledOnTouchOutside(true);
                RedetectActivity.this.xfControl(RedetectActivity.this.wellBean, 38);
                alertDialog.dismiss();
            }
        });
    }

    private void isUnLockedDialog() {
        final AlertDialog alertDialog = (new AlertDialog.Builder((Context) this, 0)).create();
        alertDialog.show();
        alertDialog.setContentView(R.layout.dialog_islocked);
        Window window = alertDialog.getWindow();
        window.setLayout(-2, -2);
        window.setGravity(17);
        //  window.setWindowAnimations(2131493067);
        ((TextView) window.findViewById(R.id.message)).setText("This well has been locked by other users !");
        window.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
            public void onClick(View param1View) {
                alertDialog.dismiss();
            }
        });
    }

    private void queryData(String paramString) {
        BasicData basicData = new BasicData();
        basicData.setId(paramString);
        basicData.setsEcho(this.nextPage + "");
        basicData.setiDisplayStart(this.displyStart + "");
        basicData.setiDisplayLength(this.rows + "");
        basicData.setToken(this.token);
        String str = (new Gson()).toJson(basicData);
        Log.e("sendmsg ", "" + JSON.toJSONString(str));
        //  MainManager.getInstance().queryBasic(paramString, this.nextPage + "", this.displyStart + "", this.rows + "", this.token, str, (Subscriber) getSubscriber(5));
    }

    private void rControl(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[]{"_wellareaaddr"}).where(new String[]{"_wellareaid = ?", paramWellBean.get_wellareaid()}).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean) list.get(0)).get_wellareaaddr();
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
            //  MainManager.getInstance().cmdPost(str2, this.rstatus, this.token, str1, (Subscriber) getSubscriber(19));
            return;
        }
        Log.e("sendmsg ", "井场地为空");
    }

    private void rRead(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[]{"_wellareaaddr"}).where(new String[]{"_wellareaid = ?", paramWellBean.get_wellareaid()}).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean) list.get(0)).get_wellareaaddr();
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
            // MainManager.getInstance().cmdPost(str2, this.rstatus, this.token, str1, (Subscriber) getSubscriber(27));
            return;
        }
        Log.e("sendmsg ", "井场地为空");
    }

    private void rRtest(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[]{"_wellareaaddr"}).where(new String[]{"_wellareaid = ?", paramWellBean.get_wellareaid()}).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean) list.get(0)).get_wellareaaddr();
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
            //  MainManager.getInstance().cmdPost(str2, this.rstatus, this.token, str1, (Subscriber) getSubscriber(28));
            return;
        }
        Log.e("sendmsg ", "井场地为空");
    }

    private void rTest(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[]{"_wellareaaddr"}).where(new String[]{"_wellareaid = ?", paramWellBean.get_wellareaid()}).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean) list.get(0)).get_wellareaaddr();
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
            // MainManager.getInstance().cmdPost(str2, this.rstatus, this.token, str1, (Subscriber) getSubscriber(26));
            return;
        }
        Log.e("sendmsg ", "井场地为空");
    }

    private void xfControl(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[]{"_wellareaaddr"}).where(new String[]{"_wellareaid = ?", paramWellBean.get_wellareaid()}).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean) list.get(0)).get_wellareaaddr();
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
            //  MainManager.getInstance().cmdPost(str2, this.xfstatus, this.token, str1, (Subscriber) getSubscriber(10));
            return;
        }
        Log.e("sendmsg ", "井场地为空");
    }

    private void xfRelease(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.where(new String[]{"_wellareaid = ?", paramWellBean.get_wellareaid()}).find(WellAreaBean.class);
        if (!list.isEmpty()) {
            String str3 = ((WellAreaBean) list.get(0)).get_wellareaaddr();
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
            //  MainManager.getInstance().cmdPost(str2, this.xfstatus, this.token, str1, (Subscriber) getSubscriber(24));
            return;
        }
        Log.e("sendmsg ", "井场地为空");
    }

    private void xfTest(WellBean paramWellBean, int paramInt) {
        List<WellAreaBean> list = DataSupport.select(new String[]{"_wellareaaddr"}).where(new String[]{"_wellareaid = ?", paramWellBean.get_wellareaid()}).find(WellAreaBean.class);
        if (list.size() != 0) {
            String str3 = ((WellAreaBean) list.get(0)).get_wellareaaddr();
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
            //  MainManager.getInstance().cmdPost(str2, this.xfstatus, this.token, str1, (Subscriber) getSubscriber(25));
            return;
        }
        Log.e("sendmsg ", "井场地为空");
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
            ((LinearLayout) findViewById(R.id.wellLinear_buttom)).setLayoutParams((ViewGroup.LayoutParams) new RelativeLayout.LayoutParams(getWindowManager().getDefaultDisplay().getWidth(), i));
        }
        initView();
        initData();
        initCtrl();
    }

    @SuppressLint("WrongConstant")
    public void onError(Throwable paramThrowable, int paramInt) {
        if (this.mProgressDialog != null)
            this.mProgressDialog.dismiss();
        Toast.makeText((Context) this, getString(R.string.password_error), 1).show();
    }

    public void onNext(Object paramObject, int paramInt) {
        if (paramInt == 10) {
            if (((CmdData) paramObject).getResult().equals("1")) {
                Log.e("action ", "下发获取权限成功");
                rControl(this.wellBean, 38);
                return;
            }
            if (this.mProgressDialog != null) {
                this.mProgressDialog.dismiss();
                return;
            }
            return;
        }
        if (paramInt == 19) {
            if (((CmdData) paramObject).getMessageExt().equals("6")) {
                Log.e("action ", "下发获取权限成功");
                xfTest(this.wellBean, 29);
                return;
            }
            if (this.mProgressDialog != null)
                this.mProgressDialog.dismiss();
            isUnLockedDialog();
            return;
        }
        if (paramInt == 25) {
            if (((CmdData) paramObject).getResult().equals("1")) {
                Log.e("action ", "下发页面测试命令成功");
                rTest(this.wellBean, 29);
                return;
            }
            if (this.mProgressDialog != null) {
                this.mProgressDialog.dismiss();
                return;
            }
            return;
        }
        if (paramInt == 26) {
            if (((CmdData) paramObject).getMessageExt().equals("3")) {
                Log.e("action ", "=============");
                this.handler.postDelayed(this.runnable1, 1000L);
                return;
            }
            if (this.mProgressDialog != null)
                this.mProgressDialog.dismiss();
            Log.e("action ", "");
            xfRelease(this.wellBean, 39);
            return;
        }
        if (paramInt == 27) {
            paramObject = paramObject;
//            if (paramObject.getMessageExt().equals("3")) {
//                Log.e("action ", "");
//                if (this.handler != null)
//                    this.handler.removeCallbacks(this.runnable1);
//                this.tankf_luid = paramObject.getMessage();
//                this.handler.postDelayed(this.runnable, 1000L);
//                return;
//            }
//            if (paramObject.getMessageExt().equals("1") && paramObject.getMessage().equals("executing")) {
//                Log.e("action ", "");
//                return;
//            }
            if (this.mProgressDialog != null)
                this.mProgressDialog.dismiss();
            xfRelease(this.wellBean, 39);
            return;
        }
        if (paramInt == 28) {
            paramObject = paramObject;
            Log.e("action ", "");
//            if (!paramObject.getMessage().equals("100")) {
//                if (paramObject.getMessage().equals("executing")) {
//                    this.progress = paramObject.getMessage();
//                    this.mProgressDialog.setMessage(this.progress);
//                    Log.e("action ", ");
//                    return;
//                }
//                return;
//            }
            if (this.mProgressDialog != null)
                this.mProgressDialog.dismiss();
            Log.e("action ", "");
            this.handler.removeCallbacks(this.runnable);
            xfRelease(this.wellBean, 39);
            queryData(this.wellBean.get_wellid());
            this.tv1.setText(this.tankf_luid);
            return;
        }
        if (paramInt == 24) {
            if (((CmdData) paramObject).getResult().equals("1")) {
                Log.e("action ", "");
                return;
            }
            return;
        }
        if (paramInt == 5) {
            paramObject = paramObject;
//            if (paramObject.getMessage().length() > 0) {
//                paramObject = StringGsonToObject.stringToList(paramObject.getMessage().replace("\\/", "").trim(), BasicData.MessageBasic.class);
//                this.list.addAll((Collection<? extends BasicData.MessageBasic>) paramObject);
//                if (((BasicData.MessageBasic) this.list.get(0)).get_collectdate() != null) {
//                    paramObject = TimeUtils.millis2String(Long.parseLong(((BasicData.MessageBasic) this.list.get(0)).get_collectdate().substring(5, ((BasicData.MessageBasic) this.list.get(0)).get_collectdate().length() - 6)));
//                    this.tvTime.setText((CharSequence) paramObject);
//                }
//                this.tv2.setText(((BasicData.MessageBasic) this.list.get(0)).get_gaslinepres());
//                this.tv3.setText(((BasicData.MessageBasic) this.list.get(0)).get_casingpres());
//                return;
//            }
            return;
        }
        if (this.mProgressDialog != null) {
            this.mProgressDialog.dismiss();
            return;
        }
    }
}
