package com.adb;
import com.adb.factory.CourseFactory;
import com.adb.factory.ProfessorFactory;
import com.adb.factory.StudentFactory;
import com.adb.model.*;
import com.adb.util.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    private static Db4oConnection db4oConnection;
    public static void main(String args[]) throws Exception{
        db4oConnection = new Db4oConnection();
        db4oConnection.connect();
//        db4oConnection.close();
//        insertDummyPerson("db/dbfile", 1000);
//        deletePersonDb4o();
//        countPersonDb4o();
//        db4oConnection.defrag();
//        InputUtil inputUtil = new InputUtil();
//        List<List<String>> students = inputUtil.getCollectionString("student.in");
//        List<List<String>> professors = inputUtil.getCollectionString("professor.in");
//        List<List<String>> courses = inputUtil.getCollectionString("course.in");
//        List<List<String>> courseTaken = inputUtil.getCollectionString("coursetaken.in");
//        Map<Integer, Student> studentMap = StudentFactory.produce(students);
//        Map<Integer, Professor> professorMap = ProfessorFactory.produce(professors);
//        List<Course> courseList = CourseFactory.produce(courses, courseTaken, professorMap,studentMap);
        ObjectSet<Course> objectSet = db4oConnection.getObjectContainer().query(Course.class);
        while(objectSet.hasNext()){
            System.out.println(objectSet.next());
        }
        List<MetaDTO> metaDTOS = new ArrayList<MetaDTO>();
        List<String> name = new ArrayList<String>();
        name.add("name");
        name.add("id");
        metaDTOS.add(new MetaDTO("id", "1", "Integer"));
        String res = PostgreQueriesBuilder.select("Student", name, metaDTOS);
        System.out.println(res);
//        for(Course course: courseList){
//            db.store(course);
//        }
        db4oConnection.commit();
        db4oConnection.close();
//        List<Student> list = StudentFactory.produce(students);
//        for(int i = 0 ; i < list.size() ; i++){
//            System.out.println(list.get(i));
//        }
    }

    public static void insertDummyPerson(String dbfilename, int N){
        ObjectContainer db = db4oConnection.getObjectContainer();
        for(int i = 0 ; i < N ; i++){
            db.store(new Person((i+1), "dolsky"));
        }
        db4oConnection.commit();
        db4oConnection.close();
    }

    private static void countPersonDb4o(){
        ObjectContainer db = db4oConnection.getObjectContainer();
        ObjectSet<Person> objectSet = db.query(Person.class);
        System.out.println("Object size: "+objectSet.size());
        db4oConnection.commit();
        db4oConnection.close();
    }

    private static void countJdbc(String table) throws Exception{
        Connection connection = PostgresConnection.getConnection();
        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \""+table+"\"");
        System.out.println("Record size: " + resultSet.getRow());
    }

    private  static void deletePersonDb4o(){
        ObjectContainer db = db4oConnection.getObjectContainer();
        ObjectSet<Person> objectSet = db.query(Person.class);
        for (Person person: objectSet) {
            db.delete(person);
        }
        db4oConnection.commit();
        db4oConnection.close();
    }

    public static void testTimer() throws Exception{
        TimeUtil.start();
        countPersonDb4o();
        TimeUtil.stop();
        System.out.println("Counting object of DB4O takes "+ TimeUtil.runTime() + " s");

        TimeUtil.start();
        countPersonDb4o();
        TimeUtil.stop();
        System.out.println("Counting object of DB4O takes "+ TimeUtil.runTime() + " s");

        String tablename = "Person";
        TimeUtil.start();
        countJdbc(tablename);
        TimeUtil.stop();
        System.out.println("Counting person of postgresql takes " + TimeUtil.runTime() + " s");
    }
}

