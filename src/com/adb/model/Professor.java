package com.adb.model;

import java.util.List;

public class Professor extends Person {
    private int profId;
    private List<Course> courses;

    public Professor(){
        super();
    }

    public Professor(String name, int profId){
        super(name);
        this.profId = profId;

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
}
