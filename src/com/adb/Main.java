package com.adb;
import com.adb.model.Person;
import com.adb.util.Db4oConnection;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;


public class Main {
    public static void main(String args[]) throws Exception{
//        ObjectContainer obj = Db4o.openFile("db/dbfile");
//        for(int i = 0 ; i < 1 ; i++)
//        obj.store(new Person("dolsky"));
//        obj.commit();
//        obj.close();
        Options db4oBenchmarkOptions =
                new OptionsBuilder().include(com.adb.benchmarking.Db4oBenchmark.class.getSimpleName())
                        .warmupIterations(1)
                        .measurementIterations(3)
                        .timeout(TimeValue.hours(3))
                        .resultFormat(ResultFormatType.CSV)
                        .forks(1)
                        .build();

        new Runner(db4oBenchmarkOptions).run();

        Options jdbcBenchmarkOptions =
                new OptionsBuilder().include(com.adb.benchmarking.JdbcBenchmark.class.getSimpleName())
                        .warmupIterations(1)
                        .measurementIterations(3)
                        .timeout(TimeValue.hours(3))
                        .resultFormat(ResultFormatType.CSV)
                        .forks(1)
                        .build();

        new Runner(jdbcBenchmarkOptions).run();
        countPersonDb4o();

    }

    public static void countPersonDb4o(){
        ObjectContainer db = Db4o.openFile("db/dbfile");
        ObjectSet<Person> objectSet = db.query(Person.class);
        System.out.println(objectSet.size());
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

