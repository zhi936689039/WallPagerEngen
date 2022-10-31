package com.oyzb.wallpaper.bean;

import java.util.List;

/**
 * 作者：oyzb
 * 时间：2022/8/18 17:01
 * 邮箱：ouyangzb@yonyou.com
 * 说明：
 */
public class WallPagerBean {

    private int ret;
    private List<DataDTO> data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO {
        private String title;
        private String thumbnail;
        private String preview;
        private int is_free;
        private int category;
        private int id;
        private int cnt_like;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getPreview() {
            return preview;
        }

        public void setPreview(String preview) {
            this.preview = preview;
        }

        public int getIs_free() {
            return is_free;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCnt_like() {
            return cnt_like;
        }

        public void setCnt_like(int cnt_like) {
            this.cnt_like = cnt_like;
        }
    }
}
