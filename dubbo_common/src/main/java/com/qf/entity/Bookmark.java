package com.qf.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class Bookmark implements Serializable {
    private Integer bId;

    private String bName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bDate;

    private String dDecr;

    private Integer uid;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getbId() {
        return bId;
    }

    public void setbId(Integer bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName == null ? null : bName.trim();
    }

    public Date getbDate() {
        return bDate;
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    public String getdDecr() {
        return dDecr;
    }

    public void setdDecr(String dDecr) {
        this.dDecr = dDecr == null ? null : dDecr.trim();
    }
}