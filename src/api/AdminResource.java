
package api;


import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.*;

public class AdminResource {

    private static AdminResource INSTANCE;
    private static final CustomerService customerService = CustomerService.getInstance();
    private static final ReservationService reservationService = ReservationService.getInstance();
    private AdminResource() {
    }

    public static AdminResource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AdminResource();
        }

        return INSTANCE;
    }

    public Customer getCustomer(String email) {
        return customerService.getCustomer(email);
    }

    public void addRoom(List<IRoom> rooms) {
        for (IRoom room : rooms) {
            reservationService.addRoom(room);
        }
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }
}

