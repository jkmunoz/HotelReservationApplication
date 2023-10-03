package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static ReservationService INSTANCE = null;

    public static ReservationService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReservationService();
        }
        return INSTANCE;
    }
    public List<Reservation> reservations;
    public ReservationService() {
        reservations = new ArrayList<>();
    }
    public static Map<String, IRoom> rooms = new HashMap<String, IRoom>();
    public static HashSet<String> hashSetKeys = new HashSet<String>(rooms.keySet());
    public static HashSet<IRoom> hashSetValues = new HashSet<IRoom>(rooms.values());

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);
    }


    public IRoom getARoom(String roomNumber) {
        return rooms.get(roomNumber);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }


    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;
    }



    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        Collection<IRoom> availableRooms = new ArrayList<>();

        ArrayList<IRoom> roomsFromHashMap = new ArrayList<>(rooms.values());


        for (IRoom room : roomsFromHashMap) {
            String roomNumber = room.getRoomNumber();
            boolean vacant;

            vacant = isVacant(roomNumber, checkInDate, checkOutDate);

            if (vacant)
                availableRooms.add(room);
        }
        if (availableRooms.isEmpty()) {
            System.out.println("There are no rooms available.");
        }
        return availableRooms;
    }

    private boolean isVacant(String roomNumber, Date checkInDate, Date checkOutDate) {
//        boolean vacant = true;

        for(Reservation reservation : reservations) {
            if (reservation.getRoom().equals(roomNumber) && reservation.getCheckInDate().compareTo(checkOutDate) <= 0 && reservation.getCheckOutDate().compareTo(checkInDate) >= 0) {
                return false;
            }
        }
        return true;
    }
//            if (reservation.getRoom().getRoomNumber().equals(roomNumber)) {
                //Two booking cannot start or end at the same time, for the same room.
//                if (reservation.getCheckInDate().equals(checkInDate) || reservation.getCheckOutDate().equals(checkOutDate)) {
//                    vacant = false;
//                    break;
//                    //Cannot book within the span of another for the same room.
//                } else if (checkInDate.before(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckOutDate())) {
//                    vacant = false;
//                    break;
//                    //No over-lapping bookings.
//                } else if (checkInDate.after(reservation.getCheckInDate()) && checkInDate.before(reservation.getCheckOutDate())) {
//                    vacant = false;
//                    break;
//                    //Same reason as above, just for check-in.
//                } else if (checkOutDate.after(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckOutDate())) {
//                    vacant = false;
//                    break;
//                }
//            }
//        }
//        return vacant;
//    }


    public Collection<Reservation> getCustomersReservation(Customer customer) {
        ArrayList<Reservation> customerReservations = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }


    public void printAllReservation() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations have been made.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println(reservation);
            }
        }
    }
}











//package service;
//
//import model.Customer;
//import model.IRoom;
//import model.Reservation;
//
//import java.util.*;
//
//public class ReservationService {
//
//    private static ReservationService INSTANCE = null;
//
//    private ReservationService() {
//    }
//
//    public static ReservationService getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new ReservationService();
//        }
//        return INSTANCE;
//    }
//    static ArrayList<Reservation> reservations = new ArrayList<>();
//    static Map<String, IRoom> rooms = Collections.synchronizedMap(new HashMap<>());
//
//    public void addRoom(IRoom room) {
//        rooms.put(room.getRoomNumber(), room);
//    }
//
//
//    public IRoom getARoom(String roomID) {
//        return rooms.get(roomID);
//    }
//
//    public Collection<IRoom> getAllRooms() {
//        return rooms.values();
//    }
//
//
//    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
//        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
//        reservations.add(reservation);
//        return reservation;
//    }
//
//
//
//    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
//        Collection<IRoom> availableRooms = new ArrayList<>();
//
//        ArrayList<IRoom> roomsFromHashMap = new ArrayList<>(rooms.values());
//
//
//        for (IRoom room : roomsFromHashMap) {
//            String roomNumber = room.getRoomNumber();
//            boolean vacant;
//
//            vacant = isVacant(roomNumber, checkInDate, checkOutDate);
//
//            if (vacant)
//                availableRooms.add(room);
//        }
//        if (availableRooms.isEmpty()) {
//            System.out.println("There are no rooms available.");
//        }
//        return availableRooms;
//    }
//
//    private boolean isVacant(String roomNumber, Date checkInDate, Date checkOutDate) {
//        boolean vacant = true;
//
//        for(Reservation reservation : reservations) {
//            if (reservation.getRoom().getRoomNumber().equals(roomNumber)) {
//                //Two booking cannot start or end at the same time, for the same room.
//                if (reservation.getCheckInDate().equals(checkInDate) || reservation.getCheckOutDate().equals(checkOutDate)) {
//                    vacant = false;
//                    break;
//                    //Cannot book within the span of another for the same room.
//                } else if (checkInDate.before(reservation.getCheckInDate()) && checkOutDate.after(reservation.getCheckOutDate())) {
//                    vacant = false;
//                    break;
//                    //No over-lapping bookings.
//                } else if (checkInDate.after(reservation.getCheckInDate()) && checkInDate.before(reservation.getCheckOutDate())) {
//                    vacant = false;
//                    break;
//                    //Same reason as above, just for check-in.
//                } else if (checkOutDate.after(reservation.getCheckInDate()) && checkOutDate.before(reservation.getCheckOutDate())) {
//                    vacant = false;
//                    break;
//                }
//            }
//        }
//        return vacant;
//    }
//
//
//    public Collection<Reservation> getCustomersReservation(Customer customer) {
//         ArrayList<Reservation> customerReservations = new ArrayList<>();
//
//        for (Reservation reservation : reservations) {
//            if (reservation.getCustomer().equals(customer)) {
//                customerReservations.add(reservation);
//            }
//        }
//        return customerReservations;
//    }
//
//
//    public void printAllReservation() {
//        if (reservations.isEmpty()) {
//            System.out.println("No reservations have been made.");
//        } else {
//            for (Reservation reservation : reservations) {
//                System.out.println(reservation);
//            }
//        }
//    }
//}
//
//
