package com.our.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import com.our.bean.RechargeBean;
import com.our.util.DBUtil;

/**
 * 关于充值的所有操作的类
 */
public class RechargeDao {
    /**
     * 添加充值信息，传入所有的信息
     *
     * @param uid
     * @param crb
     * @param date
     * @throws SQLException
     */
    public static void addRecharge(String uid, int crb, Date date) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "insert into recharge(u_id,c_rb,date) values(?,?,?)";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, uid);
            stm.setInt(2, crb);
            stm.setDate(3, date);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 根据uid,获取该用户的所有充值信息，返回的是ArrayList数组形式
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public static ArrayList<RechargeBean> get_ListInfo(String uid) throws SQLException {
        ArrayList<RechargeBean> tag_Array = new ArrayList<RechargeBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from recharge where u_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1,uid);
            rs = stm.executeQuery();
            while (rs.next()) {
                RechargeBean tag = new RechargeBean();
                tag.setId(rs.getInt(1));
                tag.setUid(rs.getString(2));
                tag.setC_rb(rs.getInt(3));
                tag.setDate(rs.getDate(4));
                tag_Array.add(tag);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }
}
