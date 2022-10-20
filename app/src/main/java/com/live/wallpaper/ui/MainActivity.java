package com.live.wallpaper.ui;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.live.wallpaper.constant.Constants;
import com.live.wallpaper.util.FileUtils;
import com.live.wallpaper.util.LogUtil;
import com.live.wallpaper.util.SettingsUtil;
import com.live.wallpaper.util.SharePreferenceUtil;
import com.live.wallpaper.util.ToastUtils;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.live.wallpaper.R;
import com.live.wallpaper.adapter.MainPicListAdapter;
import com.live.wallpaper.base.BaseActivity;
import com.live.wallpaper.bean.WallPagerBean;
import com.live.wallpaper.contract.MainContract;
import com.live.wallpaper.present.MainPresent;
import com.live.wallpaper.util.ValidateUtils;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresent> implements MainContract.View {

    @BindView(R.id.ry_pic_list)
    RecyclerView ryPicList;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.layout_refresh)
    SmartRefreshLayout layout_refresh;
    private int pageNo = 1;
    private MainPicListAdapter mAdapter;
    private boolean sIsScrolling;

    @Override
    public void getLayoutId() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {

    }

    @Override
    public MainPresent getPresenter() {
        return new MainPresent();
    }

    @Override
    protected void initView() {
        SettingsUtil.setString("videoUrl","");
        initRefreshLayout();
        initRecycleView();
        deleteFile();
    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    private void initRefreshLayout() {
        //设置 Header 样式
        layout_refresh.setRefreshHeader(new ClassicsHeader(mContext));
        //设置 Footer 为 球脉冲 样式
        layout_refresh.setRefreshFooter(new ClassicsFooter(mContext));
        layout_refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                pageNo = 1;
                deleteFile();
                requestData();
            }
        });
        layout_refresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
                pageNo++;
                requestData();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(ValidateUtils.isValidate(layout_refresh)){
            layout_refresh.finishRefresh();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ValidateUtils.isValidate(layout_refresh)){
            layout_refresh.autoRefresh();
        }
    }

    private void initRecycleView() {

        ryPicList.getItemAnimator().setAddDuration(0);
        ryPicList.getItemAnimator().setChangeDuration(0);
        ryPicList.getItemAnimator().setMoveDuration(0);
        ryPicList.getItemAnimator().setRemoveDuration(0);
        ((SimpleItemAnimator) ryPicList.getItemAnimator()).setSupportsChangeAnimations(false);

        ryPicList.setLayoutManager(new GridLayoutManager(mContext, 2));
        mAdapter = new MainPicListAdapter(R.layout.item_main_pic);
        ryPicList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                SharePreferenceUtil.put(mContext, Constants.videoUrl,mAdapter.getData().get(position).getPreview());
//                VideoLiveWallpaper.setToWallPaper(mContext,mAdapter.getData().get(position).getPreview());
                startActivity(new Intent(mContext,LiveWallPagerPlayActivity.class)
                        .putExtra(Constants.videoUrl,mAdapter.getData().get(position).getPreview())
                .putExtra(Constants.picId,mAdapter.getData().get(position).getId()));
            }
        });

    }

    private void deleteFile(){
        File file = null;
        String fileParent = mContext.getExternalFilesDir("downVideo").getAbsolutePath() + File.separator;
        file=new File(fileParent);
        FileUtils.deleteFile2(file);
    }
    private void requestData() {
        mPresenter.getwallpaperlistVideo();
    }

    @OnClick(R.id.iv_setting)
    public void onClick() {
        startActivity(new Intent(mContext,SettingActivity.class));
    }

    @Override
    public void getWallPagerListVideo(WallPagerBean bean) {
        layout_refresh.finishRefresh();
        layout_refresh.finishLoadMore();
        if (pageNo == 1) {
            if (ValidateUtils.isValidate(bean.getData())) {
                mAdapter.setNewData(bean.getData());
            }
        } else {
            if (ValidateUtils.isValidate(bean.getData())) {
                mAdapter.addData(bean.getData());
            } else {
                layout_refresh.finishLoadMoreWithNoMoreData();
            }
        }
    }

    @Override
    public void getgetWallPagerListVideoFail(String msg) {
        layout_refresh.finishRefresh();
        layout_refresh.finishLoadMore();
        ToastUtils.showShort(msg);
    }
}