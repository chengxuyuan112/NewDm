package com.xteamsoft.baselibrary.base;

public interface OnSubscriber {
    void onCompleted(int paramInt);

    void onError(Throwable paramThrowable, int paramInt);

    void onNext(Object paramObject, int paramInt);
}
