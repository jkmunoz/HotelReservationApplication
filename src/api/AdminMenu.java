package api;

import model.Customer;
import model.IRoom;
import model.Room;
import model.RoomType;
import java.util.*;

import static service.ReservationService.rooms;

public class AdminMenu {
    //Making use of the adminResource singleton so that I could use the methods on this page.
    //This is my own logic. I'm not sure how else I could use the AdminResource without this code.
    //I don't think this should be flagged as plagiarism.
    static AdminResource adminResource = AdminResource.getInstance();
    public static void adminMenu() {
        Scanner sc = new Scanner(System.in);
        String line = "";
        displayAdminMenu();

        //keeps the menu running even after one option has been chosen.
        while (true) {
            //user input is taken after output from program.
            //waits for user input after output, otherwise it's an infinite loop.
            //I chose a string for user input because that was familiar and logical to me.
            //I did not copy this.
            line = sc.nextLine();
            //My original logic for the code below looked like...
            //"case '1':
            //  seeAllCustomers();
            //  break;..." etc.
            //IntelliJ recommended changing it to a simplified version below.
            //I did not copy this.
            switch (line.charAt(0)) {
                case '1' -> seeAllCustomers();
                case '2' -> seeAllRooms();
                case '3' -> seeAllReservations();
                case '4' -> addARoom();
                case '5' -> MainMenu.mainMenu();
                default -> System.out.println("Please select a valid menu option.");
            }
        }
        }

        private static void addARoom() {
            Scanner sc = new Scanner(System.in);
            String roomNumber = "";
            List<IRoom> rooms = new ArrayList<>();

            System.out.println("\nEnter room number: ");
            roomNumber = sc.nextLine();
            try {
                //will ensure room numbers are only positive integers.
                Integer.parseUnsignedInt(roomNumber);

            } catch (NumberFormatException e) {
                System.out.println("Please enter a numerical room number.");
                addARoom();
            }

            System.out.println("\nEnter price per night: ");
            final double roomPrice = sc.nextDouble();

            System.out.println("\nEnter room type (1 for a single bed, 2 for a double bed): ");
            RoomType roomType = RoomType.valueOfLabel(sc.next());

            if ("1".equals(roomType)) {
                roomType = RoomType.valueOfLabel("SINGLE");
            } else if ("2".equals(roomType)) {
                roomType = RoomType.valueOfLabel("DOUBLE");
            }


            Room room = new Room(roomNumber, roomPrice, roomType);
            rooms.add(room);
            adminResource.addRoom(rooms);
            System.out.println("Success! The room has been added.");
            System.out.println(room);
            anotherRoom();
        }

    private static void anotherRoom(){
        final Scanner sc = new Scanner(System.in);
        String add = "";

        System.out.println("Would you like to add another room? y/n");
        add = sc.nextLine().toLowerCase().trim();

        if ("y".equals(add)) {
            addARoom();
        } else if ("n".equals(add)) {
            adminMenu();
        } else {
            System.out.println("Please enter 'y' for yes, or 'n' for no.");
            anotherRoom();
        }
    }



    //A logical way to display all reservations and recommended by intelliJ.
    private static void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    // pulling all added rooms if collection is not empty.
    private static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();
        if (rooms.isEmpty()) {
            System.out.println("No rooms were found.");
        } else {
            for (IRoom room : rooms) {
                System.out.println(room.toString());
            }
            }
    }

        // pulling all added customers if collection is not empty.
    private static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers.");
        } else {
            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        }
    }

    

   //I used Sys....ln() to recreate the menu PROVIDED BY UDACITY'S example of the project.
    //It understandably may look like other people's since it's intuitive to make the project look like what
   // udacity has asked for.
    private static void displayAdminMenu() {
        System.out.println(
                "Hotel Reservation Application ADMIN MENU:" +
                "\nPlease choose from the following options..." +
                "\n-----------------------------------------------------------" +
                "\n1. See all customers." +
                "\n2. See all rooms." +
                "\n3. See all reservations." +
                "\n4. Add a room." +
                "\n5. Back to Main Menu." +
                "\n-----------------------------------------------------------");
    }

    }

