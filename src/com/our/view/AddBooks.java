package com.our.view;

import com.our.service.BookService;
import com.our.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBooks extends JFrame {
    private String id=null;
    private String title=null;
    private String author=null;
    private String publisher=null;
    private int amount=0;
    public AddBooks() {
        setLocation(600, 200);
        setVisible(true);
        setSize(700, 600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setLayout(null);

        Toolkit tk=Toolkit.getDefaultToolkit();
        Image im=tk.createImage("image/tj.png");
        setIconImage(im);

        JTextField textFields[]= {
                new JTextField(),
                new JTextField(),
                new JTextField(),
                new JTextField()
        };
        JLabel jLabel[]={
                new JLabel("书名"),
                new JLabel("作者"),
                new JLabel("出版社"),
                new JLabel("rb/现金"),
        };

        UserService userService=new UserService();
        JComboBox<String> suid=userService.gettotalBox();
        suid.setBounds(350,130,150,40);
        add(suid);
        JLabel jLabel1=new JLabel("用户/管理员id");
        jLabel1.setBounds(225,120,100,50);
        add(jLabel1);
        for(int i=0;i<4;i++){
            jLabel[i].setBounds(225,190+i*50,100,50);
            textFields[i].setBounds(350,200+i*50,150,30);
            textFields[i].setColumns(15);
        }
        for(int i=0;i<4;i++){
            getContentPane().add(jLabel[i]);
            getContentPane().add(textFields[i]);
        }

        JButton jb1 = new JButton("书籍捐赠");
        JButton jb2 = new JButton("书籍购买");
        jb1.setBounds(230,400,100,30);
        jb2.setBounds(350,400,100,30);
        getContentPane().add(jb1);
        getContentPane().add(jb2);

        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                id=suid.getSelectedItem().toString().substring(0,8);
                title=textFields[0].getText();
                author=textFields[1].getText();
                publisher=textFields[2].getText();
                BookService bookService =new BookService();
                if(id!="--------"){
                    if(!textFields[3].getText().isEmpty()){
                        amount=Integer.parseInt(textFields[3].getText());
                        if(!title.isEmpty()&&!author.isEmpty()&&!publisher.isEmpty()){
                            if(bookService.donate(id,title,author,publisher,amount)){
                                JOptionPane.showMessageDialog(null, "录入成功");
                            }
                            else {
                                JOptionPane.showMessageDialog(null, "操作失败");
                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "请补全捐赠信息");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "请补全捐赠信息");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "请重新选择用户");
                }
            }
        });


        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                id=suid.getSelectedItem().toString().substring(0,8);
                title=textFields[0].getText();
                author=textFields[1].getText();
                publisher=textFields[2].getText();
                BookService bookService =new BookService();
                if(!textFields[3].getText().isEmpty()){
                    amount=Integer.parseInt(textFields[3].getText());
                    if(!title.isEmpty()&&!author.isEmpty()&&!publisher.isEmpty()){
                        if(bookService.buy(id,title,author,publisher,amount)){
                            JOptionPane.showMessageDialog(null, "录入成功");
                        }
                        else JOptionPane.showMessageDialog(null, "操作失败");
                    }else {
                        JOptionPane.showMessageDialog(null, "请补全购书信息");
                    }
                }else JOptionPane.showMessageDialog(null, "请补全购书信息");

            }
        });
    }
}