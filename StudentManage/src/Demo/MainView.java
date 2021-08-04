package Demo;

import Demo.Handler.MainViewHandler;
import Demo.ext.MainViewTable;
import Demo.ext.MainViewTableModel;
import Management.Service.Impl.StudentServiceImpl;
import Management.Service.StudentService;
import Management.req.StudentRequest;
import Management.res.TableDTO;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Vector;

public class MainView extends JFrame {
    JPanel northPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn=new JButton("增加");
    JButton updateBtn=new JButton("修改");
    JButton delBtn=new JButton("删除");
    JTextField searchTxt=new JTextField(15);
    JButton searchBtn=new JButton("查询");
    JPanel southPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn=new JButton("上一页");
    JButton nextBtn=new JButton("下一页");
    MainViewTable mainViewTable=new MainViewTable();
    private int jdg=1;              //权限
    private int pageNow=1;              //当前页数
    private int pageSize=10;         //一页显示多少记录
    MainViewHandler mainViewHandler;
    public MainView(){ this(1);}
    public MainView(int jdg) {
        super("主界面——学生信息管理系统");
        this.jdg=jdg;
        Rectangle bonds = DimensionUtil.getBonds();

        pageSize=Math.floorDiv(bonds.height,35);  //根据屏幕动态计算行数
        Container contentPane = getContentPane();
        mainViewHandler=new MainViewHandler(this);
        //北部组件
        layoutNorth(contentPane);
        //设置中间的Jtable
        layoutCenter(contentPane);
        //南边的组件
        layoutSouth(contentPane);
        //设置图标
        URL imgUrl = MainView.class.getClassLoader().getResource("resources\\pkq.jpeg");
        setIconImage(new ImageIcon(imgUrl).getImage());

        //根据屏幕大小设置界面大小
        setBounds(bonds);
        //充满整个屏幕
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void layoutCenter(Container contentPane) {
        TableDTO tableDTO = getTableDTO();
        Vector<Vector<Object>> data = tableDTO.getData();
        MainViewTableModel mainViewTableModel=MainViewTableModel.assembleModel(data);
        mainViewTable.setModel(mainViewTableModel);
        mainViewTable.renderRule();
        JScrollPane jScrollPane=new JScrollPane(mainViewTable);
        contentPane.add(jScrollPane,BorderLayout.CENTER);
        showPreNext(tableDTO.getTotalCount());
    }

    private TableDTO getTableDTO() {
        StudentService studentService = new StudentServiceImpl();
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setPageNow(pageNow);
        studentRequest.setPageSize(pageSize);
        studentRequest.setSearchKey(searchTxt.getText().trim());
        return studentService.retrieveStudents(studentRequest);
    }

    private void layoutSouth(Container contentPane) {
        preBtn.addActionListener(mainViewHandler);
        nextBtn.addActionListener(mainViewHandler);
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }
    //上一页下一页判断
    private void showPreNext(int totalCount){
        if(pageNow==1){
            preBtn.setVisible(false);
        }else {
            preBtn.setVisible(true);
        }
        int pageCount=0;//总页数
        if(totalCount%pageSize==0){
            pageCount=totalCount/pageSize;
        }else{
            pageCount=totalCount/pageSize+1;
        }
        if(pageNow==pageCount){
            nextBtn.setVisible(false);
        }else {
            nextBtn.setVisible(true);
        }
    }

    private void layoutNorth(Container contentPane) {
        //增加事件监听
        addBtn.addActionListener(mainViewHandler);
        updateBtn.addActionListener(mainViewHandler);
        delBtn.addActionListener(mainViewHandler);
        searchBtn.addActionListener(mainViewHandler);
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(delBtn);
        northPanel.add(searchTxt);
        northPanel.add(searchBtn);
        if(jdg==0){
            updateBtn.setVisible(false);
            delBtn.setVisible(false);
            addBtn.setVisible(false);
        }
        contentPane.add(northPanel,BorderLayout.NORTH);
    }

    public void setPageNow(int pageNow) {
        this.pageNow = pageNow;
    }

    public int getPageNow() {
        return pageNow;
    }


    public void reloadTable(){
        TableDTO tableDTO = getTableDTO();
        Vector<Vector<Object>> data = tableDTO.getData();
        MainViewTableModel.updateModel(data);
        mainViewTable.renderRule();
        showPreNext(tableDTO.getTotalCount());
    }
    public String[] getSelectedStudentIds(){
       int [] selectedRows=mainViewTable.getSelectedRows();
       String [] ids=new String[selectedRows.length];
       for(int i=0;i<selectedRows.length;i++){
           int rowIndex=selectedRows[i];
           Object idObj=mainViewTable.getValueAt(rowIndex,0);
           ids[i]=idObj.toString();
       }
       return ids;
    }
    public static void main(String[] args) {
        new MainView();
    }
}
