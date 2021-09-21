package tests;

import exceptions.DateException;
import models.Course;
import models.Curriculum;
import models.Student;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import report.Reporter;
import service.impl.ReportServiceImpl;
import service.interfaces.ReportService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ReportTest {
    private ReportService reportService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        reportService = new ReportServiceImpl();
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {new Student("Igor", Curriculum.AQA,
                        LocalDate.of(2021, 9, 10),
                        List.of(new Course("Java course", 30))),
                        LocalDateTime.of(2021, 9, 15, 16, 0)
                }
        };
    }

    private Student student;
    private LocalDateTime date;

    public ReportTest(Student student, LocalDateTime date) {
        this.student = student;
        this.date = date;
    }

    @Test
    public void getValidInfoAboutProgress() throws DateException {
        String infoProgressOfCourses = reportService.getInfoProgressOfCourses(student, date);
        assertTrue("info about courses progress not correct", infoProgressOfCourses.contains("0 days 0 hours"));
    }

    @Test
    public void getInvalidInfoAboutProgress() throws DateException {
        String infoProgressOfCourses = reportService.getInfoProgressOfCourses(student, LocalDateTime.of(2021, 9, 28, 15, 0));
        assertFalse("info about courses progress not correct", infoProgressOfCourses.contains("1 days 3 hours"));
    }

    @Test
    public void getDateException() throws DateException {
        expectedException.expect(DateException.class);
        expectedException.expectMessage("The date can not be early than " + student.getStartDate().format(Reporter.DATE_FORMATTER));
        reportService.getInfoProgressOfCourses(student, LocalDateTime.of(2020, 5, 1, 15, 0));
    }

    @Test
    public void getInfoAboutProgressWithStartDate() throws DateException {
        String infoProgressOfCourses = reportService.getInfoProgressOfCourses(student, LocalDateTime.of(student.getStartDate(), LocalTime.of(10, 0)));
        assertTrue("info about courses progress not correct", infoProgressOfCourses.equalsIgnoreCase("You just started!"));
    }
}
