package com.adb.model;

import com.adb.exception.NegativeAmountException;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private List<Course> courses;
    private int yearlyTuitionFee;
    private Person guardian;

    public Student(){
        super();
        courses = new ArrayList<>();
        yearlyTuitionFee = 0;
        guardian = null;
    }

    public Student(int id){
        super(id);
    }

    public Student(int id, String name, List<Course> courses, int yearlyTuitionFee, Person guardian){
        super(id, name);
        this.courses = courses;
        for(Course course: courses){
            course.addStudent(this);
        }
        this.yearlyTuitionFee = yearlyTuitionFee;
        this.guardian = guardian;
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

    public void setYearlyTuitionFee(int yearlyTuitionFee) throws NegativeAmountException {
        if(yearlyTuitionFee < 0) throw new NegativeAmountException();
        this.yearlyTuitionFee = yearlyTuitionFee;
    }

    public String toString(){
        return getId()+", "+getName()+", "+getYearlyTuitionFee()+", "+guardian.getName();
    }
}
