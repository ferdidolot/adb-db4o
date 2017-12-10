package com.adb;
import com.adb.model.Person;
import com.adb.util.Db4oConnection;
import com.adb.util.PostgresConnection;
import com.adb.util.Timer;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.defragment.Defragment;
import com.db4o.defragment.DefragmentConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {
    private static Db4oConnection db4oConnection;
    public static void main(String args[]) throws Exception{
        db4oConnection = new Db4oConnection();
        db4oConnection.connect();
        db4oConnection.close();
//        insertDummyPerson("db/dbfile", 1000);
//        deletePersonDb4o();
//        countPersonDb4o();
        db4oConnection.defrag();
    }

    public static void insertDummyPerson(String dbfilename, int N){
        ObjectContainer db = db4oConnection.getObjectContainer();
        for(int i = 0 ; i < N ; i++){
            db.store(new Person((i+1), "dolsky"));
        }
        db4oConnection.commit();
        db4oConnection.close();
    }

    public static void countPersonDb4o(){
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

    public static void deletePersonDb4o(){
        ObjectContainer db = db4oConnection.getObjectContainer();
        ObjectSet<Person> objectSet = db.query(Person.class);
        for (Person person: objectSet) {
            db.delete(person);
        }
        db4oConnection.commit();
        db4oConnection.close();
    }

    public static void testTimer() throws Exception{
        String dbfilename = "db/dbfile";
        Timer.start();
        countPersonDb4o();
        Timer.stop();
        System.out.println("Counting object of DB4O takes "+Timer.runTime() + " s");

        dbfilename = "db/Person1000";
        Timer.start();
        countPersonDb4o();
        Timer.stop();
        System.out.println("Counting object of DB4O takes "+Timer.runTime() + " s");

        String tablename = "Person";
        Timer.start();
        countJdbc("Person");
        Timer.stop();
        System.out.println("Counting person of postgresql takes " + Timer.runTime() + " s");
    }
}

