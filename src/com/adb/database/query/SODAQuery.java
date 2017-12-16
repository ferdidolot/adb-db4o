package com.adb.database.query;

import com.adb.database.connection.Db4oConnection;
import com.adb.model.Student;
import com.db4o.ObjectSet;
import com.db4o.query.Query;

public class SODAQuery {
    private Db4oConnection db4oConnection;
    private Query query;

    public SODAQuery(Db4oConnection db4oConnection){
        this.db4oConnection = db4oConnection;
        this.query = db4oConnection.getObjectContainer().query();
    }

    public ObjectSet queryStudent(){
        query.constrain(Student.class);
        query.descend("id").constrain(1);
        return query.execute();
    }
}
