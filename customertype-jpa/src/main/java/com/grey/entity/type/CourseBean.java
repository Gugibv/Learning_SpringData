package com.grey.entity.type;

import com.alibaba.fastjson.JSON;


public class CourseBean {

    private String course;

    private int grade;

    public CourseBean(String course, int grade) {
        this.course = course;
        this.grade = grade;
    }

    public static CourseBean fromString(String s) {
        CourseBean courseBean = JSON.parseObject(s, CourseBean.class);
        return courseBean;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}