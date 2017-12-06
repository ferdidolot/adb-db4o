package com.adb;
import com.adb.model.*;
import com.adb.util.*;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String args[]) throws Exception{
        Options options = new OptionsBuilder()
                .warmupIterations(5)
                .measurementIterations(5)
                .include(Main.class.getSimpleName()).forks(1).build();

        new Runner(options).run();
//        countDb4o();

    }

    public static void countPersonDb4o(){
        ObjectContainer db = Db4oConnection.getObjectContainer();
        ObjectSet<Person> objectSet = db.query(Person.class);
        System.out.println(objectSet.size());
    }

    public static void deletePersonDb4o(){
        ObjectContainer db = Db4oConnection.getObjectContainer();
        ObjectSet<Person> objectSet = db.query(Person.class);
        for (Person person: objectSet) {
            db.delete(person);
        }
    }


    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    public static void runDb4oQueries(){
        ObjectContainer db = Db4oConnection.getObjectContainer();
        ObjectSet<Person> objectSet = db.query(Person.class);
        for (Person person: objectSet) {
        }
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    public static void runPostgreQueries() throws Exception{
        Connection connection = PostgresConnection.getConnection();
        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Person\"");
        while(resultSet.next()){
        }
    }
}

