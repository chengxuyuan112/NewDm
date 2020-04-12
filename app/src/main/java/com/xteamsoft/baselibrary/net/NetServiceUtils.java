package com.xteamsoft.baselibrary.net;

import android.support.annotation.NonNull;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import io.reactivex.functions.Action;
public class NetServiceUtils<C> {
    public static String CONNECTION_URI = "";

    public static String MESSAGE_URL = "http://42.123.124.201:8888/web/interfaces/";

    public static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = (new OkHttpClient.Builder()).addInterceptor((Interceptor) httpLoggingInterceptor).build();
        return (new Retrofit.Builder()).client(okHttpClient).baseUrl(CONNECTION_URI).addConverterFactory((Converter.Factory) GsonConverterFactory.create()).addCallAdapterFactory((CallAdapter.Factory) RxJava2CallAdapterFactory.create()).build();
    }

    public static Retrofit getRetrofit(@NonNull String paramString) {
        return (new Retrofit.Builder()).baseUrl(paramString).addConverterFactory((Converter.Factory) GsonConverterFactory.create()).addCallAdapterFactory((CallAdapter.Factory) RxJava2CallAdapterFactory.create()).build();
    }

    public static Retrofit getRetrofits() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = (new OkHttpClient.Builder()).addInterceptor((Interceptor) httpLoggingInterceptor).build();
        return (new Retrofit.Builder()).client(okHttpClient).baseUrl(MESSAGE_URL).addConverterFactory((Converter.Factory) GsonConverterFactory.create()).addCallAdapterFactory((CallAdapter.Factory) RxJava2CallAdapterFactory.create()).build();
    }

    public static <T> T getService(@NonNull Class<T> paramClass) {
        return (T) getRetrofit().create(paramClass);
    }

    public static <T> T getServiceToken(@NonNull Class<T> paramClass) {
        return (T) getTokenRetrofit().create(paramClass);
    }

    public static <T> T getServices(@NonNull Class<T> paramClass) {
        return (T) getRetrofits().create(paramClass);
    }

    public static Retrofit getTokenRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = (new OkHttpClient.Builder()).addInterceptor((Interceptor) httpLoggingInterceptor).build();
        return (new Retrofit.Builder())
                .client(okHttpClient)
                .baseUrl(CONNECTION_URI)
                .addConverterFactory((Converter.Factory) GsonConverterFactory.create())
                .addCallAdapterFactory((CallAdapter.Factory) RxJava2CallAdapterFactory.create())
                .build();
    }

    public void invoke(@NonNull Observable<C> paramObservable, @NonNull Consumer<C> paramAction1, @NonNull Observer<C> paramSubscriber) {
        paramObservable.subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                       .doOnNext(paramAction1)
                       .subscribe(paramSubscriber);
    }
}
