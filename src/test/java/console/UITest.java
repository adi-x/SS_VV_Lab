package console;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.Service;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class UITest {

    Service mockedService;
    UI ui;

    @BeforeEach
    public void setUp() {
        mockedService = Mockito.mock(Service.class);
        ui = new UI(mockedService);
    }


    @Test
    void saveTema_correctInput_validMessage() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("a\nb\n1\n2".getBytes()));
        Mockito.when(mockedService.saveTema("a", "b", 1, 2)).thenReturn(1);
        ui.uiSaveTema();
        output.flush();
        String actualOutput = output.toString();

        assertTrue(actualOutput.contains("Tema adaugata cu succes!"));
    }

    @Test
    void saveTema_incorrectInput_invalidMessage() throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        System.setOut(new PrintStream(output));
        System.setIn(new ByteArrayInputStream("a\nb\n1\n2".getBytes()));
        Mockito.when(mockedService.saveTema("a", "b", 1, 2)).thenReturn(0);
        ui.uiSaveTema();
        output.flush();
        String actualOutput = output.toString();

        assertTrue(actualOutput.contains("Tema existenta sau invalida!"));
    }
}