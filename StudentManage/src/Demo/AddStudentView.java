package Demo;

import Demo.Handler.AddStudentViewHandler;
import Demo.entity.StudentDO;

import javax.swing.*;
import java.awt.*;

public class AddStudentView extends JDialog {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));
    JLabel nameLabel =new JLabel("姓名:",JLabel.RIGHT);
    JLabel idLabel=new JLabel("学号:",JLabel.RIGHT);
    JLabel sexLabel=new JLabel("性别:",JLabel.RIGHT);
    JLabel schoolLabel=new JLabel("学院:",JLabel.RIGHT);
    JTextField idTxt=new JTextField();
    JTextField nameTxt=new JTextField();
    JTextField sexTxt=new JTextField();
    JTextField schoolTxt=new JTextField();
    JButton addBtn=new JButton("添加");

    AddStudentViewHandler addStudentViewHandler;
    public AddStudentView(MainView mainView){
        super(mainView,"添加学生",true);
        addStudentViewHandler=new AddStudentViewHandler(this,mainView);
        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(idTxt);

        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(nameTxt);

        sexLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sexLabel);
        sexTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(sexTxt);

        schoolLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(schoolLabel);
        schoolTxt.setPreferredSize(new Dimension(200,30));
        jPanel.add(schoolTxt);

        addBtn.addActionListener(addStudentViewHandler);
        jPanel.add(addBtn);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);

        setSize(350,330);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        //销毁当前窗体
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    public StudentDO buildStudentDO(){
        StudentDO studentDO=new StudentDO();
        studentDO.setId(idTxt.getText());
        studentDO.setName(nameTxt.getText());
        studentDO.setSex(sexTxt.getText());
        studentDO.setSchool(schoolTxt.getText());
        return studentDO;
    }
}
