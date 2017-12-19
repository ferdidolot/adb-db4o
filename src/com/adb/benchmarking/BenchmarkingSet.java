package com.adb.benchmarking;

import com.adb.database.builder.Db4oDatabaseBuilder;
import com.adb.database.config.Db4oConfiguration;
import com.adb.database.connection.Db4oServerConnection;
import com.adb.model.Student;
import com.adb.util.TimeUtil;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

public class BenchmarkingSet implements Benchmarking{
    private Db4oServerConnection db4oServerConnection;
    private ObjectContainer client;
    private int nStudent, nCourse, nProfessor;
    private String dbfilename;
    private String postgresImportFile;

    public BenchmarkingSet(String dbfilename, int nStudent, int nProfessor, int nCourse, String postgresImportFile){
        this.dbfilename = dbfilename;
        this.nStudent = nStudent;
        this.nProfessor = nProfessor;
        this.nCourse = nCourse;
        this.postgresImportFile = postgresImportFile;
        File db = new File(Db4oConfiguration.db4oRootPath + dbfilename);
        if(db.exists()){
            db.delete();
        }
    }

    @Override
    public void importPostgres() throws IOException, InterruptedException {
        TimeUtil.start();
        String command = "cmd /c start /wait cmd.exe /K \"cd script && set PGPASSWORD=passw0rd&& "+postgresImportFile+ " &&exit\"";
        Runtime.getRuntime()
                .exec(command)
                .waitFor();
        TimeUtil.stop();
        System.out.println("Time to import to postgresql: " + TimeUtil.runTime());

    }

    @Override
    public void importDb4o() throws IOException {
        Db4oDatabaseBuilder db4oDatabaseBuilder = new Db4oDatabaseBuilder(client);
        db4oDatabaseBuilder.createDb4oSchema(nStudent, nProfessor, nCourse);
    }

    @Override
    public void startDb4o() {
        db4oServerConnection = new Db4oServerConnection(dbfilename);
        db4oServerConnection.run();
        client = db4oServerConnection.getClient(Db4oConfiguration.username, Db4oConfiguration.password);
//        client.deactivate(Student.class, 1);
    }

    @Override
    public void start() throws IOException, SQLException, InterruptedException {
        startDb4o();
        importPostgres();
        importDb4o();

        System.out.println("Start JDBC query\n--");
        System.out.println("Simple select");
        System.out.println("Average: " + JdbcBenchmark.benchmarkJdbcSimpleSelect(NUM_OF_ITERATIONS));
        System.out.println();

        System.out.println("Complex select");
        System.out.println("Average: " + JdbcBenchmark.benchmarkJdbcComplexSelect(NUM_OF_ITERATIONS));
        System.out.println();

        System.out.println("Group and aggregate");
        System.out.println("Average: " + JdbcBenchmark.benchmarkJdbcGroupAndAggregate(NUM_OF_ITERATIONS));
        System.out.println();

        System.out.println("Simple join");
        System.out.println("Average: " + JdbcBenchmark.benchmarkJdbcSimpleJoin(NUM_OF_ITERATIONS));
        System.out.println();

        System.out.println("Complex join");
        System.out.println("Average: " + JdbcBenchmark.benchmarkJdbcComplexJoin(NUM_OF_ITERATIONS));
        System.out.println();

        Db4oBenchmark db4oBenchmark = new Db4oBenchmark(client);
        System.out.println("Start DB4O query\n--");
        System.out.println("Simple select");
        System.out.println("Average: " + db4oBenchmark.benchmarkDb4oSimpleSelect(NUM_OF_ITERATIONS));
        System.out.println();

        System.out.println("Complex select");
        System.out.println("Average: " + db4oBenchmark.benchmarkDb4oComplexSelect(NUM_OF_ITERATIONS));
        System.out.println();

        System.out.println("Group and aggregate");
        System.out.println("Average: " + db4oBenchmark.benchmarkDb4oGroupAndAggregate(NUM_OF_ITERATIONS));
        System.out.println();

        System.out.println("Simple join");
        System.out.println("Average: " + db4oBenchmark.benchmarkDb4oSimpleJoin(NUM_OF_ITERATIONS));
        System.out.println();

        System.out.println("Complex join");
        System.out.println("Average: " + db4oBenchmark.benchmarkDb4oComplexJoin(NUM_OF_ITERATIONS));
        System.out.println();


        client.close();
        db4oServerConnection.stop();
    }


}
