package com.our.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

import com.our.bean.BorrowBean;
import com.our.util.DBUtil;

/**
 * 有关借阅历史的连接数据库操作类，写入借阅信息，修改借阅信息，查询借阅历史
 */
public class BorrowDao {
    /**
     * 添加借阅记录
     *
     * @param uid
     * @param bid
     * @throws SQLException
     */
    public static void addBorrow(String uid, int bid) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "insert into borrow(u_id,b_id,b_date,r_date,c_rb) values(?,?,null,null,0)";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, uid);
            stm.setInt(2, bid);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 删除借阅信息，根据传入的uid,bid作为条件
     *
     * @param ID
     * @throws SQLException
     */
    public static void deleteBorrow(String ID) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "delete from borrow where ID=?";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 更新借阅时间，根据传入的ID作为条件
     *
     * @param ID
     * @param bdate
     * @throws SQLException
     */
    public static void updateBorrow(String ID, Date bdate) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "update borrow set b_date=? where ID=?";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setDate(1, bdate);
            stm.setString(2, ID);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 更新还书时间、花费rb，根据传入的ID作为条件
     *
     * @param ID
     * @param rdate
     * @param crb
     * @throws SQLException
     */
    public static void updateBorrow(String ID, Date rdate, int crb) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "update borrow set r_date=?,c_rb=? where ID=?";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setDate(1, rdate);
            stm.setInt(2, crb);
            stm.setString(3, ID);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 根据uid,获取该用户的所有已归还的借阅信息，返回的是ArrayList数组形式
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public static ArrayList<BorrowBean> get_ListInfo(String uid) throws SQLException {
        ArrayList<BorrowBean> tag_Array = new ArrayList<BorrowBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select ID,b_id,b_date,r_date,c_rb from borrow where r_date is not null and u_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, uid);
            rs = stm.executeQuery();
            while (rs.next()) {
                BorrowBean tag = new BorrowBean();
                tag.setId(rs.getInt(1));
                tag.setBid(rs.getString(2));
                tag.setB_date(rs.getDate(3));
                tag.setR_date(rs.getDate(4));
                tag.setC_rb(rs.getInt(5));
                tag_Array.add(tag);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }

    /**
     * 返回所有未归还书籍相关信息，返回的是ArrayList数组形式
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<BorrowBean> getListInfo() throws SQLException {
        ArrayList<BorrowBean> tag_Array = new ArrayList<BorrowBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select ID,u_id,b_id,b_date from borrow where r_date is null and b_date is not null";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                BorrowBean tag = new BorrowBean();
                tag.setId(rs.getInt(1));
                tag.setUid(rs.getString(2));
                tag.setBid(rs.getString(3));
                tag.setB_date(rs.getDate(4));
                tag_Array.add(tag);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }



    /**
     * 根据用户id，返回所有未归还书籍相关信息，返回的是ArrayList数组形式
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public static ArrayList<BorrowBean> getListInfo(String uid) throws SQLException {
        ArrayList<BorrowBean> tag_Array = new ArrayList<BorrowBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select ID,b_id,b_date from borrow where r_date is null and b_date is not null and u_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;

        stm = conn.prepareStatement(sql);
        stm.setString(1,uid);
        rs = stm.executeQuery();
        while (rs.next()) {
            BorrowBean tag = new BorrowBean();
            tag.setId(rs.getInt(1));
            tag.setBid(rs.getString(2));
            tag.setB_date(rs.getDate(3));
            tag_Array.add(tag);
        }

        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }

    /**
     * 返回所有待审核信息
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<BorrowBean> getPassInfo() throws SQLException {
        ArrayList<BorrowBean> tag_Array = new ArrayList<BorrowBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select ID,u_id,b_id from borrow where b_date is null";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                BorrowBean tag = new BorrowBean();
                tag.setId(rs.getInt(1));
                tag.setUid(rs.getString(2));
                tag.setBid(rs.getString(3));
                tag_Array.add(tag);
            }
        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }

    /**
     * 根据ID，查询借阅时间
     *
     * @param ID
     * @return
     * @throws SQLException
     */
    public static Date getDate(String ID) throws SQLException {
        Date date = null;
        Connection conn = DBUtil.getConnectDb();
        String sql = "select b_date from borrow where ID=?";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, ID);
            rs = stm.executeQuery();
            if (rs.next()) {
                date = rs.getDate(1);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return date;
    }
}