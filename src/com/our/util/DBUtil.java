package com.our.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    /**
     * 连接数据库的操作，用户名，密码，使用jdbc连接
     */
    //10.138.255.206
//    static final String url = "jdbc:sqlserver://10.138.255.206:1433;DatabaseName=our_library";
//    static final String username = "sc";
    private static final String url = "jdbc:sqlserver://localhost:1433;DatabaseName=our_library";
    private static final String username = "sc";
    private static final String password = "1234567890ABC";

    static{
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnectDb(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,username,password);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return conn;
    }

    public static void CloseDB(ResultSet rs, PreparedStatement stm, Connection conn){
        if(rs!=null)
        {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stm!=null)
        {
            try {
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null)
        {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
