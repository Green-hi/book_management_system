package com.our.bean;

import java.sql.Date;

public class BuyBean {
    /**
     * 管理员购书表的bean
     */
    private int id;//id
    private String mid;
    private String bid;//书id
    private int money;
    private Date date;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getMid() { return mid; }
    public void setMid(String mid) {
        this.mid = mid;
    }
    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) { this.money = money; }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
}
