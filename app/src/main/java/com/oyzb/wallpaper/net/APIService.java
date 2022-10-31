package com.oyzb.wallpaper.net;

import com.oyzb.wallpaper.bean.WallPagerBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface APIService {
    @GET("image/wallpaperlist_video")
    Observable<WallPagerBean> wallpaperlist_video(@QueryMap Map<String,Object> map);
    @POST("base/feedback")
    Observable<Object> feedBack(@Body RequestBody body);
}
