package com.wallpaper.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import com.trello.rxlifecycle4.LifecycleTransformer;
import com.wallpaper.util.ValidateUtils;
import com.wallpaper.util.statusBar.StatusBarHelper;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends BaseContract.BasePresenter> extends RxAppCompatActivity implements BaseContract.BaseView {
    protected P mPresenter;
    protected Context mContext;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutId();
        unbinder = ButterKnife.bind(this);
        mContext = this;
        if (null != getPresenter()) {
            mPresenter = getPresenter();
        }
        //开启沉浸式
        StatusBarHelper.immersiveStatusBar(this);
        if (isUseEventBus()) {
            EventBus.getDefault().register(this);
        }
        attachView();
        initData();
        initView();
        initEvent();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.bindToLifecycle();
    }

    private void attachView() {
        if (ValidateUtils.isValidate(mPresenter)) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ValidateUtils.isValidate(mPresenter)) {
            mPresenter.detachView();
            mPresenter = null;
            System.gc();
        }
        if (ValidateUtils.isValidate(unbinder)) {
            unbinder.unbind();
        }
        if(EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
    protected void startActivity(Class<?> targetClass) {
        Intent intent = new Intent(this, targetClass);
        startActivity(intent);
    }

    protected void startActivity(Class<?> targetClass,Bundle bundle) {
        Intent intent = new Intent(this, targetClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 获取Presenter
     */
    public P getPresenter() {
        return null;
    }

    public abstract void getLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract boolean isUseEventBus();
}
