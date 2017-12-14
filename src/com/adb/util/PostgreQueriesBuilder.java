package com.adb.util;

import com.adb.model.MetaDTO;

import java.util.ArrayList;
import java.util.List;

public class PostgreQueriesBuilder {

    /**
     * Build insertion queries based on how many record per transaction
     * @param n number of parameter per transaction
     * @param table table to be executed
     */
    public static List<String> insert(int n, String table, List<List<String>> param){
        List<String> commands = new ArrayList<String>();
        int batch = (n < param.size()) ? 1 : param.size()/n + param.size() % n;
        for(int i = 0 ; i < batch ; i++){
            StringBuffer string = new StringBuffer("");
            string.append("INSERT INTO "+ table + " VALUES ");
            for(int j = 0 ; j < param.size() ; i++){
                string.append("( " );
                for(int k = 0 ; k < param.get(j).size()-1 ; k++) {
                    string.append(param.get(j).get(k)+", ");
                }
                string.append(param.get(j).get(param.get(j).size() - 1) + ") " );
            }
            commands.add(string.toString());
        }
       return commands;
    }

    /**
     * Generate postgresql select queries
     * @param table
     * @param fields
     * @param param
     * @return
     */
    public static String select(String table, List<String> fields, List<MetaDTO> param){
        StringBuffer stringBuffer = new StringBuffer("");
        stringBuffer.append("SELECT ");
        for(int i = 0 ; i < fields.size() ; i++ ){
            stringBuffer.append(fields.get(i)+", ");
        }
        stringBuffer.delete(stringBuffer.length()-2, stringBuffer.length());
        stringBuffer.append(" WHERE ");;
        for(int i = 0; i < param.size() ; i++){
            switch(param.get(i).getType()){
                case "String": stringBuffer.append(param.get(i).getName()+" = \'+"+param.get(i).getValue()+"\', ");
                    break;
                case "Integer": stringBuffer.append(param.get(i).getName()+" = " + param.get(i).getValue());
                    break;
            }
        }
        stringBuffer.substring(0, stringBuffer.length() - 3);
        stringBuffer.append(";");
        return stringBuffer.toString();
    }

}
