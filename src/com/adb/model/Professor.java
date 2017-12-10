package com.adb.model;

import java.util.List;

public class Professor extends Person {
    private int profId;
    private List<Course> courses;

    public Professor(){
        super();
    }

    public Professor(String name, int profId, List<Course> courses){
        super(name);
        this.profId = profId;
        for(Course course: courses){
            course.setProfessor(this);
        }
    }

    public int getProfId() {
        return profId;
    }

    public void setProfId(int profId) {
        this.profId = profId;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourses(Course course){
        courses.add(course);
        course.setProfessor(this);
    }
}
