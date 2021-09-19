package com.our.view;

import com.our.bean.BorrowBean;
import com.our.dao.BookDao;
import com.our.service.GetHistoryService;
import com.our.service.RbService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class UserInfo extends JFrame{
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private String uid=null;
    private Object [][]data=null;
    private DefaultTableModel defaultModel = null;
    private String columnNames[]=null;
    private JTable table;

    public UserInfo(String a) {

        uid=a;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
        setBounds(550, 200, 800, 600);
        setTitle("个人信息");

        Toolkit tk=Toolkit.getDefaultToolkit();
        Image im=tk.createImage("image/user.png");
        setIconImage(im);

        JPanel jp1=new JPanel();
        add(jp1, BorderLayout.NORTH);
        jp1.setBorder(BorderFactory.createTitledBorder("基本信息"));
        JLabel lblUid = new JLabel("用户id：");
        jp1.add(lblUid);

        textField = new JTextField();
        textField.setColumns(10);
        textField.setText(uid);
        jp1.add(textField);

        JLabel lblrb = new JLabel("rb余额：");
        jp1.add(lblrb);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        RbService rbService = new RbService(uid);
        textField_1.setText(String.valueOf(rbService.getBalance()));
        jp1.add(textField_1);

        JPanel jp2=new JPanel();
        add(jp2, BorderLayout.CENTER);
        jp2.setLayout(null);
        jp2.setBorder(BorderFactory.createTitledBorder("借阅记录"));

        columnNames = new String[]{"ID","书籍名称","书籍id", "借阅日期", "归还日期", "花费"};   //列名
        Object[][] Data = queryData();
        defaultModel = new DefaultTableModel(Data, columnNames);

        table = new JTable(defaultModel);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setViewportView(table);
        scrollPane.setBounds(130,30,520,420);
        jp2.add(scrollPane);

        JButton fanhui =new JButton("返回");
        fanhui.setBounds(670,520,60,30);
        jp2.add(fanhui);
    }

    public Object[][] queryData(){

        GetHistoryService getHistory=new GetHistoryService(uid);
        List<BorrowBean> list=getHistory.getBorrowHistory();
        data=new Object[list.size()][columnNames.length];

        for(int i=0;i<list.size();i++){
            for(int j=0;j<columnNames.length;j++){
                data[i][0]=list.get(i).getId();
                try {
                    data[i][1]= BookDao.getBookTitle(Integer.parseInt(list.get(i).getBid()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                data[i][2]=list.get(i).getBid();
                data[i][3]=list.get(i).getB_date();
                data[i][4]=list.get(i).getR_date();
                data[i][5]=list.get(i).getC_rb();
            }
        }
        return data;
    }

}
