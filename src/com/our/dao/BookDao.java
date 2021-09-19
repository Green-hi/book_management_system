package com.our.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.our.bean.BookBean;
import com.our.util.DBUtil;

/**
 * 关于图书连接数据库的所有操作的类
 */
public class BookDao {
    /**
     * 添加图书信息，传入所有的信息
     *
     * @param title
     * @param author
     * @param publisher
     * @param quantity
     * @throws SQLException
     */
    public static void addBook(String title, String author, String publisher, int quantity) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "insert into books(title,author,publisher,quantity) values(?,?,?,?)";
        PreparedStatement stm = null;

        stm = conn.prepareStatement(sql);
        stm.setString(1, title);
        stm.setString(2, author);
        stm.setString(3, publisher);
        stm.setInt(4, quantity);
        stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);
    }

    /**
     * 删除图书信息，根据传入的bid作为条件
     *
     * @param bid
     * @throws SQLException
     */
    public static void deleteBook(int bid) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "delete from books where b_id=?";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setInt(1, bid);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     *修改图书的数量，bid作为条件
     *
     * @param bid
     * @param value
     * @throws SQLException
     */
    public static void updateQuantity(int bid, int value) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "update books set quantity=quantity+(?) where b_id=?";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setInt(1, value);
            stm.setInt(2, bid);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 根据bid，更新图书的三大属性
     *
     * @param bid
     * @param title
     * @param author
     * @param publisher
     * @throws SQLException
     */
    public static void updateBook(int bid,String title, String author, String publisher) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "update books set title=? , author=? , publisher=? where b_id=?";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2,author);
            stm.setString(3,publisher);
            stm.setInt(4, bid);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 获取指定图书的bid，以title、author、publisher为条件
     *
     * @param title
     * @param author
     * @param publisher
     * @return
     * @throws SQLException
     */
    public static int getBookID(String title,String author,String publisher) throws SQLException {
        int bid=0;
        Connection conn = DBUtil.getConnectDb();
        String sql = "select b_id from books where title=? and author=? and publisher=?";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, author);
            stm.setString(3,publisher);
            rs = stm.executeQuery();
            if(rs.next()){
                bid=rs.getInt(1);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return bid;
    }
    /**
     * 根据bid,获取书名title
     *
     * @param bid
     * @return
     * @throws SQLException
     */
    public static String getBookTitle(int bid) throws SQLException {

        String title=null;
        Connection conn = DBUtil.getConnectDb();
        String sql = "select title from books where b_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        stm = conn.prepareStatement(sql);
        stm.setInt(1, bid);
        rs = stm.executeQuery();
        if(rs.next()){
            title=rs.getString(1);
        }

        DBUtil.CloseDB(rs, stm, conn);

        return title;
    }
    /**
     * 用户查找图书，根据输入的书名/作者/出版社，使用like进行模糊查询，然后返回一个ArrayList数组类型
     *
     * @param title
     * @param author
     * @param publisher
     * @return
     * @throws SQLException
     */
    public static ArrayList<BookBean> getLikeList(String title,String author,String publisher) throws SQLException {
        ArrayList<BookBean> tag_Array = new ArrayList<BookBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from books where title like '%"+title+"%' and author like '%"+author+"%' and publisher like '%"+publisher+"%'";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()){
                BookBean tag = new BookBean();
                tag.setBid(rs.getInt(1));
                tag.setTitle(rs.getString(2));
                tag.setAuthor(rs.getString(3));
                tag.setPublisher(rs.getString(4));
                tag.setQuantity(rs.getInt(5));
                tag_Array.add(tag);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }

    /**
     * 获取所有的图书信息，返回的是ArrayList数组形式
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<BookBean> get_ListInfo() throws SQLException {
        ArrayList<BookBean> tag_Array = new ArrayList<BookBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from books";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                BookBean tag = new BookBean();
                tag.setBid(rs.getInt(1));
                tag.setTitle(rs.getString(2));
                tag.setAuthor(rs.getString(3));
                tag.setPublisher(rs.getString(4));
                tag.setQuantity(rs.getInt(5));
                tag_Array.add(tag);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }

    /**
     * 判断书本是否在书库中存在
     *
     * @param title
     * @param author
     * @param publisher
     * @return
     * @throws SQLException
     */
    public static boolean bookExist(String title,String author,String publisher) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        PreparedStatement stm=null;
        ResultSet rs=null;
        String sql="select b_id from books where title=? and author=? and publisher=?";

            stm=conn.prepareStatement(sql);
            stm.setString(1,title);
            stm.setString(2,author);
            stm.setString(3,publisher);
            rs=stm.executeQuery();
            if(rs.next()){
                DBUtil.CloseDB(rs, stm, conn);
                return true;
            }

        DBUtil.CloseDB(rs, stm, conn);

        return false;
    }
}