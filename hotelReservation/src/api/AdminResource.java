package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class AdminResource {
    private AdminResource(){
        super();
    }
    private static AdminResource ADMINRESOURCE = null;
    public static AdminResource getInstance() {
        if (ADMINRESOURCE == null) {
            ADMINRESOURCE = new AdminResource();
        }
        return ADMINRESOURCE;
    }

    private static final CustomerService CUSTOMERSERVICE = CustomerService.getInstance();
    private static final ReservationService RESERVATIONSERVICE = ReservationService.getInstance();


    public Customer getCustomer(String email) {

        return CUSTOMERSERVICE.getCustomer(email);
    }

    public void addRooms(List<IRoom> rooms) {
        for (IRoom r : rooms) {
            addRoom(r);
        }
    }

    public void addRoom(IRoom room) {

        RESERVATIONSERVICE.addRoom(room);
    }

    public Collection<IRoom> getAllRooms() {

        return RESERVATIONSERVICE.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {

        return CUSTOMERSERVICE.getAllCustomers();
    }

    public Collection<String> getAllAccounts() {

        return CUSTOMERSERVICE.getAllAccount();
    }

    public void displayAllReservations() {

        RESERVATIONSERVICE.printAllReservation();
    }
}
