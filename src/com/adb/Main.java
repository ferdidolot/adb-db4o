package com.adb;
import com.adb.model.Person;
import com.adb.util.PostgresConnection;
import com.adb.util.Timer;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class Main {
    public static void main(String args[]) throws Exception{
//        ObjectContainer obj = Db4o.openFile("db/dbfile");
//        for(int i = 0 ; i < 1000000 ; i++)
//        obj.store(new Person("dolsky"));
//        obj.commit();
//        obj.close();
//        Options db4oBenchmarkOptions =
//                new OptionsBuilder().include(com.adb.benchmarking.Db4oBenchmark.class.getSimpleName())
//                        .warmupIterations(1)
//                        .measurementIterations(3)
//                        .timeout(TimeValue.hours(3))
//                        .resultFormat(ResultFormatType.CSV)
//                        .forks(1)
//                        .build();
//
//        new Runner(db4oBenchmarkOptions).run();
//
//        Options jdbcBenchmarkOptions =
//                new OptionsBuilder().include(com.adb.benchmarking.JdbcBenchmark.class.getSimpleName())
//                        .warmupIterations(1)
//                        .measurementIterations(3)
//                        .timeout(TimeValue.hours(3))
//                        .resultFormat(ResultFormatType.CSV)
//                        .forks(1)
//                        .build();
//
//        new Runner(jdbcBenchmarkOptions).run();
        String dbfilename = "db/dbfile";
        Timer.start();
        countPersonDb4o(dbfilename);
        Timer.stop();
        System.out.println("Counting object of DB4O takes "+Timer.runTime() + " s");

        dbfilename = "db/Person1000";
        Timer.start();
        countPersonDb4o(dbfilename);
        Timer.stop();
        System.out.println("Counting object of DB4O takes "+Timer.runTime() + " s");

        String tablename = "Person";
        Timer.start();
        countJdbc("Person");
        Timer.stop();
        System.out.println("Counting person of postgresql takes " + Timer.runTime() + " s");

    }

    public static void countPersonDb4o(String dbfilename){
        ObjectContainer db = Db4o.openFile(dbfilename);
        ObjectSet<Person> objectSet = db.query(Person.class);
        System.out.println("Object size: "+objectSet.size());
        db.close();
    }

    private static void countJdbc(String table) throws Exception{
        Connection connection = PostgresConnection.getConnection();
        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \""+table+"\"");
        System.out.println("Record size: " + resultSet.getRow());
    }
//
//    public static void deletePersonDb4o(){
//        ObjectContainer db = Db4oConnection.getObjectContainer();
//        ObjectSet<Person> objectSet = db.query(Person.class);
//        for (Person person: objectSet) {
//            db.delete(person);
//        }
//    }

}

