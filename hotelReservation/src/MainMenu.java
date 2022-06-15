import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.regex.*;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

public class MainMenu {
    //    Scanner for user input
//    Handle errors:IllegalArgumentException
    public static AdminResource _admin = AdminResource.getInstance();
    public static HotelResource _hotel = HotelResource.getInstance();

    //regex check variables
    public static String emailRegex = "^(.+)@(.+).(.+)$";
    public static Pattern pattern = Pattern.compile(emailRegex);

    //  return to main menu
    public static void returnToMain(Scanner scanner) {
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            MainMenu.main(null);
        }
    }
//    subMethods for findAndReserve
    public static boolean checkDate (Date date) {
        Calendar calendar = Calendar.getInstance();
        Date newDate = new java.util.Date();
        calendar.setTime(newDate);
        Date currentDate = calendar.getTime();
        if ((date.compareTo(currentDate)) == -1) {
            return false;
        } else {
            return true;
        }
    }
    public static Date stringToDate (String string) throws ParseException {
        return new SimpleDateFormat("MM/dd/yyyy").parse(string);
    }

    public static Date addDate (Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_WEEK,7);
        return calendar.getTime();
    }

    public static void makeReservation (Scanner scanner, String email, Date checkInDate, Date checkOutDate){
        System.out.println("Room number of desired room");
        String roomId = scanner.nextLine();
        IRoom desiredRoom = _hotel.getRoom(roomId);
        _hotel.bookARoom(email, desiredRoom, checkInDate, checkOutDate);

        Customer customer = _admin.getCustomer(email);
        System.out.println("Room booked! Please confirm your information and reservation: \n" +
                "First Name: " + customer.getFirstName() + " Last Name: " + customer.getLastName() +
                " Email: " + email + "\n" +
                "Reserved Room Number: " + roomId + "\n" +
                "Check In Date: " + checkInDate + " " + "Check Out Date: " + checkOutDate);
    }
    public static void findAndReserve (Scanner scanner) throws ParseException {
        System.out.println("Please enter your email: ");
        boolean emailForm = true;
        String email = null;
        while (emailForm) {
            String enteredEmail = scanner.nextLine();
            if (pattern.matcher(enteredEmail).matches()) {
                email = enteredEmail;
                emailForm = false;
            } else {
                System.out.println("Error! Invalid email. Please enter your email in correct format");
            }
        }

        if (!_admin.getAllAccounts().contains(email)) {
            System.out.println("Error! Account not in system.\nc-Create account\nany other key-return to main menu ");
            String choice = scanner.nextLine();
            if (choice.equals("c")) {
                MainMenu.createAccount(scanner);
            } else {
                MainMenu.returnToMain(scanner);
            }
        } else {
            Date checkInDate = null;
            boolean checkIn = true;
            while (checkIn) {
                System.out.println("Please enter desired check in date (mm/dd/yyyy): ");
                String checkInString = scanner.nextLine();
                Date enteredDate = MainMenu.stringToDate(checkInString);
                if (MainMenu.checkDate(enteredDate)) {
                    checkInDate = enteredDate;
                    checkIn = false;
                } else {
                    System.out.println("Error! Invalid date. Please enter a date that is at least the current date or after.");
                }
            }

            Date checkOutDate = null;
            boolean checkOut = true;
            while (checkOut) {
                System.out.println("Please enter desired check out date (mm/dd/yyyy): ");
                String checkOutString = scanner.nextLine();
                Date enteredDate2 = MainMenu.stringToDate(checkOutString);
                if (MainMenu.checkDate(enteredDate2)) {
                    checkOutDate = enteredDate2;
                    checkOut = false;
                } else {
                    System.out.println("Error! Invalid date. Please enter a date that is at least the current date or after.");
                }
            }

            if (!_hotel.findARoom(checkInDate, checkOutDate).isEmpty()) {
                System.out.println("Here are all available rooms based on entered dates: ");
                ArrayList<IRoom> availableRooms = (ArrayList<IRoom>) _hotel.findARoom(checkInDate, checkOutDate);
                for (IRoom r : availableRooms) {
                    System.out.println(r);
                }
                System.out.println("Would you like to reserve a room?\ny-Yes\nany other key-no, return to Main Menu ");
                String reserve = scanner.nextLine();
                if (reserve.equals("y")) {
                    MainMenu.makeReservation(scanner, email, checkInDate, checkOutDate);
                } else {
                    MainMenu.returnToMain(scanner);
                }
            } else {
                Date newCheckInDate = addDate(checkInDate);
                Date newCheckOutDate = addDate(checkOutDate);

                if(!_hotel.findARoom(newCheckInDate, newCheckOutDate).isEmpty()) {
                    System.out.println("Oops! Seems like there are no available rooms within the date range provided.\n" +
                            "Here are the recommended rooms for the date range a week after: ");
                    ArrayList<IRoom> newAvailableRooms = (ArrayList<IRoom>) _hotel.findARoom(newCheckInDate, newCheckOutDate);
                    for (IRoom r : newAvailableRooms) {
                        System.out.println(r);
                    }
                    System.out.println("Would you like to reserve a room during the recommended date range?\ny-yes" +
                            "\nany other key-no, returns to Main Menu");
                    String moreReservation = scanner.nextLine();
                    if (moreReservation.equals("n")) {
                        MainMenu.makeReservation(scanner, email, newCheckInDate, newCheckOutDate);
                    } else {
                        MainMenu.returnToMain(scanner);
                    }
                } else {
                    System.out.println("Oops! Seems like there are no available rooms within this date range either." +
                            "We apologize for the inconvenience. Press any key to go back to Main Menu.");
                }
            }
        }
    }
    public static void seeCustomerReservations(Scanner scanner) {
        System.out.println("Please enter your email: ");
        boolean emailForm = true;
        String email = null;
        while (emailForm) {
            String enteredEmail = scanner.nextLine();
            if (pattern.matcher(enteredEmail).matches()) {
                email = enteredEmail;
                emailForm = false;
            } else {
                System.out.println("Error! Invalid email. Please enter your email in correct format");
            }
        }

        Collection <Reservation> customerReservation = _hotel.getCustomersReservations(email);
        if (customerReservation != null) {
            if (!customerReservation.isEmpty()) {
                for (Reservation r : _hotel.getCustomersReservations(email)) {
                    System.out.println(r);
                }
            } else {
                System.out.println("There are currently no reservations.");
            }
            System.out.println("Press any key to go back to main menu");
            MainMenu.returnToMain(scanner);
        } else {
        System.out.println("Error! Customer does not exist.\n" +
                "Press any key to go back to main menu");
        MainMenu.returnToMain(scanner);
    }
}

    public static void createAccount (Scanner scanner) {
        System.out.println("Please enter your first name");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name");
        String lastName = scanner.nextLine();

        System.out.println("Please enter your email: ");
        boolean emailForm = true;
        String userEmail = null;
        while (emailForm) {
            String enteredEmail = scanner.nextLine();
            if (pattern.matcher(enteredEmail).matches()) {
                userEmail = enteredEmail;
                emailForm = false;
            } else {
                System.out.println("Error! Invalid email. Please enter your email in correct format");
            }
        }

        if (_hotel.createACustomer(userEmail, firstName, lastName)) {
            System.out.println("Account successfully created!");
        } else {
            System.out.println("Error! Account already exists in system. Account creation failed.\n" +
                    "Press any key to go back to main menu");
            MainMenu.returnToMain(scanner);
        }

    }

    public static void main(String[] args) {
//        variables
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                System.out.println("Welcome to T's Hotel Reservation System! ");
                System.out.println("Please enter an integer from 1-5 for your service: ");
                System.out.println("------------------------");
                System.out.println("1. Find and reserve a room");
                System.out.println("2. See my reservations");
                System.out.println("3. Create an account");
                System.out.println("4. Admin");
                System.out.println("5. Exit");
                int input = Integer.parseInt(scanner.nextLine());

                switch (input) {
                    case 1:
                        MainMenu.findAndReserve(scanner);
                        break;
                    case 2:
                        MainMenu.seeCustomerReservations(scanner);
                        break;
                    case 3:
                        MainMenu.createAccount(scanner);
                        break;
                    case 4:
                        AdminMenu.main(null);
                        break;
                    case 5:
                        System.exit(0);
                    default:
                        System.out.println("Please enter an integer between 1 and 5");
                }
            } catch (Exception ex) {
                System.out.println("\nError - Invalid Input\n");
            }
        }
    }

}
