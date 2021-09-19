package com.our.service;

import com.our.bean.BorrowBean;
import com.our.dao.BookDao;
import com.our.dao.BorrowDao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PassService {

    /**
     * 获取待审核的预约记录
     *
     * @return
     */
    public ArrayList<BorrowBean> getPassList(){
        ArrayList<BorrowBean> borrowBeanArrayList=null;
        try {
            borrowBeanArrayList = BorrowDao.getPassInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return borrowBeanArrayList;
    }

    /**
     * 实现管理员通过借书请求
     *
     * @param ID
     * @return
     */
    public boolean doPass(String ID){
        Date date = new Date(System.currentTimeMillis());
        try {
            BorrowDao.updateBorrow(ID,date);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 实现管理员驳回借书请求
     *
     * @param ID
     * @param bid
     * @return
     */
    public boolean doNotPass(String ID,int bid){
        try {
            BookDao.updateQuantity(bid,1);
            BorrowDao.deleteBorrow(ID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
