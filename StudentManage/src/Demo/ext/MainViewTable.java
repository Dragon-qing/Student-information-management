package Demo.ext;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Vector;

public class MainViewTable extends JTable{
    public MainViewTable(){

        //设置表头
        JTableHeader tableHeader=getTableHeader();
        tableHeader.setFont(new Font(null,Font.BOLD,16));
        tableHeader.setForeground(Color.RED);
        //设置表格体
        setFont(new Font(null,Font.PLAIN,14));
        setForeground(Color.black);
        setGridColor(Color.black);
        setRowHeight(30);
        //设置多行选择
        getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    }
    public void renderRule(){
        //设置表格列的渲染
        Vector<String> columns = MainViewTableModel.getColumns();
        MainViewCellRander studentCellRender=new MainViewCellRander();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column=getColumn(columns.get(i));
            column.setCellRenderer(studentCellRender);
        }

    }
}
