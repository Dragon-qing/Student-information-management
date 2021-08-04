package Demo;

import Demo.Handler.UpdateStudentViewHandler;
import Demo.entity.StudentDO;
import Management.Pojo.Student;
import Management.Service.Impl.StudentServiceImpl;
import Management.Service.StudentService;

import javax.swing.*;
import java.awt.*;

public class UpdateStudentView extends JDialog {
    JPanel jPanel=new JPanel(new FlowLayout(FlowLayout.CENTER,10,20));

    JLabel nameLabel =new JLabel("姓名:",JLabel.RIGHT);
    JLabel idLabel=new JLabel("学号:",JLabel.RIGHT);
    JLabel sexLabel=new JLabel("性别:",JLabel.RIGHT);
    JLabel schoolLabel=new JLabel("学院:",JLabel.RIGHT);
    JLabel noLabel=new JLabel("修改学生学号:",JLabel.RIGHT);
    JTextField noTxt=new JTextField();
    JTextField idTxt=new JTextField();
    JTextField nameTxt=new JTextField();
    JTextField sexTxt=new JTextField();
    JTextField schoolTxt=new JTextField();
    JButton updateBtn=new JButton("修改");
    UpdateStudentViewHandler updateStudentViewHandler;
    public UpdateStudentView(MainView mainView,String selectedStudentId){
        super(mainView,"修改学生信息",true);
        updateStudentViewHandler=new UpdateStudentViewHandler(this,mainView);
        StudentService studentService=new StudentServiceImpl();
        Student selectedStudent=studentService.queryById(selectedStudentId);
        noLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(noLabel);
        noTxt.setPreferredSize(new Dimension(200,30));
        noTxt.setEnabled(false);
        noTxt.setText(selectedStudent.getStudentId());
        jPanel.add(noTxt);

        idLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(idLabel);
        idTxt.setPreferredSize(new Dimension(200,30));
        idTxt.setText(selectedStudent.getStudentId());
        idTxt.setEnabled(false);
        jPanel.add(idTxt);

        nameLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(nameLabel);
        nameTxt.setPreferredSize(new Dimension(200,30));
        nameTxt.setText(selectedStudent.getStudentName());
        jPanel.add(nameTxt);

        sexLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(sexLabel);
        sexTxt.setPreferredSize(new Dimension(200,30));
        sexTxt.setText(selectedStudent.getStudentSex());
        jPanel.add(sexTxt);

        schoolLabel.setPreferredSize(new Dimension(80,30));
        jPanel.add(schoolLabel);
        schoolTxt.setPreferredSize(new Dimension(200,30));
        schoolTxt.setText(selectedStudent.getStudentSchool());
        jPanel.add(schoolTxt);

        updateBtn.addActionListener(updateStudentViewHandler);
        jPanel.add(updateBtn);

        Container contentPane = getContentPane();
        contentPane.add(jPanel);

        setSize(350,350);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        //销毁当前窗体
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    // 获取修改后的学生对象
    public StudentDO buildUpdatedStudentDO(){
        StudentDO studentDO=new StudentDO();
        studentDO.setId(idTxt.getText());
        studentDO.setName(nameTxt.getText());
        studentDO.setSex(sexTxt.getText());
        studentDO.setSchool(schoolTxt.getText());
        return studentDO;
    }
}
