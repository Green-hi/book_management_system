package com.our.view;

import com.our.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagement extends JFrame {
    private String uid=null;
    UserService user = new UserService();
    public UserManagement(){
        setLocation(750, 350);
        setVisible(true);
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        Toolkit tk=Toolkit.getDefaultToolkit();
        Image im=tk.createImage("image/yhgl.ico");
        setIconImage(im);

        JComboBox<String> suid=user.getuidBox();

        JButton jb1=new JButton("删除用户");
        JButton jb2=new JButton("重置密码");
        JLabel jlb=new JLabel("选择用户");
        JPanel jp1=new JPanel();
        JPanel jp2=new JPanel();
        jp2.add(jlb);
        jp2.add(suid);
        jp1.add(jb1);
        jp1.add(jb2);
        getContentPane().add(jp1, BorderLayout.SOUTH);
        getContentPane().add(jp2,BorderLayout.CENTER);
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uid=suid.getSelectedItem().toString().substring(0,8);
                if(user.delete(uid)) {
                    JOptionPane.showMessageDialog(null, "该用户已成功删除");}
                else JOptionPane.showMessageDialog(null, "操作失败");
            }
        });
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uid=suid.getSelectedItem().toString().substring(0,8);
                if(user.update(uid,"123456")){
                    JOptionPane.showMessageDialog(null, "密码已修改为123456");
                }
                else JOptionPane.showMessageDialog(null, "操作失败");
            }
        });
    }
}
