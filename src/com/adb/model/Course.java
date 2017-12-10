package com.adb.model;

import java.util.List;

public class Course {
    private int courseId;
    private String title;
    private Professor professor;
    private List<Student> students;

    public Course(){
    }

    public Course(int courseId, String title, Professor professor, List<Student> students){
        this.title = title;
        this.courseId = courseId;
        this.professor = professor;
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
        students.add(student);
        student.addCourse(this);
    }
}
