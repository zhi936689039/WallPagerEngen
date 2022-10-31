package com.oyzb.wallpaper.contract;

import android.content.Context;

import com.oyzb.wallpaper.base.BaseContract;
import com.oyzb.wallpaper.bean.WallPagerBean;

/**
 * 作者：oyzb
 * 时间：2022/8/18 14:54
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public interface MainContract {
    interface Presenter extends BaseContract.BasePresenter<MainContract.View> {
        void getwallpaperlistVideo();
        void feedBack(Context context, String email, String message);
    }
    interface View extends BaseContract.BaseView {
        void getWallPagerListVideo(WallPagerBean bean);
        void getgetWallPagerListVideoFail(String msg);
    }
}
