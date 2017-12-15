package com.adb.database.query;

import com.adb.database.connection.Db4oConnection;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;

public class NativeQuery {
    private Db4oConnection db4oConnection;

    public NativeQuery(){
        db4oConnection = new Db4oConnection();
        db4oConnection.connect();
    }

    public ObjectSet execute(Predicate predicate){
        return db4oConnection.getObjectContainer().query(predicate);
    }
}
