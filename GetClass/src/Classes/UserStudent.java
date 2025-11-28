import java.time.LocalDate;

public class UserStudent extends User {

    public UserStudent(String name, LocalDate birthDate, String email, String password) {
        super(name, birthDate, email, password);
        this.role = "STUDENT";
    }

    
}