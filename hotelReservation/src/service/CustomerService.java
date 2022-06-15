package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;



public class CustomerService {

    private CustomerService() {
        this.allCustomers = new HashMap<>();
    }

    private static CustomerService CUSTOMERSERVICE = null;

    public static CustomerService getInstance() {
        if (CUSTOMERSERVICE == null) {
            CUSTOMERSERVICE = new CustomerService();
        }
        return CUSTOMERSERVICE;
    }
// Variables
    private Map<String, Customer> allCustomers;

//  Methods
    public boolean addCustomer(String email, String firstName, String lastName) {
        Customer c = new Customer(email, firstName, lastName);
        if (allCustomers.containsKey(c.getEmail())) {
            return false;
        } else {
            allCustomers.put(email, c);
            return true;
        }
    }

    public Customer getCustomer(String customerEmail) {

        return allCustomers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {

        return allCustomers.values();
    }

    public Collection<Reservation> getCustomerReservations(Customer customer) {
        if (allCustomers.containsKey(customer.getEmail())) {
            return customer.getCustomerReservations();
        }
        return null;
    }

    public Collection<String> getAllAccount(){

        return allCustomers.keySet();

    }

}