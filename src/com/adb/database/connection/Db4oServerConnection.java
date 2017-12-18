package com.adb.database.connection;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectServer;

public class Db4oServerConnection implements Runnable{
    public ObjectServer server;

    @Override
    public void run() {
        // Create server
        server = Db4o.openServer("db/dbfile", 8732);
        server.grantAccess("user1", "password");
        server.grantAccess("user2", "password");
    }

    public ObjectContainer getClient(String username, String password){
        ObjectContainer client = Db4o.openClient("interstellar", 8732, username, password);
        return client;
    }

}
