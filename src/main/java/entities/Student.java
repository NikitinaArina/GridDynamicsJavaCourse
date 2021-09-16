package entities;

import util.DateFormatter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Student {
    private String name;

    private Curriculum curriculum;

    private LocalDate startDate;
    private LocalDateTime endDate;

    private List<Course> courses;

    private int workDayFrom;
    private int workDayTo;

    public Student(String name, Curriculum curriculum, LocalDate startDate, List<Course> courses) {
        this.workDayFrom = 10;
        this.workDayTo = 18;
        this.name = name;
        this.curriculum = curriculum;
        this.startDate = startDate;
        this.courses = courses;
        this.endDate = getEndOfCourses();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurriculum() {
        return curriculum.getDesc();
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public String getStartDate() {
        return startDate.format(DateFormatter.dateFormatter);
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getWorkDayFrom() {
        return workDayFrom;
    }

    public void setWorkDayFrom(int workDayFrom) {
        this.workDayFrom = workDayFrom;
    }

    public int getWorkDayTo() {
        return workDayTo;
    }

    public void setWorkDayTo(int workDayTo) {
        this.workDayTo = workDayTo;
    }

    public String getEndDate() {
        return endDate.format(DateFormatter.dateFormatter);
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getWorkingTime() {
        return String.format("From %s to %s", workDayFrom, workDayTo);
    }

    public int getTotalDurationOfCourses() {
        int total = 0;
        for (Course c : courses) {
            total += c.getDuration();
        }
        return total;
    }

    public LocalDateTime getEndOfCourses() {
        int totalDuration = getTotalDurationOfCourses();

        LocalDateTime endDate = startDate.atStartOfDay();
        endDate = endDate.withHour(workDayFrom);
        endDate = endDate.withMinute(0);
        endDate = endDate.withSecond(0);

        while (totalDuration > 0) {

            while (endDate.getDayOfWeek() == DayOfWeek.SATURDAY || endDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                endDate = endDate.plusDays(1);
                endDate = endDate.withHour(workDayFrom);
            }

            if (endDate.getHour() > workDayTo - 1) {
                endDate = endDate.plusDays(1);
                endDate = endDate.withHour(workDayFrom);
            } else {
                endDate = endDate.plusHours(1);
                totalDuration--;
            }
        }
        return endDate;
    }

    public String getFullReport() {
        return String.format(getReport() + "\nWorking time: %s \nStart date: %s \n" +
                        "Program duration: %s \nCourses: %s \nEnd date: %s \nInfo about progress: ",
                getWorkingTime(),
                getStartDate(),
                getTotalDurationOfCourses(),
                getCourses(),
                getEndDate()
        );
    }

    private String getReport() {
        return String.format("%s (%s) ", getName(), getCurriculum());
    }
    public String getShortReport() {
        return getReport();
    }
}
