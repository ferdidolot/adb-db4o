package com.adb.database.query;

import com.adb.model.Student;
import com.adb.util.TimeUtil;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Db4oQueryExecutor {
    ObjectContainer client;

    public Db4oQueryExecutor(ObjectContainer client){
        this.client = client;
    }
    public double executeDb4oComplexJoin(){
        TimeUtil.start();
        ObjectSet objectSet = client.query(Student.class);
        TimeUtil.stop();
        return TimeUtil.runTime();
    }
}
