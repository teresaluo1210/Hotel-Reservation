

import java.util.*;
import java.util.regex.*;
import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;

public class AdminMenu {
    public static void returnAdminMenu (Scanner scanner) {
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            AdminMenu.main(null);
        }
    }
    public static void addRoom (Scanner scanner) {
        String roomNumber = null;
        System.out.println("Please enter a room number (integer): ");
        String input = scanner.nextLine();
        Matcher m = Pattern.compile("^\\d+$").matcher(input);
        if (m.matches()) {
            roomNumber = input;
        } else {
            System.out.println("Error! Please enter an integer for room number. Press any key to return to Admin Menu");
            AdminMenu.returnAdminMenu(scanner);
        }

        System.out.println("Please enter a price (Format 00.00):");
        String input2 = scanner.nextLine();
        Double price = null;
        Matcher m2 = Pattern.compile("\\d{1,13}(\\.\\d*)?").matcher(input2);
        if (m2.matches()) {
            price = Double.parseDouble(input2);
        } else {
            System.out.println("Error! Please enter price in correct format (00.00) Press any key to return to Admin Menu");
            AdminMenu.returnAdminMenu(scanner);
        }

        System.out.println("Please enter a room type: \n s-Single \n d-Double");
        String type = scanner.nextLine();
        RoomType roomType = null;
        if (type.charAt(0) == 's') {
            roomType = RoomType.SINGLE;
        } else if (type.charAt(0) == 'd') {
            roomType = RoomType.DOUBLE;
        } else {
            System.out.println("Error! Invalid input. Please press any key to return to Admin Menu");
            AdminMenu.returnAdminMenu(scanner);
        }
        Room r = new Room(roomNumber, price, roomType);
        _admin.addRoom(r);

        System.out.println("Press any key to return to Admin Menu");
        AdminMenu.returnAdminMenu(scanner);
    }
    public static AdminResource _admin = AdminResource.getInstance();
    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println("Please enter an integer from 1-5 for your service: ");
                System.out.println("------------------------");
                System.out.println("1. See all Customers");
                System.out.println("2. See all Rooms");
                System.out.println("3. See all Reservations");
                System.out.println("4. Add a Room");
                System.out.println("5. Back to Main Menu");
                Scanner scanner = new Scanner(System.in);
                int input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1:
                        if (!_admin.getAllCustomers().isEmpty()) {
                            for (Customer c : _admin.getAllCustomers()) {
                                System.out.println(c);
                            }
                        } else {
                            System.out.println("There are currently no customers.");
                        }
                        break;
                    case 2:
                        if (!_admin.getAllRooms().isEmpty()) {
                            Iterator<IRoom> allRoomIter = _admin.getAllRooms().iterator();
                            while (allRoomIter.hasNext()) {
                                System.out.println(allRoomIter.next());
                            }
                        } else {
                            System.out.println("There are currently no rooms in the reservation system" );
                        }
                        break;
                    case 3:
                        _admin.displayAllReservations();
                        break;
                    case 4:
                        AdminMenu.addRoom(scanner);
                        break;
                    case 5:
                        MainMenu.main(null);
                    default:
                        System.out.println("Please enter an integer between 1 and 5");
                }
            } catch (Exception ex) {
                System.out.println("\nError - Invalid Input\n");
            }
        }
    }
}

