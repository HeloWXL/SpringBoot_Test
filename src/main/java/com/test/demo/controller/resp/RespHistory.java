package com.test.demo.controller.resp;

import java.util.Date;

/**
 * @author wangxl
 * @date 2018/12/3 9:21
 */
public class RespHistory {


    private String studentName;
    private String courseName;
    private Date createTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
