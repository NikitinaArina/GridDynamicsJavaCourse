package util;

import java.time.format.DateTimeFormatter;

public class DateFormatter {
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy - EEEE");
    public static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d MMMM yyyy - EEEE (hh:mm)");
}
