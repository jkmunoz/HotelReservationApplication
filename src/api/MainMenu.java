package api;

import model.*;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;



public class MainMenu {
    //Used https://www.baeldung.com/java-singleton
    private static final HotelResource hotelResource = HotelResource.getInstance();

    private static final ReservationService reservationService = ReservationService.getInstance();
    static final String emailRegex = "^(.+)@(.+).(.+)$";
    static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        String input = "";

        do {
            try {
                displayMainMenu();
                //Keeps the scanner open and ready to receive input.
                input = sc.nextLine();
                switch (input.charAt(0)) {
                    case '1':
                        findAndReserveARoom();
                        break;
                    case '2':
                        seeMyReservations();
                        break;
                    case '3':
                        createAnAccount();
                        break;
                    case '4':
                        AdminMenu.adminMenu();
                        break;
                    case '5':
                        System.out.println("Exiting application");
                        break;
                    default:
                        System.out.println("Please select a valid menu option.");
                        mainMenu();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid action.\n");
            }
        } while (input.length() == 0);
        sc.close();
    }

    private static void findAndReserveARoom() throws ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your check-in date. (ex: 05/01/2023) ");
        String checkIn = dateFormat.format(new Date(sc.nextLine()));
        Date checkInDate = dateFormat.parse(checkIn);


        System.out.println("Enter your check-out date. (ex: 05/01/2023) ");
        String checkOut = dateFormat.format(new Date(sc.nextLine()));
        Date checkOutDate = dateFormat.parse(checkOut);

//        if (!(checkIn.matches("MM/dd/yyyy") || checkOut.matches("MM/dd/yyyy"))) {
//            System.out.println("Please re-enter dates in the following format: (mm/dd/yyyy)");
//            findAndReserveARoom();
//        }


        System.out.println("Do you have an account with us? y/n");
        String hasAccount = sc.nextLine().toLowerCase().trim();

        if ("n".equals(hasAccount)) {
            System.out.println("You must first create an account.");
            createAnAccount();

        } else if ("y".equals(hasAccount)) {
            System.out.println("Please enter your email.");
            final String customerEmail = sc.nextLine();

            if (hotelResource.getCustomer(customerEmail) == null) {
                System.out.println("There is not an account associated with that email.");
                mainMenu();
            } else {
                Collection<IRoom> availableRooms = hotelResource.findARoom(checkInDate, checkOutDate);
                boolean roomChoice = true;

                System.out.println("Here is a list of our available rooms...");
                printRooms(availableRooms);

                if (availableRooms.isEmpty()) {
                    mainMenu();
                } else {
                    do {
                        System.out.println("Please enter the room number you would like to reserve.");
                        String roomNum = sc.next();

                        for (IRoom room : availableRooms) {
                            if (room.getRoomNumber().equals(roomNum)) {
                                roomChoice = true;
                                Reservation reservation =
                                        hotelResource.bookARoom(customerEmail, room, checkInDate, checkOutDate);


                                System.out.println("Your reservation has been created!");
                                System.out.println(reservation);
                                mainMenu();
                            }
                        }
                    } while (!roomChoice);
                }
            }
        }
    }

    private static void printRooms(final Collection<IRoom> rooms) {
        if (rooms.isEmpty()) {
            System.out.println("No rooms found.");
        } else {
            rooms.forEach(System.out::println);
        }
    }


        private static void seeMyReservations() {
            Scanner sc = new Scanner(System.in);
            System.out.println("Do you have an account with us? y/n");
            String hasAccount = sc.nextLine().toLowerCase().trim();

            if ("n".equals(hasAccount)) {
                System.out.println("You must first create an account.");
                createAnAccount();

            } else if ("y".equals(hasAccount)) {
                System.out.println("Please enter your email.");
                final String customerEmail = sc.nextLine();
                if (hotelResource.getCustomer(customerEmail) == null) {
                    System.out.println("There is not an account associated with that email.");
                    mainMenu();
                } else {
                    Collection<Reservation> reservations = hotelResource.getCustomersReservations(customerEmail);

                    if (reservations.isEmpty()) {
                        System.out.println("You do not have any reservations yet.");
                        mainMenu();
                    } else {
                        System.out.println(reservations);
                        mainMenu();
                    }
                }
            }
        }


        private static void createAnAccount() {
            Scanner sc = new Scanner(System.in);
            //Asking questions to fill in parameters for createACustomer.
            System.out.println("Enter first name");
            String firstName = sc.nextLine();
            System.out.println("Enter last name");
            String lastName = sc.nextLine();
            System.out.println("Enter email");
            String email = sc.nextLine();
            Pattern pattern = Pattern.compile(emailRegex);
            //checking that the email is using the valid format.
            if (pattern.matcher(email).matches()) {
                //Logically creating and storing the new customer using the instance of hotelResource,
                // and sending a message to the user.
                //This pattern (request input, get input, confirmation message, redirect to main menu/page)
                // is very common across websites. Please consider this as a logical way to set up the project.
                // It is NOT intended plagiarism.
                hotelResource.createACustomer(firstName, lastName, email);
                System.out.println("Your account has been created!");
                mainMenu();
            } else {
                System.out.println("please enter a valid email (yourname@domain.com)");
                createAnAccount();
            }
        }


    //I used Sys....ln() to recreate the menu PROVIDED BY UDACITY'S example of the project.
    //It understandably may look like other people's since it's intuitive to make the project look like what
    // udacity has asked for.
            public static void displayMainMenu() {
                System.out.println("Welcome to the Hotel Reservation application!" +
                        "\n Please choose from the following options..." +
                        "\n-----------------------------------------------------------" +
                        "\n1. Find and reserve a room." +
                        "\n2. See my reservations." +
                        "\n3. Create an account." +
                        "\n4. Admin Menu." +
                        "\n5. Exit." +
                        "\n-----------------------------------------------------------");
            }
    }
