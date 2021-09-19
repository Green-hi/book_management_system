package com.our.view;

import com.our.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class UserRegister extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField textField_1;
    private JPasswordField textField_2;
    private JTextField textField_3;
    private String uid = null;
    private String name = null;
    private String password = null;
    private String password_1 = null;

    public UserRegister() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("用户注册");
        setBounds(690, 340, 520, 320);
        contentPane = new JPanel();


        Toolkit tk=Toolkit.getDefaultToolkit();
        Image im=tk.createImage("image/yhzc.png");
        setIconImage(im);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel label = new JLabel("密码");
        label.setBounds(83, 122, 72, 25);
        contentPane.add(label);

        JLabel label_1 = new JLabel("确认密码");
        label_1.setBounds(59, 169, 90, 25);
        contentPane.add(label_1);

        textField = new JTextField();
        textField.setBounds(237, 77, 147, 30);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JPasswordField();
        textField_1.setBounds(237, 119, 147, 30);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JPasswordField();
        textField_2.setBounds(237, 166, 147, 30);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblUid = new JLabel("u_id:");
        lblUid.setBounds(83, 34, 72, 25);
        contentPane.add(lblUid);

        textField_3 = new JTextField("用户id为八位数字");
        textField_3.setColumns(10);
        textField_3.setBounds(237, 31, 147, 30);
        textField_3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField_3.setText("");
            }
            public void mousePressed(MouseEvent e) { }
            public void mouseReleased(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
        });
        contentPane.add(textField_3);

        JLabel label_2 = new JLabel("姓名：");
        label_2.setBounds(83, 80, 72, 25);
        contentPane.add(label_2);

        JButton button = new JButton("注册");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                uid = textField_3.getText();
                name = textField.getText();
                password = textField_1.getText();
                password_1 = textField_2.getText();
                UserService userService = new UserService();
                if (uid.length() == 0 || name.length() == 0 || password.length() == 0 || password_1.length() == 0) {
                    JOptionPane.showMessageDialog(null, "请补全注册信息");
                }
                else if(uid.length()!=8){
                    JOptionPane.showMessageDialog(null, "id必须为八位数字，请重新输入");
                }
                else {
                    if (!userService.userExist(uid)) {
                        if (password.equals(password_1)) {
                            if (userService.register(uid, name, password))
                                JOptionPane.showMessageDialog(null, "注册成功");
                            else
                                JOptionPane.showMessageDialog(null, " 注册失败");
                        } else
                            JOptionPane.showMessageDialog(null, "密码输入不一致");
                    } else
                        JOptionPane.showMessageDialog(null, "该用户已存在");
                }
                //隐藏窗口
            }
        });
        button.setForeground(Color.RED);
        button.setBounds(190, 225, 113, 27);
        contentPane.add(button);
    }

}
