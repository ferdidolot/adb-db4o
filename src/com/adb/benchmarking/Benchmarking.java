package com.adb.benchmarking;

import java.io.IOException;
import java.sql.SQLException;

public interface Benchmarking {
    final int NUM_OF_ITERATIONS = 5;

    void importPostgres() throws IOException, InterruptedException;
    void importDb4o() throws IOException;
    void startDb4o();
    void start() throws IOException, SQLException, InterruptedException;
}
