import com.db4o.*;

public class Main {
    public static void main(String args[]) throws Exception{
        ObjectContainer db = Db4o.openFile("db/dbfile");
        Person p = new Person("dolsky", 28);
        db.store(p);
        ObjectSet<Person> objectSet = db.query(Person.class);
        for (Person person: objectSet) {
            System.out.println(person);
        }
        db.close();
    }
}

