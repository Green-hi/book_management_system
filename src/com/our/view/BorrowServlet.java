package com.our.view;


import com.our.bean.BookBean;
import com.our.dao.BookDao;
import com.our.service.BorrowService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class BorrowServlet extends JFrame {
    private String uid;
    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField cTextField;
    private Object [][]data=null;
    private DefaultTableModel defaultModel = null;
    private String columnNames[]=null;
    private JTable table;
    private int bid=0;
    private String amount=null;


    public BorrowServlet(String a){
        uid=a;
        setVisible(true);
        setTitle("查询预约");
        setBounds(550, 300, 800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Toolkit tk=Toolkit.getDefaultToolkit();
        Image im=tk.createImage("image/query.jpg");
        setIconImage(im);

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("书库总览"));
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.add(new JLabel("书名: "));
        aTextField = new JTextField("", 10);
        panel.add(aTextField);
        panel.add(new JLabel("作者: "));
        bTextField = new JTextField("", 10);
        panel.add(bTextField);
        panel.add(new JLabel("出版社"));
        cTextField = new JTextField("",10);
        panel.add(cTextField);
        JButton bt1 = new JButton("查询");
        JButton bt2 = new JButton("预约");
        panel.add(bt2);
        panel.add(bt1);


        columnNames = new String[]{"书籍id", "书名", "作者", "出版社", "剩余数量"};   //列名
        Object[][] Data=queryData();
        defaultModel = new DefaultTableModel(Data,columnNames);
        table = new JTable(defaultModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        table.addMouseListener(new MouseAdapter() {    //鼠标事件
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow(); //获得选中行索引
                Object oa = defaultModel.getValueAt(selectedRow, 1);
                Object ob = defaultModel.getValueAt(selectedRow, 2);
                Object oc = defaultModel.getValueAt(selectedRow, 3);
                Object od = defaultModel.getValueAt(selectedRow, 0);
                Object oe = defaultModel.getValueAt(selectedRow, 4);
                bid=od.hashCode();
                amount=oe.toString();
                aTextField.setText(oa.toString());
                bTextField.setText(ob.toString());
                cTextField.setText(oc.toString());
            }
        });

        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new BorrowServlet2(aTextField.getText(),bTextField.getText(),cTextField.getText(),uid);
            }
        });

        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BorrowService borrowService=new BorrowService(uid);
                if(bid==0)JOptionPane.showMessageDialog(null, "请选择一本书籍");
                else if(Integer.parseInt(amount)==0){
                    JOptionPane.showMessageDialog(null, "书籍暂时借完，可以联系管理员购买或者等待其他读者归还。");
                }
                else if(borrowService.order(bid)){
                    //int selectedRow = table.getSelectedRow();
                    //data[selectedRow][4]=data[selectedRow][4]-1;
                    JOptionPane.showMessageDialog(null, "预约成功！请等候管理员通知。");
                }
                else {
                    JOptionPane.showMessageDialog(null, "预约失败！");
                }
            }
        });
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
