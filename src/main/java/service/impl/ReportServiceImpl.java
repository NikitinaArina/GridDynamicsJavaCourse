package service.impl;

import exceptions.DateException;
import models.Student;
import report.Reporter;
import service.interfaces.ReportService;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

import static java.lang.Math.abs;

public class ReportServiceImpl implements ReportService {
    @Override
    public String getFullReport(Student student) {
        return String.format(getReport(student) + "\nWorking time: %s \nStart date: %s \n" +
                        "Program duration: %s \nCourses: %s \nEnd date: %s \nInfo about progress: ",
                student.getWorkingTime(),
                student.getStartDateAsString(),
                student.getTotalDurationOfCourses(),
                student.getCourse(),
                student.getEndDateAsString()
        );
    }

    @Override
    public String getReport(Student student) {
        return String.format("%s (%s) ", student.getName(), student.getCurriculum());
    }

    @Override
    public String getInfoProgressOfCourses(Student student, LocalDateTime timeOfReportGeneration) throws DateException {
        if (student.getStartDate().isAfter(ChronoLocalDate.from(timeOfReportGeneration)))
            throw new DateException("The date can not be early than " + student.getStartDate().format(Reporter.DATE_FORMATTER));
        if (student.getStartDate().isEqual(ChronoLocalDate.from(timeOfReportGeneration)))
            return "You just started!";
        if (student.getEndOfCourses().isAfter(timeOfReportGeneration)) {
            return String.format("Training is not finished. %s days %s hours are left until the end.\n",
                    getDiffBetweenDays(student, timeOfReportGeneration), getDiffBetweenHours(student, timeOfReportGeneration));
        } else
            return String.format("Training completed. %s days %s hours have passed since the end.\n",
                    getDiffBetweenDays(student, timeOfReportGeneration), getDiffBetweenHours(student, timeOfReportGeneration));
    }

    @Override
    public int getDiffBetweenDays(Student student, LocalDateTime timeOfReportGeneration) {
        return abs(student.getEndDate().getDayOfYear() - timeOfReportGeneration.getDayOfYear());
    }

    @Override
    public int getDiffBetweenHours(Student student, LocalDateTime timeOfReportGeneration) {
        return abs(student.getEndDate().getHour() - timeOfReportGeneration.getHour());
    }
}
