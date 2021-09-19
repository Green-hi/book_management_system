package com.our.view;

import com.our.service.UserService;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginServlet extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    String st1=null;


    public LoginServlet() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }catch(Exception e) {
            System.out.println(e);
        }

        Toolkit tk=Toolkit.getDefaultToolkit();
        Image im=tk.createImage("image/1.ico");
        setIconImage(im);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 250, 650, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        Container c = getContentPane();
        setVisible(true);

        JLabel tlable=new JLabel("图书管理系统");
        tlable.setIcon(new ImageIcon("image/2.png"));
        tlable.setBounds(185,10,350,200);
        tlable.setFont(new Font("楷体", Font.PLAIN, 45));
        add(tlable);

        JLabel label = new JLabel("账号：");
        //label.setIcon(new ImageIcon("image/static/login.ico"));
        label.setBounds(195, 178, 81, 25);
        contentPane.add(label);

        textField = new JTextField();
        textField.setBounds(267, 175, 196, 35);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel label_1 = new JLabel("密码：");
        label_1.setBounds(195, 228, 81, 25);
        contentPane.add(label_1);

        passwordField = new JPasswordField();
        passwordField.setBounds(267, 225, 196, 35);
        contentPane.add(passwordField);

        JButton button = new JButton("用户登陆",new ImageIcon("image/yh.png"));
        button.setBounds(132, 327, 120, 40);
        contentPane.add(button);

        JButton button_1 = new JButton("管理员登陆",new ImageIcon("image/gly.png"));
        button_1.setBounds(277, 327, 120, 40);
        contentPane.add(button_1);

        JButton button_2 = new JButton("账号注册",new ImageIcon("image/yhzc.png"));
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                new UserRegister();
            }
        });
        button_2.setBounds(422, 327, 120, 40);
        contentPane.add(button_2);

        ButtonGroup group =new ButtonGroup();
        group.add(button);
        group.add(button_1);
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserService userService = new UserService();

                    st1 = textField.getText();
                    String st2 = new String(passwordField.getPassword());
                    if (st1.length() == 0) {
                        JOptionPane.showMessageDialog(null, "姓名不能为空");
                        return;
                    }
                    else if (st2.length() == 0) {
                    JOptionPane.showMessageDialog(null, "id不能为空");
                    return;
                    }
                    else if(!userService.managerLogin(st2,st1)) {
                    JOptionPane.showMessageDialog(null, "账号或密码不正确");
                    return;
                    }
                    else{
                        new ManagerMenu();
                    }
                    dispose();

            }
        });
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserService userService = new UserService();

                    st1 = textField.getText();
                    String st2 = new String(passwordField.getPassword());

                    if (st1.length() == 0) {
                        JOptionPane.showMessageDialog(null, "账号不能为空");
                        return;
                    }
                    else if (st2.length() == 0) {
                        JOptionPane.showMessageDialog(null, "密码不能为空");
                        return;
                    }
                    else if(!userService.login(st1,st2)) {
                        JOptionPane.showMessageDialog(null, "账号或密码不正确");
                        return;
                    }
                    else{
                        new Menu(st1);
                    }
                    dispose();// 隐藏登录窗口

            }
        });
    }
}