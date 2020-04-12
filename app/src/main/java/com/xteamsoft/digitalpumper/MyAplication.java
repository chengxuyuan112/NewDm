package com.xteamsoft.digitalpumper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.xteamsoft.baselibrary.net.NetServiceUtils;
import com.xteamsoft.baselibrary.utils.Utils;

import java.util.LinkedList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

public class MyAplication extends LitePalApplication {
    public static final String HAS_FINGERPRINT_API = "hasFingerPrintApi";

    public static String REG_ID;

    public static final String SETTINGS = "settings";

    private static MyAplication instance;

    private static Context mContext;

    private List<Activity> mList = new LinkedList<Activity>();

    public static MyAplication getInstance() {
        // Byte code:
        //   0: ldc com/xteamsoft/digitalpumper/MyAplication
        //   2: monitorenter
        //   3: getstatic com/xteamsoft/digitalpumper/MyAplication.instance : Lcom/xteamsoft/digitalpumper/MyAplication;
        //   6: ifnonnull -> 19
        //   9: new com/xteamsoft/digitalpumper/MyAplication
        //   12: dup
        //   13: invokespecial <init> : ()V
        //   16: putstatic com/xteamsoft/digitalpumper/MyAplication.instance : Lcom/xteamsoft/digitalpumper/MyAplication;
        //   19: getstatic com/xteamsoft/digitalpumper/MyAplication.instance : Lcom/xteamsoft/digitalpumper/MyAplication;
        //   22: astore_0
        //   23: ldc com/xteamsoft/digitalpumper/MyAplication
        //   25: monitorexit
        //   26: aload_0
        //   27: areturn
        //   28: astore_0
        //   29: ldc com/xteamsoft/digitalpumper/MyAplication
        //   31: monitorexit
        //   32: aload_0
        //   33: athrow
        // Exception table:
        //   from	to	target	type
        //   3	19	28	finally
        //   19	23	28	finally
        return instance;
    }

    private void loadLibrary() {
        System.loadLibrary("gnustl_shared");
        System.loadLibrary("dsl");
        System.loadLibrary("dslalien");
        System.loadLibrary("DPRTSPSDK");
        System.loadLibrary("PlatformRestSDK");
        System.loadLibrary("PlatformSDK");
        System.loadLibrary("netsdk");
        System.loadLibrary("CommonSDK");
    }

    protected void attachBaseContext(Context paramContext) {
        super.attachBaseContext(paramContext);
    }

    public void onCreate() {
        super.onCreate();
        mContext = (Context) this;
        instance = this;
        Stetho.initializeWithDefaults((Context) this);
        (new OkHttpClient.Builder()).addNetworkInterceptor((Interceptor) new StethoInterceptor()).build();
        Utils.init(mContext);
        LitePal.initialize((Context) this);
        //loadLibrary();
        SharedPreferences sharedPreferences2 = mContext.getSharedPreferences("serviceData", 0);
        String str1 = sharedPreferences2.getString("ip", "");
        String str2 = sharedPreferences2.getString("port", "");
        NetServiceUtils.CONNECTION_URI = str1 + ":" + str2 + "/digpumper/";
        SharedPreferences sharedPreferences1 = getSharedPreferences("settings", 0);
        if (sharedPreferences1.contains("hasFingerPrintApi"))
            return;
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        try {
            Class.forName("android.hardware.fingerprint.FingerprintManager");
            editor.putBoolean("hasFingerPrintApi", true);
        } catch (ClassNotFoundException classNotFoundException) {
            editor.putBoolean("hasFingerPrintApi", false);
            classNotFoundException.printStackTrace();
        }
        editor.apply();
    }
}
