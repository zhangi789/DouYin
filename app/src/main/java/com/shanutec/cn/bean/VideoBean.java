package com.shanutec.cn.bean;

/**
 * @author 张海洋
 * @Date on 2019/03/13.
 * @org 上海..科技有限公司
 * @describe
 */
public class VideoBean {

    /**
     * 可以自行封装
     */
    private String title;
    private String url;
    private String thumb;
    private String like;
    private String msg;
    private String share;
    public String getLike() {
        return like;
    }
    public void setLike(String like) {
        this.like = like;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public VideoBean(String title, String thumb, String url, String like, String msg, String share) {
        this.title = title;
        this.thumb = thumb;
        this.url = url;
        this.like = like;
        this.msg = msg;
        this.share = share;
    }
}
