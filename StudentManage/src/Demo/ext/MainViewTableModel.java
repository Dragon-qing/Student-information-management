package Demo.ext;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class MainViewTableModel extends DefaultTableModel {
    static Vector<String> columns=new Vector<>();
    static{
        columns.addElement("学号");
        columns.addElement("姓名");
        columns.addElement("性别");
        columns.addElement("学院");
    }
    private MainViewTableModel(){
        super(null,columns);
    }
    
    private static MainViewTableModel mainViewTableModel=new MainViewTableModel();
    public static MainViewTableModel assembleModel(Vector<Vector<Object>>data){
        mainViewTableModel.setDataVector(data,columns);
        return mainViewTableModel;
    }
    public static void updateModel(Vector<Vector<Object>>data){
        mainViewTableModel.setDataVector(data,columns);
    }
    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
