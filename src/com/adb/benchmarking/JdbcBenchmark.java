package com.adb.benchmarking;

import com.adb.db.PostgresConnection;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class JdbcBenchmark {

    @Param({"Person"})
    private static String table;

    @Benchmark
    public static ResultSet runPostgreQueries(Blackhole bh) throws Exception{
        ResultSet resultSet = runTest(table);
        return resultSet;
    }

    private static ResultSet runTest(String table) throws Exception{
        Connection connection = PostgresConnection.getConnection();
        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \""+table+"\"");
        return resultSet;
    }

}
