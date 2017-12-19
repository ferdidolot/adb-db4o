package com.adb.database.query;

import com.adb.model.Person;
import com.adb.model.Student;
import com.adb.util.TimeUtil;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

import java.util.Map;

public class Db4oQueryExecutor {
    private ObjectContainer client;
    private Db4oQuery db4oQuery;

    public Db4oQueryExecutor(ObjectContainer client){
        this.client = client;
        this.db4oQuery = new Db4oQuery(client);
    }

    public double executeDb4oSimpleSelect(){
        TimeUtil.start();
        ObjectSet objectSet = db4oQuery.simpleSelect();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }

    public double executeDb4oGroupAndAggregate(){
        TimeUtil.start();
        Map<String, Integer> map = db4oQuery.groupAndAggregate();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }

    public double executeDb4oComplexSelect(){
        TimeUtil.start();
        ObjectSet objectSet = db4oQuery.complexSelect();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }

    public double executeDb4oSimpleJoin(){
        TimeUtil.start();
        ObjectSet objectSet = db4oQuery.simpleJoin();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }

    public double executeDb4oComplexJoin(){
        TimeUtil.start();
        ObjectSet objectSet = db4oQuery.complexJoin();
        TimeUtil.stop();
        return TimeUtil.runTime();
    }


}
