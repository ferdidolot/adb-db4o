package util;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;

public class Db4oConnection {
    private static String dbfile = "db/dbfile";
    private static ObjectContainer objectContainer;
    private static Db4oConnection db4oConnection= new Db4oConnection();

    private Db4oConnection(){
        connect();
    }

    public static ObjectContainer getObjectContainer(){
        return objectContainer;
    }

    private void connect(){
        objectContainer = null;
        try{
            objectContainer = Db4o.openFile(dbfile);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
