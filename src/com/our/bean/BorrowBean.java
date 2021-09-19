package com.our.bean;

import java.sql.Date;

public class BorrowBean {
    /**
     * 用户的借阅表的bean
     */
    private int id;//id
    private String uid;
    private String bid;//书id
    private Date b_date;
    private Date r_date;
    private int c_rb;
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
    public Date getB_date() {
        return b_date;
    }
    public void setB_date(Date b_date) {
        this.b_date = b_date;
    }
    public Date getR_date() {
        return r_date;
    }
    public void setR_date(Date r_date) {
        this.r_date = r_date;
    }
    public int getC_rb() {
        return c_rb;
    }
    public void setC_rb(int c_rb) {
        this.c_rb = c_rb;
    }

}
