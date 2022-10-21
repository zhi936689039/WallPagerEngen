package com.live.wallpaper.ui;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.service.wallpaper.WallpaperService;
import android.text.TextUtils;
import android.view.SurfaceHolder;

import androidx.annotation.RequiresApi;

import com.live.wallpaper.App;
import com.live.wallpaper.constant.Constants;
import com.live.wallpaper.util.LogUtil;
import com.live.wallpaper.util.SettingsUtil;
import com.live.wallpaper.util.SharePreferenceUtil;
import com.live.wallpaper.util.ValidateUtils;

import java.io.File;
import java.io.IOException;

public class VideoLiveWallpaper extends WallpaperService {
    private static final String SERVICE_NAME = "com.live.wallpaper.VideoLiveWallpaper";


    public Engine onCreateEngine() {
        return new VideoEngine();
    }

    private String TAG = "视频壁纸";

    /**
     * 设置壁纸
     *
     * @param context
     */
    public void setToWallPaper(Context context, String videoPath) {
        try {
            WallpaperManager.getInstance(context).clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SettingsUtil.setString(Constants.videoUrl, videoPath);
        Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(context, VideoLiveWallpaper.class));
        context.startActivity(intent);
    }

    class VideoEngine extends Engine {

        private MediaPlayer mMediaPlayer;

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            LogUtil.d(TAG, "VideoEngine#onCreate:" + isPreview());
        }

        @Override
        public void onDestroy() {
            LogUtil.d(TAG, "VideoEngine#onDestroy");
            super.onDestroy();

        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            LogUtil.d(TAG, "VideoEngine#onVisibilityChanged visible = " + visible);
            if (visible) {
                mMediaPlayer.start();
//                if (isPreview()) {
//                    try {
//                        clearWallpaper();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
            } else {
                mMediaPlayer.pause();
            }
        }


        @Override
        public void onSurfaceCreated(SurfaceHolder holder) {
            LogUtil.d(TAG, "VideoEngine#onSurfaceCreated ");
            super.onSurfaceCreated(holder);
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setSurface(holder.getSurface());
            try {
                mMediaPlayer.setDataSource(SettingsUtil.getString(Constants.videoUrl, ""));
                mMediaPlayer.setLooping(true);
                mMediaPlayer.setVolume(0, 0);
                mMediaPlayer.prepare();
                mMediaPlayer.start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onSurfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            LogUtil.d(TAG, "VideoEngine#onSurfaceChanged===isPreview： " + isPreview());
            super.onSurfaceChanged(holder, format, width, height);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            LogUtil.d(TAG, "VideoEngine#onSurfaceDestroyed ");
            super.onSurfaceDestroyed(holder);
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }


}  