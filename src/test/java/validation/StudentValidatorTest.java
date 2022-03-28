package validation;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentValidatorTest {

    Validator<Student> studentValidator;
    Student student;

    String VALID_STUDENT_NAME = "studentName";
    String VALID_STUDENT_ID = "STUDENT_ID";
    Integer VALID_GROUP = 934;

    @BeforeEach
    public void setUp() {
        studentValidator = new StudentValidator();
        student = new Student(
                VALID_STUDENT_ID,
                VALID_STUDENT_NAME,
                VALID_GROUP);
    }

    @Test
    public void validateStudent_lessThanThreeDigitsGroup_invalidStudent() {
        int lessThenThreeDigitsGroup = 12;
        student.setGrupa(lessThenThreeDigitsGroup);
        assertThrows(ValidationException.class, () -> studentValidator.validate(student));
    }

    @Test
    public void validateStudent_moreThanThreeDigitsGroup_invalidStudent() {
        int lessThenThreeDigitsGroup = 1234;
        student.setGrupa(lessThenThreeDigitsGroup);
        assertThrows(ValidationException.class, () -> studentValidator.validate(student));
    }

    @Test
    public void validateStudent_exactlyThreeDigitsGroup_invalidStudent() {
        int lessThenThreeDigitsGroup = 123;
        student.setGrupa(lessThenThreeDigitsGroup);
        assertDoesNotThrow(() -> studentValidator.validate(student));
    }

    @Test
    public void validateStudent_negativeNumberGroup_invalidStudent() {
        int negativeNumberGroup = -123;
        student.setGrupa(negativeNumberGroup);
        assertThrows(ValidationException.class, () -> studentValidator.validate(student));
    }

    @Test
    public void validateStudent_lastTwoDigitsZeroGroup_invalidStudent() {
        int lastTwoDigitsZero = 900;
        student.setGrupa(lastTwoDigitsZero);
        assertThrows(ValidationException.class, () -> studentValidator.validate(student));
    }

    @Test
    public void validateStudent_thirdDigitZeroGroup_invalidStudent() {
        int thirdDigitZero = 910;
        student.setGrupa(thirdDigitZero);
        assertThrows(ValidationException.class, () -> studentValidator.validate(student));
    }

    @Test
    public void validateStudent_secondDigitZeroGroup_invalidStudent() {
        int secondDigitZeroGroup = 901;
        student.setGrupa(secondDigitZeroGroup);
        assertThrows(ValidationException.class, () -> studentValidator.validate(student));
    }

    @Test
    public void validateStudent_GroupIsZero_invalidStudent() {
        int ZERO = 0;
        student.setGrupa(ZERO);
        assertThrows(ValidationException.class, () -> studentValidator.validate(student));
    }

    @Test
    public void validateStudent_MultipleZeroDigitsGroup_invalidStudent() {
        int multipleZeros = 90000;
        student.setGrupa(multipleZeros);
        assertThrows(ValidationException.class, () -> studentValidator.validate(student));
    }
}