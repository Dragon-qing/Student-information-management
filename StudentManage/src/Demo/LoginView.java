package Demo;

import Demo.Handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class LoginView extends JFrame {
    JLabel nameLabel=new JLabel("学生信息管理系统",JLabel.CENTER);
    SpringLayout springLayout=new SpringLayout();
    JPanel centerPanel=new JPanel(springLayout);
    JLabel userNameLabel=new JLabel("用户名:");
    JLabel pwdLabel=new JLabel("密码:");
    JTextField userText=new JTextField();
    JPasswordField pwdField=new JPasswordField();
    JButton loginBtn=new JButton("登录");
    JButton resetBtn=new JButton("重置");

    TrayIcon trayIcon;
    SystemTray systemTray;
    LoginHandler loginHandler;
    public LoginView() {
        super("学生信息管理系统");
        loginHandler=new LoginHandler(this);
        Container contentPane = getContentPane();
        nameLabel.setFont(new Font("华文行楷",Font.PLAIN,40));
        nameLabel.setPreferredSize(new Dimension(0,80));
        Font centerFont=new Font("楷体",Font.PLAIN,20);
        userNameLabel.setFont(centerFont);
        userText.setPreferredSize(new Dimension(200,30));
        pwdLabel.setFont(centerFont);
        pwdField.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(centerFont);
        resetBtn.setFont(centerFont);
        //组件加入面板
        centerPanel.add(userNameLabel);
        centerPanel.add(userText);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdField);
        loginBtn.addActionListener(loginHandler);
        loginBtn.addKeyListener(loginHandler);
        centerPanel.add(loginBtn);
        resetBtn.addActionListener(loginHandler);
        centerPanel.add(resetBtn);
        //弹簧布局
        layoutCenter();
        //边界布局
        contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel,BorderLayout.CENTER);
        //设置默认按钮
        getRootPane().setDefaultButton(loginBtn);
        //设置图标
        URL imgUrl = LoginView.class.getClassLoader().getResource("resources\\pkq.jpeg");
        setIconImage(new ImageIcon(imgUrl).getImage());
        if(SystemTray.isSupported()){
            systemTray=SystemTray.getSystemTray();
            trayIcon=new TrayIcon(new ImageIcon(imgUrl).getImage());
            trayIcon.setImageAutoSize(true);
            try {
                systemTray.add(trayIcon);
            } catch (AWTException e) {
                e.printStackTrace();
            }
            //增加最小化时销毁资源
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowIconified(WindowEvent e) {
                   LoginView.this.dispose();
                }
            });
            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int clickCount=e.getClickCount();
                    if(clickCount==1){
                        LoginView.this.setExtendedState(JFrame.NORMAL);
                    }
                    LoginView.this.setVisible(true);
                }
            });
        }

        setSize(600,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void layoutCenter() {
        //userNameLabel
        Spring childWith = Spring.sum(Spring.sum(Spring.width(userNameLabel), Spring.width(userText)), Spring.constant(20));
        int offsetX = childWith.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST,userNameLabel,-offsetX,
                SpringLayout.HORIZONTAL_CENTER,centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH,userNameLabel,20,SpringLayout.NORTH,centerPanel);
        //userTxt
        springLayout.putConstraint(SpringLayout.WEST,userText,20,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userText,0,SpringLayout.NORTH,userNameLabel);
        //password
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,20,SpringLayout.SOUTH,userNameLabel);
        springLayout.putConstraint(SpringLayout.EAST,pwdField,0,SpringLayout.EAST,userText);
        springLayout.putConstraint(SpringLayout.NORTH,pwdField,0,SpringLayout.NORTH,pwdLabel);
        //loginBtn
        springLayout.putConstraint(SpringLayout.WEST,loginBtn,50,SpringLayout.WEST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,loginBtn,20,SpringLayout.SOUTH,pwdLabel);
        //resetBtn
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,50,SpringLayout.EAST,loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,0,SpringLayout.NORTH,loginBtn);
    }

    public JTextField getUserText() {
        return userText;
    }


    public JPasswordField getPwdField() {
        return pwdField;
    }

//    public static void main(String[] args) {
//        new LoginView();
//    }
}
