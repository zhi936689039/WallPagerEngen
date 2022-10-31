package com.oyzb.wallpaper.base;


import com.trello.rxlifecycle2.LifecycleTransformer;

public class BaseContract {
    public interface BasePresenter<V extends BaseView> {

        void attachView(V view);

        void detachView();

    }


    public interface BaseView {

        //显示进度中
        void showLoading();

        //关闭进度
        void hideLoading();

//        //显示请求成功
//        void showSuccess();
//
//        //失败重试
//        void showFailed();
//
//        //数据为空
//        void showEmpty();
//
//        //显示当前网络不可用
//        void showNoNet();
//
//        //重试
//        void onRetry();

        <T> LifecycleTransformer<T> bindToLife();
    }
}
