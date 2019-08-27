package com.test.demo.controller.resp;

/**
 * @author wangxl
 * @date 2018/12/10 13:39
 */
public class RespCourseVo {
    private String CourseName;
    private String teacherName;
    private String CoursePicture;
    private Integer courseId;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCoursePicture() {
        return CoursePicture;
    }

    public void setCoursePicture(String coursePicture) {
        CoursePicture = coursePicture;
    }
}
