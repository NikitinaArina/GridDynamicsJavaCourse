package models;

import lombok.Data;
import report.Reporter;
import service.impl.StudentServiceImpl;
import service.interfaces.StudentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Student {

    private String name;
    private Curriculum curriculum;
    private LocalDate startDate;
    private LocalDateTime endDate;
    private List<Course> courses;
    private int workDayFrom;
    private int workDayTo;

    private StudentService studentService = new StudentServiceImpl();

    public Student(String name, Curriculum curriculum, LocalDate startDate, List<Course> courses) {
        this.workDayFrom = 10;
        this.workDayTo = 18;
        this.name = name;
        this.curriculum = curriculum;
        this.startDate = startDate;
        this.courses = courses;
        this.endDate = getEndOfCourses();
    }

    public String getStartDateAsString() {
        return startDate.format(Reporter.DATE_FORMATTER);
    }

    public String getEndDateAsString() {
        return endDate.format(Reporter.DATE_TIME_FORMATTER);
    }

    public String getWorkingTime() {
        return String.format("From %s to %s", workDayFrom, workDayTo);
    }

    public LocalDateTime getEndOfCourses() {
        return studentService.getEndOfCourses(this);
    }

    public int getTotalDurationOfCourses() {
        return studentService.getTotalDurationOfCourses(this);
    }

    public String getCourse() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Course c: courses) {
            stringBuilder.append(c.toString());
        }
        return String.valueOf(stringBuilder);
    }
}
