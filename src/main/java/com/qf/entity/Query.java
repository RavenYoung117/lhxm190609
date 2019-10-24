package com.qf.entity;

public class Query {
    private Long qId;

    private String record;

    private String qType;

    private Long uId;

    public Long getqId() {
        return qId;
    }

    public void setqId(Long qId) {
        this.qId = qId;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record == null ? null : record.trim();
    }

    public String getqType() {
        return qType;
    }

    public void setqType(String qType) {
        this.qType = qType == null ? null : qType.trim();
    }

    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }
}