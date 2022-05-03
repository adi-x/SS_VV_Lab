package service;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ServiceTest {

    StudentXMLRepository studentRepository;
    TemaXMLRepository assignmentRepository;
    NotaXMLRepository gradeRepository;
    Service service;

    @BeforeEach
    public void SetUp() {
        studentRepository = new StudentXMLRepository(new StudentValidator(), "test_files/testSaveStudent.xml");
        assignmentRepository = new TemaXMLRepository(new TemaValidator(), "test_files/testSaveAssignment.xml");
        gradeRepository = new NotaXMLRepository(new NotaValidator(), "test_files/testSaveGrade.xml");
        service = new Service(studentRepository, assignmentRepository, gradeRepository);
    }

    @AfterEach
    void AfterEach() {
        StudentXMLRepository studentRepository = new StudentXMLRepository(new StudentValidator(), "test_files/testSaveStudent.xml");
        TemaXMLRepository assignmentRepository = new TemaXMLRepository(new TemaValidator(), "test_files/testSaveAssignment.xml");
        NotaXMLRepository gradeRepository = new NotaXMLRepository(new NotaValidator(), "test_files/testSaveGrade.xml");

        StreamSupport.stream(studentRepository.findAll().spliterator(), false)
                .map(Student::getID)
                .collect(Collectors.toList())
                .forEach(studentRepository::delete);
        StreamSupport.stream(assignmentRepository.findAll().spliterator(), false)
                .map(Tema::getID)
                .collect(Collectors.toList())
                .forEach(assignmentRepository::delete);
        StreamSupport.stream(gradeRepository.findAll().spliterator(), false)
                .map(Nota::getID)
                .collect(Collectors.toList())
                .forEach(gradeRepository::delete);
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

    @Test
    public void bigbang_addStudent() {
        String id = "1234";
        String nume = "Ana";
        int grupa = 221;

        int success = service.saveStudent(id, nume, grupa);

        assertEquals(0, success);
    }

    @Test
    public void bigbang_addAssignment() {
        String id = "1234";
        String descriere = "random stuff";
        int deadline = 5;
        int startline = 3;

        int success = service.saveTema(id, descriere, deadline, startline);

        assertEquals(0, success);
    }

    @Test
    public void bigbang_addGrade() {
        String idStudent = "1234";
        String idAssignment = "1234";
        int valNota = 5;
        int predata = 3;
        String feedback = "nothing important";
        service.saveStudent("1234", "Ana", 221);
        service.saveTema("1234", "random stuff", 5, 3);


        int success = service.saveNota(idStudent, idAssignment, valNota, predata, feedback);

        assertEquals(0, success);
    }

    @Test
    public void bigbang_addAll() {
        String idStudent = "1234";
        String nume = "Ana";
        int grupa = 221;

        String idAssignment = "1234";
        String descriere = "random stuff";
        int deadline = 5;
        int startline = 3;

        int valNota = 5;
        int predata = 3;
        String feedback = "nothing important";


        int successStudent = service.saveStudent(idStudent, nume, grupa);
        int successAssignment = service.saveTema(idAssignment, descriere, deadline, startline);
        int successGrade = service.saveNota(idStudent, idAssignment, valNota, predata, feedback);

        assertEquals(0, successStudent);
        assertEquals(0, successAssignment);
        assertEquals(0, successGrade);
    }

    @Test
    public void integration_addStudent() {
        String id = "1234";
        String nume = "Vasile";
        int grupa = 221;

        int success = service.saveStudent(id, nume, grupa);

        assertEquals(0, success);
    }

    @Test
    public void integration_addAssignment() {
        String studentId = "1234";
        String studentNume = "Vasile";
        int studentGrupa = 221;

        int studentSuccess = service.saveStudent(studentId, studentNume, studentGrupa);

        String assignmentId = "1234";
        String assignmentDescriere = "random stuff";
        int assignmentDeadline = 5;
        int assignmentStartline = 3;

        int assignmentSuccess = service.saveTema(assignmentId, assignmentDescriere, assignmentDeadline, assignmentStartline);

        assertEquals(0, studentSuccess);
        assertEquals(0, assignmentSuccess);
    }

    @Test
    public void integration_addGrade() {
        String studentId = "1234";
        String studentNume = "Vasile";
        int studentGrupa = 221;

        int studentSuccess = service.saveStudent(studentId, studentNume, studentGrupa);

        String assignmentId = "1234";
        String assignmentDescriere = "random stuff";
        int assignmentDeadline = 5;
        int assignmentStartline = 3;

        int assignmentSuccess = service.saveTema(assignmentId, assignmentDescriere, assignmentDeadline, assignmentStartline);

        int valNota = 5;
        int predata = 10;
        String feedback = "nothing important";
        int success = service.saveNota(studentId, assignmentId, valNota, predata, feedback);

        assertEquals(0, studentSuccess);
        assertEquals(0, assignmentSuccess);
        assertEquals(0, success);
    }

}