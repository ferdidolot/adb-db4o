package com.adb.model;

import java.util.List;

public class Student extends Person{
    private int studentId;
    private List<Course> courses;

    public Student(){
        super();
    }

    public Student(int studentId, String name, List<Course> courses){
        super(name);
        this.studentId = studentId;
        this.courses = courses;
    }

    public void setStudentId(int studentId){
        this.studentId = studentId;
    }

    public int getStudentId(){
        return this.studentId;
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
