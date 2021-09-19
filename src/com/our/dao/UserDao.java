package com.our.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.our.bean.UserBean;
import com.our.util.DBUtil;

/**
 * 有关读者账号的连接数据库操作，登录验证，注册，修改账号，修改密码
 */
public class UserDao {

    /**
     * 登录验证功能，传入账号和密码，在数据库中查找，如果找到了，返回true，没找到则返回false
     *
     * @param uid
     * @param password
     * @return
     * @throws SQLException
     */
    public static boolean Login(String uid, String password) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from users where u_id='" + uid + "' and password='" + password + "'";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            if (rs.next()) {
                DBUtil.CloseDB(rs, stm, conn);
                return true;
            }



        return false;
    }

    /**
     * 注册账号的函数，传入账号，姓名，密码
     *
     * @param uid
     * @param name
     * @param password
     * @return
     * @throws SQLException
     */
    public static boolean Register(String uid, String name, String password) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "insert into users(u_id,name,password,balance) values(?,?,?,?)";

        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, uid);
            stm.setString(2, name);
            stm.setString(3, password);
            stm.setInt(4, 0);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

        return true;
    }

    /**
     * 删除用户的信息，根据传入的uid作为条件
     *
     * @param uid
     * @throws SQLException
     */
    public static void deleteUser(String uid) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "delete from users where u_id=?";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, uid);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 修改用户的rb余额，根据传入的uid作为条件
     *
     * @param uid
     * @param value
     * @throws SQLException
     */
    public static void updateUser(String uid,int value) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "update users set balance=balance+(?) where u_id=?";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setInt(1, value);
            stm.setString(2, uid);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 修改用户的password，根据传入的uid作为条件
     *
     * @param uid
     * @param password
     * @throws SQLException
     */
    public static void updateUser(String uid,String password) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "update users set password=? where u_id=?";
        PreparedStatement stm = null;

            stm = conn.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, uid);
            stm.executeUpdate();

        DBUtil.CloseDB(null, stm, conn);

    }

    /**
     * 根据uid,获取该用户的余额信息
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public static int getUserBalance(String uid) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        String sql = "select balance from users where u_id=?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        int rb=0;

            stm = conn.prepareStatement(sql);
            stm.setString(1,uid);
            rs = stm.executeQuery();
            while (rs.next()) {
                rb=rs.getInt(1);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return rb;
    }

    /**
     * 获取所有的用户信息，返回的是ArrayList数组形式
     *
     * @return
     * @throws SQLException
     */
    public static ArrayList<UserBean> get_ListInfo() throws SQLException {
        ArrayList<UserBean> tag_Array = new ArrayList<UserBean>();
        Connection conn = DBUtil.getConnectDb();
        String sql = "select * from users";
        PreparedStatement stm = null;
        ResultSet rs = null;

            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                UserBean tag = new UserBean();
                tag.setUid(rs.getString(1));
                tag.setName(rs.getString(2));
                tag.setPassword(rs.getString(3));
                tag.setBalance(rs.getInt(4));
                tag_Array.add(tag);
            }

        DBUtil.CloseDB(rs, stm, conn);

        return tag_Array;
    }

    /**
     * 根据uid，判断是否存在该用户，如果存在返回true
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public static boolean userExist(String uid) throws SQLException {
        Connection conn = DBUtil.getConnectDb();
        PreparedStatement stm=null;
        ResultSet rs=null;
        String sql="select * from users where u_id='"+uid+"'";

            stm=conn.prepareStatement(sql);
            rs=stm.executeQuery();
            if(rs.next()){
                return true;
            }

        DBUtil.CloseDB(rs, stm, conn);

        return false;
    }
}