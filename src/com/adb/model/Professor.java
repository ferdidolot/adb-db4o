package com.adb.model;

import java.util.ArrayList;
import java.util.List;

public class Professor extends Person {
    private List<Course> courses;
    private int monthlySalary;

    public Professor(){
        super();
        this.courses = new ArrayList<>();
        this.monthlySalary = 0;
    }

    public Professor(int id, String name, List<Course> courses, int monthlySalary){
        super(id, name);
        this.courses = courses;
        for(Course course: courses){
            course.setProfessor(this);
        }
        this.monthlySalary = monthlySalary;
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

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String toString(){
        return getId()+ "," + getName()+","+getMonthlySalary() ;
    }
}
