package com.adb.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private List<Course> courses;

    public Student(){
        super();
        courses = new ArrayList<Course>();
    }

    public Student(int id, String name, List<Course> courses){
        super(id, name);
        this.courses = courses;
        for(Course course: courses){
            course.addStudent(this);
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course){
        if(!courses.contains(course)) courses.add(course);
        course.addStudent(this);
    }
}
