package com.adb.database.config;

import com.adb.model.User;
import com.db4o.Db4o;

import java.util.ArrayList;
import java.util.List;

public class Db4oConfiguration {
    public static final String username = "user1";
    public static final String password = "password";
    public static final String db4oRootPath = "db/";


    public static List<User> getGrantedUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("user1", "password"));
        users.add(new User("user2", "password"));
        return users;
    }


}
