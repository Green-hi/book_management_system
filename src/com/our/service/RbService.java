package com.our.service;

import com.our.dao.RechargeDao;
import com.our.dao.UserDao;
import java.sql.Date;
import java.sql.SQLException;

public class RbService {

    private String uid;

    public RbService(String uid){
        this.uid=uid;
    }

    /**
     * rb充值
     *
     * @param crb
     * @return
     */
    public boolean recharge(int crb){
        Date date = new Date(System.currentTimeMillis());
        try {
            if(UserDao.userExist(uid)&&crb>0){
                RechargeDao.addRecharge(uid,crb,date);
                UserDao.updateUser(uid,crb);
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 获取rb余额
     *
     * @return
     */
    public int getBalance(){
        int userBalance = 0;
        try {
            userBalance = UserDao.getUserBalance(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userBalance;
    }
}