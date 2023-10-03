package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static CustomerService INSTANCE = null;
    private final Map<String, Customer> customers = new HashMap<>();
    private CustomerService() {
    }
    public static CustomerService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CustomerService();
        }
        return INSTANCE;
    }


    public void addCustomer(String firstName, String lastName, String email) {
        try {
            Customer customer = new Customer(firstName, lastName, email);
            customers.put(email, customer);
        } catch (IllegalArgumentException e) {
            System.out.println("Customer was not added. Try entering a valid email.");
        }
    }


    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }


    public Collection<Customer>getAllCustomers() {
        return customers.values();
    }

}


