package com.live.wallpaper.ui;

import android.view.View;

import com.live.wallpaper.R;
import com.live.wallpaper.base.BaseActivity;
import com.live.wallpaper.ui.dialog.RateDialogFragment;
import com.live.wallpaper.util.ValidateUtils;

/**
 * 作者：oyzb
 * 时间：2022/10/17 16:15
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class SettingActivity extends BaseActivity {
    private RateDialogFragment mDialogFragment;
    @Override
    public void getLayoutId() {
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void initData() {
        mDialogFragment=new RateDialogFragment();
    }

    @Override
    protected void initView() {
        findViewById(R.id.rl_evaluation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogFragment.show(getSupportFragmentManager(),"dialog");
            }
        });
        findViewById(R.id.iv_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(ValidateUtils.isValidate(mDialogFragment)){
            mDialogFragment.dismissAllowingStateLoss();
            mDialogFragment=null;
        }
    }
}
