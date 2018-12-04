package com.test.demo.controller.resp;

/**
 * @author wangxl
 * @date 2018/11/30 9:26
 */
public class ScoreVo {
    private  String   studentName;
    private Integer studentSno;
    private  String TeacherName;
    private  String courseName;
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentSno() {
        return studentSno;
    }

    public void setStudentSno(Integer studentSno) {
        this.studentSno = studentSno;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
