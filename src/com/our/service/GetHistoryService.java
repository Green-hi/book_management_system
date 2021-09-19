package com.our.service;

import com.our.bean.BorrowBean;
import com.our.bean.BuyBean;
import com.our.bean.DonateBean;
import com.our.bean.RechargeBean;
import com.our.dao.BorrowDao;
import com.our.dao.BuyDao;
import com.our.dao.DonateDao;
import com.our.dao.RechargeDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetHistoryService {

    private String id;

    public GetHistoryService(String id){
        this.id=id;
    }

    /**
     * 获取特定用户的借阅历史信息
     *
     * @return
     */
    public ArrayList<BorrowBean> getBorrowHistory(){
        ArrayList<BorrowBean> borrowBeanArrayList= null;
        try {
            borrowBeanArrayList = BorrowDao.get_ListInfo(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return borrowBeanArrayList;
    }

    /**
     * 获取特定用户的捐赠历史信息
     *
     * @return
     */
    public ArrayList<DonateBean> getDonateHistory(){
        ArrayList<DonateBean> donateBeanArrayList= null;
        try {
            donateBeanArrayList = DonateDao.get_ListInfo(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return donateBeanArrayList;
    }

    /**
     * 获取特定用户的充值历史信息
     *
     * @return
     */
    public ArrayList<RechargeBean> getRechargeHistory(){
        ArrayList<RechargeBean> rechargeBeanArrayList= null;
        try {
            rechargeBeanArrayList = RechargeDao.get_ListInfo(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rechargeBeanArrayList;
    }

    /**
     * 获取管理员的买书信息
     *
     * @return
     */
    public ArrayList<BuyBean> getBuyHistory(){
        ArrayList<BuyBean> buyBeanArrayList= null;
        try {
            buyBeanArrayList = BuyDao.get_ListInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return buyBeanArrayList;
    }
}
