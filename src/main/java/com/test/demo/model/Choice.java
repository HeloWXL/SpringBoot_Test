package com.test.demo.model;

import java.util.Date;

public class Choice {
    private Integer selectId;

    private String selectQuestion;

    private String selectA;

    private String selectB;

    private String selectC;

    private String selectD;

    private String answer;

    private Integer teacherId;

    private Date selectCreatetime;

    public Integer getSelectId() {
        return selectId;
    }

    public void setSelectId(Integer selectId) {
        this.selectId = selectId;
    }

    public String getSelectQuestion() {
        return selectQuestion;
    }

    public void setSelectQuestion(String selectQuestion) {
        this.selectQuestion = selectQuestion == null ? null : selectQuestion.trim();
    }

    public String getSelectA() {
        return selectA;
    }

    public void setSelectA(String selectA) {
        this.selectA = selectA == null ? null : selectA.trim();
    }

    public String getSelectB() {
        return selectB;
    }

    public void setSelectB(String selectB) {
        this.selectB = selectB == null ? null : selectB.trim();
    }

    public String getSelectC() {
        return selectC;
    }

    public void setSelectC(String selectC) {
        this.selectC = selectC == null ? null : selectC.trim();
    }

    public String getSelectD() {
        return selectD;
    }

    public void setSelectD(String selectD) {
        this.selectD = selectD == null ? null : selectD.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
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