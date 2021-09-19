package com.our.view;


//以用户id为参数查找用户的借书记录，并创建新界面显示
import com.our.bean.BorrowBean;
import com.our.service.GetHistoryService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class UserBorrowHistory extends JFrame {

    private JTable table;

    private Object [][]data=null;
    private DefaultTableModel defaultModel = null;
    private String columnNames[]=null;
    //private BorrowDao retrival=new BorrowDao();


    String uid=null;

    public UserBorrowHistory(String a) {
        super();
        uid = a;
        setVisible(true);

        setTitle("个人借阅历史");
        setBounds(100, 100, 1000, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        columnNames = new String[]{"ID","书籍id", "借阅日期", "归还日期", "花费"};   //列名
        Object[][] Data = queryData();
        defaultModel = new DefaultTableModel(Data, columnNames);


        table = new JTable(defaultModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);


        scrollPane.setViewportView(table);
        final JPanel panel = new JPanel();
    }
        public Object[][] queryData(){

            GetHistoryService getHistory=new GetHistoryService(uid);
            List<BorrowBean> list=getHistory.getBorrowHistory();
            data=new Object[list.size()][columnNames.length];

            for(int i=0;i<list.size();i++){
                for(int j=0;j<columnNames.length;j++){
                    data[i][0]=list.get(i).getId();
                    data[i][1]=list.get(i).getBid();
                    data[i][2]=list.get(i).getB_date();
                    data[i][3]=list.get(i).getR_date();
                    data[i][4]=list.get(i).getC_rb();
                }
            }
            return data;
        }

}