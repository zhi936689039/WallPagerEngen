package com.live.wallpaper.ui;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.live.wallpaper.App;
import com.live.wallpaper.R;
import com.live.wallpaper.base.BaseActivity;
import com.live.wallpaper.constant.Constants;
import com.live.wallpaper.util.FileUtils;
import com.live.wallpaper.util.LogUtil;
import com.live.wallpaper.util.ToastUtils;
import com.live.wallpaper.util.ValidateUtils;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveWallPagerPlayActivity extends BaseActivity {
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;
    @BindView(R.id.layout_surfaceview)
    SurfaceView surfaceView;
    @BindView(R.id.iv_return)
    ImageView iv_return;
    @BindView(R.id.tv_set_wall_pager)
    TextView tv_set_wall_pager;
    @BindView(R.id.tv_retry)
    TextView tv_retry;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    private String videoPath;
    private int picId;
    private VideoLiveWallpaper mVideoLiveWallpaper;

    @Override
    public void getLayoutId() {
        setContentView(R.layout.activity_live_wall_pager_play);
    }

    @Override
    protected void initData() {
        videoPath = getIntent().getStringExtra(Constants.videoUrl);
        picId = getIntent().getIntExtra(Constants.picId, 0);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        surfaceHolder = surfaceView.getHolder(); //主要是将Surfaceview与mediaplayer关联起来
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder holder) {
                mediaPlayer.setSurface(holder.getSurface());
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

            }
        });

    }

    @Override
    protected void initView() {
        mVideoLiveWallpaper=new VideoLiveWallpaper();
        initFileDownload();
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_set_wall_pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoLiveWallpaper.setToWallPaper(mContext, videoPath,mContext.getExternalFilesDir("downVideo").getAbsolutePath()
                        + File.separator+"1_" + picId + ".mp4");
                finish();
            }
        });
        tv_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initFileDownload();
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(mediaPlayer.isPlaying()||mediaPlayer.isLooping()){
            mediaPlayer.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer!=null){
            if (mediaPlayer.isPlaying()){
                mediaPlayer.stop(); //停止播放视频
            }
            mediaPlayer.release(); //释放资源
        }
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    private void initFileDownload() {
        File file = null;
        String fileParent = mContext.getExternalFilesDir("downVideo").getAbsolutePath() + File.separator;
        file = new File(fileParent, "1_" + picId + ".mp4");
        if (FileUtils.isFileExists(file)) {
            if (ValidateUtils.isValidate(file.getAbsolutePath())) {
                playVideo(file.getAbsolutePath());
            } else {
                playVideo(file.getPath());
            }
        } else {
            FileUtils.downloadFromUrl(videoPath, "1_" + picId + ".mp4", mContext, new FileUtils.FileDownLoadListener() {
                @Override
                public void downLoadFile(String errorMsg) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            BaseActivity activity= (BaseActivity) mContext;
                            if(activity.isFinishing()){
                                return;
                            }
                            ToastUtils.showShort("Download File"+errorMsg);
                            tv_retry.setVisibility(View.VISIBLE);
                            pbLoading.setVisibility(View.GONE);

                        }
                    });
                }

                @Override
                public void downLoadSuccess(File file) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            BaseActivity activity= (BaseActivity) mContext;
                            if(activity.isFinishing()){
                                return;
                            }
                            tv_retry.setVisibility(View.GONE);
                            pbLoading.setVisibility(View.VISIBLE);
                            if (ValidateUtils.isValidate(file.getAbsolutePath())) {
                                playVideo(file.getAbsolutePath());
                            } else {
                                playVideo(file.getPath());
                            }
                        }
                    });
                }

                @Override
                public void onDownloadProgress(int progress) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            BaseActivity activity = (BaseActivity) mContext;
                            if (activity.isFinishing()) {
                                return;
                            }
                            pbLoading.setProgress(progress);
                        }
                    });
                }
            });
        }
    }

    private void playVideo(String path) {
        BaseActivity activity = (BaseActivity) mContext;
        if (activity.isFinishing()) {
            return;
        }
        llLoading.setVisibility(View.GONE);
        surfaceView.setVisibility(View.VISIBLE);
        tv_set_wall_pager.setVisibility(View.VISIBLE);

        try {
            //   mediaPlayer.reset();
            LogUtil.e("文件下载", "文件下载:" + path);
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepareAsync();
            mediaPlayer.setLooping(true);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

        } catch (IOException e) {

        }
    }

}
