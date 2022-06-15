package model;

import java.util.*;

public interface IRoom {
    public String getRoomNumber();

    public Double getRoomPrice();

    public RoomType getRoomType();

    public boolean isFree();

    public boolean isAvailable(Date checkIn, Date checkOut);

    public void setRoomSchedule (Reservation r);






}
