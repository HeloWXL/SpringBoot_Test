package com.test.demo.model;

import java.util.Date;

public class Blank {
    private Integer blankId;

    private String blankQuestion;

    private String blankAnswer;

    private Integer teacherId;

    private Date selectCreatetime;

    public Integer getBlankId() {
        return blankId;
    }

    public void setBlankId(Integer blankId) {
        this.blankId = blankId;
    }

    public String getBlankQuestion() {
        return blankQuestion;
    }

    public void setBlankQuestion(String blankQuestion) {
        this.blankQuestion = blankQuestion == null ? null : blankQuestion.trim();
    }

    public String getBlankAnswer() {
        return blankAnswer;
    }

    public void setBlankAnswer(String blankAnswer) {
        this.blankAnswer = blankAnswer == null ? null : blankAnswer.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getSelectCreatetime() {
        return selectCreatetime;
    }

    public void setSelectCreatetime(Date selectCreatetime) {
        this.selectCreatetime = selectCreatetime;
    }
}