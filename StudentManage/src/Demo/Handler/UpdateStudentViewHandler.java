package Demo.Handler;

import Demo.AddStudentView;
import Demo.MainView;
import Demo.UpdateStudentView;
import Demo.entity.StudentDO;
import Management.Pojo.Student;
import Management.Service.Impl.StudentServiceImpl;
import Management.Service.StudentService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStudentViewHandler implements ActionListener {
    private UpdateStudentView updateStudentView;
    private MainView mainView;
    public UpdateStudentViewHandler(UpdateStudentView updateStudentView, MainView mainView){
        this.updateStudentView=updateStudentView;
        this.mainView=mainView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton=(JButton) e.getSource();
        String text=jButton.getText();
        if("修改".equals(text)){
            StudentDO studentDO=updateStudentView.buildUpdatedStudentDO();
            Student student=new Student(studentDO.getId(),studentDO.getName(), studentDO.getSex(), studentDO.getSchool());
            StudentService studentService=new StudentServiceImpl();
            boolean addResult=studentService.updateAll(student);
            if(addResult){
                //重新加载数据
                mainView.reloadTable();
                updateStudentView.dispose();
            }else{
                JOptionPane.showMessageDialog(updateStudentView,"修改失败");
            }
        }
    }
}
