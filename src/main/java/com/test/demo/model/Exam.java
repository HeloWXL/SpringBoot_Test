package com.test.demo.model;

import java.util.Date;

public class Exam {
    private Integer examId;

    private String examName;

    private Integer examChoice;

    private Integer examBlank;

    private Integer teacherId;

    private Integer courseId;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName == null ? null : examName.trim();
    }

    public Integer getExamChoice() {
        return examChoice;
    }

    public void setExamChoice(Integer examChoice) {
        this.examChoice = examChoice;
    }

    public Integer getExamBlank() {
        return examBlank;
    }

    public void setExamBlank(Integer examBlank) {
        this.examBlank = examBlank;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}