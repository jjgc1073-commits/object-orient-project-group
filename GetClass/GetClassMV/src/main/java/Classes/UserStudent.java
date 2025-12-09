package Classes;

import java.time.LocalDate;

public class UserStudent extends User {

    public StudentInfo studentInfo;

    public UserStudent(String name, LocalDate birthDate, String email, String password) {
        super(name, birthDate, email, password);
        this.role = "STUDENT";
    }

    public void setId(int id){
        this.id = id;
    }


}