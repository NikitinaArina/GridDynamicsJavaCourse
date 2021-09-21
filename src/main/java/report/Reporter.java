package report;

import exceptions.DateException;
import models.Student;
import service.impl.ReportServiceImpl;
import service.interfaces.ReportService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Reporter {

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("d MMMM yyyy - EEEE");
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMMM yyyy - EEEE (hh:mm)");
    private LocalDateTime TIME_OF_REPORT_GENERATION;
    private ReportService reportService = new ReportServiceImpl();

    public Reporter(LocalDateTime TIME_OF_REPORT_GENERATION) {
        this.TIME_OF_REPORT_GENERATION = TIME_OF_REPORT_GENERATION;
    }

    public void getReport(Student student, int parameter) {
        System.out.println("Time of report generation: " + TIME_OF_REPORT_GENERATION.format(DATE_TIME_FORMATTER));
        if (parameter == 1) {
            fullReport(student);
        } else shortReport(student);
        try {
            System.out.println(reportService.getInfoProgressOfCourses(student, TIME_OF_REPORT_GENERATION));
        } catch (DateException e) {
            e.printStackTrace();
        }
    }

    public void getReport(List<Student> studentList, int parameter) {
        for(Student s: studentList) {
            getReport(s, parameter);
        }
    }

    public void getReport(Student student) {
        getReport(student, 0);
    }

    public void getReport(List<Student> studentList) {
        for(Student s: studentList) {
            getReport(s, 0);
        }
    }

    private void fullReport(Student student) {
        System.out.print(reportService.getFullReport(student));
    }

    private void shortReport(Student student) {
        System.out.print(reportService.getReport(student));
    }
}
