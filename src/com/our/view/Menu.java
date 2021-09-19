package com.our.view;

import com.our.bean.BorrowBean;
import com.our.dao.BookDao;
import com.our.service.ReturnService;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class Menu extends JFrame implements ActionListener{
    private Object [][]data=null;
    private DefaultTableModel defaultModel = null;
    private String columnNames[]=null;
    private JTable table;
    private String uid=null;
    JMenuBar menubar = new JMenuBar ();
    JMenu meArray[] = { new JMenu("常用功能"),
            new JMenu("个人中心"),
            new JMenu("帮助") };
    JMenuItem item[] = { new JMenuItem("浏览图书"),
            new JMenuItem("书籍预约",new ImageIcon("image/query.jpg")),
            new JMenuItem("我的空间",new ImageIcon("image/user.jpg")),
            new JMenuItem("帮助信息",new ImageIcon("image/help.jpg")) };
    CardLayout card=new CardLayout();
    JPanel cardPanel=new JPanel();


    public Menu(String p1) {
        uid=p1;
        init();
        setLocation(550, 200);
        setVisible(true);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }
    public void init() {
        Toolkit tk=Toolkit.getDefaultToolkit();
        Image im=tk.createImage("image/1.ico");
        setIconImage(im);
        cardPanel.setLayout(card);
        setTitle("图书管理系统");
        meArray[0].add(item[1]);
        meArray[1].add(item[2]);
        meArray[2].add(item[3]);

        for (int i = 0; i < item.length; i++) {
            item[i].addActionListener(this);
        }

        for (int i = 0; i < meArray.length; i++) {
            menubar.add(meArray[i]);
        }
        setJMenuBar(menubar);

        JPanel mjp=new JPanel();
        mjp.setBorder(BorderFactory.createEtchedBorder());
        add(mjp);

        JPanel jp2=new JPanel();
        add(jp2, BorderLayout.CENTER);
        jp2.setBorder(BorderFactory.createTitledBorder("待归还书籍"));

        columnNames = new String[]{"ID","书籍id","书籍名称","借阅日期"};   //列名
        Object[][] Data = queryData();
        defaultModel = new DefaultTableModel(Data, columnNames);

        table = new JTable(defaultModel);
        JScrollPane scrollPane = new JScrollPane(table);
        //getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(table);
        scrollPane.setBounds(10,20,760,470);
        jp2.add(scrollPane);
    }
    public void actionPerformed(ActionEvent e){

            if (e.getSource() == item[1]) {//查找图书
                new BorrowServlet(uid);
            }
            if (e.getSource() == item[2]) {//个人信息
                new UserInfo(uid);
            }
    }
    public Object[][] queryData(){

        ReturnService returnService=new ReturnService();
        List<BorrowBean> list=returnService.getReturnList(uid);
        data=new Object[list.size()][columnNames.length];

        for(int i=0;i<list.size();i++){
            for(int j=0;j<columnNames.length;j++){
                data[i][0]=list.get(i).getId();
                data[i][1]=list.get(i).getBid();
                try {
                    data[i][2]= BookDao.getBookTitle(Integer.parseInt(list.get(i).getBid()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                data[i][3]=list.get(i).getB_date();
            }
        }
        return data;
    }

}