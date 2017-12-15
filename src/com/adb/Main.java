package com.adb;
import com.adb.factory.CourseFactory;
import com.adb.factory.ProfessorFactory;
import com.adb.factory.StudentFactory;
import com.adb.model.*;
import com.adb.util.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import db.Db4oConnection;
import db.PostgreQueriesBuilder;
import db.PostgresConnection;

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
        ObjectSet<Course> objectSet = db4oConnection.getObjectContainer().query(Course.class);
        while(objectSet.hasNext()){
//            db4oConnection.getObjectContainer().delete(objectSet.next());
            System.out.println(objectSet.next());
        }
//        createDb4oSchema();

        db4oConnection.commit();
        db4oConnection.close();
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

