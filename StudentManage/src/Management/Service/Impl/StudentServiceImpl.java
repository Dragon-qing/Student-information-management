package Management.Service.Impl;


import Management.Dao.Impl.StudentDaoImpl;
import Management.Dao.StudentDao;
import Management.Pojo.Student;
import Management.Service.StudentService;
import Management.Utils.DBUtil;
import Management.req.StudentRequest;
import Management.res.TableDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author lzq
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();

    @Override
    public TableDTO retrieveStudents(StudentRequest request) {
        StringBuilder sql=new StringBuilder();
        sql.append("select * from student ");
        if(request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
            sql.append(" where studentName like '%"+request.getSearchKey().trim()+"%'"+" OR studentId='"+request.getSearchKey().trim()+"'");

        }
        sql.append("order by studentId asc limit ").append(request.getStart()).append(",")
        .append(request.getPageSize());
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        TableDTO returnDTO=new TableDTO();
        try{
            conn = DBUtil.getConn();
            ps=conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            //查询记录
            returnDTO.setData(fillData(rs));

            sql.setLength(0);
            sql.append("select count(*) from student ");
            if(request.getSearchKey()!=null&&!"".equals(request.getSearchKey().trim())){
                sql.append(" where studentName like '%"+request.getSearchKey().trim()+"%'");

            }
            ps=conn.prepareStatement(sql.toString());
            rs=ps.executeQuery();
            while(rs.next()){
                int count = rs.getInt(1);
                returnDTO.setTotalCount(count);
            }
            return returnDTO;
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
            DBUtil.closeRs(rs);
        }
        return null;
    }

    private Vector<Vector<Object>> fillData(ResultSet rs) throws SQLException {
        Vector<Vector<Object>> data=new Vector<>();
        while(rs.next()){
            Vector<Object>oneRecord=new Vector<>();
            String id= rs.getString("studentId");
            String studentName = rs.getString("studentName");
            String studentSex = rs.getString("studentSex");
            String studentSchool = rs.getString("studentSchool");
            oneRecord.addElement(id);
            oneRecord.addElement(studentName);
            oneRecord.addElement(studentSex);
            oneRecord.addElement(studentSchool);
            data.addElement(oneRecord);

        }
        return data;
    }

    /**
     * 通过学号查询学生
     * @param id 学生学号
     * @return
     */
    @Override
    public Student queryById(String id) {
        Student student = studentDao.queryById(id);
        return student;
    }

    /**
     * 修改学生信息
     * @param student
     * @return 是否成功
     */
    @Override
    public boolean updateAll(Student student) {

        int i = studentDao.updateAll(student);

        return i > 0 ? true : false;
    }
    /**
     * 插入学生信息
     * @param student 一个学生对象
     */
    @Override
    public boolean save(Student student) {


        if (student == null||"".equals(student.getStudentId().trim())) {
            System.out.println("学生信息为空");
            return false;
        }

        int save = studentDao.save(student);
        if (save > 0) {
           return true;
        } else {
            return false;
        }
    }

    /**
     * 通过学号修改学生姓名
     * @param id 学生学号
     * @param name 要修改的姓名
     */
    @Override
    public void updateNameById(String id, String name) {

        if (id == null || name == null || id.length() == 0 || name.length() == 0) {
            System.out.println("缺少参数");
            return;
        }

        int i = studentDao.updateNameById(id, name);
        if (i > 0) {
            System.out.println("修改成功");
        }

    }

    /**
     * 通过学号修改学生学院
     * @param id 学生学号
     * @param school 学院
     */
    @Override
    public void updateSchoolById(String id, String school) {
        if (id == null || school == null || id.length() == 0 || school.length() == 0) {
            System.out.println("缺少参数");
            return;
        }
        int i = studentDao.updateSchoolById(id, school);
        if (i > 0) {
            System.out.println("修改成功");
        }
    }

    /**
     * 删除学生信息
     * @param selectedStudentIds
     * @return boolean
     */
    @Override
    public boolean delete(String[] selectedStudentIds) {
        StringBuilder sql=new StringBuilder();
        sql.append("delete from student where studentId in (");
        for(int i=0;i<selectedStudentIds.length;i++){
            if(i== selectedStudentIds.length-1){
                sql.append(" ? ");
            }else{
                sql.append(" ?, ");
            }
        }
        sql.append(")");
        Connection conn=null;
        PreparedStatement ps=null;
        try{
            conn=DBUtil.getConn();
            ps=conn.prepareStatement(sql.toString());
            for(int i=0;i<selectedStudentIds.length;i++){
                ps.setString(i+1,selectedStudentIds[i]);
            }
            return ps.executeUpdate()==selectedStudentIds.length;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closePs(ps);
            DBUtil.closeConn(conn);
        }
        return false;
    }
}
