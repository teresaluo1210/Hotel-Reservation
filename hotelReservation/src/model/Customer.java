package model;

import java.util.ArrayList;

public class Customer {
//  Variables
    public String firstName;
    public String lastName;
    public String email;
    private ArrayList<Reservation> customerReservations = new ArrayList<>();

//    Constructor
    public Customer(String email, String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


//  Methods
//    get methods
    public String getFirstName(){

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public String getEmail() {

        return email;
    }
    public ArrayList<Reservation> getCustomerReservations(){

        return customerReservations;
    }

//    other methods
    public void addCustomerReservations(Reservation r){

        customerReservations.add(r);
    }



    @Override
    public String toString() {
        return "Customer{" +
                "First Name: '" + firstName + '\'' +
                ", Last Name: '" + lastName + '\'' +
                ", Email: '" + email + '\'' +
                '}';
    }
}
