package com.adb.database.connection;

import com.adb.database.config.Db4oConfiguration;
import com.adb.model.Student;
import com.adb.model.User;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;
import com.db4o.config.Configuration;


import java.util.List;

public class Db4oServerConnection {
    private String dbfilename;
    private String username, password;
    private List<User> grantedUsers;
    public ObjectServer server;


    public Db4oServerConnection(String dbfilename){
        this.dbfilename = dbfilename;
        this.grantedUsers = Db4oConfiguration.getGrantedUsers();
        Db4o.configure().activationDepth(1);
    }

    public void run() {
        // Create server
        Configuration configuration = Db4o.newConfiguration();
        configuration.activationDepth(1);
        server = Db4o.openServer(configuration, Db4oConfiguration.db4oRootPath + dbfilename, 8732);
        for(User user: grantedUsers) {
            server.grantAccess(user.getUsername(), user.getPassword());
        }
    }

    public void stop(){
        server.close();
    }

    public ObjectContainer getClient(String username, String password){
        Configuration configuration = Db4o.newConfiguration();
        configuration.activationDepth(1);
        ObjectContainer client = Db4o.openClient(configuration, "interstellar", 8732, username, password);

        client.activate(Student.class, 1);
        return client;
    }

}
