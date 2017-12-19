package com.adb.benchmarking;

import com.adb.database.query.JdbcQueryExecutor;
import java.sql.SQLException;

public class JdbcBenchmark {


    public static double benchmarkJdbcSimpleSelect(int iterations) throws SQLException {
        double res = JdbcQueryExecutor.executeJdbcSimpleSelect();
        double sum = 0;
        System.out.println("Warming up: " + res);
        for(int i = 1 ; i <= iterations ; i++){
            res = JdbcQueryExecutor.executeJdbcSimpleSelect();
            sum += res;
            System.out.print("Iteration "+ i +": " + res);
            System.out.println();
        }
        return sum/iterations;
    }

    public static double benchmarkJdbcComplexSelect(int iterations) throws SQLException {
        double res = JdbcQueryExecutor.executeJdbcComplexSelect();
        double sum = 0;
        System.out.println("Warming up: " + res);
        for(int i = 1 ; i <= iterations ; i++){
            res = JdbcQueryExecutor.executeJdbcComplexSelect();
            sum += res;
            System.out.print("Iteration "+ i +": " + res);
            System.out.println();
        }
        return sum/iterations;
    }

    public static double benchmarkJdbcGroupAndAggregate(int iterations) throws SQLException {
        double res = JdbcQueryExecutor.executeJdbcGroupAndAggregate();
        double sum = 0;
        System.out.println("Warming up: " + res);
        for(int i = 1 ; i <= iterations ; i++){
            res = JdbcQueryExecutor.executeJdbcGroupAndAggregate();
            sum += res;
            System.out.print("Iteration "+ i +": " + res);
            System.out.println();
        }
        return sum/iterations;
    }

    public static double benchmarkJdbcSimpleJoin(int iterations) throws SQLException {
        double res = JdbcQueryExecutor.executeJdbcSimpleJoin();
        double sum = 0;
        System.out.println("Warming up: " + res);
        for(int i = 1 ; i <= iterations ; i++){
            res = JdbcQueryExecutor.executeJdbcSimpleJoin();
            sum += res;
            System.out.print("Iteration "+ i +": " + res);
            System.out.println();
        }
        return sum/iterations;
    }

    public static double benchmarkJdbcComplexJoin(int iterations) throws SQLException {
        double res = JdbcQueryExecutor.executeJdbcComplexJoin();
        double sum = 0;
        System.out.println("Warming up: " + res);
        for(int i = 1 ; i <= iterations ; i++){
            res = JdbcQueryExecutor.executeJdbcComplexJoin();
            sum += res;
            System.out.print("Iteration "+ i +": " + res);
            System.out.println();
        }
        return sum/iterations;
    }
}
