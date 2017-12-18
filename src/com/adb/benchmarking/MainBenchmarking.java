package com.adb.benchmarking;

import java.io.IOException;
import java.sql.SQLException;

public class MainBenchmarking {
    public static void main(String args[]) throws SQLException, IOException, InterruptedException{
        new BenchmarkingSet("dbset1", 100, 100, 100
                , "import_input_set_1.bat").start();

        new BenchmarkingSet("dbset2", 10000, 100, 100
                , "import_input_set_2.bat").start();
    }
}
