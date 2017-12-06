package com.adb.model;

import java.util.List;

public class Course {
    private int courseId;
    private String title;
    private int profId;
    private List<Student> students;

    public Course(){
        this.title = "";
        this.profId = 0;
    }

    public Course(int courseId, String title, int profId){
        this.title = title;
        this.profId = profId;
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProfId() {
        return profId;
    }

    public void setProfId(int profId) {
        this.profId = profId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
