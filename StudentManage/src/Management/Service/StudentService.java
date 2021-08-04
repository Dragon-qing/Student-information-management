package Management.Service;

import Management.Dao.StudentDao;
import Management.Pojo.Student;
import Management.req.StudentRequest;
import Management.res.TableDTO;

/**
 * @author lzq
 */
public interface StudentService {
    public boolean updateAll(Student student);

    /**
     * 返回学生信息
     */
    TableDTO retrieveStudents(StudentRequest request);
    /**
     * 通过学号查询学生类
     * @param id 学生学号
     * @return 返回一个学生对象
     */
    public Student queryById(String id);

    /**
     * 保存一个用户信息
     * @param student 一个学生对象
     */
    public boolean save(Student student);

    /**
     * 通过学号修改学生姓名
     * @param id 学生学号
     * @param name 要修改的姓名
     */
    public void updateNameById(String id,String name);

    /**
     * 通过学生学号修改学生学院
     * @param id 学生学号
     * @param school
     */
    public void updateSchoolById(String id,String school);


    public boolean delete(String[] selectedStudentIds);
}
