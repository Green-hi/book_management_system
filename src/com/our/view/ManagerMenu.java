package com.our.view;

import com.our.bean.BookBean;
import com.our.dao.BookDao;
import com.our.service.BookService;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ManagerMenu extends JFrame implements ActionListener{
    private Object [][]data=null;
    private DefaultTableModel defaultModel = null;
    private String columnNames[]=null;
    private JTable table;
    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField cTextField;
    private int bid=0,amount=0;


    JMenuBar menubar = new JMenuBar ();
    JMenu meArray[] = { new JMenu("用户管理"),
            new JMenu("书籍管理") };
    JMenuItem item[] = {new JMenuItem("账户管理",new ImageIcon("image/yhgl.png")),
            new JMenuItem("R币充值",new ImageIcon("image/rbcz.png")),
            new JMenuItem("捐赠与购买",new ImageIcon("image/tj.png")),
            new JMenuItem("书籍购买"),
            new JMenuItem("书库总览"),
            new JMenuItem("预约审核",new ImageIcon("image/sh.png")),
            new JMenuItem("书籍归还",new ImageIcon("image/gh.png")),
    };
    CardLayout card=new CardLayout();
    JPanel cardPanel=new JPanel();
    JButton bt1 = new JButton("修改");
    JButton bt2 = new JButton("删除");
    public ManagerMenu() {

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
        meArray[0].add(item[0]);
        meArray[0].add(item[1]);
        meArray[1].add(item[2]);
        //meArray[1].add(item[3]);
        //meArray[1].add(item[4]);
        meArray[1].add(item[5]);
        meArray[1].add(item[6]);
        for (int i = 0; i < item.length; i++) {
            item[i].addActionListener(this);
        }

        for (int i = 0; i < meArray.length; i++) {
            menubar.add(meArray[i]);
        }
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        setJMenuBar(menubar);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("删改操作"));
        add(panel, BorderLayout.SOUTH);
        panel.add(new JLabel("书名: "));
        aTextField = new JTextField("", 10);
        panel.add(aTextField);
        panel.add(new JLabel("作者: "));
        bTextField = new JTextField("", 10);
        panel.add(bTextField);
        panel.add(new JLabel("出版社"));
        cTextField = new JTextField("",10);
        panel.add(cTextField);


        panel.add(bt2);
        panel.add(bt1);

        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createTitledBorder("书库总览"));
        add(panel2, BorderLayout.CENTER);

        columnNames = new String[]{"书籍id", "书名", "作者", "出版社", "剩余数量"};   //列名
        Object[][] Data=queryData();
        defaultModel = new DefaultTableModel(Data,columnNames);
        table = new JTable(defaultModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setViewportView(table);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        table.addMouseListener(new MouseAdapter() {    //鼠标事件
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow(); //获得选中行索引
                Object oa = defaultModel.getValueAt(selectedRow, 0);
                Object ob = defaultModel.getValueAt(selectedRow, 1);
                Object oc = defaultModel.getValueAt(selectedRow, 2);
                Object od = defaultModel.getValueAt(selectedRow, 3);
                Object oe = defaultModel.getValueAt(selectedRow, 4);
                bid=oa.hashCode();
                amount=oe.hashCode();
                aTextField.setText(ob.toString());
                bTextField.setText(oc.toString());
                cTextField.setText(od.toString());
            }
        });
        scrollPane.setBounds(100,30,600,400);
        panel2.setLayout(null);
        panel2.add(scrollPane);

    }
    public void actionPerformed(ActionEvent e){
        try {

            if (e.getSource() == item[1]) { //RB充值
                new  RbRecharge();
            }
            if (e.getSource() == item[5]) {
                new ManagerPass();
            }
            if (e.getSource() == item[6]) {
                new BooksReturn();
            }
            if (e.getSource() == item[0]) {
                new UserManagement();
            }
            if (e.getSource() == item[2]) {
                new AddBooks();
            }
            if(e.getSource()==bt1){
                int selectedRow = table.getSelectedRow();
                if(selectedRow!=-1){
                    BookService bookService=new BookService();
                    if(bookService.update(bid,aTextField.getText(),bTextField.getText(),cTextField.getText())){
                        JOptionPane.showMessageDialog(null, "信息已修改！");
                        defaultModel.setValueAt(aTextField.getText(), selectedRow, 1);
                        defaultModel.setValueAt(bTextField.getText(), selectedRow, 2);
                        defaultModel.setValueAt(cTextField.getText(), selectedRow, 3);
                    }
                }
            }
            if(e.getSource()==bt2){
                int selectedRow = table.getSelectedRow();
                if(selectedRow!=-1){
                    BookService bookService=new BookService();
                    int n = JOptionPane.showConfirmDialog(null, "确定要删除该书籍吗?", "警告",JOptionPane.YES_NO_OPTION);
                    if(n==0){
                        if(bookService.delete(bid)){
                            JOptionPane.showMessageDialog(null, "成功删除！");
                            defaultModel.removeRow(selectedRow);
                        }
                        else JOptionPane.showMessageDialog(null, "删除失败！");
                    }
                }
            }

        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public Object[][] queryData(){
        BookDao b=new BookDao();
        List<BookBean> list= null;
        try {
            list = b.get_ListInfo();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        data=new Object[list.size()][columnNames.length];

        for(int i=0;i<list.size();i++){
            for(int j=0;j<columnNames.length;j++){
                data[i][0]=list.get(i).getBid();
                data[i][1]=list.get(i).getTitle();
                data[i][2]=list.get(i).getAuthor();
                data[i][3]=list.get(i).getPublisher();
                data[i][4]=list.get(i).getQuantity();
            }
        }
        return data;
    }

}

