package com.adb.factory;

import com.adb.model.Course;
import com.adb.model.Professor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfessorFactory {
    public static Map<Integer, Professor> produce(List<List<String>> list){
        Map<Integer, Professor> dict = new HashMap<>();
        for(List<String> outer: list){
            dict.put(Integer.parseInt(outer.get(0)), new Professor(Integer.parseInt(outer.get(0)), outer.get(1), new ArrayList<>()));
        }
        return dict;
    }
}
