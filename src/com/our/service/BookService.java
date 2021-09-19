package com.our.service;

import com.our.dao.BookDao;
import com.our.dao.BuyDao;
import com.our.dao.DonateDao;
import com.our.dao.UserDao;

import java.sql.Date;
import java.sql.SQLException;

public class BookService {

    Date date = new Date(System.currentTimeMillis());

    /**
     * 实现管理员界面的买书功能
     *
     * @param mid
     * @param title
     * @param author
     * @param publisher
     * @param money
     * @return
     */
    public boolean buy(String mid, String title,String author,String publisher,int money){

        try {
            if(BookDao.bookExist(title,author,publisher)){
                int bid=BookDao.getBookID(title,author,publisher);
                BookDao.updateQuantity(bid,1);
                System.out.println("图书已入库！");
            }else{
                BookDao.addBook(title,author,publisher,1);
                System.out.println("图书已入库！");
            }
            int bid=BookDao.getBookID(title,author,publisher);
            BuyDao.addBuy(mid,bid,money,date);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 实现管理员界面的捐书功能
     *
     * @param uid
     * @param title
     * @param author
     * @param publisher
     * @param rb
     * @return
     */
    public boolean donate(String uid,String title,String author,String publisher,int rb){

        try {
            if(BookDao.bookExist(title,author,publisher)){
                int bid=BookDao.getBookID(title,author,publisher);
                BookDao.updateQuantity(bid,1);
                System.out.println("图书已入库！");
            }else{
                BookDao.addBook(title,author,publisher,1);
                System.out.println("图书已入库！");
            }
            int bid=BookDao.getBookID(title,author,publisher);
            UserDao.updateUser(uid,rb);
            DonateDao.addDonate(uid,bid,rb,date);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    /**
     * 管理员更新书籍信息
     *
     * @param bid
     * @param title
     * @param author
     * @param publisher
     * @return
     */
    public boolean update(int bid,String title,String author,String publisher){
        try {
            BookDao.updateBook(bid,title,author,publisher);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    /**
     * 删除书籍
     *
     * @param bid
     * @return
     */
    public boolean delete(int bid){
        try {
            BookDao.deleteBook(bid);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
