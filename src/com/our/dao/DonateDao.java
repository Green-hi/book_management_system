package com.our.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import com.our.bean.DonateBean;
import com.our.util.DBUtil;

/**
 * 关于捐书的所有操作的类
 */
public class DonateDao {
    /**
     * 添加捐书信息，传入所有的信息
     *
     * @param uid
     * @param bid
     * @param crb
     * @param date
     * @throws SQLException
     */
    public static void addDonate(String uid, int bid, int crb, Date date) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "insert into donate(u_id,b_id,c_rb,date) values(?,?,?,?)";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, uid);
            stm.setInt(2, bid);
            stm.setInt(3, crb);
            stm.setDate(4, date);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 根据uid,获取该用户的所有捐书信息，返回的是ArrayList数组形式
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public static ArrayList<DonateBean> get_ListInfo(String uid) throws SQLException {
        ArrayList<DonateBean> tag_Array = new ArrayList<DonateBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from donate where u_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1,uid);
            rs = stm.executeQuery();
            while (rs.next()) {
                DonateBean tag = new DonateBean();
                tag.setId(rs.getInt(1));
                tag.setUid(rs.getString(2));
                tag.setBid(rs.getString(3));
                tag.setC_rb(rs.getInt(4));
                tag.setDate(rs.getDate(5));
                tag_Array.add(tag);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }
}
