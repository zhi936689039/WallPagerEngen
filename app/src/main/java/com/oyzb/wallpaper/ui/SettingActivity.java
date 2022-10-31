package com.oyzb.wallpaper.ui;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.oyzb.wallpaper.R;
import com.oyzb.wallpaper.base.BaseActivity;
import com.oyzb.wallpaper.ui.dialog.RateDialogFragment;
import com.oyzb.wallpaper.util.ValidateUtils;

/**
 * 作者：oyzb
 * 时间：2022/10/17 16:15
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class SettingActivity extends BaseActivity {
    private RateDialogFragment mDialogFragment;
    private boolean isShow;
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
                isShow=true;
                mDialogFragment.show(getSupportFragmentManager(),"dialog");
            }
        });
        findViewById(R.id.iv_return).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.tv_private_policy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse("https://www.freeprivacypolicy.com/live/7251bbf5-a3da-4b34-9bd1-87ecfa114841");
                intent.setData(content_url);
                startActivity(intent);
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
        if(ValidateUtils.isValidate(mDialogFragment)&&mDialogFragment.getDialog()!=null&&mDialogFragment.getDialog().isShowing()){
            mDialogFragment.dismissAllowingStateLoss();
            mDialogFragment=null;
        }
    }
}
