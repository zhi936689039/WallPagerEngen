package com.oyzb.wallpaper.present;

import android.content.Context;

import com.google.gson.Gson;
import com.oyzb.wallpaper.R;
import com.oyzb.wallpaper.base.BaseObserver;
import com.oyzb.wallpaper.base.BasePresenter;
import com.oyzb.wallpaper.bean.WallPagerBean;
import com.oyzb.wallpaper.bean.param.WallPagerLIstVideoParam;
import com.oyzb.wallpaper.contract.MainContract;
import com.oyzb.wallpaper.net.RetrofitHelper;
import com.oyzb.wallpaper.util.RxSchedulers;
import com.oyzb.wallpaper.util.ToastUtils;
import com.oyzb.wallpaper.util.ValidateUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * 作者：oyzb
 * 时间：2022/8/18 14:56
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class MainPresent extends BasePresenter<MainContract.View> implements MainContract.Presenter {
    @Override
    public void getwallpaperlistVideo() {
        WallPagerLIstVideoParam param = new WallPagerLIstVideoParam(5);
        RetrofitHelper.getApiData().wallpaperlist_video(objectToMap(param))
                .compose(RxSchedulers.applySchedulers())
                .compose(mView.bindToLife())
                .subscribe(new BaseObserver<WallPagerBean>() {
                    @Override
                    protected void onSucceed(WallPagerBean wallPagerBean) {
                        mView.getWallPagerListVideo(wallPagerBean);
                    }

                    @Override
                    protected void onFail(Throwable e) {
                        mView.getgetWallPagerListVideoFail(e.getMessage().toString());
                    }
                });
    }

    @Override
    public void feedBack(Context context, String email, String message) {
        Map<String, String> map = new HashMap<>();
        if(ValidateUtils.isValidate(email)){
            map.put("email",email);
        }
        if(ValidateUtils.isValidate(message)){
            map.put("message",message);
        }
        String route=new Gson().toJson(map);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), route);
        RetrofitHelper.getApiData().feedBack(body)
                .compose(RxSchedulers.applySchedulers())
                .subscribe(new BaseObserver<Object>() {
                    @Override
                    protected void onSucceed(Object object) {
                        ToastUtils.showShort(context.getResources().getString(R.string.feed_back_success));
                    }

                    @Override
                    protected void onFail(Throwable e) {
                    }
                });
    }


    private Map<String, Object> objectToMap(Object object){
        Map<String,Object> dataMap = new HashMap<>();
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                dataMap.put(field.getName(),field.get(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return dataMap;
    }
}
