package collectionsExample;

import java.util.*;

public class CollectionsExercise {
    public static void main(String[] args){
        List<String> listOfItems = new LinkedList<String>();

        listOfItems.add("Mike");
        listOfItems.add("Bob");
        listOfItems.add("Alice");

        for(String i: listOfItems){
            System.out.println(i);
        }
    }
}
