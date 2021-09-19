package com.our.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.our.bean.ManagerBean;
import com.our.util.DBUtil;

/**
 * 有关管理员账号的连接数据库操作，登录验证，注册，修改账号，修改密码
 */

public class ManagerDao {
    /**
     * 登录验证功能，传入账号和密码，在数据库中查找，如果找到了，返回true，没找到则返回false
     *
     * @param mid
     * @param name
     * @return
     * @throws SQLException
     */
    public static boolean Login(String mid, String name) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from managers where m_id='" + mid + "' and name='" + name + "'";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }

        DBUtil.CloseDB(rs, stm, conn);

        return false;
    }

    /**
     * 注册账号的函数，传入账号，姓名
     *
     * @param mid
     * @param name
     * @return
     * @throws SQLException
     */
    public static boolean Register(String mid, String name) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "insert into managers(m_id,name) values(?,?)";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, mid);
            stm.setString(2, name);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

        return true;
    }

    /**
     * 删除管理员的信息，根据传入的mid作为条件
     *
     * @param mid
     * @throws SQLException
     */
    public static void deleteManager(String mid) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "delete from managers where m_id=?";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, mid);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 获取所有的管理员信息，返回的是ArrayList数组形式
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<ManagerBean> get_ListInfo() throws SQLException {
        ArrayList<ManagerBean> tag_Array = new ArrayList<ManagerBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from managers";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                ManagerBean tag = new ManagerBean();
                tag.setMid(rs.getString(1));
                tag.setName(rs.getString(2));
                tag_Array.add(tag);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }
}
