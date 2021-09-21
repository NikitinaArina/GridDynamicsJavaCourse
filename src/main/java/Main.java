import models.Course;
import models.Curriculum;
import models.Student;
import report.Reporter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Student> students = List.of(new Student("Ivan Ivanov", Curriculum.JavaDeveloper,
                    LocalDate.of(2020, 6, 1),
                    List.of(new Course("Java", 16), new Course("Spring", 16), new Course("JDBC", 24))),
            new Student("Ivan Ivanov", Curriculum.AQA,
                    LocalDate.of(2020, 6, 1),
                    List.of(new Course("Test design", 10), new Course("Page Object", 16),
                            new Course("Selenium", 16))));
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input date (yyyy-mm-dd)");
        String date = scanner.nextLine();
        System.out.println("Input time (hh:mm)");
        String time = scanner.nextLine();
        System.out.println("Input parameter for type of report (1 - full report, no parameter/0 - short)");
        int parameter = scanner.nextInt();
        scanner.close();
        LocalDateTime dateTime = LocalDateTime.parse(date + "T" + time);
        Reporter report = new Reporter(dateTime);
        report.getReport(students, parameter);
    }
}
