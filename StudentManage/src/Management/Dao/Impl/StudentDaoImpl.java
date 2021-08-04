package Management.Dao.Impl;

import Management.Dao.StudentDao;
import Management.Pojo.Student;
import Management.Utils.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author lzq
 */
public class StudentDaoImpl implements StudentDao {
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    @Override
    public int updateAll(Student student) {
        String sql = "update student set studentSchool = ? , studentName = ? ,studentSex=? where studentId = ?";

        int update = jdbcTemplate.update(sql, student.getStudentSchool(), student.getStudentName(),student.getStudentSex() ,student.getStudentId());
        return update;
    }
    @Override
    public Student queryById(String id) {
        Student student = null;
        try{
            String sql = "select * from student where studentId = ?";
            student = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), id);
        }catch (Exception e){

        }

        return student;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into Student values(?,?,?,?)";
        int update = 0;
        try{
            update = jdbcTemplate.update(sql,
                    student.getStudentId(),
                    student.getStudentName(),
                    student.getStudentSex(),
                    student.getStudentSchool());
        }catch (Exception e){

        }

        return update;
    }

    @Override
    public int updateNameById(String id,String name) {
        String sql = "update Student set studentName = ? where studentId = ?";
        int update = jdbcTemplate.update(sql, name, id);
        return update;
    }

    @Override
    public int updateSchoolById(String id, String school) {
        String sql = "update student set studentSchool = ? where studentId = ?";
        int update = jdbcTemplate.update(sql, school, id);
        return update;
    }


}
