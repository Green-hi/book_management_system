package com.our.service;

import com.our.bean.BookBean;
import com.our.dao.BookDao;
import com.our.dao.BorrowDao;

import java.sql.SQLException;
import java.util.ArrayList;

public class BorrowService {

    private String uid;

    public BorrowService(String uid){
        this.uid=uid;
    }

    public String getUid() {
        return uid;
    }

    /**
     * 实现用户的图书检索功能
     *
     * @param title
     * @param author
     * @param publisher
     * @return
     */
    public ArrayList<BookBean> retrieval(String title, String author, String publisher){
        ArrayList<BookBean> bookBeanArrayList=null;
        try {
            bookBeanArrayList = BookDao.getLikeList(title,author,publisher);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookBeanArrayList;
    }

    /**
     * 实现用户预约图书功能
     *
     * @param bid
     * @return
     */
    public boolean order(int bid) {

        try {
            BookDao.updateQuantity(bid, -1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        try {
            BorrowDao.addBorrow(uid, bid);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                BookDao.updateQuantity(bid, 1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
}
