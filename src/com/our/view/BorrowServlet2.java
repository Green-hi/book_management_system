package com.our.view;

import com.our.bean.BookBean;
import com.our.service.BorrowService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BorrowServlet2 extends JFrame {

    private JTable table;
    private JTextField aTextField;
    private JTextField bTextField;
    private JTextField cTextField;
    private Object [][]data=null;
    private DefaultTableModel defaultModel = null;
    private String columnNames[]=null;
    private String amount=null;

    String title=null,author=null,publisher=null;
    int bid=0;
    private String uid;

    public BorrowServlet2(String a, String b, String c, String d) {
        super();
        uid=d;
        setVisible(true);
        title=a;author=b;publisher=c;
        setTitle("查询预约");
        setBounds(550, 300, 800, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Toolkit tk=Toolkit.getDefaultToolkit();
        Image im=tk.createImage("image/query.jpg");
        setIconImage(im);

        columnNames = new String[]{"书籍id", "书名", "作者", "出版社", "剩余数量"};   //列名
        Object[][] Data=queryData(title,author,publisher);
        defaultModel = new DefaultTableModel(Data,columnNames);

        table = new JTable(defaultModel);
        JScrollPane scrollPane = new JScrollPane(table);
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
                amount=oe.toString();
                bid=od.hashCode();
                aTextField.setText(oa.toString());  //给文本框赋值
                bTextField.setText(ob.toString());
                cTextField.setText(oc.toString());
            }
        });
        scrollPane.setViewportView(table);
        final JPanel panel = new JPanel();
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
        JButton bt1 = new JButton("预约");
        panel.add(bt1);
        JButton bt2=new JButton("查询");
        panel.add(bt2);
        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BorrowService borrowService=new BorrowService(uid);
                if(bid==0)JOptionPane.showMessageDialog(null, "请选择一本书籍");
                else if(Integer.parseInt(amount)==0){
                    JOptionPane.showMessageDialog(null, "书籍暂时借完，可以联系管理员购买或者等待其他读者归还。");
                }
                else if(borrowService.order(bid)){
                    JOptionPane.showMessageDialog(null, "预约成功！请等候管理员通知。");
                }
                else {
                    JOptionPane.showMessageDialog(null, "预约失败！");
                }
            }
        });
        bt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new BorrowServlet2(aTextField.getText(),bTextField.getText(),cTextField.getText(),uid);
            }
        });
/*      //添加功能
        final JButton addButton = new JButton("添加");   //添加按钮
        addButton.addActionListener(new ActionListener() {//添加事件
            public void actionPerformed(ActionEvent e) {
                String[] rowValues = {aTextField.getText(), bTextField.getText()};
                defaultModel.addRow(rowValues);  //添加一行
                int rowCount = table.getRowCount() + 1;   //行数加上1
                aTextField.setText("A" + rowCount);
                bTextField.setText("B" + rowCount);
            }
        });
        panel.add(addButton);
*/
/*      //修改功能
        final JButton updateButton = new JButton("修改");   //修改按钮
        updateButton.addActionListener(new ActionListener() {//添加事件
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();//获得选中行的索引
                if (selectedRow != -1)   //是否存在选中行
                {

                    defaultModel.setValueAt(aTextField.getText(), selectedRow, 0);
                    defaultModel.setValueAt(bTextField.getText(), selectedRow, 1);
                    table.setValueAt(arg0, arg1, arg2)
                }
            }
        });
        panel.add(updateButton);
*/
/*      删除功能
        final JButton delButton = new JButton("删除");
        delButton.addActionListener(new ActionListener() {//添加事件
            public void actionPerformed(ActionEvent e) {
                System.out.println("e = [" + e.getActionCommand() + "]");
                int selectedRow = table.getSelectedRow();//获得选中行的索引
                if (selectedRow != -1)  //存在选中行
                {
                    defaultModel.removeRow(selectedRow);  //删除行
                }
            }
        });
        panel.add(delButton);
*/
    }
    public Object[][] queryData(String a,String b,String c){
        title=a;author=b;publisher=c;
        BorrowService borrowService=new BorrowService(uid);
        List<BookBean> list=borrowService.retrieval(title,author,publisher);
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
