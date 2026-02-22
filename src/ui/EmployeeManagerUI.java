package ui;

import bean.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class EmployeeManagerUI extends JFrame{
    // 项目实战：管理界面
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;
    private JTextField nameTextFieldSearch;
    private User loginPeople;
    private int globalTotalID = 10;// 记录总人数，保证中间删除后id不重复，作为唯一标识
    // 创建存放数据
    private static ArrayList<Employee> employee = new ArrayList<>();


    public EmployeeManagerUI(){

        frame = this;
        initialize();
    }

    public EmployeeManagerUI(User loginPeople){
        this.loginPeople = loginPeople;
        frame = this;
        initialize();
    }

    // 获取在AddEmployee编辑过的对象
    public void addEmployeeToTable(Employee e){
        // 添加到表格model
//        int id = model.getRowCount() + 1;
        globalTotalID++;
        employee.add(e);
        model.addRow(new Object[]{
                globalTotalID,// 从这里获取员工序号的
                e.getName(),
                e.getPosition(),
                e.getDepartment()
        });
    }

    private void initialize(){
        this.setTitle("员工管理系统: " + loginPeople.getUserName());
        this.setBounds(100, 100, 800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());// getContentPane()是返回面板内容

        // 输入框和搜索按钮
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        nameTextFieldSearch = new JTextField(20);
        JButton btnSearch = new JButton("搜索");
        JButton btnAdd = new JButton("添加");
        btnSearch.setBackground(new Color(0xFFFFFFFF, true));
        btnAdd.setBackground(new Color(0xFFFFFFFF, true));
        topPanel.add(nameTextFieldSearch);
        topPanel.add(btnSearch);
        topPanel.add(btnAdd);

        // 创建表格
        model = new DefaultTableModel(
                new Object[][]{},// 数据源
                new String[]{"ID", "姓名", "职位", "部门"}// 表头
        ){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;// 设置单元格为不可编辑
            }
        };

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);// 数据在表格内过多时提供滚动条查看
        table.setRowHeight(30);

        // 没学过db，假设是原先存在的数据与添加数据到表格
        for (int i = 0; i < globalTotalID; i++){
            model.addRow(new Object[]{
                    i + 1, "员工" + (i + 1),"职员" + (i + 1),"部门" + (i +1)
            });
        }


        // 右键菜单
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem editItem = new JMenuItem("编辑");
        JMenuItem deleItem = new JMenuItem("删除");
        popupMenu.add(editItem);
        popupMenu.add(deleItem);
        table.addMouseListener(new MouseAdapter() {
            // 为 table 组件添加鼠标事件监听器
            @Override
            public void mousePressed(MouseEvent e) {
                // 检查是否为鼠标右键
                if (e.getButton() == MouseEvent.BUTTON3){
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0){
                        table.setRowSelectionInterval(row, row);
                        popupMenu.show(table, e.getX(), e.getY());
                    }

                }
            }
        });

        // 绑定事件到菜单
        editItem.addActionListener(e -> {
                int selectRow = table.getSelectedRow();

                if (selectRow >= 0){
                    int id = (Integer)model.getValueAt(selectRow, 0);// 假设id在第一行
                    JOptionPane.showMessageDialog(frame, "编辑ID:" + id);
                    // 剩下的可以添加编辑逻辑

                }
        });

        deleItem.addActionListener(e -> {
                int selectedRow = table.getSelectedRow();
                if(selectedRow >= 0){
                    int id = (Integer)model.getValueAt(selectedRow, 0);// 假设id在第一列
                    JOptionPane.showMessageDialog(frame, "删除ID:" + id);
                    // 剩下的可以添加删除逻辑
                    model.removeRow(selectedRow);
                }
        });

        // 搜索按钮监听器
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在此可添加新员工的逻辑
            }
        });

        // 添加按钮监听器
        btnAdd.addActionListener(e -> new AddEmployeeUI(this));
//        btnAdd.addActionListener(new AddEmployeeUI(this));

        this.getContentPane().add(topPanel, BorderLayout.NORTH);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        this.setVisible(true);

    }

}
