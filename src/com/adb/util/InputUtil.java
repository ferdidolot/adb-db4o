package com.adb.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputUtil {
    private static final String PATH = "input/";

    public List<List<String>> getCollectionString(String filename) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader(PATH + filename));
        String s;
        List<List<String>> list = new ArrayList<>();
        while( ( s = br.readLine()) != null){
            String[] in = s.split(",");
            List tmp = new ArrayList<String>();
            for(String string: in){
                tmp.add(string);
            }
            list.add(tmp);
        }
        return list;
    }
}
