package service.impl;

import models.Course;
import models.Student;
import service.interfaces.StudentService;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class StudentServiceImpl implements StudentService {

    @Override
    public int getTotalDurationOfCourses(Student student) {
        int total = 0;
        for (Course c : student.getCourses()) {
            total += c.getDuration();
        }
        return total;
    }

    @Override
    public LocalDateTime getEndOfCourses(Student student) {
        int totalDuration = getTotalDurationOfCourses(student);

        LocalDateTime endDate = student.getStartDate().atStartOfDay();
        endDate = endDate.withHour(student.getWorkDayFrom());
        endDate = endDate.withMinute(0);
        endDate = endDate.withSecond(0);

        while (totalDuration > 0) {
            while (endDate.getDayOfWeek() == DayOfWeek.SATURDAY || endDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                endDate = endDate.plusDays(1);
                endDate = endDate.withHour(student.getWorkDayFrom());
            }

            if (endDate.getHour() > student.getWorkDayTo() - 1) {
                endDate = endDate.plusDays(1);
                endDate = endDate.withHour(student.getWorkDayFrom());
            } else {
                endDate = endDate.plusHours(1);
                totalDuration--;
            }
        }
        return endDate;
    }
}
