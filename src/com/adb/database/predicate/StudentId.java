package com.adb.database.predicate;

import com.adb.model.Student;
import com.db4o.query.Predicate;

public class StudentId extends Predicate<Student>{
    private int id;

    public StudentId(int id){
        this.id = id;
    }
    public boolean match(Student student){
        return student.getId() == id;
    }
}
