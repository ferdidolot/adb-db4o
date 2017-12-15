package com.adb.benchmarking;

import com.adb.model.Person;
import com.adb.database.connection.Db4oConnection;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class Db4oBenchmark {
    static ObjectContainer objectContainer;
    @Param({"Person"})
    private static String dbfilename;

    @Benchmark
    public static ObjectSet runDb4oQueries(Blackhole bh) throws Exception{
        ObjectSet objectSet = runTest(dbfilename);
        return objectSet;
    }

    private static ObjectSet runTest(String dbfilename) throws Exception {
        ObjectContainer db = new Db4oConnection().getObjectContainer();
        ObjectSet objectSet = db.query(Person.class);
//        db.close();
        return objectSet;
    }


}
