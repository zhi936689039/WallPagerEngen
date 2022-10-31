package com.oyzb.wallpaper.ui;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.service.wallpaper.WallpaperService;
import android.view.SurfaceHolder;

import com.oyzb.wallpaper.constant.Constants;
import com.oyzb.wallpaper.util.LogUtil;
import com.oyzb.wallpaper.util.SettingsUtil;

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
    public void setToWallPaper(Context context, String videoPath,String file_videoPath) {
        try {
            WallpaperManager.getInstance(context).clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SettingsUtil.setString(Constants.videoUrl, videoPath);
        SettingsUtil.setString(Constants.file_video_url, file_videoPath);
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
            } else {
                if (mMediaPlayer.isPlaying()) {
                    mMediaPlayer.stop();
                    try {
                        mMediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


            @Override
            public void onSurfaceCreated (SurfaceHolder holder){
                LogUtil.d(TAG, "VideoEngine#onSurfaceCreated ");
                super.onSurfaceCreated(holder);
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setSurface(holder.getSurface());
                String videoPath;
                try {
                    LogUtil.e("视频壁纸","文件url:"+SettingsUtil.getString(Constants.file_video_url,"")
                            +"网络url:"+SettingsUtil.getString(Constants.videoUrl,""));
                    File file=new File(SettingsUtil.getString(Constants.file_video_url,""));
                    if(file.exists()){
                        videoPath=SettingsUtil.getString(Constants.file_video_url,"");
                    }else{
                        videoPath=SettingsUtil.getString(Constants.videoUrl,"");
                    }
                    mMediaPlayer.setDataSource(videoPath);
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.setVolume(0, 0);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onSurfaceChanged (SurfaceHolder holder,int format, int width, int height){
                LogUtil.d(TAG, "VideoEngine#onSurfaceChanged===isPreview： " + isPreview());
                super.onSurfaceChanged(holder, format, width, height);
            }

            @Override
            public void onSurfaceDestroyed (SurfaceHolder holder){
                LogUtil.d(TAG, "VideoEngine#onSurfaceDestroyed ");
                super.onSurfaceDestroyed(holder);
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        }


    }