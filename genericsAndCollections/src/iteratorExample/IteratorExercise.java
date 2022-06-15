package iteratorExample;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class IteratorExercise {
    public static void main (String[] args){
        List<String> names = new LinkedList<>();

        names.add("Mike");
        names.add("Bob");
        names.add("Katie");

        Iterator<String> iterator = names.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }




    }
}
