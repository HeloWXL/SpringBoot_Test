package com.test.demo.model;

public class Course {
    private Integer courseId;

    private String courseName;

    private String coursePicture;

    private String courseVideo;

    private String coursePingfen;

    private Integer teacherId;

    private String courseIntroduce;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public String getCoursePicture() {
        return coursePicture;
    }

    public void setCoursePicture(String coursePicture) {
        this.coursePicture = coursePicture == null ? null : coursePicture.trim();
    }

    public String getCourseVideo() {
        return courseVideo;
    }

    public void setCourseVideo(String courseVideo) {
        this.courseVideo = courseVideo == null ? null : courseVideo.trim();
    }

    public String getCoursePingfen() {
        return coursePingfen;
    }

    public void setCoursePingfen(String coursePingfen) {
        this.coursePingfen = coursePingfen == null ? null : coursePingfen.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseIntroduce() {
        return courseIntroduce;
    }

    public void setCourseIntroduce(String courseIntroduce) {
        this.courseIntroduce = courseIntroduce == null ? null : courseIntroduce.trim();
    }
}