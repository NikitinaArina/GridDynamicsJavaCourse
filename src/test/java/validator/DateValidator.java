package validator;

import util.DateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
    private DateTimeFormatter dateFormatter;

    public DateValidator(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    public boolean isValid(String date) {
        try {
            LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
