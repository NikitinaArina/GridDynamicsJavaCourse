package tests;

import models.Course;
import models.Curriculum;
import models.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import service.impl.StudentServiceImpl;
import service.interfaces.StudentService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class StudentTest {

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {new Student("Ivan Petrov", Curriculum.JavaDeveloper,
                        LocalDate.of(2021, 9, 10),
                        List.of(new Course("Java for testing", 40),
                                new Course("Spring for testing", 40),
                                new Course("GIT", 20))),
                        LocalDateTime.of(2021, 9, 28, 14, 0)}
        };
    }

    private Student student;
    private final StudentService studentService = new StudentServiceImpl();
    private LocalDateTime endDate;

    public StudentTest(Student student, LocalDateTime endDate) {
        this.student = student;
        this.endDate = endDate;
    }

    @Test
    public void defineCorrectEndDate() {
        LocalDateTime endOfCourses = studentService.getEndOfCourses(student);
        assertEquals("date of end training not correct", endDate, endOfCourses);
    }
}
