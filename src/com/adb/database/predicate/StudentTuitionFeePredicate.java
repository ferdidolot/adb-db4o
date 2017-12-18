package com.adb.database.predicate;

import com.adb.model.Student;
import com.db4o.query.Predicate;

public class StudentTuitionFeePredicate extends Predicate<Student>{
    public boolean match(Student student){
        return student.getYearlyTuitionFee() > 8000;
    }
}
