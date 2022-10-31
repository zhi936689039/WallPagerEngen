package com.oyzb.wallpaper.bean.param;

/**
 * 作者：oyzb
 * 时间：2022/8/18 14:47
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class WallPagerLIstVideoParam extends BaseParam{
    private int pageSize;

    public WallPagerLIstVideoParam(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
