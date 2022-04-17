package service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TemaXMLRepository;
import validation.TemaValidator;

import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceAddTema {
    TemaXMLRepository temaRepository;
    Service service;

    String NULL = null;
    String EMPTY_STRING = "";

    int LESS_THAN_ONE = 0;
    int MORE_THAN_FOURTEEN = 15;
    int DEADLINE = 2;
    int STARTLINE = 1;

    String ID = "id";
    String DESCRIPTION = "description";

    int FAILURE = 1;
    int SUCCESS = 0;

    String FILE_PATH = "test_files/testAddTema.xml";

    @BeforeEach
    public void SetUp() {
        temaRepository = new TemaXMLRepository(new TemaValidator(), FILE_PATH);
        service = new Service(null, temaRepository, null);
    }

    @AfterEach
    public void TearDown() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, false);
        fw.write("<xml/>");
        fw.close();
    }

    @Test
    public void saveTema_nullId_failureResult() {
        int result = service.saveTema(NULL, DESCRIPTION, DEADLINE, STARTLINE);

        assertEquals(FAILURE, result);
    }

    @Test
    public void saveTema_emptyStringId_failureResult() {
        int result = service.saveTema(EMPTY_STRING, DESCRIPTION, DEADLINE, STARTLINE);

        assertEquals(FAILURE, result);
    }

    @Test
    public void saveTema_nullDescription_failureResult() {
        int result = service.saveTema(ID, NULL, DEADLINE, STARTLINE);

        assertEquals(FAILURE, result);
    }

    @Test
    public void saveTema_emptyStringDescription_failureResult() {
        int result = service.saveTema(ID, EMPTY_STRING, DEADLINE, STARTLINE);

        assertEquals(FAILURE, result);
    }

    @Test
    public void saveTema_lessThanOneDeadline_failureResult() {
        int result = service.saveTema(ID, DESCRIPTION, LESS_THAN_ONE, STARTLINE);

        assertEquals(FAILURE, result);
    }

    @Test
    public void saveTema_moreThanFourteenDeadline_failureResult() {
        int result = service.saveTema(ID, DESCRIPTION, MORE_THAN_FOURTEEN, STARTLINE);

        assertEquals(FAILURE, result);
    }

    @Test
    public void saveTema_AdvancedDeadline_failureResult() {
        int result = service.saveTema(ID, DESCRIPTION, STARTLINE, DEADLINE);

        assertEquals(FAILURE, result);
    }

    @Test
    public void saveTema_lessThanOneStartline_failureResult() {
        int result = service.saveTema(ID, DESCRIPTION, DEADLINE, LESS_THAN_ONE);

        assertEquals(FAILURE, result);
    }
    
    @Test
    public void saveTema_moreThanFourteenStartline_failureResult() {
        int result = service.saveTema(ID, DESCRIPTION, DEADLINE, MORE_THAN_FOURTEEN);

        assertEquals(FAILURE, result);
    }

    @Test
    public void saveTema_AlreadyExisting_failureResult() {
        service.saveTema(ID, DESCRIPTION, DEADLINE, STARTLINE);
        int result = service.saveTema(ID, DESCRIPTION, DEADLINE, STARTLINE);
        assertEquals(FAILURE, result);
    }
}
