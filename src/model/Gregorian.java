package model;

import java.util.regex.Pattern;

//https://www.baeldung.com/java-date-regular-expressions
public class Gregorian implements DateMatcher {

    //guards calendar for leap year, 30 day months, and 31 day months.
    public static Pattern DATE_PATTERN = Pattern.compile(
            "^(02/29/(2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26])))$"
                    + "|^(02/(0[1-9]|1[0-9]|2[0-8])/((19|2[0-9])[0-9]{2}))$"
                    + "|^((0[13578]|10|12)/(0[1-9]|[12][0-9]|3[01])/((19|2[0-9])[0-9]{2}))$"
                    + "|^((0[469]|11)/  (0[1-9]|[12][0-9]|30)/((19|2[0-9])[0-9]{2}))$");

    @Override
    public boolean matches(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }
}
