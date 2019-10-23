package com.qf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Record {
    private Integer recordid;

    private Long tId;

    private Long uId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rTime;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
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

    public Date getrTime() {
        return rTime;
    }

    public void setrTime(Date rTime) {
        this.rTime = rTime;
    }
}