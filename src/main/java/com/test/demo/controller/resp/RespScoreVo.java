package com.test.demo.controller.resp;

/**
 * @author wangxl
 * @date 2018/12/1
 */
public class RespScoreVo {

    private String studentName ;
    private  String courseName ;
    private Integer score ;
    private Integer studentSno;

    public Integer getStudentSno() {
        return studentSno;
    }

    public void setStudentSno(Integer studentSno) {
        this.studentSno = studentSno;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
