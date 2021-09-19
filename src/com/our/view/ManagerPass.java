package com.our.view;
import com.our.bean.BorrowBean;

import com.our.dao.BookDao;
import com.our.service.PassService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class ManagerPass extends JFrame {

    private JTable table;
    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField cTextField;
    private Object [][]data=null;
    private DefaultTableModel defaultModel = null;
    private String columnNames[]=null;
    private PassService pass=new PassService();
    private String Bid=null;

    public ManagerPass() {
        super();

        setVisible(true);
        setTitle("审核预约");
        setBounds(550, 300, 800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Toolkit tk=Toolkit.getDefaultToolkit();
        Image im=tk.createImage("image/sh.png");
        setIconImage(im);

        columnNames = new String[]{"ID","用户id","书籍名称","书籍id"};   //列名
        Object[][] Data = queryData();
        defaultModel = new DefaultTableModel(Data, columnNames);
        table = new JTable(defaultModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(table);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  //单选
        table.addMouseListener(new MouseAdapter() {    //鼠标事件
            public void mouseClicked(MouseEvent e) {
                int selectedRow = table.getSelectedRow(); //获得选中行索引
                Object oa = defaultModel.getValueAt(selectedRow, 0);
                Object ob = defaultModel.getValueAt(selectedRow, 1);
                Object oc = defaultModel.getValueAt(selectedRow, 2);
                Object od = defaultModel.getValueAt(selectedRow, 3);
                Bid=od.toString();
                aTextField.setText(oa.toString());  //给文本框赋值
                bTextField.setText(ob.toString());
                cTextField.setText(oc.toString());
            }
        });
        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.add(new JLabel("ID: "));
        aTextField = new JTextField("", 10);
        panel.add(aTextField);
        panel.add(new JLabel("用户id: "));
        bTextField = new JTextField("", 10);
        panel.add(bTextField);
        panel.add(new JLabel("书籍名称"));
        cTextField = new JTextField("",10);
        panel.add(cTextField);
        JButton Button1 = new JButton("通过");
        panel.add(Button1);
        JButton Button2 = new JButton("未通过");
        panel.add(Button2);
        Button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(pass.doPass(aTextField.getText())){
                    int selectedRow = table.getSelectedRow();
                    defaultModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "已通过！");
                }
            }
        });
        Button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int bid=Integer.parseInt(Bid);
                if(pass.doNotPass(aTextField.getText(),bid)){
                    int selectedRow = table.getSelectedRow();
                    defaultModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "已驳回！");
                }
            }
        });
    }
    public Object[][] queryData(){

        List<BorrowBean> list=pass.getPassList();
        data=new Object[list.size()][columnNames.length];

        for(int i=0;i<list.size();i++){
            for(int j=0;j<columnNames.length;j++){
                data[i][0]=list.get(i).getId();
                data[i][1]=list.get(i).getUid();
                try {
                    data[i][2]= BookDao.getBookTitle(Integer.parseInt(list.get(i).getBid()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                data[i][3]=list.get(i).getBid();
            }
        }
        return data;
    }

}
