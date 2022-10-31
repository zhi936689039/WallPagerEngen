package com.oyzb.wallpaper.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.widget.TextView;

import com.oyzb.wallpaper.R;
import com.oyzb.wallpaper.base.BaseActivity;

import java.util.Calendar;

import butterknife.BindView;


public class SplashActivity extends BaseActivity {
    @BindView(R.id.tv_splash_content)
    TextView tv_splash_content;
    @Override
    public void getLayoutId() {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        try{
            PackageInfo pInfo=mContext.getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_META_DATA);
            tv_splash_content.setText(mContext.getResources().getString(R.string.app_name)
                    + " "+Calendar.getInstance().get(Calendar.YEAR)+" V"+pInfo.versionName);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(mContext,MainActivity.class));
                }
            },3000);
        }catch (Exception e){

        }

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }
}
