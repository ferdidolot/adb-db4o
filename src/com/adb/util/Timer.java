package com.adb.util;

public class Timer {
    private static long startTime;
    private static long endTime;

    public Timer(){
        this.startTime = 0;
        this.endTime = 0;
    }

    public Timer(long startTime, long endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

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
