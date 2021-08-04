package Demo.Handler;

import Demo.AddStudentView;
import Demo.MainView;
import Demo.entity.StudentDO;
import Management.Pojo.Student;
import Management.Service.Impl.StudentServiceImpl;
import Management.Service.StudentService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentViewHandler implements ActionListener {
    private AddStudentView addStudentView;
    private MainView mainView;
    public AddStudentViewHandler(AddStudentView addStudentView,MainView mainView){this.addStudentView=addStudentView;this.mainView=mainView;}
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton) e.getSource();
        String text=jButton.getText();
        if("添加".equals(text)){
            StudentDO studentDO=addStudentView.buildStudentDO();
            Student student=new Student(studentDO.getId(),studentDO.getName(), studentDO.getSex(), studentDO.getSchool());
            StudentService studentService=new StudentServiceImpl();
            boolean addResult=studentService.save(student);
            if(addResult){
                //重新加载数据
                mainView.reloadTable();
                addStudentView.dispose();
            }else{
                JOptionPane.showMessageDialog(addStudentView,"添加失败");
            }
        }
    }
}
