package com.adb.factory;

import com.adb.model.Person;
import com.adb.model.Student;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class StudentFactory {
    public static Map<Integer, Student> produce(List<List<String>> list){
        Map<Integer, Student> dict = new HashMap<>();
        for(List<String> outer: list){
            dict.put(Integer.parseInt(outer.get(0)),
                    new Student(Integer.parseInt(outer.get(0)), outer.get(1), new ArrayList<>(), Integer.parseInt(outer.get(2)), new Person(outer.get(3)) ));
        }
        return dict;
    }
}
