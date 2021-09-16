import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

import static java.lang.Math.abs;

public class Main {
    public static void main(String[] args) {
        Course course = new Course("Java for testing", 24);
        Course course1 = new Course("Spring for testing", 32);
        Student student = new Student("Petr", Curriculum.JavaDeveloper, LocalDate.of(2021, 9, 8), new ArrayList<>(Arrays.asList(course, course1)));
        Report.getReport(student, 1);
        Report.getReport(student, 0);
    }
}
