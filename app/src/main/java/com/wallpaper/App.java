package com.wallpaper;

import android.app.Application;

/**
 * 作者：oyzb
 * 时间：2022/4/12 22:48
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class App extends Application {
    private static App mInstance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }
    public static App getInstance() {
        return mInstance;
    }
}
