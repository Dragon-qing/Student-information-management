package Management.test;

import Management.Dao.Impl.StudentDaoImpl;
import Management.Dao.StudentDao;
import Management.Pojo.Student;
import Management.Service.Impl.StudentServiceImpl;
import Management.Service.StudentService;
import Management.Utils.JdbcUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author lzq
 */
public class test {


    @Test
    public void test01(){
        StudentService studentService = new StudentServiceImpl();
        Student student = studentService.queryById("1910300723");
        //Student student1 = new Student("1910300111","黄晓宇","男","材料与化学");

        //studentService.save(student1);

        //studentService.updateNameById("1910300723","胡婧禾");

//        studentService.updateSchoolById("1910300723","经济与管理");
        System.out.println(student);
    }

    @Test
    public void test02(){

    }


}
