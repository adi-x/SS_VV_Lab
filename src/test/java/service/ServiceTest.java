package service;

import org.junit.jupiter.api.Test;
import repository.StudentRepository;
import repository.StudentXMLRepository;
import validation.StudentValidator;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    void saveStudent_correctInput_correctlyCreated() {
        StudentXMLRepository studentRepository = new StudentXMLRepository(new StudentValidator(), "test_files/testSaveStudent.xml");
        Service service = new Service(studentRepository, null, null);
        String id = "1";
        String nume = "Ana";
        int grupa = 221;

        int success = service.saveStudent(id, nume, grupa);

        assertEquals(0, success);
    }

    @Test
    void saveStudent_invalidInput_errorThrown() {
        StudentXMLRepository studentRepository = new StudentXMLRepository(new StudentValidator(), "test_files/testSaveStudent.xml");
        Service service = new Service(studentRepository, null, null);
        String id = null;
        String nume = "Ana";
        int grupa = 221;

        int success = service.saveStudent(id, nume, grupa);

        assertEquals(1, success);
    }
}