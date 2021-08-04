package Demo.Handler;

import Demo.AddStudentView;
import Demo.MainView;
import Demo.UpdateStudentView;
import Management.Service.Impl.StudentServiceImpl;
import Management.Service.Impl.UserServiceImpl;
import Management.Service.StudentService;
import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainViewHandler extends KeyAdapter implements ActionListener {
    private MainView mainView;
    public MainViewHandler(MainView mainView){this.mainView=mainView;}
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String text=jButton.getText();
        if("增加".equals(text)){
            new AddStudentView(mainView);
        }else if("修改".equals(text)){
            String[] selectedStudentIds = mainView.getSelectedStudentIds();
            if(selectedStudentIds.length!=1){
                JOptionPane.showMessageDialog(mainView,"一次只能修改一个");
                return;
            }
            new UpdateStudentView(mainView,selectedStudentIds[0]);
        }else if("删除".equals(text)){
            String[] selectedStudentIds = mainView.getSelectedStudentIds();
            if(selectedStudentIds.length==0){
                JOptionPane.showMessageDialog(mainView,"请选择要删除的行");
                return;
            }
            int option=JOptionPane.showConfirmDialog(mainView,"你确认要删除选择的"+
            +selectedStudentIds.length+"行吗?","确认删除",JOptionPane.YES_NO_OPTION);
            if(option==JOptionPane.YES_NO_OPTION){
                //执行删除
                StudentService studentService=new StudentServiceImpl();
                boolean deleteResult=studentService.delete(selectedStudentIds);
                if(deleteResult){
                    //重新加载数据
                    mainView.reloadTable();
                }else{
                    JOptionPane.showMessageDialog(mainView,"删除失败");
                }
            }
        }else if("查询".equals(text)){
           mainView.setPageNow(1);
           mainView. reloadTable();
        }else if("上一页".equals(text)){
           mainView.setPageNow(mainView.getPageNow()-1);
            mainView.reloadTable();
        }else if("下一页".equals(text)){
            mainView.setPageNow(mainView.getPageNow()+1);
            mainView.reloadTable();
        }
    }

}
