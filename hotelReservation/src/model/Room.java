package model;

import java.util.*;

public class Room implements IRoom {
//  Variables
    public String roomNumber;
    public Double price;
    public RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }
//  Methods
//     get methods
    @Override
    public String getRoomNumber(){
        return roomNumber;
    }

    @Override
    public Double getRoomPrice(){
        return price;
    }

    @Override
    public RoomType getRoomType() {
        return enumeration;
    }

    @Override
    public boolean isFree() {
        return false;
    }

    private ArrayList<Reservation> schedule = new ArrayList<>();

//  other methods
    public boolean isAvailable(Date checkIn, Date checkOut) {
        if (schedule.size() == 0) {
            return true;
        } else {
            for (Reservation r : schedule) {
                if (!(checkIn.compareTo(r.checkOutDate) > 0 || checkOut.compareTo(r.checkInDate) < 0 )) {
                    return false;
                }
            }
        }   return true;
    }

    public Room getRoom(String roomNumber, Double price, RoomType enumeration) {
        return new Room(roomNumber, price, enumeration);
    }

    public void setRoomSchedule(Reservation r) {
        schedule.add(r);
    }
    @Override
    public String toString() {
        return "room Number: " + roomNumber + " " +
                "price: " + price + " " +
                "type: " + enumeration;
    }
}
