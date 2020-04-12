package com.xteamsoft.digitalpumper.manager;

import android.support.annotation.NonNull;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.xteamsoft.baselibrary.net.NetServiceUtils;
import com.xteamsoft.digitalpumper.bean.BasicData;
import com.xteamsoft.digitalpumper.bean.ChartData;
import com.xteamsoft.digitalpumper.bean.CmdData;
import com.xteamsoft.digitalpumper.bean.FeedData;
import com.xteamsoft.digitalpumper.bean.FluidData;
import com.xteamsoft.digitalpumper.bean.HisBasicData;
import com.xteamsoft.digitalpumper.bean.UserData;
import com.xteamsoft.digitalpumper.bean.UserFirebase;
import com.xteamsoft.digitalpumper.bean.WellAreaData;
import com.xteamsoft.digitalpumper.bean.WellData;
import com.xteamsoft.digitalpumper.beanReports.ReportsData;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainManager {
    private static MainManager instance;

//    private Action1<BasicData> basicData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<UserData> changeData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<ChartData> chartData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<CmdData> cmdData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<FeedData> feedData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<FluidData> fluidData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<UserData> forgetData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<HisBasicData> hisBasicData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<UserData> loginData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<UserData> logoutData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<UserFirebase> noReadUserFirebase = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<ReportsData> reportsData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<UserFirebase> updateUserFirebase = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<UserFirebase> userFirebase = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<UserFirebase> userFirebases = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<WellAreaData> wellAreaData = MainManager$.Lambda.null.lambdaFactory$();
//
//    private Action1<WellData> wellData = MainManager$.Lambda.null.lambdaFactory$();

    public static MainManager getInstance() {
        if (instance == null)
            instance = new MainManager();
        return instance;
    }

    public Subscriber<UserData> changePost(String paramString1, String paramString2, String paramString3, String paramString4, @NonNull String paramString5, @NonNull Subscriber<UserData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString5);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).change(paramString1, paramString2, paramString3, paramString4, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.changeData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<CmdData> cmdPost(String paramString1, int paramInt, String paramString2, @NonNull String paramString3, @NonNull Subscriber<CmdData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString3);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).cmdPost(paramString1, paramInt, paramString2, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.cmdData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<FeedData> feedPost(String paramString1, String paramString2, @NonNull String paramString3, @NonNull Subscriber<FeedData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString3);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).feedPost(paramString1, paramString2, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.feedData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<UserData> forgetPost(String paramString1, @NonNull String paramString2, @NonNull Subscriber<UserData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString2);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).forget(paramString1, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.forgetData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<UserData> loginPost(String paramString1, String paramString2, @NonNull String paramString3, @NonNull Subscriber<UserData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString3);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).login(paramString1, paramString2, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.loginData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<UserData> logoutPost(String paramString1, String paramString2, String paramString3, @NonNull String paramString4, @NonNull Subscriber<UserData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString4);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).logout(paramString1, paramString2, paramString3, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.logoutData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<UserFirebase> noReadMessage(String paramString1, @NonNull String paramString2, @NonNull Subscriber<UserFirebase> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString2);
//        Observable observable = ((RxApi)NetServiceUtils.getServices(RxApi.class)).noReadMessage(paramString1, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.noReadUserFirebase, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<BasicData> queryBasic(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, @NonNull String paramString6, @NonNull Subscriber<BasicData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString6);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).queryBasic(paramString1, paramString2, paramString3, paramString4, paramString5, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.basicData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<ChartData> queryChart(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, @NonNull String paramString8, @NonNull Subscriber<ChartData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString8);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).queryChart(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.chartData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<FluidData> queryFluid(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, @NonNull String paramString8, @NonNull Subscriber<FluidData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString8);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).queryFluid(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.fluidData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<HisBasicData> queryHisBasic(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, @NonNull String paramString8, @NonNull Subscriber<HisBasicData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString8);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).queryHisBasic(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.hisBasicData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<UserFirebase> queryMessageList(String paramString1, String paramString2, String paramString3, String paramString4, @NonNull String paramString5, @NonNull Subscriber<UserFirebase> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString5);
//        Observable observable = ((RxApi)NetServiceUtils.getServices(RxApi.class)).queryMessageList(paramString1, paramString2, paramString3, paramString4, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.userFirebases, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<ReportsData> queryReports(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, @NonNull String paramString8, @NonNull Subscriber<ReportsData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString8);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).queryReports(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, paramString7, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.reportsData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<WellData> queryWell(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, @NonNull String paramString7, @NonNull Subscriber<WellData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString7);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).queryWell(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.wellData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<WellAreaData> queryWellArea(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, @NonNull String paramString7, @NonNull Subscriber<WellAreaData> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString7);
//        Observable observable = ((RxApi)NetServiceUtils.getService(RxApi.class)).queryWellArea(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.wellAreaData, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<UserFirebase> updateMessageRead(String paramString1, String paramString2, @NonNull String paramString3, @NonNull Subscriber<UserFirebase> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString3);
//        Observable observable = ((RxApi)NetServiceUtils.getServices(RxApi.class)).updateMessageRead(paramString1, paramString2, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.updateUserFirebase, paramSubscriber);
        return paramSubscriber;
    }

    public Subscriber<UserFirebase> updateRegID(String paramString1, String paramString2, String paramString3, @NonNull String paramString4, @NonNull Subscriber<UserFirebase> paramSubscriber) {
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), paramString4);
//        Observable observable = ((RxApi)NetServiceUtils.getServices(RxApi.class)).updateRegID(paramString1, paramString2, paramString3, requestBody);
//        (new NetServiceUtils()).invoke(observable, this.userFirebase, paramSubscriber);
        return paramSubscriber;
    }
}
