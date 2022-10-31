package com.oyzb.wallpaper;

import android.app.Application;

import com.oyzb.wallpaper.util.SettingsUtil;

/**
 * 作者：oyzb
 * 时间：2022/4/12 22:48
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class App extends Application {
    private static App mInstance = null;
    private static int imageDir;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        SettingsUtil.initialize(this);
    }
    public static App getInstance() {
        return mInstance;
    }

    public static int getImageDir() {
        return imageDir;
    }

    public static void setImageDir(int imageDir) {
        App.imageDir = imageDir;
    }
}
