package com.xteamsoft.baselibrary.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public abstract class BaseActivity extends AppCompatActivity implements OnSubscriber {
    public MyNewSub myNewSub;

    public MySubscriber subscriber;

    public MyNewSub getNewSubscriber(OnSubscriber paramOnSubscriber) {
        MyNewSub myNewSub = new MyNewSub(paramOnSubscriber);
        this.myNewSub = myNewSub;
        return myNewSub;
    }

    public MySubscriber getSubscriber(int paramInt) {
        MySubscriber mySubscriber = new MySubscriber(paramInt);
        this.subscriber = mySubscriber;
        return mySubscriber;
    }

    @SuppressLint("WrongConstant")
    public void onCreate(Bundle paramBundle, PersistableBundle paramPersistableBundle) {
        super.onCreate(paramBundle, paramPersistableBundle);
        setRequestedOrientation(1);
    }

    public void onStop() {
        super.onStop();
//        if (this.subscriber != null)
           // this.subscriber.unsubscribe();
    }

    private class MyNewSub implements Subscriber {
        private BaseActivity.OnSubscriber myOnSubscriber;

        private int what = -1;

        public MyNewSub(BaseActivity.OnSubscriber param1OnSubscriber) {
            this.myOnSubscriber = param1OnSubscriber;
        }

        public void onCompleted() {
            this.myOnSubscriber.onCompleted();
        }

        public void onError(Throwable param1Throwable) {
            BaseActivity.this.onError(param1Throwable, this.what);
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onSubscribe(Subscription s) {

        }

        public void onNext(Object param1Object) {
            this.myOnSubscriber.onNext(param1Object);
        }
    }

    public class MySubscriber implements Subscriber {
        private int what = -1;

        public MySubscriber() {}

        public MySubscriber(int param1Int) {
            this.what = param1Int;
        }

        public void onCompleted() {
            BaseActivity.this.onCompleted(this.what);
        }

        public void onError(Throwable param1Throwable) {
            BaseActivity.this.onError(param1Throwable, this.what);
        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onSubscribe(Subscription s) {

        }

        public void onNext(Object param1Object) {
            BaseActivity.this.onNext(param1Object, this.what);
        }
    }

    public static interface OnSubscriber {
        void onCompleted();

        void onError(Throwable param1Throwable);

        void onNext(Object param1Object);
    }
}
