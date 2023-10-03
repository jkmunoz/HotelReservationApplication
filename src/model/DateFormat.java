package model;

import java.util.regex.Pattern;


//https://www.baeldung.com/java-date-regular-expressions
public class DateFormat implements DateMatcher {

   //Users must use MM/DD/YYYY.
    public Pattern DATE_PATTERN = Pattern.compile(
            "^\\d{2}/\\d{2}/\\d{4}$");

    @Override
    public boolean matches(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }
}
