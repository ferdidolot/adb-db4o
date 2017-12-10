package com.adb;
import com.adb.model.Person;
import com.adb.util.Db4oConnection;
import com.adb.util.PostgresConnection;
import com.adb.util.InputUtil;
import com.adb.util.TimeUtil;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


public class Main {
    private static Db4oConnection db4oConnection;
    public static void main(String args[]) throws Exception{
//        db4oConnection = new Db4oConnection();
//        db4oConnection.connect();
//        db4oConnection.close();
//        insertDummyPerson("db/dbfile", 1000);
//        deletePersonDb4o();
//        countPersonDb4o();
//        db4oConnection.defrag();
        InputUtil inputUtil = new InputUtil();
        List<List<String>> students = inputUtil.getCollectionString("student.in");
        for(int i = 0 ; i < students.size() ; i++){
            for(int j = 0 ; j < students.get(i).size() ; j++){
                System.out.println(students.get(i).get(j));
            }
        }
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

