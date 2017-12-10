package com.adb.model;

import java.util.List;

public class Student extends Person{
    private List<Course> courses;

    public Student(){
        super();
    }

    public Student(int id, String name, List<Course> courses){
        super(id, name);
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course){
        courses.add(course);
        course.addStudent(this);
    }
}
