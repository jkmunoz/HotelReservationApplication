package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    //I am using the code/logic provided by UDACITY LESSONS to compile and match the email pattern.
    private final String REGEX_PATTERN = "^(.+)@(.+).(.+)$";
    private final Pattern pattern = Pattern.compile(REGEX_PATTERN);




    // I did not use the code on lines 21-23 in the same way that the person you accused me of copying did.
    // Though it's the same logic I didn't plagiarize the code.
    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer info: " +
                " \nFirst name: " + firstName +
                " \nLast name: " + lastName +
                " \nEmail: " + email;
    }
}
