package com.our.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import com.our.bean.BuyBean;
import com.our.util.DBUtil;

public class BuyDao {
    /**
     * 添加购书信息，传入所有的信息
     *
     * @param mid
     * @param bid
     * @param money
     * @param date
     * @throws SQLException
     */
    public static void addBuy(String mid, int bid, int money, Date date) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "insert into buy(m_id,b_id,money,date) values(?,?,?,?)";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, mid);
            stm.setInt(2, bid);
            stm.setInt(3, money);
            stm.setDate(4, date);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 获取所有购书信息，返回的是ArrayList数组形式
     *
     * @param mid
     * @return
     * @throws SQLException
     */
    public ArrayList<BuyBean> get_ListInfo(String mid) throws SQLException {
        ArrayList<BuyBean> tag_Array = new ArrayList<BuyBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from buy where m_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1,mid);
            rs = stm.executeQuery();
            while (rs.next()) {
                BuyBean tag = new BuyBean();
                tag.setBid(rs.getString(1));
                tag.setMid(rs.getString(2));
                tag.setBid(rs.getString(3));
                tag.setMoney(rs.getInt(4));
                tag.setDate(rs.getDate(5));
                tag_Array.add(tag);
            }
        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }

    /**
     * 获取所有的购书信息，返回的是ArrayList数组形式
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<BuyBean> get_ListInfo() throws SQLException {
        ArrayList<BuyBean> tag_Array = new ArrayList<BuyBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from buy";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                BuyBean tag = new BuyBean();
                tag.setBid(rs.getString(1));
                tag.setMid(rs.getString(2));
                tag.setBid(rs.getString(3));
                tag.setMoney(rs.getInt(4));
                tag.setDate(rs.getDate(5));
                tag_Array.add(tag);
            }
        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }
}
