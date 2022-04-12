package com.wallpaper.base;


import com.wallpaper.util.ValidateUtils;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends BaseContract.BaseView> implements BaseContract.BasePresenter<V> {
    private WeakReference<V> mvpView;

    @Override
    public void attachView(V view) {
        this.mvpView = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (ValidateUtils.isValidate(mvpView)) {
            mvpView.clear();
            mvpView = null;
        }
    }

    @Override
    public boolean isViewAttach() {
        return ValidateUtils.isValidate(mvpView);
    }


}
