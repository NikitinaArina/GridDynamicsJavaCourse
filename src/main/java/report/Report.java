package report;

import entities.Student;
import util.DateFormatter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Math.abs;

public class Report {
    private static final LocalDateTime timeOfReportGeneration = LocalDateTime.now();

    public static void getReport(Student student, int parameter) {
        System.out.println("Time of report generation: " + timeOfReportGeneration.format(DateFormatter.dateTimeFormatter));
        if (parameter == 1) {
            fullReport(student);
        } else shortReport(student);
        getInfoProgressOfCourses(student);
    }

    public static void getReport(Student student) {
        getReport(student, 0);
    }

    private static void fullReport(Student student) {
        System.out.print(student.getFullReport());
    }

    private static void shortReport(Student student) {
        System.out.print(student.getShortReport());
    }

    private static int getDiffBetweenDays(LocalDateTime endDate) {
        return abs(endDate.getDayOfYear() - timeOfReportGeneration.getDayOfYear());
    }

    private static int getDiffBetweenHours(LocalDateTime endDate) {
        return abs(endDate.getHour() - timeOfReportGeneration.getHour());
    }

    private static void getInfoProgressOfCourses(Student student) {
        if (student.getEndOfCourses().isBefore(timeOfReportGeneration)) {
            System.out.println(String.format("Training is not finished. %s d %s hours are left until the end.\n",
                    getDiffBetweenDays(student.getEndOfCourses()), getDiffBetweenHours(student.getEndOfCourses())));
        } else
            System.out.println(String.format("Training completed. $s days %s hours have passed since the end.\n",
                    getDiffBetweenDays(student.getEndOfCourses()), getDiffBetweenHours(student.getEndOfCourses())));
    }
}
