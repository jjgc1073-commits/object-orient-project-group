package DataBase.DTO;

import java.time.LocalDate;

public class UserStudentDTO extends UserDTO{

    public UserStudentDTO(int id, String name, String email, String password, LocalDate birthDate){
        super(id, name, email, password, birthDate);
        
    } 
    
}
