package Demo.Handler;

import Demo.LoginView;
import Demo.MainView;
import Management.Service.Impl.UserServiceImpl;
import Management.Service.UserService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginHandler extends KeyAdapter implements ActionListener {
    private LoginView loginView;
    public LoginHandler(LoginView loginView) {
        this.loginView=loginView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text=jButton.getText();
        if("重置".equals(text)){
            loginView.getUserText().setText("");
            loginView.getPwdField().setText("");
        }else if("登录".equals(text)){
            login();
        }
    }

    private void login() {
        String user=loginView.getUserText().getText();
        char[] chars = loginView.getPwdField().getPassword();
        String pwd=new String(chars);
        //查询DB
        UserService userService=new UserServiceImpl();
        boolean flag=false;
        flag= userService.login(user,pwd);
        if(flag){
            new MainView(userService.getJurisdictionByUserName(user));
            loginView.dispose();
        }else{
            JOptionPane.showMessageDialog(loginView,"用户名密码错误");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER==e.getKeyCode()){
            login();
        }
    }
}
