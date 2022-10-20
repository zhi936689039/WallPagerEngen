package com.live.wallpaper.bean.param;

import com.live.wallpaper.App;
import com.live.wallpaper.util.AppUtil;

import java.util.Locale;

public class BaseParam {
    private String pkgName;
    private String appvc;//版本号
    private String appvn;//版本名
    private String tm;//当前时间戳
    private String os_version;//设备系统版本号
    private String launguage;//设备语言
    private String device_model;//设备型号
    public BaseParam(){
        pkgName= AppUtil.getPackage(App.getInstance());
        appvc=String.valueOf(AppUtil.getVersionCode(App.getInstance()));
        appvn=AppUtil.getVersionName(App.getInstance());
        tm=String.valueOf(System.currentTimeMillis());
        os_version=AppUtil.getOSVersion();
        launguage= Locale.getDefault().getLanguage();
        device_model=AppUtil.getModel();
    }
    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getAppvc() {
        return appvc;
    }

    public void setAppvc(String appvc) {
        this.appvc = appvc;
    }

    public String getAppvn() {
        return appvn;
    }

    public void setAppvn(String appvn) {
        this.appvn = appvn;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public String getLaunguage() {
        return launguage;
    }

    public void setLaunguage(String launguage) {
        this.launguage = launguage;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }
}
