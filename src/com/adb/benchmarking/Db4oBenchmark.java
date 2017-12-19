package com.adb.benchmarking;

import com.adb.database.query.Db4oQueryExecutor;
import com.adb.model.Person;
import com.adb.database.connection.Db4oConnection;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class Db4oBenchmark {
    private Db4oQueryExecutor db4oQueryExecutor;

    public Db4oBenchmark(ObjectContainer client){
        db4oQueryExecutor = new Db4oQueryExecutor(client);
    }

    public double benchmarkDb4oSimpleSelect(int iterations) {
        double sum = 0;
        double res = db4oQueryExecutor.executeDb4oSimpleSelect();
        System.out.println("Warming up: " + res);
        for(int i = 1 ; i <= iterations ; i++){
            res = db4oQueryExecutor.executeDb4oSimpleSelect();
            sum += res;
            System.out.print("Iteration "+ i +": " + res);
            System.out.println();
        }
        return sum/iterations;
    }

    public double benchmarkDb4oComplexSelect(int iterations) {
        double sum = 0;
        double res = db4oQueryExecutor.executeDb4oComplexSelect();
        System.out.println("Warming up: " + res);
        for(int i = 1 ; i <= iterations ; i++){
            res = db4oQueryExecutor.executeDb4oComplexSelect();
            sum += res;
            System.out.print("Iteration "+ i +": " + res);
            System.out.println();
        }
        return sum/iterations;
    }

    public double benchmarkDb4oGroupAndAggregate(int iterations) {
        double sum = 0;
        double res = db4oQueryExecutor.executeDb4oGroupAndAggregate();
        System.out.println("Warming up: " + res);
        for(int i = 1 ; i <= iterations ; i++){
            res = db4oQueryExecutor.executeDb4oGroupAndAggregate();
            sum += res;
            System.out.print("Iteration "+ i +": " + res);
            System.out.println();
        }
        return sum/iterations;
    }

    public double benchmarkDb4oSimpleJoin(int iterations) {
        double sum = 0;
        double res = db4oQueryExecutor.executeDb4oSimpleJoin();
        System.out.println("Warming up: " + res);
        for(int i = 1 ; i <= iterations ; i++){
            res = db4oQueryExecutor.executeDb4oSimpleJoin();
            sum += res;
            System.out.print("Iteration "+ i +": " + res);
            System.out.println();
        }
        return sum/iterations;
    }

    public double benchmarkDb4oComplexJoin(int iterations) {
        double sum = 0;
        double res = db4oQueryExecutor.executeDb4oComplexJoin();
        System.out.println("Warming up: " + res);
        for(int i = 1 ; i <= iterations ; i++){
            res = db4oQueryExecutor.executeDb4oComplexJoin();
            sum += res;
            System.out.print("Iteration "+ i +": " + res);
            System.out.println();
        }
        return sum/iterations;
    }
}
