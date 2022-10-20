package com.live.wallpaper.constant;

import com.live.wallpaper.App;

import java.io.File;

public interface Constants {
    String netUrl="https://api.prime2.juu.ltd/";
    String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    String PATH_CACHE = PATH_DATA + File.separator + "NetCache";
    String videoUrl="videoUrl";
    String picId="PIC_ID";
    long TIMEOUT = 30;
}
