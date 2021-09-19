package com.our.service;

import com.our.bean.BorrowBean;
import com.our.dao.BookDao;
import com.our.dao.BorrowDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReturnService {

    /**
     * 获取待还书信息
     *
     * @return
     */
    public ArrayList<BorrowBean> getReturnList(){
        ArrayList<BorrowBean> borrowBeanArrayList=null;
        try {
            borrowBeanArrayList = BorrowDao.getListInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return borrowBeanArrayList;
    }

    /**
     * 获取指定用户的待还书信息
     *
     * @param uid
     * @return
     */
    public ArrayList<BorrowBean> getReturnList(String uid){
        ArrayList<BorrowBean> borrowBeanArrayList=null;
        try {
            borrowBeanArrayList = BorrowDao.getListInfo(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return borrowBeanArrayList;
    }

    /**
     * 实现管理员还书功能
     *
     * @param ID
     * @param bid
     * @return
     */
    public boolean doReturn(String ID,int bid){
        Date r_date = new Date(System.currentTimeMillis());
        Date b_date = null;
        try {
            b_date = BorrowDao.getDate(ID);
            BookDao.updateQuantity(bid,1);
            BorrowDao.updateBorrow(ID,r_date,getCrb(b_date,r_date));
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 计算用户花费rb数量的方法
     *
     * @param b_date
     * @param r_date
     * @return
     */
    private static int getCrb(Date b_date,Date r_date){
        int a = r_date.getDate()-b_date.getDate();
        if(a<0){
            a+=31;
        }
        return a;
    }

    /*public static void main(String[] args) {
        Date a = new Date(System.currentTimeMillis());
        System.out.println(Calendar.DAY_OF_MONTH);
    }*/
}
