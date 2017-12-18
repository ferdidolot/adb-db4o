package com.adb.database.connection;

import com.adb.database.config.Db4oConfiguration;
import com.adb.model.User;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;


import java.util.List;

public class Db4oServerConnection implements Runnable{
    private String dbfilename;
    private String username, password;
    private List<User> grantedUsers;
    public ObjectServer server;


    public Db4oServerConnection(String dbfilename){
        this.dbfilename = dbfilename;
        this.grantedUsers = Db4oConfiguration.getGrantedUsers();
    }

    @Override
    public void run() {
        // Create server
        Db4o.configure().activationDepth(4);
        server = Db4o.openServer(Db4oConfiguration.db4oRootPath + dbfilename, 8732);
        for(User user: grantedUsers) {
            server.grantAccess(user.getUsername(), user.getPassword());
        }
    }

    public void stop(){
        server.close();
    }

    public ObjectContainer getClient(String username, String password){
        ObjectContainer client = Db4o.openClient("interstellar", 8732, username, password);
        return client;
    }

}
