import com.db4o.*;
import model.Person;
import util.Db4oConnection;
import util.PostgresConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String args[]) throws Exception{
        ObjectContainer db = Db4oConnection.getObjectContainer();
        Person p = new Person("dolsky", 28);
        db.store(p);
        ObjectSet<Person> objectSet = db.query(Person.class);
        for (Person person: objectSet) {
            System.out.println(person);
        }
        db.close();

        Connection connection = PostgresConnection.getConnection();
        Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM \"Person\"");
        while(resultSet.next()){
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getInt("age"));
        }
    }
}

