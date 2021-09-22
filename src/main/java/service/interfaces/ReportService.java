package service.interfaces;

import exceptions.DateException;
import models.Student;

import java.time.LocalDateTime;

public interface ReportService {
    String getFullReport(Student student);

    String getReport(Student student);

    String getInfoProgressOfCourses(Student student, LocalDateTime timeOfReportGeneration) throws DateException;

    int getDiffBetweenDays(Student student, LocalDateTime timeOfReportGeneration);

    int getDiffBetweenHours(Student student, LocalDateTime timeOfReportGeneration);
}
