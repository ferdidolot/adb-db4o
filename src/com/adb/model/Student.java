package com.adb.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private List<Course> courses;
    private int yearlyTuitionFee;

    public Student(){
        super();
        courses = new ArrayList<>();
        yearlyTuitionFee = 0;
    }

    public Student(int id){
        super(id);
    }

    public Student(int id, String name, List<Course> courses, int yearlyTuitionFee){
        super(id, name);
        this.courses = courses;
        for(Course course: courses){
            course.addStudent(this);
        }
        this.yearlyTuitionFee = yearlyTuitionFee;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course){
        if(!courses.contains(course)) courses.add(course);
        if(!course.getStudents().contains(this)) course.addStudent(this);
    }

    public int getYearlyTuitionFee() {
        return yearlyTuitionFee;
    }

    public void setYearlyTuitionFee(int yearlyTuitionFee) {
        this.yearlyTuitionFee = yearlyTuitionFee;
    }

    public String toString(){
        return getId()+", "+getName()+", "+getYearlyTuitionFee();
    }
}
