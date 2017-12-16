package com.adb;
import com.adb.database.predicate.StudentId;
import com.adb.database.query.JdbcQuery;
import com.adb.database.query.NativeQuery;
import com.adb.database.query.SODAQuery;
import com.adb.factory.CourseFactory;
import com.adb.factory.ProfessorFactory;
import com.adb.factory.StudentFactory;
import com.adb.model.*;
import com.adb.util.*;
import com.db4o.Db4o;
import com.db4o.ObjectSet;
import com.adb.database.connection.Db4oConnection;
import com.adb.database.builder.PostgreQueriesBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    private static Db4oConnection db4oConnection;
    public static void main(String args[]) throws Exception{
        Db4o.configure().activationDepth(1);
        db4oConnection = new Db4oConnection();
        db4oConnection.connect();

//        createDb4oSchema();
//        NativeQuery nativeQuery = new NativeQuery(db4oConnection);
//        QBEQuery qbeQuery = new QBEQuery(db4oConnection);
        SODAQuery sodaQuery = new SODAQuery(db4oConnection);
        Student student = new Student(1);
        TimeUtil.start();
        System.out.println("Start native query");
        for(int i = 0 ; i < 30 ; i++){
            ObjectSet<Student> objectSet = sodaQuery.queryStudent();
            db4oConnection.getObjectContainer().activate(student, 3);
            student = objectSet.next();
        }
        System.out.println(student.getCourses().get(0));

        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        TimeUtil.start();
        System.out.println("Start jdbc query");
        for(int i = 0 ; i < 30 ; i++){
            JdbcQuery.simpleJoin();
        }
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        db4oConnection.commit();
        db4oConnection.close();
//        db4oConnection.defrag();
    }

    public static void printObjectSet(ObjectSet objectSet){
        while(objectSet.hasNext()){
            System.out.println(objectSet.next());
        }
    }

    public static void clearDb4o(){
        ObjectSet objectSet = db4oConnection.getObjectContainer().query().execute();
        while(objectSet.hasNext()){
            db4oConnection.getObjectContainer().delete(objectSet.next());
            System.out.println(objectSet.next());
        }
    }

    public static void createDb4oSchema() throws Exception {
        InputUtil inputUtil = new InputUtil();
        System.out.println("Reading student input");
        TimeUtil.start();
        List<List<String>> students = inputUtil.getCollectionString("student1000.in");
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        System.out.println("Reading professor input");
        TimeUtil.start();
        List<List<String>> professors = inputUtil.getCollectionString("professor100.in");
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        System.out.println("Reading course input");
        TimeUtil.start();
        List<List<String>> courses = inputUtil.getCollectionString("course100.in");
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());

        System.out.println("Reading course taken input");
        TimeUtil.start();
        List<List<String>> courseTaken = inputUtil.getCollectionString("coursetaken_1000_100.in");
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
            db4oConnection.getObjectContainer().store(course);
        }
        TimeUtil.stop();
        System.out.println(TimeUtil.runTime());
    }

    public static void testQueryBuilder(){
        List<MetaDTO> metaDTOS = new ArrayList<MetaDTO>();
        List<String> name = new ArrayList<String>();
        name.add("name");
        name.add("id");
        metaDTOS.add(new MetaDTO("id", "1", "Integer"));
        String res = PostgreQueriesBuilder.select("Student", name, metaDTOS);
        System.out.println(res);
    }

}

