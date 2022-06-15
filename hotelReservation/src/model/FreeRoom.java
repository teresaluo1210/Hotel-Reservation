package model;

public class FreeRoom extends Room{

    public FreeRoom(String roomNumber, Double price, RoomType enumeration){
        super(roomNumber,price,enumeration);
        this.price = 0.0;
    }
//    get methods

    @Override
    public boolean isFree() {return true; }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + '\'' +
                ", price: " + "FREE!" +
                ", type" + enumeration ;
    }
}
