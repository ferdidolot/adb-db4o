package com.adb.database.connection;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.defragment.Defragment;
import com.db4o.defragment.DefragmentConfig;


public class Db4oConnection {
    private static final String DBFILE= "db/dbfile" ;
    private ObjectContainer objectContainer;

    public Db4oConnection(){
        objectContainer = null;
    }

    public ObjectContainer getObjectContainer(){
        return objectContainer;
    }

    public void commit(){
        objectContainer.commit();
    }

    public void close(){
        objectContainer.close();
    }

    public void connect(){
        try{
            objectContainer = Db4o.openFile(DBFILE);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public  void defrag() throws Exception{
        Defragment.defrag(new DefragmentConfig(DBFILE));
    }
}

