package com.adb.factory;

import com.adb.model.Course;
import com.adb.model.Professor;
import com.adb.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseFactory {
    public static List<Course> produce(List<List<String>>list,  List<List<String>> studentcourse,
                                Map<Integer, Professor> professors, Map<Integer, Student> students){
        List<Course> courses = new ArrayList<>();
        Map<Integer, List<Student>> coursesMap = new HashMap<>();
        for(List<String> outer: studentcourse){
            int studentId = Integer.parseInt(outer.get(0));
            int courseId = Integer.parseInt(outer.get(1));
//            System.out.println(studentId + " " + courseId);
            List<Student> now = coursesMap.get(courseId);
            if(now == null) {
                now = new ArrayList<>();
                now.add(students.get(studentId));
                coursesMap.put(courseId, now);
            }
            else{
                now.add(students.get(studentId));
                coursesMap.put(courseId, now);
            }
        }

        for(List<String> outer: studentcourse){
            int courseId = Integer.parseInt(outer.get(1));
//            System.out.println("now: "+ courseId+" " +coursesMap.get(courseId));
        }

        for(List<String> outer: list){
            int courseId = Integer.parseInt(outer.get(0));
            int profId = Integer.parseInt(outer.get(2));
            courses.add(new Course(courseId, outer.get(1),
                        professors.get(profId), coursesMap.get(courseId)  ));
        }
        return courses;
    }
}
