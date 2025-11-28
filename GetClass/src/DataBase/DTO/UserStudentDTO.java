package DataBase.DTO;

import java.time.LocalDate;

public class UserStudentDTO extends UserDTO{

    public UserStudentDTO(int id, String name, LocalDate birthDate, String email, int age, String password, String role){
        super(id, name, birthDate, email, age, password, role);
        
    } 
    
}
