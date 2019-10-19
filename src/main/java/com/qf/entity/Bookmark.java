package com.qf.entity;

import java.util.Date;

public class Bookmark {
    private Integer bId;

    private String bName;

    private Date bDate;

    private String dDecr;

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