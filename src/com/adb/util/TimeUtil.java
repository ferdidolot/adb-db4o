package com.adb.util;

public class TimeUtil {
    private static long startTime;
    private static long endTime;

    public static void start(){
        startTime = System.nanoTime();
    }

    public static void stop(){
        endTime = System.nanoTime();
    }

    public static double runTime(){
        return (endTime- startTime) / 1000000000.0;
    }
}
