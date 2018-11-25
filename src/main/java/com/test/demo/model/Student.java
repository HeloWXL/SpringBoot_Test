package com.test.demo.model;

public class Student {
    private Integer studentId;

    private Integer studentSno;

    private String studentName;

    private String studentPassword;

    private Integer studentAge;

    private String studentSex;

    private String studentPicture;

    private String studentProfession;

    private Integer classId;

    private String studentSchool;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

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
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword == null ? null : studentPassword.trim();
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(String studentSex) {
        this.studentSex = studentSex == null ? null : studentSex.trim();
    }

    public String getStudentPicture() {
        return studentPicture;
    }

    public void setStudentPicture(String studentPicture) {
        this.studentPicture = studentPicture == null ? null : studentPicture.trim();
    }

    public String getStudentProfession() {
        return studentProfession;
    }

    public void setStudentProfession(String studentProfession) {
        this.studentProfession = studentProfession == null ? null : studentProfession.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getStudentSchool() {
        return studentSchool;
    }

    public void setStudentSchool(String studentSchool) {
        this.studentSchool = studentSchool == null ? null : studentSchool.trim();
    }
}