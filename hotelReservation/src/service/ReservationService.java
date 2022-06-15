package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationService {

    private ReservationService(){
        this.allReservations = new ArrayList<>();
        this.allRoomId = new HashSet<>();
        this.allRooms = new HashMap<>();
    }
    private static ReservationService RESERVATIONSERVICE = null;
    public static ReservationService getInstance() {
        if (RESERVATIONSERVICE == null) {
            RESERVATIONSERVICE = new ReservationService();
        }
        return RESERVATIONSERVICE;
    }
//  Variables
    private ArrayList<Reservation> allReservations;
    public  HashSet<String> allRoomId;
    public HashMap<String,IRoom> allRooms;


// Methods
// admin-add room to all rooms
    public void addRoom(IRoom room) {
        if (allRooms.containsKey(room.getRoomNumber())) {
            System.out.println("Error-Room already exists.\nAdd room failed.");
        } else {
            allRooms.put(room.getRoomNumber(),room);
            System.out.println("Room successfully added!");
        }
    }
//customer-returns room object: after user select a room from a list of available rooms
    public IRoom getARoom(String roomID){
        return allRooms.get(roomID);
    }
//returns a reservation object
    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation r = new Reservation(customer, room, checkInDate, checkOutDate);
        allReservations.add(r);
        customer.addCustomerReservations(r);
        room.setRoomSchedule(r);
        return r;
    }

// returns a list of room objects that fits within the available time interval
    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        ArrayList<IRoom> availableRooms = new ArrayList<>();
        for (IRoom room : allRooms.values()) {
            if (room.isAvailable(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public void printAllReservation() {
        if (!allReservations.isEmpty()) {
            Iterator<Reservation> allReservationsIter = allReservations.iterator();
            while (allReservationsIter.hasNext()) {
                System.out.println(allReservationsIter.next());
            }
        } else {
            System.out.println("There are currently no reservations in the system.");
        }
    }

    public Collection<IRoom> getAllRooms() {
        return allRooms.values();
    }
}
