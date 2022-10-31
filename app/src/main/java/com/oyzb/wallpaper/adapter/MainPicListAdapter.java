package com.oyzb.wallpaper.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.oyzb.wallpaper.R;
import com.oyzb.wallpaper.bean.WallPagerBean;
import com.oyzb.wallpaper.util.LogUtil;
import com.oyzb.wallpaper.util.ValidateUtils;

/**
 * 作者：oyzb
 * 时间：2022/9/15 14:43
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class MainPicListAdapter extends BaseQuickAdapter<WallPagerBean.DataDTO, BaseViewHolder> {

    public MainPicListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, WallPagerBean.DataDTO item) {
        ImageView iv = helper.getView(R.id.iv_pic);
        iv.setTag(R.id.iv_pic, helper.getLayoutPosition());
        LogUtil.e("图片异步加载", "pos:" + helper.getLayoutPosition() + "-----------当前图片地址:" + item.getThumbnail());
        if (ValidateUtils.isValidate(item.getThumbnail())) {
            Glide.with(mContext)
                    .load(item.getThumbnail())
                    .into(iv);
        }
    }

}
