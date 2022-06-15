package mapsExample;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MapExercise {
    public static void main(String[] args){
        Map<String,Person> mapOfPeople = new HashMap<String,Person>();
        ArrayList<Person> people= new ArrayList<Person>();

        Person jeff = new Person("Jeff","jeff@email.com");
        Person kate = new Person("Kate","kate@email.com");
        Person amy = new Person("Amy","amy@email.com");
        Person tom = new Person("Tom","tom@email.com");

        people.add(jeff);
        people.add(kate);
        people.add(amy);
        people.add(tom);

        for(Person p: people){
            mapOfPeople.put(p.getEmail(),p);
        }

        System.out.println(mapOfPeople.get("jeff@email.com"));
    }
}


