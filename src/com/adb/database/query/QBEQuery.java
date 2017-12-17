package com.adb.database.query;

import com.adb.database.connection.Db4oConnection;
import com.adb.model.Student;
import com.db4o.ObjectSet;

public class QBEQuery {
    private Db4oConnection db4oConnection;

    public QBEQuery(Db4oConnection db4oConnection){
        this.db4oConnection = db4oConnection;
    }

    public ObjectSet queryStudent(Student student){
        return db4oConnection.getObjectContainer().queryByExample(student);
    }
}
