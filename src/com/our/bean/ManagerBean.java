package com.our.bean;

public class ManagerBean {
    /**
     * 管理员的数据表的bean
     */
    private String mid;//id
    private String name;//管理员的姓名
    public String getMid() {
        return mid;
    }
    public void setMid(String mid) {
        this.mid = mid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
