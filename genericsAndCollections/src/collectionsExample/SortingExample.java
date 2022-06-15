package collectionsExample;


import java.util.ArrayList;
import java.util.Collections;

public class SortingExample {
    public static void main(String[] args){
        ArrayList<String> name = new ArrayList<String>();
        name.add("Zeus");
        name.add("Haha");
        name.add("Shasha");

        Collections.sort(name);

        for(String n: name){
            System.out.println(n);
        }

    }


}
