package service.interfaces;

import models.Student;

import java.time.LocalDateTime;

public interface StudentService {
    int getTotalDurationOfCourses(Student student);

    LocalDateTime getEndOfCourses(Student student);
}
