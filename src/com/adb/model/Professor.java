package com.adb.model;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Person {
    private List<Course> courses;

    public Professor(){
        super();
        this.courses = new ArrayList<>();
    }

    public Professor(int id, String name, List<Course> courses){
        super(id, name);
        this.courses = courses;
        for(Course course: courses){
            course.setProfessor(this);
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourses(Course course){
        if(!courses.contains(course)) courses.add(course);
        course.setProfessor(this);
    }

    public String toString(){
        return getId()+" " + getName() ;
    }
}
