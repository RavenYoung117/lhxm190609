package com.qf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Blog {
    private Long bolgId;

    private String title;

    private String imageUrl;

    private String content;

    private String cimage;

    private Long reply;

    private Long collect;

    private Long like;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pubDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date editDate;

    private Long bolgstate;

    private Long tId;

    private Long uId;

    private Users users;

    @JsonIgnore
    private List<Replyblog> replyblogList;

    public List<Replyblog> getReplyblogList() {
        return replyblogList;
    }

    public void setReplyblogList(List<Replyblog> replyblogList) {
        this.replyblogList = replyblogList;
    }


    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Long getBolgId() {
        return bolgId;
    }

    public void setBolgId(Long bolgId) {
        this.bolgId = bolgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCimage() {
        return cimage;
    }

    public void setCimage(String cimage) {
        this.cimage = cimage == null ? null : cimage.trim();
    }

    public Long getReply() {
        return reply;
    }

    public void setReply(Long reply) {
        this.reply = reply;
    }

    public Long getCollect() {
        return collect;
    }

    public void setCollect(Long collect) {
        this.collect = collect;
    }

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Long getBolgstate() {
        return bolgstate;
    }

    public void setBolgstate(Long bolgstate) {
        this.bolgstate = bolgstate;
    }

    public Long gettId() {
        return tId;
    }

    public void settId(Long tId) {
        this.tId = tId;
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }
}