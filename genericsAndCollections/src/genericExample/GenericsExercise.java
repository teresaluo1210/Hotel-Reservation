package genericExample;

import java.util.ArrayList;

public class GenericsExercise {
    public static void main(String[] args){
        ArrayList<Object> variables = new ArrayList<Object>();
        Double doubleNumber = 2.3;
        String name = "Teresa";
        Integer intNumber = 1;
        Character letter = 'a';

        variables.add(doubleNumber);
        variables.add(name);
        variables.add(intNumber);
        variables.add(letter);

        for (int i = 0; i < variables.size(); i ++ ) {
            displayClassName(variables.get(i));
        }
    }

    static <T> void displayClassName (T variable){
        System.out.println(variable.getClass().getName());
    }
}
