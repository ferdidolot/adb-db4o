package com.adb.model;

import java.util.List;

public class Professor extends Person {
    private List<Course> courses;

    public Professor(){
        super();
    }

    public Professor(String name, int id, List<Course> courses){
        super(id, name);
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
        courses.add(course);
        course.setProfessor(this);
    }
}
