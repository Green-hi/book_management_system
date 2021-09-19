package com.our.service;

import com.our.bean.ManagerBean;
import com.our.bean.UserBean;
import com.our.dao.ManagerDao;
import com.our.dao.UserDao;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    /**
     * 判断用户是否存在
     *
     * @param uid
     * @return
     */
    public boolean userExist(String uid){
        try {
            if(UserDao.userExist(uid)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 用户登录
     *
     * @param uid
     * @param password
     * @return
     */
    public boolean login(String uid,String password){
        try {
            if(UserDao.Login(uid,password)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 用户注册
     *
     * @param uid
     * @param name
     * @param password
     * @return
     */
    public boolean register(String uid, String name, String password){
        try {
            if(UserDao.Register(uid,name,password)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 删除用户
     *
     * @param uid
     * @return
     */
    public boolean delete(String uid){
        try {
            UserDao.deleteUser(uid);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 修改用户rb余额，value为增量
     *
     * @param uid
     * @param value
     * @return
     */
    public boolean update(String uid,int value){
        try {
            UserDao.updateUser(uid,value);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 修改用户密码
     *
     * @param uid
     * @param password
     * @return
     */
    public boolean update(String uid,String password){
        try {
            UserDao.updateUser(uid,password);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * 管理员登录
     *
     * @param mid
     * @param name
     * @return
     */
    public boolean managerLogin(String mid, String name){
        try {
            if(ManagerDao.Login(mid,name)){
                return true;
            }else{
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
    public JComboBox<String> getuidBox(){
        JComboBox<String> suid = new JComboBox<String>();
        UserDao b=new UserDao();
        List<UserBean> list= null;
        try {
            list = b.get_ListInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(int i=0;i<list.size();i++){
            suid.addItem(list.get(i).getUid()+list.get(i).getName());
        }
        return suid;
    }
    public JComboBox<String> gettotalBox(){
        JComboBox<String> suid = new JComboBox<String>();
        UserDao b=new UserDao();
        List<UserBean> list= null;
        try {
            list = b.get_ListInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(int i=0;i<list.size();i++){
            suid.addItem(list.get(i).getUid()+list.get(i).getName());
        }

        suid.addItem("--------分割项");

        ManagerDao a=new ManagerDao();
        List<ManagerBean> list1=null;
        try {
            list1 = a.get_ListInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for(int i=0;i<list1.size();i++){
            suid.addItem(list1.get(i).getMid()+list1.get(i).getName());
        }
        return suid;
    }
}
