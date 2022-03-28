package validation;
import domain.Student;

public class StudentValidator implements Validator<Student> {
    public void validate(Student student) throws ValidationException {
        if (student.getID() == null || student.getID().equals("")) {
            throw new ValidationException("ID invalid! \n");
        }
        if (student.getNume() == null || student.getNume().equals("")) {
            throw new ValidationException("Nume invalid! \n");
        }
        if (student.getGrupa() <= 110 || student.getGrupa() >= 938) {
            throw new ValidationException("Grupa invalida! \n");
        }
        if (hasZeros(student.getGrupa()))
            throw new ValidationException("Grupa invalida! \n");


    }

    private boolean hasZeros(int grupa) {
        if (grupa == 0) return true;

        while (grupa > 0) {
            if (grupa % 10 == 0)
                return true;
            grupa /= 10;
        }
        return false;
    }
}

