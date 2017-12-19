package com.adb.benchmarking;

import com.db4o.Db4o;

import java.io.IOException;
import java.sql.SQLException;

public class MainBenchmarking {
    public static void main(String args[]) throws SQLException, IOException, InterruptedException{
        Db4o.configure().activationDepth(1);
        System.out.println("First benchmarking");
        new BenchmarkingSet("dbset1", 1000, 100, 100
                , "import_input_set_1.bat").start();

        System.out.println("Second benchmarking");
        new BenchmarkingSet("dbset2", 5000, 100, 100
                , "import_input_set_2.bat").start();

        System.out.println("Third benchmarking");
        new BenchmarkingSet("dbset3", 10000, 500, 500
                , "import_input_set_3.bat").start();

        System.out.println("Fourth benchmarking");
        new BenchmarkingSet("dbset4", 20000, 500, 500
                , "import_input_set_4.bat").start();

//        System.out.println("Fifth benchmarking");
//        new BenchmarkingSet("dbset5", 50000, 500, 500
//                , "import_input_set_5.bat").start();

    }
}
