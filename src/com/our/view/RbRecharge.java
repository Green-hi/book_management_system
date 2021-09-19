package com.our.view;


import com.our.service.RbService;
import com.our.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RbRecharge extends JFrame {

    private JPanel contentPane;

    private JTextField textField_1;
    private String uid=null;
    private int crb=0;


    public RbRecharge () {
        super("rb充值");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(725, 350, 450, 300);

        Toolkit tk=Toolkit.getDefaultToolkit();
        Image im=tk.createImage("image/rbcz.png");
        setIconImage(im);

        contentPane = new JPanel();
        //contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblUid = new JLabel("选择用户");
        lblUid.setBounds(94, 86, 70, 21);
        contentPane.add(lblUid);


        UserService userService=new UserService();
        JComboBox<String> suid = userService.getuidBox();
        suid.setBounds(200, 83, 150, 27);
        contentPane.add(suid);


        JLabel lblJine = new JLabel("金额");
        lblJine.setBounds(94, 139, 70, 21);
        contentPane.add(lblJine);

        textField_1 = new JTextField();
        textField_1.setBounds(200, 136, 150, 27);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JButton button = new JButton("充值");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                uid=suid.getSelectedItem().toString().substring(0,8);
                RbService rbService=new RbService(uid);
                crb=Integer.parseInt(textField_1.getText());
                if(crb>0){
                    if(rbService.recharge(crb)) {JOptionPane.showMessageDialog(null, "充值成功");}
                    else {JOptionPane.showMessageDialog(null, "操作失败");}
                }
                else JOptionPane.showMessageDialog(null, "输入格式错误，请重新输入");
            }
        });
        button.setBounds(170, 189, 69, 29);
        contentPane.add(button);

    }
}
