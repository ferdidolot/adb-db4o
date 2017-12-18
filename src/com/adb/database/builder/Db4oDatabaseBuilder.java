package com.adb.database.builder;

import com.adb.factory.CourseFactory;
import com.adb.factory.ProfessorFactory;
import com.adb.factory.StudentFactory;
import com.adb.model.Course;
import com.adb.model.Professor;
import com.adb.model.Student;
import com.adb.util.InputUtil;
import com.adb.util.TimeUtil;
import com.db4o.ObjectContainer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Db4oDatabaseBuilder {
    private ObjectContainer client;
    public Db4oDatabaseBuilder(ObjectContainer client){
            this.client = client;
    }

    public void createDb4oSchema(int studentNum, int professorNum, int courseNum) throws IOException {
        InputUtil inputUtil = new InputUtil();
        System.out.println("Reading student input");
        TimeUtil.start();
        List<List<String>> students = inputUtil.getCollectionString("student"+ studentNum +".in");
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        System.out.println("Reading professor input");
        TimeUtil.start();
        List<List<String>> professors = inputUtil.getCollectionString("professor"+ professorNum +".in");
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        System.out.println("Reading course input");
        TimeUtil.start();
        List<List<String>> courses = inputUtil.getCollectionString("course"+ courseNum +".in");
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        System.out.println("Reading course taken input");
        TimeUtil.start();
        List<List<String>> courseTaken = inputUtil.getCollectionString("coursetaken_"+ studentNum +"_"+ courseNum +".in");
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        System.out.println("Creating student objects");
        TimeUtil.start();
        Map<Integer, Student> studentMap = StudentFactory.produce(students);
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        System.out.println("Creating professor objects");
        TimeUtil.start();
        Map<Integer, Professor> professorMap = ProfessorFactory.produce(professors);
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        System.out.println("Creating course objects");
        TimeUtil.start();
        List<Course> courseList = CourseFactory.produce(courses, courseTaken, professorMap,studentMap);
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        System.out.println("Inserting objects to db4o");
        TimeUtil.start();
        for(Course course: courseList){
            client.store(course);
        }
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());
    }
}
