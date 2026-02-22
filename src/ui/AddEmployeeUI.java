package ui;

import bean.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployeeUI extends JFrame implements ActionListener {
    private JTextField txtID, txtName, txtPosition, txtDepartment;
    private JLabel lblID, lblName, lblPosition, lblDepartment, lblHireData;
    private JFormattedTextField txtHireData;
    private JButton btnSave, btnCancel;
    private EmployeeManagerUI employeeManagerUI;
    private int oderNum = 0;

    public AddEmployeeUI(){
        super("添加员工信息");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10,10,10,10);// 内边距

        // 设置字体大小
        Font labelFont = new Font("微软雅黑", Font.BOLD, 14);

        // 标签和文本框
        // ID
        /**
        gbc.gridx = 0;
        gbc.gridy = oderNum;
        gbc.anchor = GridBagConstraints.EAST;
        lblID = new JLabel("ID:");
        lblID.setFont(labelFont);
        add(lblID, gbc);
        txtID = new JTextField(10);
        gbc.gridx = 1;
        add(txtID, gbc);*/

        // 姓名
        gbc.gridx = 0;
        gbc.gridy = ++oderNum;
        gbc.anchor = GridBagConstraints.EAST;
        lblName = new JLabel("姓名:");
        txtName = new JTextField(10);
        lblName.setFont(labelFont);
        add(lblName,gbc);
        gbc.gridx = 1;
        add(txtName, gbc);

        // 职位
        gbc.gridx = 0;
        gbc.gridy = ++oderNum;
        gbc.anchor = GridBagConstraints.EAST;
        lblPosition = new JLabel("职位:");
        txtPosition = new JTextField(10);
        lblPosition.setFont(labelFont);
        add(lblPosition, gbc);
        gbc.gridx = 1;
        add(txtPosition, gbc);

        // 部门
        gbc.gridx = 0;
        gbc.gridy = ++oderNum;
        gbc.anchor = GridBagConstraints.EAST;
        lblDepartment = new JLabel("部门:");
        txtDepartment = new JTextField(10);
        lblDepartment.setFont(labelFont);
        add(lblDepartment, gbc);
        gbc.gridx = 1;
        add(txtDepartment, gbc);

        // 添加和取消按钮
        gbc.gridx = 0;
        gbc.gridy = ++oderNum;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        btnCancel = new JButton("取消");
        btnSave = new JButton("添加");
        btnSave.setPreferredSize(new Dimension(100, 30));
        btnCancel.setPreferredSize(new Dimension(100,30));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnSave);
        add(buttonPanel, gbc);

        // 给添加按钮添加点击事件(限定符用法，传统接口实现方式)
        btnSave.addActionListener(this);
        // 给删除按钮添加点击事件(lambda用法：方法引用)
        btnCancel.addActionListener(this::actionPerformed);



        // 设置窗口属性
        pack();// 自动调整窗口大小以适应组件
        setSize(300,300);// 自适应之后再调整窗口大小
        setLocationRelativeTo(null);// 设置窗口居中
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    public AddEmployeeUI(EmployeeManagerUI employeeManagerUI){
        this();
        this.employeeManagerUI = employeeManagerUI;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        JButton btn = (JButton) e.getSource();
        if (btn == btnSave){// 添加
//            System.out.println("点击的是添加");
//            String idStr = txtID.getText();
//            int idOder = e.size();
            String nameStr = txtName.getText();
            String positionStr = txtPosition.getText();
            String departmentStr = txtDepartment.getText();

            // 创建员工对象并传入数据
            Employee emp = new Employee();
//            emp.setId(idStr);
            emp.setName(nameStr);
            emp.setPosition(positionStr);
            emp.setDepartment(departmentStr);
            employeeManagerUI.addEmployeeToTable(emp);

            this.dispose();
        }else {// 取消
//            System.out.println("点击的是取消");
            this.dispose();
        }
    }


    // 测试
//    public static void main(String[] args) {
//        AddEmployeeUI a = new AddEmployeeUI();
//        a.setVisible(true);
//    }
}
