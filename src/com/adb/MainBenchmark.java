package com.adb;

import com.adb.benchmarking.Db4oBenchmark;
import com.adb.benchmarking.JdbcBenchmark;
import com.adb.database.config.Db4oConfiguration;
import com.adb.database.connection.Db4oServerConnection;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;

import java.sql.SQLException;

public class MainBenchmark {
    private static final int NUM_OF_ITERATIONS = 5;

    public static void main(String args[]) throws SQLException{
        Db4oServerConnection db4oServerConnection = new Db4oServerConnection("dbfile");
        db4oServerConnection.run();
        ObjectContainer client = db4oServerConnection.getClient(Db4oConfiguration.username, Db4oConfiguration.password);

        System.out.println("Start JDBC query\n--");
        System.out.println("Average: " + JdbcBenchmark.benchmarkJdbcComplexJoin(NUM_OF_ITERATIONS));

        Db4oBenchmark db4oBenchmark = new Db4oBenchmark(client);
        System.out.println("Start DB4O query\n--");
        System.out.println("Average: " + db4oBenchmark.benchmarkDb4oComplexJoin(NUM_OF_ITERATIONS));
        client.close();

    }
}
