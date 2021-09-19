package com.our.bean;

import java.sql.Date;

public class DonateBean {
    /**
     * 用户的捐赠表的bean
     */
    private int id;//id
    private String uid;
    private String bid;//书id
    private int c_rb;
    private Date date;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }
    public int getC_rb() {
        return c_rb;
    }
    public void setC_rb(int c_rb) {
        this.c_rb = c_rb;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
