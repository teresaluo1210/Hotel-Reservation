import api.AdminResource;
import api.HotelResource;
import model.*;
import java.util.Random;

public class TestRoom {
    public static final int ROOMSIZE = 3;
    public static void main(String[] args) {
        for (int i = 1; i < ROOMSIZE; i++) {
            IRoom room;
            Random r = new Random();
            double price = r.nextInt(101) + 100 ;
            RoomType type = RoomType.values()[r.nextInt(RoomType.values().length)];
            if (price == 0) {
                room = new FreeRoom(Integer.toString(i), price, type);
            } else {
                room = new Room(Integer.toString(i), price, type);
            }
            AdminResource.getInstance().addRoom(room);
        }
        HotelResource.getInstance().createACustomer("cy@berkeley.edu","Curtis", "Yu");
        HotelResource.getInstance().createACustomer("t@gmail.com", "Teresa", "Luo");
        MainMenu.main(null);
    }
}
