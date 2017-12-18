package com.adb.benchmarking;

import com.adb.database.query.JdbcQueryExecutor;
import java.sql.SQLException;

public class JdbcBenchmark {

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
