package ui;

import bean.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class LoginFrameUI extends JFrame implements ActionListener {
    // 创建组件,方便以后调用
    private JTextField loginNameField; // 用户名
    private JPasswordField passwordField;// 密码
    private JButton loginButton, registerButton;


    // 定义一个静态集合，存储系统中全部的用户信息
    private static ArrayList<User> allUser = new ArrayList<>();
    static{
        allUser.add(new User("管理员", "123456", "admin"));
        allUser.add(new User("周杰伦", "jc", "JayChou"));
        allUser.add(new User("林俊杰", "jj", "JJLin"));

    }

    public LoginFrameUI(){
        // 创建主窗口
        super("登陆界面");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        createAndShowGUI();
    }
    private void createAndShowGUI(){

        // 创建面板
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240));

        // 设置字体和颜色
        Font customFont = new Font("微软雅黑", Font.BOLD, 18);
        Color primaryColor = new Color(66, 135, 245);
        Color secondaryColor = new Color(204, 204, 204);// 更浅的颜色用于注册按钮

        //  标题
        JLabel titleLabel = new JLabel("人事管理系统Demo");
        titleLabel.setBounds(50, 30, 300, 30);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        panel.add(titleLabel);

        // 用户名标签
        JLabel usernameLabel = new JLabel("用户名:");
        usernameLabel.setBounds(50, 100, 150, 30);
        usernameLabel.setFont(customFont);
        panel.add(usernameLabel);

        // 用户输入框
        loginNameField = new JTextField();
        loginNameField.setBounds(160, 100, 190, 30);
        panel.add(loginNameField);

        //  密码标签
        JLabel passwordLabel = new JLabel("密    码:");
        passwordLabel.setBounds(50, 150, 150,30);
        passwordLabel.setFont(customFont);
        panel.add(passwordLabel);

        // 密码输入框
        passwordField = new JPasswordField();
        passwordField.setBounds(160, 150, 190, 30);
        passwordField.setFont(customFont);
        passwordField.setEchoChar('*');
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    login();
                }
            }
        });
        panel.add(passwordField);

        // 登录按钮
        loginButton = new JButton("登录");
        loginButton.setBounds(50, 200, 150, 30);
        loginButton.setFont(customFont);
        loginButton.setBackground(primaryColor);
        loginButton.setForeground(Color.white);

        panel.add(loginButton);
        loginButton.addActionListener(this);

        // 注册按钮
        registerButton = new JButton("注册");
        registerButton.setBounds(200, 200, 150, 30);
        registerButton.setFont(customFont);
        registerButton.setBackground(Color.WHITE);
        registerButton.setForeground(Color.BLACK);
        panel.add(registerButton);
        registerButton.addActionListener(this);

        // 监听键盘，按回车登录


        // 添加面板到窗口
        this.add(panel);
        this.setVisible(true);

    }

    // 为整个类绑定点击事件监听器
    @Override
    public void actionPerformed(ActionEvent e) {
        // 要判断是哪个事件
        // 转化，默认是object类型
        JButton btn = (JButton) e.getSource();

        if (btn == loginButton){
            // 独立功能独立成方法
            login();

        }else if (btn == registerButton){
            // 先不写
            System.out.println("点击的是注册");
        }

    }



    private void login(){

        // 获取登录名和密码
        String loginName = loginNameField.getText();
        String password = new String(passwordField.getPassword());
        // 遍历集合，若其一不对则返回错误
        User user = loginNameMatch(loginName);
        if (user != null){
            if (password.equals(user.getPassword())){
                JOptionPane.showMessageDialog(this, "登录成功");
                new EmployeeManagerUI(user);
                this.dispose();
            }else {
                JOptionPane.showMessageDialog(this, "账号或密码错误");
            }
        }else {
            JOptionPane.showMessageDialog(this, "账号或密码错误");
        }

    }

    private User loginNameMatch(String loginName){

        for (int i = 0; i < allUser.size(); i++){
            if (loginName.equals(allUser.get(i).getLoginName())){

                return allUser.get(i);
            }
        }

        return null;
    }

}
