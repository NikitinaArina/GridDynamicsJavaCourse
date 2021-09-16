package tests;

import entities.Course;
import entities.Curriculum;
import entities.Student;
import org.junit.Before;
import org.junit.Test;
import util.DateFormatter;
import validator.DateValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentTest {
    private static final int YEAR = 2021;
    private static final int MONTH = 9;
    private static final int END_DAY_OF_MONTH = 28;
    private static final int START_DAY_OF_MONTH = 10;
    private static final int HOUR = 14;
    private static final int MINUTE = 0;
    private static final int DURATION = 40;
    private static final int SECOND_DURATION = 20;

    private Student student;
    private LocalDateTime endDate;

    @Before
    public void setUp() {
        Course course = new Course("Java for testing", DURATION);
        Course course1 = new Course("Spring for testing", DURATION);
        Course course2 = new Course("GIT", SECOND_DURATION);
        endDate = LocalDateTime.of(YEAR, MONTH, END_DAY_OF_MONTH, HOUR, MINUTE);
        student = new Student("Ivan Petrov", Curriculum.JavaDeveloper, LocalDate.of(YEAR, MONTH, START_DAY_OF_MONTH), List.of(course, course1, course2));
    }

    @Test
    public void defineCorrectEndDate() {
        LocalDateTime endOfCourses = student.getEndOfCourses();
        assertEquals(endDate, endOfCourses);
    }

    @Test
    public void dateValidatorWorkCorrect() {
        DateValidator dateValidator = new DateValidator(DateFormatter.dateFormatter);
        assertTrue(dateValidator.isValid(student.getEndDate()));
        assertTrue(dateValidator.isValid(student.getStartDate()));
    }
}
