package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class HotelResource {
    private HotelResource(){
        super();
    }
    private static final CustomerService CUSTOMERSERVICE = CustomerService.getInstance();
    private static final ReservationService RESERVATIONSERVICE = ReservationService.getInstance();
    private static HotelResource HOTELRESOURCE = null;
    public static HotelResource getInstance() {
        if (HOTELRESOURCE == null) {
            HOTELRESOURCE = new HotelResource();
        }
        return HOTELRESOURCE;
    }

//    Methods
    public Customer getCustomer(String email) {

        return CUSTOMERSERVICE.getCustomer(email);
    }

    public boolean createACustomer(String email, String firstName, String lastName) {
        return CUSTOMERSERVICE.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {

        return RESERVATIONSERVICE.getARoom(roomNumber);
    }

    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date CheckOutDate) {

        return RESERVATIONSERVICE.reserveARoom(CUSTOMERSERVICE.getCustomer(customerEmail), room, checkInDate, CheckOutDate);
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {

        return CUSTOMERSERVICE.getCustomerReservations(CUSTOMERSERVICE.getCustomer(customerEmail));
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return RESERVATIONSERVICE.findRooms(checkIn, checkOut);
    }
}
