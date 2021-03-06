package com.adb.model;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int courseId;
    private String title;
    private Professor professor;
    private List<Student> students;

    public Course(){
        this.courseId = 0;
        this.title = "";
        this.professor = null;
        this.students = new ArrayList<>();
    }

    public Course(int courseId, String title, Professor professor, List<Student> students){
        this.title = title;
        this.courseId = courseId;
        this.professor = professor;
        this.students = students;
        for(Student student: students){
            student.addCourse(this);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student){
        if(!students.contains(student)) students.add(student);
        if(!student.getCourses().contains(this))student.addCourse(this);
    }

    public String toString(){
        return courseId +" " + title ;
    }
}
