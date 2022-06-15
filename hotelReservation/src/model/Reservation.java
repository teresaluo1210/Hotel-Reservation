package model;

import java.util.*;

public class Reservation {
//  Variables
    public Customer customer;
    public IRoom room;
    public Date checkInDate;
    public Date checkOutDate;

//    constructor
    public Reservation(Customer customer, IRoom room,Date checkInDate, Date checkOutDate){
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;

    }

//    get methods

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return  "Customer: " + customer.getFirstName() + " " + customer.getLastName() + "\n"+
                "Room: " + room + "\n" +
                "Check In Date: " + checkInDate + " " +
                "Check Out Date: " + checkOutDate
                ;
    }
}

