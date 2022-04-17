package service;

import org.junit.jupiter.api.Test;
import repository.StudentXMLRepository;
import validation.StudentValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ServiceTest {

    StudentXMLRepository studentRepository;
    Service service;

    @BeforeEach
    public void SetUp() {
        studentRepository = new StudentXMLRepository(new StudentValidator(), "test_files/testSaveStudent.xml");
        service = new Service(studentRepository, null, null);
    }

    @Test
    public void saveStudent_correctInput_correctlyCreated() {
        String id = "1";
        String nume = "Ana";
        int grupa = 221;

        int success = service.saveStudent(id, nume, grupa);

        assertEquals(0, success);
    }

    @Test
    public void saveStudent_invalidInput_errorThrown() {
        String id = null;
        String nume = "Ana";
        int grupa = 221;

        int success = service.saveStudent(id, nume, grupa);

        assertEquals(1, success);
    }
}