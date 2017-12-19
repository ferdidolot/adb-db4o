package com.adb.database.query;

import com.adb.model.Person;
import com.adb.model.Student;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

import java.util.HashMap;
import java.util.Map;


public class Db4oQuery {
    private ObjectContainer client;

    public Db4oQuery(ObjectContainer client){
        this.client = client;
    }

public ObjectSet simpleSelect(){
    Query query = client.query();
    query.constrain(Student.class);
    query.descend("id").constrain(111).equal();
    return query.execute();
}

public ObjectSet complexSelect(){
    Query query = client.query();
    query.constrain(Student.class);
    query.descend("yearlyTuitionFee").constrain(1000).greater();
    query.descend("name").constrain("N").startsWith(true);
    return query.execute();
}

public Map<String, Integer> groupAndAggregate(){
    Query query = client.query();
    query.constrain(Student.class);
    ObjectSet<Student> objectSet = query.execute();
    Map<String, Integer> map = new HashMap<>();
    for(Student student: objectSet){
        if(map.containsKey(student.getGuardian().getName())){
            map.put(student.getGuardian().getName(), map.get(student.getGuardian().getName()) + student.getYearlyTuitionFee());
        }
        else{
            map.put(student.getGuardian().getName(), student.getYearlyTuitionFee());
        }
    }
    return map;
}

public ObjectSet simpleJoin(){
    Query query = client.query();
    query.constrain(Student.class);
    return query.execute();
}

    public ObjectSet complexJoin(){
        Query query = client.query();
        query.constrain(Student.class);
        return query.execute();
    }


//    public static ResultSet groupAndAggregate() throws SQLException{
//        String query = "SELECT guardian, SUM(yearlytuitionfee) FROM student GROUP BY guardian";
//        return execute(query);
//    }
//
//    public static ResultSet simpleJoin() throws SQLException{
//        String query = "SELECT * FROM student a " +
//                "INNER JOIN coursetaken b ON a.studentid = b.studentid";
//        return execute(query);
//    }
//
//    public static ResultSet complexJoin() throws SQLException{
//        String query = "SELECT * FROM student a " +
//                "INNER JOIN coursetaken b ON a.studentid = b.studentid " +
//                "INNER JOIN course c ON b.courseid = c.courseid " +
//                "INNER JOIN professor d ON d.profid = c.profid ";
//        return execute(query);
//    }
}
