package com.wallpaper.base;

import com.wallpaper.util.GsonUtil;
import com.wallpaper.util.LogUtil;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public abstract class BaseObserver<T> implements Observer<T> {
    protected Disposable disposable;

    protected abstract void onSucceed(T t);
    protected abstract void onFail(Throwable e);
    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(T t) {
        LogUtil.e("网络请求","当前网络数据:"+ GsonUtil.serializedToJson(t));
        onSucceed(t);
    }

    @Override
    public void onError(Throwable e) {
        onFail(e);
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onComplete() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
