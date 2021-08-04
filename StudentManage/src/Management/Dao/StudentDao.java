package Management.Dao;

import Management.Pojo.Student;

/**
 * @author lzq
 */
public interface StudentDao {
    /**
     * 更新全部信息
     * @param student
     * @return
     */
    public int updateAll(Student student);

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
    public int save(Student student);

    /**
     * 通过学号修改学生姓名
     * @param id 学生学号
     * @param name 要修改的姓名
     */
    public int updateNameById(String id,String name);

    /**
     * 通过学生学号修改学生学院
     * @param id 学生学号
     * @param school
     */
    public int updateSchoolById(String id,String school);


}
