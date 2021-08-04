package Demo.ext;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MainViewCellRander extends DefaultTableCellRenderer {
    //每一行每一列显示之前都会调用
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(row%2==0){
            setBackground(Color.LIGHT_GRAY);
        }else {
            setBackground(Color.white);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
