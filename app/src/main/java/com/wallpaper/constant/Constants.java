package com.wallpaper.constant;

import com.wallpaper.App;

import java.io.File;

public interface Constants {
    String netUrl="";
    String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";
    String PATH_CACHE = PATH_DATA + File.separator + "NetCache";
}
