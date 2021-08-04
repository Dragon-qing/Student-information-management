package Management.Pojo;

import java.util.Date;

/**
 * @author lzq
 */
public class Student {
    /**
     * 学号
     */
    private String studentId;
    /**
     * 姓名
     */
    private String studentName;
    /**
     * 性别
     */
    private String studentSex;
    /**
     * 学院
     */
    private String studentSchool;

    public Student (){}
    public Student(String studentId, String studentName, String studentSex, String studentSchool) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentSex = studentSex;
        this.studentSchool = studentSchool;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex;
    }

    public String getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentSex='" + studentSex + '\'' +
                ", studentSchool='" + studentSchool + '\'' +
                '}';
    }
}
