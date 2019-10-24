package com.qf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Answertopic {
    private Long answerId;

    private Long tId;

    private Long uId;

    private Long like;

    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time;

    private String answerimage;

    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
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

    public Long getLike() {
        return like;
    }

    public void setLike(Long like) {
        this.like = like;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAnswerimage() {
        return answerimage;
    }

    public void setAnswerimage(String answerimage) {
        this.answerimage = answerimage;
    }
}